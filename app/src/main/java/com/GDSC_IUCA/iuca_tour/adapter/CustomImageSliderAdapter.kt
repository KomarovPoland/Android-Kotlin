package com.GDSC_IUCA.iuca_tour.adapter

import android.R
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide


class CustomImageSliderAdapter(context: Context?, resources: Array<String>) : PagerAdapter() {

//    var mContext: Context
//    var mLayoutInflater: LayoutInflater
//    var mResources: Array<String>
//    override fun getCount(): Int {
//        return mResources.size
//    }
//
//    override fun isViewFromObject(view: View, `object`: Any): Boolean {
//        return view === `object`
//    }
//
//    override fun instantiateItem(container: ViewGroup, position: Int): Any {
//        Log.d(
//            TAG,
//            "instantiateItem() called with: container = [$container], position = [$position]"
//        )
//        val itemView: View = mLayoutInflater.inflate(R.layout.item_pager, container, false)
//        Log.d(TAG, "load in gallery:" + mResources[position] + "#end")
//        val ivPhoto: ImageView = itemView.findViewById(R.id.iv_photo) as ImageView
//        if (mResources[position] != "") {
//            Glide.with(mContext)
//                .load(mResources[position].trim { it <= ' ' })
//                .crossFade()
//                .into(ivPhoto)
//        }
//        container.addView(itemView)
//        return itemView
//    }
//
//    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        Log.d(
//            TAG,
//            "destroyItem() called with: " + "container = [" + container + "], position = [" + position
//                    + "], object = [" + `object` + "]"
//        )
//        container.removeView(`object` as LinearLayout)
//    }
//
//    companion object {
//        private const val TAG = "ImageViewPage"
//    }

    //    init {
//        mContext = context
//        mResources = resources
//        mLayoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
//    }
    override fun getCount(): Int {
        TODO("Not yet implemented")
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        TODO("Not yet implemented")
    }
}