package io.github.michaelbui99.atlas.ui.home

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import io.github.michaelbui99.atlas.R
import io.github.michaelbui99.atlas.model.domain.Subreddit

@SuppressLint("NotifyDataSetChanged")
class SubredditAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val defaultSubredditsLivedata: MutableLiveData<MutableList<Subreddit>>,
    private val defaultSubreddits: MutableList<Subreddit>,
    val listener: OnItemClickListener
) :
    RecyclerView.Adapter<SubredditAdapter.ViewHolder>() {

    init {
        defaultSubredditsLivedata.observe(lifecycleOwner) {
            Log.i("SubredditAdapter", "UPDATED")
            defaultSubreddits.clear()
            defaultSubreddits.addAll(it)
            defaultSubreddits.forEach { subreddit ->
                Log.i("SubredditAdapter", subreddit.displayName)
            }
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.layout_subreddits_default_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = defaultSubreddits[position].displayName
    }

    override fun getItemCount(): Int {
        return defaultSubreddits.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.textview_dsubreddits_name)

        init {
            itemView.setOnClickListener() {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}

interface OnItemClickListener {
    fun onItemClick(position: Int)
}