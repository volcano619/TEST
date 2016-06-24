package com.example.venky.sensored;

import android.app.AlertDialog;
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

public class MainActivity extends AppCompatActivity {

    TextView GyroSensor, GyroMax, GyroReading, GyroReading1, GyroReading2;
    TextView AccSensor, AccMax, AccReading, AccReading1, AccReading2,venky;
    SensorManager mySensorManager;
    Sensor myGyroSensor;
    Sensor myAccSensor;
    Button btnAcc, btnGyro,btnActivity2,btnActivity3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GyroSensor = (TextView) findViewById(R.id.GyroSensor);
        GyroMax = (TextView) findViewById(R.id.GyroMax);
        GyroReading = (TextView) findViewById(R.id.GyroReading);
        GyroReading1 = (TextView) findViewById(R.id.GyroReading1);
        GyroReading2 = (TextView) findViewById(R.id.GyroReading2);
    venky=(TextView)findViewById(R.id.venky);
        AccSensor = (TextView) findViewById(R.id.AccSensor);
        AccMax = (TextView) findViewById(R.id.AccMax);
        AccReading = (TextView) findViewById(R.id.AccReading);
        AccReading1 = (TextView) findViewById(R.id.AccReading1);
        AccReading2 = (TextView) findViewById(R.id.AccReading2);

        btnAcc=(Button) findViewById(R.id.btnAcc);
        btnGyro=(Button) findViewById(R.id.btnGyro);
        btnActivity2=(Button) findViewById(R.id.btnActivity2);
        //btnActivity3=(Button) findViewById(R.id.btnActivity3);

        Acc();
        Gyro();
        Activity2();
    }


    SensorEventListener GyroSensorEventListener
            = new SensorEventListener() {

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            // TODO Auto-generated method stub
            String values1 = String.valueOf(event.values[0]);
            String values11 = String.valueOf(event.values[1]);
            String values111 = String.valueOf(event.values[2]);

            int MAX_CHAR = 5;
            int maxLength = (values1.length() < MAX_CHAR)?values1.length():MAX_CHAR;
            values1 = values1.substring(0, maxLength);


            int maxLength1 = (values11.length() < MAX_CHAR)?values11.length():MAX_CHAR;
            values11 = values11.substring(0, maxLength1);


            int maxLength11 = (values111.length() < MAX_CHAR)?values111.length():MAX_CHAR;
            values111 = values111.substring(0, maxLength11);

            if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                GyroReading.setText("Gyroscope Sensor_X:"
                        + String.valueOf(values1));
                GyroReading1.setText("Gyroscope Sensor_Y:"
                        + String.valueOf(values11));
                GyroReading2.setText("Gyroscope Sensor_Z:"
                        + String.valueOf(values111));

            }
        }


    };

    SensorEventListener AccEventListener
            = new SensorEventListener() {

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            // TODO Auto-generated method stub
            String values1 = String.valueOf(event.values[0]);
            String values11 = String.valueOf(event.values[1]);
            String values111 = String.valueOf(event.values[2]);

            int MAX_CHAR = 5;
            int maxLength = (values1.length() < MAX_CHAR)?values1.length():MAX_CHAR;
            values1 = values1.substring(0, maxLength);


            int maxLength1 = (values11.length() < MAX_CHAR)?values11.length():MAX_CHAR;
            values11 = values11.substring(0, maxLength1);


            int maxLength11 = (values111.length() < MAX_CHAR)?values111.length():MAX_CHAR;
            values111 = values111.substring(0, maxLength11);
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

                AccReading.setText("Accelerometer ReadingX:"
                        + String.valueOf(values1));
                AccReading1.setText("Accelerometer ReadingY:"
                        + String.valueOf(values11));
                AccReading2.setText("Accelerometer ReadingZ:"
                        + String.valueOf(values111));

            }
        }


    };
    public void Acc() {
        btnAcc.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mySensorManager = (SensorManager) getSystemService(
                                Context.SENSOR_SERVICE);
                        myAccSensor = mySensorManager.getDefaultSensor(
                                Sensor.TYPE_ACCELEROMETER);


                        if (myAccSensor == null) {
                            AccSensor.setText("No Accelerometer Sensor!");
                        } else {
                            AccSensor.setText(myAccSensor.getName());
                            AccMax.setText("Maximum Range: "
                                    + String.valueOf(myAccSensor.getMaximumRange()));
                            mySensorManager.registerListener(AccEventListener,
                                    myAccSensor,
                                    SensorManager.SENSOR_DELAY_NORMAL);
                            venky.setText("The app is showing you Acc Sensor Data");
                        }
                    }
                });
    }


    public void Gyro() {
        btnGyro.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mySensorManager = (SensorManager) getSystemService(
                                Context.SENSOR_SERVICE);
                        myGyroSensor = mySensorManager.getDefaultSensor(
                                Sensor.TYPE_GYROSCOPE);


                        if (myGyroSensor == null) {
                            GyroSensor.setText("No Gyro Sensor!");
                        } else {
                            GyroSensor.setText(myGyroSensor.getName());
                            GyroMax.setText("Maximum Range: "
                                    + String.valueOf(myGyroSensor.getMaximumRange()));
                            mySensorManager.registerListener(GyroSensorEventListener,
                                    myGyroSensor,
                                    SensorManager.SENSOR_DELAY_NORMAL);
                            venky.setText("The app is showing you Gyro Sensor Data");
                        }
                    }
                });
    }
    public void Activity2() {
        btnActivity2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        startActivity(new Intent(getApplicationContext(),Proximity.class));
                    }
                });
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }





}