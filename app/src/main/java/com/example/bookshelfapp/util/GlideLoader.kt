package com.example.bookshelfapp.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.io.IOException


/**
 * A custom object to create a common functions for Glide which can be used in whole application.
 */
class GlideLoader(private val context: Context) {

    /**
     * A function to load image from Uri or URL for the user profile picture.
     */

    fun loadPic(image: Any?, imageView: ImageView?) {
        try {
            // Load the user image in the ImageView.
            if (imageView != null) {
                Glide
                    .with(context)
                    .load(image) // Uri or URL of the image.circleCrop() // Scale type of the image.
                    .into(imageView)
            } // the view in which the image will be loaded.
        } catch (e: IOException) {
         e.printStackTrace()
        }
    }
}