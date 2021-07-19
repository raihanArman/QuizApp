package id.co.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.airbnb.deeplinkdispatch.DeepLink
import id.co.core.data.model.Question
import id.co.core.data.network.ResponseState
import id.co.core.util.AppLink
import id.co.quiz.databinding.ActivityQuizBinding
import id.co.quiz.module.QuizModule.quizModule
import id.co.quiz.utils.Constant
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

@DeepLink(AppLink.Quiz.QUIZ_LINK)
class QuizActivity : AppCompatActivity() {

    private val viewModel: QuizViewModel by viewModel()
    private lateinit var dataBinding : ActivityQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_quiz)

        loadKoinModules(quizModule)

        if (intent.getBooleanExtra(DeepLink.IS_DEEP_LINK, false)) {
            val parameters = intent.extras?: Bundle()
            val id = parameters.getString(AppLink.Quiz.PARAM_QUIZ_ID)?: ""
            setupObserve(id)
        }

        getQuestion()

        dataBinding.btnNext.setOnClickListener {
            viewModel.setNextPage()
        }

        dataBinding.btnPrev.setOnClickListener {
            viewModel.setPrevPage()
        }

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

    private fun getQuestion(){
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
                    dataBinding.btnNext.visibility = View.INVISIBLE
                }else{
                    dataBinding.btnPrev.visibility = View.VISIBLE
                    dataBinding.btnNext.visibility = View.VISIBLE
                }
            }else{
                dataBinding.btnPrev.visibility = View.INVISIBLE
                dataBinding.btnNext.visibility = View.VISIBLE
            }
        })
    }
}