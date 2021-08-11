package id.co.core.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Quiz(
    @SerializedName("id")
    @Expose
    var id: String ?= "",

    @SerializedName("name")
    @Expose
    var name: String ?= "",

    @SerializedName("path_name")
    @Expose
    var category: String ?= "",

    @SerializedName("sum_que")
    @Expose
    var sumQuestions: String ?= "",

    @SerializedName("score")
    @Expose
    var score: String ?= ""
//
//    @SerializedName("tanggal")
//    var tanggal : Date?= Date(),

): Parcelable