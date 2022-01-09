package me.muchori.joseph.android_mvvm_login.ui.fragments.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import me.muchori.joseph.android_mvvm_login.R
import me.muchori.joseph.android_mvvm_login.databinding.FragmentPromptBinding

class PromptFragment : Fragment() {
    private lateinit var binding: FragmentPromptBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPromptBinding.inflate(inflater, container, false)

        binding.login.setOnClickListener {
            findNavController().navigate(R.id.action_promptFragment_to_loginFragment)
//            requireActivity().finish()
        }
        binding.register.setOnClickListener {
            findNavController().navigate(R.id.action_promptFragment_to_registerFragment)
//            requireActivity().finish()
        }
        return binding.root
    }
}