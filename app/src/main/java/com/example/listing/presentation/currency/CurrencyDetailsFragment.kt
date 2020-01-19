package com.example.listing.presentation.currency


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.listing.R
import com.example.listing.framework.ViewModelFactory
import com.example.listing.framework.di.FragmentsComponent
import com.example.listing.presentation.MainActivity
import kotlinx.android.synthetic.main.fragment_currency_details.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class CurrencyDetailsFragment : Fragment() {

    lateinit var fragmentComponent: FragmentsComponent
    lateinit var viewModel: CurrencyViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<CurrencyViewModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_currency_details, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentComponent = (activity!! as MainActivity).activityComponent.fragmentComponent().create()
        fragmentComponent.inject(this).also {
            viewModel = ViewModelProviders.of(this, viewModelFactory).get(CurrencyViewModel::class.java)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.opennedCurrency.observe(this, Observer {
            it.apply {
                tvCodigo.text = String.format("Codigo: %s", codigo)
                tvFecha.text = String.format("Fecha: %s", fecha)
                tvNombre.text = String.format("Nombre: %s", nombre)
                tvUnidadMedida.text = String.format("U. Medida: %s", unidadMedida)
                tvValor.text = String.format("Valor: %f", valor)
            }
        })
    }
}
