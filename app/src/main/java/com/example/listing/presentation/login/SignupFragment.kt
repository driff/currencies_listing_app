package com.example.listing.presentation.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.listing.R
import com.example.listing.framework.ViewModelFactory
import com.example.listing.presentation.MainActivity
import kotlinx.android.synthetic.main.fragment_signup.*
import javax.inject.Inject

/**
 * A fragment with a Google +1 button.
 */
class SignupFragment : Fragment() {

    lateinit var viewModel: LoginViewModel
    @Inject lateinit var viewModelFactory: ViewModelFactory<LoginViewModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity!! as MainActivity).activityComponent.inject(this).also {
            viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
        }
        findNavController().addOnDestinationChangedListener { controller, destination, arguments ->
            Log.d("LoginFragment", "controller: ${controller.currentDestination.toString()} destination: ${destination.navigatorName}") }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bnSignUp.setOnClickListener {
            Log.d("Signup", "signup Click")
            val email = edEmail.text.toString().trim()
            val password = edPassword.text.toString().trim()
            val name = edName.text.toString().trim()
            if (email.isEmpty()) {
                tlEmail.isErrorEnabled = true
                tlEmail.error = "Write an Email"
            } else if(password.isEmpty()) {
                tlPassword.isErrorEnabled = true
                tlPassword.error = "Write a Password"
            } else if(name.isEmpty()) {
                tlName.isErrorEnabled = true
                tlName.error = "Write a name"
            } else
                viewModel.addUser(email, password, name)

        }
        viewModel.createdUser.observe(this, Observer { findNavController().navigateUp() })
    }

}