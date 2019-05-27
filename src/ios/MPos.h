#import <Cordova/CDVPlugin.h>
#import "QPOSService.h"
#import "BTDeviceFinder.h"

@interface MPos : CDVPlugin<BluetoothDelegate2Mode,QPOSServiceListener> {
}

- (void)scanBTDevices: (CDVInvokedUrlCommand *)command;
- (void)connectBTDevice: (CDVInvokedUrlCommand *)command;
- (void)disconnectBTDevice: (CDVInvokedUrlCommand *)command;
- (void)getInfoBTDevice: (CDVInvokedUrlCommand *)command;
- (void)doTradeSale: (CDVInvokedUrlCommand *)command;
- (void)enterPIN:(CDVInvokedUrlCommand *)command;

@end
