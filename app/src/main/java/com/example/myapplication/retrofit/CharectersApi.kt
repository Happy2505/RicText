package com.example.myapplication.retrofit

import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterApi {
    @GET("character")
    suspend fun getCharacter(): CharacterListResponse

    @GET("character/{id}")
    suspend fun getCharacterID(@Path("id") id: Int): Character
}