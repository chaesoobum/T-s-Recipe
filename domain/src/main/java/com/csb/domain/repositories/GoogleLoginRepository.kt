package com.csb.domain.repositories

interface GoogleLoginRepository {
    // 로그인 인텐트 요청
    fun getSignInIntent(): Any

    // 로그아웃후 로그인(임시)
    fun signOutAndLogin(): Any

}