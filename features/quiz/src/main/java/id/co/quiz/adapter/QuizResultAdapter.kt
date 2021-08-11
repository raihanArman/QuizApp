package id.co.quiz.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.co.quiz.R
import id.co.quiz.databinding.ItemAnswerBinding
import id.co.quiz.utils.Constant
import id.co.room.entity.QuizResultEntity

class QuizResultAdapter: RecyclerView.Adapter<QuizResultAdapter.ViewHolder>() {

    val listQuiz = ArrayList<QuizResultEntity>()

    fun setListQuiz(listQuiz: List<QuizResultEntity>){
        this.listQuiz.clear()
        this.listQuiz.addAll(listQuiz)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemAnswerBinding = DataBindingUtil.inflate(inflater,
            R.layout.item_answer, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = listQuiz.size


    inner class ViewHolder(val binding: ItemAnswerBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(){
            with(binding){
                if (adapterPosition == 0) {
                    tvNo.setBackgroundResource(R.drawable.table_header_cell_bg)
                    tvJawabanAnda.setBackgroundResource(R.drawable.table_header_cell_bg)
                    tvJawabanBenar.setBackgroundResource(R.drawable.table_header_cell_bg)
                    tvHasil.setBackgroundResource(R.drawable.table_header_cell_bg)
                    tvNo.setText("No")
                    tvJawabanAnda.setText("Jawaban Anda")
                    tvJawabanBenar.setText("Jawaban Benar")
                    tvHasil.text = "Hasil"
                } else {
                    val correctAnswer = Constant.listQuestion[adapterPosition-1].answer!!.lowercase()
                    val yourAnswer = listQuiz[adapterPosition].answer.lowercase()
                    tvNo.setBackgroundResource(R.drawable.table_content_cell_bg)
                    tvJawabanAnda.setBackgroundResource(R.drawable.table_content_cell_bg)
                    tvJawabanBenar.setBackgroundResource(R.drawable.table_content_cell_bg)
                    tvHasil.setBackgroundResource(R.drawable.table_content_cell_bg)
                    tvNo.setText("${adapterPosition}")
                    tvJawabanAnda.setText(yourAnswer)
                    tvJawabanBenar.setText(correctAnswer)
                    tvHasil.text = if(yourAnswer == correctAnswer){
                        "Benar"
                    }else{
                        "Salah"
                    }
                }
            }
        }
    }

}