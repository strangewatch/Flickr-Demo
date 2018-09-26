package com.liamakakpo.demo.flickr.ui.feed

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.liamakakpo.demo.flickr.R
import com.liamakakpo.demo.flickr.model.FlickrImage
import kotlinx.android.synthetic.main.list_item.view.*

class ImageFeedListAdapter(private val context: Context, private val onClickAction: ((FlickrImage) -> Unit)) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<FlickrImage>()

    override fun getItemCount(): Int {
        return items.size
    }

    fun isEmpty(): Boolean {
        return items.isEmpty()
    }

    override fun getItemViewType(position: Int): Int {
        return 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        viewHolder.itemView.apply {
            author.text = item.author
            title.text = item.title
            item.media.let {
                Glide.with(context)
                        .load(it.url)
                        .into(image)
                setOnClickListener { onClickAction.invoke(item) }
            }
        }
    }

    fun update(models: Collection<FlickrImage>) {
        items.clear()
        items.addAll(models)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}