package id.e_raportsdmuhammadiyah.ui.nilai.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import id.e_raportsdmuhammadiyah.R
import id.e_raportsdmuhammadiyah.model.ResponseDataNilai
import id.e_raportsdmuhammadiyah.ui.nilai.RekapNilaiDetailActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataRekapNilaiAdapter(val mCtx: Context, val responseDataNilai: ArrayList<ResponseDataNilai>):
    RecyclerView.Adapter<DataRekapNilaiAdapter.RekapNilaiViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RekapNilaiViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.nilai_item,parent,false)
        return RekapNilaiViewHolder(view)
    }

    override fun onBindViewHolder(holder: RekapNilaiViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val rekap: ResponseDataNilai = responseDataNilai[position]
        holder.btnDetail.setOnClickListener {
            val intent = Intent(mCtx, RekapNilaiDetailActivity::class.java)
            intent.putExtra("nama_mapel", rekap.nama_mapel)
            intent.putExtra("semester", rekap.id_semester)
            intent.putExtra("tahun_ajaran", rekap.tahun_ajar)
            intent.putExtra("nilai_umum", rekap.nilai_umum)
            intent.putExtra("nilai_akhir", rekap.nilai_huruf)
            intent.putExtra("kelas", rekap.nama_kelas)
            intent.putExtra("kesopanan", rekap.kesopanan)
            intent.putExtra("kebersihan", rekap.kebersihan)
            intent.putExtra("kedisiplinan", rekap.kedisiplinan)
            intent.putExtra("hw", rekap.hw)
            intent.putExtra("tapak_suci", rekap.tapak_suci)
            intent.putExtra("marching_band", rekap.marching_band)

            mCtx.startActivity(intent)
        }
        return holder.bindView(responseDataNilai[position])
    }

    override fun getItemCount(): Int {
        return responseDataNilai.size
    }

    class RekapNilaiViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val nama_mapel: TextView = itemView.findViewById(R.id.tv_nama_mapel)
        private val semester: TextView = itemView.findViewById(R.id.tv_semester)
        private val kelas: TextView = itemView.findViewById(R.id.tv_kelas)
        private val nilai_umum: TextView = itemView.findViewById(R.id.tv_nilai_umum)
        private val nilai_huruf: TextView = itemView.findViewById(R.id.tv_nilai_huruf)
        internal val btnDetail: LinearLayout = itemView.findViewById(R.id.nilai_detail)

        fun bindView(responseDataNilai: ResponseDataNilai){
            nama_mapel.text = responseDataNilai.nama_mapel
            semester.text = responseDataNilai.id_semester
            kelas.text = responseDataNilai.nama_kelas
            nilai_umum.text = responseDataNilai.nilai_umum
            nilai_huruf.text = responseDataNilai.nilai_huruf
        }

    }

}