package com.example.bookshelfapp.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.bookshelfapp.R
import com.example.bookshelfapp.databinding.FragmentSplashScreenBinding
import com.example.bookshelfapp.ui.activity.HomeActivity
import com.example.bookshelfapp.util.findNavControllerSafely

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : BaseFragment<FragmentSplashScreenBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSplashScreenBinding =
        FragmentSplashScreenBinding::inflate

    override fun setup() {

        val zoomInAnimation: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.zoom_in)
        binding?.appCompatImageView2?.startAnimation(zoomInAnimation)

        if (savedPrefManager.isLogin() == true) {
            Handler().postDelayed({
                startActivity(Intent(requireContext(),HomeActivity::class.java))
            }, SPLASH_SCREEN_TIME_OUT)
        }else{
            Handler().postDelayed({
                findNavControllerSafely()?.navigate(SplashScreenFragmentDirections.actionSplashScreenFragmentToLaunchScreenFragment())
            }, SPLASH_SCREEN_TIME_OUT)
        }

    }

    companion object{
        const val SPLASH_SCREEN_TIME_OUT: Long = 3000
    }
}