package id.co.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserDataStore (private val context: Context){
    private val dataStore = context.createDataStore(name = "user")

    companion object{
        val ID_USER = stringPreferencesKey("ID_USER")
        val STATUS_LOGIN = booleanPreferencesKey("status_login")
        val TOKEN_USER = stringPreferencesKey("TOKEN_USER")
    }

    suspend fun storeTokenUser(token: String){
        dataStore.edit {
            it[TOKEN_USER] = token
        }
    }
    suspend fun storeUser(idUser: String){
        dataStore.edit {
            it[ID_USER] = idUser
        }
    }
    val getIdUserFlow: Flow<String> = dataStore.data.map {
        it[ID_USER] ?: ""
    }

    val getTokenUserFlow: Flow<String> = dataStore.data.map {
        it[TOKEN_USER] ?: ""
    }
    suspend fun storeStatusLogin(status: Boolean){
        dataStore.edit {
            it[STATUS_LOGIN] = status
        }
    }

    val getStatusLogin: Flow<Boolean> = dataStore.data.map {
        it[STATUS_LOGIN] ?: false
    }
}