package id.co.core.data.network

import id.co.core.data.model.*
import id.co.core.data.response.ResponseData
import id.co.core.data.response.ResponseLogin
import id.co.core.data.response.Wrapper
import retrofit2.http.*

interface ApiService {
    @GET("user")
    suspend fun getUsersById(
        @Header("Authorization") token: String
    ): Wrapper<Users>

    @GET("path/popular")
    suspend fun getPopularPath(
        @Header("Authorization") token: String
    ): Wrapper<List<Materi>>

    @GET("path/category/{id}")
    suspend fun getPathByCategory(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Wrapper<List<Materi>>

    @GET("path")
    suspend fun getMateri(
        @Header("Authorization") token: String
    ): Wrapper<List<Materi>>

    @GET("category")
    suspend fun getCategory(
        @Header("Authorization") token: String
    ): Wrapper<List<Category>>

    @GET("quiz/search")
    suspend fun getQuizSearch(
        @Header("Authorization") token: String,
        @Query("search") search: String
    ): Wrapper<List<Quiz>>

    @GET("quiz/path/{id}")
    suspend fun getQuizByPath(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Wrapper<List<Quiz>>

    @FormUrlEncoded
    @POST("login")
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Wrapper<ResponseLogin>

    @POST("logout")
    suspend fun logoutUser(
        @Header("Authorization") token: String,
    ): Wrapper<Boolean>

    @FormUrlEncoded
    @POST("register")
    suspend fun registerUser(
        @Field("email") email: String,
        @Field("name") name: String,
        @Field("password") password: String
    ): Wrapper<ResponseLogin>

    @GET("chapter/path/{id}")
    suspend fun getBabByMateri(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Wrapper<List<Chapter>>

    @GET("chapter/{id}")
    suspend fun getContentById(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Wrapper<Chapter>

    @GET("question/{id}")
    suspend fun getQuestion(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Wrapper<List<Question>>

    @FormUrlEncoded
    @POST("history/add")
    suspend fun addHistory(
        @Header("Authorization") token: String,
        @Field("user_id") userId: String,
        @Field("quiz_id") quizId: String,
        @Field("score") score: Int,
        @Field("correct") correct: Int,
        @Field("incorrect") incorrect: Int,
        @Field("not_answered") noAnswered: Int
    ): Wrapper<History>

    @GET("history/user/{id}")
    suspend fun getHistoryByUser(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Wrapper<List<History>>

    @GET("quiz/check")
    suspend fun getQuizCheck(
        @Header("Authorization") token: String,
        @Query("user_id") userId: String,
        @Query("quiz_id") quizId: String,
    ): Wrapper<Check>

}