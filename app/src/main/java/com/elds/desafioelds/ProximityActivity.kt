package com.elds.desafioelds

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment

class ProximityActivity : AppCompatActivity(), SensorEventListener{

    private lateinit var sensorManager: SensorManager
    private lateinit var proximitySensor: Sensor
    private var isProximityAvailable: Boolean = false
    private lateinit var textView: TextView
    private lateinit var vibrator: Vibrator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proximity)

        this.textView = findViewById<TextView>(R.id.sensorDistance)
        this.sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager


        if(sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) != null) {
            this.proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
            this.isProximityAvailable = true
        } else {
            textView.setText("Proximity sensor is not available")
            this.isProximityAvailable = false
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        this.textView.text = "${event!!.values[0]} cm"

        val vibrator = (getSystemService(Context.VIBRATOR_SERVICE) as Vibrator)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(500,
                VibrationEffect.DEFAULT_AMPLITUDE))
        }
        else {
            vibrator.vibrate(500)
        }

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onResume() {
        super.onResume()
        if(isProximityAvailable) {
            sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        if(isProximityAvailable) {
            sensorManager.unregisterListener(this)
        }
    }
}