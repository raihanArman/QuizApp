package id.co.core.data.network

import id.co.core.data.model.Category
import id.co.core.data.model.Materi
import id.co.core.data.model.Users
import id.co.core.data.response.ResponseData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("tampil_user_by_id.php")
    suspend fun getUsersById(
        @Query("id_user") id: String
    ): ResponseData<Users>

    @GET("tampil_materi.php")
    suspend fun getMateri(): ResponseData<List<Materi>>

    @GET("tampil_category.php")
    suspend fun getCategory(): ResponseData<List<Category>>

}