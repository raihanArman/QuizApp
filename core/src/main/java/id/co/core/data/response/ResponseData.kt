package id.co.core.data.response

data class ResponseData<T> (
    val status: String,
    val message: String,
    val data: T
)