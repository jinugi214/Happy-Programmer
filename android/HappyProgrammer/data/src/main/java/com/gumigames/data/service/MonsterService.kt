package com.gumigames.data.service

import com.gumigames.data.model.response.dogam.MonsterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MonsterService {

    /**
     * 전체 몬스터 조회
     */
    @GET("guide/monsters")
    suspend fun getAllMonsters(): Response<List<MonsterResponse>>

    /**
     * 몬스터 검색
     */
    @GET("guide/monsters/search")
    suspend fun getSearchMonsters(@Query("keyword") keyword: String): Response<List<MonsterResponse>>
}