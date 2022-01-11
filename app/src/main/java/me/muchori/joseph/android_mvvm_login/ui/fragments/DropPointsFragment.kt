package me.muchori.joseph.android_mvvm_login.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.muchori.joseph.android_mvvm_login.databinding.FragmentDropPointsBinding

class DropPointsFragment : Fragment() {

    private var _binding: FragmentDropPointsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDropPointsBinding.inflate(inflater, container, false)
        requireActivity().title = "Drop Points"

        return binding.root
    }

}