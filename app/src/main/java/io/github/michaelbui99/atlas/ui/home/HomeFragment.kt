package io.github.michaelbui99.atlas.ui.home

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.michaelbui99.atlas.R

class HomeFragment : Fragment() {

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

        // TODO: Fetch these from model such that the user can customise which to show
        val mainSubreddits: MutableList<SubredditMainItem> = mutableListOf(
            SubredditMainItem(
                name = resources.getString(R.string.home),
                description = resources.getString(R.string.subreddits_home_description),
                icon = R.drawable.ic_baseline_home_24
            ),
            SubredditMainItem(
                name = resources.getString(R.string.popular),
                description = resources.getString(R.string.subreddits_popular_description),
                icon = R.drawable.ic_baseline_whatshot_24
            ),
        )

        val recyclerView: RecyclerView = rootView.findViewById(R.id.recyclerview_subreddits_main)
        recyclerView.layoutManager = LinearLayoutManager(rootView.context)

        recyclerView.adapter = SubredditMainItemAdapter(mainSubreddits)

        return rootView
    }


}