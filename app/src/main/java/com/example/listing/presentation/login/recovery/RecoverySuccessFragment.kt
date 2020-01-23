package com.example.listing.presentation.login.recovery


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.example.listing.R
import com.example.listing.presentation.MainActivity
import kotlinx.android.synthetic.main.fragment_recovery_success.*



/**
 * A simple [Fragment] subclass.
 */
class RecoverySuccessFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recovery_success, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener {
            navigateToLogin()
        }
        val callback = object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                navigateToLogin()
            }
        }
        (activity as MainActivity).onBackPressedDispatcher.addCallback(this, callback)

    }

    private fun navigateToLogin() {
        findNavController().navigate(RecoverySuccessFragmentDirections.actionRecoverySuccessFragmentToLoginFragment())
    }
}
