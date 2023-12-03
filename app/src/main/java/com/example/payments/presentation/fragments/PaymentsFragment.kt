package com.example.payments.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.payments.R
import com.example.payments.databinding.FragmentPaymentsBinding
import com.example.payments.databinding.PaymentCardBinding
import com.example.payments.presentation.PaymentsApp
import com.example.payments.presentation.ViewModelFactory
import com.example.payments.presentation.adapters.PaymentsAdapter
import com.example.payments.presentation.viewModels.PaymentsViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


class PaymentsFragment : Fragment() {

    private val args by navArgs<PaymentsFragmentArgs>()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var paymentsViewModel: PaymentsViewModel

    private val component by lazy {
        ((requireActivity().application) as PaymentsApp).component
    }

    private var _binding: FragmentPaymentsBinding? = null
    private val binding: FragmentPaymentsBinding
        get() = _binding ?: throw RuntimeException("FragmentPaymentsBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPaymentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        paymentsViewModel = ViewModelProvider(this, viewModelFactory)[PaymentsViewModel::class.java]
        val adapter = PaymentsAdapter()
        binding.rvPayments.adapter = adapter
        addCustomToolBar()
        showPayments(adapter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    private fun showPayments(adapter: PaymentsAdapter) {
        paymentsViewModel.getPaymentsList(args.token)
        paymentsViewModel.finalPaymentsList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
    private fun addCustomToolBar() {
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.main_navigation, true)
            .build()
        binding.customToolbar.tvScreenName.text = "Платежи"
        binding.customToolbar.ivLogOut.setOnClickListener {
            findNavController().navigate(R.id.action_paymentsFragment_to_authorizationFragment,null, navOptions)
        }
    }
}