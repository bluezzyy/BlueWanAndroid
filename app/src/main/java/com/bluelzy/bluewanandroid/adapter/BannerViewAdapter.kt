package com.bluelzy.bluewanandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bluelzy.bluewanandroid.R
import com.bluelzy.bluewanandroid.model.BannerItem
import com.bluelzy.bluewanandroid.utils.initImageLoader
import com.bluelzy.bluewanandroid.view.detail.ui.WebViewActivity

/**
 *   @author    BlueLzy
 *   @email     bluehobert@gmail.com
 *   @date      11/25/2020
 *   @desc      Banner Adapter
 */
class BannerViewAdapter(private var banners: List<BannerItem>) :
    RecyclerView.Adapter<BannerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_banner_item, parent, false)
        return BannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {

        initImageLoader(
            holder.itemView.context,
            url = banners[position].imagePath,
            view = holder.image
        ).loadImage()

        holder.itemView.setOnClickListener {
            WebViewActivity.newInstance(
                holder.itemView.context,
                holder.itemView,
                banners[position].url,
                banners[position].title
            )
        }
    }

    override fun getItemCount(): Int = banners.size
}

class BannerViewHolder internal constructor(cardView: View) :
    RecyclerView.ViewHolder(cardView) {

    var image: ImageView = cardView.findViewById(R.id.iv_banner_resource)

}