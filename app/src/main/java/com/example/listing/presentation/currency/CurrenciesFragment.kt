package com.example.listing.presentation.currency


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listing.R
import com.example.listing.core.domain.Currency
import com.example.listing.framework.ViewModelFactory
import com.example.listing.framework.di.FragmentsComponent
import com.example.listing.presentation.MainActivity
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_currencies.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class CurrenciesFragment : Fragment() {

    private val TAG = this.javaClass.canonicalName
    private val disposables = CompositeDisposable()

    lateinit var fragmentComponent: FragmentsComponent
    lateinit var viewModel: CurrencyViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<CurrencyViewModel>
    @Inject
    lateinit var adapterClickListener: AdapterClickListener
    @Inject
    lateinit var sharedPreferences: SharedPreferences
    @Inject
    lateinit var currencyAdapter: CurrencyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_currencies, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentComponent = (activity!! as MainActivity).activityComponent.fragmentComponent().create()
        fragmentComponent.inject(this).also {
            viewModel = ViewModelProviders.of(this, viewModelFactory).get(CurrencyViewModel::class.java)
        }
        disposables.add(adapterClickListener.onCurrencySelected.subscribe({
            Log.i("CurrenciesFragment", "Currency: $it")
            onClick(it)
        }, {
            Log.e(TAG, "Error ", it)
        }))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvCurrencies.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = currencyAdapter
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val mainActivity = activity as MainActivity
        val username = sharedPreferences.getString("name", "")
        mainActivity.supportActionBar?.apply {
            show()
            title = String.format("Hola %s", username)
        }
        viewModel.currencies.observe(this, Observer {
            currencyAdapter.addCurrencies(it)
        })
    }

    fun onClick(currency: Currency) {
        Log.d("CurrenciesFragment", "Click")
        viewModel.selectedCurrency(currency)
        findNavController().navigate(CurrenciesFragmentDirections.actionCurrenciesFragmentToCurrencyDetailsFragment())
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }
}
