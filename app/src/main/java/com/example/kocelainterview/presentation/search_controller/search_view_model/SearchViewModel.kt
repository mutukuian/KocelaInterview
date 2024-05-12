package com.example.kocelainterview.presentation.search_controller.search_view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kocelainterview.common.core.Resource
import com.example.kocelainterview.domain.use_case.search_controller.SearchControllerUseCase
import com.example.kocelainterview.presentation.search_controller.search_screen.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchControllerUseCase: SearchControllerUseCase
): ViewModel(){
    private val _state = MutableStateFlow<SearchState>(SearchState.Empty)
    val state:StateFlow<SearchState> = _state

    fun searchShip(query:String){
        viewModelScope.launch {
            _state.value = SearchState.Loading
            val searchResult = searchControllerUseCase(query)
            searchResult.collect{resource ->
                when(resource){
                    is Resource.Loading -> {_state.value = SearchState.Loading}
                    is Resource.Success ->{ _state.value = SearchState.Success(resource.data!!)}
                    is Resource.Error -> {_state.value = SearchState.Error(resource.message!!)}
                }

            }
        }
    }
}