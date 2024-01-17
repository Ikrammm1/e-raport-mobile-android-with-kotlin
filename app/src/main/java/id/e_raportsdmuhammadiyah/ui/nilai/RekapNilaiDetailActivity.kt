package id.e_raportsdmuhammadiyah.ui.nilai

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.e_raportsdmuhammadiyah.MainActivity
import id.e_raportsdmuhammadiyah.databinding.RekapNilaiDetailBinding

class RekapNilaiDetailActivity: AppCompatActivity() {
    private var binding : RekapNilaiDetailBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RekapNilaiDetailBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        binding!!.tvNamaMapel.text = intent.getStringExtra("nama_mapel")
        binding!!.tvKelasRekap.text = intent.getStringExtra("kelas")
        binding!!.tvSemester.text = intent.getStringExtra("semester")
        binding!!.tvTahunAjaran.text = intent.getStringExtra("tahun_ajaran")
        binding!!.tvNilaiUmum.text = intent.getStringExtra("nilai_umum")
        binding!!.tvNilaiAkhir.text = intent.getStringExtra("nilai_akhir")
        binding!!.tvEkstra.text = intent.getStringExtra("kesopanan")
        binding!!.tvNilaiEkstra.text = intent.getStringExtra("kebersihan")
        binding!!.tvNilaiKedisiplinan.text = intent.getStringExtra("kedisiplinan")
        binding!!.tvNilaiHisbulwathan.text = intent.getStringExtra("hw")
        binding!!.tvNilaiTapaksuci.text = intent.getStringExtra("tapak_suci")
        binding!!.tvNilaiMarchingband.text = intent.getStringExtra("marching_band")


        if(binding!!.tvEkstra.text.isEmpty()){
            binding!!.tvEkstra.text = "Nilai tidak ada untuk UH/UTS/UAS"
        }
        if(binding!!.tvNilaiEkstra.text.isEmpty()){
            binding!!.tvNilaiEkstra.text = "Nilai tidak ada untuk UH/UTS/UAS"
        }
        if(binding!!.tvNilaiKedisiplinan.text.isEmpty()){
            binding!!.tvNilaiKedisiplinan.text = "Nilai tidak ada untuk UH/UTS/UAS"
        }
        if(binding!!.tvNilaiHisbulwathan.text.isEmpty()){
            binding!!.tvNilaiHisbulwathan.text = "Nilai tidak ada untuk UH/UTS/UAS"
        }
        if(binding!!.tvNilaiTapaksuci.text.isEmpty()){
            binding!!.tvNilaiTapaksuci.text = "Nilai tidak ada untuk UH/UTS/UAS"
        }
        if(binding!!.tvNilaiMarchingband.text.isEmpty()){
            binding!!.tvNilaiMarchingband.text = "Nilai tidak ada untuk UH/UTS/UAS"
        }

        binding!!.imgToolbarBtnBack.setOnClickListener {
            finish()
        }
    }
}
