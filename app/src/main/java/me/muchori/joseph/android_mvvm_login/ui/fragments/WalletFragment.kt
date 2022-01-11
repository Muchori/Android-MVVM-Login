package me.muchori.joseph.android_mvvm_login.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.muchori.joseph.android_mvvm_login.databinding.FragmentWalletBinding

class WalletFragment : Fragment() {

    private var _binding: FragmentWalletBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWalletBinding.inflate(inflater, container, false)
        requireActivity().title = "Wallet"

        return binding.root
    }

}