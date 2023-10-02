package com.example.bookshelfapp.ui.fragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.bookshelfapp.R
import com.example.bookshelfapp.databinding.FragmentLaunchScreenBinding
import java.util.*


class LaunchScreenFragment : BaseFragment<FragmentLaunchScreenBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLaunchScreenBinding =
        FragmentLaunchScreenBinding::inflate


    override fun setup() {

        val window = requireActivity().window

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.blue)

        binding?.btnLogin?.setOnClickListener {
            findNavController().navigate(LaunchScreenFragmentDirections.actionLaunchScreenFragmentToLoginBottomSheetFragment())
        }
        binding?.btnRegister?.setOnClickListener {
            findNavController().navigate(LaunchScreenFragmentDirections.actionLaunchScreenFragmentToSignUpBottomSheetFragment())
        }

    }
}