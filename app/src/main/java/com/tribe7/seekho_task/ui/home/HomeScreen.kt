package com.tribe7.seekho_task.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onBack: () -> Unit, modifier: Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home") },
            )
        }
    ) { paddingValues ->
        AnimeList(Modifier.padding(paddingValues = paddingValues))
    }
}

@Composable
fun AnimeList(modifier: Modifier) {
    LazyColumn(modifier = modifier) {
        item(10){
            Text("")
        }
    }
}