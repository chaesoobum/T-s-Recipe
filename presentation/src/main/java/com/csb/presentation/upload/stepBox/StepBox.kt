package com.csb.presentation.upload.stepBox

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csb.presentation.R
import com.csb.presentation.component.CustomBoxOutlineButton
import com.csb.presentation.component.SimpleOutlinedTextField
import com.csb.presentation.upload.state.StepState

@Composable
fun StepBox(
    stepState: StepState,
    addProcedure: () -> Unit,
    onImageButtonClick: () -> Unit
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        text = stringResource(id = R.string.detailedSteps),
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.pretendard)),
        color = colorResource(id = R.color.textColor262626),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .weight(5f)
        ) {
            SimpleOutlinedTextField(
                textFieldValue = stepState.stepName,
                singleLine = true,
                placeHolder = "${stringResource(id = R.string.step)}${stepState.stepIndex + 1}"
            )
        }
        Box(
            modifier = Modifier
            .weight(1f),
            contentAlignment = Alignment.Center
        ){
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_trash),
                contentDescription = null,
                tint = colorResource(id = R.color.textColor262626),
                modifier = Modifier
                    .clickable {

                    }
            )
        }
    }


    HorizontalDivider(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .padding(bottom = 10.dp)
            .height(2.dp)
            .background(color = colorResource(id = R.color.colorD9D9D9)))


    Column(modifier = Modifier.fillMaxWidth()) {
        stepState.procedureList.forEach { procedure ->
            ProcedureInputBox(
                data = procedure,
                onImageButtonClick = onImageButtonClick
            )
        }
    }

    CustomBoxOutlineButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        text = stringResource(id = R.string.addDetailProcess),
        icon = painterResource(id = R.drawable.add_circle_24px),
        onClick = addProcedure,
        buttonColor = Color.Transparent,
        borderColor = colorResource(id = R.color.color7B7B7B),
        textColor = colorResource(id = R.color.textColor262626),
    )

    Spacer(modifier = Modifier.height(10.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)
            .background(color = colorResource(id = R.color.colorE9E9E9))
    )
}