package id.co.core.domain.repository

import id.co.core.data.model.*
import id.co.core.data.network.ResponseState
import id.co.room.entity.QuizResultEntity
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getUsersById(): Flow<ResponseState<Users>>
    fun getPopularPath(): Flow<ResponseState<List<Materi>>>
    fun getPathByCategory(id: String): Flow<ResponseState<List<Materi>>>
    fun getMateri(): Flow<ResponseState<List<Materi>>>
    fun getCategory(): Flow<ResponseState<List<Category>>>
    fun getQuizSearch(search: String): Flow<ResponseState<List<Quiz>>>
    fun getQuizByPath(id: String): Flow<ResponseState<List<Quiz>>>

    fun loginUser(email: String, password: String): Flow<ResponseState<Users>>
    fun logoutUser(): Flow<ResponseState<String>>
    fun registerUser(email: String, name: String, password: String): Flow<ResponseState<Users>>
    fun getBabByMateri(id: String): Flow<ResponseState<List<Chapter>>>

    fun getContentById(id: String): Flow<ResponseState<Chapter>>
    fun getQuestion(id: String): Flow<ResponseState<List<Question>>>

    fun saveQuizResult(quiz: QuizResultEntity): Long
    fun isNumberExists(number: Int): Boolean
    fun updateQuizResult(quiz: QuizResultEntity): Int
    fun getQuizById(number: Int): QuizResultEntity
    fun getQuizResult(): List<QuizResultEntity>
    fun deleteAllAnswer(): Unit

    fun addHistory(
        quizId: String,
        score: Int,
        correct: Int,
        incorrect: Int,
        noAnswered: Int
    ): Flow<ResponseState<History>>
    fun getHistoryByUser(): Flow<ResponseState<List<History>>>

    fun getQuizCheck(quizId: String): Flow<ResponseState<Check>>

}

