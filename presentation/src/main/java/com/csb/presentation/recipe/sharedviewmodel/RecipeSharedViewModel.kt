package com.csb.presentation.recipe.sharedviewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

@HiltViewModel
class RecipeSharedViewModel @Inject constructor() : ViewModel() {

    private val _recipe = mutableStateOf(RecipeSharedDataClass())
    val recipe: State<RecipeSharedDataClass> get() = _recipe

    fun setRecipeData(
        imageUrl: String,
        title: String,
        serving: String,
        price: String,
        time: String,
        name: String
    ) {
        _recipe.value = RecipeSharedDataClass(imageUrl, title, serving, price, time, name)
    }
}