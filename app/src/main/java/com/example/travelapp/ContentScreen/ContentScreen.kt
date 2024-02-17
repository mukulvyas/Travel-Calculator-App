package com.example.travelapp.ContentScreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun contentScreen(navController: NavController,src:String, dst:String){

    Text(text = "$src")
    Text(text = "$dst")
}