package com.elds.desafioelds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    var compass : CardView? = null
    var biometric : CardView? = null
    var proximity : CardView? = null
    var step : CardView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        compass = findViewById(R.id.compass)
        biometric = findViewById(R.id.biometric)
        proximity = findViewById(R.id.proximity)
        step = findViewById(R.id.step)
    }
}