package com.best.karem.simplestepcalculator;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;

    private TextView  stepsTv;

    boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        stepsTv = findViewById(R.id.steps_tv);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);



    }

    @Override
    protected void onResume() {
        super.onResume();
        running = true;
        stepsTv.setText("0");

        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        if(countSensor != null){
            sensorManager.registerListener(this , countSensor , SensorManager.SENSOR_DELAY_UI);
        }else{
            Toast.makeText(this, "Sensor Not Found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        running = false;


    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        stepsTv.setText("0");

        if(running){
            stepsTv.setText(String.valueOf(sensorEvent.values[0]));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
