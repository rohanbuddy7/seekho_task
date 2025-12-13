package com.tribe7.seekho_task.ui.detail

import AnimeDetail
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
class DetailViewModel @Inject constructor(var repo: AnimeRepo): ViewModel() {
    private val _state = MutableStateFlow<NetworkResult<AnimeDetail?>>(NetworkResult.Loading())
    val state: StateFlow<NetworkResult<AnimeDetail?>> = _state

    fun updateAnimeDetail(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                repo.updateAnimeDetail(id)
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    fun getAnimeDetail(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repo.observeAnimeDetail(id).collect { data ->
                if (data == null) {
                    _state.value = NetworkResult.Error("No cached data found")
                } else {
                    _state.value = NetworkResult.Success(data = data)
                }
            }
        }
    }

}

