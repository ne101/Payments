package com.example.payments.presentation.fragments

import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.payments.R
import com.example.payments.databinding.FragmentAuthorizationBinding
import com.example.payments.domain.entities.userEntities.UserEntity
import com.example.payments.presentation.viewModels.AuthorizationViewModel
import com.example.payments.presentation.PaymentsApp
import com.example.payments.presentation.ViewModelFactory
import javax.inject.Inject

class AuthorizationFragment : Fragment() {

    private var _binding: FragmentAuthorizationBinding? = null
    private val binding: FragmentAuthorizationBinding
        get() = _binding ?: throw RuntimeException("FragmentAuthorizationBinding == null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var authorizationViewModel: AuthorizationViewModel

    private val component by lazy {
        ((requireActivity().application) as PaymentsApp).component
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAuthorizationBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authorizationViewModel = ViewModelProvider(
            this, viewModelFactory
        )[AuthorizationViewModel::class.java]
        addCustomToolBar()
        resetColor()
        authorizationViewModel.isEmpty.observe(viewLifecycleOwner) { empty ->
            if (empty) {
                Toast.makeText(context, "У вас есть пустые поля", Toast.LENGTH_SHORT).show()
            }
            authorizationViewModel.isError.observe(viewLifecycleOwner) { error ->
                if (error && !empty) {
                    Toast.makeText(context, "Неверные данные", Toast.LENGTH_SHORT).show()
                }
            }
        }

        launchNextScreen()
    }

    private fun addCustomToolBar() {
        binding.customToolbar.tvScreenName.text = "Авторизация"
        binding.customToolbar.ivLogOut.visibility = View.INVISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    private fun checkValid() {
        val userEntity = UserEntity("demo", "12345")
        authorizationViewModel.login(userEntity)
        if (authorizationViewModel.checkValid(
                binding.etLogin,
                binding.etPassword,
                userEntity
            )
        ) {
            authorizationViewModel.login(userEntity)
            authorizationViewModel.loginResult.observe(viewLifecycleOwner) {
                if (findNavController().currentDestination?.id == R.id.authorizationFragment) {
                    findNavController().navigate(
                        AuthorizationFragmentDirections.actionAuthorizationFragmentToPaymentsFragment(
                            it.token
                        )
                    )
                }
            }
        }
    }

    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }


    private fun resetColor() {
        binding.etLogin.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.etLogin.setBackgroundColor(Color.parseColor("#F1EFEF"))
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.etPassword.setBackgroundColor(Color.parseColor("#F1EFEF"))
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }

    private fun launchNextScreen() {
        binding.button.setOnClickListener {
            context?.let {
                if (isOnline(it)) {
                    checkValid()
                } else {
                    Toast.makeText(context, "Нет подключения к интернету!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}