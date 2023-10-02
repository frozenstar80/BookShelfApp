package com.example.bookshelfapp.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshelfapp.data.local.BookEntity
import com.example.bookshelfapp.data.remote.Book
import com.example.bookshelfapp.domain.api.NetworkResult
import com.example.bookshelfapp.domain.repository.DataRepository
import com.example.bookshelfapp.domain.repository.RemoteDataRepositoryImpl
import com.example.bookshelfapp.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BookListViewModel @Inject constructor(private val repository: DataRepository, private val remoteDataRepositoryImpl: RemoteDataRepositoryImpl)  : ViewModel(){

    private val _booksListRemoteLiveData = MutableLiveData<Event<NetworkResult<List<Book>>>>()
    val booksListRemote get() = _booksListRemoteLiveData

    private val _booksListLocalLiveData = MutableLiveData< Event<List<BookEntity>>>()
    val booksListLocal get() = _booksListLocalLiveData

    private fun getBooksListFromNetwork(){
        viewModelScope.launch {
            repository.getBooksList().collectLatest {
                _booksListRemoteLiveData.postValue(Event(it))
            }
        }
    }
     fun getBooksListFromLocal() =
        viewModelScope.launch {
            remoteDataRepositoryImpl.getAllBooks().collectLatest {
                if (it.isEmpty())
                    getBooksListFromNetwork()
                else
                    _booksListLocalLiveData.postValue(Event(it))
            }
        }

    fun updateBookData(bookEntity: BookEntity) {
        viewModelScope.launch {
           remoteDataRepositoryImpl.updateBookData(bookEntity)
        }
    }
    fun insertBookData(bookEntity: List<BookEntity>) {
        viewModelScope.launch {
            for (entry in bookEntity) {
                remoteDataRepositoryImpl.insertBookData(entry)
            }
        }
    }
}