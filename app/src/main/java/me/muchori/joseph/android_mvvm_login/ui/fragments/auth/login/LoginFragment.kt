package me.muchori.joseph.android_mvvm_login.ui.fragments.auth.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import me.muchori.joseph.android_mvvm_login.R
import me.muchori.joseph.android_mvvm_login.databinding.FragmentLoginBinding
import me.muchori.joseph.android_mvvm_login.network.NetworkResponse
import me.muchori.joseph.android_mvvm_login.network.api.LoginApi
import me.muchori.joseph.android_mvvm_login.network.retrofit.RetrofitInstance
import me.muchori.joseph.android_mvvm_login.repository.userRepository.AuthRepository
import me.muchori.joseph.android_mvvm_login.repository.userRepository.ProtoDataStoreRepository
import me.muchori.joseph.android_mvvm_login.ui.fragments.home.HomeActivity
import me.muchori.joseph.android_mvvm_login.viewmodels.auth.AuthViewModel
import me.muchori.joseph.android_mvvm_login.viewmodels.viemmodelfactory.ViewModelFactory


class LoginFragment : Fragment() {

    private lateinit var authViewModel: AuthViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var dataRepository: ProtoDataStoreRepository
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val retrofitInstance = RetrofitInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        dataRepository = ProtoDataStoreRepository(requireContext())
        viewModelFactory =
            ViewModelFactory(AuthRepository(retrofitInstance.buildApi(LoginApi::class.java)))
        authViewModel =
            ViewModelProvider(requireActivity(), viewModelFactory)[AuthViewModel::class.java]
        binding.viewmodel = authViewModel


        authViewModel.loginResponse.observe(viewLifecycleOwner, { user ->
            when (user) {
                is NetworkResponse.Success -> {
                    try {
                        lifecycleScope.launch {
//                            authViewModel.updateUserDetails(user.data!!)
                            startActivity(Intent(requireActivity(), HomeActivity::class.java))
                            Toast.makeText(
                                requireContext(),
                                "Welcome " + user.data!!.data.user.firstName,
                                Toast.LENGTH_LONG
                            ).show()
                            requireActivity().finish()
                        }
                    } catch (t: Throwable) {
                        throw t
                    }
                }
                is NetworkResponse.Error -> {
                    Toast.makeText(requireContext(), user.message.toString(), Toast.LENGTH_LONG)
                        .show()
                }
                is NetworkResponse.Loading -> {
                    Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_LONG).show()
                }
            }
        })

        binding.login.setOnClickListener {
            getUserData()

        }
        binding.registerLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        return binding.root
    }

    private fun getUserData() {
        val email = binding.loginemail.text.toString().trim()
        val password = binding.loginPass.text.toString().trim()
        authViewModel.loginUser(email, password)
    }
}