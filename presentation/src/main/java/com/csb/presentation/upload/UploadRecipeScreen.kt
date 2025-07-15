package com.csb.presentation.upload

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.csb.presentation.R
import com.csb.presentation.component.CustomBoxOutlineButton
import com.csb.presentation.component.GetPhotoButton
import com.csb.presentation.component.SimpleOutlinedTextField
import com.csb.presentation.component.TakePictureOptionBottomSheet
import com.csb.presentation.component.TopAppBar
import com.csb.presentation.component.dialog.CustomDialog
import com.csb.presentation.component.photo.PhotoItem
import com.csb.presentation.upload.ingredientBox.IngredientInputTable
import com.csb.presentation.upload.stepBox.StepBox
import kotlin.collections.forEachIndexed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadRecipeScreen(
    routeScreenNavController: NavHostController,
    viewModel: UploadRecipeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    //pretendardvariable
    val pretendard = FontFamily(Font(R.font.pretendard))

    //textColor8c8c8c
    val textColor8c8c8c = colorResource(id = R.color.textColor8c8c8c)

    //textColor262626
    val textColor262626 = colorResource(id = R.color.textColor262626)

    //colorD7D7D7
    val colorD7D7D7 = colorResource(id = R.color.colorD7D7D7)

    //color7B7B7B
    val color7B7B7B = colorResource(id = R.color.color7B7B7B)

    //colorE9E9E9
    val colorE9E9E9 = colorResource(id = R.color.colorE9E9E9)

    val textValue1 = remember { mutableStateOf("") }
    val textValue2 = remember { mutableStateOf("") }
    val textValue3 = remember { mutableStateOf("") }
    val textValue4 = remember { mutableStateOf("") }
    val textValue5 = remember { mutableStateOf("") }

    // 갤러리에서 사진 가져오는 런처
    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageUri = result.data?.data
            if (imageUri != null) {
                viewModel.setMainImageUri(imageUri)
            }
        }
    }

    //바텀시트 상태
    val sheetState = rememberModalBottomSheetState()
    if (viewModel.showBottomSheet.value) {
        TakePictureOptionBottomSheet(
            onDismissRequest = {
                viewModel.setShowBottomSheetVisible(false)
            },
            sheetState = sheetState,
            cameraOnclick = { },
            albumOnclick = {
                viewModel.setShowBottomSheetVisible(false)
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
                        type = "image/*"
                    }
                pickImageLauncher.launch(intent)
            }
        )
    }
    //사진삭제 다이얼로그
    if (viewModel.deletePictureDialog.value) {
        CustomDialog(
            message = stringResource(id = R.string.deletePicture),
            onConfirm = {
                viewModel.setMainImageUri(Uri.EMPTY)
            },
            onDismiss = {
                viewModel.setDeletePictureDialog(false)
            },
            showDialog = viewModel.deletePictureDialog.value,
            onDismissRequest = { viewModel.setDeletePictureDialog(false) }
        )
    }

    // 재료초과(50+) 다이얼로그
    if (viewModel.ingredientDialog.value) {
        CustomDialog(
            message = stringResource(id = R.string.noMoreMaterialsCanBeAdded),
            onConfirm = {
                viewModel.setIngredientDialog(false)
            },
            onDismiss = {
                viewModel.setIngredientDialog(false)
            },
            showDialog = viewModel.ingredientDialog.value,
            onDismissRequest = { viewModel.setIngredientDialog(false) }
        )
    }


    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                // 외부 터치 시 포커스를 해제하고 키보드 내리기
                detectTapGestures(onTap = {
                    focusManager.clearFocus() // 포커스를 해제
                })
            },
        topBar = {
            TopAppBar(
                title = stringResource(id = R.string.recipe),
                isDivider = true,
                navigationIconImage = ImageVector.vectorResource(id = R.drawable.close_24px),
                navigationIconOnClick = {
                    routeScreenNavController.popBackStack()
                },
                menuItems = {
                    if (!false) {
                        Text(
                            text = stringResource(id = R.string.register),
                            modifier = Modifier,
                            color = textColor8c8c8c,
                            fontFamily = pretendard
                        )
                        Spacer(
                            modifier = Modifier
                                .padding(end = 8.dp)
                        )
                    } else {
                        Text(
                            text = stringResource(id = R.string.register),
                            modifier = Modifier
                                .clickable {
                                    //등록
                                },
                            color = colorResource(id = R.color.textColor262626),
                            fontFamily = pretendard
                        )
                        Spacer(
                            modifier = Modifier
                                .padding(end = 8.dp)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                text = stringResource(id = R.string.recipeInformation),
                fontSize = 20.sp,
                fontFamily = pretendard,
                color = textColor262626,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 10.dp),
                text = stringResource(id = R.string.image),
                fontSize = 14.sp,
                fontFamily = pretendard,
                color = textColor262626,
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Spacer(modifier = Modifier.width(10.dp))
                if (viewModel.mainImageUri.value != Uri.EMPTY) {
                    PhotoItem(
                        imageUri = viewModel.mainImageUri.value,
                        deleteButton = {
                            viewModel.setDeletePictureDialog(true)
                        }
                    )
                } else {
                    GetPhotoButton(
                        onClick = {
                            viewModel.setShowBottomSheetVisible(true)
                        }
                    )
                }
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 10.dp),
                text = stringResource(id = R.string.title),
                fontSize = 14.sp,
                fontFamily = pretendard,
                color = textColor262626,
                textAlign = TextAlign.Start
            )
            SimpleOutlinedTextField(
                textFieldValue = textValue1,
                paddingTop = 10.dp,
                paddingStart = 10.dp,
                paddingEnd = 10.dp,
                placeHolder = stringResource(id = R.string.recipeTitle)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 10.dp, end = 10.dp)
                    .wrapContentHeight()
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = stringResource(id = R.string.quantity),
                        fontFamily = pretendard,
                        fontSize = 14.sp,
                        color = textColor262626
                    )
                    SimpleOutlinedTextField(
                        singleLine = true,
                        textFieldValue = textValue2,
                        paddingTop = 10.dp,
                        paddingEnd = 10.dp,
                        placeHolder = stringResource(id = R.string.howManyServing)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        var chk = remember { mutableStateOf(false) }
                        Checkbox(
                            checked = chk.value,
                            onCheckedChange = { chk.value = it },
                            colors = CheckboxDefaults.colors(
                                uncheckedColor = colorResource(id = R.color.colorD9D9D9),
                            ),
                            modifier = Modifier
                                .padding(start = 0.dp)
                        )
                        Text(
                            text = stringResource(id = R.string.notSure),
                            fontFamily = pretendard,
                            fontSize = 14.sp,
                            color = textColor262626
                        )
                    }

                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = stringResource(id = R.string.cost),
                        fontFamily = pretendard,
                        fontSize = 14.sp,
                        color = textColor262626
                    )
                    SimpleOutlinedTextField(
                        singleLine = true,
                        textFieldValue = textValue3,
                        paddingTop = 10.dp,
                        paddingEnd = 5.dp,
                        placeHolder = stringResource(id = R.string.materialPurchaseCost)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        var chk = remember { mutableStateOf(false) }
                        Checkbox(
                            checked = chk.value,
                            onCheckedChange = { chk.value = it },
                            colors = CheckboxDefaults.colors(
                                uncheckedColor = colorResource(id = R.color.colorD9D9D9),
                            ),
                            modifier = Modifier
                                .padding(start = 0.dp)
                        )
                        Text(
                            text = stringResource(id = R.string.notSure),
                            fontFamily = pretendard,
                            fontSize = 14.sp,
                            color = textColor262626
                        )
                    }

                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = stringResource(id = R.string.timeMinute),
                        fontFamily = pretendard,
                        fontSize = 14.sp,
                        color = textColor262626
                    )
                    SimpleOutlinedTextField(
                        singleLine = true,
                        textFieldValue = textValue4,
                        paddingTop = 10.dp,
                        placeHolder = stringResource(id = R.string.totalCookingTime)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        var chk = remember { mutableStateOf(false) }
                        Checkbox(
                            checked = chk.value,
                            onCheckedChange = { chk.value = it },
                            colors = CheckboxDefaults.colors(
                                uncheckedColor = colorResource(id = R.color.colorD9D9D9),
                            ),
                            modifier = Modifier
                                .padding(start = 0.dp)
                        )
                        Text(
                            text = stringResource(id = R.string.notSure),
                            fontFamily = pretendard,
                            fontSize = 14.sp,
                            color = textColor262626
                        )
                    }
                }
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 10.dp),
                text = stringResource(id = R.string.memo),
                fontSize = 14.sp,
                fontFamily = pretendard,
                color = textColor262626,
                textAlign = TextAlign.Start
            )
            SimpleOutlinedTextField(
                textFieldValue = textValue5,
                paddingTop = 10.dp,
                paddingStart = 10.dp,
                paddingEnd = 10.dp,
                placeHolder = stringResource(id = R.string.memoLabel),
            )
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .background(color = colorE9E9E9)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                text = stringResource(id = R.string.ingredient),
                fontSize = 16.sp,
                fontFamily = pretendard,
                color = textColor262626,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                text = stringResource(id = R.string.ingredientTip),
                fontSize = 16.sp,
                fontFamily = pretendard,
                color = textColor262626,
                textAlign = TextAlign.Center,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(
                    onClick = {

                    },
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_circle_minus),
                        contentDescription = "n인분 줄이기",
                        tint = Color.Unspecified
                    )
                }
                Text(
                    text = "3${stringResource(id = R.string.serving)}",
                    color = colorResource(id = R.color.textColor262626),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.pretendardvariable)),
                )
                IconButton(
                    onClick = {

                    },
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_circle_plus),
                        contentDescription = "n인분 추가하기",
                        tint = Color.Unspecified
                    )
                }
            }

            IngredientsList(viewModel)

            SourcesList(viewModel)

            Box(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()
                    .height(10.dp)
                    .background(color = colorE9E9E9)
            )


            viewModel.stepList.forEachIndexed { stepIndex, step ->
                StepBox(
                    stepState = step,
                    addProcedure = { viewModel.addProcedure(stepIndex) },
                    onImageButtonClick = { /* 이미지 버튼 클릭 처리 */ }
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(color = colorE9E9E9)
                    .padding(top = 5.dp, bottom = 5.dp)
            ) {
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.Center)
                        .padding(horizontal = 10.dp),
                ) {
                    CustomBoxOutlineButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.background),
                        text = stringResource(id = R.string.addStep),
                        icon = painterResource(id = R.drawable.add_circle_24px),
                        onClick = { viewModel.addStep() },
                        buttonColor = Color.Transparent,
                        borderColor = color7B7B7B,
                        textColor = textColor262626,
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    heightDp = 2000,
    //widthDp = 2000
)
@Composable
fun PreviewUploadRecipeScreen() {
    UploadRecipeScreen(
        rememberNavController(),
    )
}