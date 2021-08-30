package com.example.yournews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter(private val listener: NewsItemClicked): RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {
    private val itemList = ArrayList<NewsData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.bind(currentItem, listener)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun updateNews(updateNewsList: ArrayList<NewsData>) {
        itemList.clear()
        itemList.addAll(updateNewsList)

        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.tv_title)
        private val author: TextView = itemView.findViewById(R.id.tv_author)
        private val image: ImageView = itemView.findViewById(R.id.iv_image)

        fun bind(itemList: NewsData, clickListener: NewsItemClicked) {
            title.text = itemList.title
            author.text = itemList.author
            Glide.with(itemView.context).load(itemList.imageUrl).into(image)

            itemView.setOnClickListener {
                clickListener.onItemClicked(itemList)
            }
        }
    }

    interface NewsItemClicked {
        fun onItemClicked(item: NewsData)
    }
}