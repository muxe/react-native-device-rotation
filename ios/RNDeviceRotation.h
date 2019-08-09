
//#if __has_include("RCTBridgeModule.h")
//#import "RCTBridgeModule.h"
//#else
//#import <React/RCTBridgeModule.h>
//#endif
//#import <React/RCTEventEmitter.h>

#import <React/RCTBridgeModule.h>
//#import <React/RCTEventEmitter.h>
#import <CoreMotion/CoreMotion.h>

@interface RNDeviceRotation : NSObject <RCTBridgeModule> {
    CMMotionManager *_motionManager;
}

@end
  
