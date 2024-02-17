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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

    val citySource = listOf(
        "SELECT CITY",
        "MAJLIS PARK",
        "AZADPUR",
        "SHALIMAR BAGH",
        "NETAJI SUBHASH PLACE",
        "SHAKURPUR",
        "PUNJABI BAGH WEST",
        "ESI-BASAIDARPUR",
        "RAJOURI GARDEN",
        "MAYAPURI",
        "NARAINA VIHAR",
        "DELHI CANTT.",
        "DURGABAI DESHMUKH SOUTH CAMPUS",
        "SIR M. VISHWESHWARAIAH MOTI BAGH",
        "BHIKAJI CAMA PLACE",
        "SAROJINI NAGAR",
        "DILLI HAAT - INA",
        "SOUTH EXTENSION",
        "LAJPAT NAGAR",
        "VINOBAPURI",
        "ASHRAM",
        "SARAI KALE KHAN - NIZAMUDDIN",
        "MAYUR VIHAR - 1"
    )

    val filteredCities = citySource
        .dropWhile { it != src }
        .dropLastWhile { it != dst }
        .distinct()


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
            entryTextView(src,dst,filteredCities)


//             Text(text = "$src")
//            Text(text = "$dst")

            }

    }

}

@Composable
//@Preview
fun entryTextView(src: String, dst: String ,filteredCities: List<String>) {
//    var getSource = "src"
//    var getDestination = "dst"
//    var currentPlace = "Delhi"
//    var eachPlaceDistance = 0
//    var totalDistanceCover = 0
//    var totalDistanceLeft = 100
//    var currentUnit by remember { mutableStateOf(UnitType.KILOMETERS) }
//    var conversionFactor = if (currentUnit == UnitType.KILOMETERS) 1.0 else 0.621371

    var getSource = src
    var getDestination = dst
    var currentPlace = src
    var eachPlaceDistanceKm = 1.0
    var eachPlaceDistanceMiles by remember { mutableStateOf(1.60934) }
    var totalDistanceLeftKm = 2.0
    var totalDistanceLeftMiles by remember { mutableStateOf(1.60934) }
    var totalDistanceCoverKm = 1.0
    var totalDistanceCoverMiles by remember { mutableStateOf(1.60934) }
    var showList by remember { mutableStateOf(false) }
    var currentIndex by remember { mutableStateOf(0) }



    // State variables for unit conversion
    var currentUnit by remember { mutableStateOf(UnitType.KILOMETERS) }


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
                        value = filteredCities.getOrNull(currentIndex) ?: "",
                        onValueChange = { },
                        label = { Text("Current") },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)

                    )
                    OutlinedTextField(
                        value = if (currentUnit == UnitType.KILOMETERS) eachPlaceDistanceKm.toString() else eachPlaceDistanceMiles.toString(),
                        onValueChange = {
                            // Handle value change if needed
                        },
                        label = { Text("Distance") },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )
                    OutlinedTextField(
                        value = if (currentUnit == UnitType.KILOMETERS) totalDistanceLeftKm.toString() else totalDistanceLeftMiles.toString(),
                        onValueChange = {
                            // Handle value change if needed
                        },
                        label = { Text("Distance Left") },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
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
                    Button(
                        onClick = {
                            currentIndex = (currentIndex + 1) % filteredCities.size
                            currentPlace = filteredCities[currentIndex]

                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp)
                    ) {
                        Text(text = "Next Station")
                    }
                    Button(
                        onClick = {
                            // Toggle between kilometers and miles
                            currentUnit = if (currentUnit == UnitType.KILOMETERS) {
                                eachPlaceDistanceMiles = eachPlaceDistanceKm * 1.60934
                                totalDistanceLeftMiles = totalDistanceLeftKm * 1.60934
                                totalDistanceCoverMiles = totalDistanceCoverKm * 1.60934
                                UnitType.MILES
                            } else {
                                eachPlaceDistanceKm = eachPlaceDistanceMiles / 1.60934
                                totalDistanceLeftKm = totalDistanceLeftMiles / 1.60934
                                totalDistanceCoverKm = totalDistanceCoverMiles / 1.60934
                                UnitType.KILOMETERS
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        Text(text = "Convert Miles/KM")
                    }

                    Button(
                        onClick = { showList = !showList },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp)
                    ) {
                        Text(text = "List")
                    }
                }
            }

        }
        if (showList) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(7.dp)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(filteredCities) { city ->
                        Text(
                            text = city,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                    }
                }
            }
        }


    }
}

enum class UnitType {
    KILOMETERS,
    MILES
}