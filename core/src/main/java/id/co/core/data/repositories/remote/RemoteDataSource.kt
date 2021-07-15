package id.co.core.data.repositories.remote

import android.util.Log
import androidx.lifecycle.asLiveData
import id.co.datastore.UserDataStore
import id.co.core.data.model.Category
import id.co.core.data.model.Materi
import id.co.core.data.model.Quiz
import id.co.core.data.model.Users
import id.co.core.data.network.ApiService
import id.co.core.data.network.ResponseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RemoteDataSource(
    private val apiService: ApiService,
    private val userDataStore: UserDataStore
) {
    fun getUsersById(): Flow<ResponseState<Users>>{
        return flow{
            emit(ResponseState.Loading())
            try{
                userDataStore.getIdUserFlow.collect {
                    val response = apiService.getUsersById(it)
                    val data = response.data
                    if(data != null){
                        emit(ResponseState.Success(data))
                    }else{
                        emit(ResponseState.Empty)
                    }
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

    fun loginUser(email: String, password: String): Flow<ResponseState<Users>>{
        return flow{
            emit(ResponseState.Loading())
            try{
                val response = apiService.loginUser(email, password)
                if(response.value == 1){
                    val data = response.data
                    emit(ResponseState.Success(data))
                    userDataStore.storeUser(data.id.toString())
                    userDataStore.storeStatusLogin(true)
                    Log.d("Mantap", "loginUser sukses : value ${response.value}")
                }else{
                    Log.d("Mantap", "loginUser error : value ${response.value}")
                    emit(ResponseState.Error(response.message))
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun registerUser(
        email: String,
        name: String,
        password: String
    ): Flow<ResponseState<Users>>{
        return flow{
            emit(ResponseState.Loading())
            try{
                val response = apiService.registerUser(email, name, password)
                if(response.value == 1){
                    val data = response.data
                    emit(ResponseState.Success(data))
                    userDataStore.storeUser(data.id.toString())
                    userDataStore.storeStatusLogin(true)
                }else{
                    emit(ResponseState.Error(response.message))
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }
    }

}