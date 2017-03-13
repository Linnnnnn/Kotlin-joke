package com.berlin.android.mykotlin.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import cn.droidlover.xrichtext.XRichText
import com.berlin.android.mykotlin.R
import com.berlin.android.mykotlin.pojo.TextJokeBean
import kotlinx.android.synthetic.main.item_text_joke.*

/**
 * Created by zhongbolin on 2017/3/10.
 */
class TextJokeAdapter : RecyclerView.Adapter<TextJokeAdapter.TextJokeHolder>() {

    var jokes : MutableList<TextJokeBean.ShowapiResBodyBean.ContentlistBean>? = mutableListOf()

    fun setAdapterData(items : MutableList<TextJokeBean.ShowapiResBodyBean.ContentlistBean>?){
        jokes = items
        notifyDataSetChanged()
    }

    fun addAdapterData(loadMoreItems : MutableList<TextJokeBean.ShowapiResBodyBean.ContentlistBean>?){
        if (loadMoreItems != null) {
            jokes!!.addAll(loadMoreItems)
        }
        notifyItemRangeInserted(jokes!!.size - loadMoreItems!!.size,jokes!!.size)
    }

    override fun onBindViewHolder(holder: TextJokeHolder?, position: Int) {
       var joke : TextJokeBean.ShowapiResBodyBean.ContentlistBean = jokes!![position]
        holder!!.mTvJokeTitle.text = joke.title
        holder.mTvJokeContent.text = joke.text
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TextJokeHolder {
        val itemView : View = LayoutInflater.from(parent!!.context).inflate(R.layout.item_text_joke,parent,false);
        return TextJokeHolder(itemView)
    }

    override fun getItemCount(): Int {
        return jokes!!.size
    }


    class TextJokeHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var mTvJokeTitle : TextView = itemView!!.findViewById(R.id.tv_joke_title) as TextView
        var mTvJokeContent : XRichText = itemView!!.findViewById(R.id.tv_joke_content) as XRichText
    }
}