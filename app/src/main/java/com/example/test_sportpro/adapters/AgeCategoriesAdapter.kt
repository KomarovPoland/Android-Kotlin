package com.example.test_sportpro.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test_sportpro.databinding.SportListItemBinding
import com.example.test_sportpro.databinding.ThirdListItemBinding

class AgeCategoriesAdapter (private val categories: List<String>) :  RecyclerView.Adapter<AgeCategoriesAdapter.CategoriesViewHolder>() {

    inner class CategoriesViewHolder(val binding: SportListItemBinding) : RecyclerView.ViewHolder(
        binding.root
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(
            SportListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val category = categories[position]
        holder.itemView.apply {
            holder.binding.txtName.text = category

            setOnClickListener {
                holder.binding.txtName.setTextColor(Color.parseColor("#ED2840"))
                onItemClickListener?.let { it(category) }
            }
        }
    }

    private var onItemClickListener: ((String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}