package me.muchori.joseph.android_mvvm_login.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import me.muchori.joseph.android_mvvm_login.databinding.FragmentHomeBinding
import me.muchori.joseph.android_mvvm_login.repository.userRepository.DataStoreRepository
import me.muchori.joseph.android_mvvm_login.repository.userRepository.ProtoDataStoreRepository
import me.muchori.joseph.android_mvvm_login.repository.userRepository.UserRepository
import me.muchori.joseph.android_mvvm_login.viewmodels.profile.HomeViewModel
import me.muchori.joseph.android_mvvm_login.viewmodels.profile.ProfileViewModel
import me.muchori.joseph.android_mvvm_login.viewmodels.viemmodelfactory.ViewModelFactory

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var dataStoreRepository: DataStoreRepository
    private lateinit var dataRepository: ProtoDataStoreRepository
    private val repository = UserRepository()

    private var viewmodel: HomeViewModel? = null
    private var profileViewModel: ProfileViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        requireActivity().title = "Home"
        viewModelFactory = ViewModelFactory(repository)
        viewmodel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
        profileViewModel = ViewModelProvider(this, viewModelFactory)[ProfileViewModel::class.java]
        binding.viewmodel = viewmodel

        dataStoreRepository = DataStoreRepository(requireContext())
        dataRepository = ProtoDataStoreRepository(requireContext())

        val userId = runBlocking { dataStoreRepository.readIdFromDataStore.first() }
        viewmodel!!.getUser(userId)

        val userData = runBlocking { dataRepository.readProto.first() }
        binding.userNumber.text = userData.phone
        binding.userEmail.text = userData.email
        binding.userRefreshToken.text = userData.id
        binding.userName.text = userData.firstName + " " + userData.lastName

        return binding.root
    }


//    @SuppressLint("SetTextI18n")
//    private fun initUser() {
//        viewmodel?.userResponse?.observe(viewLifecycleOwner, Observer { user ->
//            if (user.isSuccessful){
//                val token = runBlocking { dataStoreRepository.refreshToken.first() }
//                Log.d("Response", user.body()?.data!!.user.id)
//
//                userEmail.text = user.body()?.data!!.user.email
//                userName.text = user.body()?.data!!.user.firstName +" "+ user.body()?.data!!.user.lastName
////                userNumber.text = user.body()?.data!!.user.phone
////                userRefreshToken.text = token
//
//                Toast.makeText(requireContext(), "Welcome, " + user.body()?.data!!.user.email + token, Toast.LENGTH_LONG).show()
//            } else{
//                Log.d("ResponseBody", user.errorBody().toString())
//            }
//        })
////        profileViewModel!!.userDetails.observe(this, {
//////            userRefreshToken.text = it.refreshToken
//////            userNumber.text =it.phone
////            Log.d("ProtoDataStore", it.accessToken)
////        })
//    }
}