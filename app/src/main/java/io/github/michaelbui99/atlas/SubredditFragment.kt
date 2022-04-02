package io.github.michaelbui99.atlas

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

private const val SUBREDDIT_NAME = "SubredditName"

class SubredditFragment : Fragment() {
    private var subredditName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            subredditName = it.getString(SUBREDDIT_NAME)
        }
        Log.i("SubredditFragment", "WAS PASSED: $subredditName")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_subreddit, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}