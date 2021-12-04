package com.example.latestnews.presentation.latestNews

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.latestnews.databinding.ItemLatestNewsBinding
import com.example.latestnews.domain.news.ItemNews
import com.example.latestnews.util.loadFromUrl
import javax.inject.Inject
import kotlin.properties.Delegates

@SuppressLint("NotifyDataSetChanged")
class NewsAdapter
@Inject constructor() : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    var collection: List<ItemNews> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    var clickListener: (ItemNews) -> Unit = { _ -> }

    class ViewHolder(
        private val binding: ItemLatestNewsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(itemNews: ItemNews, clickListener: (ItemNews) -> Unit) {
            with(binding) {
                itemTitle.text = itemNews.title
                itemDescription.text = itemNews.description
                itemImage.loadFromUrl(itemNews.image)
            }

            itemView.setOnClickListener {
                clickListener(itemNews)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLatestNewsBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(collection[position], clickListener)

    override fun getItemCount(): Int = collection.size
}