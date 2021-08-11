package id.co.room

import androidx.room.Database
import androidx.room.RoomDatabase
import id.co.room.dao.QuizDao
import id.co.room.entity.QuizResultEntity


@Database(
    entities =[
        QuizResultEntity::class
    ], version = 1, exportSchema = false
)
abstract class QuizDatabase: RoomDatabase() {
    abstract fun quizResultDao(): QuizDao
}