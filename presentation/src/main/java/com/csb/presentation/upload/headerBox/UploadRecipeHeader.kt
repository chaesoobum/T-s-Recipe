package com.csb.presentation.upload.headerBox

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csb.presentation.R
import com.csb.presentation.component.GetPhotoButton
import com.csb.presentation.component.SimpleOutlinedTextField
import com.csb.presentation.component.photo.PhotoItem
import com.csb.presentation.upload.UploadRecipeViewModel

@Composable
fun UploadRecipeHeader(
    viewModel: UploadRecipeViewModel
) {
    //pretendard
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

    //colorD9D9D9
    val colorD9D9D9 = colorResource(id = R.color.colorD9D9D9)

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
        textFieldValue = viewModel.formState.value.title,
        placeHolder = stringResource(id = R.string.recipeTitle),
        paddingTop = 10.dp,
        paddingStart = 10.dp,
        paddingEnd = 10.dp,
        singleLine = true,
        maxLength = 20
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
                textFieldValue = viewModel.formState.value.portion,
                placeHolder = stringResource(id = R.string.howManyServing),
                paddingTop = 10.dp,
                paddingEnd = 10.dp,
                singleLine = true,
                enabled = !viewModel.portionEnable.value,
                maxLength = 10,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Checkbox(
                    checked = viewModel.portionEnable.value,
                    onCheckedChange = { viewModel.setPortionEnable(it) },
                    colors = CheckboxDefaults.colors(
                        uncheckedColor = colorD9D9D9,
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
                textFieldValue = viewModel.formState.value.cost,
                placeHolder = stringResource(id = R.string.materialPurchaseCost),
                paddingTop = 10.dp,
                paddingEnd = 5.dp,
                singleLine = true,
                maxLength = 10,
                enabled = !viewModel.priceEnable.value,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Checkbox(
                    checked = viewModel.priceEnable.value,
                    onCheckedChange = { viewModel.setPriceEnable(it) },
                    colors = CheckboxDefaults.colors(
                        uncheckedColor = colorD9D9D9,
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
                textFieldValue = viewModel.formState.value.time,
                placeHolder = stringResource(id = R.string.totalCookingTime),
                paddingTop = 10.dp,
                singleLine = true,
                enabled = !viewModel.timeEnable.value
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Checkbox(
                    checked = viewModel.timeEnable.value,
                    onCheckedChange = { viewModel.setTimeEnable(it) },
                    colors = CheckboxDefaults.colors(
                        uncheckedColor = colorD9D9D9,
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
        textFieldValue = viewModel.formState.value.memo,
        placeHolder = stringResource(id = R.string.memoLabel),
        singleLine = false,
        paddingTop = 10.dp,
        paddingStart = 10.dp,
        paddingEnd = 10.dp,
    )
    Spacer(modifier = Modifier.height(20.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)
            .background(color = colorE9E9E9)
    )
}