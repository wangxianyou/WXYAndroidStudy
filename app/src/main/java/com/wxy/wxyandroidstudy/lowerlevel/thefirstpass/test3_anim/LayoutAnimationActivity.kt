package com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test3_anim

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wxy.wxyandroidstudy.R
import kotlinx.android.synthetic.main.activity_layout_anim.*

class LayoutAnimationActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_anim)
//        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = GridLayoutManager(this,2)
        recyclerview.adapter = MyAdapter(this)


    }

}

class MyAdapter(var mContext: Context):RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_layout_anim, parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    }

    class MyViewHolder(view: View):RecyclerView.ViewHolder(view){

    }
}

