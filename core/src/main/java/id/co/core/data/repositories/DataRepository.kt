package id.co.core.data.repositories

import id.co.core.data.model.*
import id.co.core.data.network.ResponseState
import id.co.core.data.repositories.local.LocalDataSource
import id.co.core.data.repositories.remote.RemoteDataSource
import id.co.core.domain.repository.Repository
import id.co.room.entity.QuizResultEntity
import kotlinx.coroutines.flow.Flow

class DataRepository(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource
): Repository {
    override fun getUsersById(): Flow<ResponseState<Users>> {
        return remote.getUsersById()
    }

    override fun getPopularPath(): Flow<ResponseState<List<Materi>>> {
        return remote.getPathPopular()
    }

    override fun getPathByCategory(id: String): Flow<ResponseState<List<Materi>>> {
        return remote.getMateriByCategory(id)
    }

    override fun getMateri(): Flow<ResponseState<List<Materi>>> {
        return remote.getMateri()
    }

    override fun getCategory(): Flow<ResponseState<List<Category>>> {
        return remote.getCategory()
    }

    override fun getQuizSearch(search: String): Flow<ResponseState<List<Quiz>>> {
        return remote.getQuizSearch(search)
    }

    override fun getQuizByPath(id: String): Flow<ResponseState<List<Quiz>>> {
        return remote.getQuizByPath(id)
    }

    override fun loginUser(email: String, password: String): Flow<ResponseState<Users>> {
        return remote.loginUser(email, password)
    }

    override fun logoutUser(): Flow<ResponseState<String>> {
        return remote.logoutUser()
    }

    override fun registerUser(
        email: String,
        name: String,
        password: String
    ): Flow<ResponseState<Users>> {
        return remote.registerUser(email, name, password)
    }


    override fun getBabByMateri(id: String): Flow<ResponseState<List<Chapter>>> {
        return remote.getBabByMateri(id)
    }

    override fun getContentById(id: String): Flow<ResponseState<Chapter>> {
        return remote.getContentById(id)
    }

    override fun getQuestion(id: String): Flow<ResponseState<List<Question>>> {
        return remote.getQuestion(id)
    }

    override fun saveQuizResult(quiz: QuizResultEntity): Long {
        return local.saveQuestionResult(quiz)
    }

    override fun isNumberExists(number: Int): Boolean {
        return local.isNumberExists(number)
    }

    override fun updateQuizResult(quiz: QuizResultEntity): Int {
        return local.updateQuizResult(quiz)
    }

    override fun getQuizById(number: Int): QuizResultEntity {
        return local.getQuizById(number)
    }

    override fun getQuizResult(): List<QuizResultEntity> {
        return local.getQuizResult()
    }

    override fun deleteAllAnswer() {
        return local.deleteAllAnswer()
    }

    override fun addHistory(
        quizId: String,
        score: Int,
        correct: Int,
        incorrect: Int,
        noAnswered: Int
    ): Flow<ResponseState<History>> {
        return remote.addHistory(quizId, score, correct, incorrect, noAnswered)
    }

    override fun getHistoryByUser(): Flow<ResponseState<List<History>>> {
        return remote.getHistoryByUser()
    }

    override fun getQuizCheck(quizId: String): Flow<ResponseState<Check>> {
        return remote.getQuizCheck(quizId)
    }
}