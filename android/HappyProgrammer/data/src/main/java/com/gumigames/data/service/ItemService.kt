package com.gumigames.data.service

import com.gumigames.data.model.response.ItemResponse
import com.moneyminions.mvvmtemplate.dto.RepoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ItemService {

    @GET("guide/items")
    suspend fun getAllItems(): Response<List<ItemResponse>>

}