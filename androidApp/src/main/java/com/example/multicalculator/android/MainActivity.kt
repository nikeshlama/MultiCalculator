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
