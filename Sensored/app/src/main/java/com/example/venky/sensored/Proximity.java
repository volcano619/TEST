package com.example.venky.sensored;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Proximity extends AppCompatActivity {
    TextView ProximitySensor, ProximityMax, ProximityReading;
    TextView LightSensor, LightMax, LightReading,venky;
    Button btnProximity,btnLight,btnBack;
    SensorManager mySensorManager;
    Sensor myProximitySensor;
    Sensor myLightSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity);



        ProximitySensor = (TextView) findViewById(R.id.proximitySensor);
        ProximityMax = (TextView) findViewById(R.id.proximityMax);
        ProximityReading = (TextView) findViewById(R.id.proximityReading);
        LightSensor = (TextView) findViewById(R.id.lightSensor);
        LightMax = (TextView) findViewById(R.id.lightMax);
        LightReading = (TextView) findViewById(R.id.lightReading);

        venky=(TextView)findViewById(R.id.venky);

        btnProximity = (Button) findViewById(R.id.btnProximity);
        btnLight= (Button) findViewById(R.id.btnLight);
        btnBack=(Button) findViewById(R.id.btnBack);
        Proximity();
        Light();
        Back();
    }

    SensorEventListener lightSensorEventListener
            = new SensorEventListener() {

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            // TODO Auto-generated method stub

            if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
                LightReading.setText("Light Sensor Reading:"
                        + String.valueOf(event.values[0]));

            }

        }
    };

    SensorEventListener proximitySensorEventListener
            = new SensorEventListener() {

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            // TODO Auto-generated method stub

            if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                ProximityReading.setText("Proximity Sensor Reading:"
                        + String.valueOf(event.values[0]));

            }
        }


    };

    public void Proximity() {
        btnProximity.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mySensorManager = (SensorManager) getSystemService(
                                Context.SENSOR_SERVICE);
                        myProximitySensor = mySensorManager.getDefaultSensor(
                                Sensor.TYPE_PROXIMITY);


                        if (myProximitySensor == null) {
                            ProximitySensor.setText("No Proximity Sensor!");
                        } else {
                            ProximitySensor.setText(myProximitySensor.getName());
                            ProximityMax.setText("Maximum Range: "
                                    + String.valueOf(myProximitySensor.getMaximumRange()));
                            mySensorManager.registerListener(proximitySensorEventListener,
                                    myProximitySensor,
                                    SensorManager.SENSOR_DELAY_NORMAL);
                            venky.setText("The app is showing you Proximity Sensor Data");
                        }
                    }
                });
    }

    public void Light() {
        btnLight.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mySensorManager = (SensorManager) getSystemService(
                                Context.SENSOR_SERVICE);
                        myLightSensor = mySensorManager.getDefaultSensor(
                                Sensor.TYPE_LIGHT);


                        if (myLightSensor == null) {
                            LightSensor.setText("No Light Sensor!");
                        } else {
                            LightSensor.setText(myLightSensor.getName());
                            LightMax.setText("Maximum Range: "
                                    + String.valueOf(myLightSensor.getMaximumRange()));
                            mySensorManager.registerListener(lightSensorEventListener,
                                    myLightSensor,
                                    SensorManager.SENSOR_DELAY_NORMAL);
                            venky.setText("The app is showing you Light Sensor Data");
                        }
                    }
                });
    }

    public void Back() {
        btnBack.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }
                });
    }





}
