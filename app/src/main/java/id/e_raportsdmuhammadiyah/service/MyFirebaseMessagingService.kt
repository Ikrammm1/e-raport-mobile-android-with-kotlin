package id.e_raportsdmuhammadiyah.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import id.e_raportsdmuhammadiyah.MainActivity
import id.e_raportsdmuhammadiyah.R
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(s: String) {
        Log.e("NEW_TOKEN", s)
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        val profile = getSharedPreferences("Login_session", MODE_PRIVATE)
        if (profile.getString("id", null) == null) {
            return
        }

        val type = remoteMessage.data["type"]
        if (type.equals("notif", ignoreCase = true) || type.equals(
                "pembayaran",
                ignoreCase = true
            )
        ) {
            val params = remoteMessage.data
            val NOTIFICATION_CHANNEL_ID = "E-RAPORT"
            val pattern = longArrayOf(0, 1000, 500, 1000)
            val mNotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val notificationChannel = NotificationChannel(
                    NOTIFICATION_CHANNEL_ID, "E-Raport Notifications",
                    NotificationManager.IMPORTANCE_HIGH
                )
                notificationChannel.description = ""
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.RED
                notificationChannel.vibrationPattern = pattern
                notificationChannel.enableVibration(true)
                mNotificationManager.createNotificationChannel(notificationChannel)
            }

            // to diaplay notification in DND Mode
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = mNotificationManager.getNotificationChannel(NOTIFICATION_CHANNEL_ID)
                channel.canBypassDnd()
            }
            val notificationBuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            val notif_type = remoteMessage.data["notif_type"]
            val title = remoteMessage.data["title"]
            val description = remoteMessage.data["description"]
            //String image = remoteMessage.getData().get("image");
            val notificationIntent = Intent(applicationContext, MainActivity::class.java)
            notificationIntent.flags =
                Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            val pendingFlags: Int
            pendingFlags = if (Build.VERSION.SDK_INT >= 23) {
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            } else {
                PendingIntent.FLAG_UPDATE_CURRENT
            }
            val contentIntent =
                PendingIntent.getActivity(applicationContext, 10, notificationIntent, pendingFlags)
            notificationBuilder.setAutoCancel(true)
                .setColor(ContextCompat.getColor(this, R.color.custom_color_primary))
                .setContentTitle(title)
                .setContentText(description)
                .setDefaults(Notification.DEFAULT_ALL or Notification.DEFAULT_SOUND)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.logo)
                .setAutoCancel(true)
            val notificationId = System.currentTimeMillis().toInt()
            mNotificationManager.notify(notificationId, notificationBuilder.build())
//            if (notif_type.equals("pembayaran", ignoreCase = true)) {
//                val broadcaster = LocalBroadcastManager.getInstance(
//                    baseContext
//                )
//                val intent = Intent(REQUEST_ACCEPT)
//                Log.d("MESSAGING", "where??")
//                broadcaster.sendBroadcast(intent)
//            }
        }
    }

    companion object {
        var REQUEST_ACCEPT = "100"
    }
}