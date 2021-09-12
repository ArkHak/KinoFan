package com.example.kinofan.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.kinofan.R
import com.example.kinofan.databinding.FragmentMainSliderFilmsBinding
import com.example.kinofan.ui.viewModel.SliderFilmsAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainSliderFilmsFragment : Fragment() {

    private lateinit var adapter: SliderFilmsAdapter

    private var _binding: FragmentMainSliderFilmsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_slider_films, container, false)
        _binding = FragmentMainSliderFilmsBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = SliderFilmsAdapter(this)
        binding.pager.adapter = adapter
        binding.pager.setPageTransformer(ZoomOutPageTransformer())

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = tabNames(position)
        }.attach()
    }

    private fun tabNames(position: Int): String? {
        when (position) {
            0 -> {
                return getString(R.string.watch_now_film)
            }
            1 -> return getString(R.string.coming_soon_film)
        }
        return null
    }

    class ZoomOutPageTransformer : ViewPager2.PageTransformer {
        private val MIN_SCALE = 0.85f
        private val MIN_ALPHA = 0.5f

        override fun transformPage(view: View, position: Float) {
            view.apply {
                val pageWidth = width
                val pageHeight = height
                when {
                    position < -1 -> {
                        alpha = 0f
                    }
                    position <= 1 -> { // [-1,1]
                        val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
                        val vertMargin = pageHeight * (1 - scaleFactor) / 2
                        val horzMargin = pageWidth * (1 - scaleFactor) / 2
                        translationX = if (position < 0) {
                            horzMargin - vertMargin / 2
                        } else {
                            horzMargin + vertMargin / 2
                        }

                        scaleX = scaleFactor
                        scaleY = scaleFactor

                        alpha = (MIN_ALPHA +
                                (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))
                    }
                    else -> { // (1,+Infinity]
                        alpha = 0f
                    }
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}