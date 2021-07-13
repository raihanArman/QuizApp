package id.co.core.data.repositories

import id.co.core.data.model.Category
import id.co.core.data.model.Materi
import id.co.core.data.model.Users
import id.co.core.data.network.ResponseState
import id.co.core.data.repositories.remote.RemoteDataSource
import id.co.core.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class DataRepository(
    private val remote: RemoteDataSource
): Repository {
    override fun getUsersById(id: String): Flow<ResponseState<Users>> {
        return remote.getUsersById(id)
    }

    override fun getMateri(): Flow<ResponseState<List<Materi>>> {
        return remote.getMateri()
    }

    override fun getCategory(): Flow<ResponseState<List<Category>>> {
        return remote.getCategory()
    }
}