package com.csb.data.repositories

import android.content.Context
import android.content.Intent
import com.csb.domain.repositories.GoogleLoginRepository
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.csb.data.BuildConfig
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class GoogleLoginRepositoryImpl@Inject constructor(
    @ApplicationContext private val context: Context
) : GoogleLoginRepository {

    //구글 로그인
    override fun getSignInIntent(): GoogleSignInClient {
        BuildConfig.GOOGLE_CLIENT_ID

        val googleSignInOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.GOOGLE_CLIENT_ID) // ID 토큰 요청
            .requestEmail() // 이메일 요청
            //.requestProfile() //프로필 요청
            .build()

        return GoogleSignIn.getClient(context, googleSignInOption)
    }

    //임시적으로 매번 로그아웃되게함
    override fun signOutAndLogin(): Intent {
        val client = getSignInIntent()
        client.signOut() // ← 자동 로그인 방지: 이전 계정 정보 제거 용도임

        return client.signInIntent
    }

}