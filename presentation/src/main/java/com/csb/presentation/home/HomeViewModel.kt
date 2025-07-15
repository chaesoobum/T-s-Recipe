package com.csb.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csb.domain.model.HomeRecipeItem
import com.csb.domain.repositories.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private var homeRepository: HomeRepository
) : ViewModel() {

    //홈 스크린 구성을 위한 레시피 아이템
    private val _homeScreenData = mutableStateListOf<HomeRecipeItem>()
    val homeScreenData: List<HomeRecipeItem> get() = _homeScreenData

    //화면구성전 더미데이터를 보여주는 시간
    private var _isHomeScreenShow = mutableStateOf(false)
    val isHomeScreenShow: State<Boolean> = _isHomeScreenShow

    fun fetchHomeScreen() {
        viewModelScope.launch {
            if (!_isHomeScreenShow.value) {
                val result = homeRepository.fetchHomeScreen()
                _homeScreenData.addAll(result)
                _isHomeScreenShow.value = true
            }
        }
    }
}