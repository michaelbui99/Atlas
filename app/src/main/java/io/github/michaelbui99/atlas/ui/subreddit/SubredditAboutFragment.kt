package io.github.michaelbui99.atlas.ui.subreddit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import io.github.michaelbui99.atlas.R
import io.github.michaelbui99.atlas.model.domain.SubredditAbout

class SubredditAboutFragment : Fragment() {
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
        val rootView = inflater.inflate(R.layout.fragment_subreddit_about, container, false)
        val description = rootView.findViewById<TextView>(R.id.textview_about_description)

        viewModel = ViewModelProvider(requireActivity()).get(SubredditViewModel::class.java)

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(this.context, it, Toast.LENGTH_SHORT).show()
        }
        viewModel.subredditAbout.observe(viewLifecycleOwner) {
            description.text = it.description
        }

        return rootView
    }
}