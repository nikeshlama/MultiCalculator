package com.example.multicalculator.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.multicalculator.Greeting

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GreetingView(Greeting().greet())
                    CalcView("this is the Calculation View space")
                    CalcRow("this is the Calculation Row space")
                    CalcDisplay("this is the Calculation Display space")
                }
            }
        }
    }
}
//creating CalcView Function
@Composable
fun CalcView(calculationView: String) {
    Text(text = "Calculation View: $calculationView")
}

//Creating CalcRow Function
@Composable
fun CalcRow(calculationRow: String) {
     Text(text = "Calculation Row: $calculationRow")
}

//Creating CalcDisplay Function
@Composable
fun CalcDisplay(calculationDisplay: String) {
    Text(text = "Calculation Display: $calculationDisplay")
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
