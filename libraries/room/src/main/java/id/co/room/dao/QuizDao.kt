package id.co.room.dao

import androidx.annotation.NonNull
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import id.co.room.entity.QuizResultEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizDao {
    @Query("SELECT * FROM quiz_result")
    fun getQuizResultAll(): List<QuizResultEntity>

    @Insert
    fun insertQuizResult(quizResultEntity: QuizResultEntity): Long

    @Query("SELECT EXISTS(SELECT * FROM quiz_result WHERE number = :number)")
    fun isNumberIsExist(number: Int) : Boolean

    @Query("SELECT * FROM quiz_result WHERE number = :number")
    fun getQuizById(number: Int) : QuizResultEntity

    @Query("SELECT * FROM quiz_result WHERE number = :number")
    fun getQuizResultByNumber(@NonNull number: Int): QuizResultEntity

    @Update
    fun updateQuizResult(@NonNull quizResultEntity: QuizResultEntity): Int

    @Query("DELETE FROM quiz_result")
    fun deleteQuizResultAll()

}