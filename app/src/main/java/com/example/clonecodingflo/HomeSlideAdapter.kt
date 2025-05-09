package com.example.clonecodingflo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clonecodingflo.databinding.ItemHomeSlideBinding

class HomeSlideAdapter(
    private val items: List<HomeSlideItem>
) : RecyclerView.Adapter<HomeSlideAdapter.HomeSlideViewHolder>() {

    inner class HomeSlideViewHolder(val binding: ItemHomeSlideBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeSlideViewHolder {
        val binding = ItemHomeSlideBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeSlideViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeSlideViewHolder, position: Int) {
        val item = items[position]
        with(holder.binding) {
            homePannelBackgroundIv.setImageResource(item.backgroundResId)
            homePannelTitleTv.text = item.title
            homePannelAlbumInfo01Tv.text = item.songInfo

            homePannelAlbumImg01Iv.setImageResource(item.albumImageResId1)
            homePannelAlbumTitle01Tv.text = item.songTitle1
            homePannelAlbumSinger02Tv.text = item.singer1

            homePannelAlbumImg02Iv.setImageResource(item.albumImageResId2)
            homePannelAlbumTitle03Tv.text = item.songTitle2
            homePannelAlbumSinger04Tv.text = item.singer2
        }
    }

    override fun getItemCount(): Int = items.size
}
