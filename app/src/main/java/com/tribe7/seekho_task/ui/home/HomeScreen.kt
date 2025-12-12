package com.tribe7.seekho_task.ui.home

import Anime
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.tribe7.seekho_task.utils.NetworkResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigate: (String) -> Unit ,
    onBack: () -> Unit,
    modifier: Modifier,
    vm: HomeViewModel = hiltViewModel()
) {
    val state by vm.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home") },
            )
        }
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (state) {
            is NetworkResult.Error<*> -> {
                val message = (state as NetworkResult.Error<*>).message
                Text(
                    text = message,
                    modifier = Modifier.padding(paddingValues)
                )
            }
            is NetworkResult.Loading<*> -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is NetworkResult.Success<*> -> {
                val items = (state as NetworkResult.Success<List<Anime>>).data
                Log.d("TAG", "HomeScreen: $items")
                if(items.isEmpty()){
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        Text("No anime found", style = MaterialTheme.typography.bodyLarge)
                    }
                } else {
                    AnimeList(
                        modifier = modifier,
                        onNavigate = { id ->
                            onNavigate("detail/$id")
                        },
                        anime = items
                    )
                }
            }
        }
        }
    }
}

@Composable
fun AnimeList(
    modifier: Modifier,
    onNavigate: (Int) -> Unit,
    anime: List<Anime>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(
            items = anime,
            key = { it.id }
        ) { anime ->
            AnimeRow(anime) { id ->
                onNavigate(id)
            }
        }
    }
}


@Composable
fun AnimeRow(item: Anime, onClick: (Int) -> Unit) {
    Card(modifier = Modifier
        .padding(vertical = 10.dp, horizontal = 20.dp)
        .fillMaxWidth()
        .clickable { onClick(item.id) },
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Card(
                modifier = Modifier.clip(RoundedCornerShape(50.dp))
            ) {
                AsyncImage(
                    model = item.posterUrl,
                    contentDescription = item.title,
                    modifier = Modifier.size(96.dp),
                    contentScale = ContentScale.FillWidth
                )
            }

            Spacer(Modifier.width(8.dp))
            Column {
                Text(item.title, style = MaterialTheme.typography.headlineMedium)
                Text("Episodes: ${item.episodes ?: "-"}")
                Text("Rating: ${item.rating ?: "-"}")
            }
        }
    }
}