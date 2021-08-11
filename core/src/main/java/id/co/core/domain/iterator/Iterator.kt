package id.co.core.domain.iterator

import id.co.core.data.model.*
import id.co.core.data.network.ResponseState
import id.co.core.domain.repository.Repository
import id.co.core.domain.usecase.UseCase
import id.co.room.entity.QuizResultEntity
import kotlinx.coroutines.flow.Flow

class Iterator(
    private val repository: Repository
): UseCase {
    override fun getUsersById(): Flow<ResponseState<Users>> {
        return repository.getUsersById()
    }

    override fun getPopularPath(): Flow<ResponseState<List<Materi>>> {
        return repository.getPopularPath()
    }

    override fun getPathByCategory(id: String): Flow<ResponseState<List<Materi>>> {
        return repository.getPathByCategory(id)
    }

    override fun getMateri(): Flow<ResponseState<List<Materi>>> {
        return repository.getMateri()
    }

    override fun getCategory(): Flow<ResponseState<List<Category>>> {
        return repository.getCategory()
    }

    override fun getQuizSearch(search: String): Flow<ResponseState<List<Quiz>>> {
        return repository.getQuizSearch(search)
    }

    override fun getQuizByPath(id: String): Flow<ResponseState<List<Quiz>>> {
        return repository.getQuizByPath(id)
    }

    override fun loginUser(email: String, password: String): Flow<ResponseState<Users>> {
        return repository.loginUser(email, password)
    }

    override fun logoutUser(): Flow<ResponseState<String>> {
        return repository.logoutUser()
    }

    override fun registerUser(
        email: String,
        name: String,
        password: String
    ): Flow<ResponseState<Users>> {
        return repository.registerUser(email, name, password)
    }

    override fun getBabByMateri(id: String): Flow<ResponseState<List<Chapter>>> {
        return repository.getBabByMateri(id)
    }

    override fun getContentById(id: String): Flow<ResponseState<Chapter>> {
        return repository.getContentById(id)
    }

    override fun getQuestion(id: String): Flow<ResponseState<List<Question>>> {
        return repository.getQuestion(id)
    }

    override fun saveQuizResult(quiz: QuizResultEntity): Long {
        return repository.saveQuizResult(quiz)
    }

    override fun isNumberExists(number: Int): Boolean {
        return repository.isNumberExists(number)
    }

    override fun updateQuizResult(quiz: QuizResultEntity): Int {
        return repository.updateQuizResult(quiz)
    }

    override fun getQuizById(number: Int): QuizResultEntity {
        return repository.getQuizById(number)
    }

    override fun getQuizResult(): List<QuizResultEntity> {
        return repository.getQuizResult()
    }

    override fun deleteAllAnswer() {
        return repository.deleteAllAnswer()
    }

    override fun addHistory(
        quizId: String,
        score: Int,
        correct: Int,
        incorrect: Int,
        noAnswered: Int
    ): Flow<ResponseState<History>> {
        return repository.addHistory(quizId, score, correct, incorrect, noAnswered)
    }

    override fun getHistoryByUser(): Flow<ResponseState<List<History>>> {
        return repository.getHistoryByUser()
    }

    override fun getQuizCheck(quizId: String): Flow<ResponseState<Check>> {
        return repository.getQuizCheck(quizId)
    }


}