package com.tribe7.seekho_task.ui.home

import Anime
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tribe7.seekho_task.data.repo.AnimeRepo
import com.tribe7.seekho_task.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(var repo: AnimeRepo): ViewModel() {
    private val _state = MutableStateFlow<NetworkResult<List<Anime>>>(NetworkResult.Loading())
    val state: StateFlow<NetworkResult<List<Anime>>> = _state

    init {
        getAnime()
        updateAnime()
    }

    fun updateAnime(){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                repo.updateAnimeList()
            }catch (e: Exception){
                e.printStackTrace()
                _state.value = NetworkResult.Error("Something went wrong. Please try again later")
            }
        }
    }

    fun getAnime(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.observeAnime().collect { list ->
                _state.value = NetworkResult.Success(list)
            }
        }
    }

}

