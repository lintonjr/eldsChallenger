package com.elds.desafioelds

import android.annotation.SuppressLint
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView

class StepActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var stepCounter: TextView
    private lateinit var stepDetector: TextView
    private lateinit var sensorManager: SensorManager
    private lateinit var mStepCounter: Sensor
    private var isCounterSensorPresent: Boolean = false
    var stepCount: Int = 0

    @SuppressLint("ServiceCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        this.stepCounter = findViewById(R.id.stepCounter)
        this.stepDetector = findViewById(R.id.stepDetector)

        this.sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            mStepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
            isCounterSensorPresent = true
            stepCounter.text = stepCount.toString()
        } else {
            stepCounter.text = "Counter Sensor is not present"
            isCounterSensorPresent = false
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(event?.sensor == mStepCounter) {
            stepCount = event.values[0].toInt()
            stepCounter.text = stepCount.toString()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("Not yet implemented")
    }

    override fun onResume() {
        super.onResume()
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            sensorManager.registerListener(this, mStepCounter, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            sensorManager.unregisterListener(this, mStepCounter)
        }
    }
}