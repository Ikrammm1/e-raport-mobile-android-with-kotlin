package id.e_raportsdmuhammadiyah.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import id.e_raportsdmuhammadiyah.MainActivity
import id.e_raportsdmuhammadiyah.databinding.LoginActivityBinding
import id.e_raportsdmuhammadiyah.model.ResponseLogin
import id.e_raportsdmuhammadiyah.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity: AppCompatActivity() {
    private var binding: LoginActivityBinding? = null
    private var nisn : String = ""
    private lateinit var profile : SharedPreferences
    private var tokenMobile = ""
    lateinit var  mContext : Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        mContext = this@LoginActivity

        getToken()

        profile = getSharedPreferences("Login_session", MODE_PRIVATE)
        if (profile.getString("id", null)!=null){
            login()
        }else{
            Toast.makeText(this@LoginActivity, "Session Habis", Toast.LENGTH_SHORT).show()

        }

        binding!!.btnLogin.setOnClickListener{
            nisn = binding!!.etNisn.text.toString()
//            pass = binding!!.etPassword.text.toString()

            when{
                nisn == "" -> {
                    binding!!.etNisn.error = "Username Tidak Boleh Kosong"
                }
//                pass == "" -> {
//                    binding!!.etPassword.error = "Password Tidak Boleh Kosong"
//                }
                else -> {
                    binding!!.loading.visibility = View.VISIBLE
                    getData()
                }
            }
        }
//        binding!!.btnRegis.setBackgroundColor(Color.parseColor("#FFFFFF"))
    }

    fun getToken() {
        FirebaseApp.initializeApp(mContext)
        FirebaseMessaging.getInstance().isAutoInitEnabled = true
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    // Handle the error
                    val exception = task.exception
                    if (exception != null) {
                        Log.e(
                            "Login",
                            "Fetching FCM registration token failed: " + exception.message
                        )
                    } else {
                        Log.e("Login", "Fetching FCM registration token failed")
                    }
                    return@OnCompleteListener
                }
                // Token generation was successful
                val token = task.result
                tokenMobile = token
                Log.d("Login", "FCM Registration Token: $token")
                // Handle the token (e.g., send it to your server)
            })
    }


    private fun getData(){
        val api = RetrofitClient().instance
        api.login(nisn, tokenMobile).enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                if (response.isSuccessful){
                    if (response.body()?.response == true){
                            Log.e("responsess", response.body().toString())
                            binding!!.loading.visibility = View.GONE
                            getSharedPreferences("Login_session", MODE_PRIVATE)
                                .edit()
                                .putString("id", response.body()?.payload?.id)
                                .putString("nisn", response.body()?.payload?.nisn)
                                .putString("nama", response.body()?.payload?.nama)
                                .putString("jk", response.body()?.payload?.jk)
                                .putString("tmp_lahir", response.body()?.payload?.tmp_lahir)
                                .putString("agama", response.body()?.payload?.agama)
                                .putString("alamat", response.body()?.payload?.alamat)
                                .putString("namaayah", response.body()?.payload?.namaayah)
                                .putString("namaibu", response.body()?.payload?.namaibu)
                                .putString("nama_kelas", response.body()?.payload?.nama_kelas)
                                .apply()
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            finish()
                    }else{
                        binding!!.loading.visibility = View.GONE
                        Toast.makeText(this@LoginActivity,
                            "Login Gagal, Periksa Kembali username dan Password",
                            Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(
                        this@LoginActivity,
                        "Login Gagal, Kesalahan Terjadi",
                        Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                Log.e("Pesan Error", "${t.message}")
            }


        })
    }

    private fun login() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this).apply {
            setTitle("Silahkan Konfirmasi .")
            setMessage("Apakah Kamu ingin keluar dari aplikasi?")

            setPositiveButton("Yes") { _, _ ->
                // if user press yes, then finish the current activity
                super.onBackPressed()
            }

            setNegativeButton("No"){_, _ ->
                // if user press no, then return the activity
                Toast.makeText(this@LoginActivity, "Terima Kasih",
                    Toast.LENGTH_LONG).show()
            }
            setCancelable(true)
        }.create().show()
        super.onBackPressed()
    }
}