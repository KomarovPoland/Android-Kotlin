package com.example.test_sportpro.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.test_sportpro.databinding.SportsmenListItemBinding
import com.example.test_sportpro.models.GridsItem
import com.example.test_sportpro.models.Matche

class GridsSportsmenAdapter() : RecyclerView.Adapter<GridsSportsmenAdapter.StagesViewHolder>() {

    inner class StagesViewHolder(val binding: SportsmenListItemBinding) : RecyclerView.ViewHolder(
        binding.root
    )

    private val differCallback = object : DiffUtil.ItemCallback<Matche>() {
        override fun areItemsTheSame(oldItem: Matche, newItem: Matche): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Matche, newItem: Matche): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StagesViewHolder {
        return StagesViewHolder(
            SportsmenListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StagesViewHolder, position: Int) {
        val matche = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this)
                .load(matche.player1.photo)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.binding.image)

            Glide.with(this)
                .load(matche.player2.photo)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.binding.image2)

            holder.binding.name.text =
                matche.player1.surname.plus(" ").plus(matche.player1.name).plus(" ")
                    .plus(matche.player1.middlename)
            holder.binding.name2.text =
                matche.player2.surname.plus(" ").plus(matche.player2.name).plus(" ")
                    .plus(matche.player2.middlename)

            holder.binding.point.text = matche.player1Score.toString()
            holder.binding.point2.text = matche.player2Score.toString()

            if (matche.player1Score != 0 && matche.player2Score != 0) {
                if (matche.player1Score > matche.player2Score) {
                    holder.binding.winner.visibility = View.VISIBLE
                } else {
                    holder.binding.winner2.visibility = View.VISIBLE
                }
            }

            setOnClickListener {
                onItemClickListener?.let { it(matche) }
            }
        }
    }

    private var onItemClickListener: ((Matche) -> Unit)? = null

    fun setOnItemClickListener(listener: (Matche) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}