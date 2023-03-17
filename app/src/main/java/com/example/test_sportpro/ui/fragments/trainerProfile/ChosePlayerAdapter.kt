package com.example.test_sportpro.ui.fragments.trainerProfile

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.test_sportpro.databinding.RowListPlayersBinding
import com.example.test_sportpro.models.PlayerItem

class ChosePlayerAdapter() : RecyclerView.Adapter<ChosePlayerAdapter.SportsmanViewHolder>() {
    private val list: MutableList<Int> = ArrayList()
    inner class SportsmanViewHolder(val binding: RowListPlayersBinding) : RecyclerView.ViewHolder(binding.root)


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
            RowListPlayersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: SportsmanViewHolder, position: Int) {

        val sportsman = differ.currentList[position]
        holder.itemView.apply {

            holder.binding.tvName.text = sportsman.name.plus(" ").plus(sportsman.surname)

            setOnClickListener {
                if (!holder.binding.checkBox.isChecked) {
                    holder.binding.checkBox.setChecked(true);
                    list.add(sportsman.id)
                }else{
                    holder.binding.checkBox.setChecked(false);
                    list.remove(sportsman.id)
                }
                Log.e("LIST_PLAYERS",list.toString())
            }

        }
    }
    fun getList(): MutableList<Int> {
        return list
    }


    private var onItemClickListener: ((PlayerItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (PlayerItem) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}