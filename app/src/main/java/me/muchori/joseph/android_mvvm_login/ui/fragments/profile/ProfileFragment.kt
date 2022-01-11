package me.muchori.joseph.android_mvvm_login.ui.fragments.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import me.muchori.joseph.android_mvvm_login.R
import me.muchori.joseph.android_mvvm_login.databinding.FragmentProfileBinding
import me.muchori.joseph.android_mvvm_login.repository.userRepository.ProtoDataStoreRepository

class ProfileFragment : Fragment() {

    private var binding: FragmentProfileBinding? = null
    private lateinit var dataRepository: ProtoDataStoreRepository


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        requireActivity().title = "Profile"

        dataRepository = ProtoDataStoreRepository(requireContext())
        val userData = runBlocking { dataRepository.readProto.first() }
        binding!!.textViewUserName.text = userData.firstName + " " + userData.lastName

        binding!!.personalDataLayout.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_personalDataFragment)
        }


        return binding!!.root
    }
}