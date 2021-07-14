package id.co.core.domain.iterator

import id.co.core.data.model.Category
import id.co.core.data.model.Materi
import id.co.core.data.model.Quiz
import id.co.core.data.model.Users
import id.co.core.data.network.ResponseState
import id.co.core.domain.repository.Repository
import id.co.core.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow

class Iterator(
    private val repository: Repository
): UseCase {
    override fun getUsersById(id: String): Flow<ResponseState<Users>> {
        return repository.getUsersById(id)
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

}