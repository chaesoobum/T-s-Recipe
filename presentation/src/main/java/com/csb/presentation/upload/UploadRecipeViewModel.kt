package com.csb.presentation.upload

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csb.domain.repositories.UploadRecipeRepository
import com.csb.presentation.upload.ingredientBox.IngredientInputBoxState
import com.csb.presentation.upload.stepBox.ProcedureInputBoxState
import com.csb.presentation.upload.headerBox.RecipeFormState
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

    fun onTitleChanged(new: String) {
        _formState.value = _formState.value.copy(title = new)
    }
    fun onPortionChanged(new: String) {
        _formState.value = _formState.value.copy(portion = new)
    }
    fun onCostChanged(new: String) {
        _formState.value = _formState.value.copy(cost = new)
    }
    fun onTimeChanged(new: String) {
        _formState.value = _formState.value.copy(time = new)
    }
    fun onMemoChanged(new: String) {
        _formState.value = _formState.value.copy(memo = new)
    }
//기본 입력폼

    // 대표 이미지 URI
    private val _mainImageUri = mutableStateOf(Uri.EMPTY)
    val mainImageUri: State<Uri> get() = _mainImageUri
    fun setMainImageUri(uri: Uri? = Uri.EMPTY) {
        _mainImageUri.value = uri
    }
// 대표 이미지 URI

    // 바텀시트 상태
    private val _showBottomSheet = mutableStateOf(false)
    val showBottomSheet: State<Boolean> get() = _showBottomSheet
    fun setShowBottomSheetVisible(isShowing: Boolean) {
        _showBottomSheet.value = isShowing
    }
// 바텀시트 상태

    // 사진 삭제 다이얼로그
    private val _deletePictureDialog = mutableStateOf(false)
    val deletePictureDialog: State<Boolean> get() = _deletePictureDialog
    fun setDeletePictureDialog(visible: Boolean) {
        _deletePictureDialog.value = visible
    }
// 사진 삭제 다이얼로그


    // 재료 초과 다이얼로그
    private val _ingredientDialog = mutableStateOf(false)
    val ingredientDialog: State<Boolean> get() = _ingredientDialog
    fun setIngredientDialog(visible: Boolean) {
        _ingredientDialog.value = visible
    }
// 재료 초과 다이얼로그

    // 재료 테이블
    private val _ingredientTable = mutableStateListOf(IngredientInputBoxState())
    val ingredientTable: List<IngredientInputBoxState> get() = _ingredientTable
// 재료 테이블


    //재료추가
    fun addIngredientBox() {
        if (_ingredientTable.size < 50) {
            _ingredientTable.add(IngredientInputBoxState())
        } else {
            setIngredientDialog(true)
        }
    }
//재료추가


    //재료이동
    fun moveIngredient(from: Int, to: Int) {
        _ingredientTable.apply {
            add(to, removeAt(from))
        }
    }
//재료이동


    //재료제거
    fun removeIngredient(item: IngredientInputBoxState) {
        _ingredientTable.remove(item)
    }
//재료제거


    // 소스 초과 다이얼로그
    private val _sourceDialog = mutableStateOf(false)
    val sourceDialog: State<Boolean> get() = _sourceDialog
    fun setSourceDialog(visible: Boolean) {
        _sourceDialog.value = visible
    }
// 소스 초과 다이얼로그

    // 소스 테이블
    private val _sourceTable = mutableStateListOf(IngredientInputBoxState())
    val sourceTable: List<IngredientInputBoxState > get() = _sourceTable
// 소스 테이블

    //소스 추가
    fun addSourceBox() {
        if (_sourceTable.size < 50) {
            _sourceTable.add(IngredientInputBoxState())
        } else {
            setSourceDialog(true)
        }
    }
//소스 추가

    //소스 이동
    fun moveSource(from: Int, to: Int) {
        _sourceTable.apply {
            add(to, removeAt(from))
        }
    }
//소스 이동

    //소스 제거
    fun removeSource(item: IngredientInputBoxState) {
        _sourceTable.remove(item)
    }
//소스 제거

    // 단계 리스트
    private val _stepList = mutableStateListOf<StepState>(
        StepState(
            stepIndex = 0,
            procedureList = mutableStateListOf(ProcedureInputBoxState())
        )

    )
    val stepList: List<StepState> get() = _stepList
// 단계 리스트

    //단계추가
    fun addStep() {
        val newStep = StepState(
            stepIndex = _stepList.size,
            procedureList = mutableStateListOf(ProcedureInputBoxState())
        )
        _stepList.add(newStep)
    }
//단계추가

    //단계제거
    fun removeStep(item: StepState) {
        _stepList.remove(item)
    }
//단계제거

    //과정추가
    fun addProcedure(stepIndex: Int) {
        if (stepIndex in _stepList.indices) {
            _stepList[stepIndex].procedureList.add(ProcedureInputBoxState())
        }
    }
//과정추가

    //과정제거
    fun removeProcedure(step: StepState, procedure: ProcedureInputBoxState) {
        step.procedureList.remove(procedure)
    }
//과정제거

    //과정이동
    fun moveProcedure(stepState:StepState,from: Int = 0, to: Int = 0) {
        _stepList.find { it == stepState }!!.procedureList.apply {
            add(to, removeAt(from))
        }
    }
//과정이동

    //업로드 프로그래스
    private var _isProgressBarShow = mutableStateOf(false)
    val isProgressBarShow: State<Boolean> get()= _isProgressBarShow
//업로드 프로그래스

    //업로드 프로세스 종료
    private var _isUploadProcessingDone = mutableStateOf(false)
    val isUploadProcessingDone: State<Boolean> get()= _isUploadProcessingDone
//업로드 프로세스 종료

    //업로드
    fun uploadProcessing(){
        viewModelScope.launch {
            _isProgressBarShow.value = true
            uploadRecipeRepository.uploadRecipe()
            _isUploadProcessingDone.value = true
        }
    }
//업로드
}
