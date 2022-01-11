package me.muchori.joseph.android_mvvm_login.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.muchori.joseph.android_mvvm_login.databinding.FragmentDocumentsBinding

class DocumentsFragment : Fragment() {

    private var _binding: FragmentDocumentsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDocumentsBinding.inflate(inflater, container, false)
        requireActivity().title = "Documents"

        return binding.root

    }
}