package com.csb.presentation.util

import com.csb.presentation.R

enum class RootScreen{
    //메인화면
    SCREEN_MAIN,
    //레시피 화면
    SCREEN_RECIPE,
    //레시피 업로드 화면
    SCREEN_UPLOAD_RECIPE,
    //시작화면
    SCREEN_START,
    //로그인 화면
    SCREEN_LOGIN,
    //회원가입화면
    SCREEN_SIGNUP
}

sealed class MainScreen(val root: String, val label: String, val icon: Int) {
    object Home : MainScreen("home", "홈", R.drawable.ic_home)
    object Search : MainScreen("Search","검색",R.drawable.ic_search)
    object UploadRecipe : MainScreen("uploadRecipe", "레시피 업로드", R.drawable.ic_addrecipe)
    object MyPage : MainScreen("myPage", "마이 페이지", R.drawable.ic_mypage)
}
enum class UnitType(val label: String) {
    TBSP("Tbsp"),
    CUP("Cup"),
    G("g"),
    ML("ml"),
    OZ("oz"),
    QTY("개수")
}


