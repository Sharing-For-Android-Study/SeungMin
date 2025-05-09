package com.example.clonecodingflo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clonecodingflo.databinding.FragmentLockerSavedsongBinding
import com.example.flo.SavedSongRVAdapter

class SavedSongFragment : Fragment() {
    lateinit var binding: FragmentLockerSavedsongBinding
    private var savedSongDatas = ArrayList<Song>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerSavedsongBinding.inflate(inflater, container, false)

        savedSongDatas.apply {
            add(Song("Lilac",                 "아이유 (IU)",             0, 0, false, "music_lilac", R.drawable.img_album_exp2))
            add(Song("Weekend",               "태연 (Taeyeon)",          0, 0, false, "music_lilac", R.drawable.img_album_exp6))
            add(Song("Butter",                "방탄소년단 (BTS)",        0, 0, false, "music_lilac", R.drawable.img_album_exp))
            add(Song("Black Mamba",           "에스파 (aespa)",          0, 0, false, "music_lilac", R.drawable.img_album_exp3))
            add(Song("Permission to Dance",   "방탄소년단 (BTS)",        0, 0, false, "music_lilac", R.drawable.img_album_exp))
            add(Song("BBoom BBoom",           "모모랜드 (MOMOLAND)",      0, 0, false, "music_lilac", R.drawable.img_album_exp5))
            add(Song("I",                     "태연 (Taeyeon)",           0, 0, false, "music_lilac", R.drawable.img_album_exp6))
            add(Song("Next Level",            "에스파 (aespa)",          0, 0, false, "music_lilac", R.drawable.img_album_exp3))
            add(Song("Mikrokosmos",           "방탄소년단 (BTS)",        0, 0, false, "music_lilac", R.drawable.img_album_exp4))
            add(Song("Coin",                  "아이유 (IU)",             0, 0, false, "music_lilac", R.drawable.img_album_exp2))
            add(Song("Boy with Luv",          "방탄소년단 (BTS)",        0, 0, false, "music_lilac", R.drawable.img_album_exp4))
            add(Song("BAAM",                  "모모랜드 (MOMOLAND)",      0, 0, false, "music_lilac", R.drawable.img_album_exp5))
        }


        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initRecyclerview()
    }

    private fun initRecyclerview(){
        val savedSongRVAdapter = SavedSongRVAdapter(savedSongDatas)
        binding.lockerSavedSongRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.lockerSavedSongRecyclerView.adapter = savedSongRVAdapter

        savedSongRVAdapter.setMyItemClickListener(object : SavedSongRVAdapter.MyItemClickListener{
            override fun onRemoveSong(position: Int) {
                savedSongRVAdapter.removeSong(position)
            }

        })

    }
}