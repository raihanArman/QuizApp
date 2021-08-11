package id.co.materi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.co.core.data.model.Materi
import id.co.core.util.Constant
import id.co.materi.R
import id.co.materi.databinding.ItemMateriBinding

class MateriAdapter(val showBab: (Materi) -> Unit): RecyclerView.Adapter<MateriAdapter.ViewHolder>() {

    val listMateri = ArrayList<Materi>()

    fun setListMateri(listMateri: List<Materi>){
        this.listMateri.clear()
        this.listMateri.addAll(listMateri)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemMateriBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(materi: Materi){
            binding.tvMateri.text=materi.materi
            Glide.with(itemView.context)
                .load(materi.image)
                .into(binding.ivMateri)
            itemView.setOnClickListener {
                showBab(materi)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemMateriBinding = DataBindingUtil.inflate(inflater,
            R.layout.item_materi, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val materi = listMateri[position]
        holder.bind(materi)
    }

    override fun getItemCount(): Int = listMateri.size

}