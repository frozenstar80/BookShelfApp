package com.example.bookshelfapp.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bookshelfapp.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding =
        FragmentLoginBinding::inflate

    override fun setup() {

    }
}