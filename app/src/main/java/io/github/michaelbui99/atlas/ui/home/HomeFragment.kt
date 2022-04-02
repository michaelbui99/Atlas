package io.github.michaelbui99.atlas.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.michaelbui99.atlas.R
import io.github.michaelbui99.atlas.model.domain.Subreddit
import java.text.FieldPosition

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

        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

        // Setup recycler view for main subreddits
        val mainSubreddits: MutableList<SubredditMainItem> = viewModel.mainSubreddits.value!!
        val recyclerViewMainSubreddits: RecyclerView =
            rootView.findViewById(R.id.recyclerview_subreddits_main)
        recyclerViewMainSubreddits.layoutManager = LinearLayoutManager(rootView.context)
        recyclerViewMainSubreddits.adapter = SubredditMainItemAdapter(mainSubreddits)

        // Setup recycler view for default subreddits
        var defaultSubreddits: MutableList<Subreddit> = viewModel.defaultSubreddits.value!!
        viewModel.defaultSubreddits.observe(this) {
            defaultSubreddits = it
        }

        val recyclerViewDefaultSubreddits: RecyclerView =
            rootView.findViewById(R.id.recyclerview_subreddits_defaults)

        recyclerViewDefaultSubreddits.layoutManager = LinearLayoutManager(rootView.context)
        recyclerViewDefaultSubreddits.adapter =
            SubredditListAdapter(
                lifecycleOwner = this,
                defaultSubredditsLivedata = viewModel.defaultSubreddits,
                defaultSubreddits = viewModel.defaultSubreddits.value!!,
                listener = OnSubredditClick(defaultSubreddits)
            )


        return rootView
    }

    inner class OnSubredditClick(val subreddits: MutableList<Subreddit>) : OnItemClickListener {
        override fun onItemClick(position: Int) {
            Log.i("HomeFragment DEBUG", "Subreddit: ${subreddits[position].displayName}")
        }
    }

}
