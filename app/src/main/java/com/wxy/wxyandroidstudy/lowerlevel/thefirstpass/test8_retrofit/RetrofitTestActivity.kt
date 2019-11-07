package com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test8_retrofit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.apkfuns.logutils.LogUtils
import com.wxy.adapter.HomeAdapter
import com.wxy.api.HttpApi
import com.wxy.api.HttpApiManager
import com.wxy.bean.BaseBean
import com.wxy.bean.NewsBean
import com.wxy.wxyandroidstudy.R
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_retrofit.*

class RetrofitTestActivity : AppCompatActivity() {
    private var list: List<NewsBean> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)
        val adapter = HomeAdapter(this, list)
        mRecyclerview.adapter = adapter
        mRecyclerview.layoutManager = LinearLayoutManager(this)


        val retrofit = HttpApiManager.getInstance().create(HttpApi::class.java)
        val observable = retrofit.getNewsListObser(1, 12)
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<BaseBean<NewsBean>> {
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: BaseBean<NewsBean>) {
                        LogUtils.e(t)
                        adapter.updateData(t.data)
                    }

                    override fun onError(e: Throwable) {
                        LogUtils.e(e.toString())
                    }
                })

//        val call = create.getNewsListPost(1,12)
//        call.enqueue(object : Callback<BaseBean<NewsBean>> {
//            override fun onFailure(call: Call<BaseBean<NewsBean>>, t: Throwable) {
//                LogUtils.e(t.toString())
//            }
//
//            override fun onResponse(call: Call<BaseBean<NewsBean>>, response: Response<BaseBean<NewsBean>>) {
//                val body = response.body()
//                adapter.updateData(body?.data)
//            }
//        })

    }
}