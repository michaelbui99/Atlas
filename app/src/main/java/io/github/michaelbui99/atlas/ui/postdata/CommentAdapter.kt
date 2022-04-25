package io.github.michaelbui99.atlas.ui.postdata

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.michaelbui99.atlas.R
import io.github.michaelbui99.atlas.model.domain.Comment

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    private var comments: MutableList<Comment> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setComments(comments: MutableList<Comment>) {
        this.comments = comments
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.layout_postdata_comment_item, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.authorNameTextView.text = comments[position].author

        val commentScoreDisplayText = if (comments[position].score > 1000) {
            "${comments[position].score / 1000}k"
        } else {
            comments[position].score.toString()
        }

        holder.commentScoreTextView.text = commentScoreDisplayText
        holder.commentTextTextView.text = comments[position].commentText
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    inner class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val authorNameTextView =
            itemView.findViewById<TextView>(R.id.textview_postData_comment_authorName)

        val commentScoreTextView =
            itemView.findViewById<TextView>(R.id.textview_postData_comment_score)

        val commentTextTextView =
            itemView.findViewById<TextView>(R.id.textview_postData_comment_commentText)
    }

}