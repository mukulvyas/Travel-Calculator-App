package com.example.travelapp.ContentScreen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlin.math.absoluteValue

data class CityDistance(val city: String, val distanceKm: Int)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun contentScreen(navController: NavController, src: String, dst: String) {

    val cityDistances = listOf(
        CityDistance("MAJLIS PARK", 0),
        CityDistance("AZADPUR", 8),
        CityDistance("SHALIMAR BAGH", 12),
        CityDistance("NETAJI SUBHASH PLACE", 15),
        CityDistance("SHAKURPUR", 20),
        CityDistance("PUNJABI BAGH WEST", 25),
        CityDistance("ESI-BASAIDARPUR", 30),
        CityDistance("RAJOURI GARDEN", 35),
        CityDistance("MAYAPURI", 40),
        CityDistance("NARAINA VIHAR", 45),
        CityDistance("DELHI CANTT.", 50),
        CityDistance("DURGABAI DESHMUKH SOUTH CAMPUS", 55),
        CityDistance("SIR M. VISHWESHWARAIAH MOTI BAGH", 60),
        CityDistance("BHIKAJI CAMA PLACE", 65),
        CityDistance("SAROJINI NAGAR", 70),
        CityDistance("DILLI HAAT - INA", 75),
        CityDistance("SOUTH EXTENSION", 80),
        CityDistance("LAJPAT NAGAR", 85),
        CityDistance("VINOBAPURI", 90),
        CityDistance("ASHRAM", 95),
        CityDistance("SARAI KALE KHAN - NIZAMUDDIN", 100),
        CityDistance("MAYUR VIHAR - 1", 105)
    )

    // Calculate the distance between src and dst
    val distanceSrc = cityDistances.firstOrNull { it.city == dst }?.distanceKm ?: 0
    val distanceDst = cityDistances.firstOrNull { it.city == src }?.distanceKm ?: 0
    val distanceSrcDst = if (distanceSrc == 0) -distanceDst else distanceSrc - distanceDst



        var filteredCities = if (distanceSrcDst < 0) {
            cityDistances
                .dropWhile { it.city != dst }
                .dropLastWhile { it.city != src }
                .distinctBy { it.city }
        }
    else{
            cityDistances
                .dropWhile { it.city != src }
                .dropLastWhile { it.city != dst }
                .distinctBy { it.city }
        }


    val reversedList: List<CityDistance> = if (distanceSrcDst < 0) {
        filteredCities.reversed()
    } else {
        filteredCities
    }


// Now you can use the new_list as needed

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(horizontalArrangement = Arrangement.Start) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back to Main Screen",
                            modifier = Modifier.clickable {
                                navController.popBackStack()
                            }
                        )
                        Spacer(modifier = Modifier.width(90.dp))
                        Text(text = "Content", textAlign = TextAlign.Center)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.LightGray),
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
             //Heading
//            Text(text = "$distanceSrcDst")
//            Text(text = "$src")
//            Text(text = "$dst")
//            Text(
//                text = "Filtered Cities: ${filteredCities.map { it.distanceKm }}",
//                modifier = Modifier
//                    .padding(16.dp)
//                    .fillMaxWidth(),
//                textAlign = TextAlign.Center,
//                fontWeight = FontWeight.Bold,
//                fontSize = 18.sp,
//                color = Color.Black
//            )
//
//                        Text(
//                            text = "Filtered Cities: ${reversedList.joinToString { it.city }}",
//                modifier = Modifier
//                    .padding(16.dp)
//                    .fillMaxWidth(),
//                textAlign = TextAlign.Center,
//                fontWeight = FontWeight.Bold,
//                fontSize = 18.sp,
//                color = Color.Black
//            )

            val totalDistance: Int = if (distanceDst == 0 || dst == "MAJLIS PARK" ) {
                filteredCities.subList(0, filteredCities.size).sumOf { it.distanceKm }

            } else {
                filteredCities.subList(1, filteredCities.size).sumOf { it.distanceKm }
            }

            Log.d("TAG","totalDistance:$totalDistance")


            entryTextView(src, dst, reversedList.map { it.city }, filteredCities.map { it.distanceKm },totalDistance)
        }
    }
}

@Composable
//@Preview
fun entryTextView(src: String, dst: String, filteredCities: List<String>, distances: List<Int>,totalDistance:Int) {

    val totalDistanceMiles = totalDistance
    val totalDistanceKm = (totalDistanceMiles / 1.609)
    var progress by remember { mutableStateOf(0.0) }

    var getSource = src
    var getDestination = dst
    var currentPlace = src
    var eachPlaceDistanceKm = 0.00
    var eachPlaceDistanceMiles by remember { mutableStateOf(0) }

    // Initialize total distances with 0
    var totalDistanceLeftKm by remember { mutableStateOf(totalDistanceKm) }
    var totalDistanceLeftMiles by remember { mutableStateOf(totalDistanceMiles) }

    var totalDistanceCoverKm by remember { mutableStateOf(0.00) }
    var totalDistanceCoverMiles by remember { mutableStateOf(0) } // Initialize with totalDistanceMiles
    var showList by remember { mutableStateOf(false) }
    var currentIndex by remember { mutableStateOf(0) }
    var currentUnit by remember { mutableStateOf(UnitType.MILES) }

    progress = 0.0
    LaunchedEffect(currentIndex) {
        val totalStations = filteredCities.size
        progress = (currentIndex + 1) / totalStations.toDouble()
        if (currentIndex == 0) {
            totalDistanceCoverKm = 0.0
            totalDistanceCoverMiles = 0
        }
    }

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
                        value = if (currentUnit == UnitType.MILES) "$totalDistanceCoverMiles miles" else "$totalDistanceCoverKm km",
                        onValueChange = {
                            // Handle value change if needed
                        },
                        label = { Text("Distance Cover") },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )
                    OutlinedTextField(
                        value = if (currentUnit == UnitType.MILES) "$totalDistanceLeftMiles miles" else "$totalDistanceLeftKm km",
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
                            if (currentIndex != filteredCities.indexOf(dst)) {
                                currentIndex = (currentIndex + 1) % filteredCities.size
                                if (currentIndex == 0)
                                {
                                    currentPlace = 0.toString()
                                }
                                else{
                                    currentPlace = filteredCities[currentIndex]}


                                eachPlaceDistanceMiles = distances[currentIndex]
                                eachPlaceDistanceKm = "%.2f".format(eachPlaceDistanceMiles * 1.60934).toDouble()


                                totalDistanceCoverKm = "%.2f".format(totalDistanceCoverKm).toDouble() + "%.2f".format(eachPlaceDistanceKm).toDouble().absoluteValue
                                totalDistanceCoverMiles += eachPlaceDistanceMiles.absoluteValue


                                totalDistanceLeftKm -=  "%.2f".format(eachPlaceDistanceKm).toDouble().absoluteValue
                                totalDistanceLeftMiles -= eachPlaceDistanceMiles.absoluteValue

                                //progress = ((totalDistanceCoverKm + eachPlaceDistanceKm) / totalDistanceKm).coerceIn(0.0, 1.0)

                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp),
                        enabled = currentIndex != filteredCities.indexOf(dst)
                    ) {
                        Text(text = "Next Station")
                    }

                    Button(
                        onClick = {
                            // Toggle between kilometers and miles
                            currentUnit = if (currentUnit == UnitType.MILES) {
                                eachPlaceDistanceKm = "%.2f".format(eachPlaceDistanceMiles * 1.60934).toDouble().absoluteValue
                                totalDistanceLeftKm = "%.2f".format(totalDistanceLeftMiles * 1.60934).toDouble().absoluteValue
                                totalDistanceCoverKm = "%.2f".format(totalDistanceCoverMiles * 1.60934).toDouble().absoluteValue

                                UnitType.KILOMETERS
                            } else {
                                eachPlaceDistanceMiles = (eachPlaceDistanceKm / 1.60934).toInt().absoluteValue
                                totalDistanceLeftMiles = (totalDistanceLeftKm / 1.60934).toInt().absoluteValue
                                totalDistanceCoverMiles = (totalDistanceCoverKm / 1.60934).toInt().absoluteValue
                                UnitType.MILES
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        Text(text = "Convert to ${if (currentUnit == UnitType.MILES) "Kilometer" else "Miles"}")
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
            LinearProgressIndicator(
                progress = progress.toFloat(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 12.dp, end = 12.dp, bottom = 12.dp)
            )

            // Percentage Text
            Text(
                text = "${"%.2f".format(progress * 100)}%",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
                    .align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )
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