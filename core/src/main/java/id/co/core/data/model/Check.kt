package id.co.core.data.model

import com.google.gson.annotations.SerializedName

data class Check(
    @SerializedName("start_quiz")
    var startQuiz: Boolean
)