package com.mey.getnews.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mey.getnews.R
import com.mey.getnews.data.model.Articles
import com.mey.getnews.databinding.CardNewsItemBinding
import com.mey.getnews.util.Constants.PLACE_HOLDER_URL

/**
created by Mehmet E. Yıldız
 **/
class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var context: Context

    class CardNewsItemViewHolder(val binding: CardNewsItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    // verilerdeki item'larda değişiklik varsa, değişikliği yansıtmak için kullanılıyor.
    private val diffUtil = object : DiffUtil.ItemCallback<Articles>() {
        override fun areItemsTheSame(oldItem: Articles, newItem: Articles): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Articles, newItem: Articles): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var articles: List<Articles>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return CardNewsItemViewHolder(
            CardNewsItemBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        bindCardNewsItemViewHolder(holder as CardNewsItemViewHolder, position)
    }

    private fun bindCardNewsItemViewHolder(
        holder: CardNewsItemViewHolder,
        position: Int
    ) {
        holder.binding.apply {
            val item = articles[position]
            item.title?.let {
                tvTitle.text = it
            }
            item.urlToImage?.let {
                Glide.with(context).load(it).placeholder(R.drawable.place_holder).into(imgNew)
            } ?: run {
                Glide.with(context)
                    .load(PLACE_HOLDER_URL)
                    .into(imgNew)


            }
        }
    }

    override fun getItemCount(): Int = articles.size


}