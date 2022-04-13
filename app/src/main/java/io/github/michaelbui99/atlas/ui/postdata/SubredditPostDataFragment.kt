package io.github.michaelbui99.atlas.ui.postdata

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import io.github.michaelbui99.atlas.MainActivity
import io.github.michaelbui99.atlas.R
import io.github.michaelbui99.atlas.ui.subreddit.SubredditViewModel

class SubredditPostDataFragment : Fragment() {
    private var subredditName: String? = null
    private var postId: String? = null
    private lateinit var viewModel: SubredditPostDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            subredditName = it.getString("SubredditName")
            postId = it.getString("PostId")
        }
        viewModel = ViewModelProvider(requireActivity()).get(SubredditPostDataViewModel::class.java)
        viewModel.setPostInfo(subredditName = this.subredditName!!, postId = this.postId!!)
        setHasOptionsMenu(true)
        (activity as MainActivity).supportActionBar?.title = subredditName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_subreddit_post_data, container, false)

        val titleTextView = rootView.findViewById<TextView>(R.id.textview_postData_title)
        val linkFlairTextTextView =
            rootView.findViewById<TextView>(R.id.textview_postData_linkFlair)
        val textContentTextView =
            rootView.findViewById<TextView>(R.id.textview_postData_textContent)
        val subredditNameTextView =
            rootView.findViewById<TextView>(R.id.textview_postData_subredditName)
        val authorNameTextView = rootView.findViewById<TextView>(R.id.textview_postData_author)

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(this.context, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.postData.observe(viewLifecycleOwner) {
            Log.i(
                "SubredditPostDataFragment",
                "Observed State change. Title: ${it.title}. Content: ${it.textContent}"
            )
            titleTextView.text = it.title
            linkFlairTextTextView.text = it.linkFlairText
            textContentTextView.text = it.textContent
            subredditNameTextView.text = it.subredditName
            authorNameTextView.text = "by ${it.postAuthor}"
        }


        return rootView
    }
}