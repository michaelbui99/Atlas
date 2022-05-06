package io.github.michaelbui99.atlas.ui.subreddit

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import io.github.michaelbui99.atlas.ui.MainActivity
import io.github.michaelbui99.atlas.R

private const val SUBREDDIT_NAME = "SubredditName"

class SubredditFragment : Fragment() {
    private var subredditName: String? = null
    private lateinit var viewModel: SubredditViewModel
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            subredditName = it.getString(SUBREDDIT_NAME)
        }
        viewModel = ViewModelProvider(requireActivity()).get(SubredditViewModel::class.java)
        viewModel.setCurrentSubreddit(subredditName!!)
        Log.i("SubredditFragment", "WAS PASSED: $subredditName")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("SubredditFragment", "Creating view")
        val rootView = inflater.inflate(R.layout.fragment_subreddit, container, false)

        val vpAdapter = SubredditViewPagerAdapter(this)
        viewPager = rootView.findViewById(R.id.viewpager_subreddit)
        viewPager.adapter = vpAdapter

        viewModel.subredditPosts.observe(viewLifecycleOwner) {
        }

        return rootView
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        Log.i("SubredditFragment", "Creating menu")
        inflater.inflate(R.menu.subreddit_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuItem_refresh_subreddit) {
            viewModel.refreshSubreddit()
        }

        if (item.itemId == R.id.menuItem_search_subreddit) {
            viewModel.onSearchMenuItem()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tabTitles = arrayOf("Posts", "About")
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout_subreddit)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        (activity as MainActivity).supportActionBar?.title = subredditName
    }
}