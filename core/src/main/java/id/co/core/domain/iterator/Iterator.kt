package id.co.core.domain.iterator

import id.co.core.data.model.*
import id.co.core.data.network.ResponseState
import id.co.core.domain.repository.Repository
import id.co.core.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow

class Iterator(
    private val repository: Repository
): UseCase {
    override fun getUsersById(): Flow<ResponseState<Users>> {
        return repository.getUsersById()
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

    override fun loginUser(email: String, password: String): Flow<ResponseState<Users>> {
        return repository.loginUser(email, password)
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

}