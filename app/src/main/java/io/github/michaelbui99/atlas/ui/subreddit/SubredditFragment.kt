package io.github.michaelbui99.atlas.ui.subreddit

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.github.michaelbui99.atlas.MainActivity
import io.github.michaelbui99.atlas.R

private const val SUBREDDIT_NAME = "SubredditName"

class SubredditFragment : Fragment() {
    private var subredditName: String? = null
    private lateinit var viewModel: SubredditViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            subredditName = it.getString(SUBREDDIT_NAME)
        }
        viewModel = ViewModelProvider(requireActivity()).get(SubredditViewModel::class.java)
        viewModel.setCurrentSubreddit(subredditName!!)
        Log.i("SubredditFragment", "WAS PASSED: $subredditName")
        setHasOptionsMenu(true)
        (activity as MainActivity).supportActionBar?.title = subredditName;
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("SubredditFragment", "Creating view")
        val rootView = inflater.inflate(R.layout.fragment_subreddit, container, false)
        viewModel.subredditPosts.observe(this) {
        }

        return rootView
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        Log.i("SubredditFragment", "Creating menu")
        inflater.inflate(R.menu.subreddit_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onStart() {
        super.onStart()
    }
}