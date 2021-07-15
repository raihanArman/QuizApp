package id.co.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.airbnb.deeplinkdispatch.DeepLink
import id.co.core.util.AppLink
import id.co.quizapp.databinding.ActivityMainBinding


@DeepLink(AppLink.Main.MAIN_LINK)
class MainActivity : AppCompatActivity() {

    private lateinit var dataBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        dataBinding.bottomNavigationView.setupWithNavController(Navigation.findNavController(this, R.id.navHost))

    }
}