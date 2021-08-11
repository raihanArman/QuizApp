package id.co.room.module

import androidx.room.Room
import id.co.room.QuizDatabase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object DatabaseModule {
    val databaseModule = module {
        factory {
            get<QuizDatabase>().quizResultDao()
        }
        single {
            val passphrase = SQLiteDatabase.getBytes("mantap".toCharArray())
            val factory = SupportFactory(passphrase)

            Room.databaseBuilder(
                androidContext(),
                QuizDatabase::class.java, "Quiz.db"
            ).fallbackToDestructiveMigration()
                .openHelperFactory(factory)
                .allowMainThreadQueries()
                .build()
        }
    }
}