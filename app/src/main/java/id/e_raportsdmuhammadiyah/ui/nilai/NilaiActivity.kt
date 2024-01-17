package id.e_raportsdmuhammadiyah.ui.nilai

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import id.e_raportsdmuhammadiyah.MainActivity
import id.e_raportsdmuhammadiyah.databinding.RekapNilaiBinding
import id.e_raportsdmuhammadiyah.model.ResponseDataNilai
import id.e_raportsdmuhammadiyah.ui.nilai.adapter.SectionPagerAdapter
import id.e_raportsdmuhammadiyah.ui.nilai.fragment.WrapNilaiFragment

class NilaiActivity: AppCompatActivity() {
    private var binding: RekapNilaiBinding? = null
    private lateinit var profile : SharedPreferences
    private var nisn = ""
    private var list = ArrayList<ResponseDataNilai>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RekapNilaiBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        profile = getSharedPreferences("Login_session", MODE_PRIVATE)
        nisn = profile.getString("nisn", null).toString()
        binding!!.tvNamaSiswa.text = profile.getString("nama", null)
        binding!!.tvNisn.text = nisn

        binding!!.imgToolbarBtnBack.setOnClickListener {
            finish()
        }

        initComponent()
    }

    private fun initComponent() {
        setupViewPager(binding!!.viewPager)
        binding!!.tabLayout.setupWithViewPager(binding!!.viewPager)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = SectionPagerAdapter(supportFragmentManager)
        // Fragment ketiga (UH)
        val bundleUN = Bundle()
        bundleUN.putString("tipe", "UH")
        val fragUN = WrapNilaiFragment()
        fragUN.arguments = bundleUN
        adapter.addFragment(fragUN, "UH")

        // Fragment pertama (UTS)
        val bundleUTS = Bundle()
        bundleUTS.putString("tipe", "UTS")
        val fragUTS = WrapNilaiFragment()
        fragUTS.arguments = bundleUTS
        adapter.addFragment(fragUTS, "UTS")

        // Fragment kedua (UAS)
        val bundleUAS = Bundle()
        bundleUAS.putString("tipe", "UAS")
        val fragUAS = WrapNilaiFragment()  // Ganti dengan UASFragment jika ada
        fragUAS.arguments = bundleUAS
        adapter.addFragment(fragUAS, "UAS")

        // Fragment keempat (Raport)
        val bundleRaport = Bundle()
        bundleRaport.putString("tipe", "Raport")
        val fragRaport = WrapNilaiFragment()  // Ganti dengan RaportFragment jika ada
        fragRaport.arguments = bundleRaport
        adapter.addFragment(fragRaport, "Raport")

        viewPager.adapter = adapter
    }


    override fun onBackPressed() {
        super.onBackPressed()
    }
}
