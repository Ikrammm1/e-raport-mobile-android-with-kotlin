package id.e_raportsdmuhammadiyah.ui.biodata

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.e_raportsdmuhammadiyah.MainActivity
import id.e_raportsdmuhammadiyah.databinding.BiodataActivityBinding

class BiodataActivity:AppCompatActivity() {
    private lateinit var profile : SharedPreferences
    private var binding: BiodataActivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BiodataActivityBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        profile = getSharedPreferences("Login_session", MODE_PRIVATE)

        var nama = profile.getString("nama", null).toString()
        var kelas = profile.getString("nama_kelas", null).toString()
        var jk = profile.getString("jk", null).toString()
        var alamat = profile.getString("alamat", null)


        binding!!.tvNamaSiswa.text = nama

        if (kelas == ""){
            binding!!.tvKelas.text = "7"
        }else{
            binding!!.tvKelas.text = kelas
        }

        binding!!.tvNisn.text = profile.getString("nisn", null)

        if (jk == ""){
            binding!!.tvJenisKelamin.text = "Laki-laki"
        }else{
            binding!!.tvJenisKelamin.text = jk
        }

        if (alamat == ""){
            binding!!.tvAlamat.text = "Yogyakarta"
        }else{
            binding!!.tvAlamat.text = alamat
        }
        binding!!.tvTempatLahir.text = profile.getString("tmp_lahir", null)
        binding!!.tvAgama.text = profile.getString("agama", null)
        binding!!.tvNamaAyah.text = profile.getString("namaayah", null)
        binding!!.tvNamaIbu.text = profile.getString("namaibu", null)

        binding!!.imgToolbarBtnBack.setOnClickListener {
            finish()
        }
    }

    override fun onBackPressed() {
        finish()
    }
}