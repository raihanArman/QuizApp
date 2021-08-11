package id.co.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class QuizDataStore(private val context: Context) {
    private val dataStore = context.createDataStore(name= "quiz")
    
    companion object{
        val QUESTION_NUMBER = intPreferencesKey("QUESTION_NUMBER")
        val ANSWER = stringPreferencesKey("ANSWER")
    }

    suspend fun saveQuestion(question: Int, answer: String){
        dataStore.edit {
            it[QUESTION_NUMBER] = question
            it[ANSWER] = answer
        }
    }

    val getQuestionNumber: Flow<Int> = dataStore.data.map {
        it[QUESTION_NUMBER] ?: 0
    }

    val getAnswer: Flow<String> = dataStore.data.map {
        it[ANSWER] ?: ""
    }
    
}