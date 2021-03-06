package io.github.michaelbui99.atlas.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ProgressBar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.michaelbui99.atlas.R
import io.github.michaelbui99.atlas.model.domain.Subreddit
import io.github.michaelbui99.atlas.ui.shared.OnItemClickListener
import io.github.michaelbui99.atlas.ui.shared.SubredditListAdapter

class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        val loadingBar = rootView.findViewById<ProgressBar>(R.id.progressBar_home_loadingBar)

        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        // Setup recycler view for main subreddits
        val mainSubreddits: MutableList<SubredditMainItem> = viewModel.mainSubreddits.value!!
        val onMainSubredditListener: OnItemClickListener = object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                if (mainSubreddits[position].name.lowercase() == "popular") {
                    Navigation.findNavController(view!!)
                        .navigate(R.id.view_subreddit, bundleOf(Pair("SubredditName", "popular")))
                }

                if (mainSubreddits[position].name.lowercase() == "home") {
                    Navigation.findNavController(view!!)
                        .navigate(R.id.view_subreddit, bundleOf(Pair("SubredditName", "home")))
                }
            }
        }

        val recyclerViewMainSubreddits: RecyclerView =
            rootView.findViewById(R.id.recyclerview_subreddits_main)
        recyclerViewMainSubreddits.layoutManager = LinearLayoutManager(rootView.context)
        recyclerViewMainSubreddits.adapter =
            SubredditMainItemAdapter(mainSubreddits, onMainSubredditListener)


        // Setup recycler view for default subreddits
        val recyclerViewDefaultSubreddits: RecyclerView =
            rootView.findViewById(R.id.recyclerview_subreddits_defaults)

        var defaultSubreddits: MutableList<Subreddit> = viewModel.subscribedSubreddits.value!!
        viewModel.subscribedSubreddits.observe(viewLifecycleOwner) {
            Log.i("HomeFragment", "Observed state change, size: ${it.size}")
            defaultSubreddits = it
        }

        viewModel.isLoadingSubreddits.observe(viewLifecycleOwner) {
            isLoadingSubreddits ->
            if (!isLoadingSubreddits) {
                loadingBar.visibility = View.GONE
                recyclerViewDefaultSubreddits.visibility = View.VISIBLE
            }
        }

        val onSubredditItemClick: OnItemClickListener = object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                Navigation.findNavController(view!!).navigate(
                    R.id.view_subreddit, bundleOf(
                        Pair("SubredditName", defaultSubreddits[position].displayName)
                    )
                )
            }
        }

        recyclerViewDefaultSubreddits.layoutManager = LinearLayoutManager(rootView.context)
        recyclerViewDefaultSubreddits.adapter =
            SubredditListAdapter(
                lifecycleOwner = this,
                subscribedSubredditsLivedata = viewModel.subscribedSubreddits,
                subscribedSubreddits = viewModel.subscribedSubreddits.value!!,
                listener = onSubredditItemClick
            )
        return rootView
    }


    override fun onResume() {
        super.onResume()
        viewModel.updateSubreddits()
    }
}
