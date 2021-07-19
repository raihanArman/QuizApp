package id.co.core.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Question(
    @SerializedName("id")
    @Expose
    var id: String ?= "",

    @SerializedName("id_quiz")
    @Expose
    var idQuiz: String ?= "",

    @SerializedName("question")
    @Expose
    var question: String ?= "",

    @SerializedName("a")
    @Expose
    var opsiA: String ?= "",

    @SerializedName("b")
    @Expose
    var opsiB: String ?= "",

    @SerializedName("c")
    @Expose
    var opsiC: String ?= "",

    @SerializedName("d")
    @Expose
    var opsiD: String ?= "",

    @SerializedName("answer")
    @Expose
    var answer: String ?= "",
)