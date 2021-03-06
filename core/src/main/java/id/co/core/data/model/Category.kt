package id.co.core.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Category(

    @SerializedName("id")
    @Expose
    var id: String ?= "",

    @SerializedName("name")
    @Expose
    var name: String ?= "",

    @SerializedName("image")
    @Expose
    var image: String ?= "",
)