package com.csb.presentation.start

import android.app.Activity
import android.content.Intent
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csb.domain.repositories.GoogleLoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartViewModel@Inject constructor(
    private val googleLoginRepository: GoogleLoginRepository
): ViewModel() {

    //구글 로그인 인텐드
    private val _googleLoginIntent = mutableStateOf<Intent?>(null)
    val googleLoginIntent: State<Intent?> = _googleLoginIntent

    fun onGoogleLoginClicked() {
        viewModelScope.launch {
            //_googleLoginIntent.value = googleLoginService.getGoogleClient(activity).signInIntent
            _googleLoginIntent.value = googleLoginRepository.signOutAndLogin()as Intent
        }
    }

}