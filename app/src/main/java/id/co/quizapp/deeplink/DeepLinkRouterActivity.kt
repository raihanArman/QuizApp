package id.co.quizapp.deeplink

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.deeplinkdispatch.DeepLinkHandler
import id.co.login.module.LoginDeepLink
import id.co.login.module.LoginDeepLinkLoader


@DeepLinkHandler(
    AppDeepLinkModule::class,
    LoginDeepLink::class,
    MainDeepLink::class
)
class DeepLinkRouterActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val deepLinkDelegate = DeepLinkDelegate(
            AppDeepLinkModuleLoader(),
            LoginDeepLinkLoader(),
            MainDeepLinkLoader()
        )
        deepLinkDelegate.dispatchFrom(this)
        finish()
    }
}