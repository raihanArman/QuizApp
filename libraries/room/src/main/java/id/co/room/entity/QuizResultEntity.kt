package id.co.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "quiz_result")
data class QuizResultEntity(
    @PrimaryKey
    @ColumnInfo(name = "number")
    var number: Int,

    @ColumnInfo(name = "answer")
    var answer: String

): Parcelable