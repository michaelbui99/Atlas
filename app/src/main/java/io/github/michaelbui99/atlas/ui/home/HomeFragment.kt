package io.github.michaelbui99.atlas.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.ui.NavigationUI
import io.github.michaelbui99.atlas.R

class HomeFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


}