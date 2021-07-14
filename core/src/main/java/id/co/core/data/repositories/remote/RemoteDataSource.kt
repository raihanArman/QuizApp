package id.co.core.data.repositories.remote

import id.co.core.data.model.Category
import id.co.core.data.model.Materi
import id.co.core.data.model.Quiz
import id.co.core.data.model.Users
import id.co.core.data.network.ApiService
import id.co.core.data.network.ResponseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(
    private val apiService: ApiService
) {
    fun getUsersById(id: String): Flow<ResponseState<Users>>{
        return flow{
            emit(ResponseState.Loading())
            try{
                val response = apiService.getUsersById(id)
                val data = response.data
                if(data != null){
                    emit(ResponseState.Success(data))
                }else{
                    emit(ResponseState.Empty)
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getMateri(): Flow<ResponseState<List<Materi>>>{
        return flow{
            emit(ResponseState.Loading())
            try{
                val response = apiService.getMateri()
                val data = response.data
                if(data.isNotEmpty()){
                    emit(ResponseState.Success(data))
                }else{
                    emit(ResponseState.Empty)
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getCategory(): Flow<ResponseState<List<Category>>>{
        return flow{
            emit(ResponseState.Loading())
            try{
                val response = apiService.getCategory()
                val data = response.data
                if(data.isNotEmpty()){
                    emit(ResponseState.Success(data))
                }else{
                    emit(ResponseState.Empty)
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getQuizSearch(search: String): Flow<ResponseState<List<Quiz>>>{
        return flow{
            emit(ResponseState.Loading())
            try{
                val response = apiService.getQuizSearch(search)
                val data = response.data

                emit(ResponseState.Success(data))
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}