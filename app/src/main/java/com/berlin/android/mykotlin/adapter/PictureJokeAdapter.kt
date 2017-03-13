package com.berlin.android.mykotlin.adapter

import android.content.Context
import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.berlin.android.mykotlin.R
import com.berlin.android.mykotlin.pojo.PictureJokeBean
import com.bumptech.glide.Glide
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget

/**
 * Created by zhongbolin on 2017/3/10.
 */
class PictureJokeAdapter : RecyclerView.Adapter<PictureJokeAdapter.PictureJokeHolder>() {

    var picJokes: MutableList<PictureJokeBean.ShowapiResBodyBean.ContentlistBean> = mutableListOf()
    var context : Context? = null
    fun setAdapterData(items: MutableList<PictureJokeBean.ShowapiResBodyBean.ContentlistBean>) {
        picJokes = items
        notifyDataSetChanged()
    }

    fun addAdapterData(items: MutableList<PictureJokeBean.ShowapiResBodyBean.ContentlistBean>) {
        picJokes.addAll(items)
        notifyItemRangeInserted(picJokes.size - items.size, picJokes.size)
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PictureJokeHolder {
        context = parent!!.context
        val itemView: View = LayoutInflater.from(context).inflate(R.layout.item_picture_joke, parent, false)
        return PictureJokeHolder(itemView)
    }

    override fun onBindViewHolder(holder: PictureJokeHolder?, position: Int) {
        var picJoke = picJokes[position]
        holder!!.tvJokeTitle.text = picJoke.title
        Glide.with(context).load(picJoke.img).into(holder.imgJokeContent)
        Log.e("bitmap_url",picJoke.img)
    }

    override fun getItemCount(): Int {
        return picJokes.size
    }


    class PictureJokeHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var tvJokeTitle: TextView = itemView!!.findViewById(R.id.tv_joke_title) as TextView
        val imgJokeContent: ImageView = itemView!!.findViewById(R.id.img_joke_content) as ImageView
    }
}