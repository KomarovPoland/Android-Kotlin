package com.example.test_sportpro.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.test_sportpro.databinding.ThirdListItemBinding
import com.example.test_sportpro.models.PlayerItem

class ThirdAdapter() :  RecyclerView.Adapter<ThirdAdapter.SportsmanViewHolder>() {

    inner class SportsmanViewHolder(val binding: ThirdListItemBinding) : RecyclerView.ViewHolder(
        binding.root
    )

    private val differCallback = object : DiffUtil.ItemCallback<PlayerItem>() {
        override fun areItemsTheSame(oldItem: PlayerItem, newItem: PlayerItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PlayerItem, newItem: PlayerItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportsmanViewHolder {
        return SportsmanViewHolder(
            ThirdListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SportsmanViewHolder, position: Int) {
        val sportsman = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this)
                    .load(sportsman.photo)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.binding.image)

            holder.binding.name.text = sportsman.name.plus(" ").plus(sportsman.surname)

            if (sportsman.age < 5 || sportsman.age in 21..24) {
                holder.binding.age.text = sportsman.age.toString().plus(" год(а)")
            } else {
                holder.binding.age.text = sportsman.age.toString().plus(" лет")
            }

            holder.binding.weight.text = sportsman.weight.toString().plus(" кг")
            holder.binding.work.text = sportsman.organization

            setOnClickListener {
                onItemClickListener?.let { it(sportsman) }
            }
        }
    }

    private var onItemClickListener: ((PlayerItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (PlayerItem) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}