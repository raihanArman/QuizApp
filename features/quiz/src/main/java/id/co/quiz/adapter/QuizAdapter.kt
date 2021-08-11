package id.co.quiz.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.co.core.data.model.Quiz
import id.co.quiz.R
import id.co.quiz.databinding.ItemQuizBinding

class QuizAdapter(val showQuiz : (Quiz) -> Unit): RecyclerView.Adapter<QuizAdapter.ViewHolder>() {

    private val quizList = ArrayList<Quiz>()

    fun setQuizList(listQuiz: List<Quiz>){
        this.quizList.clear()
        this.quizList.addAll(listQuiz)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemQuizBinding = DataBindingUtil.inflate(layoutInflater,
            R.layout.item_quiz, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quiz = quizList[position]
        holder.bind(quiz)
    }

    override fun getItemCount(): Int = quizList.size


    inner class ViewHolder(val binding: ItemQuizBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(quiz: Quiz){
            binding.tvName.text = quiz.name
//            binding.tvCategory.text = quiz.category
//            binding.tvSumQue.text = quiz.sumQuestions

            itemView.setOnClickListener {
                showQuiz(quiz)
            }
        }
    }

}