package id.co.core.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Chapter(
    @SerializedName("id")
    @Expose
    var id: String ?= "",

    @SerializedName("path_id")
    @Expose
    var idMateri: String ?= "",

    @SerializedName("name")
    @Expose
    var name: String ?= "",

    @SerializedName("content")
    @Expose
    var content: String ?= "",

    @SerializedName("image")
    @Expose
    var image: String ?= ""
)