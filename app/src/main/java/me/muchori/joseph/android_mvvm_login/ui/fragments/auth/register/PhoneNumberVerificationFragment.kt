package me.muchori.joseph.android_mvvm_login.ui.fragments.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import me.muchori.joseph.android_mvvm_login.R
import me.muchori.joseph.android_mvvm_login.databinding.FragmentPhoneNumberVerificationBinding

class PhoneNumberVerificationFragment : Fragment() {

    private lateinit var binding: FragmentPhoneNumberVerificationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhoneNumberVerificationBinding.inflate(inflater, container, false)

        binding.sendOtp.setOnClickListener {
            findNavController().navigate(R.id.action_phoneNumberVerificationFragment_to_OTPFragment)
        }

        return binding.root
    }
}