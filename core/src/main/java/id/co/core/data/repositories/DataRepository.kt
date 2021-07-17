package id.co.core.data.repositories

import id.co.core.data.model.*
import id.co.core.data.network.ResponseState
import id.co.core.data.repositories.remote.RemoteDataSource
import id.co.core.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class DataRepository(
    private val remote: RemoteDataSource
): Repository {
    override fun getUsersById(): Flow<ResponseState<Users>> {
        return remote.getUsersById()
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

    override fun loginUser(email: String, password: String): Flow<ResponseState<Users>> {
        return remote.loginUser(email, password)
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
}