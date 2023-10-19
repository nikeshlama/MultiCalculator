package com.example.multicalculator.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

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
//CalcView function logic applied
//ensuring that all the variables are using rememberSaveable
@Composable
fun CalcView() {
    var leftNumber by rememberSaveable { mutableStateOf(0) }
    var rightNumber by rememberSaveable { mutableStateOf(0) }
    var operation by rememberSaveable { mutableStateOf("") }
    var complete by rememberSaveable { mutableStateOf(false) }
    var displayText by rememberSaveable { mutableStateOf("0") }

    val coroutineScope = rememberCoroutineScope()
    fun numberPress(btnNum: Int) {
        coroutineScope.launch {
            //creating an if statement that checks if the complete variable is true
            if (complete) {
                leftNumber = 0
                rightNumber = 0
                operation = ""
                complete = true
            }

            if (operation.isNotBlank() && !complete) {
                rightNumber = rightNumber * 10 + btnNum
            } else if (operation.isBlank() && !complete) {
                leftNumber = leftNumber * 10 + btnNum
            }

            displayText = if (operation.isEmpty()) {
                leftNumber.toString()
            } else {
                rightNumber.toString()
            }
        }
    }
    //function operationPress logic applied
    fun operationPress(op: String) {
        if (!complete) {
            operation = op
            if (leftNumber != 0) {
                complete = false
            }
        }
    }
    //function equalPress logic applied
    fun equalsPress() {
        if (operation.isNotEmpty() && !complete) {
            var answer = 0
            when (operation) {
                "+" -> answer = leftNumber + rightNumber
                "-" -> answer = leftNumber - rightNumber
                "*" -> answer = leftNumber * rightNumber
                "/" -> answer = leftNumber / rightNumber
            }
            rightNumber = 0
            complete = true
            displayText = answer.toString()
        }
    }
    //function clear logic applied
    fun clear() {
        leftNumber = 0
        rightNumber = 0
        operation = ""
        complete = false
        displayText = "0"
    }
//UI code for CalcDisplay
    Column(modifier = Modifier
        .background(Color.LightGray)
        .padding(0.dp)) {
        Row {
            CalcDisplay(displayText)
        }
        Row {
            Column {
                for (i in 7 downTo 1 step 3) {
                    CalcRow(
                        onPress = { number -> numberPress(number) },
                        startNum = i,
                        numButtons = 3
                    )
                }
                Row {
                    CalcNumericButton(onPress = { number -> numberPress(number) }, number = 0)
                    CalcEqualsButton(onPress = { equalsPress() })
                }
            }

            Column {
                CalcOperationButton(
                    onPress = { op -> operationPress(op) },
                    operation = "+"
                )
                CalcOperationButton(
                    onPress = { op -> operationPress(op) },
                    operation = "-"
                )
                CalcOperationButton(
                    onPress = { op -> operationPress(op) },
                    operation = "*"
                )
                CalcOperationButton(
                    onPress = { op -> operationPress(op) },
                    operation = "/"
                )
//button created for Clear
                Button(
                    onClick = { clear() },
                    modifier = Modifier
                        .padding(4.dp)
                        .size(95.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = "C", fontSize = 30.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
//CalcRow function logic applied
@Composable
fun CalcRow(onPress: (number: Int) -> Unit, startNum: Int, numButtons: Int) {
    val endNum = startNum + numButtons
    Row(modifier = Modifier.padding(0.dp)) {
        for (i in startNum until endNum) {
            CalcNumericButton(onPress, i)
        }
    }
}

//CalcDisplay function logic applied
@Composable
fun CalcDisplay(display: String) {
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
            text = display,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(10.dp)
        )
    }
}

