package com.malov.criptotable.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.malov.criptotable.R

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about2)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val buttonRateApp = findViewById<Button>(R.id.buttonRateApp)

        buttonRateApp.setOnClickListener{
            startActivity(
                Intent(Intent.ACTION_VIEW,
                Uri.parse("market://details?id=${applicationContext.packageName}")))
        }
    }
}