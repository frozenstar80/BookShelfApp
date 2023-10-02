package com.example.bookshelfapp.ui.bottomsheet

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.bookshelfapp.databinding.BottomSheetFragmentLoginBinding
import com.example.bookshelfapp.ui.activity.HomeActivity
import com.example.bookshelfapp.ui.viewModel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginBottomSheetFragment : BaseBottomSheetFragment<BottomSheetFragmentLoginBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> BottomSheetFragmentLoginBinding =
        BottomSheetFragmentLoginBinding::inflate

    private val loginViewModel by viewModels<LoginViewModel>()

    override fun setup() {

        binding?.btnLoginUser?.setOnClickListener {
            if (validation()) {
                val userName = binding?.edtEmail?.text?.trim().toString()
                val password = binding?.edtPassword?.text.toString()
                loginViewModel.checkCredentials(userName, password)
            }
        }

        loginViewModel.loginResult.observe(this, Observer { loginSuccess ->
            if (loginSuccess) {
                toast("Login Successful")
                startActivity(Intent(requireContext(), HomeActivity::class.java))
            } else {
                toast("Login Failed. Check Name and Password")
            }
        })

    }

    private fun validation(): Boolean {
        if (binding?.edtEmail?.text?.trim().toString().isEmpty()){
            toast("Entered Name is Empty")
            return false
        }
        if (binding?.edtPassword?.text?.toString()?.isEmpty()==true){
            toast("Entered Password is Empty")
            return false
        }
        val passwordPattern = "^(?=.*[0-9])(?=.*[!@#\$%&()])(?=.*[a-z])(?=.*[A-Z]).{8,}$".toRegex()
        if (!binding?.edtPassword?.text?.toString()?.let { passwordPattern.matches(it) }!!) {
            toast("Password must have at least 8 characters with one number, special characters, one lowercase letter, and one uppercase letter.")
            return false
        }
        return true
    }

}