package io.github.michaelbui99.atlas.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.michaelbui99.atlas.R

class SubredditMainItemAdapter(private val mainSubreddits: List<SubredditMainItem>) : RecyclerView.Adapter<SubredditMainItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =inflater.inflate(R.layout.layout_subreddits_main_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = mainSubreddits[position].name
        holder.description.text = mainSubreddits[position].description
        holder.icon.setImageResource(mainSubreddits[position].icon)
    }

    override fun getItemCount(): Int {
        return mainSubreddits.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.textview_subreddits_name)
        val description: TextView = itemView.findViewById(R.id.textview_subreddits_description)
        val icon: ImageView  = itemView.findViewById(R.id.imageview_subreddits_icon)
    }

}