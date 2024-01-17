package id.e_raportsdmuhammadiyah

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import id.e_raportsdmuhammadiyah.auth.LoginActivity
import id.e_raportsdmuhammadiyah.splash_screen.SplashScreen

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT: Long = 2000
    private lateinit var profile : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        // Using a Handler to delay the start of the next activity
        Handler().postDelayed({
            navigateToAcitivity()
        }, SPLASH_TIME_OUT)
    }

    private fun navigateToAcitivity() {
        profile = getSharedPreferences("Login_session", MODE_PRIVATE)
        if (profile.getString("id", null) != null) {
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        } else {
            startActivity(Intent(this@SplashActivity, SplashScreen::class.java))
            finish()
        }
    }
}