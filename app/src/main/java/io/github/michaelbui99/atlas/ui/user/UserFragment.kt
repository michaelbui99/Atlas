package io.github.michaelbui99.atlas.ui.user

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import io.github.michaelbui99.atlas.R

class UserFragment : Fragment() {
    private lateinit var userViewModel: UserViewModel

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
        userViewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]

        val view: View
        if (!userViewModel.isLoggedIn.value!!) {
            view = setupLoginFragment(
                viewModel = userViewModel,
                inflater = inflater,
                container = container,
                savedInstanceState = savedInstanceState
            )

            return view
        }

        view = inflater.inflate(R.layout.fragment_user, container, false)
        return view;
    }

    private fun setupLoginFragment(
        viewModel: UserViewModel,
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val loginButton: Button = view.findViewById(R.id.button_login)

        userViewModel.isLoggedIn.observe(this) {
            if (it) {
                activity?.recreate()
            }
        }

        loginButton.setOnClickListener() {
            // TODO: Refactor this. Current implementation for DEBUGGING ONLY!
            Log.i("UserFragment", "CLICKED")
            userViewModel.setLogin(true)
        }

        userViewModel
        return view;
    }

}