package me.muchori.joseph.android_mvvm_login.ui.fragments.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import me.muchori.joseph.android_mvvm_login.data.repository.userRepository.ProtoDataStoreRepository
import me.muchori.joseph.android_mvvm_login.databinding.FragmentPersonalDataBinding

class PersonalDataFragment : Fragment() {
    private var binding: FragmentPersonalDataBinding? = null
    private lateinit var dataRepository: ProtoDataStoreRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPersonalDataBinding.inflate(inflater, container, false)
        requireActivity().title = "Personal Data"

        dataRepository = ProtoDataStoreRepository(requireContext())
        val userData = runBlocking { dataRepository.readProto.first() }

        binding!!.editTextUserName.setText(userData.firstName + " " + userData.lastName)
        binding!!.editTextEmail.setText(userData.email)
        binding!!.editTextPhone.setText(userData.phone)
        binding!!.editTextGender.setText(userData.gender)
        binding!!.editTextUserType.setText(userData.userType)

        return binding!!.root
    }
}