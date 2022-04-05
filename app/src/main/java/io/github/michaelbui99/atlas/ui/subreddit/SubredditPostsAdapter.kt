package io.github.michaelbui99.atlas.ui.subreddit

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.michaelbui99.atlas.R
import io.github.michaelbui99.atlas.model.domain.SubredditPost

class SubredditPostsAdapter(private var posts: List<SubredditPost>) :
    RecyclerView.Adapter<SubredditPostsAdapter.ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setPosts(posts: List<SubredditPost>) {
        this.posts = posts
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.layout_subreddit_post_item_default, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.postScore.text = posts[position].postScore.toString()
        holder.postTitle.text = posts[position].postTitle
        holder.subredditName.text = posts[position].subredditName
        holder.postSource.text = posts[position].sourceDomain

        var upvoteCount = ""
        if (posts[position].postScore >= 1000) {
            upvoteCount = "${posts[position].upVoteCount% 1000}k"
        } else {
            upvoteCount = posts[position].postScore.toString()
        }

        holder.upvoteCount.text = upvoteCount
        val commentCount = "${posts[position].commentCount} comments"
        holder.commentCount.text = commentCount
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postScore: TextView = itemView.findViewById(R.id.textview_vote_postScore)
        val postTitle: TextView = itemView.findViewById(R.id.textview_content_title)
        val subredditName: TextView = itemView.findViewById(R.id.textview_content_subredditName)
        val upvoteCount: TextView = itemView.findViewById(R.id.textview_content_upvoteCount)
        val commentCount: TextView = itemView.findViewById(R.id.textview_content_commentCount)
        val postSource: TextView = itemView.findViewById(R.id.textview_content_source)
    }
}