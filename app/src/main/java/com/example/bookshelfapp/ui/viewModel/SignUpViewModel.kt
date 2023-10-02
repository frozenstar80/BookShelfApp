package com.example.bookshelfapp.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshelfapp.data.local.BookEntity
import com.example.bookshelfapp.data.local.UserCredentials
import com.example.bookshelfapp.domain.repository.DataRepository
import com.example.bookshelfapp.domain.repository.RemoteDataRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val dataRepository: RemoteDataRepositoryImpl
) : ViewModel() {
    private val _signUpResult = MutableLiveData<Boolean>()
    val signUpResult: LiveData<Boolean> get() = _signUpResult

    fun insertUser(userCredentials: UserCredentials) {
        viewModelScope.launch {
           val registered =  dataRepository.insertUser(userCredentials)
            _signUpResult.postValue(registered)
            //return true when user is added else false
        }
    }

}