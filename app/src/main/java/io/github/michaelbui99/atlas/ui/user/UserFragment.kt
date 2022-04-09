package io.github.michaelbui99.atlas.ui.user

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import io.github.michaelbui99.atlas.R
import io.github.michaelbui99.atlas.model.auth.RedditAuthenticationManager

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

        userViewModel.isLoggedIn.observe(viewLifecycleOwner) {
            if (it) {
                activity?.recreate()
            }
        }

        loginButton.setOnClickListener() {
            Log.i("UserFragment", requireActivity().localClassName)
            startSignIn()
        }

        userViewModel
        return view;
    }

    private fun startSignIn() {
        val url = RedditAuthenticationManager.getAuthUrl()
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }


    override fun onResume() {
        super.onResume()
        if (requireActivity().intent != null && requireActivity().intent.action == Intent.ACTION_VIEW) {
            Log.i("UserFragment", "RESUMED")
            val uri: Uri = requireActivity().intent.data!!
            if (uri.getQueryParameter("error") != null) {
                val error = uri.getQueryParameter("error")
                Log.e("UserFragment", "Failed to auth: $error")
            } else {
                val state = uri.getQueryParameter("state")
                if (state == RedditAuthenticationManager.getExpectedState()) {
                    val code = uri.getQueryParameter("code")
                    Log.i("UserFragment", "User auth code: $code")
                }
            }
        }
    }
}