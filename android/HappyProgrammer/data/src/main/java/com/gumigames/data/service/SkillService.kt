package com.gumigames.data.service

import com.gumigames.data.model.response.common.SkillResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SkillService {
    /**
     * 전체 스킬 조회
     */
    @GET("guide/skills")
    suspend fun getAllSkills(): Response<List<SkillResponse>>

    /**
     * 스킬 검색
     */
    @GET("guide/skills/search")
    suspend fun getSearchSkills(@Query("keyword") keyword: String): Response<List<SkillResponse>>
}