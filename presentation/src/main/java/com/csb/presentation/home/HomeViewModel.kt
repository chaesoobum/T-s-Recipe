package com.csb.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel() {
    val temp = mutableStateListOf<List<String>>()
    val tempTemp = mutableStateListOf<List<String>>()

    private var _isHomeScreenShow = mutableStateOf<Boolean>(false)
    var isHomeScreenShow:State<Boolean> = _isHomeScreenShow

    val one = listOf<String>(
        "https://raw.githubusercontent.com/realbeginnerr/tsrecipe/main/assets/img/r07_broccoliseasonedtofu.jpg",
        "브로콜리 두부 무침",
        "3",
        "4,000",
        "15",
        "홍길동")
    val two = listOf<String>(
        "https://raw.githubusercontent.com/realbeginnerr/tsrecipe/main/assets/img/r06_chickenteriyaki.jpg",
        "닭다리살 데리야끼",
        "3",
        "4,000",
        "15",
        "홍길동")
    val three = listOf<String>(
        "https://raw.githubusercontent.com/realbeginnerr/tsrecipe/main/assets/img/r04_jeyukbokkeum.jpg",
        "제육볶음",
        "3",
        "4,000",
        "15",
        "홍길동")

    fun fetchHomeScreen(){
        viewModelScope.launch {
            temp.add(one)
            temp.add(two)
            temp.add(three)
            temp.add(one)
            temp.add(two)
            temp.add(three)
            temp.add(one)
            temp.add(two)
            temp.add(three)
            temp.add(one)
            temp.add(two)
            temp.add(three)
            tempTemp.add(two)
            tempTemp.add(three)
            tempTemp.add(one)
            tempTemp.add(two)
            tempTemp.add(three)
            tempTemp.add(one)
            tempTemp.add(two)
            tempTemp.add(three)

            delay(1000)

            _isHomeScreenShow.value = true
        }
    }

    //화면구성
    init {
        fetchHomeScreen()
    }
}