
package org.muxe.devicerotation;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class RNDeviceRotationModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;
  private DeviceRotation deviceRotation;

  public RNDeviceRotationModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNDeviceRotation";
  }

  @ReactMethod
  public boolean start() {
    if (deviceRotation == null) {
      deviceRotation = new DeviceRotation(reactContext);
    }
    return deviceRotation.start();
  }

  @ReactMethod
  public void stop() {
    if (deviceRotation != null) {
      deviceRotation.stop();
    }
  }

  @ReactMethod
  public void setUpdateInterval(float interval) {
    // does nothing on android atm
  }
}