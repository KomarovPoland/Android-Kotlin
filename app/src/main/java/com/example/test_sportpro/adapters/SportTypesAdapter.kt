package com.example.test_sportpro.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.test_sportpro.databinding.SportListItemBinding
import com.example.test_sportpro.models.Sport

class SportTypesAdapter() : RecyclerView.Adapter<SportTypesAdapter.SportViewHolder>() {

    inner class SportViewHolder(val binding: SportListItemBinding) : RecyclerView.ViewHolder(
            binding.root
    )

    private val differCallback = object : DiffUtil.ItemCallback<Sport>() {
        override fun areItemsTheSame(oldItem: Sport, newItem: Sport): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Sport, newItem: Sport): Boolean {
            return  oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportViewHolder {
        return  SportViewHolder(
                SportListItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: SportViewHolder, position: Int) {
        val sport = differ.currentList[position]
        holder.itemView.apply {
            holder.binding.txtName.text = sport.name

            setOnClickListener {
                holder.binding.txtName.setTextColor(Color.parseColor("#ED2840"))
                onItemClickListener?.let { it(sport) }
            }
        }
    }

    private  var onItemClickListener: ((Sport) -> Unit)? = null

    fun setOnItemClickListener(listener: (Sport) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}