package id.co.quiz.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import id.co.core.data.model.History
import id.co.quiz.R
import id.co.quiz.databinding.ActivityResultBinding
import id.co.quiz.module.QuizModule.quizModule
import id.co.quiz.utils.Constant
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    private val viewModel: QuizViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result)
        loadKoinModules(quizModule)

        val history: History? = intent.getParcelableExtra("history")

        with(binding){
            if(history != null) {
                tvScore.text = history.score.toString()
                tvSoal.text = Constant.listQuestion.size.toString()
                tvBenar.text = history.correct.toString()
                tvSalah.text = history.incorrect.toString()

                val resultAverange = ((history.correct.toDouble()/Constant.listQuestion.size.toDouble()) * 100)
                Log.d("Result", "onCreate: result $resultAverange")
                if(resultAverange > 50.0){
                    ivIcon.setImageResource(R.drawable.noto_astonished_face)
                }else{
                    ivIcon.setImageResource(R.drawable.twemoji_anguished_face)
                }
            }
        }


        binding.tvBack.setOnClickListener {
            gotoMainAtivity()
        }

        binding.tvLihatJawaban.setOnClickListener {
            val list = viewModel.getAllQuizResult()
            val fm: FragmentManager = supportFragmentManager
            val bundel = Bundle()
            bundel.putParcelableArrayList("quiz", list as ArrayList)

            val dialog = QuizResultDialogFragment()
            dialog.arguments = bundel

            dialog.show(fm, "")
        }

    }

    private fun gotoMainAtivity() {
        viewModel.deleteAllAnswer()
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("quiz://main"))
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        gotoMainAtivity()
    }
}