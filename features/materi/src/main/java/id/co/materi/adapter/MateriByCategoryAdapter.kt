package id.co.materi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.co.core.data.model.Materi
import id.co.core.util.Constant
import id.co.materi.R
import id.co.materi.databinding.ItemMateriByCategoryBinding

class MateriByCategoryAdapter(val showQuiz : (Materi) -> Unit): RecyclerView.Adapter<MateriByCategoryAdapter.ViewHolder>() {

    val listMateri = ArrayList<Materi>()

    fun setListMateri(listMateri: List<Materi>){
        this.listMateri.clear()
        this.listMateri.addAll(listMateri)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemMateriByCategoryBinding = DataBindingUtil.inflate(inflater, R.layout.item_materi_by_category, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val materi = listMateri[position]
        holder.bind(materi)
    }

    override fun getItemCount(): Int = listMateri.size

    inner class ViewHolder(val binding: ItemMateriByCategoryBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(materi: Materi){
            binding.tvMateri.text = materi.materi
            binding.tvStudent.text = materi.student+" student"
            Glide.with(itemView.context)
                .load(materi.image)
                .into(binding.ivMateri)

            itemView.setOnClickListener {
                showQuiz(materi)
            }
        }
    }


}