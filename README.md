
# react-native-device-rotation

Get updates about a phones attitude in three axis. Values are in degrees and are roll, pitch and azimuth.
Works for android and iOS. From my testing the differences are: pitch degrees are flipped for android and iOS, north (azimuth = 0) is the top of the phone in android and the right og the phone in iOS.

## TODOS:
* it's an early version of the package, so theres room for improvements
* make sure values are the same on ios and android
* method to check availability of the sensors

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

componentDidMount() {
		// only does stuff in iOS currently
		RNDeviceRotation.setUpdateInterval(0.2)

		const orientationEvent = new NativeEventEmitter(RNDeviceRotation)
		this.subscription = orientationEvent.addListener('DeviceRotation', event => {
			log('DeviceRotation', event)
			this.setState({
				roll: event.roll,
				pitch: event.pitch,
				azimuth: event.azimuth,
			})
		})
		RNDeviceRotation.start()
	}

	componentWillUnmount() {
		if (this.subscription) {
			this.subscription.remove()
		}
		RNDeviceRotation.stop()
	}
```
	
## Credits
* I found some older libraries for either android or iOS. I was greatly influenced by these solutions:
* [react-native-sensor-manager](https://github.com/kprimice/react-native-sensor-manager)
* [RNDeviceAngles](https://github.com/cristianszwarc/RNDeviceAngles)