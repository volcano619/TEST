
package com.example.venky.proximity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {


    TextView ProximitySensor, ProximityMax, ProximityReading;
    TextView LightSensor, LightMax, LightReading;
    TextView MagnetoSensor, MagnetoMax, MagnetoReading;
    TextView GyroSensor, GyroMax, GyroReading, GyroReading1, GyroReading2;
    TextView AccSensor, AccMax, AccReading, AccReading1, AccReading2;
    TextView btnProximity, btnAcc, btnMagneto, btnLight, btnGyro;
    SensorManager mySensorManager;
    Sensor myProximitySensor;
    Sensor myGyroSensor;
    Sensor myAccSensor;
    Sensor myLightSensor;
    Sensor myMagnetoSensor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProximitySensor = (TextView) findViewById(R.id.proximitySensor);
        ProximityMax = (TextView) findViewById(R.id.proximityMax);
        ProximityReading = (TextView) findViewById(R.id.proximityReading);
        LightSensor = (TextView) findViewById(R.id.lightSensor);
        LightMax = (TextView) findViewById(R.id.lightMax);
        LightReading = (TextView) findViewById(R.id.lightReading);
        GyroSensor = (TextView) findViewById(R.id.GyroSensor);
        GyroMax = (TextView) findViewById(R.id.GyroMax);
        GyroReading = (TextView) findViewById(R.id.GyroReading);
        GyroReading1 = (TextView) findViewById(R.id.GyroReading1);
        GyroReading2 = (TextView) findViewById(R.id.GyroReading2);
        MagnetoSensor = (TextView) findViewById(R.id.MagnetoSensor);
        MagnetoMax = (TextView) findViewById(R.id.MagnetoMax);
        MagnetoReading = (TextView) findViewById(R.id.MagnetoReading);

        btnProximity = (TextView) findViewById(R.id.btnProximity);
        btnLight=(TextView)findViewById(R.id.btnLight);
        btnMagneto=(TextView)findViewById(R.id.btnMagneto);
        btnAcc=(TextView)findViewById(R.id.btnAcc);
        btnGyro=(TextView)findViewById(R.id.btnGyro);

        AccSensor = (TextView) findViewById(R.id.AccSensor);
        AccMax = (TextView) findViewById(R.id.AccMax);
        AccReading = (TextView) findViewById(R.id.AccReading);
        AccReading1 = (TextView) findViewById(R.id.AccReading1);
        AccReading2 = (TextView) findViewById(R.id.AccReading2);
        Proximity();
        Acc();
        Gyro();
        Magneto();
        Light();





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

    SensorEventListener GyroSensorEventListener
            = new SensorEventListener() {

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            // TODO Auto-generated method stub

            if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                GyroReading.setText("Gyroscope Sensor_X:"
                        + String.valueOf(event.values[0]));
                GyroReading1.setText("Gyroscope Sensor_Y:"
                        + String.valueOf(event.values[1]));
                GyroReading2.setText("Gyroscope Sensor_Z:"
                        + String.valueOf(event.values[2]));

            }
        }


    };
    SensorEventListener MagnetoSensorEventListener
            = new SensorEventListener() {

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            // TODO Auto-generated method stub

            if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                MagnetoReading.setText("Magneto Sensor Reading:"
                        + String.valueOf(event.values[0]));

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

            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                AccReading.setText("Accelerometer ReadingX:"
                        + String.valueOf(event.values[0]));
                AccReading1.setText("Accelerometer ReadingY:"
                        + String.valueOf(event.values[1]));
                AccReading2.setText("Accelerometer ReadingZ:"
                        + String.valueOf(event.values[2]));

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
                        }
                    }
                });
    }
    public void Magneto() {
        btnMagneto.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mySensorManager = (SensorManager) getSystemService(
                                Context.SENSOR_SERVICE);
                        myMagnetoSensor = mySensorManager.getDefaultSensor(
                                Sensor.TYPE_MAGNETIC_FIELD);

                        if (myMagnetoSensor == null) {
                            MagnetoSensor.setText("No Magneto Sensor!");
                        } else {
                            MagnetoSensor.setText(myMagnetoSensor.getName());
                            MagnetoMax.setText("Maximum Range: "
                                    + String.valueOf(myMagnetoSensor.getMaximumRange()));
                            mySensorManager.registerListener(MagnetoSensorEventListener,
                                    myMagnetoSensor,
                                    SensorManager.SENSOR_DELAY_NORMAL);
                        }
                    }
                });
    }
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
                        }
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




    /*public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try
                        {
                            JSONObject jsonObject=new JSONObject(ParsingDta);
                            JSONArray jsonArray= jsonObject.getJSONArray("Retailers");
                            for(int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                                String rtrname=jsonObject1.getString("rtrname").toString();
                                String ctgname=jsonObject1.getString("ctgname").toString();
                                String rtrphoneno=jsonObject1.getString("rtrphoneno").toString();

                                str+="\n Retailers"+i+ "\n rtrname:"+rtrname+"\n ctgname:"+ctgname+"\n rtrphoneno:" +rtrphoneno+"\n";
                                boolean isInserted = database.insertData(rtrname,ctgname,rtrphoneno);
                                if (isInserted == true)
                                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                                //textView1.setText(str);
                            }
                        }
                        catch(JSONException e)
                        {
                            e.printStackTrace();
                        }

                    }
                }
        );
    }*/