#import <AudioToolbox/AudioToolbox.h>
#import "BTDeviceFinder.h"
#import "MPos.h"
#import "Util.h"

BOOL is2ModeBluetooth = YES;
BOOL isBTScanning = NO;
NSInteger scanBluetoothTime = 10;
NSInteger doTradeTime = 30;
NSInteger swipeTries = 0;

const NSString *dispatchEvent = @"cordova.plugins.mPos.on";

@interface MPos(){
    NSMutableArray *_objects;
}

@end

@implementation MPos{
    BTDeviceFinder *bt;
    NSTimer *mNSTimer;
    QPOSService *mQPOSService;
    
    NSString *tradeAmount;
    NSString *tradeCurrency;
    NSString *tradeDescription;
    NSString *tradeTerminalTime;
}

//CORDOVA EVENTS
-(void)scanBTDevices:(CDVInvokedUrlCommand *)command{
    scanBluetoothTime = [[command.arguments objectAtIndex:0] intValue];
    _objects = nil;
    
    if (bt == nil)
        bt = [BTDeviceFinder new];
    
    NSInteger delay = 0;
    
    if(is2ModeBluetooth){
        [self.commandDelegate evalJs:[NSString stringWithFormat:@"%@('%@')", dispatchEvent, @"onStartScanBT"]];
        
        NSLog(@"+++BLUETOOTH START:%ld",(long)[bt getCBCentralManagerState]);
        [bt setBluetoothDelegate2Mode:self];
        
        if ([bt getCBCentralManagerState] == CBCentralManagerStateUnknown) {
            while ([bt getCBCentralManagerState] != CBCentralManagerStatePoweredOn) {
                [self sleepMs:10];
                if(delay++ == 10){
                    NSLog(@"+++Bluetooth state is not power on");
                    [self.commandDelegate evalJs:[NSString stringWithFormat:@"%@('%@')", dispatchEvent, @"onStateOffScanBT"]];
                    return;
                }
            }
        }
        
        [bt scanQPos2Mode:scanBluetoothTime];
        isBTScanning = YES;
    }
}

-(void)connectBTDevice:(CDVInvokedUrlCommand *)command{
    NSString *address = [command.arguments objectAtIndex:0];
    
    if(isBTScanning)
        dispatch_async(dispatch_get_main_queue(),  ^{
            [self stopBluetooth];
        });
    
    [self initQposs];
    [mQPOSService connectBT:address];
}

-(void)disconnectBTDevice:(CDVInvokedUrlCommand *)command{
    [mQPOSService disconnectBT];
}

-(void)getInfoBTDevice:(CDVInvokedUrlCommand *)command{
    [mQPOSService getQPosInfo];
}

-(void)doTradeSale:(CDVInvokedUrlCommand *)command{
    swipeTries = 0;
    
    tradeAmount = [command.arguments objectAtIndex:0];
    tradeCurrency = [command.arguments objectAtIndex:1];
    tradeDescription = [command.arguments objectAtIndex:2];
    
    NSDateFormatter *dateFormatter = [NSDateFormatter new];
    [dateFormatter setDateFormat:@"yyyyMMddHHmmss"];
    tradeTerminalTime = [dateFormatter stringFromDate:[NSDate date]];
    
    [mQPOSService doTrade:doTradeTime];
}

-(void)enterPIN:(CDVInvokedUrlCommand *)command{
    NSString *pin = [command.arguments objectAtIndex:0];
    
    if([pin length] > 0)
        [mQPOSService sendPinEntryResult:pin];
    else
        [mQPOSService cancelPinEntry];
}

//LISTENERS
-(void)onBluetoothName2Mode:(NSString *)bluetoothName{
    if(bluetoothName != nil)
        NSLog(@"+++onBluetoothName2Mode %@", bluetoothName);
    
    dispatch_async(dispatch_get_main_queue(),  ^{
        if ([bluetoothName hasPrefix:@"MPOS"]){
            [self insertNewObject:bluetoothName];
            [self.commandDelegate evalJs:[NSString stringWithFormat:@"%@('%@', {name:'%@',address:'%@'})", dispatchEvent, @"onFoundBTDevice", bluetoothName, bluetoothName]];
        }
    });
}

-(void)finishScanQPos2Mode{
    dispatch_async(dispatch_get_main_queue(),  ^{
        [self stopBluetooth];
        [self.commandDelegate evalJs:[NSString stringWithFormat:@"%@('%@')", dispatchEvent, @"onFinishScanBT"]];
    });
}

-(void) onQposInfoResult: (NSDictionary*)posInfoData{
    NSString *result = [self dictionaryToJSON:posInfoData];
    NSLog(@"+++onQposInfoResult: %@", result);
    
    [self.commandDelegate evalJs:[NSString stringWithFormat:@"%@('%@', %@)", dispatchEvent, @"onDeviceInfoResponse", result]];
}

-(void) onRequestQposConnected{
    NSLog(@"+++onRequestQposConnected");
    [self.commandDelegate evalJs:[NSString stringWithFormat:@"%@('%@')", dispatchEvent, @"onPosConnected"]];
    [mQPOSService getQPosInfo];
}

-(void) onRequestQposDisconnected{
    NSLog(@"+++onRequestQposDisconnected");
    [self.commandDelegate evalJs:[NSString stringWithFormat:@"%@('%@')", dispatchEvent, @"onPosDisconnected"]];
}

-(void) onRequestNoQposDetected{
    NSLog(@"+++onRequestNoQposDetected");
    [self.commandDelegate evalJs:[NSString stringWithFormat:@"%@('%@')", dispatchEvent, @"onPosUndetected"]];
}

-(void) onRequestTransactionLog: (NSString*) tlv{
    NSLog(@"+++onTransactionLog: %@", tlv);
    [self.commandDelegate evalJs:[NSString stringWithFormat:@"%@('%@', '%@')", dispatchEvent, @"onRequestTransactionLog", tlv]];
}

-(void) onRequestWaitingUser{
    NSLog(@"+++onRequestWaitingUser: Please insert/swipe card");
    [self.commandDelegate evalJs:[NSString stringWithFormat:@"%@('%@')", dispatchEvent, @"onRequestCard"]];
}

-(void) onRequestSetAmount{
    [mQPOSService setAmount:tradeAmount aAmountDescribe:tradeDescription currency:tradeCurrency transactionType:TransactionType_SALE];
}

-(void) onError: (Error)errorState{
    NSString *msg = @"";
    
    if(errorState == Error_CMD_NOT_AVAILABLE) {
        msg = @"COMMAND_NOT_AVAILABLE";
    } else if(errorState == Error_TIMEOUT) {
        msg = @"DEVICE_TIMEOUT";
    } else if(errorState == Error_DEVICE_RESET) {
        msg = @"DEVICE_RESET";
    } else if(errorState == Error_UNKNOWN) {
        msg = @"UNKNOWN_ERROR";
    } else if(errorState == Error_DEVICE_BUSY) {
        msg = @"DEVICE_BUSY";
    } else if(errorState == Error_INPUT_OUT_OF_RANGE) {
        msg = @"INPUT_OUT_OF_RANGE";
    } else if(errorState == Error_INPUT_INVALID_FORMAT) {
        msg = @"INPUT_INVALID_FORMAT";
    } else if(errorState == Error_INPUT_ZERO_VALUES) {
        msg = @"INPUT_ZERO_VALUES";
    } else if(errorState == Error_INPUT_INVALID) {
        msg = @"INPUT_INVALID";
    } else if(errorState == Error_CASHBACK_NOT_SUPPORTED) {
        msg = @"CASHBACK_NOT_SUPPORTED";
    } else if(errorState == Error_CRC_ERROR) {
        msg = @"CRC_ERROR";
    } else if(errorState == Error_COMM_ERROR) {
        msg = @"COMM_ERROR";
    }else if(errorState == Error_MAC_ERROR){
        msg = @"MAC_ERROR";
    }else if(errorState == Error_CMD_TIMEOUT){
        msg = @"CMD_TIMEOUT";
    }else if(errorState == Error_AMOUNT_OUT_OF_LIMIT){
        msg = @"AMOUNT_OUT_OF_LIMIT";
    }
    
    NSLog(@"+++onError: %@", msg);
    [self.commandDelegate evalJs:[NSString stringWithFormat:@"%@('%@', {code:'%@'})", dispatchEvent, @"onErrorResponse", msg]];
}

-(void) onRequestDisplay: (Display)displayMsg{
    NSString *msg = @"";
    
    if (displayMsg==Display_CLEAR_DISPLAY_MSG) {
        msg = @"CLEAR_DISPLAY_MSG";
    }else if(displayMsg==Display_PLEASE_WAIT){
        msg = @"PLEASE_WAIT";
    }else if(displayMsg==Display_REMOVE_CARD){
        msg = @"PLEASE_REMOVE_CARD";
    }else if (displayMsg==Display_TRY_ANOTHER_INTERFACE){
        msg = @"PLEASE_TRY_ANOTHER_INTERFACE";
    }else if (displayMsg == Display_TRANSACTION_TERMINATED){
        msg = @"TRANSACTION_TERMINATED";
    }else if (displayMsg == Display_PROCESSING){
        msg = @"PROCESSING";
    }else if (displayMsg == Display_PIN_OK){
        msg = @"PIN_OK";
    }else if (displayMsg == Display_INPUT_PIN_ING){
        msg = @"PLEASE_INPUT_PIN";
    }else if (displayMsg == Display_MAG_TO_ICC_TRADE){
        msg = @"PLEASE_INSERT_CHIP";
    }else if (displayMsg == Display_INPUT_OFFLINE_PIN_ONLY){
        msg = @"INPUT_OFFLINE_PIN_ONLY";
    }else if(displayMsg == Display_CARD_REMOVED){
        msg = @"CARD_REMOVED";
    }
    
    NSLog(@"+++onRequestDisplay: %@", msg);
    [self.commandDelegate evalJs:[NSString stringWithFormat:@"%@('%@', {code:'%@'})", dispatchEvent, @"onRequestDisplay", msg]];
}

-(void) onRequestTime{
    [mQPOSService sendTime:tradeTerminalTime];
}

-(void) onRequestFinalConfirm{
    [mQPOSService finalConfirm:YES];
}

-(void) onRequestIsServerConnected{
    NSLog(@"Online process requested.");
    [mQPOSService isServerConnected:YES];
}

-(void) onRequestOnlineProcess: (NSString*) tlv{
    NSLog(@"+++TLV == %@", tlv);
    NSLog(@"+++onRequestOnlineProcess = %@",[[QPOSService sharedInstance] anlysEmvIccData:tlv]);
    [mQPOSService sendOnlineProcessResult:@"8A023030"];
}

-(void) onRequestTransactionResult: (TransactionResult)transactionResult {
    NSString *display = @"";

    if (transactionResult == TransactionResult_APPROVED) {
        display = @"TRANSACTION_RESULT_APPROVED";
    } else if (transactionResult == TransactionResult_TERMINATED) {
        display = @"TERMINATED";
    } else if (transactionResult == TransactionResult_DECLINED) {
        display = @"DECLINED";
    } else if (transactionResult == TransactionResult_CANCEL) {
        display = @"CANCEL";
    } else if (transactionResult == TransactionResult_CAPK_FAIL) {
        display = @"CAPK_FAIL";
    } else if (transactionResult == TransactionResult_NOT_ICC) {
        display = @"NOT_ICC";
    } else if (transactionResult == TransactionResult_SELECT_APP_FAIL) {
        display = @"SELECT_APP_FAIL";
    } else if (transactionResult == TransactionResult_DEVICE_ERROR) {
        display = @"DEVICE_ERROR";
    } else if (transactionResult == TransactionResult_CARD_NOT_SUPPORTED) {
        display = @"CARD_NOT_SUPPORTED";
    } else if (transactionResult == TransactionResult_MISSING_MANDATORY_DATA) {
        display = @"MISSING_MANDATORY_DATA";
    } else if (transactionResult == TransactionResult_CARD_BLOCKED_OR_NO_EMV_APPS) {
        display = @"CARD_BLOCKED_OR_NO_EMV_APPS";
    } else if (transactionResult == TransactionResult_INVALID_ICC_DATA) {
        display = @"INVALID_ICC_DATA";
    } else if (transactionResult == TransactionResult_FALLBACK) {
        display = @"FALLBACK";
    } else if (transactionResult == TransactionResult_NFC_TERMINATED) {
        display = @"NFC_TERMINATED";
    }

    NSLog(@"+++onRequestTransactionResult: %@", display);
    [self.commandDelegate evalJs:[NSString stringWithFormat:@"%@('%@', {code:'%@'})", dispatchEvent, @"onRequestTransactionResult", display]];
}

-(void) onDoTradeResult: (DoTradeResult)result DecodeData:(NSDictionary*)decodeData{
    NSLog(@"+++onDoTradeResult?>> result %d", result);
    NSString *display = @"";
    NSString *data = nil;
    
    if (result == DoTradeResult_NONE) {
        display = @"NO_CARD_DETECTED";
        
        if(swipeTries > 3){
            swipeTries = 0;
        }else{
            swipeTries++;
            [mQPOSService doTrade:doTradeTime];
        }
    }else if (result==DoTradeResult_ICC) {
        display = @"CARD_INSERTED_ICC";
        [mQPOSService doEmvApp:EmvOption_START];
    }else if(result==DoTradeResult_NOT_ICC){
        display = @"CARD_INSERTED_NOT_ICC";
    }else if(result==DoTradeResult_MCR){
        display = @"CARD_MCR";
        data = [self dictionaryToJSON:decodeData];
    }else if(result==DoTradeResult_NFC_OFFLINE || result == DoTradeResult_NFC_ONLINE){
        display = @"CARD_TAP";
        
        dispatch_async(dispatch_get_main_queue(),  ^{
            NSDictionary *batchDataDic = [mQPOSService getNFCBatchData];
            NSString *tlv = @"";
            
            if(batchDataDic != nil)
                tlv = batchDataDic[@"tlv"];
            
            [self.commandDelegate evalJs:[NSString stringWithFormat:@"%@('%@', {code:'%@', data: '%@'})", dispatchEvent, @"onDoTradeResponse", @"TLV_BATCH_DATA", tlv]];
        });
    }else if(result==DoTradeResult_NFC_DECLINED){
        display = @"CARD_TAP_DECLINED";
        data = [self dictionaryToJSON:decodeData];
    }else if (result==DoTradeResult_NO_RESPONSE){
        display = @"CARD_NO_RESPONSE";
    }else if(result==DoTradeResult_BAD_SWIPE){
        display = @"BAD_SWIPE";
        
        if(swipeTries > 3){
            swipeTries = 0;
        }else{
            swipeTries++;
            [mQPOSService doTrade:doTradeTime];
        }
    }else if(result==DoTradeResult_NO_UPDATE_WORK_KEY){
        display = @"DEVICE_NOT_UPDATE_WORK_KEY";
    }

    if(data == nil)
        [self.commandDelegate evalJs:[NSString stringWithFormat:@"%@('%@', {code:'%@'})", dispatchEvent, @"onDoTradeResponse", display]];
    else
        [self.commandDelegate evalJs:[NSString stringWithFormat:@"%@('%@', {code:'%@', data: %@})", dispatchEvent, @"onDoTradeResponse", display, data]];
}

-(void) onRequestBatchData: (NSString*)tlv{
    NSLog(@"+++onBatchData %@", tlv);
    [self.commandDelegate evalJs:[NSString stringWithFormat:@"%@('%@', {code:'%@', data: '%@'})", dispatchEvent, @"onDoTradeResponse", @"TLV_BATCH_DATA", tlv]];
}

-(void) onReturnReversalData: (NSString*)tlv{
    NSLog(@"+++onReversalData %@",tlv);
    [self.commandDelegate evalJs:[NSString stringWithFormat:@"%@('%@', {code:'%@', data: '%@'})", dispatchEvent, @"onDoTradeResponse", @"TLV_REVERSAL_BATCH_DATA", tlv]];
}

-(void) onRequestPinEntry{
    [self.commandDelegate evalJs:[NSString stringWithFormat:@"%@('%@')", dispatchEvent, @"onRequestPinEntry"]];
}

-(void) onReturnGetPinResult:(NSDictionary*)decodeData{
    NSString *aStr = @"pinKsn: ";
    aStr = [aStr stringByAppendingString:decodeData[@"pinKsn"]];
    aStr = [aStr stringByAppendingString:@" | pinBlock: "];
    aStr = [aStr stringByAppendingString:decodeData[@"pinBlock"]];
    
    NSLog(@"+++onReturnGetPinResult: %@", aStr);
}

-(void) onPinKeyTDESResult:(NSString *)encPin{
    NSLog(@"+++onPinKeyTDESResult: %@",encPin);
}

-(void) onReturnSetMasterKeyResult: (BOOL)isSuccess{
    [self.commandDelegate evalJs:[NSString stringWithFormat:@"%@('%@', {isSuccess: %id})", dispatchEvent, @"onSetMasterKeyResponse", isSuccess]];
}

//METHODS
-(void) sleepMs: (NSInteger)msec {
    NSTimeInterval sec = (msec / 1000.0f);
    [NSThread sleepForTimeInterval:sec];
}

-(void)insertNewObject:(id)sender
{
    if (!_objects)
        _objects = [[NSMutableArray alloc] init];
    
    if(sender !=nil)
        [_objects insertObject:sender atIndex:0];
}

-(void)initQposs{
    if (nil == mQPOSService)
        mQPOSService = [QPOSService sharedInstance];
    
    [mQPOSService setDelegate:self];
    [mQPOSService setQueue:nil];
    [mQPOSService setPosType:PosType_BLUETOOTH_2mode];
}

-(void)stopBluetooth{
    if(is2ModeBluetooth){
        [bt stopQPos2Mode];
        isBTScanning = NO;
        
        NSLog(@"+++BLUETOOTH STOP");
    }
}

-(NSString*)dictionaryToJSON:(NSDictionary *)dic{
    NSError *error;
    NSString *result;
    NSData *jsonData = [NSJSONSerialization dataWithJSONObject:dic options:0 error:&error];
    
    if (! jsonData)
        NSLog(@"Got an error: %@", error);
    else
        result = [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
    
    return result;
}

-(NSString* )getEMVStr:(NSString *)emvStr{
    NSInteger emvLen = 0;
    if (emvStr != NULL &&![emvStr  isEqual: @""]) {
        if ([emvStr length]%2 != 0) {
            emvStr = [@"0" stringByAppendingString:emvStr];
        }
        emvLen = [emvStr length]/2;
    }else{
        NSLog(@"init emv app config str could not be empty");
        return nil;
    }
    NSData *emvLenData = [Util IntToHex:emvLen];
    NSString *totalStr = [[[Util byteArray2Hex:emvLenData] substringFromIndex:2] stringByAppendingString:emvStr];
    return totalStr;
}

-(NSString *)getHexFromStr:(NSString *)str{
    NSData *data = [str dataUsingEncoding:NSUTF8StringEncoding];
    NSString *hex = [Util byteArray2Hex:data];
    return hex ;
}

-(NSString *)getHexFromIntStr:(NSString *)tmpidStr
{
    NSInteger tmpid = [tmpidStr intValue];
    NSString *nLetterValue;
    NSString *str =@"";
    int ttmpig;
    for (int i = 0; i<9; i++) {
        ttmpig=tmpid%16;
        tmpid=tmpid/16;
        switch (ttmpig)
        {
            case 10:
                nLetterValue =@"A";break;
            case 11:
                nLetterValue =@"B";break;
            case 12:
                nLetterValue =@"C";break;
            case 13:
                nLetterValue =@"D";break;
            case 14:
                nLetterValue =@"E";break;
            case 15:
                nLetterValue =@"F";break;
            default:
                nLetterValue = [NSString stringWithFormat:@"%u",ttmpig];
        }
        str = [nLetterValue stringByAppendingString:str];
        if (tmpid == 0) {
            break;
        }
    }
    
    if(str.length == 1){
        return [NSString stringWithFormat:@"0%@",str];
    }else{
        if ([str length]<8) {
            if ([str length] == (8-1)) {
                str = [@"0" stringByAppendingString:str];
            }else if ([str length] == (8-2)){
                str = [@"00" stringByAppendingString:str];
            }else if  ([str length] == (8-3)){
                str = [@"000" stringByAppendingString:str];
            }
            else if ([str length] == (8-4)) {
                str = [@"0000" stringByAppendingString:str];
            } else if([str length] == (8-5)){
                str = [@"00000" stringByAppendingString:str];
            }else if([str length] == (8-6)){
                str = [@"000000" stringByAppendingString:str];
            }
        }
        return str;
    }
}

-(NSData*)readLine:(NSString*)name
{
    NSString* file = [[NSBundle mainBundle]pathForResource:name ofType:@".asc"];
    NSFileManager* Manager = [NSFileManager defaultManager];
    NSData* data = [[NSData alloc] init];
    data = [Manager contentsAtPath:file];
    return data;
}

@end
