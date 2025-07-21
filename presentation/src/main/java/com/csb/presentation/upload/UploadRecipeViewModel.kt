package com.csb.presentation.upload

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csb.domain.repositories.UploadRecipeRepository
import com.csb.presentation.upload.state.IngredientInputBoxState
import com.csb.presentation.upload.state.ProcedureInputBoxState
import com.csb.presentation.upload.state.RecipeFormState
import com.csb.presentation.upload.stepBox.StepState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UploadRecipeViewModel
@Inject constructor(
    private var uploadRecipeRepository: UploadRecipeRepository
) : ViewModel() {
    //기본 입력폼
    private var _formState  = mutableStateOf(RecipeFormState())
    var formState: State<RecipeFormState> = _formState

    //입력폼 입력 제한 체크박스상태
    private var _portionEnable = mutableStateOf(false)
    var portionEnable: State<Boolean> = _portionEnable
    fun setPortionEnable(value: Boolean){
        _portionEnable.value = value
    }
    private var _priceEnable = mutableStateOf(false)
    var priceEnable: State<Boolean> = _priceEnable
    fun setPriceEnable(value: Boolean){
        _priceEnable.value = value
    }
    private var _timeEnable = mutableStateOf(false)
    var timeEnable: State<Boolean> = _timeEnable
    fun setTimeEnable(value: Boolean){
        _timeEnable.value = value
    }

    // 대표 이미지 URI
    private val _mainImageUri = mutableStateOf(Uri.EMPTY)
    val mainImageUri: State<Uri> get() = _mainImageUri
    fun setMainImageUri(uri: Uri? = Uri.EMPTY) {
        _mainImageUri.value = uri
    }

    // 바텀시트 상태
    private val _showBottomSheet = mutableStateOf(false)
    val showBottomSheet: State<Boolean> get() = _showBottomSheet
    fun setShowBottomSheetVisible(isShowing: Boolean) {
        _showBottomSheet.value = isShowing
    }

    // 사진 삭제 다이얼로그
    private val _deletePictureDialog = mutableStateOf(false)
    val deletePictureDialog: State<Boolean> get() = _deletePictureDialog
    fun setDeletePictureDialog(visible: Boolean) {
        _deletePictureDialog.value = visible
    }


    // 재료 초과 다이얼로그
    private val _ingredientDialog = mutableStateOf(false)
    val ingredientDialog: State<Boolean> get() = _ingredientDialog
    fun setIngredientDialog(visible: Boolean) {
        _ingredientDialog.value = visible
    }


    //재료추가
    fun addIngredientBox() {
        if (_formState.value.ingredientList.size < 50) {
            _formState.value.ingredientList.add(IngredientInputBoxState())
        } else {
            setIngredientDialog(true)
        }
    }


    //재료이동
    fun moveIngredient(from: Int, to: Int) {
        _formState.value.ingredientList.apply {
            add(to, removeAt(from))
        }
    }


    //재료제거
    fun removeIngredient(item: IngredientInputBoxState) {
        _formState.value.ingredientList.remove(item)
    }


    // 소스 초과 다이얼로그
    private val _sourceDialog = mutableStateOf(false)
    val sourceDialog: State<Boolean> get() = _sourceDialog
    fun setSourceDialog(visible: Boolean) {
        _sourceDialog.value = visible
    }

    //소스 추가
    fun addSourceBox() {
        if (_formState.value.sourceList.size < 50) {
            _formState.value.sourceList.add(IngredientInputBoxState())
        } else {
            setSourceDialog(true)
        }
    }

    //소스 이동
    fun moveSource(from: Int, to: Int) {
        _formState.value.sourceList.apply {
            add(to, removeAt(from))
        }
    }

    //소스 제거
    fun removeSource(item: IngredientInputBoxState) {
        _formState.value.sourceList.remove(item)
    }


    //단계추가
    fun addStep() {
        val newStep = StepState(
            stepIndex = _formState.value.stepList.size,
            procedureList = mutableStateListOf(ProcedureInputBoxState())
        )
        _formState.value.stepList.add(newStep)
    }

    //단계제거
    fun removeStep(item: StepState) {
        _formState.value.stepList.remove(item)
    }

    //과정추가
    fun addProcedure(stepIndex: Int) {
        if (stepIndex in _formState.value.stepList.indices) {
            _formState.value.stepList[stepIndex].procedureList.add(ProcedureInputBoxState())
        }
    }

    //과정제거
    fun removeProcedure(step: StepState, procedure: ProcedureInputBoxState) {
        step.procedureList.remove(procedure)
    }

    //과정이동
    fun moveProcedure(stepState:StepState,from: Int = 0, to: Int = 0) {
        _formState.value.stepList.find { it == stepState }!!.procedureList.apply {
            add(to, removeAt(from))
        }
    }

    //업로드 프로그래스바
    private var _isProgressBarShow = mutableStateOf(false)
    val isProgressBarShow: State<Boolean> get()= _isProgressBarShow

    //업로드 프로세스 종료
    private var _isUploadProcessingDone = mutableStateOf(false)
    val isUploadProcessingDone: State<Boolean> get()= _isUploadProcessingDone

    //업로드
    fun uploadProcessing(){
        viewModelScope.launch {
            _isProgressBarShow.value = true
            uploadRecipeRepository.uploadRecipe()
            _isUploadProcessingDone.value = true
        }
    }
}
