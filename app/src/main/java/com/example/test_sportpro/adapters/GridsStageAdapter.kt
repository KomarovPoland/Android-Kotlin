package com.example.test_sportpro.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.test_sportpro.databinding.GridListItemBinding
import com.example.test_sportpro.databinding.NewsListItemBinding
import com.example.test_sportpro.databinding.SportListItemBinding
import com.example.test_sportpro.databinding.ThirdListItemBinding
import com.example.test_sportpro.models.ArticleItem
import com.example.test_sportpro.models.Grids
import com.example.test_sportpro.models.GridsItem
import kotlinx.android.synthetic.main.grid_list_item.view.*

class GridsStageAdapter () :  RecyclerView.Adapter<GridsStageAdapter.StagesViewHolder>() {

    inner class StagesViewHolder(val binding: GridListItemBinding) : RecyclerView.ViewHolder(
        binding.root
    )

    private val differCallback = object : DiffUtil.ItemCallback<GridsItem>() {
        override fun areItemsTheSame(oldItem: GridsItem, newItem: GridsItem): Boolean {
            return oldItem.stage == newItem.stage
        }

        override fun areContentsTheSame(oldItem: GridsItem, newItem: GridsItem): Boolean {
            return  oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StagesViewHolder {
        return StagesViewHolder(
            GridListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StagesViewHolder, position: Int) {
        val grids = differ.currentList[position]
        holder.itemView.apply {
            var stage = grids.stage

//            if (stage == "1/1") {
//                stage = "Финал"
//                holder.binding.finalText.text = ""
                holder.binding.stage.text = stage
//            } else {
//                holder.binding.stage.text = stage
//            }

            setOnClickListener {
                onItemClickListener?.let {
                    it(grids) }
            }
        }
    }

    private var onItemClickListener: ((GridsItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (GridsItem) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}