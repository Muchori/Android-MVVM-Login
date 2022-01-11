package me.muchori.joseph.android_mvvm_login.ui.fragments.auth.otp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.muchori.joseph.android_mvvm_login.databinding.FragmentOTPBinding
import me.muchori.joseph.android_mvvm_login.ui.fragments.home.HomeActivity

class OTPFragment : Fragment() {

    private var _binding: FragmentOTPBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOTPBinding.inflate(inflater, container, false)

        binding.verifyOtp.setOnClickListener {
            startActivity(Intent(requireActivity(), HomeActivity::class.java))
        }

        return binding.root
    }

}