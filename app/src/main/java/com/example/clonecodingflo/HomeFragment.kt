package com.example.clonecodingflo

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.clonecodingflo.databinding.FragmentHomeBinding
import com.google.gson.Gson
import me.relex.circleindicator.CircleIndicator3

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var albumDatas = ArrayList<Album>()

    private lateinit var slideAdapter: HomeSlideAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var indicator: CircleIndicator3
    private val handler = Handler(Looper.getMainLooper())
    private var currentPage = 0

    private val slideRunnable = object : Runnable {
        override fun run() {
            if (::slideAdapter.isInitialized && slideAdapter.itemCount > 0) {
                currentPage = (currentPage + 1) % slideAdapter.itemCount
                viewPager.setCurrentItem(currentPage, true)
                handler.postDelayed(this, 5000)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        albumDatas.apply {
            add(Album("Butter", "BTS", R.drawable.img_album_exp))
            add(Album("Lilac", "아이유(IU)", R.drawable.img_album_exp2))
            add(Album("Next Level", "에스파", R.drawable.img_album_exp3))
            add(Album("Boy with Luv", "BTS", R.drawable.img_album_exp4))
            add(Album("BBoom BBoom", "모모랜드", R.drawable.img_album_exp5))
            add(Album("Weekend", "태연", R.drawable.img_album_exp6))
        }

        val albumRVAdapter = AlbumRVAdapter(albumDatas)
        binding.homeTodayMusicAlbumRv.adapter = albumRVAdapter
        binding.homeTodayMusicAlbumRv.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL, false)

        albumRVAdapter.setMyItemClickListener(object : AlbumRVAdapter.MyItemClickListener {
            override fun onItemClick(album: Album) {
                changeAlbumFragment(album)
            }

            override fun onPlayClick(album: Album) {
                (activity as? MainActivity)?.setMiniPlayer(
                    Song(
                        title     = album.title.toString(),
                        singer    = album.singer.toString(),
                        second    = 0,
                        playTime  = 60,
                        isPlaying = true,
                        music     = "",
                        coverImg  = album.coverImg
                    )
                )
            }

        })


        val slideItems = listOf(
            HomeSlideItem(
                R.drawable.img_first_album_default,
                "포근하게 덮어주는 꿈의\n목소리",
                "총 15곡 2019.11.11",
                R.drawable.img_album_exp,
                "잠이 안온다",
                "젠(zen)",
                R.drawable.img_album_exp2,
                "돌림 노래",
                "아이유 (IU)"
            ),
            HomeSlideItem(
                R.drawable.img_album_exp4,
                "오늘은 만우절\n즐거운 노래",
                "총 12곡 2025.04.01",
                R.drawable.img_album_exp5,
                "제목",
                "가수",
                R.drawable.img_album_exp6,
                "제목",
                "가수"
            )
        )

        slideAdapter = HomeSlideAdapter(slideItems)
        viewPager = binding.homeSlideVp
        indicator = binding.homeSlideIndicator

        viewPager.adapter = slideAdapter
        indicator.setViewPager(viewPager)
        handler.postDelayed(slideRunnable, 5000)

        val bannerAdapter = BannerVPAdapter(this)
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp2))
        binding.homeBannerVp.adapter = bannerAdapter
        binding.homeBannerVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacks(slideRunnable)
    }
}

private fun HomeFragment.changeAlbumFragment(album: Album) {
    (context as MainActivity).supportFragmentManager.beginTransaction()
        .replace(R.id.main_frm, AlbumFragment().apply {
            arguments = Bundle().apply {
                val gson = Gson()
                val albumJson = gson.toJson(album)
                putString("album", albumJson)
            }
        })
        .commitAllowingStateLoss()
}
