
# react-native-device-rotation

## TODOS:

* write README
* write iOS Sensor
* method to check availability

## Getting started

`yarn add react-native-device-rotation`

### Mostly automatic installation

`react-native link react-native-device-rotation`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-device-rotation` and add `RNDeviceRotation.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNDeviceRotation.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import org.muxe.devicerotation.RNDeviceRotationPackage;` to the imports at the top of the file
  - Add `new RNDeviceRotationPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-device-rotation'
  	project(':react-native-device-rotation').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-device-rotation/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-device-rotation')
  	```


## Usage
```javascript
import RNDeviceRotation from 'react-native-device-rotation';

// TODO: What to do with the module?
RNDeviceRotation;
```
  
## Credits
* [react-native-sensor-manager](https://github.com/kprimice/react-native-sensor-manager)
* [RNDeviceAngles](https://github.com/cristianszwarc/RNDeviceAngles)