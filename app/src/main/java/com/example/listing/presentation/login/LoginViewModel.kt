package com.example.listing.presentation.login

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.listing.core.data.CryptoRepository
import com.example.listing.core.data.CurrencyRepository
import com.example.listing.core.data.LoginRepository
import com.example.listing.core.domain.Currency
import com.example.listing.core.domain.User
import com.example.listing.framework.db.UserEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val cryptoRepository: CryptoRepository,
                                         private val loginRepository: LoginRepository,
                                         private val context: Context,
                                         private val sharedPreferences: SharedPreferences) : ViewModel() {

    val loggedUser: MutableLiveData<User> = MutableLiveData()
    val createdUser: MutableLiveData<User> = MutableLiveData()
    private val disposable = CompositeDisposable()

    fun verifyLogin(email: String, password: String) {
        disposable.add(loginRepository.verifyUser(email, password).subscribe ({
            Log.d("LoginViewmodel", "verify login $it")
            sharedPreferences.edit {
                this.putString("email", it.email)
                this.putString("name", it.name)
                this.apply()
            }
            loggedUser.postValue(it)
        }, {
                Log.e("LoginViewmodel", "Error on login", it)
        }))
    }

    fun addUser(email: String, password: String, name: String) {
        disposable.add(loginRepository.addUser(email, password, name)
            .subscribe({
                Log.d("LoginViewmodel", "Enc: $it")
                createdUser.postValue(it)
            }, {
                Log.e("LoginViewmodel", "Error creating user", it)
            })
        )
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}
