package com.berlin.android.mykotlin

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.berlin.android.mykotlin.adapter.PictureJokeAdapter
import com.berlin.android.mykotlin.adapter.TextJokeAdapter
import com.berlin.android.mykotlin.net.Api
import com.berlin.android.mykotlin.net.NetUtil
import com.berlin.android.mykotlin.pojo.PictureJokeBean
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
class FragmentPictureJoke : Fragment(){
    val keyId : String = "e6fc5b343f8945a0bd72f2fa084fb1ec"
    val pageSize : Int = 10
    var page : Int = 1
    var adapter : PictureJokeAdapter? = null
    var loading = true
    var hasLoadAllItem = false
    var refreshView : SwipeRefreshLayout? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater!!.inflate(R.layout.fragment_joke_list,container,false);
        val jokeContainer : RecyclerView = view.findViewById(R.id.jokes_list) as RecyclerView
        refreshView = view.findViewById(R.id.refresh_view) as SwipeRefreshLayout
        initRecyclerView(jokeContainer)
        return view
    }

    fun initRecyclerView(view : RecyclerView){
        view.layoutManager = LinearLayoutManager(context)
        adapter = PictureJokeAdapter()
        view.adapter = adapter
        val callBack : Paginate.Callbacks = object: Paginate.Callbacks {
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
        Paginate.with(view,callBack)
                .addLoadingListItem(true)
                .setLoadingListItemCreator(null)
                .setLoadingListItemSpanSizeLookup(object: LoadingListItemSpanLookup {
                    override fun getSpanSize(): Int {
                        return 3
                    }
                }).build()

        refreshView!!.setOnRefreshListener({
            queryJoke(page)
        })
        queryJoke(page)

    }

    fun queryJoke(currentPage : Int){
        loading = true
        val retrofit : Retrofit = NetUtil.getRetrofit()
        val api : Api = retrofit.create(Api::class.java)
        val call : Call<PictureJokeBean> = api.getPicJokes( pageSize.toString(), currentPage.toString())
        call.enqueue(object: Callback<PictureJokeBean?> {
            override fun onResponse(call: Call<PictureJokeBean?>?, response: Response<PictureJokeBean?>?) {
                val picJokes : MutableList<PictureJokeBean.ShowapiResBodyBean.ContentlistBean> = (response!!.body() as PictureJokeBean).showapi_res_body!!.contentlist as MutableList<PictureJokeBean.ShowapiResBodyBean.ContentlistBean>
                adapter!!.setAdapterData(picJokes)
                loading = false
                hasLoadAllItem = response.body()!!.showapi_res_body!!.allPages == page
                refreshView!!.isRefreshing = false

            }

            override fun onFailure(call: Call<PictureJokeBean?>?, t: Throwable?) {
                (activity as MainActivity).toast("网络错误")
                loading = false
            }
        })
    }

    fun loadMoreJoke(currentPage: Int){
        loading = true
        val retrofit : Retrofit = NetUtil.getRetrofit()
        val api : Api = retrofit.create(Api::class.java)
        val call : Call<PictureJokeBean> = api.getPicJokes(pageSize.toString(),currentPage.toString())
        call.enqueue(object: Callback<PictureJokeBean?> {
            override fun onResponse(call: Call<PictureJokeBean?>?, response: Response<PictureJokeBean?>?) {
                loading = false
                var body = response!!.body()
                var picJokes = body!!.showapi_res_body!!.contentlist as MutableList<PictureJokeBean.ShowapiResBodyBean.ContentlistBean>
                adapter!!.addAdapterData(picJokes)
                hasLoadAllItem = response.body()!!.showapi_res_body!!.allPages == page

            }

            override fun onFailure(call: Call<PictureJokeBean?>?, t: Throwable?) {
                (activity as MainActivity).toast("网络错误")
                loading = false
            }
        })
    }
}