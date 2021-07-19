package id.co.quiz

import android.util.Log
import android.view.View
import androidx.lifecycle.*
import id.co.core.data.model.Chapter
import id.co.core.data.model.Question
import id.co.core.data.network.ResponseState
import id.co.core.domain.usecase.UseCase
import id.co.quiz.utils.Constant

class QuizViewModel(val useCase: UseCase): ViewModel() {

    var position = MutableLiveData<Int>()
    var getQuestion = MutableLiveData<ResponseState<Question>>()

    init {
        position.value = 0
    }

    fun getQuestion(id: String): LiveData<ResponseState<List<Question>>> {
        return useCase.getQuestion(id).asLiveData()
    }


    fun setNextPage() {
        Log.d("Mantap", "setNextPage: di tekan")
        val dataQuestion = Constant.listQuestion
        if(dataQuestion != null && dataQuestion.isNotEmpty()){
            val positionQue = position.value
            if(dataQuestion?.size-1 > positionQue!!) {
                val question = dataQuestion[positionQue + 1]
                position.value = positionQue + 1
                getQuestion.postValue(ResponseState.Success(question))
            }
        }
    }


    fun setPrevPage() {
        Log.d("Mantap", "setNextPage: di tekan")
        val dataQuestion = Constant.listQuestion
        if(dataQuestion != null && dataQuestion.isNotEmpty()){
            val positionQue = position.value
            if(positionQue!! > 0) {
                val question = dataQuestion[positionQue - 1]
                position.value = positionQue - 1
                getQuestion.postValue(ResponseState.Success(question))
            }
        }else{
            Log.d("Mantap", "setNextPage: ")
        }
    }

}