package id.co.core.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import id.co.core.data.model.Users

data class ResponseLogin(
    @Expose
    @SerializedName("access_token")
    val accessToken: String,

    @Expose
    @SerializedName("token_type")
    val tokenType: String,

    @Expose
    @SerializedName("user")
    val user: Users
)