package com.csb.presentation.upload.stepBox

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.csb.presentation.R
import com.csb.presentation.component.CustomBoxOutlineButton
import com.csb.presentation.upload.UploadRecipeViewModel

@Composable
fun StepBoxList(
    viewModel: UploadRecipeViewModel
) {
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

    //colorD9D9D9
    val colorD9D9D9 = colorResource(id = R.color.colorD9D9D9)
//    viewModel.stepList.forEachIndexed { stepIndex, step ->
//        StepBox(
//            stepState = step,
//            addProcedure = { viewModel.addProcedure(stepIndex) },
//            onImageButtonClick = {
//                /* 이미지 버튼 클릭 처리 */
//            },
//            onDeleteStep = {
//                viewModel.removeStep(step)
//            },
//            onDeleteProcedure = { procedure ->
//                viewModel.removeProcedure(step, procedure)
//            },
//            moveProcedure = { from, to -> viewModel.moveProcedure(step, from, to) }
//        )
//    }

    viewModel.formState.value.stepList.forEachIndexed { stepIndex, step ->
        StepBox(
            stepState = step,
            addProcedure = { viewModel.addProcedure(stepIndex) },
            onImageButtonClick = {
                /* 이미지 버튼 클릭 처리 */
            },
            onDeleteStep = {
                viewModel.removeStep(step)
            },
            onDeleteProcedure = { procedure ->
                viewModel.removeProcedure(step, procedure)
            },
            moveProcedure = { from, to -> viewModel.moveProcedure(step, from, to) }
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