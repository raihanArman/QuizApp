package id.co.core.di

import com.google.gson.GsonBuilder
import id.co.core.BuildConfig
import id.co.core.data.network.ApiService
import id.co.core.data.repositories.DataRepository
import id.co.core.data.repositories.local.LocalDataSource
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
    val repositoryModule = module{
        single {
            RemoteDataSource(get(), get())
        }
        single {
            LocalDataSource(get())
        }
        single<Repository> {
            DataRepository(
                get(),
                get()
            )
        }
    }

}