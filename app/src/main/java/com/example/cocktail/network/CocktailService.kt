package com.example.cocktail.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "www.thecocktaildb.com/api/json/v1/1/"

   private val retrofit =
    Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory
            .create())
            .build()
   object CocktailService{
    val services =
    }
