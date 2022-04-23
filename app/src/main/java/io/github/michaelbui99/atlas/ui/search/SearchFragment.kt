package io.github.michaelbui99.atlas.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.michaelbui99.atlas.R
import io.github.michaelbui99.atlas.model.repositories.RedditRepositoryImpl
import io.github.michaelbui99.atlas.ui.shared.OnItemClickListener
import io.github.michaelbui99.atlas.ui.shared.SubredditListAdapter
import java.lang.IllegalStateException


class SearchFragment : Fragment() {
    private lateinit var viewModel: SearchViewModel
    private lateinit var searchResultRecyclerView: RecyclerView
    private lateinit var searchButton: Button
    private lateinit var searchQueryEditText: EditText
    private lateinit var recyclerViewAdapter: SubredditListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflateView(inflater, container)
        initViewModel()
        initViews(rootView!!)

        return rootView as View?
    }

    private fun initViewModel() {
        this.viewModel = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)
        this.viewModel.resultMessage.observe(viewLifecycleOwner) {
            if (it != null) {
                Toast.makeText(
                    requireContext(),
                    it,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun initViews(rootView: View) {
        this.searchResultRecyclerView =
            rootView.findViewById(R.id.recyclerview_searchSubreddits_results)
        this.searchButton = rootView.findViewById(R.id.button_search_search)
        this.searchQueryEditText = rootView.findViewById(R.id.edittext_search_query)

        val onRecyclerViewItemListener = object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                rootView.findNavController().navigate(
                    R.id.view_subreddit, bundleOf(
                        Pair("SubredditName", viewModel.searchResults.value!![position].displayName)
                    )
                )
            }
        }

        recyclerViewAdapter = SubredditListAdapter(
            lifecycleOwner = this.viewLifecycleOwner,
            subscribedSubredditsLivedata = this.viewModel.searchResults,
            subscribedSubreddits = this.viewModel.searchResults.value!!,
            listener = onRecyclerViewItemListener
        )
        searchResultRecyclerView.adapter = recyclerViewAdapter
        searchResultRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        searchButton.setOnClickListener() {
            this.viewModel.searchQuery = this.searchQueryEditText.text.toString()
            this.viewModel.search()
            this.searchQueryEditText.setText("")
        }
    }

    private fun inflateView(inflater: LayoutInflater, container: ViewGroup?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }
}