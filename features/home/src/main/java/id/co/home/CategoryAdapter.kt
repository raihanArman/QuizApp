package id.co.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.co.core.data.model.Category
import id.co.home.databinding.ItemCategoryBinding

class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private val listCategory = ArrayList<Category>()

    fun setListCategory(listCategory: List<Category>){
        this.listCategory.clear()
        this.listCategory.addAll(listCategory)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding : ItemCategoryBinding = DataBindingUtil.inflate(inflater, R.layout.item_category, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = listCategory[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int = listCategory.size


    inner class ViewHolder(val binding: ItemCategoryBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(category: Category){
            binding.tvMateri.text = category.name
            Glide.with(itemView.context)
                .load(category.image)
                .into(binding.ivMateri)
        }
    }

}