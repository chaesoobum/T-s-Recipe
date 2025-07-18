package com.csb.presentation.recipe

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csb.domain.model.ProcessedRecipeScreen
import com.csb.domain.model.RecipeScreen
import com.csb.domain.repositories.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    //레시피 화면 구성을 위한 데이터
    private val _recipeScreenData = mutableStateOf<RecipeScreen?>(null)
    val recipeScreenData: State<RecipeScreen?> get() = _recipeScreenData

    //화면구성전 더미데이터를 보여주는 시간
    private var _isRecipeScreenShow = mutableStateOf(false)
    val isRecipeScreenShow: State<Boolean> = _isRecipeScreenShow

    //화면구성
    fun fetchRecipeScreen() {
        viewModelScope.launch {
            if (!_isRecipeScreenShow.value) {
                val result = recipeRepository.fetchRecipeScreen()
                _recipeScreenData.value = result
                _isRecipeScreenShow.value = true
            }
        }
    }

}