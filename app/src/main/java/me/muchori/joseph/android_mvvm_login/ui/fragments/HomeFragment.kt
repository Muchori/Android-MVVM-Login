package me.muchori.joseph.android_mvvm_login.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import me.muchori.joseph.android_mvvm_login.repository.UserRepository
import me.muchori.joseph.android_mvvm_login.databinding.FragmentHomeBinding
import me.muchori.joseph.android_mvvm_login.repository.DataStoreRepository
import me.muchori.joseph.android_mvvm_login.util.CustomeProgressDialog
import me.muchori.joseph.android_mvvm_login.viewmodels.profile.HomeViewModel
import me.muchori.joseph.android_mvvm_login.viewmodels.viemmodelfactory.ViewModelFactory

class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    var viewmodel: HomeViewModel? = null
    private lateinit var viewModelFactory: ViewModelFactory
    var customeProgressDialog: CustomeProgressDialog? = null
    private val repository =  UserRepository()

    private lateinit var dataStoreRepository: DataStoreRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModelFactory = ViewModelFactory(repository)
        viewmodel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
        binding?.viewmodel = viewmodel
        customeProgressDialog = CustomeProgressDialog(requireContext())

        dataStoreRepository = DataStoreRepository(requireContext())
        val userId = runBlocking { dataStoreRepository.readIdFromDataStore.first() }
        viewmodel!!.getUser(userId)

        initUser()

        return binding?.root
    }


    @SuppressLint("SetTextI18n")
    private fun initUser() {
        viewmodel?.userResponse?.observe(viewLifecycleOwner, Observer { user ->
            if (user.isSuccessful){
                val token = runBlocking { dataStoreRepository.refreshToken.first() }
                Log.d("Response", user.body()?.data!!.user.id)
                userEmail.text = user.body()?.data!!.user.email
                userName.text = user.body()?.data!!.user.firstName +" "+ user.body()?.data!!.user.lastName
                userNumber.text = user.body()?.data!!.user.phone
//                userRefreshToken.text = token
                Toast.makeText(requireContext(), "Welcome, " + user.body()?.data!!.user.email + token, Toast.LENGTH_LONG).show()
            } else{
                Log.d("ResponseBody", user.errorBody().toString())
            }
        })
    }
}