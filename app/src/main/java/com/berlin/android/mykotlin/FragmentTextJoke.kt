package com.berlin.android.mykotlin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.berlin.android.mykotlin.adapter.TextJokeAdapter
import com.berlin.android.mykotlin.net.Api
import com.berlin.android.mykotlin.net.NetUtil
import com.berlin.android.mykotlin.pojo.TextJokeBean
import com.paginate.Paginate
import com.paginate.recycler.LoadingListItemSpanLookup

import kotlinx.android.synthetic.main.fragment_joke_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

/**
 * Created by zhongbolin on 2017/3/10.
 */
class FragmentTextJoke : Fragment() {
    val keyId : String = "e6fc5b343f8945a0bd72f2fa084fb1ec"
    val pageSize : Int = 10
    var page : Int = 1
    var adapter : TextJokeAdapter? = null
    var callBack : Paginate.Callbacks? = null
    var refreshView :SwipeRefreshLayout? =null
    var loading = true
    var hasLoadAllItem = false
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater!!.inflate(R.layout.fragment_joke_list,container,false);
        val jokeContainer : RecyclerView = view.findViewById(R.id.jokes_list) as RecyclerView
        refreshView = view.findViewById(R.id.refresh_view) as SwipeRefreshLayout
        initRecyclerView(jokeContainer)
        return view
    }

    fun initRecyclerView(view: RecyclerView){
        view.layoutManager = LinearLayoutManager(context)
        adapter = TextJokeAdapter()
        view.adapter = adapter
        callBack = object: Paginate.Callbacks {
            override fun onLoadMore() {
                page++
                loadMoreJoke(page)
            }

            override fun isLoading(): Boolean {
                return loading
            }

            override fun hasLoadedAllItems(): Boolean {
                return hasLoadAllItem
            }
        }
        Paginate.with(view,callBack).setLoadingTriggerThreshold(2)
                .addLoadingListItem(true)
                .setLoadingListItemCreator(null)
                .setLoadingListItemSpanSizeLookup { 3 }.build()


        refreshView!!.setOnRefreshListener {
            queryJoke(page)
        }

        queryJoke(page)
    }

    fun queryJoke(currentPage : Int){
        val retrofit : Retrofit = NetUtil.getRetrofit()
        val api : Api = retrofit.create(Api::class.java)
        val call : Call<TextJokeBean> = api.getTextJokes( pageSize.toString(), currentPage.toString())
        call.enqueue(object: Callback<TextJokeBean?> {
            override fun onResponse(call: Call<TextJokeBean?>?, response: Response<TextJokeBean?>?) {
                loading = false
                refreshView!!.isRefreshing = false
                Log.e("body",response!!.body().toString() + response.isSuccessful)
                if (response.isSuccessful){
                    val body : TextJokeBean? = response.body()
                    adapter!!.setAdapterData(body!!.showapi_res_body!!.contentlist as MutableList<TextJokeBean.ShowapiResBodyBean.ContentlistBean>)
                    hasLoadAllItem = body.showapi_res_body?.allPages == page
                }else{
                    (activity as MainActivity).toast("没有任务数据")
                }

            }

            override fun onFailure(call: Call<TextJokeBean?>?, t: Throwable?) {
                (activity as MainActivity).toast("网络错误")
            }
        })

    }

    fun loadMoreJoke(currentPage: Int){
        val retrofit : Retrofit = NetUtil.getRetrofit()
        val api : Api = retrofit.create(Api::class.java)
        val call : Call<TextJokeBean> = api.getTextJokes( pageSize.toString(), currentPage.toString())
        call.enqueue(object: Callback<TextJokeBean?> {
            override fun onResponse(call: Call<TextJokeBean?>?, response: Response<TextJokeBean?>?) {
                var body : TextJokeBean? = response!!.body()
                adapter!!.addAdapterData(body?.showapi_res_body?.contentlist as MutableList<TextJokeBean.ShowapiResBodyBean.ContentlistBean>)
                Log.e("body",body.toString())
                hasLoadAllItem = body.showapi_res_body!!.allPages == page

            }

            override fun onFailure(call: Call<TextJokeBean?>?, t: Throwable?) {
                (activity as MainActivity).toast("网络错误")
            }
        })
    }
}