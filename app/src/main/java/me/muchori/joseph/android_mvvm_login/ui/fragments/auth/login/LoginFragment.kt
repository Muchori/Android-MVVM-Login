package me.muchori.joseph.android_mvvm_login.ui.fragments.auth.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import me.muchori.joseph.android_mvvm_login.R
import me.muchori.joseph.android_mvvm_login.databinding.FragmentLoginBinding
import me.muchori.joseph.android_mvvm_login.ui.fragments.home.HomeActivity
import me.muchori.joseph.android_mvvm_login.util.CustomProgressDialog
import me.muchori.joseph.android_mvvm_login.viewmodels.auth.LoginViewModel


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    var viewmodel: LoginViewModel? = null
    var customProgressDialog: CustomProgressDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        binding.viewmodel = viewmodel
        customProgressDialog = CustomProgressDialog(requireContext())
        getUserData()

        binding.registerLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        return binding.root
    }

    private fun getUserData() {
        viewmodel!!.progressDialog?.observe(this, {
            if (it!!) customProgressDialog?.show() else customProgressDialog?.dismiss()
        })
        viewmodel!!.userLogin?.observe(viewLifecycleOwner, { user ->
            startActivity(Intent(requireActivity(), HomeActivity::class.java))
            viewmodel!!.updateUserDetails(user)
            viewmodel!!.saveToDataStore(user.data.user.id)
            viewmodel!!.saveRefreshToken(user.data.payload.refreshToken)
            requireActivity().finish()
//            Toast.makeText(requireContext(), "Welcome, " + user.data.user.firstName + user.data.payload.accessToken, Toast.LENGTH_LONG).show()
        })
    }
}