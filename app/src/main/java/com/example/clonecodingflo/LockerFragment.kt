package com.example.clonecodingflo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.clonecodingflo.databinding.FragmentLockerBinding
import com.example.clonecodingflo.databinding.FragmentLookBinding
import com.google.android.material.tabs.TabLayoutMediator


class LockerFragment : Fragment() {

    private lateinit var binding: FragmentLockerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentLockerBinding.inflate(inflater, container, false)

        val adapter = LockerVPAdapter(this)
        binding.lockerContentVp.adapter = adapter

        TabLayoutMediator(binding.lockerContentTb, binding.lockerContentVp) { tab, position ->
            tab.text = when (position) {
                0 -> "저장한 곡"
                else -> "음악 파일"
            }
        }.attach()

        return binding.root
    }
}