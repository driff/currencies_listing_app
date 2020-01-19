package com.example.listing.presentation.login

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.listing.R
import com.example.listing.framework.ViewModelFactory
import com.example.listing.framework.di.FragmentsComponent
import com.example.listing.presentation.MainActivity
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject


class LoginFragment : Fragment() {

    @Inject lateinit var viewModelFactory: ViewModelFactory<LoginViewModel>
    private lateinit var viewModel: LoginViewModel
    lateinit var fragmentComponent: FragmentsComponent
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentComponent = (activity!! as MainActivity).activityComponent.fragmentComponent().create()
        fragmentComponent.inject(this).also {
            viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
        }
        findNavController().addOnDestinationChangedListener { controller, destination, arguments ->
            Log.d("LoginFragment", "controller: ${controller.currentDestination.toString()} destination: ${destination.navigatorName}") }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bnLogin.setOnClickListener {
            Log.d("LoginFragment", "BnLoginClick")
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToCurrenciesGraph())
        }

        bnSignup.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignupFragment(edEmail.text.toString(), edPassword.text.toString()))
        }

    }
}
