package id.co.core.util

object Constant {
    const val BASE_URL = "http://192.168.1.68/api/"
    const val BASE_URL_IMAGE = "http://192.168.1.68:8000/"
}

object AppLink {
    object Login{
        const val LOGIN_LINK = "quiz://login"
    }
    object Main{
        const val MAIN_LINK = "quiz://main"
    }
    object Quiz{
        const val QUIZ_LINK = "quiz://quiz/{quiz_id}"
        const val PARAM_QUIZ_ID = "quiz_id"
    }
}