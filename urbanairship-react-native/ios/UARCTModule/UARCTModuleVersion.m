/* Copyright Airship and Contributors */

#import "UARCTModuleVersion.h"

@implementation UARCTModuleVersion

NSString *const airshipModuleVersionString = @"14.4.2";

+ (nonnull NSString *)get {
    return airshipModuleVersionString;
}

@end
