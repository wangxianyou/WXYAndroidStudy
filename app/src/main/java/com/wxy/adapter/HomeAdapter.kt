package com.wxy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wxy.bean.NewsBean
import com.wxy.wxyandroidstudy.R
import com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test8_retrofit.RetrofitTestActivity

class HomeAdapter(mContext: RetrofitTestActivity, list:List<NewsBean>?): RecyclerView.Adapter<HomeAdapter.MyViewHolder> (){
    private var list = list
    private var mContext = mContext

    fun updateData(list:MutableList<NewsBean>?){
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var holder: MyViewHolder? = null
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_news, null, false)
        if (holder == null) {
            holder = MyViewHolder(view)
            view.tag = holder
        }else{
            holder = view.tag as MyViewHolder
        }
        return holder
    }


    override fun getItemCount(): Int {
        return if (list?.size != null && list?.size!! > 0) list?.size!! else 0
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val bean = list?.get(position)
        holder.title?.text = bean?.title
        holder.time?.text = bean?.pubtime
    }


    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title:TextView? = null
        var time:TextView? = null

        init {
            title = itemView.findViewById<TextView>(R.id.title)
            time = itemView.findViewById<TextView>(R.id.time)
        }

    }

}