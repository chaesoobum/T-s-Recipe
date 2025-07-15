package com.csb.presentation.upload

import android.net.Uri
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.csb.presentation.upload.ingredientBox.IngredientInputBoxState
import com.csb.presentation.upload.state.ProcedureInputBoxState
import com.csb.presentation.upload.state.StepState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UploadRecipeViewModel
@Inject constructor() : ViewModel() {
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

    //레시피 제목(1자 ~20자)
    private var _recipeTitle = mutableStateOf("")
    val recipeTitle:State<String> get() = _recipeTitle

    //레시피 분량(숫자)(숫자만 입력받게)
    private var _recipePortion = mutableStateOf<Int?>(null)
    val recipePortion:State<Int?> get() = _recipePortion

    //레시피 비용(숫자)(숫자만 입력받게)
    private var _recipePrice = mutableStateOf<Int?>(null)
    val recipePrice:State<Int?>get() = _recipePrice

    //레시피 소요시간(숫자)(숫자만 입력받게)(분)
    private var _recipeTime = mutableStateOf<Int?>(null)
    val recipeTime:State<Int?>get() = _recipeTime

    //기타메모(스트링)(1자~100자)
    private var _recipeMemo = mutableStateOf("")
    var recipeMemo:State<String> = _recipeMemo


    // 재료 초과 다이얼로그
    private val _ingredientDialog = mutableStateOf(false)
    val ingredientDialog: State<Boolean> get() = _ingredientDialog
    fun setIngredientDialog(visible: Boolean) {
        _ingredientDialog.value = visible
    }

    // 재료 테이블
    private val _ingredientTable = mutableStateListOf(IngredientInputBoxState())
    val ingredientTable: List<IngredientInputBoxState> get() = _ingredientTable

    //재료추가
    fun addIngredientBox() {
        if (_ingredientTable.size < 50) {
            _ingredientTable.add(IngredientInputBoxState())
        } else {
            setIngredientDialog(true)
        }
    }
    //재료이동
    fun moveIngredient(from: Int, to: Int) {
        _ingredientTable.apply {
            add(to, removeAt(from))
        }
    }
    //재료제거
    fun removeIngredient(item: IngredientInputBoxState) {
        _ingredientTable.remove(item)
    }

    // 소스 초과 다이얼로그
    private val _sourceDialog = mutableStateOf(false)
    val sourceDialog: State<Boolean> get() = _sourceDialog
    fun setSourceDialog(visible: Boolean) {
        _sourceDialog.value = visible
    }

    // 소스 테이블
    private val _sourceTable = mutableStateListOf(IngredientInputBoxState())
    val sourceTable: List<IngredientInputBoxState> get() = _sourceTable

    //소스 추가
    fun addSourceBox() {
        if (_sourceTable.size < 50) {
            _sourceTable.add(IngredientInputBoxState())
        } else {
            setSourceDialog(true)
        }
    }
    //소스 이동
    fun moveSource(from: Int, to: Int) {
        _sourceTable.apply {
            add(to, removeAt(from))
        }
    }
    //소스 제거
    fun removeSource(item: IngredientInputBoxState) {
        _sourceTable.remove(item)
    }

    // 단계 리스트
    private var _stepList = mutableStateOf(
        listOf(
            StepState(
                stepIndex = 0,
                //stepName = mutableStateOf("Detailed Steps"),
                procedureList = mutableStateListOf(ProcedureInputBoxState())
            )
        )
    )
    val stepList: List<StepState> get() = _stepList.value

    //단계추가
    fun addStep() {
        val newStep = StepState(
            stepIndex = _stepList.value.size,
            //stepName = mutableStateOf("Detailed Steps"),
            procedureList = mutableStateListOf(ProcedureInputBoxState())
        )
        _stepList.value = _stepList.value + newStep
    }
    //과정추가
    fun addProcedure(stepIndex: Int) {
        _stepList.value[stepIndex].procedureList.add(ProcedureInputBoxState())
    }
}
