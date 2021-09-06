package com.example.kinofan

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kinofan.ui.view.FilmsFragment

class NumberAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    companion object {
        private const val ITEM_COUNT = 2
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                return FilmsFragment.newInstance(position)
            }
            1 -> {
                return CommingSoonFilmsFragment.newInstance(position)
            }
        }
        return FilmsFragment.newInstance(position)
    }

    override fun getItemCount(): Int {
        return ITEM_COUNT
    }

}