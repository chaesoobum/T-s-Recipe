package com.csb.presentation.recipe

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csb.domain.model.RecipeScreen
import com.csb.domain.repositories.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel@Inject constructor(
    private val recipeRepository: RecipeRepository
): ViewModel() {

    //레시피 화면 구성을 위한
    private val _homeScreenData = mutableStateOf<RecipeScreen?>(null)
    val homeScreenData: State<RecipeScreen?> get() = _homeScreenData

    //화면구성전 더미데이터를 보여주는 시간
    private var _isHomeScreenShow = mutableStateOf(false)
    val isHomeScreenShow: State<Boolean> = _isHomeScreenShow


    fun fetchRecipeScreen(){
        viewModelScope.launch {
            if (!_isHomeScreenShow.value) {
                val result = recipeRepository.fetchRecipeScreen()
                _homeScreenData.value = result
                _isHomeScreenShow.value = true
            }
        }
    }

}