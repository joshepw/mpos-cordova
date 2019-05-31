package org.apache.cordova.pos;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Class;
import java.lang.reflect.Method;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dspread.xpos.EmvAppTag;
import com.dspread.xpos.EmvCapkTag;
import com.dspread.xpos.QPOSService;
import com.dspread.xpos.QPOSService.CardTradeMode;
import com.dspread.xpos.QPOSService.CommunicationMode;
import com.dspread.xpos.QPOSService.Display;
import com.dspread.xpos.QPOSService.DoTradeResult;
import com.dspread.xpos.QPOSService.EMVDataOperation;
import com.dspread.xpos.QPOSService.EmvOption;
import com.dspread.xpos.QPOSService.Error;
import com.dspread.xpos.QPOSService.QPOSServiceListener;
import com.dspread.xpos.QPOSService.TransactionResult;
import com.dspread.xpos.QPOSService.TransactionType;
import com.dspread.xpos.QPOSService.UpdateInformationResult;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.BroadcastReceiver;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.webkit.JavascriptInterface;

public class MPos extends CordovaPlugin {
    private PosListener listener;
    private QPOSService pos;
    private BluetoothAdapter btAdapter;

    ArrayList<String> list;
    private List<Map<String, ?>> data = new ArrayList<Map<String, ?>>();
    private Activity activity;
    private CordovaWebView webView;

    private boolean isBTScanning = false;
    private int scanBluetoothTime = 10;
    private int doTradeTime = 30;
    private int swipeTries = 0;

    private String tradeAmount = "";
    private String tradeCurrency = "999";
    private String tradeDescription = "";
    private String tradeTerminalTime;
    private TransactionType tradeTransactionType = TransactionType.SALE;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        return super.execute(action, args, callbackContext);
    }

    @Override
    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("scanBTDevices"))
            scanBTDevices(args);
        else if (action.equals("connectBTDevice"))
            connectBTDevice(args);
        else if (action.equals("disconnectBTDevice"))
            disconnectBTDevice(args);
        else if (action.equals("getInfoBTDevice"))
            getInfoBTDevice(args);
        else if (action.equals("doTradeSale"))
            doTradeSale(args);
        else if (action.equals("enterPIN"))
            enterPIN(args);
        else
            return false;

        return true;
    }

    // CORDOVA EVENTS
    private void scanBTDevices(CordovaArgs args) throws JSONException {
        logTrace("+++sdkVersion: " + pos.getSdkVersion());

        if (!btAdapter.isEnabled())
            btAdapter.enable();

        Set<BluetoothDevice> boundedDevices = btAdapter.getBondedDevices();

        for (BluetoothDevice device : boundedDevices) {
            listener.onDeviceFound(device);
        }

    	final BroadcastReceiver discoverReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    listener.onDeviceFound(device);
                } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                    listener.onRequestDeviceScanFinished();
                    cordova.getActivity().unregisterReceiver(this);
                }
            }
        };

        isBTScanning = true;
        dispatchJSEvent("onStartScanBT");
        // pos.scanQPos2Mode(activity, scanBluetoothTime);

        Activity activity = cordova.getActivity();
        activity.registerReceiver(discoverReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
        activity.registerReceiver(discoverReceiver, new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED));
        btAdapter.startDiscovery();
    }

    private void connectBTDevice(CordovaArgs args) throws JSONException {
        String address = args.getString(0);

        if(isBTScanning)
            btAdapter.cancelDiscovery();

        pos.connectBluetoothDevice(true, 20, address);
    }

    private void disconnectBTDevice(CordovaArgs args) throws JSONException {
        pos.disconnectBT();
    }

    private void getInfoBTDevice(CordovaArgs args) throws JSONException {
        pos.getQposInfo();
    }

    private void doTradeSale(CordovaArgs args) throws JSONException {
        swipeTries = 0;

        tradeAmount = args.getString(0);
        tradeCurrency = args.getString(1);
        tradeDescription = args.getString(2);

        tradeTerminalTime = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
        pos.doTrade(doTradeTime);
    }

    private void enterPIN(CordovaArgs args) throws JSONException {
        String pin = args.getString(0);

        if (pin.length() >= 4 && pin.length() <= 12)
            pos.sendPin(pin);
        else
            pos.cancelPin();
    }

    // METHODS
    private void logTrace(String string) {
        callJS("console.warn('[LOG] " + string + "');");
    }

    private void logTrace(Exception exception) {
        callJS("console.warn('[EXC] " + exception.toString() + "');");
    }

    private void dispatchJSEvent(final String event) {
        callJS("cordova.plugins.mPos.on('" + event + "');");
    }

    private void dispatchJSEvent(final String event, final String jsonData) {
        callJS("cordova.plugins.mPos.on('" + event + "', " + jsonData + ");");
    }

    private void callJS(final String js) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("javascript:" + js);
            }
        });
    }

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        this.activity = cordova.getActivity();
        this.webView = webView;

        open(CommunicationMode.BLUETOOTH);
    }

    private void open(CommunicationMode mode) {
        logTrace("+++Open");

        listener = new PosListener();
        pos = QPOSService.getInstance(mode);

        if (pos == null) {
            logTrace("+++CommunicationMode unknow");
            return;
        }

        pos.setConext(cordova.getActivity());
        Handler handler = new Handler(Looper.myLooper());
        pos.initListener(handler, listener);
        btAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    // LISTENERS
    class PosListener implements QPOSServiceListener {
        @Override
        public void getMifareFastReadData(Hashtable<String, String> arg0) {
        }

        @Override
        public void getMifareCardVersion(Hashtable<String, String> arg0) {
        }

        @Override
        public void getMifareReadData(Hashtable<String, String> arg0) {
        }

        @Override
        public void onAddKey(boolean arg0) {
        }

        @Override
        public void onBluetoothBoardStateResult(boolean arg0) {
        }

        @Override
        public void onBluetoothBondFailed() {
            logTrace("+++onBluetoothBoudFailed");
        }

        @Override
        public void onBluetoothBondTimeout() {
            logTrace("+++onBluetoothBoudTimeout");
        }

        @Override
        public void onBluetoothBonded() {
            logTrace("+++onBluetoothBouded");
        }

        @Override
        public void onBluetoothBonding() {
            logTrace("+++onBluetoothBouding");
        }

        @Override
        public void onCbcMacResult(String arg0) {
        }

        @Override
        public void onConfirmAmountResult(boolean arg0) {
        }

        @Override
        public void onDeviceFound(BluetoothDevice arg0) {
            if (arg0 != null) {
                logTrace("+++onDeviceFound");
                
                String name = arg0.getName();
                String address = arg0.getAddress();

                logTrace("+++Scaned the device:\n" + name + "(" + address + ")");
                
                if (name.startsWith("MPOS") || name.startsWith("QPOS") || name.startsWith("SPOS")) {
                    dispatchJSEvent("onFoundBTDevice", "{name: '" + name + "', address: '" + address + "'}");
                }
            }
        }

        @Override
        public void onDoTradeResult(DoTradeResult arg0, Hashtable<String, String> arg1) {
            String display = "";
            String data = null;

            if (arg0 == DoTradeResult.NONE) {
                display = "NO_CARD_DETECTED";

                if (swipeTries > 3) {
                    swipeTries = 0;
                } else {
                    swipeTries++;
                    pos.doTrade(doTradeTime);
                }
            } else if (arg0 == DoTradeResult.ICC) {
                display = "CARD_INSERTED_ICC";
                pos.doEmvApp(EmvOption.START);
            } else if (arg0 == DoTradeResult.NOT_ICC) {
                display = "CARD_INSERTED_NOT_ICC";
            } else if (arg0 == DoTradeResult.MCR) {
                display = "CARD_MCR";
                JSONObject jsonObj = new JSONObject(arg1);
                data = jsonObj.toString();
            } else if ((arg0 == DoTradeResult.NFC_ONLINE) || (arg0 == DoTradeResult.NFC_OFFLINE)) {
                display = "CARD_TAP";
                Hashtable<String, String> tlv = pos.getNFCBatchData();
                data = tlv.get("tlv");
            } else if ((arg0 == DoTradeResult.NFC_DECLINED)) {
                display = "CARD_TAP_DECLINED";
            } else if (arg0 == DoTradeResult.NO_RESPONSE) {
                display = "CARD_NO_RESPONSE";
            } else if (arg0 == DoTradeResult.BAD_SWIPE) {
                display = "BAD_SWIPE";

                if (swipeTries > 3) {
                    swipeTries = 0;
                } else {
                    swipeTries++;
                    pos.doTrade(doTradeTime);
                }
            }

            if(data != null)
                dispatchJSEvent("onDoTradeResponse", "{code:'" + display + "', data: "+data+"}");
            else
                dispatchJSEvent("onDoTradeResponse", "{code:'" + display + "'}");
        }

        @Override
        public void onEmvICCExceptionData(String arg0) {
        }

        @Override
        public void onEncryptData(String arg0) {
        }

        @Override
        public void onError(Error arg0) {
            String display = "";

            if (arg0 == Error.CMD_NOT_AVAILABLE) {
                display = "COMMAND_NOT_AVAILABLE";
            } else if (arg0 == Error.TIMEOUT) {
                display = "DEVICE_TIMEOUT";
            } else if (arg0 == Error.DEVICE_RESET) {
                display = "DEVICE_RESET";
            } else if (arg0 == Error.UNKNOWN) {
                display = "UNKNOWN_ERROR";
            } else if (arg0 == Error.DEVICE_BUSY) {
                display = "DEVICE_BUSY";
            } else if (arg0 == Error.INPUT_OUT_OF_RANGE) {
                display = "INPUT_OUT_OF_RANGE";
            } else if (arg0 == Error.INPUT_INVALID_FORMAT) {
                display = "INPUT_INVALID_FORMAT";
            } else if (arg0 == Error.INPUT_ZERO_VALUES) {
                display = "INPUT_ZERO_VALUES";
            } else if (arg0 == Error.INPUT_INVALID) {
                display = "INPUT_INVALID";
            } else if (arg0 == Error.CASHBACK_NOT_SUPPORTED) {
                display = "CASHBACK_NOT_SUPPORTED";
            } else if (arg0 == Error.CRC_ERROR) {
                display = "CRC_ERROR";
            } else if (arg0 == Error.COMM_ERROR) {
                display = "COMM_ERROR";
            } else if (arg0 == Error.MAC_ERROR) {
                display = "MAC_ERROR";
            } else if (arg0 == Error.CMD_TIMEOUT) {
                display = "CMD_TIMEOUT";
            }

            dispatchJSEvent("onErrorResponse", "{code:'" + display + "'}");
        }

        @Override
        public void onFinishMifareCardResult(boolean arg0) {
        }

        @Override
        public void onGetCardNoResult(String arg0) {
        }

        @Override
        public void onGetInputAmountResult(boolean arg0, String arg1) {
        }

        @Override
        public void onGetPosComm(int arg0, String arg1, String arg2) {
        }

        @Override
        public void onGetShutDownTime(String arg0) {
        }

        @Override
        public void onGetSleepModeTime(String arg0) {
        }

        @Override
        public void onLcdShowCustomDisplay(boolean arg0) {
        }

        @Override
        public void onOperateMifareCardResult(Hashtable<String, String> arg0) {
        }

        @Override
        public void onPinKey_TDES_Result(String arg0) {
        }

        @Override
        public void onQposDoGetTradeLog(String arg0, String arg1) {
        }

        @Override
        public void onQposDoGetTradeLogNum(String arg0) {
        }

        @Override
        public void onQposDoSetRsaPublicKey(boolean arg0) {
        }

        @Override
        public void onQposDoTradeLog(boolean arg0) {
        }

        @Override
        public void onQposGenerateSessionKeysResult(Hashtable<String, String> arg0) {
        }

        @Override
        public void onQposIdResult(Hashtable<String, String> arg0) {
        }

        @Override
        public void onQposInfoResult(Hashtable<String, String> arg0) {
            JSONObject jsonObj = new JSONObject(arg0);

            logTrace("+++onQposInfoResult" + arg0);
            dispatchJSEvent("onDeviceInfoResponse", jsonObj.toString());
        }

        @Override
        public void onQposIsCardExist(boolean arg0) {
        }

        @Override
        public void onQposKsnResult(Hashtable<String, String> arg0) {
        }

        @Override
        public void onReadBusinessCardResult(boolean arg0, String arg1) {
        }

        @Override
        public void onReadMifareCardResult(Hashtable<String, String> arg0) {
        }

        @Override
        public void onRequestBatchData(String arg0) {
            if(arg0 == null)
                arg0 = "";

            dispatchJSEvent("onDoTradeResponse", "{code:'TLV_BATCH_DATA', data:'" + arg0 + "'}");
        }

        @Override
        public void onRequestCalculateMac(String arg0) {
        }

        @Override
        public void onRequestDeviceScanFinished() {
            logTrace("+++scan finished");
            isBTScanning = false;
            dispatchJSEvent("onFinishScanBT");
        }

        @Override
        public void onRequestDisplay(Display arg0) {
            String msg = "";

            if (arg0 == Display.CLEAR_DISPLAY_MSG) {
                msg = "CLEAR_DISPLAY_MSG";
            } else if (arg0 == Display.PLEASE_WAIT) {
                msg = "PLEASE_WAIT";
            } else if (arg0 == Display.REMOVE_CARD) {
                msg = "PLEASE_REMOVE_CARD";
            } else if (arg0 == Display.TRY_ANOTHER_INTERFACE) {
                msg = "PLEASE_TRY_ANOTHER_INTERFACE";
            } else if (arg0 == Display.PROCESSING) {
                msg = "PROCESSING";
            } else if (arg0 == Display.INPUT_PIN_ING) {
                msg = "PLEASE_INPUT_PIN";
            } else if (arg0 == Display.MAG_TO_ICC_TRADE) {
                msg = "PLEASE_INSERT_CHIP";
            } else if (arg0 == Display.CARD_REMOVED) {
                msg = "CARD_REMOVED";
            }

            dispatchJSEvent("onRequestDisplay", "{code:'" + msg + "'}");
        }

        @Override
        public void onRequestFinalConfirm() {
            pos.finalConfirm(true);
        }

        @Override
        public void onRequestIsServerConnected() {
            logTrace("+++onRequestIsServerConnected");
            pos.isServerConnected(true);
        }

        @Override
        public void onRequestNoQposDetected() {
            logTrace("+++onRequestNoQposDetected");
            dispatchJSEvent("onPosUndetected");
        }

        @Override
        public void onRequestOnlineProcess(String arg0) {
            Hashtable<String, String> decodeData = pos.anlysEmvIccData(arg0);
			String tlv = decodeData.get("tlv");
            pos.sendOnlineProcessResult("8A023030" + tlv);

            logTrace("+++onRequestOnlineProcess: " + decodeData);
        }

        @Override
        public void onRequestQposConnected() {
            logTrace("+++onRequestQposConnected");
            dispatchJSEvent("onPosConnected");
            pos.getQposInfo();
        }

        @Override
        public void onRequestQposDisconnected() {
            logTrace("onRequestQposDisconnected");
            dispatchJSEvent("onPosDisconnected");
        }

        @Override
        public void onRequestSelectEmvApp(ArrayList<String> arg0) {
        }

        @Override
        public void onRequestSetAmount() {
            logTrace("+++onRequestSetAmount");
            pos.setPosDisplayAmountFlag(true);
            pos.setAmount(tradeAmount, tradeDescription, tradeCurrency, tradeTransactionType);
        }

        @Override
        public void onRequestSetPin() {
            logTrace("+++onRequestSetPin");
            dispatchJSEvent("onRequestPinEntry");
        }

        @Override
        public void onRequestSignatureResult(byte[] arg0) {
        }

        @Override
        public void onRequestTime() {
            logTrace("+++onRequestTime: " + tradeTerminalTime);
            pos.sendTime(tradeTerminalTime);
        }

        @Override
        public void onRequestTransactionLog(String arg0) {
            logTrace("+++onRequestTransactionLog: " + arg0);
            dispatchJSEvent("onRequestTransactionLog", "'" + arg0 + "'");
        }

        @Override
        public void onRequestTransactionResult(TransactionResult arg0) {
            String display = "";

			if (arg0 == TransactionResult.APPROVED) {
				display = "TRANSACTION_RESULT_APPROVED";
			} else if (arg0 == TransactionResult.TERMINATED) {
				display = "TERMINATED";
			} else if (arg0 == TransactionResult.DECLINED) {
				display = "DECLINED";
			} else if (arg0 == TransactionResult.CANCEL) {
				display = "CANCEL";
			} else if (arg0 == TransactionResult.CAPK_FAIL) {
				display = "CAPK_FAIL";
			} else if (arg0 == TransactionResult.NOT_ICC) {
				display = "NOT_ICC";
			} else if (arg0 == TransactionResult.SELECT_APP_FAIL) {
				display = "SELECT_APP_FAIL";
			} else if (arg0 == TransactionResult.DEVICE_ERROR) {
				display = "DEVICE_ERROR";
			} else if(arg0 == TransactionResult.TRADE_LOG_FULL){
				display = "TRADE_LOG_FULL";
			} else if (arg0 == TransactionResult.CARD_NOT_SUPPORTED) {
				display = "CARD_NOT_SUPPORTED";
			} else if (arg0 == TransactionResult.MISSING_MANDATORY_DATA) {
				display = "MISSING_MANDATORY_DATA";
			} else if (arg0 == TransactionResult.CARD_BLOCKED_OR_NO_EMV_APPS) {
				display = "CARD_BLOCKED_OR_NO_EMV_APPS";
			} else if (arg0 == TransactionResult.INVALID_ICC_DATA) {
				display = "INVALID_ICC_DATA";
			} else if (arg0 == TransactionResult.FALLBACK) {
				display = "FALLBACK";
			} else if (arg0 == TransactionResult.NFC_TERMINATED) {
				display = "NFC_TERMINATED";
			} else if (arg0 == TransactionResult.CARD_REMOVED) {
				display = "CARD_REMOVED";
            }
            
            logTrace("+++onRequestTransactionResult: " + display);
            dispatchJSEvent("onRequestTransactionResult", "{code:'" + display + "'}");
        }

        @Override
        public void onRequestUpdateKey(String arg0) {
        }

        @Override
        public void onRequestUpdateWorkKeyResult(UpdateInformationResult arg0) {
        }

        @Override
        public void onRequestWaitingUser() {
            logTrace("+++onRequestWaitingUser: Please insert/swipe card");
            dispatchJSEvent("onRequestCard");
        }

        @Override
        public void onReturnApduResult(boolean arg0, String arg1, int arg2) {
        }

        @Override
        public void onReturnBatchSendAPDUResult(LinkedHashMap<Integer, String> arg0) {
        }

        @Override
        public void onReturnCustomConfigResult(boolean arg0, String arg1) {
        }

        @Override
        public void onReturnDownloadRsaPublicKey(HashMap<String, String> arg0) {
        }

        @Override
        public void onReturnGetEMVListResult(String arg0) {
        }

        @Override
        public void onReturnGetPinResult(Hashtable<String, String> arg0) {
            // logTrace("+++onReturnGetPinResult: " + arg0.toString());
        }

        @Override
        public void onReturnGetQuickEmvResult(boolean arg0) {
        }

        @Override
        public void onReturnNFCApduResult(boolean arg0, String arg1, int arg2) {
        }

        @Override
        public void onReturnPowerOffIccResult(boolean arg0) {
        }

        @Override
        public void onReturnPowerOffNFCResult(boolean arg0) {
        }

        @Override
        public void onReturnPowerOnIccResult(boolean arg0, String arg1, String arg2, int arg3) {
        }

        @Override
        public void onReturnPowerOnNFCResult(boolean arg0, String arg1, String arg2, int arg3) {
        }

        @Override
        public void onReturnReversalData(String arg0) {
            if(arg0 == null)
                arg0 = "";

            dispatchJSEvent("onDoTradeResponse", "{code:'TLV_REVERSAL_BATCH_DATA', data: '" + arg0 + "'}");
        }

        @Override
        public void onReturnSetMasterKeyResult(boolean arg0) {
            dispatchJSEvent("onSetMasterKeyResponse", arg0 ? "true" : "false");
        }

        @Override
        public void onReturnSetSleepTimeResult(boolean arg0) {
        }

        @Override
        public void onReturnUpdateEMVRIDResult(boolean arg0) {
        }

        @Override
        public void onReturnUpdateEMVResult(boolean arg0) {
        }

        @Override
        public void onReturnUpdateIPEKResult(boolean arg0) {
        }

        @Override
        public void onReturniccCashBack(Hashtable<String, String> arg0) {
        }

        @Override
        public void onSearchMifareCardResult(Hashtable<String, String> arg0) {
        }

        @Override
        public void onSetBuzzerResult(boolean arg0) {
        }

        @Override
        public void onSetManagementKey(boolean arg0) {
        }

        @Override
        public void onSetParamsResult(boolean arg0, Hashtable<String, Object> arg1) {
        }

        @Override
        public void onSetSleepModeTime(boolean arg0) {
        }

        @Override
        public void onUpdateMasterKeyResult(boolean arg0, Hashtable<String, String> arg1) {
        }

        @Override
        public void onUpdatePosFirmwareResult(UpdateInformationResult arg0) {
        }

        @Override
        public void onVerifyMifareCardResult(boolean arg0) {
        }

        @Override
        public void onWaitingforData(String arg0) {
        }

        @Override
        public void onWriteBusinessCardResult(boolean arg0) {
        }

        @Override
        public void onWriteMifareCardResult(boolean arg0) {
        }

        @Override
        public void transferMifareData(String arg0) {
        }

        @Override
        public void verifyMifareULData(Hashtable<String, String> arg0) {
        }

        @Override
        public void writeMifareULData(String arg0) {
        }

        @Override
        public void onReturnRSAResult(String arg0) {
        }

        // NEW METHODS LISTENER
        @Override
        public void onRequestDevice() {
        }

        @Override
        public void onGetDevicePubKey(String var1) {
        }

        @Override
        public void onGetKeyCheckValue(List<String> var1) {
        }

        @Override
        public void onBatchReadMifareCardResult(String var1, Hashtable<String, List<String>> var2) {
        }

        @Override
        public void onBatchWriteMifareCardResult(String var1, Hashtable<String, List<String>> var2) {
        }

        @Override
        public void onGetBuzzerStatusResult(String var1) {
        }

        @Override
        public void onRequestNoQposDetectedUnbond() {
        }

        @Override
        public void onSetBuzzerStatusResult(boolean var1) {
        }

        @Override
        public void onSetBuzzerTimeResult(boolean var1) {
        }
    }
}
