package me.muchori.joseph.android_mvvm_login.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import me.muchori.joseph.android_mvvm_login.R
import me.muchori.joseph.android_mvvm_login.databinding.FragmentLoginBinding
import me.muchori.joseph.android_mvvm_login.util.CustomeProgressDialog
import me.muchori.joseph.android_mvvm_login.viewmodels.LoginViewModel


class LoginFragment : Fragment() {
    private var binding: FragmentLoginBinding? = null
    var viewmodel: LoginViewModel? = null
    var customeProgressDialog: CustomeProgressDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        binding?.viewmodel = viewmodel
        customeProgressDialog = CustomeProgressDialog(requireContext())
        initObservables()

        return binding?.root
    }

    private fun initObservables() {
        viewmodel?.progressDialog?.observe(this, Observer {
            if (it!!) customeProgressDialog?.show() else customeProgressDialog?.dismiss()
        })
        viewmodel?.userLogin?.observe(viewLifecycleOwner, Observer { user ->
            Toast.makeText(requireContext(), "Welcome, " + user.data.user.firstName + user.data.payload.accessToken, Toast.LENGTH_LONG).show()
        })
    }
}