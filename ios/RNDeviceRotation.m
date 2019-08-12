
#import <React/RCTEventDispatcher.h>
#import "RNDeviceRotation.h"
#include <math.h>

@implementation RNDeviceRotation

RCT_EXPORT_MODULE();

#define RADIANS_TO_DEGREES(radians) ((radians) * (180.0 / M_PI))

- (id) init {
    self = [super init];
    NSLog(@"Gyroscope");
    
    if (self) {
        self->_motionManager = [[CMMotionManager alloc] init];
        
        if ([self->_motionManager isDeviceMotionAvailable] == YES) {
            NSLog(@"isDeviceMotionAvailable active");
        } else {
            NSLog(@"isDeviceMotionAvailable not active");
        }
    }
    return self;
}

- (NSArray<NSString *> *)supportedEvents
{
    return @[@"DeviceRotation"];
}

RCT_EXPORT_METHOD(setUpdateInterval:(double) interval) {
    NSLog(@"setDeviceMotionUpdateInterval: %f", interval);
    
    [self->_motionManager setDeviceMotionUpdateInterval:interval];
}

RCT_EXPORT_METHOD(getUpdateInterval:(RCTResponseSenderBlock) cb) {
    double interval = self->_motionManager.deviceMotionUpdateInterval;
    NSLog(@"getDeviceMotionUpdateInterval: %f", interval);
    cb(@[[NSNull null], [NSNumber numberWithDouble:interval]]);
}


RCT_EXPORT_METHOD(start) {
    NSLog(@"startMotionUpdates");
    [self->_motionManager startDeviceMotionUpdates];
    
//    [self->_motionManager startDeviceMotionUpdatesToQueue:[NSOperationQueue mainQueue]
//                                      withHandler:^(CMDeviceMotion *deviceMotion, NSError *error)
    [self->_motionManager startDeviceMotionUpdatesUsingReferenceFrame:CMAttitudeReferenceFrameXMagneticNorthZVertical
                                                toQueue:[NSOperationQueue mainQueue]
                                                withHandler:^(CMDeviceMotion *deviceMotion, NSError *error)
     {
        double yaw = fmod(RADIANS_TO_DEGREES(deviceMotion.attitude.yaw), 360.0);
        double roll = fmod(RADIANS_TO_DEGREES(deviceMotion.attitude.roll), 360.0);
        double pitch = fmod(RADIANS_TO_DEGREES(deviceMotion.attitude.pitch), 360.0);
         
         if (yaw < 0) {
             yaw = 360 - (0 - yaw);
         }
         
         if (roll < 0) {
             roll = 360 - (0 - roll);
         }
         
         if (pitch < 0) {
             pitch = 360 - (0 - pitch);
         }
         
         [self sendEventWithName:@"DeviceRotation" body:@{
            @"azimuth" : [NSNumber numberWithDouble:yaw],
            @"roll" : [NSNumber numberWithDouble:roll],
            @"pitch" : [NSNumber numberWithDouble:pitch],
        }];
     }];
    
}

RCT_EXPORT_METHOD(stop) {
    NSLog(@"stopMotionUpdates");
    [self->_motionManager stopDeviceMotionUpdates];
}

@end
