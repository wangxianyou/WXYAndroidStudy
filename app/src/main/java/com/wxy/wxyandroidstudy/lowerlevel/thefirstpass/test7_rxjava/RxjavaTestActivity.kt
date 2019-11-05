package com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test7_rxjava

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apkfuns.logutils.LogUtils
import com.wxy.wxyandroidstudy.R
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit

class RxjavaTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjava)
//        onTestChange()
//        onTestZip()
        onTestFun()
    }

    @SuppressLint("CheckResult")
    fun onTestFun() {
        var arr = arrayOf("小明",12,"xiaowww")
        val fromIterable = Observable.fromIterable(arr.toList())
        val interval = Observable.interval(1, TimeUnit.SECONDS)
        Observable.zip(fromIterable,interval, BiFunction<Any,Long,Any> { t1, t2 -> t1 })
                .delay(3, TimeUnit.SECONDS)
                .repeat()
                .subscribe(object :Observer<Any>{
                    override fun onComplete() {
                        LogUtils.e("subscribe  =========onComplete======== ")
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: Any) {
                        LogUtils.e("subscribe  =========onNext======== $t")
                    }

                    override fun onError(e: Throwable) {
                    }
                })
//        Observable.fromIterable(arr.toList())
//                .delay(3, TimeUnit.SECONDS)
//                .repeat()
////                .repeatWhen { Observable.timer(2,TimeUnit.SECONDS) }
//                .subscribe(object :Observer<Any>{
//                    override fun onComplete() {
//                        LogUtils.e("subscribe  =========onComplete======== ")
//                    }
//
//                    override fun onSubscribe(d: Disposable) {
//                    }
//
//                    override fun onNext(t: Any) {
//                        LogUtils.e("subscribe  =========onNext======== $t")
//                    }
//
//                    override fun onError(e: Throwable) {
//                    }
//                })


//        var i = 0
//        Observable.create(ObservableOnSubscribe<Int> {
//            it.onNext(1)
//            it.onNext(2)
//            it.onNext(3)
////            it.onError(Error("404"))
//            it.onComplete()
//        })
////                .repeatUntil { true }
//
//                .repeat(2)
//                .repeatWhen {
////                    Observable.empty<Int>()
////                    Observable.error<Int>(Exception("404"))
//                    Observable.just(12)
//                }
////                .retryUntil {
////                    i == 7
////                }
//                .subscribe(object : Observer<Int> {
//                    override fun onComplete() {
//                        LogUtils.e("subscribe  =========onComplete========")
//                    }
//
//                    override fun onSubscribe(d: Disposable) {
//                        LogUtils.e("subscribe  =========onSubscribe========")
//                    }
//
//                    override fun onNext(t: Int) {
//                        LogUtils.e("subscribe  =========onNext======== $t")
//                        i += t
//                    }
//
//                    override fun onError(e: Throwable) {
//                        LogUtils.e("subscribe  =========onError========")
//                    }
//                })

//        Observable.just(1,2,3,4).doOnLifecycle({ t -> t.dispose()
//            LogUtils.e("doOnLifecycle  ==========dispose=======")}, { LogUtils.e("doOnLifecycle action =================") })
//                .subscribe (object :Observer<Int>{
//                    var d:Disposable? = null
//                    override fun onComplete() {
//                        LogUtils.e("subscribe  =========onComplete========")
//                    }
//
//                    override fun onSubscribe(d: Disposable) {
//                        this.d = d
//                        LogUtils.e("subscribe  =========onSubscribe========")
//                    }
//
//                    override fun onNext(t: Int) {
//                        LogUtils.e("subscribe  =========onNext======== $t")
//                        d?.dispose()
//                    }
//
//                    override fun onError(e: Throwable) {
//                        LogUtils.e("subscribe  =========onError========")
//                    }
//                })
    }


    @SuppressLint("CheckResult")
    fun onTestZip() {

//        Observable.combineLatest(
//                Observable.intervalRange(1,4,1,2,TimeUnit.SECONDS).map { "A${it}" },
//                Observable.intervalRange(1,5,2,1,TimeUnit.SECONDS).map { "B${it}" }
//                , BiFunction<String,String,String> { t1, t2 -> t1+t2 })
//                .subscribe (object :Observer<String>{
//                    override fun onComplete() {
//                    }
//
//                    override fun onSubscribe(d: Disposable) {
//                    }
//
//                    override fun onNext(t: String) {
//                        LogUtils.e(t)
//                    }
//
//                    override fun onError(e: Throwable) {
//                    }
//                })


//        Observable.zip(Observable.just(1,2),Observable.just(3,4,5), BiFunction<Int, Int, Int> { t1, t2 ->
//            t1.times(t2)
//        }).subscribe { LogUtils.e(it) }

//        Observable.concat(Observable.just(1,3),Observable.just(2,5)).subscribe { LogUtils.e(it) }
    }

    @SuppressLint("CheckResult")
    fun onTestChange() {

        Observable.just(2, 7, 3, 4, 5)
                .scan { t1: Int, t2: Int ->
                    t1.times(t2)
                }
                .subscribe { LogUtils.e(it) }

//        Observable.just(8,5,6,7,9,1,3,6,8)
//                .groupBy { t -> t%5 }
//                .subscribe(object :Observer<GroupedObservable<Int,Int>>{
//                    override fun onComplete() {
//                    }
//
//                    override fun onSubscribe(d: Disposable) {
//                    }
//
//                    override fun onNext(t: GroupedObservable<Int, Int>) {
//                        t.subscribe(object :Observer<Int>{
//                            override fun onComplete() {
//                            }
//
//                            override fun onSubscribe(d: Disposable) {
//
//                            }
//
//                            override fun onNext(v: Int) {
//                                LogUtils.e("分组为 ：${t.key},值为：${v}")
//                            }
//
//                            override fun onError(e: Throwable) {
//                            }
//                        })
//                    }
//
//                    override fun onError(e: Throwable) {
//                    }
//                })


//        Observable.just(1,1,3,4,5).buffer(2,1).flatMap {t ->  Observable.fromIterable(t) }.subscribe{
//            LogUtils.e(it)
//        }

//        Observable.just(1,2,3).map { t -> "im$t" }.subscribe { t -> LogUtils.e(t) }

//        Observable.fromArray(1,2,3,4,5).flatMap { t ->
//            Observable.just("im$t").delay(0,TimeUnit.SECONDS)}.subscribe { t -> LogUtils.e(t) }

    }


    @SuppressLint("CheckResult")
    fun onTestCreate() {
        Observable.range(2, 5).subscribe(object : Observer<Int> {
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: Int) {
                LogUtils.e("onNext执行$t")
            }

            override fun onError(e: Throwable) {
            }
        })

//        Observable.create(ObservableOnSubscribe<String> {
//            it.onNext("wang")
//            it.onNext("xian")
//            it.onNext("you")
//            it.onComplete()
//        }).subscribe(object : Observer<String> {
//            override fun onSubscribe(d: Disposable) {
//                LogUtils.e("onSubscribe执行")
//            }
//
//            override fun onComplete() {
//                LogUtils.e("onComplete ===")
//            }
//
//            override fun onNext(t: String) {
//                showTxt.text = t
//                LogUtils.e("-------------------------------$t")
//            }
//
//            override fun onError(e: Throwable) {
//            }
//        })

//        val futureTask = FutureTask(Callable<String> { "wangxianuuuu" })
//        Observable.fromFuture(futureTask).doOnSubscribe { futureTask.run() }.subscribe { t -> LogUtils.e("我的名字是：${futureTask.get()}") }

//        var i = 100
//        val observable = Observable.defer { Observable.just(i) }
//        var observer = Consumer<Int> { LogUtils.e("数值为：$it")}
//        i = 200
//        observable.subscribe(observer)
//        i = 300
//        observable.subscribe(observer)


    }
}