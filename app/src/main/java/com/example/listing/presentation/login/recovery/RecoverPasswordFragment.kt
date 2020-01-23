package com.example.listing.presentation.login.recovery


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.listing.R
import com.example.listing.framework.ViewModelFactory
import com.example.listing.presentation.MainActivity
import com.example.listing.presentation.login.LoginViewModel
import kotlinx.android.synthetic.main.fragment_recover_password.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class RecoverPasswordFragment : Fragment() {

    @Inject lateinit var viewModelFactory: ViewModelFactory<LoginViewModel>
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recover_password, container, false)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity!! as MainActivity).activityComponent.inject(this)
            .also {
                viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bnRecover.setOnClickListener {
            if (verifyData()) {
                viewModel.recoverPassword(edEmail.text.toString(), edPassword.text.toString())
            }
        }
        viewModel.updatedUser.observe(this, Observer {
            Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
            // TODO: Navigate to next view
            findNavController().navigate(RecoverPasswordFragmentDirections.actionRecoverPasswordToRecoverySuccessFragment(), navOptions { popUpTo })
        })
    }

    private fun verifyData(): Boolean {
        if (edEmail.text.isNullOrEmpty()) {
            tlEmail.apply {
                isErrorEnabled = true
                error = "Invalid Email"
            }
            return false
        }
        if (edPassword.text.isNullOrEmpty()){
            tlPassword.apply {
                isErrorEnabled = true
                error = "Invalid Password"
            }
            return false
        }
        return true
    }
}
