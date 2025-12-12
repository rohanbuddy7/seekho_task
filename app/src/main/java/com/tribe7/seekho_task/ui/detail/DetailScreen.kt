package com.tribe7.seekho_task.ui.detail

import AnimeDetail
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.tribe7.seekho_task.utils.NetworkResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    id: Int,
    onBack: () -> Unit,
    modifier: Modifier,
    vm: DetailViewModel = hiltViewModel()
) {
    LaunchedEffect(id) {
        vm.getAnimeDetail(id)
        vm.updateAnimeDetail(id)
    }

    val state by vm.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Anime Detail") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues = paddingValues)){
            when (state) {
                is NetworkResult.Loading -> {
                    Text(
                        text = "Loading...",
                        modifier = Modifier.padding(paddingValues)
                    )
                }

                is NetworkResult.Success -> {
                    val animeDetail = (state as NetworkResult.Success<AnimeDetail>).data
                    AnimeDetailScreenUI(animeDetail)
                }

                is NetworkResult.Error -> {
                    Text(
                        text = "Something went wrong",
                        modifier = Modifier.padding(paddingValues)
                    )
                }
            }
        }
    }
}

@Composable
fun AnimeDetailScreenUI(
    detail: AnimeDetail?,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            AsyncImage(
                model = detail?.posterUrl,
                contentDescription = detail?.title,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
        }

        item {
            Text(
                text = detail?.title ?: "No Title",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Text("Rating: ${detail?.rating ?: "--"}")
                Text("Episodes: ${detail?.episodes ?: "-"}")
            }
        }

        item {
            if(detail?.trailerUrl?.isNotEmpty()==true) {
                Text(
                    text = "Watch Trailer",
                    color = Color(0xFF1E88E5),
                    fontWeight = FontWeight.SemiBold,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(detail.trailerUrl))
                        context.startActivity(intent)
                    }
                )
            }
        }

        item {
            if (detail?.genres?.isNotEmpty()==true) {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    detail.genres.forEach { genre ->
                        Card(
                            onClick = {}
                        ) {
                            Text(genre,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                    }
                }

            }
        }

        item {
            Text(
                text = "Synopsis",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = detail?.synopsis ?: "No Synopsis Available",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        if (detail?.studios?.isNotEmpty()==true) {
            item {
                Text(
                    text = "Main Cast",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(6.dp))
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    detail.studios.forEach {
                        Text("• $it")
                    }
                }
            }
        }

        item { Spacer(Modifier.height(32.dp)) }
    }
}
