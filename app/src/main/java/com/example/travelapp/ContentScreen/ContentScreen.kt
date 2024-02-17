package com.example.travelapp.ContentScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.travelapp.MainScreen.ContentMain

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun contentScreen(navController: NavController,src:String, dst:String){



    Scaffold(
        topBar = {
            TopAppBar(
                title = { Row(horizontalArrangement = Arrangement.Start) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back to Main Screen",
                        modifier = Modifier.clickable{
                            navController.popBackStack()
                        }
                    )
                    Spacer(modifier = Modifier.width(90.dp))
                    Text(text = "Content",
                        textAlign = TextAlign.Center)
                }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.LightGray),
            )
        }
    ) {
            innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            //verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Heading
            entryTextView()
            //Text(text = "$src")
            //Text(text = "$dst")

        }

    }

}
@Composable
@Preview
fun entryTextView() {
    var getSource = "src"
    var getDestination = "dst"
    var currentPlace = "Delhi"
    var eachPlaceDistance = 0
    var totalDistanceCover = 0
    var totalDistanceLeft = 100

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(7.dp)
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(7.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    //.fillMaxHeight()
                    .padding(7.dp),
            ) {
                // Column for OutlinedTextFields
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                ) {
                    OutlinedTextField(
                        value = getSource,
                        onValueChange = { },
                        label = { Text("Source") },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp)
                    )
                    OutlinedTextField(
                        value = getDestination,
                        onValueChange = { },
                        label = { Text("Destination") },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    )
                    OutlinedTextField(
                        value = currentPlace,
                        onValueChange = { },
                        label = { Text("Current") },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    )
                    OutlinedTextField(
                        value = eachPlaceDistance.toString(),
                        onValueChange = { },
                        label = { Text("Distance") },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )
                    OutlinedTextField(
                        value = totalDistanceLeft.toString(),
                        onValueChange = { },
                        label = { Text("Distance Left") },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )
                }

                // Column for Buttons
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .height(360.dp)
                        .padding(start = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(onClick = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp))
                    {
                        Text(text = "List Station")
                    }
                    Button(
                        onClick = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp)
                    ) {
                        Text(text = "Next Station")
                    }
                    Button(
                        onClick = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        Text(text = "Convert Miles/KM")
                    }
                }
            }
        }
    }
}
