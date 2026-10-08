package com.examo.android.data
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
interface ApiService{
 @POST("Auth/login") suspend fun login(@Body r:LoginRequest):AuthResponse
 @POST("Auth/register") suspend fun register(@Body r:RegisterRequest):AuthResponse
 @GET("Exams") suspend fun exams():List<Exam>
 @GET("ExamForms") suspend fun forms():List<ExamForm>
 @GET("Preparations") suspend fun preparations():List<Preparation>
 @GET("Subjects") suspend fun subjects():List<Subject>
 @GET("Schedules") suspend fun schedules():List<Schedule>
}
