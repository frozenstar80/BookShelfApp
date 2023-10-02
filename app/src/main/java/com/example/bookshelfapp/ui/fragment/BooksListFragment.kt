package com.example.bookshelfapp.ui.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.bookshelfapp.data.local.BookEntity
import com.example.bookshelfapp.databinding.FragmentBooksListBinding
import com.example.bookshelfapp.domain.api.NetworkResult
import com.example.bookshelfapp.domain.mapper.toBookEntityList
import com.example.bookshelfapp.ui.adapter.BookListAdapter
import com.example.bookshelfapp.ui.viewModel.BookListViewModel
import com.example.bookshelfapp.util.EventObserver
import com.example.bookshelfapp.util.findNavControllerSafely
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BooksListFragment : BaseFragment<FragmentBooksListBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentBooksListBinding =
        FragmentBooksListBinding::inflate

    private val viewModel by viewModels<BookListViewModel>()
    private val TAG = "BooksListFragment"
    private lateinit var adapter: BookListAdapter
    private var booksList: MutableList<BookEntity> = arrayListOf()
    private var isChecked = false
    private var checkedId: Int = 0

    override fun setup() {
        setUpViews()
        setUpObservers()
    }

    private fun setUpViews() {
        viewModel.getBooksListFromLocal()
        adapter = BookListAdapter(data = arrayListOf(),
            onRootItemClicked = {
                findNavControllerSafely()?.navigate(
                    BooksListFragmentDirections.actionBooksListFragmentToBookDetailScreenFragment(
                        it
                    )
                )
            },
            addToFav = {
                viewModel.updateBookData(bookEntity = it.copy(isFavourite = it.isFavourite.not()))
                val index = booksList.indexOf(it)
                booksList[index] = it.copy(isFavourite = it.isFavourite.not())
                setSorting(booksList)
            })
        binding?.rvBookList?.adapter = adapter

        binding?.switchAscendingOrder?.setOnCheckedChangeListener { _, isChecked ->
            this.isChecked = isChecked
            setSorting(booksList)
        }
        binding?.chipGrp?.setOnCheckedChangeListener { _, checkedId ->
            this.checkedId = checkedId
            setSorting(booksList)
        }

    }

    private fun setUpObservers() {
        viewModel.booksListRemote.observe(viewLifecycleOwner, EventObserver { response ->
            when (response) {
                is NetworkResult.Loading -> {
                    showProgressBar()
                }

                is NetworkResult.Error -> {
                    hideProgressBar()
                    toast(response.message.toString())
                    Log.d(TAG, response.message.toString())
                }

                is NetworkResult.Success -> {
                    hideProgressBar()
                    booksList = response.data?.toBookEntityList()?.toMutableList() ?: arrayListOf()
                    viewModel.insertBookData(booksList)
                    setSorting(booksList)
                }
            }
        })
        viewModel.booksListLocal.observe(viewLifecycleOwner, EventObserver {
            booksList = it.toMutableList()
            setSorting(it)
        })
    }

    private fun showProgressBar() {
        binding?.progressBar?.isVisible = true
    }

    private fun hideProgressBar() {
        binding?.progressBar?.isVisible = false
    }

    private fun setDataInAdapter(bookEntityList: List<BookEntity>) {
        adapter.updateData(bookEntityList)
    }

    private fun setSorting(bookEntityList: List<BookEntity>) {
        when (binding?.chipGrp?.findViewById<Chip>(checkedId)) {
            binding?.chipTitle -> {
                showOrderChangeSwitch()
                setDataInAdapter(if (isChecked) bookEntityList.sortedBy { it.title } else bookEntityList.sortedByDescending { it.title })
            }

            binding?.chipFav -> {
                hideOrderChangeSwitch()
                setDataInAdapter(bookEntityList.filter { it.isFavourite })
            }

            binding?.chipHits -> {
                showOrderChangeSwitch()
                setDataInAdapter(if (isChecked) bookEntityList.sortedBy { it.hits } else bookEntityList.sortedByDescending { it.hits })
            }

            else -> {
                setDataInAdapter(bookEntityList)
            }
        }
    }

    private fun showOrderChangeSwitch() {
        binding?.switchAscendingOrder?.isVisible = true
    }

    private fun hideOrderChangeSwitch() {
        binding?.switchAscendingOrder?.isVisible = false
    }
}