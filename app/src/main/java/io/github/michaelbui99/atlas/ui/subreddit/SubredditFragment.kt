package io.github.michaelbui99.atlas.ui.subreddit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
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
        Log.i("SubredditFragment", viewModel.getCurrentSubreddit())
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

}