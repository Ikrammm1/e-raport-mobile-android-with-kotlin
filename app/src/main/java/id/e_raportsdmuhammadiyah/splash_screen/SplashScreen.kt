package id.e_raportsdmuhammadiyah.splash_screen

import android.app.ActionBar.LayoutParams
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton
import com.google.firebase.FirebaseApp
import id.e_raportsdmuhammadiyah.MainActivity
import id.e_raportsdmuhammadiyah.R
import id.e_raportsdmuhammadiyah.auth.LoginActivity
import id.e_raportsdmuhammadiyah.splash_screen.adapter.SplashScreenItemAdapter

class SplashScreen : AppCompatActivity() {
    private lateinit var splashScreenItemAdapter: SplashScreenItemAdapter
    private lateinit var indicatorsContainer: LinearLayout
    private lateinit var profile: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        setSplashScreenItem()
        setupIndicators()
        setCurrentIndicator(0)

    }

    override fun onBackPressed() {
        AlertDialog.Builder(this).apply {
            setTitle("Silahkan Konfirmasi .")
            setMessage("Apakah Kamu ingin keluar dari aplikasi?")

            setPositiveButton("Yes") { _, _ ->
                // if user press yes, then finish the current activity
                super.onBackPressed()
            }

            setNegativeButton("No") { _, _ ->
                // if user press no, then return the activity
                Toast.makeText(
                    this@SplashScreen, "Terima Kasih",
                    Toast.LENGTH_LONG
                ).show()
            }
            setCancelable(true)
        }.create().show()
    }

    private fun setSplashScreenItem() {
        splashScreenItemAdapter = SplashScreenItemAdapter(
            listOf(
                SplashScreenItem(
                    splashImage = R.drawable.splash1,
                    "Lihat Rekap Nilai Kamu",
                    "Kamu dapat melihat rekap nilai setiap semester"
                ),
                SplashScreenItem(
                    splashImage = R.drawable.splash2,
                    "Dapatkan Informasi Sekolah",
                    "Kamu dapat melihat informasi terkait sekolah"
                )

            )
        )
        val splashViewPager = findViewById<ViewPager2>(R.id.spalshViewPager)
        splashViewPager.adapter = splashScreenItemAdapter
        splashViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        (splashViewPager.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER
        findViewById<ImageView>(R.id.nextImg).setOnClickListener {
            if (splashViewPager.currentItem + 1 < splashScreenItemAdapter.itemCount) {
                splashViewPager.currentItem += 1
            } else {
                navigateToLoginAcitivity()
            }
        }

        findViewById<TextView>(R.id.textSkip).setOnClickListener {
            navigateToLoginAcitivity()
        }
        findViewById<MaterialButton>(R.id.buttonMulai).setOnClickListener {
            navigateToLoginAcitivity()
        }
    }

    private fun setupIndicators() {
        indicatorsContainer = findViewById(R.id.indicatorsContainer)
        val indicators = arrayOfNulls<ImageView>(splashScreenItemAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(6, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
                it.layoutParams = layoutParams
                indicatorsContainer.addView(it)
            }
        }
    }

    private fun navigateToLoginAcitivity() {
        profile = getSharedPreferences("Login_session", MODE_PRIVATE)
        if (profile.getString("id", null) != null) {
            startActivity(Intent(this@SplashScreen, MainActivity::class.java))
            finish()
        } else {
            startActivity(Intent(this@SplashScreen, LoginActivity::class.java))
            finish()
        }
    }

    private fun setCurrentIndicator(position: Int) {
        val childCount = indicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorsContainer.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active_background
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
            }
        }
    }
}