package com.example.bookshelfapp.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.bookshelfapp.R
import com.example.bookshelfapp.util.SavedPrefManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.signout, menu)
        //inflate menu file
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_sign_out -> {
                //sign out the user and set isLogin to false
                SavedPrefManager(this).putLogin(false)
                // navigate to Authentication Activity and destroy current activity
                startActivity(Intent(this,AuthenticationActivity::class.java))
                finish()

                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}