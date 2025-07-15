package com.csb.presentation.recipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csb.presentation.R

//(과정 리스트와 스템이름을 파라미터로 받아야함)
@Composable
fun StepItem(
    stepTitle:String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .background(color = colorResource(id = R.color.colorE9E9E9))
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp,),
            color = colorResource(id = R.color.textColor262626),
            fontSize = 16.sp,
            text = stepTitle,
            fontFamily = FontFamily(Font(R.font.pretendard)),
            textAlign = TextAlign.Center
        )
        val list = listOf("과정1","과정2","과정3")
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .padding(start = 10.dp, end = 10.dp)
        ){
            list.forEachIndexed { index,item->
                ProcessItem(item)
            }
        }




    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStepItem(){
    StepItem("조리")
}