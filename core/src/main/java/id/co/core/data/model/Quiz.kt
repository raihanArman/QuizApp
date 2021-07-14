package id.co.core.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class Quiz(
    @SerializedName("id")
    @Expose
    var id: String ?= "",

    @SerializedName("name")
    @Expose
    var name: String ?= "",

    @SerializedName("materi_name")
    @Expose
    var category: String ?= "",

    @SerializedName("sum_que")
    @Expose
    var sumQuestions: String ?= "",

    @SerializedName("score")
    @Expose
    var score: String ?= "",
//
//    @SerializedName("tanggal")
//    var tanggal : Date?= Date(),

)