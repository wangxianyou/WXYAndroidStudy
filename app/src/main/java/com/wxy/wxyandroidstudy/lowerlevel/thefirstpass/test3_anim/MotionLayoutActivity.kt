package com.wxy.wxyandroidstudy.lowerlevel.thefirstpass.test3_anim

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.wxy.wxyandroidstudy.R

class MotionLayoutActivity : AppCompatActivity() {
    private val url = "http://www.chinanews.com/part/appzt/546/2019-03-26/U624P4T546D2030F25061DT20190905092935.gif"
    private val img = "http://www.chinanews.com/shipin/spfts/20190904/U817P4T309D2305F22899DT20190904100239.jpg"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motion_layout)
        val imageView = findViewById<ImageView>(R.id.gif)
        Glide.with(this).load(url).listener(object :RequestListener<Drawable>{
            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                Log.e("wwww",e.toString())
                return false
            }

            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                Log.e("wwww","onResourceReady")
                return false
            }
        }).into(imageView)
    }
}