package com.example.listing.presentation.login

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.listing.R
import com.example.listing.framework.ViewModelFactory
import com.example.listing.presentation.MainActivity
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject


class LoginFragment : Fragment() {

    @Inject lateinit var viewModelFactory: ViewModelFactory<LoginViewModel>
    private lateinit var viewModel: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity!! as MainActivity).activityComponent.inject(this)
        .also {
            viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
        }
        findNavController().addOnDestinationChangedListener { controller, destination, arguments ->
            Log.d("LoginFragment", "controller: ${controller.currentDestination.toString()} destination: ${destination.navigatorName}") }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loggedUser.observe(this, Observer {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToCurrenciesGraph())
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val mainActivity = activity as MainActivity
        mainActivity.supportActionBar?.hide()
        bnLogin.setOnClickListener {
            Log.d("LoginFragment", "BnLoginClick")
            viewModel.verifyLogin(edEmail.text.toString(), edPassword.text.toString())
        }

        bnSignup.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignupFragment(edEmail.text.toString(), edPassword.text.toString()))
        }

    }
}
