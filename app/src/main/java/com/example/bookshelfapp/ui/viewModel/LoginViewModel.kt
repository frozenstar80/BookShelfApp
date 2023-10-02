package com.example.bookshelfapp.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshelfapp.domain.repository.RemoteDataRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dataRepository: RemoteDataRepositoryImpl
) : ViewModel() {
    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> get() = _loginResult

    fun checkCredentials(userName: String, password: String) {
        viewModelScope.launch {
            dataRepository.checkCredential(userName, password)?.collectLatest {
                _loginResult.value = it.isNotEmpty()
            }
        }

    }
}