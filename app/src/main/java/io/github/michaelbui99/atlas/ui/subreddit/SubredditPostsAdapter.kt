package io.github.michaelbui99.atlas.ui.subreddit

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.michaelbui99.atlas.R
import io.github.michaelbui99.atlas.model.domain.SubredditPost
import io.github.michaelbui99.atlas.ui.shared.OnItemClickListener

class SubredditPostsAdapter(private var posts: List<SubredditPost>) :
    RecyclerView.Adapter<SubredditPostsAdapter.ViewHolder>() {

    var onTitleClick: OnItemClickListener? = null

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
        holder.createdAgo.text = posts[position].createdUTC

        if (posts[position].thumbnailUrl != null) {
            Glide.with(holder.thumbnail.context).load(posts[position].thumbnailUrl)
                .placeholder(R.drawable.ic_baseline_image_24)
                .into(holder.thumbnail)
        } else {
            Glide.with(holder.thumbnail.context).load(R.drawable.ic_baseline_image_24)
                .into(holder.thumbnail)
        }

        var upvoteCount = ""
        upvoteCount = if (posts[position].postScore >= 1000) {
            "${posts[position].upVoteCount / 1000}k"
        } else {
            posts[position].postScore.toString()
        }
        holder.upvoteCount.text = upvoteCount

        val commentCount = "${posts[position].commentCount} comments"
        holder.commentCount.text = commentCount

        // Null = No vote, false = down vote, true = upvote
        if (posts[position].userHasLiked == true) {
            holder.upvoteButton.setBackgroundResource(R.color.orange)
        } else if (posts[position].userHasLiked == false) {
            holder.downVoteButton.setBackgroundResource(R.color.orange)
        }

        holder.postTitle.setOnClickListener() {
            onTitleClick?.onItemClick(position)
        }
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
        val createdAgo: TextView = itemView.findViewById(R.id.textview_content_createdAgo)
        val thumbnail: ImageView = itemView.findViewById(R.id.imageview_post_media)
        val upvoteButton: ImageView = itemView.findViewById(R.id.imageview_vote_upVote)
        val downVoteButton: ImageView = itemView.findViewById(R.id.imageview_vote_downVote)
    }
}