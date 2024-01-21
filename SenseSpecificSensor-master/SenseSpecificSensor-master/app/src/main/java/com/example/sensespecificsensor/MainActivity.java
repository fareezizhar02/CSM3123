package com.example.sensespecificsensor;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager senseMan;
    Sensor lightSensor;
    TextView textview; //simpan value yang kita dapat dari variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview = (TextView) findViewById(R.id.textview);

        senseMan = (SensorManager) getSystemService(SENSOR_SERVICE);

        lightSensor = senseMan.getDefaultSensor(Sensor.TYPE_LIGHT);

        if (lightSensor != null){
            Toast.makeText(this, "Light Sensor is Found in Device",Toast.LENGTH_LONG);
            senseMan.registerListener(this,lightSensor,SensorManager.SENSOR_DELAY_NORMAL);
        }else{
            Toast.makeText(this, "Light Sensor is not Found in Device",Toast.LENGTH_LONG);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        textview.setText(Float.toString(sensorEvent.values[0]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        senseMan.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        senseMan.registerListener(this,lightSensor,SensorManager.SENSOR_DELAY_NORMAL);
    }


}