package com.example.bookshelfapp.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bookshelfapp.databinding.FragmentBooksDetailBinding

class BookDetailScreenFragment : BaseFragment<FragmentBooksDetailBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentBooksDetailBinding =
        FragmentBooksDetailBinding::inflate

    override fun setup() {

    }
}