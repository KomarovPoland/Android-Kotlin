package com.example.test_sportpro.adapters

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.test_sportpro.databinding.NewsListItemBinding
import com.example.test_sportpro.models.ArticleItem
import java.text.SimpleDateFormat
import java.util.*


class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(val binding: NewsListItemBinding) : RecyclerView.ViewHolder(
            binding.root
    )

    private val differCallback = object : DiffUtil.ItemCallback<ArticleItem>() {
        override fun areItemsTheSame(oldItem: ArticleItem, newItem: ArticleItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ArticleItem, newItem: ArticleItem): Boolean {
            return  oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {

        return  ArticleViewHolder(
                NewsListItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDateStr(strDate: String): String {
        val originalFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val targetFormat = SimpleDateFormat("dd.MM.yyyy")

        val date : Date = originalFormat.parse(strDate)
        return targetFormat.format(date)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this)
                    .load(article.photo)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.binding.newsCover)

            holder.binding.newsTitle.text = article.title
            holder.binding.newsDate.text = formatDateStr(article.dateofadd)
            holder.binding.newsCategory.text = article.sport.name

            setOnClickListener {
                onItemClickListener?.let { it(article) }
            }
        }
    }

    private  var onItemClickListener: ((ArticleItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (ArticleItem) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}