package id.co.quiz.ui

import android.util.Log
import androidx.lifecycle.*
import id.co.core.data.model.*
import id.co.core.data.network.ResponseState
import id.co.core.domain.usecase.UseCase
import id.co.quiz.utils.Constant
import id.co.room.entity.QuizResultEntity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class QuizViewModel(val useCase: UseCase): ViewModel() {

    var position = MutableLiveData<Int>()
    var getQuestion = MutableLiveData<ResponseState<Question>>()
    var addHistoryMutable = MutableLiveData<ResponseState<History>>()

    init {
        position.value = 0
    }

    fun getAllQuizResult(): List<QuizResultEntity>{
        return useCase.getQuizResult()
    }

    fun getQuizByPath(id: String): LiveData<ResponseState<List<Quiz>>> {
        return useCase.getQuizByPath(id).asLiveData()
    }

    fun checkAnswer(): String{
        val positionQue = position.value
        val numberIsExists = useCase.isNumberExists(positionQue!!+1)
        Log.d("Check Number", "checkAnswer: ${positionQue+1}")
        if(numberIsExists){
            val getQuiz = useCase.getQuizById(positionQue+1)
            Log.d("Check Number", "checkAnswer: nomor ${getQuiz.number} ada dan jawaban ${getQuiz.answer}")
            return getQuiz.answer
        }else{
            Log.d("Check Number", "checkAnswer: nomor tidak ada")
            return ""
        }
    }

    fun getQuestion(id: String): LiveData<ResponseState<List<Question>>> {
        return useCase.getQuestion(id).asLiveData()
    }


    fun setNextPage(answer: String) {
        Log.d("Mantap", "setNextPage: di tekan")
        val dataQuestion = Constant.listQuestion
        if(dataQuestion != null && dataQuestion.isNotEmpty()){
            val positionQue = position.value!!
            saveQuizResult(positionQue+1, answer)
            if(dataQuestion?.size-1 > positionQue) {
                val question = dataQuestion[positionQue + 1]
                position.value = positionQue + 1
                getQuestion.postValue(ResponseState.Success(question))
            }
        }
    }

    private fun saveQuizResult(number: Int, answer: String) {
        // Logic
        val quiz = QuizResultEntity(number, answer)
        Log.d("Check Number", "checkAnswer: save nomor ${number} dan jawaban ${answer}")

        val numberIsExists = useCase.isNumberExists(number)
        if(numberIsExists){
            val resultUpdate = useCase.updateQuizResult(quiz)
            if (resultUpdate > 0) {
                Log.d("save_quiz", "saveQuizResult: Berhasil di update")
            } else {
                Log.d("save_quiz", "saveQuizResult: Gagal di update")
            }
        }else {
            val result = useCase.saveQuizResult(quiz)
            if (result > 0) {
                Log.d("save_quiz", "saveQuizResult: Berhasil di save")
            } else {
                Log.d("save_quiz", "saveQuizResult: Gagal di save")
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


    fun prosesResult(quizId: String){
        val quizUser = useCase.getQuizResult()
        val dataQuestion = Constant.listQuestion
        var correct = 0
        var incorrect = 0
        var noAnswered = 0
        Log.d("Mantap", "prosesResult: size ${quizUser.size}")
        for (number in 1.. quizUser.size){
            if (quizUser[number-1].answer != ""){
                if(dataQuestion[number-1].answer!!.lowercase() == quizUser[number-1].answer!!.lowercase()){
                    correct ++
                }else{
                    incorrect++
                }
            }else{
                noAnswered++
            }

        }
        val score = correct*5

        Log.d("Mantap", "prosesResult: quiz Id ${quizId}")
        viewModelScope.launch {
            useCase.addHistory(
                quizId,
                score,
                correct,
                incorrect,
                noAnswered
            ).collect {
                addHistoryMutable.value = it
            }
        }

    }

    fun deleteAllAnswer(){
        return useCase.deleteAllAnswer()
    }

    fun getQuizCheck(quizId: String): LiveData<ResponseState<Check>> {
        return useCase.getQuizCheck(quizId).asLiveData()
    }

}