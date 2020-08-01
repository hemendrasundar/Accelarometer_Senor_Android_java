package com.hemendra.sensorservice;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.hardware.SensorManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
TextView tv;
SensorManager sm;
List<Sensor> list;
String sensorname;

SensorEventListener Sel = new SensorEventListener() {
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float[] values = sensorEvent.values;
        tv.setText("X :"+values[0]+"\n y:"+values[1]+"\n z:"+values[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        list = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);

        if(list.size()>0)
        {
            sm.registerListener(Sel,(Sensor)list.get(0),SensorManager.SENSOR_DELAY_NORMAL);
        }
        else{
            Toast.makeText(MainActivity.this,"error no accelarometer sensor available",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onStop() {
        if(list.size()>0)
        {
            sm.unregisterListener(Sel);
        }
        super.onStop();
    }
}