package id.co.core.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Users(
    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("name")
    @Expose
    var name: String ?= "",

    @SerializedName("email")
    @Expose
    var email: String ?= "",

    @SerializedName("status")
    @Expose
    var status: String ?= "",

    @SerializedName("last_score")
    @Expose
    var lastScore: String ?= "",

    @SerializedName("total_quiz")
    @Expose
    var sumQuiz: String ?= "",

)