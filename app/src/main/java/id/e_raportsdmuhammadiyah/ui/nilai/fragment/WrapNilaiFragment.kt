package id.e_raportsdmuhammadiyah.ui.nilai.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import id.e_raportsdmuhammadiyah.databinding.FragmentWrapNilaiBinding
import id.e_raportsdmuhammadiyah.ui.nilai.adapter.SectionPagerAdapter

class WrapNilaiFragment : Fragment() {
    private var binding: FragmentWrapNilaiBinding? = null
    private lateinit var profile: SharedPreferences
    var tipe = "0"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tipe = arguments?.getString("tipe").toString()
        binding = FragmentWrapNilaiBinding.inflate(layoutInflater)

        profile =
            requireActivity().getSharedPreferences("Login_session", AppCompatActivity.MODE_PRIVATE)

        initComponent()

        return binding!!.root
    }

    private fun initComponent() {
        setupViewPager(binding!!.viewPager)
        binding!!.tabLayout.setupWithViewPager(binding!!.viewPager)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = SectionPagerAdapter(childFragmentManager)

        // Fragment untuk Kelas 1
        val bundleKelas1 = Bundle()
        bundleKelas1.putString("kelas", "1")
        bundleKelas1.putString("tipe", tipe)
        val fragKelas1 = NilaiFragment()
        fragKelas1.arguments = bundleKelas1
        adapter.addFragment(fragKelas1, "Kel-1")

        // Fragment untuk Kelas 2
        val bundleKelas2 = Bundle()
        bundleKelas2.putString("kelas", "2")
        bundleKelas2.putString("tipe", tipe)
        val fragKelas2 = NilaiFragment()
        fragKelas2.arguments = bundleKelas2
        adapter.addFragment(fragKelas2, "Kel-2")

        // Fragment untuk Kelas 3
        val bundleKelas3 = Bundle()
        bundleKelas3.putString("kelas", "3")
        bundleKelas3.putString("tipe", tipe)
        val fragKelas3 = NilaiFragment()
        fragKelas3.arguments = bundleKelas3
        adapter.addFragment(fragKelas3, "Kel-3")

        // Fragment untuk Kelas 4
        val bundleKelas4 = Bundle()
        bundleKelas4.putString("kelas", "4")
        bundleKelas4.putString("tipe", tipe)
        val fragKelas4 = NilaiFragment()
        fragKelas4.arguments = bundleKelas4
        adapter.addFragment(fragKelas4, "Kel-4")

        // Fragment untuk Kelas 5
        val bundleKelas5 = Bundle()
        bundleKelas5.putString("kelas", "5")
        bundleKelas5.putString("tipe", tipe)
        val fragKelas5 = NilaiFragment()
        fragKelas5.arguments = bundleKelas5
        adapter.addFragment(fragKelas5, "Kel-5")

        // Fragment untuk Kelas 6
        val bundleKelas6 = Bundle()
        bundleKelas6.putString("kelas", "6")
        bundleKelas6.putString("tipe", tipe)
        val fragKelas6 = NilaiFragment()
        fragKelas6.arguments = bundleKelas6
        adapter.addFragment(fragKelas6, "Kel-6")

        viewPager.adapter = adapter
    }

}