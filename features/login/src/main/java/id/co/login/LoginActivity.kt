package id.co.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.airbnb.deeplinkdispatch.DeepLink
import id.co.core.util.AppLink
import id.co.login.databinding.ActivityLoginBinding

@DeepLink(AppLink.Login.LOGIN_LINK)
class LoginActivity : AppCompatActivity() {

    lateinit var dataBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        setFragment(SignInFragment())

    }

    private fun setFragment(fragment: Fragment){
        val fm = supportFragmentManager.beginTransaction()
        fm.replace(dataBinding.frameLogin.id, fragment).commit()
    }

}