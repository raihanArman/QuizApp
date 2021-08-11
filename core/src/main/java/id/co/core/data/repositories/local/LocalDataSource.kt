package id.co.core.data.repositories.local

import id.co.room.dao.QuizDao
import id.co.room.entity.QuizResultEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LocalDataSource(
    private val quizDao: QuizDao
) {

    fun saveQuestionResult(quiz: QuizResultEntity): Long {
        return quizDao.insertQuizResult(quiz)
    }

    fun isNumberExists(number: Int): Boolean{
        return quizDao.isNumberIsExist(number)
    }

    fun updateQuizResult(quiz: QuizResultEntity): Int{
        return quizDao.updateQuizResult(quiz)
    }

    fun getQuizById(number: Int): QuizResultEntity{
        return quizDao.getQuizById(number)
    }

    fun getQuizResult(): List<QuizResultEntity>{
        return quizDao.getQuizResultAll()
    }

    fun deleteAllAnswer(){
        return quizDao.deleteQuizResultAll()
    }

}