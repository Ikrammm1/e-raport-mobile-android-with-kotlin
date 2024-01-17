package id.e_raportsdmuhammadiyah.ui.info_sekolah

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import id.e_raportsdmuhammadiyah.MainActivity
import id.e_raportsdmuhammadiyah.databinding.InfoSekolahBinding
import id.e_raportsdmuhammadiyah.model.ResponseDataInfoSekolah
import id.e_raportsdmuhammadiyah.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoSekolahActivity: AppCompatActivity() {
    private var binding: InfoSekolahBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = InfoSekolahBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val api = RetrofitClient().instance
        api.get_profile_sekolah().enqueue(object : Callback<ResponseDataInfoSekolah>{
            override fun onResponse(
                call: Call<ResponseDataInfoSekolah>,
                response: Response<ResponseDataInfoSekolah>
            ) {
                if (response.isSuccessful){
                    binding!!.tvNamaSekolah.text = response.body()?.nm_sekolah
                    binding!!.tvNPSN.text = response.body()?.npsn
                    binding!!.tvJenjang.text = response.body()?.jenjang
                    binding!!.tvAkreditasi.text = response.body()?.akreditasi
                    binding!!.tvAlamatSekolah.text = response.body()?.alamat
                    binding!!.tvNamaKepsek.text = response.body()?.kepsek_nama
                    binding!!.tvNIPKepsek.text = response.body()?.kepsek_nip
                    binding!!.tvPangkatKepsek.text = response.body()?.kepsek_pangkat
                    binding!!.tvJabatanKepsek.text = response.body()?.kepsek_jabatan
                }
                else{
                    binding!!.tvNamaSekolah.text = "SD Muhammadiyah 01 Kencong"
                    binding!!.tvNPSN.text = "20525018"
                    binding!!.tvJenjang.text = "SD"
                    binding!!.tvAkreditasi.text = "A"
                    binding!!.tvAlamatSekolah.text = "Jl. Diponegoro No.164, Kamaran, Kencong, Kec. Kencong, Kabupaten Jember, Jawa Timur 68167"
                    binding!!.tvNamaKepsek.text = "Boris Damar Nadru,S.Pd.SD."
                    binding!!.tvNIPKepsek.text = "19711030 1994101 001"
                    binding!!.tvPangkatKepsek.text = "Pembina/ IVa"
                    binding!!.tvJabatanKepsek.text = "Guru ahli madya"
                }
            }

            override fun onFailure(call: Call<ResponseDataInfoSekolah>, t: Throwable) {
                Log.e("Error", "$t")
            }

        })

        binding!!.imgToolbarBtnBack.setOnClickListener {
            finish()
        }
    }

    override fun onBackPressed() {
        finish()
    }
}