package id.co.core.data.repositories.remote

import android.util.Log
import id.co.core.data.model.*
import id.co.datastore.UserDataStore
import id.co.core.data.network.ApiService
import id.co.core.data.network.ResponseState
import id.co.datastore.QuizDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.http.Field
import retrofit2.http.Header

class RemoteDataSource(
    private val apiService: ApiService,
    private val userDataStore: UserDataStore
) {
    fun getUsersById(): Flow<ResponseState<Users>>{
        return flow{
            emit(ResponseState.Loading())
            try{
                userDataStore.getTokenUserFlow.collect {
                    val response = apiService.getUsersById(token = "Bearer $it")
                    val data = response.data
                    if(response.meta!!.code == 200){
                        if(data != null){
                            emit(ResponseState.Success(data))
                        }else{
                            emit(ResponseState.Empty)
                        }
                    }else{
                        emit(ResponseState.Error(response.meta!!.message))
                    }
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getPathPopular(): Flow<ResponseState<List<Materi>>>{
        return flow{
            emit(ResponseState.Loading())
            try{
                userDataStore.getTokenUserFlow.collect {
                    val response = apiService.getPopularPath(token = "Bearer $it")
                    val data = response.data
                    if(response.meta!!.code == 200){
                        if(!data.isNullOrEmpty()){
                            emit(ResponseState.Success(data))
                        }else{
                            emit(ResponseState.Empty)
                        }
                    }else{
                        emit(ResponseState.Error(response.meta!!.message))
                    }
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }
    }

    fun getMateri(): Flow<ResponseState<List<Materi>>>{
        return flow{
            emit(ResponseState.Loading())
            try{
                userDataStore.getTokenUserFlow.collect {
                    val response = apiService.getMateri("Bearer $it")
                    val data = response.data
                    if(!data.isNullOrEmpty()){
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

    fun getMateriByCategory(id: String): Flow<ResponseState<List<Materi>>>{
        return flow{
            emit(ResponseState.Loading())
            try{
                userDataStore.getTokenUserFlow.collect {
                    val response = apiService.getPathByCategory("Bearer $it", id)
                    val data = response.data
                    if(!data.isNullOrEmpty()){
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

    fun getCategory(): Flow<ResponseState<List<Category>>>{
        return flow{
            emit(ResponseState.Loading())
            try{
                userDataStore.getTokenUserFlow.collect {
                    val response = apiService.getCategory("Bearer $it")
                    val data = response.data
                    if(!data.isNullOrEmpty()){
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

    fun getQuizSearch(search: String): Flow<ResponseState<List<Quiz>>>{
        return flow{
            emit(ResponseState.Loading())
            try{
                userDataStore.getTokenUserFlow.collect {
                    val response = apiService.getQuizSearch("Bearer $it",search)
                    val data = response.data
                    if(!data.isNullOrEmpty())
                        emit(ResponseState.Success(data))
                    else
                        emit(ResponseState.Empty)
                }

            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getQuizByPath(id: String): Flow<ResponseState<List<Quiz>>>{
        return flow{
            emit(ResponseState.Loading())
            try{
                userDataStore.getTokenUserFlow.collect {
                    val response = apiService.getQuizByPath("Bearer $it",id)
                    val data = response.data
                    if(!data.isNullOrEmpty())
                        emit(ResponseState.Success(data))
                    else
                        emit(ResponseState.Empty)
                }

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
                if(response.meta!!.code == 200){
                    val data = response.data!!.user
                    emit(ResponseState.Success(data))
                    userDataStore.storeUser(data.id.toString())
                    userDataStore.storeStatusLogin(true)
                    userDataStore.storeTokenUser(response.data.accessToken)
                    Log.d("Mantap", "loginUser sukses : value ${response.meta.message}")
                }else{
                    Log.d("Mantap", "loginUser error : value ${response.meta.message}")
                    emit(ResponseState.Error(response.meta.message))
                }
//                if(response.value == 1){
//                    val data = response.data
//                    emit(ResponseState.Success(data))
//                    userDataStore.storeUser(data.id.toString())
//                    userDataStore.storeStatusLogin(true)
//                    Log.d("Mantap", "loginUser sukses : value ${response.value}")
//                }else{
//                    Log.d("Mantap", "loginUser error : value ${response.value}")
//                    emit(ResponseState.Error(response.message))
//                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun logoutUser(): Flow<ResponseState<String>>{
        return flow{
            emit(ResponseState.Loading())
            try{
                userDataStore.getTokenUserFlow.collect {
                    val response = apiService.logoutUser("Bearer $it")
                    if(response.meta!!.code == 200){
                        val data = response.data
                        if(data != null && data == true) {
                            emit(ResponseState.Success("Berhasil logout"))
                            userDataStore.storeUser("")
                            userDataStore.storeStatusLogin(false)
                            userDataStore.storeTokenUser("")
                            Log.d("Mantap", "loginUser sukses : value ${response.meta.message}")
                        }else{
                            emit(ResponseState.Error("Gagal login"))
                        }
                    }else{
                        Log.d("Mantap", "loginUser error : value ${response.meta.message}")
                        emit(ResponseState.Error(response.meta.message))
                    }
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
                if(response.meta!!.code == 200){
                    val data = response.data!!.user
                    emit(ResponseState.Success(data))
                    userDataStore.storeUser(data.id.toString())
                    userDataStore.storeStatusLogin(true)
                    userDataStore.storeTokenUser(response.data.accessToken)
                    Log.d("Mantap", "registerUser sukses : value ${response.meta.message}")
                }else{
                    Log.d("Mantap", "registerUser error : value ${response.meta.message}")
                    emit(ResponseState.Error(response.meta.message))
                }

//                if(response.value == 1){
//                    val data = response.data
//                    emit(ResponseState.Success(data))
//                    userDataStore.storeUser(data.id.toString())
//                    userDataStore.storeStatusLogin(true)
//                }else{
//                    emit(ResponseState.Error(response.message))
//                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }
    }

    fun getBabByMateri(id: String): Flow<ResponseState<List<Chapter>>>{
        return flow {
            emit(ResponseState.Loading())
            try{
                userDataStore.getTokenUserFlow.collect {
                    val response = apiService.getBabByMateri("Bearer $it", id)
                    val data = response.data
                    if(!data.isNullOrEmpty()){
                        emit(ResponseState.Success(data))
                    }else{
                        emit((ResponseState.Empty))
                    }
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getContentById(id: String): Flow<ResponseState<Chapter>>{
        return flow{
            emit(ResponseState.Loading())
            try{
                userDataStore.getTokenUserFlow.collect {
                    val response = apiService.getContentById("Bearer $it", id)
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

    fun getQuestion(id: String): Flow<ResponseState<List<Question>>>{
        return flow {
            emit(ResponseState.Loading())
            try{
                userDataStore.getTokenUserFlow.collect {
                    val response = apiService.getQuestion("Bearer $it", id)
                    val data = response.data
                    if(!data.isNullOrEmpty()){
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

    fun addHistory(
        quizId: String,
        score: Int,
        correct: Int,
        incorrect: Int,
        noAnswered: Int
    ): Flow<ResponseState<History>>{
        return flow {
            emit(ResponseState.Loading())
            try{
                userDataStore.getTokenUserFlow.collect {token->
                    userDataStore.getIdUserFlow.collect { userId->
                        val response = apiService.addHistory(
                            "Bearer $token",
                            userId, quizId, score, correct, incorrect, noAnswered
                        )
                        val data = response.data
                        if(data != null){
                            emit(ResponseState.Success(data))
                        }else{
                            emit(ResponseState.Empty)
                        }
                    }
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }
    }

    fun getHistoryByUser(): Flow<ResponseState<List<History>>>{
        return flow {
            emit(ResponseState.Loading())
            try{
                userDataStore.getTokenUserFlow.collect {token->
                    userDataStore.getIdUserFlow.collect { idUser->
                        val response = apiService.getHistoryByUser("Bearer $token", idUser)
                        val data = response.data
                        if(!data.isNullOrEmpty()){
                            emit(ResponseState.Success(data))
                        }else{
                            emit((ResponseState.Empty))
                        }
                    }
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getQuizCheck(
        quizId: String
    ): Flow<ResponseState<Check>>{
        return flow {
            emit(ResponseState.Loading())
            try{
                userDataStore.getTokenUserFlow.collect {token->
                    userDataStore.getIdUserFlow.collect { userId->
                        val response = apiService.getQuizCheck(
                            "Bearer $token",
                            userId, quizId
                        )
                        val data = response.data
                        if(data != null){
                            emit(ResponseState.Success(data))
                        }else{
                            emit(ResponseState.Empty)
                        }
                    }
                }
            }catch (e: Exception){
                emit(ResponseState.Error(e.toString()))
            }
        }
    }

}