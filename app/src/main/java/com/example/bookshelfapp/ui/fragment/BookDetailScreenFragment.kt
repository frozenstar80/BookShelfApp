package com.example.bookshelfapp.ui.fragment

import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.example.bookshelfapp.R
import com.example.bookshelfapp.databinding.FragmentBooksDetailBinding
import com.example.bookshelfapp.util.GlideLoader
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class BookDetailScreenFragment : BaseFragment<FragmentBooksDetailBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentBooksDetailBinding =
        FragmentBooksDetailBinding::inflate

    private val navArgs by navArgs<BookDetailScreenFragmentArgs>()

    override fun setup() {
        GlideLoader(binding?.imgBook?.context!!).loadPic(navArgs.booksEntity?.image.toString(),binding?.imgBook)
        binding?.txtBookName?.text = navArgs.booksEntity?.title
        binding?.txtHits?.text = navArgs.booksEntity?.hits.toString()

        if(navArgs.booksEntity?.isFavourite == true){
            binding?.imgFav?.setColorFilter(ContextCompat.getColor(binding?.imgFav?.context!!, R.color.yellow), PorterDuff.Mode.SRC_IN)
        }else{
            binding?.imgFav?.colorFilter = null
        }

        binding?.txtAlias?.text = navArgs.booksEntity?.alias

        binding?.txtUpdated?.text = navArgs.booksEntity?.lastChapterDate?.let {
            convertTimestampToDate(
                it
            )
        }

    }

    private fun convertTimestampToDate(timestamp: Long): String {
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())

        // Convert timestamp to Date
        val date = Date(timestamp)

        // Format the date
        return dateFormat.format(date)
    }
}