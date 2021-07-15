package id.co.core.di

import com.google.gson.GsonBuilder
import id.co.core.data.network.ApiService
import id.co.core.data.repositories.DataRepository
import id.co.core.data.repositories.remote.RemoteDataSource
import id.co.core.domain.repository.Repository
import id.co.core.util.Constant.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object CoreModule {
    val networkModule = module{
        single {
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(300, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)
                .build()
        }
        single{
            GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create()
        }
        single {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(get()))
                .client(get())
                .build()
            retrofit.create(ApiService::class.java)
        }
    }

    val repositoryModule = module{
        single {
            RemoteDataSource(get(), get())
        }
        single<Repository> {
            DataRepository(
                get()
            )
        }
    }

}