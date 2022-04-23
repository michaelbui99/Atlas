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
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import io.github.michaelbui99.atlas.R
import io.github.michaelbui99.atlas.model.auth.RedditAuthStore
import io.github.michaelbui99.atlas.model.auth.STATE
import io.github.michaelbui99.atlas.model.repositories.RedditRepositoryImpl
import io.github.michaelbui99.atlas.model.util.getMonthsBetweenLocalDateTimes
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDateTime

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

        val nameTextView = view.findViewById<TextView>(R.id.textview_user_name)
        val karmaCountTextView = view.findViewById<TextView>(R.id.textview_user_karmaCount)
        val accountAgeTextView = view.findViewById<TextView>(R.id.textview_user_accountAge)
        val iconImageView = view.findViewById<ImageView>(R.id.imageview_user_icon)

        userViewModel.user.observe(viewLifecycleOwner) {
            nameTextView.text = it?.displayName
            karmaCountTextView.text = getFormattedKarmaCount(it?.karmaCount)
            accountAgeTextView.text = getFormattedAccountAgeText(it?.createdUtc)
            loadIconWithGlide(requireView(), iconImageView, it?.iconUrl)
        }

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

        userViewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(this.context, "Something went wrong...: $it", Toast.LENGTH_SHORT).show()
        }

        loginButton.setOnClickListener() {
            Log.i("UserFragment", requireActivity().localClassName)
            startSignIn()
        }

        userViewModel
        return view;
    }

    private fun startSignIn() {
        val url = userViewModel.getAuthUrl()
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }


    override fun onResume() {
        super.onResume()

        // Handling of oauth grant
        if (requireActivity().intent != null && requireActivity().intent.action == Intent.ACTION_VIEW) {
            Log.i("UserFragment", "RESUMED")
            val uri: Uri = requireActivity().intent.data!!
            if (uri.getQueryParameter("error") != null) {
                val error = uri.getQueryParameter("error")
                Log.e("UserFragment", "Failed to auth: $error")
            } else {
                val state = uri.getQueryParameter("state")
                if (state == STATE) {
                    val code = uri.getQueryParameter("code")
                    Log.i("UserFragment", "User auth code: $code")
                    if (userViewModel.isLoggedIn.value == false) {
                        userViewModel.userGrantsAuthPermissions(code!!)
                    }
                }
            }
        }
    }
}


private fun getFormattedAccountAgeText(createdDate: LocalDateTime?): String {
    if (createdDate == null) {
        return "0y 0m"
    }

    val monthsSinceAccountCreation =
        getMonthsBetweenLocalDateTimes(createdDate, LocalDateTime.now())

    val years = (monthsSinceAccountCreation / 12)
    val months = monthsSinceAccountCreation % 12

    return "${years}y ${months}m"
}

private fun getFormattedKarmaCount(karmaCount: Long?): String {
    if (karmaCount == null) {
        return "0"
    }

    if (karmaCount > 1000) {
        return "${BigDecimal(karmaCount.toDouble() / 1000.0).setScale(1, RoundingMode.HALF_UP)}k"
    }

    return karmaCount.toString()
}

private fun loadIconWithGlide(view: View, imageView: ImageView, iconUrl: String?) {
    Glide.with(view).load(iconUrl).placeholder(R.drawable.ic_baseline_person_24).into(imageView)
}