package android.brams.dk.mycompas;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    SensorManager sensorService;
    CompassView compassView;
    Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
            compassView = new CompassView(this);
            setContentView(compassView);

            sensorService = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            sensor = sensorService.getDefaultSensor(Sensor.TYPE_ORIENTATION);
            if (sensor != null) {
                sensorService.registerListener(mySensorEventListener, sensor,
                        SensorManager.SENSOR_DELAY_NORMAL);
                Log.i(TAG, "Registerered for ORIENTATION Sensor");
            } else {
                Log.e(TAG, "Registerered for ORIENTATION Sensor - failed");
                Toast.makeText(this, "ORIENTATION Sensor not found",
                        Toast.LENGTH_LONG).show();
                finish();
            }
        }

        private SensorEventListener mySensorEventListener = new SensorEventListener() {

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }

            @Override
            public void onSensorChanged(SensorEvent event) {
                // angle between the magnetic north direction
                // 0=North, 90=East, 180=South, 270=West
                float azimuth = event.values[0];
                compassView.updateData(azimuth);
            }
        };

        @Override
        protected void onDestroy() {
            super.onDestroy();
            if (sensor != null) {
                sensorService.unregisterListener(mySensorEventListener);
            }
        }

}
