package id.co.core.data.network

import id.co.core.data.model.*
import id.co.core.data.response.ResponseData
import retrofit2.http.*

interface ApiService {
    @GET("tampil_user_by_id.php")
    suspend fun getUsersById(
        @Query("id_user") id: String
    ): ResponseData<Users>

    @GET("tampil_materi.php")
    suspend fun getMateri(): ResponseData<List<Materi>>

    @GET("tampil_category.php")
    suspend fun getCategory(): ResponseData<List<Category>>

    @GET("quiz_search.php")
    suspend fun getQuizSearch(
        @Query("cari") search: String
    ): ResponseData<List<Quiz>>

    @FormUrlEncoded
    @POST("login.php")
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): ResponseData<Users>

    @FormUrlEncoded
    @POST("register.php")
    suspend fun registerUser(
        @Field("email") email: String,
        @Field("name") name: String,
        @Field("password") password: String
    ): ResponseData<Users>

    @GET("tampil_bab.php")
    suspend fun getBabByMateri(
        @Query("id_materi") id: String
    ): ResponseData<List<Chapter>>


}