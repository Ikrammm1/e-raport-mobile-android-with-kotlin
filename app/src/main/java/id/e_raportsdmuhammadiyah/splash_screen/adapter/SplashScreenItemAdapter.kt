package id.e_raportsdmuhammadiyah.splash_screen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.e_raportsdmuhammadiyah.R

import id.e_raportsdmuhammadiyah.splash_screen.SplashScreenItem

class SplashScreenItemAdapter(private val splashScreenItem: List<SplashScreenItem>) :
    RecyclerView.Adapter<SplashScreenItemAdapter.SplashScreenItemViewHolder>() {
    inner class SplashScreenItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageSplashScreen = view.findViewById<ImageView>(R.id.imageSplash)
        private val textTitle = view.findViewById<TextView>(R.id.textTitle)
        private val textDeskiprisi = view.findViewById<TextView>(R.id.textDeskripsi)

        fun bind(splashScreenItem: SplashScreenItem) {
            imageSplashScreen.setImageResource(splashScreenItem.splashImage)
            textTitle.text = splashScreenItem.title
            textDeskiprisi.text = splashScreenItem.deskripsi
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SplashScreenItemViewHolder {
        return SplashScreenItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.splash_screen_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SplashScreenItemViewHolder, position: Int) {
        holder.bind(splashScreenItem[position])
    }

    override fun getItemCount(): Int {
        return splashScreenItem.size
    }
}