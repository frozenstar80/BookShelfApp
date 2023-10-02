package com.example.bookshelfapp.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.bookshelfapp.R
import com.example.bookshelfapp.databinding.ActivitySplashBinding
import com.example.bookshelfapp.util.SavedPrefManager

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

//    Opening Activity Of The Application.

    private lateinit var binding: ActivitySplashBinding
     private lateinit var savedPrefManager: SavedPrefManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_splash)
        savedPrefManager =  SavedPrefManager(this)

        val zoomInAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.zoom_in)
        binding.appCompatImageView2.startAnimation(zoomInAnimation)

        //If User Has Already Logged In Go to Home Activity .
        // If not logged in go to authentication page

        if (savedPrefManager.isLogin() == true) {
            Handler().postDelayed({
                startActivity(Intent(this,HomeActivity::class.java))
                finish()
            }, SPLASH_SCREEN_TIME_OUT)
        }else{
            Handler().postDelayed({
                startActivity(Intent(this,AuthenticationActivity::class.java))
                finish()
            },SPLASH_SCREEN_TIME_OUT)
        }
    }

    companion object{
        //time out to open another activity
        const val SPLASH_SCREEN_TIME_OUT: Long = 3000
    }
}