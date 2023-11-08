package com.gumigames.data.service

import com.gumigames.data.model.response.common.ItemResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ItemService {

    /**
     * 전체 아이템 조회
     */
    @GET("guide/items")
    suspend fun getAllItems(): Response<List<ItemResponse>>

    /**
     * 아이템 검색
     */
    @GET("guide/items/search")
    suspend fun searchItems(@Query("keyword") keyword: String): Response<List<ItemResponse>>


}