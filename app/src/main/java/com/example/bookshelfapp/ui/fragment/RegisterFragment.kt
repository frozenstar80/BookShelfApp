package com.example.bookshelfapp.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bookshelfapp.databinding.FragmentRegisterBinding

class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRegisterBinding =
        FragmentRegisterBinding::inflate

    override fun setup() {

    }
}