package com.example.bookshelfapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookshelfapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}