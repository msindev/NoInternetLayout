package net.penguincoders.nointernetlayout

import android.net.ConnectivityManager
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var internetLayout: RelativeLayout
    private lateinit var noInternetLayout: RelativeLayout

    private lateinit var tryAgainButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        internetLayout = findViewById(R.id.internetLayout)
        noInternetLayout = findViewById(R.id.noInternetLayout)
        tryAgainButton = findViewById(R.id.try_again_button)

        drawLayout()

        tryAgainButton.setOnClickListener {
            drawLayout()
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)

        return (capabilities != null && capabilities.hasCapability(NET_CAPABILITY_INTERNET))

    }

    private fun drawLayout() {
        if (isNetworkAvailable()) {
            internetLayout.visibility = VISIBLE
            noInternetLayout.visibility = GONE
        } else {
            noInternetLayout.visibility = VISIBLE
            internetLayout.visibility = GONE
        }
    }
}