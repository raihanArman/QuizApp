package id.co.profil

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.co.core.data.model.Quiz
import id.co.profil.databinding.ItemHistoryBinding

class HistoryAdapter: RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private val listHistoryQuiz = ArrayList<Quiz>()

    fun setListHistory(listQuiz: List<Quiz>){
        this.listHistoryQuiz.clear()
        this.listHistoryQuiz.addAll(listQuiz)
        notifyDataSetChanged()
    }


    inner class ViewHolder(val binding: ItemHistoryBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(quiz: Quiz){
            binding.tvName.text = quiz.name
            binding.tvScore.text = quiz.score
            binding.tvSumQue.text = quiz.sumQuestions
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemHistoryBinding = DataBindingUtil.inflate(inflater, R.layout.item_history, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quiz = listHistoryQuiz[position]
        holder.bind(quiz)
    }

    override fun getItemCount(): Int = listHistoryQuiz.size

}