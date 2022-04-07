package io.github.michaelbui99.atlas.ui.subreddit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.michaelbui99.atlas.R

class SubredditPostsFragment : Fragment() {

    private lateinit var viewModel: SubredditViewModel

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
        val rootView = inflater.inflate(R.layout.fragment_subreddit_posts, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(SubredditViewModel::class.java)

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(this.context, it, Toast.LENGTH_SHORT).show()
        }

        val rcAdapter = SubredditPostsAdapter(listOf())
        viewModel.subredditPosts.observe(viewLifecycleOwner) {
            rcAdapter.setPosts(it)
        }

        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerview_subredditPosts)
        recyclerView.adapter = rcAdapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        return rootView
    }
}