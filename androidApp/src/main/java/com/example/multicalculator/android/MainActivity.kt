package com.example.multicalculator.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CalcView()
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        CalcView()
    }
}
//creating CalcView Function
@Composable
fun CalcView(){
    val displayText = remember {mutableStateOf("0")}
    Column(modifier = Modifier.background(Color.LightGray) then  Modifier.padding(0.dp)) {
        Row {
            CalcDisplay(displayText)
        }
        Row {
            Column {
                for (i in 7 downTo 1 step 3) CalcRow(display = displayText, startNum = i, numButtons = 3)
                Row {
                    CalcNumericButton(number = 0, display =displayText )
                    CalcEqualsButton(display = displayText)
                }
            }
            Column {
                CalcOperationButton(operation = "+", display = displayText  )
                CalcOperationButton(operation = "-", display = displayText  )
                CalcOperationButton(operation = "*", display = displayText  )
                CalcOperationButton(operation = "/", display = displayText  )
            }
        }
    }
}
@Composable
fun CalcRow(display: MutableState<String>, startNum : Int, numButtons : Int){
    val endNum = startNum + numButtons
    Row (modifier = Modifier.padding(0.dp)){
        for (i in startNum until endNum){
            CalcNumericButton(number = i, display = display)
        }
    }
}
@Composable
fun CalcDisplay(display: MutableState<String>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(10.dp)
            .background(Color.White)
            .border(1.dp, Color.Gray, RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = display.value,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(10.dp)
        )
    }
}


@Composable
fun CalcEqualsButton (calculationEqualsButton: String) {
    Text(text ="Calculation Equals Button: $calculationEqualsButton")
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }


}