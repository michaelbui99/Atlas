package io.github.michaelbui99.atlas.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.michaelbui99.atlas.R

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

        // TODO: Fetch these from viewmodel such that the user can customise which to show
        val mainSubreddits: MutableList<SubredditMainItem> = viewModel.mainSubreddits.value!!
        val recyclerView: RecyclerView = rootView.findViewById(R.id.recyclerview_subreddits_main)
        recyclerView.layoutManager = LinearLayoutManager(rootView.context)

        recyclerView.adapter = SubredditMainItemAdapter(mainSubreddits)

        return rootView
    }


}