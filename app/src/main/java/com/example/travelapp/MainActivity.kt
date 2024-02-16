package com.example.travelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.travelapp.ui.theme.TravelAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TravelAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Source_Destination_Dropdown()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Source_Destination_Dropdown(){
    var selectSource by remember{ mutableStateOf("") }
    var selectDestination by remember {
        mutableStateOf("")
    }
    val scrollingState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Travel App") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue),


            )
        }
    )  {
            innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).fillMaxWidth()
                .verticalScroll(scrollingState),
            //verticalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()) {
                  Card (modifier = Modifier
                      .fillMaxWidth()
                      .fillMaxWidth()
                      .padding(12.dp),
                      shape = RoundedCornerShape(corner = CornerSize(15.dp)),
                      colors = CardDefaults.cardColors(containerColor = Color.White),
                      elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)){

                  }

            }

        }

    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TravelAppTheme {
        Source_Destination_Dropdown()
    }
}