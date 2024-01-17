package id.e_raportsdmuhammadiyah

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.FirebaseApp
import id.e_raportsdmuhammadiyah.auth.LoginActivity
import id.e_raportsdmuhammadiyah.databinding.ActivityMainBinding
import id.e_raportsdmuhammadiyah.ui.biodata.BiodataActivity
import id.e_raportsdmuhammadiyah.ui.info_sekolah.InfoSekolahActivity
import id.e_raportsdmuhammadiyah.ui.nilai.NilaiActivity

class MainActivity : AppCompatActivity() {
    private var binding : ActivityMainBinding? = null
    private lateinit var profile : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        profile = getSharedPreferences("Login_session", MODE_PRIVATE)
        if (profile.getString("id", null) == null) {
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

        binding!!.btnNilai.setOnClickListener {
            startActivity(Intent(this@MainActivity, NilaiActivity::class.java))
        }

        binding!!.btnInfoSekolah.setOnClickListener {
            startActivity(Intent(this@MainActivity, InfoSekolahActivity::class.java))
        }

        binding!!.btnBiodata.setOnClickListener {
            startActivity(Intent(this@MainActivity, BiodataActivity::class.java))
        }

        binding!!.btnLogout.setOnClickListener {
            showLogoutConfirmationDialog()
        }
    }

    private fun showLogoutConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Konfirmasi Logout")
        builder.setMessage("Apakah Anda yakin ingin logout?")

        builder.setPositiveButton("Ya") { dialog, which ->
            val sharedPreferences = getSharedPreferences("Login_session", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()

            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        builder.setNegativeButton("Tidak") { dialog, which ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this).apply {
            setTitle("Silahkan Konfirmasi .")
            setMessage("Apakah Kamu ingin keluar dari aplikasi?")

            setPositiveButton("Yes") { _, _ ->
                Toast.makeText(this@MainActivity, "Terima Kasih",
                    Toast.LENGTH_LONG).show()
                super.onBackPressed()
            }

            setNegativeButton("No"){_, _ ->
            }
            setCancelable(true)
        }.create().show()
    }
}