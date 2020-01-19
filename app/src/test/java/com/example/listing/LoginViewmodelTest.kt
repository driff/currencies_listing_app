package com.example.listing

import com.example.listing.presentation.MainActivity
import com.example.listing.presentation.login.LoginViewModel
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class LoginViewmodelTest {

    lateinit var loginVm: LoginViewModel
    lateinit var activity: MainActivity

    @Before
    fun setup(){
        activity = mock(MainActivity::class.java)

    }

}