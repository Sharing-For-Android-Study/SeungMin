package com.example.flo

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clonecodingflo.Song
import com.example.clonecodingflo.databinding.ItemSongBinding

class SavedSongRVAdapter(private val savedSongList: ArrayList<Song>) : RecyclerView.Adapter<SavedSongRVAdapter.ViewHolder>() {

    interface MyItemClickListener{
        fun onRemoveSong(position: Int)
    }

    private lateinit var mItemClickListener : MyItemClickListener
    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }

    init {
        setHasStableIds(true)
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SavedSongRVAdapter.ViewHolder {
        val binding: ItemSongBinding = ItemSongBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SavedSongRVAdapter.ViewHolder, position: Int) {
        holder.bind(savedSongList[position])
        holder.binding.itemSongMoreIv.setOnClickListener {
            mItemClickListener.onRemoveSong(position)
        }

        holder.binding.itemSongSwitch.apply {
            setOnCheckedChangeListener(null)
            isChecked = savedSongList[position].isChecked
            setOnCheckedChangeListener { _, isChecked ->
                savedSongList[position].isChecked = isChecked
            }
        }
    }

    override fun getItemCount(): Int = savedSongList.size

    @SuppressLint("NotifyDataSetChanged")
    fun addSongs(songs: ArrayList<Song>) {
        this.savedSongList.clear()
        this.savedSongList.addAll(songs)

        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removeSong(position: Int){
        savedSongList.removeAt(position)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemSongBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(song: Song){
            binding.itemSongImgIv.setImageResource(song.coverImg!!)
            binding.itemSongTitleTv.text = song.title
            binding.itemSongSingerTv.text = song.singer
        }
    }
}