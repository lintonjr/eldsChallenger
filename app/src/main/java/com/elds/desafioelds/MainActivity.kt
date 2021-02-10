package com.elds.desafioelds


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val compass = findViewById<CardView>(R.id.compass)
        val biometric = findViewById<CardView>(R.id.biometric)
        val proximity = findViewById<CardView>(R.id.proximity)
        val step = findViewById<CardView>(R.id.step)

        compass.setOnClickListener {
            val intent = Intent(this, CompassActivity::class.java)
            startActivity(intent)
        }

        biometric.setOnClickListener {
            val intent = Intent(this, BiometricActivity::class.java)
            startActivity(intent)
        }

        proximity.setOnClickListener {
            val intent = Intent(this, ProximityActivity::class.java)
            startActivity(intent)
        }

        step.setOnClickListener {
            val intent = Intent(this, StepActivity::class.java)
            startActivity(intent)
        }

    }
}