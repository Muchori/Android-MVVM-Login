package me.muchori.joseph.android_mvvm_login.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import me.muchori.joseph.android_mvvm_login.data.network.api.UserApi
import me.muchori.joseph.android_mvvm_login.data.network.retrofit.RetrofitInstance
import me.muchori.joseph.android_mvvm_login.data.repository.userRepository.DataStoreRepository
import me.muchori.joseph.android_mvvm_login.data.repository.userRepository.ProtoDataStoreRepository
import me.muchori.joseph.android_mvvm_login.data.repository.userRepository.UserRepository
import me.muchori.joseph.android_mvvm_login.databinding.FragmentHomeBinding
import me.muchori.joseph.android_mvvm_login.viewmodels.profile.HomeViewModel
import me.muchori.joseph.android_mvvm_login.viewmodels.profile.ProfileViewModel
import me.muchori.joseph.android_mvvm_login.viewmodels.viemmodelfactory.HomeViewModelFactory

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModelFactory: HomeViewModelFactory
    private lateinit var dataStoreRepository: DataStoreRepository
    private lateinit var dataRepository: ProtoDataStoreRepository

    private var viewmodel: HomeViewModel? = null
    private var profileViewModel: ProfileViewModel? = null
    private val retrofitInstance = RetrofitInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        requireActivity().title = "Home"

        viewModelFactory =
            HomeViewModelFactory(UserRepository(retrofitInstance.buildApi(UserApi::class.java)))
        viewmodel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
//        profileViewModel = ViewModelProvider(this, viewModelFactory)[ProfileViewModel::class.java]
        binding.viewmodel = viewmodel

//        viewmodel!!.userData.observe(viewLifecycleOwner, {
//            binding.userNumber.text = it.phone
//            binding.userEmail.text = it.email
//            binding.userRefreshToken.text = it.id
//            binding.userName.text = it.firstName + " " + it.lastName
//        })

        return binding.root
    }
}