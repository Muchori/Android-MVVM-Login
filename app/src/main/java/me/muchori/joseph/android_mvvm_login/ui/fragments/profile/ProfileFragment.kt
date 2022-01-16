package me.muchori.joseph.android_mvvm_login.ui.fragments.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import me.muchori.joseph.android_mvvm_login.R
import me.muchori.joseph.android_mvvm_login.data.network.api.UserApi
import me.muchori.joseph.android_mvvm_login.data.network.retrofit.RetrofitInstance
import me.muchori.joseph.android_mvvm_login.data.repository.userRepository.AuthRepository
import me.muchori.joseph.android_mvvm_login.data.repository.userRepository.ProtoDataStoreRepository
import me.muchori.joseph.android_mvvm_login.databinding.FragmentProfileBinding
import me.muchori.joseph.android_mvvm_login.ui.MainActivity
import me.muchori.joseph.android_mvvm_login.viewmodels.auth.AuthViewModel
import me.muchori.joseph.android_mvvm_login.viewmodels.viemmodelfactory.ViewModelFactory

class ProfileFragment : Fragment() {

    private lateinit var navController: NavController
    private var binding: FragmentProfileBinding? = null
    private lateinit var dataRepository: ProtoDataStoreRepository
    private lateinit var authViewModel: AuthViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    private val retrofitInstance = RetrofitInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        requireActivity().title = "Profile"

        dataRepository = ProtoDataStoreRepository(requireContext())

        viewModelFactory =
            ViewModelFactory(
                AuthRepository(
                    retrofitInstance.buildApi(UserApi::class.java),
                    dataRepository
                )
            )
        authViewModel =
            ViewModelProvider(requireActivity(), viewModelFactory)[AuthViewModel::class.java]

        dataRepository = ProtoDataStoreRepository(requireContext())
        val userData = runBlocking { dataRepository.readProto.first() }
        binding!!.textViewUserName.text = userData.firstName + " " + userData.lastName

        binding!!.personalDataLayout.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_personalDataFragment)
        }
        binding!!.logout.setOnClickListener {
            lifecycleScope.launch {
                val token = runBlocking { dataRepository.readProto.first() }
                val accessToken = token.accessToken
                val userApi = retrofitInstance.buildApi(UserApi::class.java, accessToken)
                authViewModel.logout(userApi)
                dataRepository.clear()
                startActivity(Intent(requireActivity(), MainActivity::class.java))
                Toast.makeText(requireContext(), "Thank you for choosing us.", Toast.LENGTH_SHORT)
                    .show()
                requireActivity().finish()
            }
        }

        return binding!!.root
    }
}