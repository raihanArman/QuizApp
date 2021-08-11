package id.co.core.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class History(
    @SerializedName("user_id")
    @Expose
    var userId: String ?= "",
    @SerializedName("quiz_id")
    @Expose
    var quizId: String ?= "",
    @SerializedName("score")
    @Expose
    var score: Int,
    @SerializedName("correct")
    @Expose
    var correct: Int,
    @SerializedName("incorrect")
    @Expose
    var incorrect: Int,
    @SerializedName("no_answered")
    @Expose
    var noAnswered: Int,
    @SerializedName("quiz")
    @Expose
    var quiz: List<Quiz>
): Parcelable