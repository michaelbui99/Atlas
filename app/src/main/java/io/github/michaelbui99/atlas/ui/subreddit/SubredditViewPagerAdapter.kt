package io.github.michaelbui99.atlas.ui.subreddit

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import io.github.michaelbui99.atlas.ui.home.HomeFragment

const val TAB_COUNT = 2

class SubredditViewPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return TAB_COUNT
    }

    override fun createFragment(position: Int): Fragment {
        if (position == 0){
            return SubredditPostsFragment()
        }

        return SubredditAboutFragment()
    }
}