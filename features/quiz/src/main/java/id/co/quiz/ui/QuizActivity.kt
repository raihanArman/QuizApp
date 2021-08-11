package id.co.quiz.ui

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.airbnb.deeplinkdispatch.DeepLink
import id.co.core.data.model.Question
import id.co.core.data.network.ResponseState
import id.co.core.util.AppLink
import id.co.quiz.R
import id.co.quiz.databinding.ActivityQuizBinding
import id.co.quiz.module.QuizModule.quizModule
import id.co.quiz.utils.Constant
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

@DeepLink(AppLink.Quiz.QUIZ_LINK)
class QuizActivity : AppCompatActivity() {

    private val viewModel: QuizViewModel by viewModel()
    private lateinit var dataBinding : ActivityQuizBinding
    var id: String ?= ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_quiz)

        loadKoinModules(quizModule)

        if (intent.getBooleanExtra(DeepLink.IS_DEEP_LINK, false)) {
            val parameters = intent.extras?: Bundle()
            id = parameters.getString(AppLink.Quiz.PARAM_QUIZ_ID)?: ""
            setupObserve(id!!)
            setupGetQuestion()
            setupAddHistory()
        }

        dataBinding.btnNext.setOnClickListener {
            if(dataBinding.btnNext.text == "FINISH"){
                val alertDialog = AlertDialog.Builder(this)
                alertDialog.setMessage("Apakah anda yakin sudah selesai ?")
                alertDialog.setPositiveButton("Ya", object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        prosesQuiz()
                    }
                })
                alertDialog.setNegativeButton("Tidak", object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        dialog!!.dismiss()
                    }
                })
                alertDialog.show()
            }else{
                nextQuestion()
            }
        }

        dataBinding.btnPrev.setOnClickListener {
            previousQuestion()
        }

    }

    fun getAnswer(): String{
        return if(dataBinding.rbA.isChecked){
            "a"
        }else if(dataBinding.rbB.isChecked){
            "b"
        }else if(dataBinding.rbC.isChecked){
            "c"
        }else if(dataBinding.rbD.isChecked){
            "d"
        }else{
            ""
        }
    }

    private fun previousQuestion() {
        viewModel.setPrevPage()
        checkAnswer()
    }

    private fun nextQuestion() {
        viewModel.setNextPage(getAnswer())
        checkAnswer()
    }

    private fun prosesQuiz() {
        viewModel.setNextPage(getAnswer())
        viewModel.prosesResult(id!!)
    }

    private fun checkAnswer() {
        val answer = viewModel.checkAnswer()
        Log.d("Check Number", "checkAnswer: $answer")
        setClearRadioButton()
        if(answer != ""){
            if(answer == "a"){
                dataBinding.rbA.isChecked = true
            }else if(answer == "b"){
                dataBinding.rbB.isChecked = true
            }else if(answer == "c"){
                dataBinding.rbC.isChecked = true
            }else if(answer == "d"){
                dataBinding.rbD.isChecked = true
            }else{
                setClearRadioButton()
            }
        }
    }

    private fun setClearRadioButton() {
        dataBinding.rbA.isChecked = false
        dataBinding.rbB.isChecked = false
        dataBinding.rbC.isChecked = false
        dataBinding.rbD.isChecked = false
    }

    private fun setupObserve(id: String) {
        viewModel.getQuestion(id).observe(this, Observer { response ->
            when(response){
                is ResponseState.Success ->{
                    Constant.listQuestion = response.data as ArrayList<Question>
                    setData(response.data[0])
                    setButtonNextPrevState()
                }
                is ResponseState.Loading ->{

                }
                is ResponseState.Error ->{
                    Toast.makeText(this, "Error : ${response.errorMessage}", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setupAddHistory(){
        viewModel.addHistoryMutable.observe(this, Observer { response->
            when(response){
                is ResponseState.Success ->{
                    val data = response.data
                    val intent = Intent(this, ResultActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    intent.putExtra("history", data)
                    startActivity(intent)
                }
                is ResponseState.Loading ->{

                }
                is ResponseState.Error ->{
                    Toast.makeText(this, response.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setupGetQuestion(){
        viewModel.getQuestion.observe(this, Observer { response->
            when(response){
                is ResponseState.Success ->{
                    val data = response.data
                    setData(data)
                    setButtonNextPrevState()
                }
                is ResponseState.Loading ->{

                }
                is ResponseState.Error ->{

                }
            }
        })
    }

    private fun setData(data: Question) {
        with(dataBinding){
            tvQuestion.text = data.question
            rbA.text = data.opsiA
            rbB.text = data.opsiB
            rbC.text = data.opsiC
            rbD.text = data.opsiD
        }
    }

    private fun setButtonNextPrevState() {
        viewModel.position.observe(this, Observer { position->
            dataBinding.tvTitle.text = "Pertanyaan ${position+1}/${Constant.listQuestion.size}"
            if(position > 0){
                if(Constant.listQuestion.size-1 == position){
                    dataBinding.btnPrev.visibility = View.VISIBLE
                    dataBinding.btnNext.text = "FINISH"
                }else{
                    dataBinding.btnPrev.visibility = View.VISIBLE
                    dataBinding.btnNext.text = "NEXT"
                }
            }else{
                dataBinding.btnPrev.visibility = View.INVISIBLE
                dataBinding.btnNext.text = "NEXT"
            }
        })
    }
}