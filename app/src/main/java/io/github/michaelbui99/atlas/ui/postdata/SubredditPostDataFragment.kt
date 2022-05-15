package io.github.michaelbui99.atlas.ui.postdata

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.michaelbui99.atlas.ui.MainActivity
import io.github.michaelbui99.atlas.R

class SubredditPostDataFragment : Fragment() {
    private var subredditName: String? = null
    private var postId: String? = null
    private lateinit var viewModel: SubredditPostDataViewModel
    private lateinit var postDataContainer: ConstraintLayout
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            subredditName = it.getString("SubredditName")
            postId = it.getString("PostId")
        }
        viewModel = ViewModelProvider(requireActivity()).get(SubredditPostDataViewModel::class.java)
        viewModel.setPostInfo(subredditName = this.subredditName!!, postId = this.postId!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_subreddit_post_data, container, false)

        postDataContainer =
            rootView.findViewById(R.id.constraintLayout_postData_container)
        progressBar = rootView.findViewById(R.id.progressBar_postData_loadingBar)
        val titleTextView = rootView.findViewById<TextView>(R.id.textview_postData_title)
        val linkFlairTextTextView =
            rootView.findViewById<TextView>(R.id.textview_postData_linkFlair)
        val textContentTextView =
            rootView.findViewById<TextView>(R.id.textview_postData_textContent)
        val subredditNameTextView =
            rootView.findViewById<TextView>(R.id.textview_postData_subredditName)
        val authorNameTextView = rootView.findViewById<TextView>(R.id.textview_postData_author)
        val mediaContentImageView =
            rootView.findViewById<ImageView>(R.id.imageview_postData_mediaContent)
        val upVoteImageView =
            rootView.findViewById<ImageView>(R.id.imageview_postData_action_upVote)
        val downVoteImageView =
            rootView.findViewById<ImageView>(R.id.imageview_postData_action_downVote)


        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerview_postData_comments)
        recyclerView.adapter = CommentAdapter()
        recyclerView.layoutManager = LinearLayoutManager(rootView.context)
        recyclerView.isNestedScrollingEnabled = false

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(this.context, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.postData.observe(viewLifecycleOwner) {
            Log.i(
                "SubredditPostDataFragment",
                "Observed State change. Title: ${it.title}. Content: ${it.textContent}"
            )

            // Null = No vote, false = down vote, true = upvote
            if (it.userHasLiked == true) {
                upVoteImageView.setBackgroundResource(R.color.orange)
            }else if (it.userHasLiked == false){
                downVoteImageView.setBackgroundResource(R.color.orange)
            }

            titleTextView.text = it.title
            linkFlairTextTextView.text = it.linkFlairText

            // Link to media is displayed if the post contains no text content
            // and provides user a way to access the link via implicit intent
            if (it.textContent.isNullOrBlank()) {
                textContentTextView.text = it.mediaContent
                textContentTextView.setTextColor(
                    ContextCompat.getColor(
                        this.requireContext(),
                        R.color.primaryColor
                    )
                )
                textContentTextView.setOnClickListener { _ ->
                    if (it.mediaContent != null) {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.mediaContent))
                        startActivity(intent)
                    }
                }
            } else {
                textContentTextView.text = it.textContent
            }

            subredditNameTextView.text = it.subredditName
            val authorText = "by ${it.postAuthor}"
            authorNameTextView.text = authorText
            Glide.with(this).load(it.mediaContent).into(mediaContentImageView)

            if (it.topLevelComments != null) {
                (recyclerView.adapter as CommentAdapter).setComments(it.topLevelComments!!)
            }
        }

        viewModel.isLoadingData.observe(viewLifecycleOwner) {
            if (it) {
                displayDataLoading()
            } else {
                displayPostData()
            }
        }
        return rootView
    }

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        (activity as MainActivity).supportActionBar?.title = subredditName
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.subreddit_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuItem_refresh_subreddit) {
            viewModel.refreshPostData()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun displayDataLoading() {
        postDataContainer.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE
    }

    private fun displayPostData() {
        postDataContainer.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }
}