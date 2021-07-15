package id.co.quizapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import id.co.core.di.CoreModule.networkModule
import id.co.core.di.CoreModule.repositoryModule
import id.co.datastore.module.DataStoreModule.dataStoreModule
import id.co.quizapp.module.AppModule.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class QuizApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@QuizApplication)
            modules(listOf(
                dataStoreModule,
                networkModule,
                repositoryModule,
                useCaseModule
            ))
        }
    }
}