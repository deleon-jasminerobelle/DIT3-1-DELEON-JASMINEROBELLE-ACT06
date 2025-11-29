package com.example.myapplication

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.currentsapi.services/"

interface NewsApiService {
    @GET("v1/latest-news")
    suspend fun getLatestNews(
        @Query("language") language: String = "en",
        @Query("apiKey") apiKey: String
    ): Response<NewsResponse>

    @GET("v1/search")
    suspend fun searchNews(
        @Query("keywords") query: String,
        @Query("language") language: String = "en",
        // FIX 3: Removed the typo "Query.Query" and used the correct "@Query"
        @Query("apiKey") apiKey: String
    ): Response<NewsResponse>
}

object NewsApi {
    val retrofitService: NewsApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApiService::class.java)
    }
}



