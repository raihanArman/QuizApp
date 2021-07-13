package id.co.core.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Materi (

    @SerializedName("id")
    @Expose
    var id: String ?= "",

    @SerializedName("name")
    @Expose
    var materi: String ?= "",

    @SerializedName("category")
    @Expose
    var category: String ?= "",

    @SerializedName("image")
    var image: String ?= "",

    @SerializedName("student")
    var student: String ?= ""
)