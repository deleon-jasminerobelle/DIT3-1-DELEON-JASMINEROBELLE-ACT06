package com.example.myapplication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.isEmpty
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface NewsUiState {
    data class Success(val articles: List<Article>) : NewsUiState
    data class Error(val message: String) : NewsUiState
    object Loading : NewsUiState
}

class NewsViewModel : ViewModel() {

    var newsUiState: NewsUiState by mutableStateOf(NewsUiState.Loading)
        private set

    // FIX: Add state for the search query
    var searchQuery by mutableStateOf("")
        private set

    private val apiKey = "dvHMB1G77HsZA5eyacP6xWHmw-mRXVle3tgjKEl69sbz5iQ2"

    init {
        // Fetch top headlines when the app starts
        fetchNews()
    }

    // FIX: Create a public function to update the search query
    fun onSearchQueryChange(newQuery: String) {
        searchQuery = newQuery
    }

    // FIX: Renamed and generalized the fetching logic
    fun fetchNews(query: String = "") {
        viewModelScope.launch {
            newsUiState = NewsUiState.Loading
            if (apiKey.isBlank()) {
                newsUiState = NewsUiState.Error("API Key is missing.")
                return@launch
            }

            newsUiState = try {
                val response = if (query.isBlank()) {
                    // If query is empty, get top headlines
                    NewsApi.retrofitService.getLatestNews(apiKey = apiKey)
                } else {
                    // Otherwise, search with the query
                    NewsApi.retrofitService.searchNews(query = query, apiKey = apiKey)
                }

                if (response.isSuccessful && response.body() != null) {
                    if (response.body()!!.articles.isEmpty()) {
                        NewsUiState.Error("No articles found for your search.")
                    } else {
                        NewsUiState.Success(response.body()!!.articles)
                    }
                } else {
                    handleApiError(response.code())
                }
            } catch (e: IOException) {
                NewsUiState.Error("No Internet: Please check your connection.")
            } catch (e: Exception) {
                NewsUiState.Error("An unexpected error occurred: ${e.message}")
            }
        }
    }

    private fun handleApiError(code: Int): NewsUiState.Error {
        return NewsUiState.Error(
            when (code) {
                401 -> "API Key is invalid. Please check it."
                429 -> "Too many requests. Please try again later."
                else -> "API Error: Code $code"
            }
        )
    }
}


