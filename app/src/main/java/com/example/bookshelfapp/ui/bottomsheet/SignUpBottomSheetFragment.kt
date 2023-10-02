package com.example.bookshelfapp.ui.bottomsheet

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.bookshelfapp.data.local.UserCredentials
import com.example.bookshelfapp.databinding.BottomSheetFragmentSignupBinding
import com.example.bookshelfapp.ui.activity.HomeActivity
import com.example.bookshelfapp.ui.viewModel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignUpBottomSheetFragment : BaseBottomSheetFragment<BottomSheetFragmentSignupBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> BottomSheetFragmentSignupBinding =
        BottomSheetFragmentSignupBinding::inflate

    private val signUpViewModel by viewModels<SignUpViewModel>()

    override fun setup() {


        binding?.btnRegister?.setOnClickListener {
            if (validation()) {
                signUpViewModel.insertUser(
                    UserCredentials(
                        userName = binding?.edtEmail?.text?.trim().toString(),
                        password = binding?.edtPassword?.text.toString(),
                        country = ""
                    )
                )
            }
        }
        signUpViewModel.signUpResult.observe(viewLifecycleOwner){
            if (it){
                registrationSuccessful()
            }else{
                toast("Some Error Occurred. Please Try Again")
            }
        }

    }

    private fun registrationSuccessful() {
        savedPrefManager.putLogin(true)
        startActivity(Intent(requireContext(), HomeActivity::class.java))

    }

    private fun validation(): Boolean {
        if (binding?.edtEmail?.text?.trim().toString().isEmpty()) {
            toast("Entered Name is Empty")
            return false
        }
        if (binding?.edtPassword?.text?.toString()?.isEmpty() == true) {
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
