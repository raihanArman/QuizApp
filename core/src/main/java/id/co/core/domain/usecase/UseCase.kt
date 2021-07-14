package id.co.core.domain.usecase

import id.co.core.data.model.Category
import id.co.core.data.model.Materi
import id.co.core.data.model.Quiz
import id.co.core.data.model.Users
import id.co.core.data.network.ResponseState
import kotlinx.coroutines.flow.Flow

interface UseCase {
    fun getUsersById(id: String): Flow<ResponseState<Users>>
    fun getMateri(): Flow<ResponseState<List<Materi>>>
    fun getCategory(): Flow<ResponseState<List<Category>>>
    fun getQuizSearch(search: String): Flow<ResponseState<List<Quiz>>>
}