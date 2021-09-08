package com.example.kinofan.ui.view

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SliderFilmsAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    companion object {
        private const val ITEM_COUNT = 2
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                return ReleasedFilmsFragment.newInstance(position)
            }
            1 -> {
                return UpcomingFilmsFragment.newInstance(position)
            }
        }
        return ReleasedFilmsFragment.newInstance(position)
    }

    override fun getItemCount(): Int {
        return ITEM_COUNT
    }

}