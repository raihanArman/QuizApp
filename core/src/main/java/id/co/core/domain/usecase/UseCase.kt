package id.co.core.domain.usecase

import id.co.core.data.model.*
import id.co.core.data.network.ResponseState
import kotlinx.coroutines.flow.Flow

interface UseCase {
    fun getUsersById(): Flow<ResponseState<Users>>
    fun getMateri(): Flow<ResponseState<List<Materi>>>
    fun getCategory(): Flow<ResponseState<List<Category>>>
    fun getQuizSearch(search: String): Flow<ResponseState<List<Quiz>>>

    fun loginUser(email: String, password: String): Flow<ResponseState<Users>>
    fun registerUser(email: String, name: String, password: String): Flow<ResponseState<Users>>
    fun getBabByMateri(id: String): Flow<ResponseState<List<Chapter>>>

    fun getContentById(id: String): Flow<ResponseState<Chapter>>
    fun getQuestion(id: String): Flow<ResponseState<List<Question>>>

}