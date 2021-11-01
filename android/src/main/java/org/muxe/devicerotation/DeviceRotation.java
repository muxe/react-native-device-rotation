package org.muxe.devicerotation;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import androidx.annotation.Nullable;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class DeviceRotation implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Sensor mMagnetometer;
    private boolean isRegistered = false;

    private ReactContext mReactContext;


    public DeviceRotation(ReactApplicationContext reactContext) {
        mSensorManager = (SensorManager)reactContext.getSystemService(reactContext.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mReactContext = reactContext;
    }

    public boolean start() {
        if (mAccelerometer != null && isRegistered == false) {
            mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
            mSensorManager.registerListener(this, mMagnetometer, SensorManager.SENSOR_DELAY_UI);
            isRegistered = true;
            return true;
        }
        return false;
    }

    public void stop() {
        if (isRegistered == true) {
            mSensorManager.unregisterListener(this);
            isRegistered = false;
        }
    }

    private void sendEvent(String eventName, @Nullable WritableMap params) {
        try {
            mReactContext
                    .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit(eventName, params);
        } catch (RuntimeException e) {
            Log.e("ERROR", "java.lang.RuntimeException: Trying to invoke JS before CatalystInstance has been set!");
        }
    }

    float[] mGravity;
    float[] mGeomagnetic;

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;
        WritableMap map = Arguments.createMap();

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER)
            mGravity = sensorEvent.values;
        if (mySensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
            mGeomagnetic = sensorEvent.values;
        if (mGravity != null && mGeomagnetic != null) {
            float R[] = new float[9];
            float I[] = new float[9];
            boolean success = mSensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic);
            if (success) {
                long curTime = System.currentTimeMillis();
                float orientation[] = new float[3];
                mSensorManager.getOrientation(R, orientation);

                float heading = (float)((Math.toDegrees(orientation[0])) % 360.0f);
                float pitch = (float)((Math.toDegrees(orientation[1])) % 360.0f);
                float roll = (float)((Math.toDegrees(orientation[2])) % 360.0f);

                if (heading < 0) {
                    heading = 360 - (0 - heading);
                }

                if (pitch < 0) {
                    pitch = 360 - (0 - pitch);
                }

                if (roll < 0) {
                    roll = 360 - (0 - roll);
                }

                map.putDouble("azimuth", heading);
                map.putDouble("pitch", pitch);
                map.putDouble("roll", roll);
                sendEvent("DeviceRotation", map);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
