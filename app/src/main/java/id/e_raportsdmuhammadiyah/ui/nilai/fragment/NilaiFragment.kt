package id.e_raportsdmuhammadiyah.ui.nilai.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.e_raportsdmuhammadiyah.databinding.FragmentNilaiBinding
import id.e_raportsdmuhammadiyah.model.ResponseDataNilai
import id.e_raportsdmuhammadiyah.network.RetrofitClient
import id.e_raportsdmuhammadiyah.ui.nilai.adapter.DataRekapNilaiAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NilaiFragment : Fragment() {
    private var binding: FragmentNilaiBinding? = null
    private lateinit var profile: SharedPreferences
    private var id = ""
    private var list = ArrayList<ResponseDataNilai>()
    var kelas = ""
    var tipe = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        kelas = arguments?.getString("kelas").toString()
        tipe = arguments?.getString("tipe").toString()

        binding = FragmentNilaiBinding.inflate(layoutInflater)
        profile =
            requireActivity().getSharedPreferences("Login_session", AppCompatActivity.MODE_PRIVATE)
        id = profile.getString("id", null).toString()

        binding!!.rvNilai.setHasFixedSize(true)
        binding!!.rvNilai.layoutManager = LinearLayoutManager(requireContext())

        binding!!.spSemester.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                getData()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        getData()

        return binding!!.root
    }

    fun getData(){
        val api = RetrofitClient().instance
        list.clear()
        api.get_data_nilai(id, tipe, (binding!!.spSemester.selectedItemPosition + 1).toString(), kelas).enqueue(object : Callback<ArrayList<ResponseDataNilai>> {
            override fun onResponse(
                call: Call<ArrayList<ResponseDataNilai>>,
                response: Response<ArrayList<ResponseDataNilai>>
            ) {
                response.body()?.let { list.addAll(it) }
                val adapter = DataRekapNilaiAdapter(requireContext(), list)
                binding!!.rvNilai.adapter = adapter

                if(list.size == 0){
                    binding!!.tvNotFound.visibility = View.VISIBLE
                }else {
                    binding!!.tvNotFound.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<ArrayList<ResponseDataNilai>>, t: Throwable) {
                Toast.makeText(requireContext(), "${t.message}", Toast.LENGTH_LONG).show()
                Log.e("error", "$t")
            }

        })
    }
}