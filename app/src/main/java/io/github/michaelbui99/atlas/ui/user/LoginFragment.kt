package io.github.michaelbui99.atlas.ui.user

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import io.github.michaelbui99.atlas.R

class LoginFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_login, container, false)

        val viewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]
        viewModel.isLoggedIn.observe(this) {
            if (it) {
                Log.i("LoginFragment", "Navigating to new view")
                findNavController().navigate(R.id.view_user)
            }
        }

        val loginButton: Button = rootView.findViewById(R.id.button_login)
        loginButton.setOnClickListener(){
            // TODO: Refactor this. Current implementation for DEBUGGING ONLY!
            viewModel.setLogin(true)
        }

        return rootView
    }
}