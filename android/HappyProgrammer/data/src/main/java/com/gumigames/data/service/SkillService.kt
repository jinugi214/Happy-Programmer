package com.gumigames.data.service

import com.gumigames.data.model.response.bookmark.BookmarkResponse
import com.gumigames.data.model.response.common.SkillResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
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
    suspend fun searchSkills(@Query("keyword") keyword: String): Response<List<SkillResponse>>
    /**
     * 스킬 즐겨찾기 > 토글
     */
    @POST("guide/skills/favorite/{skillId}")
    suspend fun toggleBookmarkSkill(@Path("skillId") skillId: Int): Response<BookmarkResponse>
}