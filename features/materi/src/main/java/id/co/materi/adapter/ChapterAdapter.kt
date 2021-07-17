package id.co.materi.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.co.core.data.model.Chapter
import id.co.materi.R
import id.co.materi.databinding.ItemChapterBinding

class ChapterAdapter internal constructor(private val listener: ChapterAdapterClickListener):
    RecyclerView.Adapter<ChapterAdapter.ViewHolder>() {

    val listBab = ArrayList<Chapter>()

    fun setBab(listChapter: List<Chapter>){
        this.listBab.clear()
        this.listBab.addAll(listChapter)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemChapterBinding = DataBindingUtil.inflate(inflater,
            R.layout.item_chapter, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bab = listBab[position]
        holder.bind(bab)
    }

    override fun getItemCount(): Int = listBab.size

    inner class ViewHolder(val binding: ItemChapterBinding): RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(chapter: Chapter){
            with(binding){
                tvNo.text = "${(adapterPosition+1)}."
                tvName.text = chapter.name
            }
            itemView.setOnClickListener {
                listener.onItemClicked(adapterPosition, listBab[adapterPosition].id)
            }
        }
    }

}

internal interface ChapterAdapterClickListener {
    fun onItemClicked(position: Int, moduleId: String?)
}