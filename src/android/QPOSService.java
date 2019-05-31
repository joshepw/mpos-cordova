package org.apache.cordova.pos;

import android.app.AlertDialog.Builder;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences.Editor;
import android.content.res.AssetManager;
import android.hardware.usb.UsbDevice;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager.WakeLock;
import android.text.TextUtils;
import android.util.Log;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class QPOSService {
    private static final String hk = "2.8.9";
    private static final String hl = "2018/05/14";
    private static final boolean el = false;
    public static final String TAG = "QPOSService";
    private String hm;
    private QPOSService.DataFormat hn;
    private int timeout;
    private String ho;
    private Context P;
    public QPOSService.QPOSServiceListener listener;
    private QPOSService.EMVDataOperation hp;
    private String hq;
    private String hr;
    protected Handler handler;
    private QPOSService.d hs;
    private bl eQ;
    private bk ht;
    private boolean hu;
    private String hv;
    private boolean ef;
    private boolean hw;
    private static QPOSService.CommunicationMode hx;
    private boolean eo;
    private boolean hy;
    private String hz;
    private String hA;
    private int hB;
    private String hC;
    private List<BluetoothDevice> aa;
    private BroadcastReceiver ab;
    private Context mContext;
    private static QPOSService hD;
    private static BluetoothAdapter R;
    private com.dspread.xpos.d ei;
    private int hE;
    private static boolean hF;
    private int hG;
    private w hH;
    private String cp;
    protected static BroadcastReceiver hI;
    private static IntentFilter hJ;
    private QPOSService.CHECKVALUE_KEYTYPE hK;
    private int hL;
    private QPOSService.UsbOTGDriver hM;
    private QPOSService.EmvOption hN;
    protected bi ea;
    private Handler hO;
    private boolean eb;
    private String es;
    private String et;
    private String eu;
    private String ev;
    private String ew;
    private String ex;
    private String hP;
    private String ey;
    private int hQ;
    private int hR;
    private String hS;
    private String eO;
    private String hT;
    private int hU;
    private String em;
    private String hV;
    private int hW;
    private String hX;
    private int hY;
    private int hZ;
    private int en;
    private String ia;
    private String ib;
    private static boolean er;
    private int eq;
    private int ic;
    private String id;
    private String deviceAddress;
    private boolean ee;
    private boolean ec;
    private boolean ep;
    private String eP;
    private int ie;
    protected s if;
    private t eg;
    protected x ig;
    private av ih;
    private ao ii;
    private y ij;
    private ad ik;
    private v il;
    private al im;
    private ai io;
    public l console;
    private String ip;
    private ay iq;
    protected WakeLock ir;
    private String is;
    private static boolean it;
    private static QPOSService.BTCONNTYPE iu;
    private String iv;
    private int eN;
    private QPOSService.b iw;
    private boolean eC;
    private QPOSService.CardTradeMode ix;
    private boolean eK;
    protected boolean iy;
    private String iz;
    private String iA;
    private String iB;
    private String iC;
    private String iD;
    private int iE;
    protected QPOSService.Error iF;
    protected QPOSService.Display iG;
    protected QPOSService.TransactionResult iH;
    protected QPOSService.DoTradeResult iI;
    private boolean iJ;
    private boolean iK;
    private int iL;
    private int iM;
    private String iN;
    private String message;
    private int iO;
    private String iP;
    private QPOSService.f iQ;
    private int iR;
    private String iS;
    private String iT;
    private int iU;
    private int iV;
    private int iW;
    private QPOSService.e iX;
    protected QPOSService.DoTradeMode iY;
    private boolean eS;
    private LinkedHashMap<Integer, String[]> iZ;
    private boolean ja;
    private boolean jb;
    private boolean jc;
    private Context eA;
    private boolean jd;
    protected boolean fI;
    private UsbDevice ez;

    public void unregisterBlue() {
        this.P.registerReceiver(hI, hJ);
    }

    public String getMifareStatusMsg() {
        return this.hH == null ? null : this.R(this.hH.aS());
    }

    private String R(String status) {
        String staCode = null;
        byte var4 = -1;
        switch(status.hashCode()) {
        case 1536:
            if (status.equals("00")) {
                var4 = 0;
            }
            break;
        case 1537:
            if (status.equals("01")) {
                var4 = 1;
            }
            break;
        case 1538:
            if (status.equals("02")) {
                var4 = 2;
            }
            break;
        case 1539:
            if (status.equals("03")) {
                var4 = 3;
            }
            break;
        case 1540:
            if (status.equals("04")) {
                var4 = 4;
            }
            break;
        case 1541:
            if (status.equals("05")) {
                var4 = 5;
            }
            break;
        case 1542:
            if (status.equals("06")) {
                var4 = 6;
            }
            break;
        case 1543:
            if (status.equals("07")) {
                var4 = 7;
            }
            break;
        case 1557:
            if (status.equals("0E")) {
                var4 = 8;
            }
            break;
        case 1558:
            if (status.equals("0F")) {
                var4 = 9;
            }
            break;
        case 1567:
            if (status.equals("10")) {
                var4 = 10;
            }
            break;
        case 2240:
            if (status.equals("FF")) {
                var4 = 11;
            }
        }

        switch(var4) {
        case 0:
            staCode = "SUCCESS";
            break;
        case 1:
            staCode = "NFC_MIFARE_PARAM_ERROR";
            break;
        case 2:
            staCode = "NFC_MIFARE_TIMEOUT_ERROR";
            break;
        case 3:
            staCode = "NFC_MIFARE_CRC_ERROR";
            break;
        case 4:
            staCode = "NFC_MIFARE_NACK_ERROR";
            break;
        case 5:
            staCode = "NFC_MIFARE_POLL_ERROR";
            break;
        case 6:
            staCode = "NFC_MIFARE_OPERATION_ERROR";
            break;
        case 7:
            staCode = "NFC_MIFARE_ERROR_END";
            break;
        case 8:
            staCode = "NFC_ERR_ERROR";
            break;
        case 9:
            staCode = "NFC_ERR_BLOCK_ADDR";
            break;
        case 10:
            staCode = "NFC_ERR_READ_FAIL";
            break;
        case 11:
            staCode = "NFC_ERR_WRITE_FAIL";
        }

        return staCode;
    }

    public void onNeedPermissionDevice() {
        this.ht.dK();
    }

    public void onRequestDevice() {
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onRequestDevice();
                }

            }
        });
    }

    public void setPermissionDevice(UsbDevice permissionDevice) {
        ((be)this.ea).setUsbDevice(permissionDevice);
        this.ht.az("PERMISSION_ALREADY");
    }

    public void getKeyCheckValue(int keyIndex, QPOSService.CHECKVALUE_KEYTYPE checkvalue_keytype) {
        if (checkvalue_keytype == QPOSService.CHECKVALUE_KEYTYPE.DUKPT_EMV_KSN || checkvalue_keytype == QPOSService.CHECKVALUE_KEYTYPE.DUKPT_TRK_KSN || checkvalue_keytype == QPOSService.CHECKVALUE_KEYTYPE.DUKPT_PIN_KSN) {
            this.hL = 10;
        }

        this.getKeyCheckValue(keyIndex, checkvalue_keytype, 5, this.hL);
    }

    public void getKeyCheckValue(int keyIndex, QPOSService.CHECKVALUE_KEYTYPE checkvalue_keytype, int timeout, int checkValueLen) {
        aq.ag("QPOSService getQposId");
        this.timeout = timeout;
        this.eq = keyIndex;
        this.hK = checkvalue_keytype;
        this.hL = checkValueLen;
        if (this.aD()) {
            this.a(QPOSService.a.ln);
        }
    }

    public void onGetKeyCheckValue(final List<String> checkValues) {
        this.s("onGetPosComm");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onGetKeyCheckValue(checkValues);
                }

            }
        });
    }

    public void getDevicePublicKey(int timeout) {
        this.timeout = timeout;
        if (this.aD()) {
            this.a(QPOSService.a.lm);
        }
    }

    public void onGetDevicePubKey(final String clearKeys) {
        this.s("onGetDevicePubKey");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onGetDevicePubKey(clearKeys);
                }

            }
        });
    }

    public void setUsbSerialDriver(QPOSService.UsbOTGDriver driver) {
        if (driver != null) {
            this.hM = driver;
        }

        if (this.ea instanceof be) {
            ((be)this.ea).a(this.hM);
        }

    }

    protected QPOSService.DataFormat bs() {
        return this.hn;
    }

    public void openLog(boolean flag) {
        aq.oY = flag;
    }

    public boolean scanQPos2Mode(Context context, long time) {
        if (this.ei == null && hD != null) {
            this.ei = com.dspread.xpos.d.a(hD);
        }

        this.ei.j();
        this.ei.a(context, time);
        return true;
    }

    public void clearBluetoothBuffer() {
        if (this.ei != null && hD != null) {
            this.ei.g();
        }

    }

    private void a(boolean enable, int timeout) {
        if (hD == null || this.iq == null) {
            this.iq = ay.de();
        }

        if (this.iq != null) {
            this.iq.b(enable, timeout);
            aq.af("scanQposBLE");
        }

    }

    public void startScanQposBLE(int timeout) {
        this.a(true, timeout);
    }

    public void stopScanQposBLE() {
        this.a(false, 5);
    }

    protected String bt() {
        return this.ia;
    }

    public List<BluetoothDevice> getBLElist() {
        return this.iq != null ? this.iq.dd() : null;
    }

    public boolean connectBLE(String blueToothAddress) {
        if (this.ea == null) {
            return true;
        } else if (this.az()) {
            this.onError(QPOSService.Error.DEVICE_BUSY);
            return false;
        } else if (!(this.ea instanceof ay)) {
            aq.af("connectBT: is not VPosBluetoothBLE");
            this.onError(QPOSService.Error.UNKNOWN);
            this.e(false);
            return false;
        } else if (blueToothAddress != null && !"".equals(blueToothAddress)) {
            this.cp = blueToothAddress;
            if (this.ea.V() != null && !this.ea.V().equals("")) {
                if (!blueToothAddress.equals(this.ea.V())) {
                    aq.ag(">>>>>>>>>>>>>two buletooth");
                    this.ea.g(blueToothAddress);
                }
            } else {
                aq.ag("++++++++++++++++++++++++++");
                this.ea.g(blueToothAddress);
            }

            this.e(true);
            this.j(30011);
            return true;
        } else {
            this.ea.g(blueToothAddress);
            this.onRequestQposDisconnected();
            return false;
        }
    }

    public void disconnectBLE() {
        if (this.iq != null) {
            this.iq.disconnectBLE();
            this.iq.close();
        }

    }

    public void setOnlineTime(int time) {
        if (time > 2550) {
            this.onError(QPOSService.Error.INPUT_OUT_OF_RANGE);
        } else {
            this.hE = time;
        }
    }

    protected int bu() {
        return this.hE;
    }

    public List<BluetoothDevice> getDeviceList() {
        if (this.ei != null) {
            this.aa = this.ei.getDeviceList();
        }

        return this.aa;
    }

    private void j() {
        if (this.ei != null) {
            this.ei.j();
        }

    }

    public void stopScanQPos2Mode() {
        this.j();
    }

    public void unregisterBoardCast() {
        this.ei.unregisterBoardCast();
    }

    private QPOSService() {
        this.hn = QPOSService.DataFormat.OLD;
        this.timeout = 5;
        this.ho = "";
        this.hq = "";
        this.hr = "";
        this.hs = null;
        this.hu = false;
        this.hv = "00";
        this.ef = false;
        this.hw = false;
        this.eo = false;
        this.hy = false;
        this.hz = "";
        this.hA = "";
        this.hB = 0;
        this.ab = null;
        this.ei = null;
        this.hE = 200;
        this.hG = 0;
        this.cp = "";
        this.hL = 3;
        this.hM = QPOSService.UsbOTGDriver.CDCACM;
        this.ea = null;
        this.hO = null;
        this.eb = false;
        this.es = "";
        this.et = "";
        this.ev = "";
        this.ew = "";
        this.ex = "";
        this.hP = "";
        this.ey = "";
        this.hQ = 0;
        this.hR = 0;
        this.hS = "";
        this.eO = "";
        this.hT = "";
        this.em = "";
        this.hV = "";
        this.hX = "";
        this.hY = 0;
        this.hZ = 0;
        this.en = 60;
        this.ia = "";
        this.ib = "";
        this.eq = 0;
        this.id = "";
        this.deviceAddress = "";
        this.ee = true;
        this.ec = false;
        this.ep = true;
        this.eP = "";
        this.ie = 0;
        this.iv = "";
        this.eN = 0;
        this.iw = QPOSService.b.lt;
        this.eC = true;
        this.ix = QPOSService.CardTradeMode.SWIPE_TAP_INSERT_CARD;
        this.eK = false;
        this.iz = "";
        this.iA = "";
        this.iB = "";
        this.iC = "";
        this.iD = "";
        this.iF = null;
        this.iG = null;
        this.iH = null;
        this.iI = null;
        this.iJ = false;
        this.iK = false;
        this.iL = 0;
        this.iM = 0;
        this.iN = "";
        this.message = "";
        this.iO = 0;
        this.iP = "";
        this.iR = 0;
        this.iX = QPOSService.e.lJ;
        this.iY = QPOSService.DoTradeMode.COMMON;
        this.eS = false;
        this.iZ = null;
        this.ja = false;
        this.jb = false;
        this.jc = false;
        this.fI = true;
        this.bw();
    }

    public void get_encpin(String pwd, String pan) {
        pwd = aw.byteArray2Hex(aw.ax(pwd));
        final String pin = aw.l(pwd, pan);
        this.handler.post(new Runnable() {
            public void run() {
                QPOSService.this.listener.onWaitingforData(pin);
            }
        });
    }

    public static QPOSService getQposService() {
        return hD;
    }

    public QPOSService setMTK() {
        ((bg)this.ea).du();
        return this;
    }

    public QPOSService setSamSung() {
        ((bg)this.ea).dv();
        return this;
    }

    public QPOSService setPinpad() {
        ((bg)this.ea).dw();
        return this;
    }

    public Set<BluetoothSocket> getConnectedSocketList() {
        return this.ea.getConnectedSocketList();
    }

    public void initListener(Handler handler, QPOSService.QPOSServiceListener listener) {
        this.listener = listener;
        this.handler = handler;
        if (this.ea instanceof bj) {
            AudioManager localAudioManager = (AudioManager)this.P.getSystemService("audio");
            boolean f = localAudioManager.isWiredHeadsetOn();
            if (f) {
                this.setVolume(this.P);
            }
        }

        this.ea.h(hD);
    }

    public void initListener(QPOSService.QPOSServiceListener listener) {
        aq.af("initListener2:" + listener);
        this.listener = listener;
        if (this.handler == null) {
            this.handler = new Handler();
        }

        this.ea.h(hD);
    }

    public boolean setContext(Context context) {
        return this.setConext(context);
    }

    public boolean setConext(Context context) {
        this.eA = context;
        u.a(context, getSdkVersion());
        if (context == null) {
            return false;
        } else if (this.P != null && this.P.equals(context)) {
            return true;
        } else if (this.ea == null) {
            aq.ah("audio---pos null--------------");
            return false;
        } else {
            if (this.P != null && !this.P.equals(context)) {
                if (this.ea instanceof bj) {
                    this.closeAudio();
                } else {
                    aq.ah("setConext nnnnnnnn----------------- ");
                }
            }

            this.P = context;
            boolean f = this.ea.a(context);
            return f;
        }
    }

    public boolean syncSetConext(Context context) {
        aq.ah("setConext;;;;;;;;;;;;;;;;;;;;;;;;; " + context);
        if (context == null) {
            return false;
        } else if (this.ea == null) {
            aq.ah("audio---pos null--------------");
            return false;
        } else {
            if (this.P != null && !this.P.equals(context)) {
                if (this.ea instanceof bj) {
                    this.closeAudio();
                } else {
                    this.ea.destroy();
                }
            }

            this.P = context;
            boolean f = this.ea.a(context);
            return f;
        }
    }

    public static QPOSService syncGetInstance(QPOSService.CommunicationMode mode, Context context) {
        if (hD == null) {
            hD = new QPOSService();
        }

        boolean f = true;
        f = hD.setPosMode(mode);
        if (!f) {
            return null;
        } else {
            f = hD.syncSetConext(context);
            return !f ? null : hD;
        }
    }

    protected static QPOSService.CommunicationMode bv() {
        return hx;
    }

    protected void a(QPOSService.CommunicationMode communicationMode) {
        hx = communicationMode;
        u.E(String.valueOf(hx));
    }

    public static QPOSService getInstance() {
        QPOSService s = getInstance(QPOSService.CommunicationMode.AUDIO);
        er = false;
        return s;
    }

    public boolean setPosMode(QPOSService.CommunicationMode mode) {
        aq.ah("[QPOSService]->setPosMode");
        hD.a(mode);
        this.f(false);
        if (QPOSService.CommunicationMode.AUDIO == mode) {
            aq.ah("CommunicationMode.AUDIO " + mode);
            hD.bF();
        } else {
            if (QPOSService.CommunicationMode.BLUETOOTH_VER2 == mode) {
                Log.w("POS_SDK", "This version has been abandoned");
                return false;
            }

            if (QPOSService.CommunicationMode.USB == mode) {
                hD.bz();
            } else if (QPOSService.CommunicationMode.UART_K7 == mode) {
                hD.bA();
            } else if (QPOSService.CommunicationMode.BLUETOOTH_2Mode == mode) {
                aq.ah("CommunicationMode.BLUETOOTH_2Mode " + mode);
                hD.bC();
            } else if (QPOSService.CommunicationMode.BLUETOOTH == mode) {
                aq.ah("CommunicationMode.BLUETOOTH " + mode);
                hD.ax();
            } else if (QPOSService.CommunicationMode.BLUETOOTH_4Mode == mode) {
                hD.bE();
            } else if (QPOSService.CommunicationMode.UART == mode) {
                hD.bx();
            } else if (QPOSService.CommunicationMode.UART_GOOD == mode) {
                hD.by();
            } else if (QPOSService.CommunicationMode.USB_OTG_CDC_ACM != mode && QPOSService.CommunicationMode.USB_OTG != mode) {
                if (QPOSService.CommunicationMode.BLUETOOTH_BLE != mode) {
                    return false;
                }

                hD.bD();
            } else {
                hD.aw();
            }
        }

        boolean f = true;
        aq.ah("setPosMode: this.context: " + this.P);
        return f;
    }

    public boolean isQuickEmv() {
        return this.hy;
    }

    public void setQuickEmv(boolean isQuickEmv) {
        this.hy = isQuickEmv;
    }

    public void setFormatId(QPOSService.FORMATID id) {
        if (id == QPOSService.FORMATID.MKSK) {
            this.is = "0002";
        } else if (id == QPOSService.FORMATID.LP) {
            this.is = "0007";
        } else if (id == QPOSService.FORMATID.MKSK_PLAIN) {
            this.is = "0008";
        } else if (id == QPOSService.FORMATID.MOSAMBEE) {
            this.is = "0018";
        } else if (id == QPOSService.FORMATID.SOFTPAY) {
            this.is = "0020";
        } else if (id == QPOSService.FORMATID.DUKPT) {
            this.is = "0000";
        }

    }

    public void setFormatId(String formatId) {
        this.is = formatId;
    }

    public String getFormatId() {
        return this.is;
    }

    public static QPOSService getInstance(QPOSService.CommunicationMode mode) {
        iu = QPOSService.BTCONNTYPE.AUTO;

        while(it) {
            aq.ah("[QPOSService] isDestroy ing");

            try {
                Thread.sleep(10L);
            } catch (InterruptedException var2) {
                var2.printStackTrace();
            }
        }

        if (hD == null) {
            aq.ah("[QPOSService] qposService getInstance() == null");
            hD = new QPOSService();
        }

        aq.ah("getInstance: " + mode);
        aq.ah("qposService: " + hD);
        boolean f = hD.setPosMode(mode);
        return !f ? null : hD;
    }

    public static QPOSService getInstance(QPOSService.CommunicationMode mode, QPOSService.BTCONNTYPE connType) {
        iu = connType;

        while(it) {
            aq.ah("[QPOSService] isDestroy ing");

            try {
                Thread.sleep(10L);
            } catch (InterruptedException var3) {
                var3.printStackTrace();
            }
        }

        if (hD == null) {
            hD = new QPOSService();
        }

        aq.ah("getInstance: " + mode);
        aq.ah("qposService: " + hD);
        boolean f = hD.setPosMode(mode);
        return !f ? null : hD;
    }

    private void bw() {
        this.if = new s(this);
        this.eg = new t(this);
        this.ig = new x(this);
        this.hH = new w(this);
        this.eQ = new bl(this);
        this.ht = new bk(this);
        this.ih = new av(this);
        this.ii = new ao(this);
        this.ij = new y(this);
        this.ik = new ad(this);
        this.il = new v(this);
        this.im = new al(this);
        this.io = new ai();
        this.console = new l(this);
        this.en = 60;
        this.eq = 0;
        er = true;
        this.setVolumeFlag(true);
        this.f(false);
    }

    private void bx() {
        aq.ah("[QPOSService] initUart:            ");
        this.ea = bf.ds();
        aq.ah("[QPOSService] initUart:            " + this.ea);
        this.setPosExistFlag(false);
    }

    private void aw() {
        this.ea = be.dm();
        this.setPosExistFlag(false);
    }

    private void by() {
        aq.ah("[QPOSService] initUartGood:            ");
        this.f(true);
        this.ea = am.cU();
        aq.ah("[QPOSService] initUartGood:            " + this.ea);
        this.setPosExistFlag(false);
    }

    private void bz() {
        this.ea = bh.dx();
        this.setPosExistFlag(false);
    }

    private void bA() {
        this.ea = bg.dt();
        this.setPosExistFlag(false);
    }

    private void bB() {
        this.ea = bb.di();
        this.setPosExistFlag(false);
    }

    private void bC() {
        if (this.ea != null && this.getBluetoothState()) {
            this.ea = null;
            this.ea = az.df();
            this.ea.a(iu);
        } else {
            this.ea = az.df();
            this.setPosExistFlag(false);
            this.ea.a(iu);
        }
    }

    private void bD() {
        if (this.ea != null) {
            this.ea = null;
            this.ea = ay.de();
            aq.af("initbluetoothble 1");
            this.ea.a(iu);
        } else {
            this.ea = ay.de();
            aq.af("initbluetoothble 2");
            this.setPosExistFlag(false);
            this.ea.a(iu);
        }
    }

    private void ax() {
        if (this.ea != null && this.getBluetoothState()) {
            this.ea = null;
            this.ea = ax.da();
            aq.af("initBluetooth1:" + this.ea);
            this.ea.a(iu);
        } else {
            this.ea = ax.da();
            aq.af("initBluetooth2:" + this.ea);
            this.setPosExistFlag(false);
            this.ea.a(iu);
        }
    }

    private void bE() {
        this.ea = ba.dg();
        this.setPosExistFlag(false);
    }

    private void bF() {
        this.ea = bj.dF();
        this.setPosExistFlag(false);
    }

    private Context getContext() {
        return this.P;
    }

    protected boolean aE() {
        return this.eo;
    }

    protected void f(boolean isByGood) {
        this.eo = isByGood;
    }

    protected int bG() {
        return this.ie;
    }

    protected void s(int emvL2KernelStatus) {
        this.ie = emvL2KernelStatus;
    }

    protected boolean bH() {
        return this.ec;
    }

    public void setPosInputAmountFlag(boolean flag) {
        this.ec = flag;
    }

    protected boolean aI() {
        return this.ep;
    }

    public void setPosDisplayAmountFlag(boolean flag) {
        this.ep = flag;
    }

    public void setAmountPoint(boolean flag) {
        if (flag) {
            this.iv = "01";
        } else {
            this.iv = "00";
        }

    }

    protected String bI() {
        return this.iv;
    }

    public void setVolume(Context context) {
        if (this.isSetVolumeFlag()) {
            u.aN();
        }
    }

    public boolean isSetVolumeFlag() {
        return this.ee;
    }

    public void setVolumeFlag(boolean isSetVolume) {
        this.ee = isSetVolume;
    }

    protected String bJ() {
        return this.ib;
    }

    private void S(String pinStr) {
        this.ib = pinStr;
    }

    protected QPOSService.EmvOption bK() {
        return this.hN;
    }

    protected void a(QPOSService.EmvOption emvOption) {
        this.hN = emvOption;
    }

    private String aB() {
        return this.em;
    }

    private void v(String lcdShowCustomDisplayStr) {
        this.em = lcdShowCustomDisplayStr;
    }

    private String bL() {
        return this.hS;
    }

    private void T(String updateFirmwareStr) {
        this.hS = updateFirmwareStr;
    }

    private boolean aD() {
        aq.ag("QPOSService isPosExistFlag");
        if (hD == null) {
            Log.i("POS_SDK", "[QPOSService] QPOSService is null");
            this.onError(QPOSService.Error.UNKNOWN);
            return false;
        } else {
            if (this.handler == null) {
                this.handler = new Handler();
            }

            if (this.listener == null) {
                Log.i("POS_SDK", "[QPOSService] QPOSServiceListener is null");
                this.onError(QPOSService.Error.UNKNOWN);
                return false;
            } else {
                int i = 0;
                if (this.iw == QPOSService.b.lu) {
                    while(this.iw != QPOSService.b.lv) {
                        if (this.iw == QPOSService.b.lt) {
                            this.onError(QPOSService.Error.UNKNOWN);
                            return false;
                        }

                        try {
                            Thread.sleep(10L);
                        } catch (InterruptedException var4) {
                            var4.printStackTrace();
                        }

                        if (i++ == 200) {
                            break;
                        }
                    }
                }

                if (this.az()) {
                    this.iX = QPOSService.e.lK;
                    this.s("isPosExistFlag");
                    this.j(30020);
                    return false;
                } else {
                    i = 0;
                    aq.ag("isTradeFlag: " + this.az());

                    while(this.az()) {
                        try {
                            Thread.sleep(10L);
                        } catch (InterruptedException var3) {
                            var3.printStackTrace();
                        }

                        if (i++ == 200) {
                            this.onError(QPOSService.Error.DEVICE_BUSY);
                            return false;
                        }

                        aq.ag("QPOSService isPosExistFlag disConnect() 2");
                        this.s("isPosExistFlag");
                    }

                    aq.ag("posExistFlag: " + this.ef);
                    if (!this.ef) {
                        boolean f = false;
                        if (!this.getBluetoothState()) {
                            aq.ag("posExistFlag getBluetoothState(): " + this.getBluetoothState());
                            f = this.f(1);
                            if (!f) {
                                this.onRequestNoQposDetected();
                            }
                        } else {
                            f = true;
                        }

                        return f;
                    } else {
                        return true;
                    }
                }
            }
        }
    }

    private boolean bM() {
        if (hD == null) {
            Log.i("POS_SDK", "[QPOSService] QPOSService is null");
            this.iF = QPOSService.Error.UNKNOWN;
            return false;
        } else {
            return this.ef;
        }
    }

    public void setPosExistFlag(boolean posExistFlag) {
        this.ef = posExistFlag;
    }

    protected boolean az() {
        return this.eb;
    }

    protected void e(boolean tradeFlag) {
        aq.ah("setTradeFlag;;;;;;;;;;;;;;;;;; " + tradeFlag);
        this.eb = tradeFlag;
    }

    protected int bN() {
        return this.hR;
    }

    protected void t(int selectEmvAppIndex) {
        this.hR = selectEmvAppIndex;
    }

    private void i(int what) {
        this.eN = what;
    }

    private void h(int what) {
    }

    private boolean f(int count) {
        boolean f = false;
        if (this.ea != null) {
            count = this.ea.dy();
        }

        for(int i = 0; i < count; ++i) {
            try {
                if (this.ea == null) {
                    break;
                }

                aq.ah("connect (i): " + i);
                this.ea.U(i);
                f = this.ea.Y();
            } catch (Exception var6) {
                aq.ah("open exception");
                f = false;
                var6.printStackTrace();
                break;
            }

            if (f) {
                break;
            }

            this.ea.destroy();
            if (count > 1) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException var5) {
                    var5.printStackTrace();
                }
            }
        }

        if (!f) {
            if (this.ea instanceof bb && !((bb)this.ea).dj() && this.ea.dC()) {
                return f;
            }

            this.onRequestNoQposDetected();
            aq.ah("bollean open false");
            this.e(f);
        }

        return f;
    }

    private boolean u(int count) {
        boolean f = false;
        int count = 1;

        for(int i = 0; i < count; ++i) {
            try {
                if (this.ea == null) {
                    break;
                }

                f = this.ea.Y();
            } catch (Exception var6) {
                aq.ah("open exception");
                f = false;
                var6.printStackTrace();
                break;
            }

            if (f) {
                break;
            }

            if (count > 1) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException var5) {
                    var5.printStackTrace();
                }
            }
        }

        if (!f) {
            if (this.ea instanceof bb && !((bb)this.ea).dj() && this.ea.dC()) {
                return f;
            }

            aq.ah("bollean open false");
            this.e(f);
        }

        return f;
    }

    protected void s(String functionname) {
        aq.ah("<<<<<<<<<<<<disConnect start: " + functionname);
        this.iw = QPOSService.b.lu;

        try {
            if (this.ea != null && this.eC) {
                this.ea.close();
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        this.ec = false;
        this.ex = "";
        this.ey = "";
        this.ev = "";
        this.eq = 0;
        this.e(false);
        this.eQ.dO();
        this.hw = true;
        this.if.a(com.dspread.xpos.s.a.dO);
        this.ix = QPOSService.CardTradeMode.SWIPE_TAP_INSERT_CARD;
        this.ie = 0;
        aq.ah("disConnect end>>>>>>>>>>>");
        this.iw = QPOSService.b.lv;
    }

    public void setAutomaticDisconnect(boolean flag) {
        this.eC = flag;
    }

    public String getPlainData(String ksn, String datastr) {
        return r.c(ksn, datastr);
    }

    public HashMap<Integer, Tlv> getTag(String data) {
        HashMap<Integer, Tlv> map = new HashMap();
        List<Tlv> list = ar.aj(data);

        for(int i = 0; i < list.size(); ++i) {
            Tlv tlv = (Tlv)list.get(i);
            tlv.setTag(tlv.getTag());
            tlv.setValue(tlv.getValue());
            map.put(i, tlv);
            aq.af("tag:" + tlv.getTag() + "value:" + tlv.getValue() + "\n");
        }

        return map != null ? map : null;
    }

    public void closeDevice() {
        this.eC = true;
        this.s("closeDevice");
    }

    public void setCardTradeMode(QPOSService.CardTradeMode cardTradeMode) {
        int i = 0;
        if (this.iw == QPOSService.b.lu) {
            while(this.iw != QPOSService.b.lv) {
                if (this.iw == QPOSService.b.lt) {
                    return;
                }

                try {
                    Thread.sleep(10L);
                } catch (InterruptedException var5) {
                    var5.printStackTrace();
                }

                if (i++ == 200) {
                    break;
                }
            }
        }

        i = 0;

        while(this.az()) {
            try {
                Thread.sleep(10L);
            } catch (InterruptedException var4) {
                var4.printStackTrace();
            }

            if (i++ == 200) {
                return;
            }

            this.s("disConnect");
        }

        this.ix = cardTradeMode;
    }

    protected boolean c(bi pos) {
        if (!this.f(1)) {
            return false;
        } else {
            i dc = new i(32, 0, 0, 5);
            pos.a(dc);
            j uc = pos.p(5);
            boolean f = this.b(uc);
            return f;
        }
    }

    protected boolean a(bi pos, boolean isCheckCmd) {
        i dc = new i(32, 0, 0, 5);
        pos.a(dc);
        j uc = pos.p(5);
        boolean f = true;
        if (isCheckCmd) {
            f = this.b(uc);
        }

        return f;
    }

    private boolean exit() {
        boolean f = false;

        try {
            this.s("exit disConnect() 1");
            Thread.sleep(50L);
            f = this.f(1);
            if (f) {
                for(int i = 0; i < 1; ++i) {
                    f = this.c(this.ea);
                    if (f) {
                        break;
                    }
                }
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        this.s("exit disConnect() 2");
        return f;
    }

    private void aG() {
        if (this.f(1)) {
            if (er) {
                this.aH();
            } else {
                this.eO = "";
                boolean f = this.eg.b(this.ea, this.es, this.en, this.eO, this.ev, this.ex, this.ey, this.eq, this.ix, this.eu, this.ew, this.eP);
                if (!f) {
                    return;
                }
            }

        }
    }

    private void bO() {
        if (this.f(1)) {
            aq.af("waitSetAmountState");
            boolean f = this.eQ.dN();
            if (f) {
                f = this.y(this.es);
                if (f) {
                    if (this.eu.equals("04")) {
                        f = this.z(this.et);
                    }

                    if (f) {
                        if (this.hP != "" && this.hP.length() > 0 && this.hP != null) {
                            f = this.eg.a(this.hQ, this.ex, this.es, this.ey, this.en, this.hP, this.ea);
                        } else {
                            f = this.eg.a(this.hQ, this.ex, this.es, this.ey, this.en, this.ea);
                        }

                        if (!f) {
                            return;
                        }
                    }
                }
            } else {
                this.s("doTrade_QF__");
                this.onRequestTransactionResult(QPOSService.TransactionResult.CANCEL);
            }

        }
    }

    private void bP() {
        if (this.f(1)) {
            if (er) {
                this.hw = false;
                this.onRequestTime();

                while(!this.hw) {
                    try {
                        Thread.sleep(30L);
                    } catch (InterruptedException var2) {
                        var2.printStackTrace();
                    }
                }

                this.onRequestDisplay(QPOSService.Display.PLEASE_WAIT);
                this.if.a(this.es, this.eu, this.ev, this.ew, this.ea, this.et);
            } else {
                this.bR();
            }

            this.s("doEmvTrade__");
        }
    }

    private Hashtable<String, Object> bQ() {
        Hashtable<String, Object> ht = null;
        boolean f = this.eQ.dN();
        aq.ah("set amount f = " + f);
        if (f) {
            aq.ah("set amount ");
            f = this.y(this.es);
            if (f) {
                if (this.eu.equals("04")) {
                    f = this.z(this.et);
                }

                if (f) {
                    ht = this.eg.a(this.ea, this.es, this.en, this.eO, this.ev, this.ex, this.ey, this.eq, this.ix);
                    if (!(Boolean)ht.get("result")) {
                        return ht;
                    }
                }
            }
        } else {
            this.onRequestTransactionResult(QPOSService.TransactionResult.CANCEL);
        }

        this.s("syncKeyboardTrade");
        return ht;
    }

    private void aH() {
        boolean f = this.eQ.dN();
        aq.ah("set amount f = " + f);
        if (f) {
            aq.ah("set amount ");
            f = this.y(this.es);
            aq.af("check==========");
            if (f) {
                if (this.eu.equals("04")) {
                    f = this.z(this.et);
                }

                if (f) {
                    if (this.aE()) {
                        aq.ah("pos:" + this.ea.toString() + "\ntradeAmount:" + this.es + "\ndo_trade_timeout:" + this.en + "\namountIcon:" + this.eO + "\nterminalTime:" + this.ev + "\nrandomString:" + this.ex + "\nrandomString:" + this.ex + "\nextraString:" + this.ey + "\nkeyIndex:" + this.eq + "\ncardTradeMode:" + this.ix + "\ntradeType:" + this.eu + "\ncurrencyCode:" + this.ew + "\ncustomDisplayString:" + this.eP);
                        f = this.console.a(this.ea, this.es, this.en, this.eO, this.ev, this.ex, this.ey, this.eq, this.ix, this.eu, this.ew, this.eP);
                    } else {
                        aq.ah("pos:" + this.ea.toString() + "\ntradeAmount:" + this.es + "\ndo_trade_timeout:" + this.en + "\namountIcon:" + this.eO + "\nterminalTime:" + this.ev + "\nrandomString:" + this.ex + "\nrandomString:" + this.ex + "\nextraString:" + this.ey + "\nkeyIndex:" + this.eq + "\ncardTradeMode:" + this.ix + "\ntradeType:" + this.eu + "\ncurrencyCode:" + this.ew + "\ncustomDisplayString:" + this.eP);
                        f = this.eg.b(this.ea, this.es, this.en, this.eO, this.ev, this.ex, this.ey, this.eq, this.ix, this.eu, this.ew, this.eP);
                    }

                    if (!f) {
                        return;
                    }
                }
            }
        } else {
            this.s("isKeyboardTrade");
            this.onRequestTransactionResult(QPOSService.TransactionResult.CANCEL);
        }

    }

    private void bR() {
        boolean f = this.eQ.dN();
        if (f) {
            aq.ah("set amount ");
            f = this.y(this.es);
            if (f) {
                if (this.eu.equals("04")) {
                    f = this.z(this.et);
                }

                if (f) {
                    this.hw = false;
                    this.onRequestTime();

                    while(!this.hw) {
                        try {
                            Thread.sleep(30L);
                        } catch (InterruptedException var3) {
                            var3.printStackTrace();
                        }
                    }

                    this.onRequestDisplay(QPOSService.Display.PLEASE_WAIT);
                    this.if.a(this.es, this.eu, this.ev, this.ew, this.ea, this.et);
                }
            } else {
                this.c(this.ea);
            }
        } else {
            this.c(this.ea);
            this.onRequestDisplay(QPOSService.Display.TRANSACTION_TERMINATED);
            this.onRequestDisplay(QPOSService.Display.REMOVE_CARD);
            this.onRequestTransactionResult(QPOSService.TransactionResult.TERMINATED);
        }

    }

    private boolean y(String tradeAmount) {
        boolean f = true;
        if (tradeAmount != null && !"".equals(tradeAmount)) {
            if ("FFFFFFFF".equals(tradeAmount)) {
                this.ec = true;
                aq.af("===========tradeAmount:" + tradeAmount);
                return f;
            }

            if ("00000000".equals(tradeAmount)) {
                this.es = "";
                return f;
            }

            if (tradeAmount.startsWith("0")) {
                this.onError(QPOSService.Error.INPUT_INVALID);
                f = false;
                return f;
            }

            try {
                long amount = Long.parseLong(tradeAmount);
                aq.af("amount===" + amount);
            } catch (NumberFormatException var5) {
                aq.ah("amount format error");
                this.onError(QPOSService.Error.INPUT_INVALID_FORMAT);
                f = false;
            }
        } else if (!this.eu.equals("05")) {
            this.onError(QPOSService.Error.INPUT_INVALID);
            f = false;
        }

        return f;
    }

    private boolean z(String cashbackAmount) {
        boolean f = true;
        if (cashbackAmount != null && !"".equals(cashbackAmount)) {
            if (cashbackAmount.length() <= 8 && !cashbackAmount.startsWith("0")) {
                try {
                    Integer.parseInt(cashbackAmount);
                } catch (NumberFormatException var4) {
                    aq.ah("amount format error");
                    this.onError(QPOSService.Error.INPUT_INVALID_FORMAT);
                    f = false;
                }
            } else {
                this.onError(QPOSService.Error.INPUT_INVALID);
                f = false;
            }
        } else {
            aq.ah("amount format error");
            this.onError(QPOSService.Error.INPUT_INVALID_FORMAT);
            f = false;
        }

        return f;
    }

    private void v(int timeout) {
        if (this.f(1)) {
            this.ig.c(this.ea, timeout);
        }
    }

    public boolean isCardExist(int timeout) {
        return !this.f(1) ? false : this.ig.c(this.ea, timeout);
    }

    protected void m(boolean isExit) {
        this.eK = isExit;
    }

    protected boolean bS() {
        return this.eK;
    }

    private void w(int timeout) {
        if (this.f(1)) {
            this.ig.d(this.ea, timeout);
        }
    }

    public void doSetRsaPublicKey(String pinTransactionData) {
        if (this.f(1)) {
            this.ig.c(this.ea, this.iz, this.iA, this.iB, this.iC, this.iD);
        }
    }

    protected boolean bT() {
        return this.iy;
    }

    protected void n(boolean charge) {
        this.iy = charge;
    }

    private void aF() {
        if (this.f(1)) {
            this.ig.d(this.ea);
        }
    }

    private void a(String keyType, String grounp, String rsaRegin, String rsaN, String rsaE) {
        if (this.f(1)) {
            aq.ah("generate session key");

            try {
                this.ig.b(this.ea, keyType, grounp, rsaRegin, rsaN, rsaE);
            } catch (Exception var7) {
                var7.printStackTrace();
            }

        }
    }

    private void a(String data, String keyType, String keyIndex, int timeout) {
        if (this.f(1)) {
            this.ig.b(this.ea, data, keyType, keyIndex, timeout);
        }
    }

    public void doSetManagementKey(String paras) {
        if (this.f(1)) {
            this.ig.d(this.ea, paras);
        }
    }

    public void updateEmvAPP(QPOSService.EMVDataOperation operationType, String appTLV) {
        if (this.f(1)) {
            this.hp = operationType;
            this.hr = appTLV;
            this.a(QPOSService.a.kE);
        }
    }

    public void updateEmvAPPByTlv(QPOSService.EMVDataOperation operationType, String appTLV) {
        this.updateEmvAPP(operationType, appTLV);
    }

    public void updateEmvCAPKByTlv(QPOSService.EMVDataOperation operationType, String ridTLV) {
        this.updateEmvCAPK(operationType, ridTLV);
    }

    public void updateEmvCAPK(QPOSService.EMVDataOperation operationType, String ridTLV) {
        if (this.f(1)) {
            this.hq = ridTLV;
            this.hp = operationType;
            aq.af("data====" + this.hq);
            this.a(QPOSService.a.kD);
        }
    }

    public void updateEmvAPP(QPOSService.EMVDataOperation operationType, ArrayList<String> appTag) {
        if (this.f(1)) {
            if (appTag == null) {
                appTag = new ArrayList();
            }

            int a;
            for(int i = 0; i < appTag.size(); ++i) {
                for(a = appTag.size() - 1; a > i; --a) {
                    if (appTag.get(i) == appTag.get(a)) {
                        appTag.remove(a);
                    }
                }
            }

            String data = "";
            this.hr = "";
            int a = false;
            if (appTag != null && appTag.size() > 0) {
                a = appTag.size();
                String[] lenArr = new String[a];

                for(int i = 0; i < a; ++i) {
                    String tagStr = ((String)appTag.get(i)).substring(0, 4);
                    String tagValue = ((String)appTag.get(i)).substring(4, ((String)appTag.get(i)).length());
                    if (tagValue.length() % 2 == 1) {
                        tagValue = "0" + tagValue;
                    }

                    if (tagStr.equals("9F16") || tagStr.equals("9F4E")) {
                        tagValue = aw.aq(tagValue);
                        int valLen = tagValue.length();
                        if (valLen < 30) {
                            for(int m = 0; m < 30 - valLen; ++m) {
                                tagValue = "0" + tagValue;
                            }
                        }
                    }

                    lenArr[i] = Integer.toHexString(tagValue.length() / 2);
                    if (lenArr[i].length() % 2 == 1) {
                        lenArr[i] = "0" + lenArr[i];
                    }

                    data = data + tagStr + lenArr[i] + tagValue;
                    aq.af("app data:" + data);
                }
            }

            this.hp = operationType;
            this.hr = data;
            this.a(QPOSService.a.kE);
        }
    }

    public void updateEmvAPP(QPOSService.EMVDataOperation operationType, String tagName, String appTag) {
        if (this.f(1)) {
            int len = appTag.length() / 2;
            String tagLen = Integer.toHexString(len);
            if (tagLen.length() % 2 != 0) {
                tagLen = "0" + tagLen;
            }

            this.hp = operationType;
            this.hr = tagName + tagLen + appTag;
            this.a(QPOSService.a.kE);
        }
    }

    public void setSleepModeTime(int time) {
        if (this.f(1)) {
            this.ig.n(this.ea, time);
        }
    }

    public void getSleepModeTime() {
        if (this.f(1)) {
            this.ig.j(this.ea);
        }
    }

    public void getShutDownTime() {
        if (this.f(1)) {
            this.ig.k(this.ea);
        }
    }

    public void doTradeLogOperation(QPOSService.DoTransactionType type, int data) {
        if (this.f(1)) {
            this.ig.a(this.ea, type, data);
        }
    }

    public void doTradeLogOperation(QPOSService.DoTransactionType type, String data) {
        if (this.f(1)) {
            this.ig.a(this.ea, type, data);
        }
    }

    public void setShutDownTime(int time) {
        this.setPosSleepTime(time);
    }

    public void getQuickEMVStatus(QPOSService.EMVDataOperation operationType, String data) {
        if (this.f(1)) {
            this.ih.d(this.ea, operationType, data);
        }
    }

    public void setQuickEmvStatus(boolean flag) {
        if (this.f(1)) {
            this.ih.a(this.ea, QPOSService.EMVDataOperation.update, flag);
        }
    }

    public void updateEmvCAPK(QPOSService.EMVDataOperation operationType, ArrayList<String> ridTag) {
        if (this.f(1)) {
            if (ridTag == null) {
                ridTag = new ArrayList();
            }

            int a;
            for(int i = 0; i < ridTag.size(); ++i) {
                for(a = ridTag.size() - 1; a > i; --a) {
                    if (ridTag.get(i) == ridTag.get(a)) {
                        ridTag.remove(a);
                    }
                }
            }

            String data = "";
            this.hq = "";
            int a = false;
            if (ridTag != null && ridTag.size() > 0) {
                a = ridTag.size();
                aq.ah("a=======" + a);
                String[] lenArr = new String[a];

                for(int i = 0; i < a; ++i) {
                    String tagStr = ((String)ridTag.get(i)).substring(0, 4);
                    String tagValue = ((String)ridTag.get(i)).substring(4, ((String)ridTag.get(i)).length());
                    if (tagValue.length() % 2 == 1) {
                        tagValue = "0" + tagValue;
                    }

                    lenArr[i] = Integer.toHexString(tagValue.length() / 2);
                    if (tagStr.equals("DF02") && Integer.parseInt(lenArr[i], 16) >= 128) {
                        if (lenArr[i].length() == 2) {
                            lenArr[i] = "81" + lenArr[i];
                        } else if (lenArr.length == 3) {
                            lenArr[i] = "820" + lenArr[i];
                        } else if (lenArr.length == 4) {
                            lenArr[i] = "82" + lenArr[i];
                        }
                    }

                    if (lenArr[i].length() % 2 == 1) {
                        lenArr[i] = "0" + lenArr[i];
                    }

                    data = data + tagStr + lenArr[i] + tagValue;
                }
            }

            this.hq = data;
            this.hp = operationType;
            aq.af("data====" + this.hq);
            this.a(QPOSService.a.kD);
        }
    }

    public void doUpdateIPEKOperation(String ipekgroup, String trackksn, String trackipek, String trackipekCheckvalue, String emvksn, String emvipek, String emvipekCheckvalue, String pinksn, String pinipek, String pinipekCheckvalue) {
        if (this.f(1)) {
            this.ih.a(this.ea, ipekgroup, trackksn, trackipek, trackipekCheckvalue, emvksn, emvipek, emvipekCheckvalue, pinksn, pinipek, pinipekCheckvalue);
        }
    }

    public void setBlockaddr(String blockaddr) {
        this.hA = blockaddr;
    }

    protected String bU() {
        return this.hA;
    }

    public void setKeyValue(String keyVlaue) {
        this.hC = keyVlaue;
    }

    public void setMafireLen(int dataLen) {
        this.hB = dataLen;
    }

    protected int bV() {
        return this.hB;
    }

    protected String bW() {
        return this.hC;
    }

    public void doMifareCard(String paras, int timeout) {
        if (this.f(1)) {
            this.timeout = timeout;
            String cmd;
            if (paras.startsWith("02")) {
                cmd = paras.substring(2, paras.length());
                if (cmd.equals("Key A")) {
                    this.hz = "60";
                } else if (cmd.equals("Key B")) {
                    this.hz = "61";
                }

                paras = "02" + this.hz;
                aq.af("mif-->" + paras + "====" + this.bU() + "---" + this.bW());
            } else if (paras.startsWith("05")) {
                cmd = paras.substring(2, paras.length());
                if (cmd.equals("add")) {
                    cmd = "C1";
                } else if (cmd.equals("reduce")) {
                    cmd = "C0";
                } else if (cmd.equals("restore")) {
                    cmd = "C2";
                }

                aq.af("05 cmd:" + cmd);
                paras = "05" + cmd;
            }

            this.hm = paras;
            this.a(QPOSService.a.ky);
        }
    }

    public void doSetBuzzerOperation(int paras) {
        if (this.f(1)) {
            this.ig.h(this.ea, paras);
        }
    }

    public void setBuzzerTime(int paras) {
        if (this.f(1)) {
            this.iE = paras;
            this.a(QPOSService.a.kz);
        }
    }

    public void setBuzzerStatus(int paras) {
        if (this.f(1)) {
            this.iE = paras;
            this.a(QPOSService.a.kA);
        }
    }

    public void getBuzzerStatus() {
        if (this.f(1)) {
            this.a(QPOSService.a.kB);
        }
    }

    private void bX() {
        if (this.f(1)) {
            if (this.hU != 0) {
                String len = Integer.toHexString(this.hU);
                if (len.length() == 1) {
                    len = "0" + len;
                }

                this.eg.c(this.ea, this.hT, len);
            } else {
                this.eg.c(this.ea, this.hT, "06");
            }

            this.hU = 0;
        }
    }

    private void bY() {
        if (this.f(1)) {
            this.ij.m(this.ea);
        }
    }

    private void bZ() {
        if (this.f(1)) {
            this.ik.u(this.ea, this.timeout);
        }
    }

    private void ca() {
        if (this.f(1)) {
            this.ih.w(this.ea, this.timeout);
        }
    }

    private void cb() {
        if (this.f(1)) {
            this.ih.h(this.ea, this.bL(), this.timeout);
        }
    }

    private void cc() {
        if (this.f(1)) {
            this.ih.a(this.ea, this.iU, this.iV, this.iW, this.bL(), this.timeout);
        }
    }

    private void cd() {
        if (this.f(1)) {
            this.ih.t(this.ea, this.bL(), this.timeout);
        }
    }

    private void ce() {
        if (this.f(1)) {
            this.ih.u(this.ea, this.bL(), this.timeout);
        }
    }

    private void cf() {
        if (this.f(1)) {
            this.ih.v(this.ea, this.bL(), this.timeout);
        }
    }

    private void cg() {
        if (this.f(1)) {
            this.ih.w(this.ea, this.bL(), this.timeout);
        }
    }

    private void ch() {
        if (this.f(1)) {
            this.ih.x(this.ea, this.bL(), this.timeout);
        }
    }

    private void ci() {
        if (this.f(1)) {
            this.ij.l(this.ea);
        }
    }

    private void cj() {
        if (this.f(1)) {
            this.ik.t(this.ea, this.timeout);
        }
    }

    private void ck() {
        if (this.f(1)) {
            this.ij.k(this.ea, this.hV);
        }
    }

    private String f(String apduString, int timeout) {
        if (!this.f(1)) {
            return null;
        } else {
            String apduStr = this.ij.d(this.ea, apduString, timeout);
            aq.af("sendApdu  -----2" + apduStr);
            return apduStr;
        }
    }

    private void cl() {
        if (this.f(1)) {
            this.ik.e(this.ea, this.hV, this.timeout);
        }
    }

    private void cm() {
        if (this.f(1)) {
            this.ig.b(this.ea, this.hW);
        }
    }

    private void cn() {
        if (this.f(1)) {
            this.il.f(this.ea);
        }
    }

    private void aC() {
        if (this.f(1)) {
            this.b(this.ea);
        }
    }

    private void x(int udpateWorkKey_timeout) {
        if (this.f(1)) {
            this.ih.d(this.ea, aw.an(this.bL()), udpateWorkKey_timeout);
        }
    }

    private void a(int udpateWorkKey_timeout, int isoFormat, int keyIndex, int pinLength, String pan, String message) {
        if (this.f(1)) {
            this.ih.a(this.ea, aw.an(this.bL()), udpateWorkKey_timeout, isoFormat, keyIndex, pinLength, pan, message);
        }
    }

    private void g(String transferStr, int udpateWorkKey_timeout) {
        if (this.f(1)) {
            this.ih.a(this.ea, aw.an(this.bL()), aw.an(transferStr), udpateWorkKey_timeout);
        }
    }

    private void y(int calcMacDouble_timeout) {
        if (this.f(1)) {
            if (this.timeout == 0) {
                this.timeout = 5;
            }

            if (this.iQ == QPOSService.f.lR) {
                this.ih.c(this.ea, aw.an(this.bL()), calcMacDouble_timeout);
            } else if (this.iQ == QPOSService.f.lS) {
                this.ih.b(this.ea, aw.an(this.bL()), calcMacDouble_timeout);
            } else if (this.iQ == QPOSService.f.lT) {
                this.ih.a(this.ea, aw.an(this.bL()), calcMacDouble_timeout);
            } else {
                aq.ag("QPOSService doCalculateMac disConnect()");
                this.s("doCalculateMac");
                this.onError(QPOSService.Error.CMD_NOT_AVAILABLE);
            }

        }
    }

    private void co() {
        if (this.f(1)) {
            this.ih.c(this.ea, aw.an(this.bL()));
        }
    }

    private void cp() {
        if (this.f(1)) {
            this.ii.p(this.ea);
        }
    }

    private void b(bi apos) {
        byte[] paras = aw.an(this.aB());
        i dc = new i(65, 16, this.en, paras);
        apos.a(dc);
        j uc = apos.p(5);
        boolean f = this.b(uc);
        if (f) {
            try {
                Thread.sleep((long)(this.en * 1000));
                if (this.az()) {
                    this.onLcdShowCustomDisplay(f);
                }

                this.eb = false;
            } catch (InterruptedException var7) {
                var7.printStackTrace();
            }
        }

    }

    private void cq() {
        if (this.f(1)) {
            String[] arrs = this.hX.split(",");
            this.hZ = 0;
            boolean f = this.im.a(this.ea, com.dspread.xpos.al.a.mk, this.hZ, arrs[0]);
            if (f) {
                this.hZ = 0;
                f = this.im.a(this.ea, com.dspread.xpos.al.a.ml, this.hZ, arrs[1]);
            }

            this.onReturnCustomConfigResult(f, "");
        }
    }

    private void cr() {
        if (this.f(1)) {
            this.hZ = 0;
            boolean f = this.im.a(this.ea, com.dspread.xpos.al.a.mk, this.hZ, this.hX);
            if (!f) {
                this.onReturnCustomConfigResult(f, "");
            }

        }
    }

    private void cs() {
        if (this.f(1)) {
            this.hZ = 0;
            boolean f = this.im.a(this.ea, com.dspread.xpos.al.a.ml, this.hZ, this.hX);
            if (!f) {
                this.onReturnCustomConfigResult(f, "");
            }

        }
    }

    private void ct() {
        if (this.f(1)) {
            boolean f = this.im.a(this.ea, com.dspread.xpos.al.a.ml, 0, 0);
            if (!f) {
                this.onReturnCustomConfigResult(f, "");
            }

        }
    }

    private void cu() {
        if (this.f(1)) {
            boolean f = this.im.a(this.ea, com.dspread.xpos.al.a.mk, 0, 0);
            if (!f) {
                this.onReturnCustomConfigResult(f, "");
            }

        }
    }

    private void cv() {
        if (this.f(1)) {
            boolean f = this.im.a(this.ea, com.dspread.xpos.al.a.mi, this.hZ, this.hX);
            this.onReturnCustomConfigResult(f, "");
        }
    }

    private void cw() {
        if (this.f(1)) {
            boolean f = this.im.a(this.ea, com.dspread.xpos.al.a.mi, this.hZ, this.hY);
            if (!f) {
                this.onReturnCustomConfigResult(f, "");
            }

        }
    }

    private void cx() {
        if (this.f(1)) {
            this.if.a(this.ea, this.et, this.hT, this.ex);
        }
    }

    private void z(int timeout) {
        if (this.f(1)) {
            this.ih.f(this.ea, this.bL(), timeout);
        }
    }

    private void A(int timeout) {
        if (this.f(1)) {
            this.ih.i(this.ea, this.bL(), timeout);
        }
    }

    private void B(int timeout) {
        if (this.f(1)) {
            this.ih.j(this.ea, this.bL(), timeout);
        }
    }

    private void C(int timeout) {
        if (this.f(1)) {
            this.ih.k(this.ea, this.bL(), timeout);
        }
    }

    private void D(int timeout) {
        if (this.f(1)) {
            this.ih.l(this.ea, this.bL(), timeout);
        }
    }

    private void E(int timeout) {
        if (this.f(1)) {
            this.ih.n(this.ea, this.bL(), timeout);
        }
    }

    private void F(int timeout) {
        if (this.f(1)) {
            this.ih.m(this.ea, this.bL(), timeout);
        }
    }

    private void G(int setMasterKey_timeout) {
        if (this.f(1)) {
            this.ih.g(this.ea, this.bL(), setMasterKey_timeout);
        }
    }

    private void cy() {
        if (this.f(1)) {
            this.ij.a(this.ea, this.iZ);
        }
    }

    private void cz() {
        if (this.f(1)) {
            this.ea.g(this.deviceAddress);
            if (this.ea instanceof ax) {
                ((ax)this.ea).q(false);
            }

            com.dspread.xpos.bi.b f = this.ea.a(this.deviceAddress, this.jd);
            if (this.ea instanceof ax) {
                ((ax)this.ea).q(true);
            }

            aq.ag("QPOSService doUpgradeCpu disConnect()");
            this.s("doUpgradeCpu");
            aq.ah("doUpgradeCpu retuslt : " + f);
            if (!LogFileConfig.getInstance().getWriteFlag()) {
                LogFileConfig.getInstance().deleteAllFile();
            }

            if (f == com.dspread.xpos.bi.b.sg) {
                this.onUpdatePosFirmwareResult(QPOSService.UpdateInformationResult.UPDATE_SUCCESS);
            } else if (f == com.dspread.xpos.bi.b.sh) {
                this.onUpdatePosFirmwareResult(QPOSService.UpdateInformationResult.UPDATE_FAIL);
            } else if (f == com.dspread.xpos.bi.b.si) {
                this.onUpdatePosFirmwareResult(QPOSService.UpdateInformationResult.UPDATE_LOWPOWER);
            } else if (f == com.dspread.xpos.bi.b.sj) {
                this.onUpdatePosFirmwareResult(QPOSService.UpdateInformationResult.UPDATE_PACKET_VEFIRY_ERROR);
            } else if (f == com.dspread.xpos.bi.b.sl) {
                this.onUpdatePosFirmwareResult(QPOSService.UpdateInformationResult.USB_RECONNECTING);
            }

        }
    }

    private void H(int timeout) {
        if (this.f(1)) {
            this.ih.p(this.ea, this.bL(), timeout);
        }
    }

    private void I(int timeout) {
        if (this.f(1)) {
            this.ih.q(this.ea, this.bL(), timeout);
        }
    }

    private void J(int timeout) {
        if (this.f(1)) {
            this.ih.b(this.ea, this.iS, this.iT, timeout);
        }
    }

    private void K(int timeout) {
        if (this.f(1)) {
            this.ih.r(this.ea, this.bL(), timeout);
        }
    }

    private void L(int timeout) {
        if (this.f(1)) {
            this.ih.s(this.ea, this.bL(), timeout);
        }
    }

    private byte a(QPOSService.TransactionType tradeType) {
        if (tradeType == QPOSService.TransactionType.GOODS) {
            return 1;
        } else if (tradeType == QPOSService.TransactionType.SERVICES) {
            return 2;
        } else if (tradeType == QPOSService.TransactionType.CASH) {
            return 3;
        } else if (tradeType == QPOSService.TransactionType.CASHBACK) {
            return 4;
        } else if (tradeType == QPOSService.TransactionType.INQUIRY) {
            return 5;
        } else if (tradeType == QPOSService.TransactionType.TRANSFER) {
            return 6;
        } else if (tradeType == QPOSService.TransactionType.ADMIN) {
            return 7;
        } else if (tradeType == QPOSService.TransactionType.CASHDEPOSIT) {
            return 8;
        } else if (tradeType == QPOSService.TransactionType.PAYMENT) {
            return 9;
        } else if (tradeType != QPOSService.TransactionType.PBOCLOG && tradeType != QPOSService.TransactionType.ECQ_INQUIRE_LOG) {
            if (tradeType == QPOSService.TransactionType.SALE) {
                return 11;
            } else if (tradeType == QPOSService.TransactionType.PREAUTH) {
                return 12;
            } else if (tradeType == QPOSService.TransactionType.ECQ_DESIGNATED_LOAD) {
                return 16;
            } else if (tradeType == QPOSService.TransactionType.ECQ_UNDESIGNATED_LOAD) {
                return 17;
            } else if (tradeType == QPOSService.TransactionType.ECQ_CASH_LOAD) {
                return 18;
            } else if (tradeType == QPOSService.TransactionType.ECQ_CASH_LOAD_VOID) {
                return 19;
            } else if (tradeType == QPOSService.TransactionType.REFUND) {
                return 20;
            } else {
                return (byte)(tradeType == QPOSService.TransactionType.UPDATE_PIN ? -16 : 1);
            }
        } else {
            return 10;
        }
    }

    protected j n(bi pos) {
        i dc = null;
        dc = new i(32, 176, 30);
        pos.a(dc);
        j uc = pos.p(30);
        return uc;
    }

    protected j o(bi pos) {
        i dc = new i(34, 0, 0, 30);
        pos.a(dc);
        j uc = pos.p(30);
        return uc;
    }

    protected boolean b(j uc) {
        boolean rf = false;
        aq.ah("============== checkCmdID: " + uc);
        if (uc == null) {
            aq.ag("QPOSService checkCmdId disConnect() uc == null");
            this.s("checkCmdId disConnect() uc == null");
            if (this.iX != QPOSService.e.lK && this.ef) {
                aq.ah("============== onError(Error.TIMEOUT);");
                this.onError(QPOSService.Error.TIMEOUT);
            }
        } else if (uc.L() != 36 && uc.L() != 136) {
            if (uc.L() == 65) {
                rf = true;
                if (uc.length() > 0) {
                    if (uc.getByte(0) == 0) {
                        this.onRequestDisplay(QPOSService.Display.INPUT_PIN_ING);
                    } else if (uc.getByte(0) == 1) {
                        this.onRequestDisplay(QPOSService.Display.INPUT_LAST_OFFLINE_PIN);
                    } else {
                        this.onRequestDisplay(QPOSService.Display.INPUT_OFFLINE_PIN_ONLY);
                    }
                } else {
                    this.onRequestDisplay(QPOSService.Display.INPUT_PIN_ING);
                }
            } else if (uc.L() == 66) {
                rf = true;
                this.onRequestDisplay(QPOSService.Display.MAG_TO_ICC_TRADE);
            } else if (uc.L() == 67) {
                rf = true;
            } else if (uc.L() == 73) {
                rf = true;
            } else if (uc.L() == 137) {
                rf = true;
            } else if (uc.L() == 82) {
                rf = true;
                this.onRequestDisplay(QPOSService.Display.MSR_DATA_READY);
            } else {
                rf = false;
                aq.ag("QPOSService checkCmdId disConnect() 22");
                this.s("checkCmdId disConnect()222");
                if (uc.L() == 38) {
                    this.onRequestTransactionResult(QPOSService.TransactionResult.DEVICE_ERROR);
                } else if (uc.L() == 37) {
                    this.onError(QPOSService.Error.CMD_TIMEOUT);
                } else if (uc.L() == 81) {
                    this.onRequestDisplay(QPOSService.Display.CARD_REMOVED);
                } else if (uc.L() == 40) {
                    this.onRequestDisplay(QPOSService.Display.TRANSACTION_TERMINATED);
                    this.onRequestTransactionResult(QPOSService.TransactionResult.CANCEL);
                } else if (uc.L() == 81) {
                    this.onRequestTransactionResult(QPOSService.TransactionResult.CARD_REMOVED);
                } else if (uc.L() == 41) {
                    this.onError(QPOSService.Error.MAC_ERROR);
                } else if (uc.L() == 52) {
                    this.onEmvICCExceptionData(aw.byteArray2Hex(uc.a(0, uc.length())));
                    this.onRequestTransactionResult(QPOSService.TransactionResult.DECLINED);
                } else if (uc.L() == 51) {
                    this.onRequestDisplay(QPOSService.Display.TRANSACTION_TERMINATED);
                    this.onEmvICCExceptionData(aw.byteArray2Hex(uc.a(0, uc.length())));
                    this.onRequestTransactionResult(QPOSService.TransactionResult.TERMINATED);
                } else if (uc.L() == 72) {
                    this.onRequestTransactionResult(QPOSService.TransactionResult.NFC_TERMINATED);
                } else if (uc.L() == 53) {
                    this.onError(QPOSService.Error.CMD_NOT_AVAILABLE);
                } else if (uc.L() == 0) {
                    this.onError(QPOSService.Error.CMD_NOT_AVAILABLE);
                } else if (uc.L() == 32) {
                    rf = true;
                    this.onError(QPOSService.Error.DEVICE_RESET);
                } else if (uc.L() == 49) {
                    Log.i("POS_SDK", "CmdId.CMDID_ICC_POWER_ON_ERROR,49");
                    this.onDoTradeResult(QPOSService.DoTradeResult.NOT_ICC, (Hashtable)null);
                } else if (uc.L() == 57) {
                    this.onError(QPOSService.Error.WR_DATA_ERROR);
                } else if (uc.L() == 55) {
                    this.onError(QPOSService.Error.EMV_APP_CFG_ERROR);
                } else if (uc.L() == 56) {
                    this.onError(QPOSService.Error.EMV_CAPK_CFG_ERROR);
                } else if (uc.L() == 64) {
                    this.onDoTradeResult(QPOSService.DoTradeResult.NO_UPDATE_WORK_KEY, (Hashtable)null);
                } else if (uc.L() == 68) {
                    this.onRequestTransactionResult(QPOSService.TransactionResult.CARD_BLOCKED_OR_NO_EMV_APPS);
                } else if (uc.L() == 69) {
                    this.onRequestTransactionResult(QPOSService.TransactionResult.SELECT_APP_FAIL);
                } else if (uc.L() == 70) {
                    this.onRequestTransactionResult(QPOSService.TransactionResult.CAPK_FAIL);
                } else if (uc.L() == 71) {
                    this.onRequestTransactionResult(QPOSService.TransactionResult.FALLBACK);
                } else {
                    if (uc.L() == 48) {
                        Log.i("POS_SDK", "CmdId.CMDID_ICC_INIT_ERROR,48");
                    } else if (uc.L() == 50) {
                        Log.i("POS_SDK", "CmdId.CMDID_ICC_TRADE_ERROR,50");
                    } else {
                        Log.i("POS_SDK", "uc command id = " + uc.L());
                    }

                    this.onError(QPOSService.Error.UNKNOWN);
                }
            }
        } else {
            rf = true;
        }

        aq.ah("checkCmdId rf = " + rf);
        this.e(rf);
        return rf;
    }

    protected boolean a(j uc, Hashtable<String, Object> rTable) {
        boolean rf = false;
        aq.ah("============== checkCmdID");
        if (uc == null) {
            if (this.iX != QPOSService.e.lK && this.ef) {
                aq.ah("============== onError(Error.TIMEOUT);");
                this.iF = QPOSService.Error.TIMEOUT;
                rTable.put("errorInfo", this.iF);
            }
        } else if (uc.L() == 36) {
            rf = true;
        } else if (uc.L() == 65) {
            rf = true;
            if (uc.length() > 0) {
                if (uc.getByte(0) == 0) {
                    this.iG = QPOSService.Display.INPUT_PIN_ING;
                } else {
                    this.iG = QPOSService.Display.INPUT_OFFLINE_PIN_ONLY;
                }
            } else {
                this.iG = QPOSService.Display.INPUT_PIN_ING;
            }
        } else if (uc.L() == 66) {
            rf = true;
            this.iG = QPOSService.Display.MAG_TO_ICC_TRADE;
        } else if (uc.L() == 67) {
            rf = true;
        } else {
            rf = false;
            if (uc.L() == 38) {
                this.iH = QPOSService.TransactionResult.DEVICE_ERROR;
            } else if (uc.L() == 37) {
                this.iF = QPOSService.Error.CMD_TIMEOUT;
            } else if (uc.L() == 40) {
                this.iG = QPOSService.Display.TRANSACTION_TERMINATED;
                this.iH = QPOSService.TransactionResult.CANCEL;
                rTable.put("code", false);
                rTable.put("TransactionResult", QPOSService.TransactionResult.CANCEL);
            } else if (uc.L() == 41) {
                this.iF = QPOSService.Error.MAC_ERROR;
            } else if (uc.L() == 52) {
                rTable.put("code", false);
                rTable.put("errorInfo", "onEmvICCExceptionData:" + aw.byteArray2Hex(uc.a(0, uc.length())));
                this.iH = QPOSService.TransactionResult.DECLINED;
            } else if (uc.L() == 51) {
                this.iG = QPOSService.Display.TRANSACTION_TERMINATED;
                rTable.put("code", false);
                rTable.put("errorInfo", "onEmvICCExceptionData:" + aw.byteArray2Hex(uc.a(0, uc.length())));
                this.iH = QPOSService.TransactionResult.TERMINATED;
            } else if (uc.L() == 53) {
                this.iF = QPOSService.Error.CMD_NOT_AVAILABLE;
            } else if (uc.L() == 0) {
                this.iF = QPOSService.Error.CMD_NOT_AVAILABLE;
            } else if (uc.L() == 32) {
                rf = true;
                this.iF = QPOSService.Error.DEVICE_RESET;
            } else if (uc.L() == 49) {
                Log.i("POS_SDK", "CmdId.CMDID_ICC_POWER_ON_ERROR,49");
                this.iI = QPOSService.DoTradeResult.NOT_ICC;
            } else if (uc.L() == 57) {
                this.iF = QPOSService.Error.WR_DATA_ERROR;
            } else if (uc.L() == 55) {
                this.iF = QPOSService.Error.EMV_APP_CFG_ERROR;
            } else if (uc.L() == 56) {
                this.iF = QPOSService.Error.EMV_CAPK_CFG_ERROR;
            } else if (uc.L() == 64) {
                this.onDoTradeResult(QPOSService.DoTradeResult.NO_UPDATE_WORK_KEY, (Hashtable)null);
            } else if (uc.L() == 68) {
                this.iH = QPOSService.TransactionResult.CARD_BLOCKED_OR_NO_EMV_APPS;
            } else if (uc.L() == 69) {
                this.iH = QPOSService.TransactionResult.SELECT_APP_FAIL;
                rTable.put("errorInfo", QPOSService.TransactionResult.SELECT_APP_FAIL);
            } else if (uc.L() == 70) {
                this.iH = QPOSService.TransactionResult.CAPK_FAIL;
            } else if (uc.L() == 71) {
                this.iH = QPOSService.TransactionResult.FALLBACK;
            } else {
                if (uc.L() == 48) {
                    Log.i("POS_SDK", "CmdId.CMDID_ICC_INIT_ERROR,48");
                } else if (uc.L() == 50) {
                    Log.i("POS_SDK", "CmdId.CMDID_ICC_TRADE_ERROR,50");
                } else {
                    Log.i("POS_SDK", "uc command id = " + uc.L());
                }

                this.iF = QPOSService.Error.UNKNOWN;
            }
        }

        aq.ah("checkCmdId rf = " + rf);
        this.e(rf);
        return rf;
    }

    public void openAudio() {
        this.cK();
    }

    public boolean syncOpenAudio() {
        return this.cL();
    }

    public void closeAudio() {
        this.s("closeAudio");
        if (this.P != null) {
            if (this.hs != null) {
                this.P.unregisterReceiver(this.hs);
                this.hs = null;
            }

        }
    }

    private boolean U(String blueToothAddress) {
        return blueToothAddress.startsWith("00:15:83:") || blueToothAddress.startsWith("00:13:8A:");
    }

    private void cA() {
        try {
            boolean f = this.f(1);
            aq.ah("connect bluetooth end");
            this.e(false);
            if (f) {
                this.setPosExistFlag(true);
                this.onRequestQposConnected();
            }
        } catch (Exception var2) {
            this.onError(QPOSService.Error.UNKNOWN);
        }

    }

    private void cB() {
        this.iJ = false;
        this.ea.T(1);
        this.ea.U(0);
    }

    public boolean connectBluetoothDevice(int bondtime, String blueToothAddress) {
        this.cB();
        return this.a(true, bondtime, blueToothAddress);
    }

    public boolean connectBT(String blueToothAddress) {
        this.cB();
        return this.a(true, 30, blueToothAddress);
    }

    public boolean connectBluetoothDevice(boolean auto, int bondtime, String blueToothAddress) {
        if (this.ea == null) {
            return true;
        } else {
            this.cB();
            return this.a(auto, bondtime, blueToothAddress);
        }
    }

    public boolean connectBluetoothDevice(boolean auto, int bondtime, int connectCount, String blueToothAddress) {
        if (this.ea == null) {
            return true;
        } else {
            this.cB();
            this.iJ = true;
            this.ea.T(connectCount);
            return this.a(auto, bondtime, blueToothAddress);
        }
    }

    public void createRfcommSocketToServiceRecord(boolean flag) {
        if (this.ea != null && this.ea instanceof az) {
            az.df().c(flag);
        }

    }

    private boolean a(boolean auto, int bondtime, String blueToothAddress) {
        aq.ah("QPOSService connectBT blueToothAddress: " + blueToothAddress);
        if (this.ea == null) {
            return true;
        } else if (this.az()) {
            this.onError(QPOSService.Error.DEVICE_BUSY);
            return false;
        } else {
            this.ea.r(auto);
            this.ea.S(bondtime);
            if (!(this.ea instanceof az) && !(this.ea instanceof ba) && !(this.ea instanceof ax)) {
                aq.af("connectBT: is not VPosBluetooth");
                this.onError(QPOSService.Error.UNKNOWN);
                this.e(false);
                return false;
            } else if (blueToothAddress != null && !"".equals(blueToothAddress)) {
                this.cp = blueToothAddress;
                if (this.ea.V() != null && !this.ea.V().equals("")) {
                    if (!blueToothAddress.equals(this.ea.V())) {
                        aq.ag(">>>>>>>>>>>>>two buletooth");
                        this.ea.g(blueToothAddress);
                    }
                } else {
                    aq.ag("++++++++++++++++++++++++++");
                    this.ea.g(blueToothAddress);
                }

                this.e(true);
                this.j(30011);
                return true;
            } else {
                this.ea.g(blueToothAddress);
                return false;
            }
        }
    }

    public boolean syncConnectBluetooth(boolean auto, int bondtime, String blueToothAddress) {
        aq.ah("connectBT blueToothAddress: " + blueToothAddress);
        if (this.ea == null) {
            return true;
        } else if (this.az()) {
            this.onError(QPOSService.Error.DEVICE_BUSY);
            return false;
        } else {
            this.ea.r(auto);
            this.ea.S(bondtime);
            if (!(this.ea instanceof az) && !(this.ea instanceof ba) && !(this.ea instanceof ax)) {
                aq.af("connectBT: is not VPosBluetooth");
                this.onError(QPOSService.Error.UNKNOWN);
                this.e(false);
                return false;
            } else if (blueToothAddress != null && !"".equals(blueToothAddress)) {
                if (this.ea.V() != null && !this.ea.V().equals("")) {
                    if (!blueToothAddress.equals(this.ea.V())) {
                        aq.ag(">>>>>>>>>>>>>two buletooth");
                        this.ea.g(blueToothAddress);
                    }
                } else {
                    aq.ag("++++++++++++++++++++++++++");
                    this.ea.g(blueToothAddress);
                }

                this.e(true);

                try {
                    aq.ah("connect bluetooth start");
                    boolean f = this.f(1);
                    aq.ah("connect bluetooth end");
                    this.e(false);
                    if (f) {
                        this.setPosExistFlag(true);
                    }
                } catch (Exception var5) {
                    this.onError(QPOSService.Error.UNKNOWN);
                }

                this.s("syncConnectBluetooth");
                return true;
            } else {
                this.ea.g(blueToothAddress);
                return false;
            }
        }
    }

    public void disConnectBtPos() {
        i dc = new i(32, 0, 0, 5);
        this.ea.a(dc);
        j uc = this.ea.p(5);
        this.disconnectBT();
    }

    public void disconnectBT() {
        aq.ah("disconnect buletooth");
        this.jd = false;
        if (hD != null) {
            if (this.az()) {
                this.s("disconnectBT");
                this.e(false);
            }

            if (this.ei != null) {
                this.ei.g();
            }

            this.connectBT((String)null);
            this.setPosExistFlag(false);
        }
    }

    public boolean syncDisconnectBT() {
        aq.ah("syncDisconnectBT buletooth");
        if (hD == null) {
            return true;
        } else {
            if (this.az()) {
                this.s("syncDisconnectBT");
                this.e(false);
            }

            this.iK = true;
            this.connectBT((String)null);
            this.setPosExistFlag(false);
            return true;
        }
    }

    public void disconnectBT(String address) {
        aq.ah("disconnect buletooth from address");
        if (hD != null) {
            if (this.az()) {
                aq.ag("QPOSService disconnectBT(...) disConnect()");
                this.s("disconnectBT(...)");
                this.e(false);
            }

            if (address != null) {
                this.ea.f(address);
                this.setPosExistFlag(false);
            }
        }
    }

    public boolean resetQPOS() {
        if (hD == null) {
            return false;
        } else {
            boolean f = false;
            this.iw = QPOSService.b.lt;
            this.iX = QPOSService.e.lJ;

            try {
                if (this.az()) {
                    this.iX = QPOSService.e.lK;
                    f = this.exit();
                    this.iX = QPOSService.e.lL;
                } else {
                    this.s("resetQPOS");
                    f = true;
                }
            } catch (Exception var3) {
            }

            this.e(false);
            return f;
        }
    }

    public boolean resetPosStatus(boolean quick) {
        aq.ah("QPOSService resetPosStatus(boolean quick)");
        if (!quick) {
            return this.resetPosStatus();
        } else if (hD == null) {
            return false;
        } else if (!this.ef) {
            return true;
        } else {
            boolean f = false;

            try {
                f = this.exit();
            } catch (Exception var4) {
            }

            this.s("resetPosStatus(...) ");
            this.e(false);
            return f;
        }
    }

    public boolean resetPosStatus() {
        aq.ah("QPOSService resetPosStatus");
        if (hD == null) {
            return false;
        } else {
            boolean f = false;
            this.iw = QPOSService.b.lt;
            this.iX = QPOSService.e.lJ;
            if (!this.ef) {
                return true;
            } else {
                try {
                    if (!this.aE()) {
                        this.iX = QPOSService.e.lK;
                        f = this.exit();
                        this.iX = QPOSService.e.lL;
                    }

                    this.s("resetPosStatus");
                } catch (Exception var3) {
                }

                this.e(false);
                return f;
            }
        }
    }

    public void setDesKey(String ikey) {
        ah.j(aw.an(ikey));
    }

    public void signature() {
        if (this.aD()) {
            this.a(QPOSService.a.kg);
        }
    }

    public void udpateWorkKey(String str) {
        if (this.aD()) {
            this.timeout = 5;
            this.T(str);
            this.a(QPOSService.a.kf);
        }
    }

    public void doTrade_QF(int tradeMode, String randomString, String extraString, QPOSService.TransactionType type) {
        this.doTrade_QF(tradeMode, randomString, extraString, type, 60);
    }

    public void doTrade_QF(int tradeMode, String randomString, String extraString) {
        this.hP = "";
        this.doTrade_QF(tradeMode, randomString, extraString, 60);
    }

    public void doTrade_QF(int tradeMode, String randomString, String extraString, QPOSService.TransactionType type, int timeout) {
        if (this.aD()) {
            aq.ah("randomString: " + randomString);
            this.en = timeout;
            this.ex = randomString;
            this.ey = extraString;
            this.hQ = tradeMode;
            this.hP = aw.byteArray2Hex(new byte[]{this.a(type)});
            this.a(QPOSService.a.ke);
        }
    }

    public void doTrade_QF(int tradeMode, String randomString, String extraString, int timeout) {
        if (this.aD()) {
            this.hP = "";
            aq.ah("randomString: " + randomString);
            this.en = timeout;
            this.ex = randomString;
            this.ey = extraString;
            this.hQ = tradeMode;
            this.a(QPOSService.a.ke);
        }
    }

    public void getCardNo() {
        if (this.aD()) {
            this.a(QPOSService.a.km);
        }
    }

    public void lcdShowCustomDisplay(QPOSService.LcdModeAlign lcdModeAlign, String lcdFont) {
        this.lcdShowCustomDisplay(lcdModeAlign, lcdFont, 5);
    }

    public void lcdShowCloseDisplay() {
        this.lcdShowCustomDisplay((QPOSService.LcdModeAlign)null, "", 10);
    }

    public void lcdShowCustomDisplay(QPOSService.LcdModeAlign lcdModeAlign, String lcdFont, int timeout) {
        this.e(false);
        if (this.aD()) {
            String align = "00";
            if (lcdModeAlign == QPOSService.LcdModeAlign.LCD_MODE_ALIGNLEFT) {
                align = "00";
            } else if (lcdModeAlign == QPOSService.LcdModeAlign.LCD_MODE_ALIGNRIGHT) {
                align = "20";
            } else if (lcdModeAlign == QPOSService.LcdModeAlign.LCD_MODE_ALIGNCENTER) {
                align = "40";
            } else {
                align = "00";
            }

            if (lcdModeAlign == null) {
                align = "80";
            }

            String str = "";
            if (lcdFont != null && !"".equals(lcdFont)) {
                str = align + lcdFont + "00";
            }

            this.v(str);
            this.en = timeout;
            this.a(QPOSService.a.kn);
        }
    }

    public void udpateWorkKey(String workKey, String workKeyCheck) {
        if (workKeyCheck.length() == 8) {
            workKeyCheck = workKeyCheck + "00000000";
        }

        this.udpateWorkKey(workKey, workKeyCheck, workKey, workKeyCheck, "", "");
    }

    private void a(String workKey, String workKeyCheck, int isoformat, int keyIndex, int pinlength, String pan, String message) {
        if (workKeyCheck.length() == 8) {
            workKeyCheck = workKeyCheck + "00000000";
        }

        this.a(workKey, workKeyCheck, "", "", "", "", isoformat, keyIndex, pinlength, pan, message);
    }

    public void udpateWorkKey(String pik, String pikCheck, String trk, String trkCheck, String mak, String makCheck) {
        if (this.aD()) {
            this.timeout = 5;
            String str = "";
            int pikkLen = 0;
            if (pik != null && !"".equals(pik) && pikCheck != null && !"".equals(pikCheck)) {
                pikkLen = pik.length() + pikCheck.length();
                pikkLen /= 2;
            } else {
                pik = "";
                pikCheck = "";
            }

            str = str + aw.byteArray2Hex(new byte[]{(byte)pikkLen}) + pik + pikCheck;
            int trkLen = 0;
            if (trk != null && !"".equals(trk) && trkCheck != null && !"".equals(trkCheck)) {
                trkLen = trk.length() + trkCheck.length();
                trkLen /= 2;
            } else {
                trk = "";
                trkCheck = "";
            }

            str = str + aw.byteArray2Hex(new byte[]{(byte)trkLen}) + trk + trkCheck;
            int makLen = 0;
            if (mak != null && !"".equals(mak) && makCheck != null && !"".equals(makCheck)) {
                makLen = mak.length() + makCheck.length();
                makLen /= 2;
            } else {
                mak = "";
                makCheck = "";
            }

            str = str + aw.byteArray2Hex(new byte[]{(byte)makLen}) + mak + makCheck;
            aq.ah("work keys: " + str);
            this.T(str);
            this.a(QPOSService.a.ko);
        }
    }

    private void a(String pik, String pikCheck, String trk, String trkCheck, String mak, String makCheck, int isoformat, int keyIndex, int pinlength, String pan, String message) {
        if (this.aD()) {
            this.iL = isoformat;
            this.iM = pinlength;
            this.iN = pan;
            this.message = message;
            this.iO = keyIndex;
            this.timeout = 5;
            String str = "";
            int pikkLen = 0;
            if (pik != null && !"".equals(pik) && pikCheck != null && !"".equals(pikCheck)) {
                pikkLen = pik.length() + pikCheck.length();
                pikkLen /= 2;
            } else {
                pik = "";
                pikCheck = "";
            }

            str = str + aw.byteArray2Hex(new byte[]{(byte)pikkLen}) + pik + pikCheck;
            int trkLen = 0;
            if (trk != null && !"".equals(trk) && trkCheck != null && !"".equals(trkCheck)) {
                trkLen = trk.length() + trkCheck.length();
                trkLen /= 2;
            } else {
                trk = "";
                trkCheck = "";
            }

            str = str + aw.byteArray2Hex(new byte[]{(byte)trkLen}) + trk + trkCheck;
            int makLen = 0;
            if (mak != null && !"".equals(mak) && makCheck != null && !"".equals(makCheck)) {
                makLen = mak.length() + makCheck.length();
                makLen /= 2;
            } else {
                mak = "";
                makCheck = "";
            }

            str = str + aw.byteArray2Hex(new byte[]{(byte)makLen}) + mak + makCheck;
            if (keyIndex > 9) {
                this.onError(QPOSService.Error.INPUT_INVALID);
            }

            str = str + "0" + Integer.toHexString(keyIndex);
            aq.ah("work keys: " + str);
            this.T(str);
            this.a(QPOSService.a.kp);
        }
    }

    public void udpateWorkKey(String pik, String pikCheck, String trk, String trkCheck, String mak, String makCheck, int keyIndex) {
        this.udpateWorkKey(pik, pikCheck, trk, trkCheck, mak, makCheck, keyIndex, 5);
    }

    public void udpateWorkKey(String pik, String pikCheck, String trk, String trkCheck, String mak, String makCheck, int keyIndex, int timeout) {
        aq.ah("QPOSService udpateWorkKey");
        this.timeout = timeout;
        if (this.aD()) {
            if (keyIndex >= 5) {
                this.onError(QPOSService.Error.INPUT_INVALID_FORMAT);
            } else {
                this.a(pik, pikCheck, trk, trkCheck, mak, makCheck, keyIndex);
                this.a(QPOSService.a.ko);
            }
        }
    }

    public Hashtable<String, Object> syncUpdateWorkKey(String pik, String pikCheck, String trk, String trkCheck, String mak, String makCheck, int keyIndex, int timeout) {
        Hashtable<String, Object> rTable = new Hashtable();
        rTable.put("code", false);
        rTable.put("errorInfo", QPOSService.Error.UNKNOWN);
        this.timeout = timeout;
        if (!this.bM()) {
            aq.ag("QPOSService syncUpdateWorkKey() disConnect()");
            this.s("syncUpdateWorkKey");
            return rTable;
        } else if (keyIndex >= 5) {
            rTable.put("errorInfo", QPOSService.Error.INPUT_INVALID_FORMAT);
            aq.ag("QPOSService syncUpdateWorkKey() disConnect() 222");
            this.s("syncUpdateWorkKey222");
            return rTable;
        } else {
            this.a(pik, pikCheck, trk, trkCheck, mak, makCheck, keyIndex);
            i dc = new i(16, 240, timeout, aw.an(this.bL()));
            j uc = null;
            this.ea.a(dc);
            uc = this.ea.p(timeout);
            boolean f = this.a(uc, rTable);
            if (!f) {
                this.s("syncUpdateWorkKey333");
                return rTable;
            } else if (uc.K() == 0) {
                rTable.put("code", true);
                this.s("syncUpdateWorkKey444");
                return rTable;
            } else if (uc.K() == 8) {
                this.s("syncUpdateWorkKey555");
                return rTable;
            } else {
                this.s("syncUpdateWorkKey666");
                return rTable;
            }
        }
    }

    private void a(String pik, String pikCheck, String trk, String trkCheck, String mak, String makCheck, int keyIndex) {
        String str = "";
        int pikkLen = 0;
        if (pik != null && !"".equals(pik) && pikCheck != null && !"".equals(pikCheck)) {
            pikkLen = pik.length() + pikCheck.length();
            pikkLen /= 2;
        } else {
            pik = "";
            pikCheck = "";
        }

        str = str + aw.byteArray2Hex(new byte[]{(byte)pikkLen}) + pik + pikCheck;
        int trkLen = 0;
        if (trk != null && !"".equals(trk) && trkCheck != null && !"".equals(trkCheck)) {
            trkLen = trk.length() + trkCheck.length();
            trkLen /= 2;
        } else {
            trk = "";
            trkCheck = "";
        }

        str = str + aw.byteArray2Hex(new byte[]{(byte)trkLen}) + trk + trkCheck;
        int makLen = 0;
        if (mak != null && !"".equals(mak) && makCheck != null && !"".equals(makCheck)) {
            makLen = mak.length() + makCheck.length();
            makLen /= 2;
        } else {
            mak = "";
            makCheck = "";
        }

        str = str + aw.byteArray2Hex(new byte[]{(byte)makLen}) + mak + makCheck;
        aq.ah("work keys: " + str);
        this.T(str + "0" + keyIndex);
    }

    public void udpateWorkKey(String pik, String pikCheck, String trk, String trkCheck, String mak, String makCheck, String transKey, String transKeyCheck, int keyIndex, int timeout) {
        this.timeout = timeout;
        if (this.aD()) {
            if (keyIndex >= 5) {
                this.onError(QPOSService.Error.INPUT_INVALID_FORMAT);
            } else {
                this.a(pik, pikCheck, trk, trkCheck, mak, makCheck, keyIndex);
                String tsk = transKey + transKeyCheck;
                int tskLen = 0;
                if (tsk != null && !"".equals(tsk)) {
                    tskLen = tsk.length();
                    tskLen /= 2;
                } else {
                    tsk = "";
                }

                this.iP = this.iP + aw.byteArray2Hex(new byte[]{(byte)tskLen}) + tsk;
                this.iP = this.iP + aw.byteArray2Hex(new byte[]{(byte)keyIndex});
                this.a(QPOSService.a.kP);
            }
        }
    }

    public void MacKeyEncrypt(String macStr) {
        if (this.aD()) {
            if (macStr != null && !macStr.equals("") && (macStr.length() == 16 || macStr.length() == 24)) {
                this.timeout = 5;
                this.iQ = QPOSService.f.lR;
                String str = "000000" + macStr;
                this.T(str);
                this.a(QPOSService.a.kq);
            } else {
                this.onError(QPOSService.Error.INPUT_INVALID_FORMAT);
            }
        }
    }

    public void calcMacSingleAll(String macStr, int timeout) {
        if (this.az()) {
            this.calcMacSingleNoCheck(macStr, timeout);
        } else {
            this.calcMacSingle(macStr);
        }

    }

    public void calcMacSingle(String macStr) {
        if (this.aD()) {
            if (macStr != null && !macStr.equals("") && macStr.length() == 32) {
                this.timeout = 5;
                this.iQ = QPOSService.f.lT;
                this.T(macStr);
                this.a(QPOSService.a.kq);
            } else {
                this.onError(QPOSService.Error.INPUT_INVALID_FORMAT);
            }
        }
    }

    public void calcMacDoubleAll(String macStr, int keyIndex, int timeout) {
        if (this.az()) {
            this.calcMacDoubleNoCheck(macStr, keyIndex, timeout);
        } else {
            this.calcMacDouble(macStr, keyIndex, timeout);
        }

    }

    public void calcMacDouble(String macStr) {
        if (this.aD()) {
            if (macStr != null && !macStr.equals("") && macStr.length() == 32) {
                this.timeout = 5;
                this.iQ = QPOSService.f.lS;
                this.T(macStr);
                this.a(QPOSService.a.kq);
            } else {
                this.onError(QPOSService.Error.INPUT_INVALID_FORMAT);
            }
        }
    }

    public void calcMacDouble(String macStr, int keyIndex) {
        this.calcMacDouble(macStr, keyIndex, 5);
    }

    public void calcMacDoubleNoCheck(String macStr, int keyIndex, int timeout) {
        this.timeout = timeout;
        if (macStr != null && !macStr.equals("") && macStr.length() == 32) {
            if (keyIndex > 5) {
                this.onError(QPOSService.Error.INPUT_INVALID_FORMAT);
            } else {
                this.iQ = QPOSService.f.lS;
                this.T(macStr + "0" + keyIndex);
                this.ih.b(this.ea, aw.an(this.bL()), timeout);
            }
        } else {
            this.onError(QPOSService.Error.INPUT_INVALID_FORMAT);
        }
    }

    public void MacKeyEncryptAll(String macStr, int timeout) {
        if (this.az()) {
            this.MacKeyEncryptNoCheck(macStr, timeout);
        } else {
            this.MacKeyEncrypt(macStr);
        }

    }

    public void MacKeyEncryptNoCheck(String macStr, int timeout) {
        this.timeout = timeout;
        if (macStr != null && !macStr.equals("") && (macStr.length() == 16 || macStr.length() == 24)) {
            this.iQ = QPOSService.f.lR;
            String str = "000000" + macStr;
            this.T(str);
            this.ih.c(this.ea, aw.an(this.bL()), timeout);
        } else {
            this.onError(QPOSService.Error.INPUT_INVALID_FORMAT);
        }
    }

    public void calcMacSingleNoCheck(String macStr, int timeout) {
        this.timeout = timeout;
        if (macStr != null && !macStr.equals("") && macStr.length() == 32) {
            this.iQ = QPOSService.f.lT;
            this.T(macStr);
            this.ih.a(this.ea, aw.an(this.bL()), timeout);
        } else {
            this.onError(QPOSService.Error.INPUT_INVALID_FORMAT);
        }
    }

    public void calcMacDouble(String macStr, int keyIndex, int timeout) {
        this.timeout = timeout;
        if (this.aD()) {
            if (macStr != null && !macStr.equals("") && macStr.length() == 32) {
                if (keyIndex > 5) {
                    this.onError(QPOSService.Error.INPUT_INVALID_FORMAT);
                } else {
                    this.iQ = QPOSService.f.lS;
                    this.T(macStr + "0" + keyIndex);
                    this.a(QPOSService.a.kq);
                }
            } else {
                this.onError(QPOSService.Error.INPUT_INVALID_FORMAT);
            }
        }
    }

    public Hashtable<String, Object> syncCalcMacDouble(String macStr, int keyIndex, boolean isIccCard, int timeout) {
        Hashtable<String, Object> resultTable = new Hashtable();
        resultTable.put("code", false);
        resultTable.put("errorInfo", QPOSService.Error.UNKNOWN);
        this.timeout = timeout;
        if (!isIccCard && !this.bM()) {
            resultTable.put("errorInfo", QPOSService.Error.DEVICE_BUSY);
            return resultTable;
        } else if (macStr != null && !macStr.equals("") && macStr.length() == 32) {
            if (keyIndex > 5) {
                resultTable.put("errorInfo", QPOSService.Error.INPUT_INVALID_FORMAT);
                return resultTable;
            } else {
                this.iQ = QPOSService.f.lS;
                this.T(macStr + "0" + keyIndex);
                resultTable = this.ih.b(this.ea, aw.an(this.bL()), timeout, resultTable);
                if (!isIccCard) {
                    aq.ag("QPOSService syncCalcMacDouble() disConnect()");
                    this.s("syncCalcMacDouble");
                }

                return resultTable;
            }
        } else {
            resultTable.put("errorInfo", QPOSService.Error.INPUT_INVALID_FORMAT);
            return resultTable;
        }
    }

    public Hashtable<String, Object> syncCalcMacSingle(String macStr, int keyIndex, boolean isIccCard, int timeout) {
        Hashtable<String, Object> resultTable = new Hashtable();
        resultTable.put("code", false);
        resultTable.put("errorInfo", QPOSService.Error.UNKNOWN);
        this.timeout = timeout;
        if (!isIccCard && !this.bM()) {
            resultTable.put("errorInfo", QPOSService.Error.DEVICE_BUSY);
            return resultTable;
        } else if (macStr != null && !macStr.equals("") && macStr.length() == 32) {
            this.timeout = 5;
            this.iQ = QPOSService.f.lS;
            this.T(macStr);
            resultTable = this.ih.a(this.ea, aw.an(this.bL()), timeout, resultTable);
            if (!isIccCard) {
                aq.ag("QPOSService syncCalcMacSingle() disConnect()");
                this.s("syncCalcMacSingle");
            }

            return resultTable;
        } else {
            resultTable.put("errorInfo", QPOSService.Error.INPUT_INVALID_FORMAT);
            return resultTable;
        }
    }

    public void intoMenu(int timeout) {
        if (this.aD()) {
            this.timeout = timeout;
            this.a(QPOSService.a.kJ);
        }
    }

    public void downloadRsaPublicKey(String rid, String index, String module, String exponent, int timeout) {
        this.downloadRsaPublicKey(0, rid, index, module, exponent, timeout);
    }

    public void downloadRsaPublicKey(int useType, String rid, String index, String module, String exponent, int timeout) {
        if (this.aD()) {
            this.timeout = timeout;
            String str = "00";
            str = str + "0" + useType;
            str = str + "00";
            if (useType != 1) {
                int ridLen = 0;
                if (rid != null && !"".equals(rid)) {
                    ridLen = rid.length();
                    ridLen /= 2;
                } else {
                    rid = "";
                }

                str = str + aw.byteArray2Hex(new byte[]{(byte)ridLen}) + rid;
                int indexLen = 0;
                if (index != null && !"".equals(index)) {
                    indexLen = index.length();
                    indexLen /= 2;
                } else {
                    index = "";
                }

                str = str + aw.byteArray2Hex(new byte[]{(byte)indexLen}) + index;
                int moduleLen = 0;
                if (module != null && !"".equals(module)) {
                    moduleLen = module.length();
                    moduleLen /= 2;
                } else {
                    module = "";
                }

                String M = aw.byteArray2Hex(new byte[]{0, (byte)moduleLen}) + module;
                int exponentLen = 0;
                if (exponent != null && !"".equals(exponent)) {
                    exponentLen = exponent.length();
                    exponentLen /= 2;
                } else {
                    exponent = "";
                }

                String E = aw.byteArray2Hex(new byte[]{0, (byte)exponentLen}) + exponent;
                int pkLen = false;
                int pkLen = (M + E).length() / 2;
                str = str + aw.byteArray2Hex(new byte[]{0, (byte)pkLen}) + M + E;
            }

            this.T(str);
            this.a(QPOSService.a.kI);
        }
    }

    public void updateMasterKey(int step, String RN1, String RN2, String masterKey, String masterKeyCK, int masterKeyIndex, int timeout) {
        this.iR = masterKeyIndex;
        this.updateMasterKey(step, RN1, RN2, masterKey, masterKeyCK, timeout);
        this.iR = 0;
    }

    public void updateMasterKey(int step, String RN1, String RN2, String masterKey, String masterKeyCK, int timeout) {
        if (this.aD()) {
            this.timeout = timeout;
            String str = null;
            switch(step) {
            case 1:
                str = "";
                this.a(QPOSService.a.kL);
                break;
            case 2:
                str = "";
                int RN1Len = 0;
                if (RN1 != null && !"".equals(RN1)) {
                    RN1Len = RN1.length();
                    RN1Len /= 2;
                } else {
                    RN1 = "";
                }

                str = str + aw.byteArray2Hex(new byte[]{(byte)RN1Len}) + RN1;
                int RN2Len = 0;
                if (RN2 != null && !"".equals(RN2)) {
                    RN2Len = RN2.length();
                    RN2Len /= 2;
                } else {
                    RN2 = "";
                }

                str = str + aw.byteArray2Hex(new byte[]{(byte)RN2Len}) + RN2;
                this.T(str);
                this.a(QPOSService.a.kM);
                break;
            case 3:
                if (this.iR >= 5) {
                    this.onError(QPOSService.Error.INPUT_INVALID_FORMAT);
                    return;
                }

                str = "";
                int masterKeyLen = 0;
                if (masterKey != null && !"".equals(masterKey)) {
                    masterKeyLen = masterKey.length();
                    masterKeyLen /= 2;
                } else {
                    masterKey = "";
                }

                str = str + aw.byteArray2Hex(new byte[]{(byte)masterKeyLen}) + masterKey;
                int masterKeyCKLen = 0;
                if (masterKeyCK != null && !"".equals(masterKeyCK)) {
                    masterKeyCKLen = masterKeyCK.length();
                    masterKeyCKLen /= 2;
                } else {
                    masterKeyCK = "";
                }

                str = str + aw.byteArray2Hex(new byte[]{(byte)masterKeyCKLen}) + masterKeyCK;
                if (this.iR != 0) {
                    str = str + aw.byteArray2Hex(new byte[]{(byte)this.iR});
                }

                this.T(str);
                this.a(QPOSService.a.kN);
            }

        }
    }

    public void updateMasterKeyRandom(int type, int keyIndex, String tmk, String tmkCk, int timeout) {
        if (this.aD()) {
            this.timeout = timeout;
            String str = "0000";
            str = str + aw.byteArray2Hex(new byte[]{(byte)keyIndex});
            str = str + aw.byteArray2Hex(new byte[]{(byte)type});
            switch(type) {
            case 1:
                str = str + aw.byteArray2Hex(new byte[]{16});
                this.T(str);
                this.a(QPOSService.a.kQ);
                break;
            case 2:
                int tmkLen = 0;
                if (tmk != null && !"".equals(tmk)) {
                    tmkLen = tmk.length() / 2;
                } else {
                    tmk = "";
                }

                int tmkCkLen = 0;
                if (tmkCk != null && !"".equals(tmkCk)) {
                    tmkCkLen = tmkCk.length() / 2;
                } else {
                    tmkCk = "";
                }

                str = str + aw.byteArray2Hex(new byte[]{(byte)tmkLen}) + tmk;
                str = str + aw.byteArray2Hex(new byte[]{(byte)tmkCkLen}) + tmkCk;
                this.T(str);
                this.a(QPOSService.a.kQ);
            }

        }
    }

    public void pinKey_TDES_ALL(int key_index, String pin, int timeout) {
        aq.ah("QPOSSerivce pinKey_TDES_ALL");
        if (this.az()) {
            this.pinKey_TDES_NOCHECK(key_index, pin, timeout);
        } else {
            this.pinKey_TDES(key_index, pin, timeout);
        }

    }

    public void pinKey_TDES_ALL(int key_index, String pin, String pan, int timeout) {
        pin = aw.byteArray2Hex(aw.ax(pin));
        String pinXorString = aw.l(pin, pan);
        this.pinKey_TDES_ALL(0, pinXorString, 5);
    }

    public void pinKey_TDES(int key_index, String pin, int timeout) {
        if (this.aD()) {
            this.timeout = timeout;
            if (pin != null && !"".equals(pin) && pin.length() == 16) {
                String str = "0000";
                str = str + aw.byteArray2Hex(new byte[]{(byte)key_index}) + pin;
                this.T(str);
                this.a(QPOSService.a.kO);
            } else {
                this.onError(QPOSService.Error.INPUT_INVALID_FORMAT);
            }
        }
    }

    public void pinKey_TDES_NOCHECK(int key_index, String pin, int timeout) {
        if (pin != null && !"".equals(pin) && pin.length() == 16) {
            String str = "0000";
            str = str + aw.byteArray2Hex(new byte[]{(byte)key_index}) + pin;
            this.T(str);
            this.ih.m(this.ea, this.bL(), timeout);
        } else {
            this.onError(QPOSService.Error.INPUT_INVALID_FORMAT);
        }
    }

    public void setMasterKey(String key, String checkValue) {
        if (this.aD()) {
            this.timeout = 5;
            this.T(key + checkValue);
            this.a(QPOSService.a.kx);
        }
    }

    public void getKsn() {
        if (this.f(1)) {
            this.ig.g(this.ea);
        }
    }

    public void setMasterKey(String key, String checkValue, int keyIndex) {
        this.setMasterKey(key, checkValue, keyIndex, 5);
    }

    public void setMasterKey(String key, String checkValue, int keyIndex, int timeout) {
        this.timeout = timeout;
        if (this.aD()) {
            if (keyIndex >= 10) {
                this.onError(QPOSService.Error.INPUT_INVALID_FORMAT);
            } else {
                this.T(key + checkValue + "0" + keyIndex);
                this.a(QPOSService.a.kx);
            }
        }
    }

    public Hashtable<String, Object> syncSetMasterKey(String key, String checkValue, int keyIndex, int timeout) {
        Hashtable<String, Object> rTable = new Hashtable();
        rTable.put("code", false);
        rTable.put("errorInfo", QPOSService.Error.UNKNOWN);
        this.timeout = timeout;
        if (!this.bM()) {
            aq.ag("QPOSService syncSetMasterKey() disConnect()");
            this.s("syncSetMasterKey");
            return rTable;
        } else if (keyIndex >= 5) {
            rTable.put("errorInfo", QPOSService.Error.INPUT_INVALID_FORMAT);
            aq.ag("QPOSService syncSetMasterKey() disConnect()222");
            this.s("syncSetMasterKey222");
            return rTable;
        } else {
            this.T(key + checkValue + "0" + keyIndex);
            i dc = new i(16, 226, timeout, aw.an(this.bL()));
            j uc = null;
            this.ea.a(dc);
            uc = this.ea.p(timeout);
            boolean f = this.a(uc, rTable);
            if (!f) {
                aq.ag("QPOSService syncSetMasterKey() disConnect()333");
                this.s("syncSetMasterKey333");
                return rTable;
            } else if (uc.K() == 0) {
                rTable.put("code", true);
                aq.ag("QPOSService syncSetMasterKey() disConnect()444");
                this.s("syncSetMasterKey444");
                return rTable;
            } else if (uc.K() == 8) {
                aq.ag("QPOSService syncSetMasterKey() disConnect()555");
                this.s("syncSetMasterKey555");
                return rTable;
            } else {
                aq.ag("QPOSService syncSetMasterKey() disConnect()666");
                this.s("syncSetMasterKey666");
                return rTable;
            }
        }
    }

    public void setMerchantID(String merchantID, int timeout) {
        this.timeout = timeout;
        this.T(merchantID);
        if (this.az()) {
            this.ih.p(this.ea, this.bL(), timeout);
        } else {
            if (!this.aD()) {
                return;
            }

            this.a(QPOSService.a.kR);
        }

    }

    public void setTerminalID(String terminalID, int timeout) {
        if (this.aD()) {
            this.timeout = timeout;
            this.T(terminalID);
            this.a(QPOSService.a.kS);
        }
    }

    public void setTerminalMerchantID(String terminalID, String merchantID, int timeout) {
        if (this.aD()) {
            this.timeout = timeout;
            this.iS = terminalID;
            this.iT = merchantID;
            this.a(QPOSService.a.kY);
        }
    }

    public void getInputAmount(String currencySymbol, int amountMaxLen, String DisplayStr, int timeout) {
        if (this.aD()) {
            this.timeout = timeout;
            String str = "";
            int currencySymbolLen = 0;
            if (currencySymbol != null && !"".equals(currencySymbol)) {
                currencySymbolLen = aw.byteArray2Hex(currencySymbol.getBytes()).length() / 2;
            } else {
                currencySymbol = "";
            }

            str = aw.byteArray2Hex(new byte[]{(byte)currencySymbolLen}) + aw.byteArray2Hex(currencySymbol.getBytes());
            str = str + aw.byteArray2Hex(new byte[]{(byte)amountMaxLen});
            boolean var7 = false;

            try {
                int DisplayStrLen = DisplayStr.getBytes().length;
                str = str + aw.byteArray2Hex(new byte[]{(byte)DisplayStrLen}) + aw.byteArray2Hex(DisplayStr.getBytes("gbk"));
            } catch (UnsupportedEncodingException var9) {
                var9.printStackTrace();
            }

            this.T(str);
            this.a(QPOSService.a.kT);
        }
    }

    public void setSystemDateTime(String dataTime, int timeout) {
        if (this.aD()) {
            this.timeout = timeout;
            this.T(aw.byteArray2Hex(dataTime.getBytes()));
            this.a(QPOSService.a.kU);
        }
    }

    public void powerOnNFC(int isEncrypt, int timeout) {
        this.timeout = timeout;
        this.a(QPOSService.a.kV);
    }

    public void sendApduByNFC(String apduString, int timeout) {
        if (apduString != null && !"".equals(apduString)) {
            this.timeout = timeout;
            this.hV = apduString;
            this.a(QPOSService.a.kW);
        } else {
            this.onError(QPOSService.Error.INPUT_INVALID_FORMAT);
        }
    }

    public void powerOffNFC(int timeout) {
        if (this.aD()) {
            this.timeout = timeout;
            this.a(QPOSService.a.kX);
        }
    }

    public void getMagneticTrackPlaintext(int timeout) {
        if (this.aD()) {
            this.timeout = timeout;
            this.a(QPOSService.a.kZ);
        }
    }

    public void updateBluetoothConfig(String paras, int timeout) {
        if (this.aD()) {
            this.timeout = timeout;
            this.T("0000");
            this.a(QPOSService.a.la);
        }
    }

    public void cbc_mac(int keyLen, int algorithmType, int operatorType, String data, int timeout) {
        if (this.aD()) {
            this.timeout = timeout;
            this.iU = keyLen;
            this.iV = algorithmType;
            this.iW = operatorType;
            String str = "";
            int dataLen = data.length();
            int diffLen = 8 - dataLen % 8;
            str = str + aw.byteArray2Hex(data.getBytes());
            if (diffLen != 8) {
                for(int i = 0; i < diffLen; ++i) {
                    str = str + "00";
                }
            }

            aq.ai("str===================================" + str);
            this.T(str);
            this.a(QPOSService.a.lb);
        }
    }

    public void cbc_macAll(int keyLen, int algorithmType, int operatorType, String data, int timeout) {
        if (this.az()) {
            this.cbc_macNocheck(keyLen, algorithmType, operatorType, data, timeout);
        } else {
            this.cbc_mac(keyLen, algorithmType, operatorType, data, timeout);
        }

    }

    public void cbc_mac_cnAll(int keyLen, int algorithmType, int operatorType, String data, int timeout) {
        if (this.az()) {
            this.cbc_macNocheck_cn(keyLen, algorithmType, operatorType, data, timeout);
        } else {
            this.cbc_mac_cn(keyLen, algorithmType, operatorType, data, timeout);
        }

    }

    public void cbc_macNocheck(int keyLen, int algorithmType, int operatorType, String data, int timeout) {
        this.timeout = timeout;
        this.iU = keyLen;
        this.iV = algorithmType;
        this.iW = operatorType;
        String str = "";
        int dataLen = data.length();
        int diffLen = 8 - dataLen % 8;
        str = str + aw.byteArray2Hex(data.getBytes());
        if (diffLen != 8) {
            for(int i = 0; i < diffLen; ++i) {
                str = str + "00";
            }
        }

        this.ih.a(this.ea, keyLen, algorithmType, operatorType, str, timeout);
    }

    public void cbc_mac_cn(int keyLen, int algorithmType, int operatorType, String data, int timeout) {
        if (this.aD()) {
            this.timeout = timeout;
            this.iU = keyLen;
            this.iV = algorithmType;
            this.iW = operatorType;
            String str = "";
            int dataLen = data.length() / 2;
            int diffLen = 8 - dataLen % 8;
            str = data;
            if (diffLen != 8) {
                for(int i = 0; i < diffLen; ++i) {
                    str = str + "00";
                }
            }

            aq.ai("str===================================" + str);
            this.T(str);
            this.a(QPOSService.a.lb);
        }
    }

    public void cbc_macNocheck_cn(int keyLen, int algorithmType, int operatorType, String data, int timeout) {
        this.timeout = timeout;
        this.iU = keyLen;
        this.iV = algorithmType;
        this.iW = operatorType;
        String str = "";
        int dataLen = data.length() / 2;
        int diffLen = 8 - dataLen % 8;
        str = str + data;
        if (diffLen != 8) {
            for(int i = 0; i < diffLen; ++i) {
                str = str + "00";
            }
        }

        this.ih.a(this.ea, keyLen, algorithmType, operatorType, str, timeout);
    }

    public void readBusinessCard(String cardType, String address, int readLen, String cardPin, int vender_id, int timeout) {
        if (this.aD()) {
            this.timeout = timeout;
            String str = "0000";
            str = str + cardType;
            str = str + "02";
            str = str + aw.byteArray2Hex(aw.P(vender_id));
            int cardPinLen = false;
            int cardPinLen;
            if (cardPin != null && !cardPin.equals("")) {
                cardPinLen = cardPin.length() / 2;
            } else {
                cardPin = "";
                cardPinLen = 0;
            }

            str = str + aw.byteArray2Hex(new byte[]{(byte)cardPinLen}) + cardPin;
            str = str + "00" + address;
            str = str + aw.byteArray2Hex(aw.P(readLen));
            this.T(str);
            this.a(QPOSService.a.lc);
        }
    }

    public void writeBusinessCard(String cardType, String address, String data, String cardPin, boolean isUpdatePinFlag, int vender_id, int timeout) {
        if (this.aD()) {
            this.timeout = timeout;
            String str = "0000";
            str = str + cardType;
            str = str + "02";
            str = str + aw.byteArray2Hex(aw.P(vender_id));
            int cardPinLen = false;
            int cardPinLen;
            if (cardPin != null && !cardPin.equals("")) {
                cardPinLen = cardPin.length() / 2;
            } else {
                cardPin = "";
                cardPinLen = 0;
            }

            str = str + aw.byteArray2Hex(new byte[]{(byte)cardPinLen}) + cardPin;
            if (isUpdatePinFlag) {
                str = str + "01";
            } else {
                str = str + "00";
            }

            str = str + "00" + address;
            int dataLen = false;
            int dataLen;
            if (data != null && !data.equals("")) {
                dataLen = data.length() / 2;
            } else {
                dataLen = 0;
                data = "";
            }

            str = str + aw.byteArray2Hex(aw.P(dataLen)) + data;
            this.T(str);
            this.a(QPOSService.a.ld);
        }
    }

    public String syncReadBusinessCard(String cardType, String address, int readLen, String cardPin, int vender_id, int timeout) {
        if (!this.aD()) {
            return "";
        } else {
            this.timeout = timeout;
            String str = "0000";
            str = str + cardType;
            str = str + "02";
            str = str + aw.byteArray2Hex(aw.P(vender_id));
            int cardPinLen = false;
            int cardPinLen;
            if (cardPin != null && !cardPin.equals("")) {
                cardPinLen = cardPin.length() / 2;
            } else {
                cardPin = "";
                cardPinLen = 0;
            }

            str = str + aw.byteArray2Hex(new byte[]{(byte)cardPinLen}) + cardPin;
            str = str + "00" + address;
            str = str + aw.byteArray2Hex(aw.P(readLen));
            if (!this.f(1)) {
                return "";
            } else {
                i dc = new i(23, 64, timeout, aw.an(str));
                j uc = null;
                this.ea.a(dc);
                uc = this.ea.p(timeout);
                boolean f = this.b(uc);
                aq.ag("QPOSService syncReadBusinessCard() disConnect()");
                this.s("syncReadBusinessCard");
                if (!f) {
                    return "";
                } else {
                    String result = "";
                    if (uc.K() == 0) {
                        result = aw.byteArray2Hex(uc.a(0, uc.length()));
                    }

                    return result;
                }
            }
        }
    }

    public int syncWriteBusinessCard(String cardType, String address, String data, String cardPin, boolean isUpdatePinFlag, int vender_id, int timeout) {
        if (!this.aD()) {
            return -1;
        } else {
            this.timeout = timeout;
            String str = "0000";
            str = str + cardType;
            str = str + "02";
            str = str + aw.byteArray2Hex(aw.P(vender_id));
            int cardPinLen = false;
            int cardPinLen;
            if (cardPin != null && !cardPin.equals("")) {
                cardPinLen = cardPin.length() / 2;
            } else {
                cardPin = "";
                cardPinLen = 0;
            }

            str = str + aw.byteArray2Hex(new byte[]{(byte)cardPinLen}) + cardPin;
            if (isUpdatePinFlag) {
                str = str + "01";
            } else {
                str = str + "00";
            }

            str = str + "00" + address;
            int dataLen = false;
            int dataLen;
            if (data != null && !data.equals("")) {
                dataLen = data.length() / 2;
            } else {
                dataLen = 0;
                data = "";
            }

            str = str + aw.byteArray2Hex(aw.P(dataLen)) + data;
            if (!this.f(1)) {
                return -1;
            } else {
                i dc = new i(23, 80, timeout, aw.an(str));
                j uc = null;
                this.ea.a(dc);
                uc = this.ea.p(timeout);
                boolean f = this.b(uc);
                aq.ag("QPOSService syncWriteBusinessCard() disConnect()");
                this.s("syncWriteBusinessCard");
                if (!f) {
                    return -1;
                } else {
                    return uc.K() == 0 ? 0 : -1;
                }
            }
        }
    }

    public int syncStartPowerWithVender(int vender_id, int cardType, int timeout) {
        if (!this.aD()) {
            return -1;
        } else {
            this.timeout = timeout;
            if (!this.f(1)) {
                return -1;
            } else {
                String str = "0000";
                str = str + aw.byteArray2Hex(aw.O(vender_id));
                str = str + aw.byteArray2Hex(aw.O(cardType));
                i dc = new i(23, 99, timeout, aw.an(str));
                j uc = null;
                this.ea.a(dc);
                uc = this.ea.p(timeout);
                boolean f = this.b(uc);
                aq.ag("QPOSService syncStartPowerWithVender() disConnect()");
                this.s("syncStartPowerWithVender");
                if (!f) {
                    return -1;
                } else {
                    return uc.K() == 0 ? 0 : -2;
                }
            }
        }
    }

    public int syncResetCard(int vender_id, int cardType, int timeout) {
        return 0;
    }

    private int cC() {
        if (!this.aD()) {
            return -1;
        } else if (!this.f(1)) {
            return -1;
        } else {
            i dc = new i(32, 64, 5);
            j uc = null;
            this.ea.a(dc);
            uc = this.ea.p(5);
            boolean f = this.b(uc);
            aq.ag("QPOSService CloseEngineeringModel() disConnect()");
            this.s("CloseEngineeringModel");
            if (!f) {
                return -1;
            } else {
                return uc.K() == 0 ? 0 : -1;
            }
        }
    }

    public void confirmAmount(String amount, int timeout) {
        if (this.aD()) {
            this.timeout = timeout;
            String str = "0000";
            int amountLen = false;
            if (amount != null && !amount.equals("")) {
                int amountLen = amount.length();

                try {
                    str = str + aw.byteArray2Hex(new byte[]{(byte)amountLen}) + aw.byteArray2Hex(amount.getBytes("gbk")) + "00";
                } catch (UnsupportedEncodingException var6) {
                    var6.printStackTrace();
                }
            } else {
                int amountLen = 0;
                amount = "";
                str = str + aw.byteArray2Hex(new byte[]{(byte)amountLen}) + amount + "00";
            }

            this.T(str);
            this.a(QPOSService.a.le);
        }
    }

    public void getPin(int encryptType, int keyIndex, int maxLen, String typeFace, String cardNo, String data, int timeout) {
        if (this.aD()) {
            this.timeout = timeout;
            String str = "0000";
            str = str + aw.byteArray2Hex(new byte[]{(byte)encryptType}) + aw.byteArray2Hex(new byte[]{(byte)keyIndex}) + aw.byteArray2Hex(new byte[]{(byte)maxLen});
            int typeFaceLen = false;
            if (typeFace != null && !typeFace.equals("")) {
                try {
                    int typeFaceLen = typeFace.getBytes().length + 1;
                    str = str + aw.byteArray2Hex(new byte[]{(byte)typeFaceLen}) + aw.byteArray2Hex(typeFace.getBytes("gbk")) + "00";
                } catch (UnsupportedEncodingException var12) {
                    var12.printStackTrace();
                }
            } else {
                int typeFaceLen = 0;
                typeFace = "";
                str = str + aw.byteArray2Hex(new byte[]{(byte)typeFaceLen}) + typeFace;
            }

            int cardNoLen = false;
            if (typeFace != null && !typeFace.equals("")) {
                int cardNoLen = cardNo.length();
                str = str + aw.byteArray2Hex(new byte[]{(byte)cardNoLen}) + aw.byteArray2Hex(cardNo.getBytes());
            } else {
                int cardNoLen = 0;
                cardNo = "";
                str = str + aw.byteArray2Hex(new byte[]{(byte)cardNoLen}) + cardNo;
            }

            int dataLen = false;
            if (typeFace != null && !typeFace.equals("")) {
                int dataLen = data.length() / 2;
                str = str + aw.byteArray2Hex(new byte[]{(byte)dataLen}) + data;
            } else {
                int dataLen = 0;
                data = "";
                str = str + aw.byteArray2Hex(new byte[]{(byte)dataLen}) + data;
            }

            this.T(str);
            this.a(QPOSService.a.lf);
        }
    }

    public void customInputDisplay(int operationType, int displayType, int maxLen, String DisplayStr, int timeout) {
        if (this.aD()) {
            this.timeout = timeout;
            String str = "0000";
            str = str + aw.byteArray2Hex(new byte[]{(byte)operationType}) + aw.byteArray2Hex(new byte[]{(byte)displayType}) + aw.byteArray2Hex(new byte[]{(byte)maxLen});
            int DisplayStrLen = false;
            if (DisplayStr != null && !DisplayStr.equals("")) {
                try {
                    int DisplayStrLen = DisplayStr.getBytes().length + 1;
                    str = str + aw.byteArray2Hex(new byte[]{(byte)DisplayStrLen}) + aw.byteArray2Hex(DisplayStr.getBytes("gbk")) + "00";
                } catch (UnsupportedEncodingException var9) {
                    var9.printStackTrace();
                }

                this.T(str);
                this.a(QPOSService.a.lg);
            } else {
                this.onError(QPOSService.Error.INPUT_INVALID_FORMAT);
            }
        }
    }

    private String V(String oString) {
        String resultStr = "";

        try {
            int len = oString.length();
            resultStr = oString.substring(0, len - 4);
        } catch (Exception var4) {
            resultStr = "";
        }

        return resultStr;
    }

    public Hashtable<String, String> syncReadZRCPUCardDelay(int timeout) {
        if (!this.aD()) {
            return null;
        } else if (!this.f(1)) {
            return null;
        } else {
            i dc = new i(23, 96, timeout);
            j uc = null;
            this.ea.a(dc);
            uc = this.ea.p(timeout);
            boolean f = this.b(uc);
            aq.ag("QPOSService syncReadZRCPUCardDelay() disConnect()");
            this.s("syncReadZRCPUCardDelay");
            if (!f) {
                return null;
            } else if (uc.K() == 0) {
                int index = 0;
                int temLen = false;
                int temLen = aw.r(uc.a(index, 1));
                int index = index + 1;
                String userCardNO = aw.byteArray2Hex(uc.a(index, temLen));
                userCardNO = this.V(userCardNO);
                index += temLen;
                temLen = aw.r(uc.a(index, 1));
                ++index;
                String userCardType = aw.byteArray2Hex(uc.a(index, temLen));
                userCardType = this.V(userCardType);
                index += temLen;
                temLen = aw.r(uc.a(index, 1));
                ++index;
                String cardAreaAndcardDiv = aw.byteArray2Hex(uc.a(index, temLen));
                cardAreaAndcardDiv = this.V(cardAreaAndcardDiv);
                index += temLen;
                temLen = aw.r(uc.a(index, 1));
                ++index;
                String randomCode = aw.byteArray2Hex(uc.a(index, temLen));
                randomCode = this.V(randomCode);
                index += temLen;
                temLen = aw.r(uc.a(index, 1));
                ++index;
                String remainingBalance = aw.byteArray2Hex(uc.a(index, temLen));
                remainingBalance = this.V(remainingBalance);
                index += temLen;
                temLen = aw.r(uc.a(index, 1));
                ++index;
                String randomNum = aw.byteArray2Hex(uc.a(index, temLen));
                randomNum = this.V(randomNum);
                index += temLen;
                temLen = aw.r(uc.a(index, 1));
                ++index;
                String randomEncrypt = aw.byteArray2Hex(uc.a(index, temLen));
                randomEncrypt = this.V(randomEncrypt);
                Hashtable<String, String> ksnDict = new Hashtable();
                ksnDict.put("userCardNO", userCardNO);
                ksnDict.put("userCardType", userCardType);
                ksnDict.put("cardAreaAndcardDiv", cardAreaAndcardDiv);
                ksnDict.put("randomCode", randomCode);
                ksnDict.put("remainingBalance", remainingBalance);
                ksnDict.put("randomNum", randomNum);
                ksnDict.put("randomEncrypt", randomEncrypt);
                return ksnDict;
            } else {
                return null;
            }
        }
    }

    public String syncBuyGasInitializeGasV(int gasV, String terminalNO, int timeout) {
        if (!this.aD()) {
            return "";
        } else {
            String str = "0000";
            str = str + aw.byteArray2Hex(aw.Q(gasV));
            str = str + terminalNO;
            if (!this.f(1)) {
                return "";
            } else {
                i dc = new i(23, 97, timeout, aw.an(str));
                j uc = null;
                this.ea.a(dc);
                uc = this.ea.p(timeout);
                boolean f = this.b(uc);
                this.s("syncBuyGasInitializeGasV");
                if (!f) {
                    return "";
                } else {
                    String result = "";
                    if (uc.K() == 0) {
                        result = aw.byteArray2Hex(uc.a(0, uc.length()));

                        try {
                            result = result.substring(2, result.length());
                        } catch (Exception var10) {
                            return "";
                        }
                    }

                    return result;
                }
            }
        }
    }

    public boolean syncBuyGasDate(String toWriteOrdersCPUResult, String mackey, String randomCode, int timeout) {
        if (!this.aD()) {
            return false;
        } else {
            String str = "0000";
            str = str + toWriteOrdersCPUResult;
            str = str + mackey;
            str = str + randomCode;
            if (!this.f(1)) {
                return false;
            } else {
                i dc = new i(23, 98, timeout, aw.an(str));
                j uc = null;
                this.ea.a(dc);
                uc = this.ea.p(timeout);
                boolean f = this.b(uc);
                this.s("syncBuyGasDate");
                if (!f) {
                    return false;
                } else {
                    return uc.K() == 0;
                }
            }
        }
    }

    public int printGbkText(String gbkText, Integer vertical_spacing) {
        return !this.aD() ? 0 : this.io.a(this.ea, gbkText, vertical_spacing);
    }

    public void printTestPage(byte[] picData) {
        if (this.aD()) {
            this.io.a(this.ea, picData);
        }
    }

    private void a(QPOSService.a mode) {
        aq.ah("BusinessMode: " + mode);
        int i = 0;
        if (this.iw == QPOSService.b.lu) {
            while(this.iw != QPOSService.b.lv) {
                if (this.iw == QPOSService.b.lt) {
                    this.onError(QPOSService.Error.UNKNOWN);
                    return;
                }

                try {
                    Thread.sleep(10L);
                } catch (InterruptedException var4) {
                    var4.printStackTrace();
                }

                if (i++ == 200) {
                    break;
                }
            }
        }

        if (this.iX == QPOSService.e.lK) {
            this.onError(QPOSService.Error.DEVICE_BUSY);
        } else {
            this.iX = QPOSService.e.lJ;
            if (this.az()) {
                this.iX = QPOSService.e.lK;
                this.s("onDoTrade(DDDD)");
                this.h(30020);
                this.i(30020);
            } else {
                this.e(true);
                switch(SyntheticClass_1.jl[mode.ordinal()]) {
                case 1:
                    this.h(30004);
                    this.i(30004);
                    break;
                case 2:
                    this.h(30001);
                    this.i(30001);
                    break;
                case 3:
                    this.h(30005);
                    this.i(30005);
                    break;
                case 4:
                    this.h(30066);
                    this.i(30066);
                    break;
                case 5:
                    this.h(30072);
                    this.i(30072);
                    break;
                case 6:
                    this.h(30073);
                    this.i(30073);
                    break;
                case 7:
                    this.h(30074);
                    this.i(30074);
                    break;
                case 8:
                    this.h(30067);
                    this.i(30067);
                    break;
                case 9:
                    this.h(30071);
                    this.i(30071);
                    break;
                case 10:
                    this.h(30068);
                    this.i(30068);
                    break;
                case 11:
                    this.h(30006);
                    this.i(30006);
                    break;
                case 12:
                    this.h(30069);
                    this.i(30069);
                    break;
                case 13:
                    this.h(30070);
                    this.i(30070);
                    break;
                case 14:
                    this.h(30009);
                    this.i(30009);
                    break;
                case 15:
                    this.h(30060);
                    this.i(30060);
                    break;
                case 16:
                    this.h(30012);
                    this.i(30012);
                    break;
                case 17:
                    this.h(30013);
                    this.i(30013);
                    break;
                case 18:
                    this.h(30014);
                    this.i(30014);
                    break;
                case 19:
                    this.h(30063);
                    this.i(30063);
                    break;
                case 20:
                    this.h(30015);
                    this.i(30015);
                    break;
                case 21:
                    this.h(30007);
                    this.i(30007);
                    break;
                case 22:
                    this.h(30002);
                    this.i(30002);
                    break;
                case 23:
                    this.h(30008);
                    this.i(30008);
                    break;
                case 24:
                    this.h(30016);
                    this.i(30016);
                    break;
                case 25:
                    this.h(30017);
                    this.i(30017);
                    break;
                case 26:
                    this.h(30018);
                    this.i(30018);
                    break;
                case 27:
                    this.h(30061);
                    this.i(30061);
                    break;
                case 28:
                    this.h(30040);
                    this.i(30040);
                    break;
                case 29:
                    this.h(30019);
                    this.i(30019);
                    break;
                case 30:
                    this.h(30021);
                    this.i(30021);
                    break;
                case 31:
                    this.h(30022);
                    this.i(30022);
                    break;
                case 32:
                    this.h(30024);
                    this.i(30024);
                    break;
                case 33:
                    this.h(30025);
                    this.i(30025);
                    break;
                case 34:
                    this.h(30023);
                    this.i(30023);
                    break;
                case 35:
                    this.h(30026);
                    this.i(30026);
                    break;
                case 36:
                    this.h(30034);
                    this.i(30034);
                    break;
                case 37:
                    this.h(30035);
                    this.i(30035);
                    break;
                case 38:
                    this.h(30036);
                    this.i(30036);
                    break;
                case 39:
                    this.h(30037);
                    this.i(30037);
                    break;
                case 40:
                    this.h(30038);
                    this.i(30038);
                    break;
                case 41:
                    this.h(30042);
                    this.i(30042);
                    break;
                case 42:
                    this.h(30039);
                    this.i(30039);
                    break;
                case 43:
                    this.h(30027);
                    this.i(30027);
                    break;
                case 44:
                    this.h(30028);
                    this.i(30028);
                    break;
                case 45:
                    this.h(30030);
                    this.i(30030);
                    break;
                case 46:
                    this.h(30031);
                    this.i(30031);
                    break;
                case 47:
                    this.h(30032);
                    this.i(30032);
                    break;
                case 48:
                    this.h(30043);
                    this.i(30043);
                    break;
                case 49:
                    this.h(30044);
                    this.i(30044);
                    break;
                case 50:
                    this.h(30050);
                    this.i(30050);
                    break;
                case 51:
                    this.h(30045);
                    this.i(30045);
                    break;
                case 52:
                    this.h(30046);
                    this.i(30046);
                    break;
                case 53:
                    this.h(30047);
                    this.i(30047);
                    break;
                case 54:
                    this.h(30048);
                    this.i(30048);
                    break;
                case 55:
                    this.h(30049);
                    this.i(30049);
                    break;
                case 56:
                    this.h(30051);
                    this.i(30051);
                    break;
                case 57:
                    this.h(30052);
                    this.i(30052);
                    break;
                case 58:
                    this.h(30053);
                    this.i(30053);
                    break;
                case 59:
                    this.h(30054);
                    this.i(30054);
                    break;
                case 60:
                    this.h(30055);
                    this.i(30055);
                    break;
                case 61:
                    this.h(30056);
                    this.i(30056);
                    break;
                case 62:
                    this.h(30057);
                    this.i(30057);
                    break;
                case 63:
                    this.h(30058);
                    this.i(30058);
                    break;
                case 64:
                    this.h(30059);
                    this.i(30059);
                    break;
                case 65:
                    this.h(30075);
                    this.i(30075);
                    break;
                case 66:
                    this.h(30076);
                    this.i(30076);
                }

                this.j(this.eN);
            }
        }
    }

    public void iccCashBack(String transactionTime, String random) {
        if (this.aD()) {
            this.ex = random;
            this.hT = transactionTime;
            this.et = "02";
            this.a(QPOSService.a.kw);
        }
    }

    public void getIccCardNo(String transactionTime) {
        if (this.aD()) {
            this.ex = "123";
            this.hT = transactionTime;
            this.et = "01";
            this.a(QPOSService.a.kw);
        }
    }

    public void getIccCardNo(String transactionTime, int timeout) {
        boolean a = this.resetPosStatus();
        if (a) {
            this.ex = "123";
            this.hT = transactionTime;
            this.et = "01";
            this.a(QPOSService.a.kw);
        }

    }

    public void inquireECQAmount(String transactionTime) {
        if (this.aD()) {
            this.ex = "123";
            this.hT = transactionTime;
            this.et = "00";
            this.a(QPOSService.a.kw);
        }
    }

    public void getPin(String transactionData) {
        if (this.aD()) {
            this.hT = transactionData;
            if (this.hT != null && !"".equals(this.hT)) {
                int pinTransactionDataLen = this.hT.length();
                if (pinTransactionDataLen > 24) {
                    this.onError(QPOSService.Error.INPUT_OUT_OF_RANGE);
                    return;
                }
            }

            this.a(QPOSService.a.kh);
        }
    }

    public void getPin(String transactionData, int len) {
        if (this.aD()) {
            this.hT = transactionData;
            this.timeout = this.timeout;
            this.hU = len;
            if (this.hT != null && !"".equals(this.hT)) {
                int pinTransactionDataLen = this.hT.length();
                if (pinTransactionDataLen > 24) {
                    this.onError(QPOSService.Error.INPUT_OUT_OF_RANGE);
                    return;
                }
            }

            this.a(QPOSService.a.kh);
        }
    }

    protected QPOSService.DoTradeMode cD() {
        return this.iY;
    }

    public void setJudgeDebitOrCreditFlag(boolean flag) {
        this.eS = flag;
    }

    public void setDoTradeMode(QPOSService.DoTradeMode doTradeMode) {
        if (this.eS) {
            this.iY = QPOSService.DoTradeMode.IS_DEBIT_OR_CREDIT;
        } else {
            this.iY = doTradeMode;
        }

    }

    public void saveUserData(int offset, String userData) {
        if (this.aD()) {
            if (userData != null && !"".equals(userData) && userData.length() % 2 == 0) {
                this.hX = userData;
                this.hZ = offset;
                this.a(QPOSService.a.kF);
            } else {
                this.onError(QPOSService.Error.INPUT_INVALID_FORMAT);
            }
        }
    }

    public void readUserData(int offset, int size) {
        if (this.aD()) {
            this.hZ = offset;
            this.hY = size;
            this.a(QPOSService.a.kG);
        }
    }

    public void updateEmvConfig(String emvAppCfg, String emvCapkCfg) {
        if (this.aD()) {
            if (emvAppCfg != null && !"".equals(emvAppCfg) && emvAppCfg.length() % 2 == 0) {
                if (emvCapkCfg != null && !"".equals(emvCapkCfg) && emvCapkCfg.length() % 2 == 0) {
                    this.hX = emvAppCfg + "," + emvCapkCfg;
                    this.a(QPOSService.a.kr);
                } else {
                    this.onError(QPOSService.Error.INPUT_INVALID_FORMAT);
                }
            } else {
                this.onError(QPOSService.Error.INPUT_INVALID_FORMAT);
            }
        }
    }

    public Hashtable<String, Object> syncUpdateEmvConfig(String emvAppCfg, String emvCapkCfg) {
        Hashtable<String, Object> resultTable = new Hashtable();
        resultTable.put("code", false);
        resultTable.put("errorInfo", QPOSService.Error.UNKNOWN);
        if (!this.bM()) {
            resultTable.put("errorInfo", QPOSService.Error.DEVICE_BUSY);
            return resultTable;
        } else if (emvAppCfg != null && !"".equals(emvAppCfg) && emvAppCfg.length() % 2 == 0) {
            if (emvCapkCfg != null && !"".equals(emvCapkCfg) && emvCapkCfg.length() % 2 == 0) {
                this.hX = emvAppCfg + "," + emvCapkCfg;
                if (!this.f(1)) {
                    return resultTable;
                } else {
                    String[] arrs = this.hX.split(",");
                    this.hZ = 0;
                    boolean f = this.im.a(this.ea, com.dspread.xpos.al.a.mk, this.hZ, arrs[0]);
                    if (f) {
                        this.hZ = 0;
                        this.im.a(this.ea, com.dspread.xpos.al.a.ml, this.hZ, arrs[1]);
                    }

                    resultTable.put("code", true);
                    resultTable.put("content", "");
                    this.s("syncUpdateEmvConfig");
                    return resultTable;
                }
            } else {
                resultTable.put("errorInfo", QPOSService.Error.INPUT_INVALID_FORMAT);
                return resultTable;
            }
        } else {
            resultTable.put("errorInfo", QPOSService.Error.INPUT_INVALID_FORMAT);
            return resultTable;
        }
    }

    public boolean syncGenerateQRCode(String data, String amount, int timeout) {
        if (!this.u(1)) {
            return false;
        } else if (!this.bM()) {
            return false;
        } else {
            String str = "0000";
            int dataLen = 0;
            if (null != data && !"".equals(data)) {
                dataLen = data.length();
            } else {
                data = "";
            }

            try {
                str = str + aw.byteArray2Hex(aw.P(dataLen)) + aw.byteArray2Hex(data.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException var10) {
                var10.printStackTrace();
            }

            int amountLen = 0;
            if (null != amount && !"".equals(amount)) {
                amountLen = amount.length();
            } else {
                amount = "";
            }

            str = str + aw.byteArray2Hex(aw.O(amountLen)) + aw.byteArray2Hex(amount.getBytes());
            i dc = new i(65, 128, timeout, aw.an(str));
            j uc = null;
            this.ea.a(dc);
            uc = this.ea.p(timeout);
            boolean f = this.b(uc);
            this.s("syncGenerateQRCode");
            return !f ? f : f;
        }
    }

    public void readEmvAppConfig() {
        if (this.aD()) {
            this.a(QPOSService.a.ku);
        }
    }

    public void readEmvCapkConfig() {
        if (this.aD()) {
            this.a(QPOSService.a.kv);
        }
    }

    public void setOpenReceiver(boolean flag) {
        if (this.ea != null) {
            this.ea.setOpenReceiver(flag);
        }
    }

    public void powerOnIcc() {
        if (this.aD()) {
            this.a(QPOSService.a.ki);
        }
    }

    public void powerOffIcc() {
        if (this.aD()) {
            this.a(QPOSService.a.kj);
        }
    }

    public void sendApdu(String apduString) {
        if (this.aD()) {
            if (apduString != null && !"".equals(apduString)) {
                this.hV = apduString;
                this.a(QPOSService.a.kk);
            } else {
                this.onError(QPOSService.Error.INPUT_INVALID_FORMAT);
            }
        }
    }

    public String sendApdu(String apduString, int timeout) {
        if (apduString != null && !"".equals(apduString)) {
            aq.af("sendApdu  -----1");
            return this.f(apduString, timeout);
        } else {
            this.onError(QPOSService.Error.INPUT_INVALID_FORMAT);
            return null;
        }
    }

    public void setPosSleepTime(int time) {
        if (this.aD()) {
            this.hW = time;
            this.a(QPOSService.a.kl);
        }
    }

    public void doTradeNoPinpad(int timeout) {
        er = false;
        this.doCheckCard(timeout);
    }

    public void setPinPadFlag(boolean flag) {
        er = flag;
    }

    public void doTrade() {
        this.doTrade(60);
    }

    public Hashtable<String, String> getICCTag(int cardType, int tagCount, String tagArrStr) {
        Hashtable<String, String> hashtable = new Hashtable();
        if (!this.az()) {
            if (!this.aD()) {
                return hashtable;
            }

            if (!this.f(1)) {
                return hashtable;
            }
        }

        String str = "00";
        str = str + aw.byteArray2Hex(aw.O(cardType));
        str = str + aw.byteArray2Hex(aw.O(cardType));
        str = str + aw.byteArray2Hex(aw.O(tagCount));
        if (tagArrStr == null || "".equals(tagArrStr)) {
            tagArrStr = "00";
        }

        str = str + tagArrStr;
        aq.ag("str: " + str);
        byte[] paras = aw.an(str.trim());
        i dc = new i(22, 81, 10, paras);
        this.ea.a(dc);
        j uc = this.ea.p(10);
        boolean f = this.b(uc);
        if (!this.az()) {
            this.s("getICCTag");
        }

        if (cardType == 1) {
            this.s("getICCTag cy");
        }

        if (!f) {
            return hashtable;
        } else {
            if (uc.L() == 73) {
                str = this.if.f(paras);
            } else {
                str = aw.byteArray2Hex(uc.a(0, uc.length()));
            }

            hashtable.put("tlv", str);
            return hashtable;
        }
    }

    public Hashtable<String, String> getICCTag(QPOSService.EncryptType encryType, int cardType, int tagCount, String tagArrStr) {
        Hashtable<String, String> hashtable = new Hashtable();
        if (!this.az()) {
            if (!this.aD()) {
                return hashtable;
            }

            if (!this.f(1)) {
                return hashtable;
            }
        }

        String str = "";
        if (encryType == QPOSService.EncryptType.ENCRYPTED) {
            str = "01";
        } else if (encryType == QPOSService.EncryptType.PLAINTEXT) {
            str = "00";
        }

        str = str + "00";
        str = str + aw.byteArray2Hex(aw.O(cardType));
        str = str + aw.byteArray2Hex(aw.O(tagCount));
        if (tagArrStr == null || "".equals(tagArrStr)) {
            tagArrStr = "00";
        }

        str = str + tagArrStr;
        aq.ag("str: " + str);
        byte[] paras = aw.an(str.trim());
        i dc = new i(22, 81, 10, paras);
        this.ea.a(dc);
        j uc = this.ea.p(10);
        boolean f = this.b(uc);
        if (!this.az()) {
            this.s("getICCTag");
        }

        if (cardType == 1) {
            this.s("getICCTag cy");
        }

        if (!f) {
            return hashtable;
        } else {
            if (uc.L() == 73) {
                str = this.if.f(paras);
            } else {
                str = aw.byteArray2Hex(uc.a(0, uc.length()));
            }

            hashtable.put("tlv", str);
            aq.ah("tlv===" + str);
            return hashtable;
        }
    }

    public Hashtable<String, String> getNFCBatchData() {
        return this.getICCTag(1, 0, "");
    }

    public void doTrade(Hashtable<String, Object> table) {
        if (this.aD()) {
            String random = (String)table.get("random");
            String extra = (String)table.get("extraData");
            int timeoutS = (Integer)table.get("timeout");
            QPOSService.TransactionType tradeTypeS = (QPOSService.TransactionType)table.get("transactionType");
            String currencyCodeS = (String)table.get("currencyCode");
            String subTime = (String)table.get("TransactionTime");
            int keyIndexS = (Integer)table.get("keyIndex");
            this.eP = (String)table.get("customDisplayString");
            this.ix = (QPOSService.CardTradeMode)table.get("cardTradeMode");
            this.ex = random;
            this.ey = extra;
            this.eu = aw.byteArray2Hex(new byte[]{this.a(tradeTypeS)});
            this.ew = currencyCodeS;
            this.ev = subTime + "FF";
            this.eq = Integer.valueOf(keyIndexS);
            this.en = Integer.valueOf(timeoutS);
            if (this.eq >= 5) {
                this.onError(QPOSService.Error.INPUT_INVALID_FORMAT);
            } else {
                this.setDoTradeMode(QPOSService.DoTradeMode.COMMON);
                this.a(QPOSService.a.ka);
            }
        }
    }

    public void doTrade(String random, String extra, int timeout) {
        if (this.aD()) {
            if (random != null && !"".equals(random) && random.length() == 6) {
                this.ex = random;
                this.ey = extra;
                this.eu = "";
                this.ew = "";
                this.en = timeout;
                this.setDoTradeMode(QPOSService.DoTradeMode.COMMON);
                this.a(QPOSService.a.ka);
            } else {
                this.onError(QPOSService.Error.INPUT_INVALID_FORMAT);
            }
        }
    }

    public void doTrade(String subTime, int timeout) {
        if (this.aD()) {
            if (subTime != null && !"".equals(subTime) && subTime.length() == 14) {
                this.ev = subTime + "FF";
                this.en = timeout;
                this.eu = "";
                this.ew = "";
                this.eq = 0;
                this.setDoTradeMode(QPOSService.DoTradeMode.COMMON);
                this.a(QPOSService.a.ka);
            } else {
                this.onError(QPOSService.Error.INPUT_INVALID_FORMAT);
            }
        }
    }

    public void doTrade(int keyIndex, int timeout) {
        if (this.aD()) {
            if (keyIndex > 10) {
                this.onError(QPOSService.Error.INPUT_INVALID_FORMAT);
            } else {
                this.en = timeout;
                this.ev = "";
                this.ex = "";
                this.ey = "";
                this.eu = "";
                this.ew = "";
                this.eq = keyIndex;
                this.setDoTradeMode(QPOSService.DoTradeMode.COMMON);
                this.a(QPOSService.a.ka);
            }
        }
    }

    public void doTrade(String subTime, String random, String extra, int keyIndex, int timeout) {
        if (this.aD()) {
            if (subTime != null && !"".equals(subTime) && subTime.length() != 14) {
                this.onError(QPOSService.Error.INPUT_INVALID_FORMAT);
            } else if (random != null && !"".equals(random) && random.length() != 6) {
                this.onError(QPOSService.Error.INPUT_INVALID_FORMAT);
            } else if (keyIndex > 5) {
                this.onError(QPOSService.Error.INPUT_INVALID_FORMAT);
            } else {
                aq.ah("--------------------------------");
                this.ev = subTime + "FF";
                this.ex = random;
                this.ey = extra;
                this.eq = keyIndex;
                this.eu = "";
                this.ew = "";
                this.en = timeout;
                this.setDoTradeMode(QPOSService.DoTradeMode.COMMON);
                this.a(QPOSService.a.ka);
            }
        }
    }

    public void setPosPresent(boolean flag) {
        this.setPosExistFlag(flag);
        if (flag && this.ea instanceof bj) {
            AudioManager localAudioManager = (AudioManager)this.P.getSystemService("audio");
            boolean f = localAudioManager.isWiredHeadsetOn();
            if (f) {
                this.setVolume(this.P);
            }
        }

    }

    public void setIsSaveLog(boolean isSaveLog) {
        this.hu = isSaveLog;
    }

    public boolean getIsSaveLog() {
        return this.hu;
    }

    public void setPanStatus(QPOSService.PanStatus status) {
        switch(SyntheticClass_1.jm[status.ordinal()]) {
        case 1:
            this.hv = "00";
            break;
        case 2:
            this.hv = "01";
            break;
        case 3:
            this.hv = "02";
        }

    }

    protected String cE() {
        return this.hv;
    }

    public LinkedHashMap<Integer, String> SynVIPOSBatchSendAPDU(LinkedHashMap<Integer, String[]> batchAPDU) {
        return this.ij.b(this.ea, batchAPDU);
    }

    public LinkedHashMap<Integer, String> SynVIPOSBatchSendAPDU(Boolean isOpen, LinkedHashMap<Integer, String[]> batchAPDU) {
        if (!isOpen) {
            if (!this.aD()) {
                return null;
            }

            if (!this.f(1)) {
                return null;
            }
        }

        LinkedHashMap<Integer, String> hashMap = this.ij.b(this.ea, batchAPDU);
        if (!isOpen) {
            this.s("SynVIPOSBatchSendAPDU");
        }

        return hashMap;
    }

    public void VIPOSBatchSendAPDU(LinkedHashMap<Integer, String[]> batchAPDU) {
        if (this.aD()) {
            if (batchAPDU == null) {
                this.onError(QPOSService.Error.INPUT_INVALID_FORMAT);
            } else {
                this.iZ = batchAPDU;
                this.a(QPOSService.a.kC);
            }
        }
    }

    public void doTrade(int timeout) {
        aq.ah("QPOSService doTrade: " + timeout);
        if (this.aD()) {
            this.eq = 0;
            this.en = timeout;
            this.setDoTradeMode(QPOSService.DoTradeMode.COMMON);
            this.a(QPOSService.a.ka);
        }
    }

    public void doTrade(int timeout, String batchId) {
        aq.ah("QPOSService doTrade2: " + timeout);
        this.jb = true;
        if (this.aD()) {
            this.eq = 0;
            this.en = timeout;
            this.ia = aw.aq(batchId);
            this.hG = this.ia.length();
            this.setDoTradeMode(QPOSService.DoTradeMode.CHECK_CARD_NO_IPNUT_PIN);
            (new Thread(new Runnable() {
                public void run() {
                    QPOSService.this.ja = QPOSService.this.cG();
                    if (QPOSService.this.ja) {
                        QPOSService.this.e(false);
                        QPOSService.this.a(QPOSService.a.ka);
                    } else {
                        QPOSService.this.onError(QPOSService.Error.TIMEOUT);
                    }

                }
            })).start();
        }
    }

    protected boolean cF() {
        return this.jb;
    }

    private boolean cG() {
        i dc = null;
        j uc = null;
        dc = new i(23, 160, 10, aw.an("05DF13050040000000"));
        this.ea.a(dc);
        uc = this.ea.p(10);
        boolean f = this.b(uc);
        if (!f) {
            return false;
        } else if (uc.K() == 0) {
            aq.ah("emv uc");
            return true;
        } else {
            return false;
        }
    }

    public int getBachidLen() {
        return this.hG;
    }

    public Hashtable<String, Object> syncDoTrade(int timeout) {
        if (!this.bM()) {
            return null;
        } else {
            this.setDoTradeMode(QPOSService.DoTradeMode.COMMON);
            if (!this.f(1)) {
                return null;
            } else if (er) {
                return this.bQ();
            } else {
                this.eO = "";
                Hashtable<String, Object> f = this.eg.a(this.ea, this.es, this.en, this.eO, this.ev, this.ex, this.ey, this.eq, this.ix);
                if (!(Boolean)f.get("result")) {
                    return f;
                } else {
                    this.s("syncDoTrade");
                    return f;
                }
            }
        }
    }

    public void doCheckCard(int timeout) {
        if (this.aD()) {
            this.eq = 0;
            this.en = timeout;
            this.setDoTradeMode(QPOSService.DoTradeMode.CHECK_CARD_NO_IPNUT_PIN);
            this.a(QPOSService.a.ka);
        }
    }

    public void doCheckCard(int timeout, int index) {
        if (this.aD()) {
            this.eq = index;
            this.en = timeout;
            this.setDoTradeMode(QPOSService.DoTradeMode.CHECK_CARD_NO_IPNUT_PIN);
            this.a(QPOSService.a.ka);
        }
    }

    public void doCheckCard() {
        this.doCheckCard(60);
    }

    public void getQposInfo() {
        if (this.aD()) {
            this.a(QPOSService.a.kd);
        }
    }

    private void a(String rsaRegin, String rsaN, String rsaE) {
        this.a("00", 10, rsaRegin, rsaN, rsaE);
    }

    public void getRSAText(int len) {
        if (this.aD()) {
            this.ic = len;
            this.a(QPOSService.a.ll);
        }
    }

    private void W(String pemFile) {
        ContextWrapper contextWrapper = new ContextWrapper(this.P);
        AssetManager assetManager = contextWrapper.getAssets();
        aj rsa = new aj();

        try {
            InputStream inputStream = assetManager.open(pemFile);
            rsa.c(inputStream);
            RSAPublicKey publicKey = rsa.cR();
            this.a((PublicKey)publicKey, (String)pemFile);
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }

    private void a(PublicKey key, String pemFile) {
        if (this.aD()) {
            try {
                RSAPublicKey publicKey = (RSAPublicKey)key;
                String n = publicKey.getModulus().toString(16);
                String e = publicKey.getPublicExponent().toString(16);
                pemFile = aw.aq(pemFile);
                this.a("01", 10, pemFile, n, "010001");
            } catch (Exception var6) {
                var6.printStackTrace();
            }

        }
    }

    private void a(String keyType, int grounp, String rsaRegin, String rsaN, String rsaE) {
        if (this.aD()) {
            this.iz = keyType;
            if (grounp < 16) {
                this.iA = "0" + Integer.toHexString(grounp);
            }

            this.iB = rsaRegin;
            this.iC = rsaN;
            this.iD = rsaE;
            this.a(QPOSService.a.kK);
        }
    }

    public void generateSessionKeys() {
        if (this.aD()) {
            if (!this.jc) {
                this.b("03", "0A", "", "", "");
            } else {
                this.b("04", "0A", "", "", "");
            }

        }
    }

    protected boolean cH() {
        return this.jc;
    }

    protected void o(boolean isGenerateSessionFlag) {
        this.jc = isGenerateSessionFlag;
    }

    private void b(String keyType, String grounp, String rsaRegin, String rsaN, String rsaE) {
        this.jc = !this.jc;
        this.o(this.jc);
        this.iz = keyType;
        this.iA = "0A";
        this.iB = rsaRegin;
        this.iC = rsaN;
        this.iD = rsaE;
        this.a(QPOSService.a.lk);
    }

    public void getEncryptData(byte[] data, String keyType, String keyIndex, int timeout) {
        if (this.az()) {
            this.a(data, keyType, keyIndex, timeout);
        } else {
            this.b(data, keyType, keyIndex, timeout);
        }

    }

    private void a(byte[] data, String keyType, String keyIndex, int timeout) {
        String dString = "";
        aq.af("data:" + data.length);
        if (data.length % 8 != 0) {
            byte[] a = new byte[data.length + 8 - data.length % 8];

            int i;
            for(i = 0; i < data.length; ++i) {
                a[i] = data[i];
            }

            for(i = data.length; i <= 8 - data.length % 8; ++i) {
                a[i] = 0;
            }

            dString = aw.byteArray2Hex(a);
            aq.ah("a2:" + aw.byteArray2Hex(a));
            aq.af("a len:" + a.length);
        } else {
            dString = aw.byteArray2Hex(data);
        }

        aq.af("str:" + dString);
        this.timeout = timeout;
        this.ip = dString;
        this.ho = "0" + keyType;
        this.id = "0" + keyIndex;
        this.a(dString, keyType, this.id, timeout);
    }

    private void b(byte[] data, String keyType, String keyIndex, int timeout) {
        if (this.aD()) {
            String dString = "";
            aq.af("data:" + data.length);
            if (data.length % 8 != 0) {
                byte[] a = new byte[data.length + 8 - data.length % 8];

                int i;
                for(i = 0; i < data.length; ++i) {
                    a[i] = data[i];
                }

                for(i = data.length; i <= 8 - data.length % 8; ++i) {
                    a[i] = 0;
                }

                dString = aw.byteArray2Hex(a);
                aq.ah("a2:" + aw.byteArray2Hex(a));
                aq.af("a len:" + a.length);
            } else {
                dString = aw.byteArray2Hex(data);
            }

            aq.af("str:" + dString);
            this.timeout = timeout;
            this.ip = dString;
            this.ho = "0" + keyType;
            this.id = "0" + keyIndex;
            this.a(QPOSService.a.li);
        }
    }

    public void getQposId() {
        this.getQposId(5);
    }

    public void isAsyncCardExist(int timeout) {
        this.timeout = timeout;
        if (this.aD()) {
            this.a(QPOSService.a.kb);
        }
    }

    public void getQposId(int timeout) {
        aq.ag("QPOSService getQposId");
        this.timeout = timeout;
        if (this.aD()) {
            this.a(QPOSService.a.kc);
        }
    }

    public void addKsn(String ksnType) {
        if (this.f(1)) {
            this.ig.f(this.ea, ksnType);
        }
    }

    public String getUpdateCheckValue() {
        return !this.f(1) ? null : this.ih.q(this.ea);
    }

    private j m(bi pos, int timeout) {
        i dc = null;
        j uc = null;
        dc = new i(16, 0, timeout);
        pos.a(dc);
        uc = pos.p(timeout);
        return uc;
    }

    public Hashtable<String, Object> syncGetQposId(int timeout) {
        Hashtable<String, Object> result = new Hashtable();
        result.put("code", false);
        this.timeout = timeout;
        if (!this.bM()) {
            return result;
        } else {
            j uc = this.m(this.ea, timeout);
            boolean f = this.a(uc, result);
            if (!f) {
                return result;
            } else {
                aq.ah("pos id : " + aw.byteArray2Hex(uc.a(0, uc.length())));
                int countLen = uc.length();
                int index = 0;
                int index = index + 1;
                int psamIdLen = uc.getByte(index);
                String psamId = aw.byteArray2Hex(uc.a(index, psamIdLen));
                index += psamIdLen;
                int posIdLen = uc.getByte(index++);
                String posId = aw.byteArray2Hex(uc.a(index, posIdLen));
                index += posIdLen;
                String merchantId = "";
                String vendorCode = "";
                String deviceNumber = "";
                String psamNo = "";
                int merchantIdLen = uc.getByte(index++);
                merchantId = new String(uc.a(index, merchantIdLen));
                index += merchantIdLen;
                int vendorCodeLen = uc.getByte(index++);
                vendorCode = new String(uc.a(index, vendorCodeLen));
                index += vendorCodeLen;
                byte csnLen;
                if (index < countLen) {
                    int deviceNumberLen = uc.getByte(index++);
                    deviceNumber = new String(uc.a(index, deviceNumberLen));
                    index += deviceNumberLen;
                    csnLen = uc.getByte(index++);
                    psamNo = new String(uc.a(index, csnLen));
                    index += csnLen;
                }

                String csn = "";
                if (index < countLen) {
                    csnLen = uc.getByte(index++);
                    csn = aw.byteArray2Hex(uc.a(index, csnLen));
                    index += csnLen;
                }

                String tmk0Status = "";
                String tmk1Status = "";
                String tmk2Status = "";
                String tmk3Status = "";
                String tmk4Status = "";
                byte keyboardflaglen;
                if (index < countLen) {
                    int tmk0Len = uc.getByte(index++);
                    tmk0Status = aw.byteArray2Hex(uc.a(index, tmk0Len));
                    index += tmk0Len;
                    keyboardflaglen = uc.getByte(index++);
                    tmk1Status = aw.byteArray2Hex(uc.a(index, keyboardflaglen));
                    index += keyboardflaglen;
                    int tmk2Len = uc.getByte(index++);
                    tmk2Status = aw.byteArray2Hex(uc.a(index, tmk2Len));
                    index += tmk2Len;
                    int tmk3Len = uc.getByte(index++);
                    tmk3Status = aw.byteArray2Hex(uc.a(index, tmk3Len));
                    index += tmk3Len;
                    int tmk4Len = uc.getByte(index++);
                    tmk4Status = aw.byteArray2Hex(uc.a(index, tmk4Len));
                    index += tmk4Len;
                }

                String keyboardflag = "";
                if (index < countLen) {
                    keyboardflaglen = uc.getByte(index++);
                    keyboardflag = aw.byteArray2Hex(uc.a(index, keyboardflaglen));
                    if ("00".equals(keyboardflag)) {
                        keyboardflag = "false";
                    } else {
                        keyboardflag = "true";
                    }

                    int var10000 = index + keyboardflaglen;
                }

                Hashtable<String, String> ksnDict = new Hashtable();
                ksnDict.put("posId", posId);
                ksnDict.put("psamId", psamId);
                ksnDict.put("merchantId", merchantId);
                ksnDict.put("vendorCode", vendorCode);
                ksnDict.put("deviceNumber", deviceNumber);
                ksnDict.put("psamNo", psamNo);
                ksnDict.put("csn", csn);
                ksnDict.put("tmk0Status", tmk0Status);
                ksnDict.put("tmk1Status", tmk1Status);
                ksnDict.put("tmk2Status", tmk2Status);
                ksnDict.put("tmk3Status", tmk3Status);
                ksnDict.put("tmk4Status", tmk4Status);
                ksnDict.put("isKeyboard", keyboardflag);
                result.put("code", true);
                result.put("content", ksnDict);
                this.s("syncGetQposId");
                return result;
            }
        }
    }

    public void doEmvApp(QPOSService.EmvOption emvOption) {
        this.a(emvOption);
        this.j(30003);
    }

    private void j(final int doTradeStatus) {
        this.h(doTradeStatus);
        this.i(doTradeStatus);
        (new Thread() {
            public void run() {
                QPOSService.this.k(doTradeStatus);
            }
        }).start();
    }

    public Hashtable<String, Object> syncDoEmvApp(QPOSService.EmvOption emvOption) {
        this.a(emvOption);
        if (!this.f(1)) {
            return null;
        } else if (!er) {
            this.bR();
            this.s("syncDoEmvApp");
            return null;
        } else {
            this.hw = true;

            while(!this.hw) {
                try {
                    Thread.sleep(30L);
                } catch (InterruptedException var3) {
                    var3.printStackTrace();
                }
            }

            return this.if.b(this.es, this.eu, this.ev, this.ew, this.ea, this.et);
        }
    }

    public Hashtable<String, Object> syncOnlineProcessResult(String iccData) {
        Hashtable<String, Object> rTable = new Hashtable();
        this.sendOnlineProcessResult(iccData);
        return this.if.a(iccData, this.ea, rTable);
    }

    public void cancelSetAmount() {
        aq.ah("cancelSetAmount");
        this.eQ.dO();
    }

    public void setAmountIcon(String amountIcon) {
        this.setAmountIcon(QPOSService.AmountType.MONEY_TYPE_CUSTOM_STR, amountIcon);
    }

    public void setAmountIcon(QPOSService.AmountType amtType, String amountIcon) {
        String str = "";
        if (amtType == QPOSService.AmountType.MONEY_TYPE_NONE) {
            str = "01";
        } else if (amtType == QPOSService.AmountType.MONEY_TYPE_RMB) {
            str = "02";
        } else if (amtType == QPOSService.AmountType.MONEY_TYPE_DOLLAR) {
            str = "03";
        } else if (amtType == QPOSService.AmountType.MONEY_TYPE_CUSTOM_STR) {
            this.eO = amountIcon;
            if (amountIcon != null && !"".equals(amountIcon)) {
                this.eO = aw.byteArray2Hex(amountIcon.trim().getBytes());
            }

            return;
        }

        this.eO = str;
    }

    public void release() {
        this.ea.destroy();
    }

    public void setAmount(String amount, String cashbackAmount, String currencyCode, QPOSService.TransactionType transactionType) {
        aq.ah("setAmount");
        this.es = amount;
        this.et = cashbackAmount;
        this.ew = currencyCode;
        aq.ah("transactionType :" + this.a(transactionType));
        this.eu = aw.byteArray2Hex(new byte[]{this.a(transactionType)});
        aq.ah("setAmount tradeType: " + this.eu);
        this.eQ.dP();
    }

    public void setAmount(String amount, String cashbackAmount, String currencyCode, QPOSService.TransactionType transactionType, boolean isPosDisplayAmount) {
        aq.ah("setAmount");
        this.es = amount;
        this.et = cashbackAmount;
        this.ew = currencyCode;
        aq.ah("transactionType :" + this.a(transactionType));
        this.eu = aw.byteArray2Hex(new byte[]{this.a(transactionType)});
        aq.ah("setAmount tradeType: " + this.eu);
        this.setPosDisplayAmountFlag(isPosDisplayAmount);
        this.eQ.dP();
    }

    public void selectEmvApp(int index) {
        this.t(index);
        this.if.a(com.dspread.xpos.s.a.dP);
    }

    public Hashtable<String, Object> syncSelectEmvApp(int index) {
        this.t(index);
        this.if.a(com.dspread.xpos.s.a.dP);
        Hashtable<String, Object> resultTable = new Hashtable();
        return this.if.a(this.ea, resultTable);
    }

    public void cancelSelectEmvApp() {
        this.t(9);
        this.if.a(com.dspread.xpos.s.a.dO);
    }

    public void finalConfirm(boolean isConfirmed) {
        if (isConfirmed) {
            this.if.a(com.dspread.xpos.s.a.dP);
        } else {
            this.if.a(com.dspread.xpos.s.a.dO);
        }

    }

    public void sendOnlineProcessResult(String tlv) {
        aq.ag("sendOnlineProcessResult");
        if (this.hy) {
            if (tlv != null && !tlv.equals("")) {
                if (tlv.startsWith("8A023030")) {
                    this.onRequestTransactionResult(QPOSService.TransactionResult.APPROVED);
                } else {
                    this.onRequestTransactionResult(QPOSService.TransactionResult.DECLINED);
                }

            }
        } else {
            this.if.k(tlv);
            this.if.a(com.dspread.xpos.s.a.dP);
        }
    }

    public void isServerConnected(boolean isConnected) {
        if (isConnected) {
            this.if.a(com.dspread.xpos.s.a.dP);
        } else {
            this.if.a(com.dspread.xpos.s.a.dO);
        }

    }

    protected String getTime() {
        return this.ev;
    }

    public void sendTime(String terminalTime) {
        aq.ah("terminalTime = " + terminalTime);
        this.ev = terminalTime;
        this.hw = true;
    }

    public void buildPinBLock(String workKey, String workKeyCheck, int isoformat, int keyIndex, int pinlength, String pan, String message) {
        this.a(workKey, workKeyCheck, isoformat, keyIndex, pinlength, pan, message);
    }

    public boolean isQposPresent() {
        return this.ef;
    }

    public static String getSdkVersion() {
        return "2.8.9";
    }

    public static String getSdkDate() {
        return "2018/05/14";
    }

    public static String getAudioVersion() {
        return String.valueOf(A01Kernel.b().java_get_jni_version());
    }

    public void sendPin(String pin) {
        if (pin == null || "".equals(pin)) {
            pin = "EMPTYPIN";
        }

        this.S(pin);
        this.if.a(com.dspread.xpos.s.a.dP);
    }

    public Hashtable<String, Object> syncSendPin(String pin) {
        if (pin == null || "".equals(pin)) {
            pin = "EMPTYPIN";
        }

        this.S(pin);
        this.if.a(com.dspread.xpos.s.a.dP);
        Hashtable<String, Object> resultTable = new Hashtable();
        return this.if.b(this.ea, resultTable);
    }

    protected void cI() {
        this.S("");
        this.if.a(com.dspread.xpos.s.a.dP);
    }

    public void bypassPin() {
        this.S("");
        this.if.a(com.dspread.xpos.s.a.dP);
    }

    public Hashtable<String, Object> syncBypassPin() {
        this.S("");
        this.if.a(com.dspread.xpos.s.a.dP);
        Hashtable<String, Object> resultTable = new Hashtable();
        return this.if.b(this.ea, resultTable);
    }

    public void cancelPin() {
        this.S("");
        this.if.a(com.dspread.xpos.s.a.dO);
    }

    public Hashtable<String, Object> syncCancelPin() {
        this.S("");
        this.if.a(com.dspread.xpos.s.a.dO);
        Hashtable<String, Object> resultTable = new Hashtable();
        return this.if.b(this.ea, resultTable);
    }

    public boolean isIdle() {
        return !this.az();
    }

    public void onDestroy() {
        if (hD != null) {
            it = true;
            int var1 = 0;

            while(this.az()) {
                try {
                    Thread.sleep(10L);
                } catch (InterruptedException var4) {
                    var4.printStackTrace();
                }

                if (var1++ == 150) {
                    break;
                }
            }

            if (this.iw == QPOSService.b.lu) {
                while(this.iw != QPOSService.b.lv) {
                    if (this.iw == QPOSService.b.lt) {
                        this.onError(QPOSService.Error.UNKNOWN);
                        return;
                    }

                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException var3) {
                        var3.printStackTrace();
                    }
                }
            }

            if (this.iX == QPOSService.e.lK) {
                this.onError(QPOSService.Error.DEVICE_BUSY);
            } else {
                aq.ah(">>>>>>>>>>>>>>>>>>>>onDestroy");
                this.ea.destroy();
                this.P = null;
                this.ea = null;
                hD = null;
                aq.ah(">>>>>>>>>>>>>>>>>>>>onDestroy end");
                it = false;
            }
        }
    }

    public Hashtable<String, String> anlysEmvIccData(String tlv) {
        return this.if.l(tlv);
    }

    public Hashtable<String, String> anlysEmvIccDataFor18(String tlv) {
        return this.if.m(tlv);
    }

    public boolean isIssScriptRes() {
        return this.if.isIssScriptRes();
    }

    public Hashtable<String, String> anlysEmvIccData_qf(String tlv) {
        return aw.av(tlv);
    }

    public Hashtable<String, String> anlysEmvIccData_zl(String tlv) {
        return aw.au(tlv);
    }

    public Hashtable<String, String> anlysEmvIccData_lp(String tlv) {
        return aw.as(tlv);
    }

    private void k(int doTradeStatus) {
        switch(doTradeStatus) {
        case 30001:
            aq.ah("TradeMsg.MSG_DO_TRADE");

            try {
                this.aG();
            } catch (Exception var68) {
                aq.ai(var68.getMessage());
                var68.printStackTrace();
                this.s("MSG_DO_TRADE");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30002:
            try {
                this.bO();
            } catch (Exception var67) {
                this.s("MSG_DO_TRADE_QF");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30003:
            try {
                this.bP();
            } catch (Exception var66) {
                this.s("MSG_DO_EMV_APP");
                var66.printStackTrace();
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30004:
            try {
                this.v(this.timeout);
            } catch (Exception var57) {
                this.s("MSG_IS_CARD_EXIST");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30005:
            try {
                this.w(this.timeout);
            } catch (Exception var65) {
                this.s("MSG_GET_POS_ID");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30006:
            try {
                this.aF();
            } catch (Exception var56) {
                this.s("MSG_GET_POS_INFO");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30007:
            try {
                this.co();
            } catch (Exception var52) {
                this.s("MSG_UPDATE_FIRMWARE");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30008:
            try {
                this.cp();
            } catch (Exception var51) {
                this.s("MSG_SIGNATURE");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30009:
            try {
                this.bX();
            } catch (Exception var50) {
                this.s("MSG_GET_PIN");
                this.onError(QPOSService.Error.UNKNOWN);
            }
        case 30010:
        case 30033:
        case 30041:
        case 30062:
        case 30063:
        case 30064:
        case 30065:
        default:
            break;
        case 30011:
            this.cA();
            break;
        case 30012:
            try {
                this.bY();
            } catch (Exception var48) {
                this.s("MSG_POWER_ON_ICC");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30013:
            try {
                this.ci();
            } catch (Exception var47) {
                this.s("MSG_POWER_OFF_ICC");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30014:
            try {
                this.ck();
            } catch (Exception var45) {
                this.s("MSG_SEND_APDU");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30015:
            try {
                this.cm();
            } catch (Exception var43) {
                this.s("MSG_SET_SLEEP_TIME");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30016:
            try {
                this.cn();
            } catch (Exception var42) {
                this.s("MSG_GET_CARDNO");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30017:
            try {
                this.aC();
            } catch (Exception var41) {
                this.s("MSG_LCD_SHOW_CUSTOM_DISPLAY");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30018:
            try {
                this.x(this.timeout);
            } catch (Exception var40) {
                this.s("MSG_UPDATE_FIRMWARE_TMK_ZZ");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30019:
            try {
                this.y(this.timeout);
            } catch (Exception var37) {
                this.s("MSG_CALCULATE_MAC");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30020:
            try {
                this.onError(QPOSService.Error.DEVICE_BUSY);
                boolean f = this.exit();
                this.s("MSG_EXIT_TRADE");
            } catch (Exception var49) {
                this.s("MSG_EXIT_TRADE222");
                this.onError(QPOSService.Error.UNKNOWN);
            }

            this.iX = QPOSService.e.lL;
            break;
        case 30021:
            try {
                this.cr();
            } catch (Exception var36) {
                this.s("MSG_UPDATE_EMV_APP_CONFIG");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30022:
            try {
                this.cs();
            } catch (Exception var35) {
                this.s("MSG_UPDATE_EMV_CAPK_CONFIG");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30023:
            try {
                this.cq();
            } catch (Exception var32) {
                this.s("MSG_UPDATE_EMV_CONFIG");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30024:
            try {
                this.cu();
            } catch (Exception var34) {
                this.s("MSG_READ_EMV_APP_CONFIG");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30025:
            try {
                this.ct();
            } catch (Exception var33) {
                this.s("MSG_READ_EMV_CAPK_CONFIG");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30026:
            try {
                this.cx();
            } catch (Exception var31) {
                this.s("MSG_GET_ICC_EMV_DATA");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30027:
            try {
                this.G(this.timeout);
            } catch (Exception var23) {
                this.s("MSG_SET_MASTER_KEY");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30028:
            try {
                this.cy();
            } catch (Exception var22) {
                this.s("MSG_VIPOS_BATCH_SEND_APDU");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30029:
            Looper.myLooper().quit();
            break;
        case 30030:
            try {
                this.cv();
            } catch (Exception var21) {
                this.s("MSG_SAVE_USER_DATA");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30031:
            try {
                this.cw();
            } catch (Exception var20) {
                this.s("MSG_READ_USER_DATA");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30032:
            try {
                this.cz();
            } catch (Exception var19) {
                this.s("MSG_UPGRADE_CPU");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30034:
            try {
                this.z(this.timeout);
            } catch (Exception var30) {
                this.s("MSG_DOWNLOAD_RSA_PUBLICKEY");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30035:
            try {
                this.A(this.timeout);
            } catch (Exception var29) {
                this.s("MSG_DO_INTO_MENU");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30036:
            try {
                this.B(this.timeout);
            } catch (Exception var28) {
                this.s("MSG_DO_UPDATE_MASTER_KEY");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30037:
            try {
                this.C(this.timeout);
            } catch (Exception var27) {
                this.s("MSG_DO_UPDATE_MASTER_KEY_2");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30038:
            try {
                this.D(this.timeout);
            } catch (Exception var26) {
                this.s("MSG_DO_UPDATE_MASTER_KEY_3");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30039:
            try {
                this.F(this.timeout);
            } catch (Exception var24) {
                this.s("MSG_DO_PINKEY_TDES");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30040:
            try {
                this.g(this.iP, this.timeout);
            } catch (Exception var38) {
                this.s("MSG_UPDATE_FIRMWARE_TSK_ZZ");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30042:
            try {
                this.E(this.timeout);
            } catch (Exception var25) {
                this.s("MSG_DO_UPDATE_MASTER_BY_RANDOM");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30043:
            try {
                this.H(this.timeout);
            } catch (Exception var18) {
                this.s("MSG_SET_MerchantID");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30044:
            try {
                this.I(this.timeout);
            } catch (Exception var17) {
                this.s("MSG_SET_TerminalID");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30045:
            try {
                this.K(this.timeout);
            } catch (Exception var15) {
                this.s("MSG_Get_Input_Amount");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30046:
            try {
                this.L(this.timeout);
            } catch (Exception var14) {
                this.s("MSG_SET_SystemDateTime");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30047:
            try {
                this.bZ();
            } catch (Exception var13) {
                this.s("MSG_POWER_ON_NFC");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30048:
            try {
                this.cl();
            } catch (Exception var44) {
                this.s("MSG_SEND_APDU_NFC");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30049:
            try {
                this.cj();
            } catch (Exception var46) {
                this.s("MSG_POWER_OFF_NFC");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30050:
            try {
                this.J(this.timeout);
            } catch (Exception var16) {
                this.s("MSG_SET_TerminalMerchantID");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30051:
            try {
                this.ca();
            } catch (Exception var12) {
                this.s("MSG_getMagneticTrackPlaintext");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30052:
            try {
                this.cb();
            } catch (Exception var11) {
                this.s("MSG_4011");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30053:
            try {
                this.cc();
                this.s("MSG_CBC_MAC 22");
            } catch (Exception var10) {
                this.s("MSG_CBC_MAC");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30054:
            try {
                this.cd();
            } catch (Exception var9) {
                this.s("MSG_READ_BUSINESS_CARD");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30055:
            try {
                this.ce();
            } catch (Exception var8) {
                this.s("MSG_WRITE_BUSINESS_CARD");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30056:
            try {
                this.cf();
            } catch (Exception var7) {
                this.s("MSG_Confirm_Amount");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30057:
            try {
                this.cg();
            } catch (Exception var6) {
                this.s("MSG_GET_PIN1071");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30058:
            try {
                this.ch();
            } catch (Exception var5) {
                this.s("MSG_CUSTOM_INPUT_DISPLAY");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30059:
            try {
                this.aG();
            } catch (Exception var69) {
                var69.printStackTrace();
                this.s("MSG_DO_TRADE_BY_GOOD");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30060:
            try {
                this.a(this.ip, this.ho, this.id, this.timeout);
                this.s("MSG_DO_ENCRYPT_DATA");
            } catch (Exception var53) {
                this.s("MSG_DO_ENCRYPT_DATA");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30061:
            try {
                this.a(this.timeout, this.iL, this.iO, this.iM, this.iN, this.message);
            } catch (Exception var39) {
                this.s("MSG_UPDATE_WORKKEY");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30066:
            try {
                this.hH.c(this.ea, this.hm, this.timeout);
            } catch (Exception var64) {
                this.s("MSG_DO_MAFIRE");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30067:
            try {
                this.ih.e(this.ea, this.hp, this.hq);
            } catch (Exception var60) {
                this.s("MSG_DO_MAFIRE");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30068:
            try {
                this.ih.c(this.ea, this.hp, this.hr);
            } catch (Exception var58) {
                this.s("MSG_DO_MAFIRE");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30069:
            try {
                String rsaNLen = Integer.toHexString(this.iC.length() / 2);
                if (rsaNLen.length() == 1) {
                    rsaNLen = "000" + rsaNLen;
                } else if (rsaNLen.length() == 2) {
                    rsaNLen = "00" + rsaNLen;
                } else if (rsaNLen.length() == 3) {
                    rsaNLen = "0" + rsaNLen;
                }

                this.doSetRsaPublicKey(rsaNLen + this.iC + "0003010001");
            } catch (Exception var55) {
                var55.printStackTrace();
                this.s("MSG_UPDATE_RSA");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30070:
            try {
                this.a(this.iz, this.iA, this.iB, this.iC, this.iD);
            } catch (Exception var54) {
                var54.printStackTrace();
                this.s("MSG_GET_POS_INFO");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30071:
            try {
                this.ih.v(this.ea, this.ic);
            } catch (Exception var59) {
                this.s("MSG_GET_PUBLIC_RSA");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30072:
            try {
                this.ig.f(this.ea, this.iE);
            } catch (Exception var63) {
                this.s("MSG_DO_MAFIRE");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30073:
            try {
                this.ig.g(this.ea, this.iE);
            } catch (Exception var62) {
                this.s("MSG_DO_MAFIRE");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30074:
            try {
                this.ig.h(this.ea);
            } catch (Exception var61) {
                this.s("MSG_DO_MAFIRE");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30075:
            try {
                if (!this.f(1)) {
                    return;
                }

                this.ig.a(this.ea, this.eq, this.hK, this.timeout, this.hL);
            } catch (Exception var4) {
                this.s("MSG_GET_CHECKVALUE_KEYTYPE");
                this.onError(QPOSService.Error.UNKNOWN);
            }
            break;
        case 30076:
            try {
                if (!this.f(1)) {
                    return;
                }

                this.ig.r(this.ea, this.timeout);
            } catch (Exception var3) {
                this.s("MSG_GET_DEVICE_PUBKEY");
                this.onError(QPOSService.Error.UNKNOWN);
            }
        }

    }

    protected void onRequestDeviceScanFinished() {
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onRequestDeviceScanFinished();
                }

            }
        });
    }

    protected void onSetManagementKey(final boolean b) {
        this.s("onEncryptData");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onSetManagementKey(b);
                }

            }
        });
    }

    protected void onSetSleepModeTime(final boolean b) {
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onSetSleepModeTime(b);
                }

            }
        });
    }

    protected void onGetSleepModeTime(final String b) {
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onGetSleepModeTime(b);
                }

            }
        });
    }

    protected void onGetShutDownTime(final String b) {
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onGetShutDownTime(b);
                }

            }
        });
    }

    protected void onEncryptData(final String b) {
        this.s("onEncryptData");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onEncryptData(b);
                }

            }
        });
    }

    protected void onAddKey(final boolean b) {
        this.s("onAddKey");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onAddKey(b);
                }

            }
        });
    }

    protected void onSetBuzzerResult(final boolean b) {
        this.s("onSetBuzzerResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onSetBuzzerResult(b);
                }

            }
        });
    }

    protected void onSetBuzzerTimeResult(final boolean b) {
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onSetBuzzerTimeResult(b);
                }

            }
        });
    }

    protected void onSetBuzzerStatusResult(final boolean b) {
        this.s("onSetBuzzerStatusResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onSetBuzzerStatusResult(b);
                }

            }
        });
    }

    protected void onGetBuzzerStatusResult(final String b) {
        this.s("onGetBuzzerStatusResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onGetBuzzerStatusResult(b);
                }

            }
        });
    }

    protected void onConfirmAmountResult(final boolean b) {
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onConfirmAmountResult(b);
                }

            }
        });
    }

    protected void onWriteBusinessCardResult(final boolean b) {
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onWriteBusinessCardResult(b);
                }

            }
        });
    }

    protected void onReadBusinessCardResult(final boolean b, final String result) {
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onReadBusinessCardResult(b, result);
                }

            }
        });
    }

    protected void onCbcMacResult(final String result) {
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onCbcMacResult(result);
                }

            }
        });
    }

    protected void onReturnPowerOffNFCResult(final boolean result) {
        this.s("onReturnPowerOffNFCResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onReturnPowerOffNFCResult(result);
                }

            }
        });
    }

    protected void onReturnPowerOnNFCResult(final boolean result, final String ksn, final String atr, final int atrLen) {
        this.s("onReturnPowerOnNFCResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onReturnPowerOnNFCResult(result, ksn, atr, atrLen);
                }

            }
        });
    }

    protected void onReturnNFCApduResult(final boolean result, final String apdu, final int apduLen) {
        this.s("onReturnNFCApduResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onReturnNFCApduResult(result, apdu, apduLen);
                }

            }
        });
    }

    protected void onGetInputAmountResult(final boolean b, final String amount) {
        this.s("onGetInputAmountResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onGetInputAmountResult(b, amount);
                }

            }
        });
    }

    protected void onSetParamsResult(final boolean b, final Hashtable<String, Object> resultTable) {
        this.s("onSetParamsResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onSetParamsResult(b, resultTable);
                }

            }
        });
    }

    protected void onPinKey_TDES_Result(final String result) {
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onPinKey_TDES_Result(result);
                }

            }
        });
    }

    protected void onUpdatePosFirmwareResult(final QPOSService.UpdateInformationResult result) {
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onUpdatePosFirmwareResult(result);
                }

            }
        });
    }

    protected void onBluetoothBoardStateResult(final boolean result) {
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onBluetoothBoardStateResult(result);
                }

            }
        });
    }

    protected void onLcdShowCustomDisplay(final boolean isSuccess) {
        this.s("onLcdShowCustomDisplay");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onLcdShowCustomDisplay(isSuccess);
                }

            }
        });
    }

    protected void onReturniccCashBack(final Hashtable<String, String> result) {
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onReturniccCashBack(result);
                }

            }
        });
    }

    protected void onBluetoothBondTimeout() {
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onBluetoothBondTimeout();
                }

            }
        });
    }

    protected void onBluetoothBondFailed() {
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onBluetoothBondFailed();
                }

            }
        });
    }

    protected void onBluetoothBonded() {
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onBluetoothBonded();
                }

            }
        });
    }

    protected void onBluetoothBonding() {
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onBluetoothBonding();
                }

            }
        });
    }

    protected void onReturnBatchSendAPDUResult(final LinkedHashMap<Integer, String> batchAPDUResult) {
        this.s("onReturnBatchSendAPDUResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onReturnBatchSendAPDUResult(batchAPDUResult);
                }

            }
        });
    }

    protected void onReturnSetMasterKeyResult(final boolean isSuccess) {
        this.s("onReturnSetMasterKeyResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onReturnSetMasterKeyResult(isSuccess);
                }

            }
        });
    }

    protected void onRequestUpdateKey(final String result) {
        this.s("onRequestUpdateKey");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onRequestUpdateKey(result);
                }

            }
        });
    }

    protected void onReturnUpdateIPEKResult(final boolean isSuccess) {
        this.s("onReturnUpdateIPEKResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onReturnUpdateIPEKResult(isSuccess);
                }

            }
        });
    }

    protected void onReturnRSAResult(final String isSuccess) {
        this.s("onReturnRSAResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onReturnRSAResult(isSuccess);
                }

            }
        });
    }

    protected void onReturnUpdateEMVResult(final boolean isSuccess) {
        this.s("onReturnUpdateEMVResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onReturnUpdateEMVResult(isSuccess);
                }

            }
        });
    }

    protected void onReturnGetQuickEmvResult(final boolean isSuccess) {
        this.s("onReturnGetQuickEmvResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onReturnGetQuickEmvResult(isSuccess);
                }

            }
        });
    }

    protected void onReturnGetEMVListResult(final String data) {
        this.s("onReturnGetEMVListResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onReturnGetEMVListResult(data);
                }

            }
        });
    }

    protected void onReturnUpdateEMVRIDResult(final boolean isSuccess) {
        this.s("onReturnUpdateEMVRIDResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onReturnUpdateEMVRIDResult(isSuccess);
                }

            }
        });
    }

    protected void onDeviceFound(final BluetoothDevice device) {
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onDeviceFound(device);
                }

            }
        });
    }

    protected void onRequestSetPin() {
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onRequestSetPin();
                }

            }
        });
    }

    protected void onReturnCustomConfigResult(final boolean isSuccess, final String result) {
        this.s("onReturnCustomConfigResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onReturnCustomConfigResult(isSuccess, result);
                }

            }
        });
    }

    protected void onRequestCalculateMac(final String calMac) {
        this.s("onRequestCalculateMac");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onRequestCalculateMac(calMac);
                }

            }
        });
    }

    protected void onGetCardNoResult(final String cardNo) {
        this.s("onGetCardNoResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onGetCardNoResult(cardNo);
                }

            }
        });
    }

    protected void onReturnSetSleepTimeResult(final boolean isSuccess) {
        this.s("onReturnSetSleepTimeResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onReturnSetSleepTimeResult(isSuccess);
                }

            }
        });
    }

    protected void onReturnApduResult(final boolean isSuccess, final String apdu, final int apduLen) {
        this.s("onReturnApduResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onReturnApduResult(isSuccess, apdu, apduLen);
                }

            }
        });
    }

    protected void onReturnPowerOffIccResult(final boolean isSuccess) {
        this.s("onReturnPowerOffIccResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onReturnPowerOffIccResult(isSuccess);
                }

            }
        });
    }

    protected void onReturnPowerOnIccResult(final boolean isSuccess, final String ksn, final String atr, final int atrLen) {
        this.s("onReturnPowerOnIccResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onReturnPowerOnIccResult(isSuccess, ksn, atr, atrLen);
                }

            }
        });
    }

    protected void onReturnGetPinResult(final Hashtable<String, String> result) {
        this.s("onReturnGetPinResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onReturnGetPinResult(result);
                }

            }
        });
    }

    protected void onReturnReversalData(final String tlv) {
        this.s("onReturnReversalData");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onReturnReversalData(tlv);
                }

            }
        });
    }

    protected void onEmvICCExceptionData(final String tlv) {
        this.s("onEmvICCExceptionData");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onEmvICCExceptionData(tlv);
                }

            }
        });
    }

    protected void onRequestUpdateWorkKeyResult(final QPOSService.UpdateInformationResult result) {
        this.s("onRequestUpdateWorkKeyResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onRequestUpdateWorkKeyResult(result);
                }

            }
        });
    }

    protected void onRequestSignatureResult(final byte[] paras) {
        this.s("onRequestSignatureResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onRequestSignatureResult(paras);
                }

            }
        });
    }

    protected void onRequestDisplay(final QPOSService.Display displayMsg) {
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onRequestDisplay(displayMsg);
                }

            }
        });
    }

    protected void onDoTradeResult(final QPOSService.DoTradeResult result, final Hashtable<String, String> decodeData) {
        if (result != QPOSService.DoTradeResult.ICC && result != QPOSService.DoTradeResult.BAD_SWIPE) {
            this.s("onDoTradeResult");
        }

        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onDoTradeResult(result, decodeData);
                }

            }
        });
    }

    protected void onRequestSetAmount() {
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onRequestSetAmount();
                }

            }
        });
    }

    protected void onRequestSelectEmvApp(final ArrayList<String> appList) {
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onRequestSelectEmvApp(appList);
                }

            }
        });
    }

    protected void onRequestFinalConfirm() {
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onRequestFinalConfirm();
                }

            }
        });
    }

    protected void onQposInfoResult(final Hashtable<String, String> deviceInfoData) {
        this.s("onQposInfoResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onQposInfoResult(deviceInfoData);
                }

            }
        });
    }

    protected void p(final boolean flag) {
        this.s("onDoSetRsaPublicKey");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onQposDoSetRsaPublicKey(flag);
                }

            }
        });
    }

    protected void onQposGenerateSessionKeysResult(final Hashtable<String, String> rsaData) {
        this.s("onQposGenerateSessionKeysResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onQposGenerateSessionKeysResult(rsaData);
                }

            }
        });
    }

    protected void onSearchMifareCardResult(final Hashtable<String, String> cardData) {
        this.s("onSearchMifareCardResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onSearchMifareCardResult(cardData);
                }

            }
        });
    }

    protected void onBatchReadMifareCardResult(final String msg, final Hashtable<String, List<String>> cardData) {
        this.s("onSearchMifareCardResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onBatchReadMifareCardResult(msg, cardData);
                }

            }
        });
    }

    protected void onBatchWriteMifareCardResult(final String msg, final Hashtable<String, List<String>> cardData) {
        this.s("onSearchMifareCardResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onBatchWriteMifareCardResult(msg, cardData);
                }

            }
        });
    }

    protected void onFinishMifareCardResult(final boolean flag) {
        this.s("onFinishMifareCardResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onFinishMifareCardResult(flag);
                }

            }
        });
    }

    protected void onVerifyMifareCardResult(final boolean flag) {
        this.s("onFinishMifareCardResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onVerifyMifareCardResult(flag);
                }

            }
        });
    }

    protected void onReadMifareCardResult(final Hashtable<String, String> flag) {
        this.s("onFinishMifareCardResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onReadMifareCardResult(flag);
                }

            }
        });
    }

    protected void onWriteMifareCardResult(final boolean flag) {
        this.s("onFinishMifareCardResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onWriteMifareCardResult(flag);
                }

            }
        });
    }

    protected void onOperateMifareCardResult(final Hashtable<String, String> flag) {
        this.s("onFinishMifareCardResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onOperateMifareCardResult(flag);
                }

            }
        });
    }

    protected void getMifareCardVersion(final Hashtable<String, String> flag) {
        this.s("getMifareCardVersion");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.getMifareCardVersion(flag);
                }

            }
        });
    }

    protected void getMifareReadData(final Hashtable<String, String> flag) {
        this.s("getMifareReadData");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.getMifareReadData(flag);
                }

            }
        });
    }

    protected void getMifareFastReadData(final Hashtable<String, String> flag) {
        this.s("getMifareReadData");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.getMifareFastReadData(flag);
                }

            }
        });
    }

    protected void writeMifareULData(final String flag) {
        this.s("getMifareReadData");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.writeMifareULData(flag);
                }

            }
        });
    }

    protected void verifyMifareULData(final Hashtable<String, String> flag) {
        this.s("getMifareReadData");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.verifyMifareULData(flag);
                }

            }
        });
    }

    protected void transferMifareData(final String flag) {
        this.s("transferMifareData");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.transferMifareData(flag);
                }

            }
        });
    }

    protected void onRequestOnlineProcess(final String tlv) {
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onRequestOnlineProcess(tlv);
                }

            }
        });
    }

    protected void onRequestIsServerConnected() {
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onRequestIsServerConnected();
                }

            }
        });
    }

    protected void onRequestTime() {
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onRequestTime();
                }

            }
        });
    }

    protected void onRequestTransactionResult(final QPOSService.TransactionResult transactionResult) {
        this.s("onRequestTransactionResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onRequestTransactionResult(transactionResult);
                }

            }
        });
    }

    protected void onRequestTransactionLog(final String tlv) {
        this.s("onRequestTransactionLog");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onRequestTransactionLog(tlv);
                }

            }
        });
    }

    protected void onRequestBatchData(final String tlv) {
        this.s("onRequestBatchData");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onRequestBatchData(tlv);
                }

            }
        });
    }

    protected void onRequestQposConnected() {
        aq.ah("onRequestQposConnected");
        this.setPosExistFlag(true);
        this.s("onRequestQposConnected");
        if (this.handler != null) {
            this.handler.post(new Runnable() {
                public void run() {
                    if (QPOSService.this.listener != null) {
                        try {
                            Thread.sleep(500L);
                        } catch (InterruptedException var2) {
                            var2.printStackTrace();
                        }

                        QPOSService.this.listener.onRequestQposConnected();
                    } else {
                        Log.i("POS_SDK", ">>>>>>>QPOSServiceListener is null");
                    }

                }
            });
        }
    }

    protected void onRequestQposDisconnected() {
        if (this.iK) {
            this.iK = false;
        } else {
            aq.ah("onRequestQposDisconnected");
            if (this.az()) {
                this.s("onRequestQposDisconnected");
                this.e(false);
            }

            this.setPosExistFlag(false);
            if (this.handler != null) {
                this.handler.postDelayed(new Runnable() {
                    public void run() {
                        if (QPOSService.this.listener != null) {
                            QPOSService.this.listener.onRequestQposDisconnected();
                        } else {
                            Log.i("POS_SDK", ">>>>>>>QPOSServiceListener is null");
                        }

                    }
                }, 500L);
            }
        }
    }

    protected void onRequestNoQposDetected() {
        aq.ah("onRequestNoQposDetected");
        if (hD != null) {
            if (this.az()) {
                this.s("onRequestNoQposDetected");
                this.e(false);
            }

            this.ea.g("");
            this.setPosExistFlag(false);
            if (this.handler != null) {
                this.handler.post(new Runnable() {
                    public void run() {
                        if (QPOSService.this.listener != null) {
                            if (QPOSService.this.iJ) {
                                QPOSService.this.listener.onRequestNoQposDetectedUnbond();
                            } else {
                                QPOSService.this.listener.onRequestNoQposDetected();
                            }
                        } else {
                            Log.i("POS_SDK", ">>>>>>>QPOSServiceListener is null");
                        }

                    }
                });
            }
        }
    }

    protected void onError(final QPOSService.Error errorState) {
        if (errorState != QPOSService.Error.DEVICE_BUSY) {
            this.s("onError");
        }

        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onError(errorState);
                }

            }
        });
    }

    protected void onRequestWaitingUser() {
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onRequestWaitingUser();
                }

            }
        });
    }

    protected void onQposIsCardExist(final boolean haveCard) {
        this.s("onQposIsCardExist");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onQposIsCardExist(haveCard);
                }

            }
        });
    }

    protected void onQposDoTradeLog(final boolean isSuccess) {
        this.s("onQposDoTradeLog");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onQposDoTradeLog(isSuccess);
                }

            }
        });
    }

    protected void onQposDoGetTradeLogNum(final String data) {
        this.s("onQposDoGetTradeLogNum");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onQposDoGetTradeLogNum(data);
                }

            }
        });
    }

    protected void onQposDoGetTradeLog(final String data, final String orderId) {
        this.s("onQposDoTradeLog");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onQposDoGetTradeLog(data, orderId);
                }

            }
        });
    }

    protected void onQposIdResult(final Hashtable<String, String> ksnDict) {
        this.s("onQposIdResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onQposIdResult(ksnDict);
                }

            }
        });
    }

    protected void onQposKsnResult(final Hashtable<String, String> ksn) {
        this.s("onQposIdResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onQposIdResult(ksn);
                }

            }
        });
    }

    protected void onReturnDownloadRsaPublicKey(final HashMap<String, String> map) {
        this.s("onReturnDownloadRsaPublicKey");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onReturnDownloadRsaPublicKey(map);
                }

            }
        });
    }

    protected void onGetPosComm(final int mod, final String amount, final String posid) {
        this.s("onGetPosComm");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onGetPosComm(mod, amount, posid);
                }

            }
        });
    }

    protected void onUpdateMasterKeyResult(final boolean result, final Hashtable<String, String> resultTable) {
        this.s("onUpdateMasterKeyResult");
        this.handler.post(new Runnable() {
            public void run() {
                if (QPOSService.this.listener != null) {
                    QPOSService.this.listener.onUpdateMasterKeyResult(result, resultTable);
                }

            }
        });
    }

    public void setMainContext(Context context) {
        this.eA = context;
    }

    protected void X(String str) {
        (new Builder(this.eA)).setTitle("Title").setMessage(str).setPositiveButton("OK", (OnClickListener)null).show();
    }

    protected boolean Y(String posid) {
        if (this.P != null) {
            try {
                SharedPreferences mySharedPreferences = this.P.getSharedPreferences("dsp_data_x", 0);
                Editor editor = mySharedPreferences.edit();
                editor.putString("dsp_qposId_x", posid);
                editor.commit();
                return true;
            } catch (Exception var4) {
            }
        }

        return false;
    }

    protected String cJ() {
        if (this.P != null) {
            try {
                SharedPreferences mySharedPreferences = this.P.getSharedPreferences("dsp_data_x", 0);
                String posid = mySharedPreferences.getString("dsp_qposId_x", "");
                return posid;
            } catch (Exception var3) {
            }
        }

        return "";
    }

    protected String k(byte[] bs) {
        this.ea.x(bs);
        j uc = this.ea.p(5);
        return uc == null ? "" : aw.byteArray2Hex(uc.M());
    }

    private void cK() {
        if (this.P != null) {
            if (this.hs == null) {
                AudioManager localAudioManager = (AudioManager)this.P.getSystemService("audio");
                boolean f = localAudioManager.isWiredHeadsetOn();
                if (!f) {
                    this.onRequestNoQposDetected();
                }

                this.setPosExistFlag(f);
                this.hs = new QPOSService.d();
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.HEADSET_PLUG");
                this.P.registerReceiver(this.hs, intentFilter);
            }

        }
    }

    private boolean cL() {
        if (this.P == null) {
            return false;
        } else {
            if (this.hs == null) {
                AudioManager localAudioManager = (AudioManager)this.P.getSystemService("audio");
                boolean f = localAudioManager.isWiredHeadsetOn();
                if (!f) {
                    return f;
                }

                this.setPosExistFlag(f);
                this.hs = new QPOSService.d();
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.HEADSET_PLUG");
                this.P.registerReceiver(this.hs, intentFilter);
            }

            return true;
        }
    }

    public void openUartK7() {
        if (this.ea != null && this.ea instanceof bg) {
            ((bg)this.ea).aj();
            this.setPosExistFlag(true);
            this.onRequestQposConnected();
        }

    }

    public void closeUartK7() {
        if (this.ea != null && this.ea instanceof bg) {
            ((bg)this.ea).ak();
        }

        this.setPosExistFlag(false);
        this.onRequestQposDisconnected();
    }

    public void openUsb() {
        if (this.ea != null) {
            aq.ag("open usb");
            boolean f = this.ea.Y();
            this.setPosExistFlag(f);
            if (f) {
                this.onRequestQposConnected();
                return;
            }
        }

        this.setPosExistFlag(false);
        this.onRequestQposDisconnected();
    }

    public int getUpdateTime() {
        return ((be)this.ea).getUpdateTime();
    }

    public void setUpdateTime(int updatetime) {
        ((be)this.ea).setUpdateTime(updatetime);
    }

    public void isUsbUpdat(boolean flag) {
        this.jd = flag;
    }

    public void openUsb(UsbDevice usbDevice) {
        this.jd = true;
        if (this.ea != null) {
            if (usbDevice == null) {
                usbDevice = this.getUsbDevice();
                if (usbDevice == null) {
                    this.setPosExistFlag(false);
                    this.onRequestNoQposDetected();
                    return;
                }
            }

            ((be)this.ea).setUsbDevice(usbDevice);
            boolean f = this.ea.Y();
            aq.ag("UsbSerialPort open f: " + f);
            this.setPosExistFlag(f);
            if (f) {
                this.onRequestQposConnected();
                return;
            }
        }

        this.setPosExistFlag(false);
        this.onRequestQposDisconnected();
    }

    public void closeUsb() {
        if (this.ea != null) {
            this.ea.close();
        }

        this.setPosExistFlag(false);
        this.onRequestQposDisconnected();
    }

    public void openUart() {
        if (this.ea != null) {
            boolean f = this.ea.Y();
            this.setPosExistFlag(f);
            if (f) {
                this.onRequestQposConnected();
                return;
            }
        }

        this.onRequestQposDisconnected();
    }

    public void closeUart() {
        if (this.ea != null) {
            this.ea.close();
        }

        this.setPosExistFlag(false);
        this.onRequestQposDisconnected();
    }

    public boolean getBluetoothState() {
        aq.ah("QPOSService getBluetoothState");
        if (this.ea != null) {
            boolean f = this.ea.U();
            aq.ah("getBluetoothState ======== " + f);
            return f;
        } else {
            return false;
        }
    }

    public boolean getBluetoothState2(QPOSService.CommunicationMode mode) {
        if (this.ea != null) {
            if (mode == QPOSService.CommunicationMode.BLUETOOTH) {
                return this.ea.U();
            }

            if (mode == QPOSService.CommunicationMode.BLUETOOTH_2Mode) {
                hJ.addAction("com.disbluetooth");
                hJ.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
                this.P.registerReceiver(hI, hJ);
                return hF;
            }
        }

        return false;
    }

    public int updatePosFirmware(byte[] data, String deviceAddress) {
        if (!this.aD()) {
            return -1;
        } else {
            this.ea.z(data);
            if (deviceAddress != null && !"".equals(deviceAddress)) {
                this.deviceAddress = deviceAddress;
            }

            if (!this.jd) {
                i dc = null;
                j uc = null;
                dc = new i(17, 48, 5);
                this.ea.a(dc);
                uc = this.ea.p(5);
                if (uc != null) {
                    boolean f = this.b(uc);
                    if (!f) {
                        return 0;
                    }

                    int index = 0;
                    int index = index + 1;
                    int bootloaderVersionLen = uc.getByte(index);
                    index += bootloaderVersionLen;
                    int firmwareVersionLen = uc.getByte(index++);
                    index += firmwareVersionLen;
                    int hardwareVersionLen = uc.getByte(index++);
                    index += hardwareVersionLen;
                    int batteryLevelLen = uc.getByte(index++);
                    index += batteryLevelLen;
                    int isChargingLen = uc.getByte(index++);
                    String isCharging = aw.byteArray2Hex(uc.a(index, isChargingLen));
                    if ("00".equals(isCharging)) {
                        isCharging = "false";
                    } else {
                        isCharging = "true";
                    }

                    this.s("get qpos info==");
                    if (!isCharging.equals("true")) {
                        return -1;
                    }

                    aq.ah("<<<<<<<<<<<<disConnect start: is charging " + isCharging);
                    this.a(QPOSService.a.kH);
                    aq.af("begin updating===");
                } else {
                    this.a(QPOSService.a.kH);
                }
            } else {
                this.a(QPOSService.a.kH);
            }

            return 0;
        }
    }

    public int getUpdateProgress() {
        return this.ea == null ? -1 : this.ea.dE();
    }

    public void setDeviceAddress(String address) {
        this.deviceAddress = address;
        this.ea.g(address);
    }

    public byte[] dataInterchange(byte[] data, int timeout) {
        i dc = null;
        j uc = null;
        dc = new i(16, 0, timeout, data);
        this.ea.a(dc);
        uc = this.ea.p(timeout);
        if (uc == null) {
            return new byte[0];
        } else {
            return uc.I() == 16 && uc.J() == 0 ? uc.a(0, uc.length()) : new byte[0];
        }
    }

    public void open() {
        this.ea.Y();
    }

    public void close() {
        this.ea.close();
    }

    public int getAudioFirmwareVersion() {
        return 2;
    }

    public void setAudioControl(boolean flag) {
        if (this.ea != null) {
            this.ea.setAudioControl(flag);
            u.setAudioControl(flag);
        }

    }

    public boolean getAudioControl() {
        return this.ea != null ? this.ea.getAudioControl() : false;
    }

    private int cM() {
        i dc = null;
        j uc = null;
        dc = new i(33, 128, 5, aw.an("0011"));
        this.ea.a(dc);
        uc = this.ea.p(60);
        return uc == null ? -1 : 0;
    }

    public void doTradeByGood(String subTime, int keyIndex, int timeout) {
        if (this.aD()) {
            if (subTime != null && !"".equals(subTime) && subTime.length() == 14) {
                if (keyIndex >= 5) {
                    this.onError(QPOSService.Error.INPUT_INVALID_FORMAT);
                } else {
                    this.ev = subTime + "FF";
                    this.en = timeout;
                    this.eu = "";
                    this.ew = "";
                    this.eq = keyIndex;
                    this.setDoTradeMode(QPOSService.DoTradeMode.COMMON);
                    this.a(QPOSService.a.lh);
                }
            } else {
                this.onError(QPOSService.Error.INPUT_INVALID_FORMAT);
            }
        }
    }

    public void doTrade(String subTime, int keyIndex, int timeout) {
        if (this.aD()) {
            if (subTime != null && !"".equals(subTime) && subTime.length() == 14) {
                if (keyIndex >= 5) {
                    this.onError(QPOSService.Error.INPUT_INVALID_FORMAT);
                } else {
                    this.ev = subTime + "FF";
                    this.en = timeout;
                    this.eu = "";
                    this.ew = "";
                    this.eq = keyIndex;
                    this.setDoTradeMode(QPOSService.DoTradeMode.COMMON);
                    this.a(QPOSService.a.lh);
                }
            } else {
                this.onError(QPOSService.Error.INPUT_INVALID_FORMAT);
            }
        }
    }

    public void setUsbDevice(UsbDevice usbDevice) {
        this.ez = usbDevice;
    }

    public UsbDevice getUsbDevice() {
        return this.ez;
    }

    private String a(String fieldAddr, int paramSize, String onceLen) {
        if (!TextUtils.isEmpty(fieldAddr) && fieldAddr.length() <= 8) {
            StringBuffer buffer = new StringBuffer();
            boolean b = this.cN();
            if (!b) {
                return null;
            } else {
                try {
                    if (!this.f(1)) {
                        return null;
                    }

                    String addr = fieldAddr;

                    for(int i = 0; i < paramSize; ++i) {
                        String fieldPart = this.ig.a(this.ea, addr, onceLen, this.timeout);
                        if (fieldPart == null) {
                            return null;
                        }

                        buffer.append(fieldPart);
                        addr = aw.m(addr, onceLen);
                    }
                } catch (Exception var9) {
                    this.s("MSG_GET_FLASH_PAM");
                    this.onError(QPOSService.Error.UNKNOWN);
                    return null;
                }

                this.s("MSG_GET_FLASH_PAM");
                return buffer.toString();
            }
        } else {
            return null;
        }
    }

    private boolean cN() {
        int i = 0;
        if (this.iw == QPOSService.b.lu) {
            while(this.iw != QPOSService.b.lv) {
                if (this.iw == QPOSService.b.lt) {
                    this.onError(QPOSService.Error.UNKNOWN);
                    return false;
                }

                try {
                    Thread.sleep(10L);
                } catch (InterruptedException var3) {
                    var3.printStackTrace();
                }

                if (i++ == 200) {
                    break;
                }
            }
        }

        if (this.iX == QPOSService.e.lK) {
            this.onError(QPOSService.Error.DEVICE_BUSY);
            return false;
        } else {
            this.iX = QPOSService.e.lJ;
            if (this.az()) {
                this.iX = QPOSService.e.lK;
                this.s("onDoTrade(DDDD)");
                this.h(30020);
                this.i(30020);
                return false;
            } else {
                this.e(true);
                return true;
            }
        }
    }

    static {
        hx = QPOSService.CommunicationMode.UNKNOW;
        hD = null;
        R = null;
        hF = false;
        hI = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action.equals("com.bluetooth")) {
                    if (intent != null) {
                        boolean a = intent.getBooleanExtra("connStatus", true);
                        QPOSService.hF = a;
                    }
                } else if (action.equals("com.disbluetooth")) {
                    QPOSService.hF = false;
                } else if (action.equals("android.bluetooth.device.action.ACL_DISCONNECTED")) {
                    QPOSService.hF = false;
                }

            }
        };
        hJ = new IntentFilter("com.bluetooth");
        er = true;
        it = false;
        iu = QPOSService.BTCONNTYPE.AUTO;
    }

    public interface QPOSServiceListener {
        void onRequestWaitingUser();

        void onQposIdResult(Hashtable<String, String> var1);

        void onQposKsnResult(Hashtable<String, String> var1);

        void onQposIsCardExist(boolean var1);

        void onRequestDeviceScanFinished();

        void onQposInfoResult(Hashtable<String, String> var1);

        void onQposGenerateSessionKeysResult(Hashtable<String, String> var1);

        void onQposDoSetRsaPublicKey(boolean var1);

        void onSearchMifareCardResult(Hashtable<String, String> var1);

        void onBatchReadMifareCardResult(String var1, Hashtable<String, List<String>> var2);

        void onBatchWriteMifareCardResult(String var1, Hashtable<String, List<String>> var2);

        void onDoTradeResult(QPOSService.DoTradeResult var1, Hashtable<String, String> var2);

        void onFinishMifareCardResult(boolean var1);

        void onVerifyMifareCardResult(boolean var1);

        void onReadMifareCardResult(Hashtable<String, String> var1);

        void onWriteMifareCardResult(boolean var1);

        void onOperateMifareCardResult(Hashtable<String, String> var1);

        void getMifareCardVersion(Hashtable<String, String> var1);

        void getMifareReadData(Hashtable<String, String> var1);

        void getMifareFastReadData(Hashtable<String, String> var1);

        void writeMifareULData(String var1);

        void verifyMifareULData(Hashtable<String, String> var1);

        void transferMifareData(String var1);

        void onRequestSetAmount();

        void onRequestSelectEmvApp(ArrayList<String> var1);

        void onRequestIsServerConnected();

        void onRequestFinalConfirm();

        void onRequestOnlineProcess(String var1);

        void onRequestTime();

        void onRequestTransactionResult(QPOSService.TransactionResult var1);

        void onRequestTransactionLog(String var1);

        void onRequestBatchData(String var1);

        void onRequestQposConnected();

        void onRequestQposDisconnected();

        void onRequestNoQposDetected();

        void onRequestNoQposDetectedUnbond();

        void onError(QPOSService.Error var1);

        void onRequestDisplay(QPOSService.Display var1);

        void onReturnReversalData(String var1);

        void onReturnGetPinResult(Hashtable<String, String> var1);

        void onReturnPowerOnIccResult(boolean var1, String var2, String var3, int var4);

        void onReturnPowerOffIccResult(boolean var1);

        void onReturnApduResult(boolean var1, String var2, int var3);

        void onReturnSetSleepTimeResult(boolean var1);

        void onGetCardNoResult(String var1);

        void onRequestSignatureResult(byte[] var1);

        void onRequestCalculateMac(String var1);

        void onRequestUpdateWorkKeyResult(QPOSService.UpdateInformationResult var1);

        void onReturnCustomConfigResult(boolean var1, String var2);

        void onRequestSetPin();

        void onReturnSetMasterKeyResult(boolean var1);

        void onRequestUpdateKey(String var1);

        void onReturnUpdateIPEKResult(boolean var1);

        void onReturnRSAResult(String var1);

        void onReturnUpdateEMVResult(boolean var1);

        void onReturnGetQuickEmvResult(boolean var1);

        void onReturnGetEMVListResult(String var1);

        void onReturnUpdateEMVRIDResult(boolean var1);

        void onDeviceFound(BluetoothDevice var1);

        void onReturnBatchSendAPDUResult(LinkedHashMap<Integer, String> var1);

        void onBluetoothBonding();

        void onBluetoothBonded();

        void onWaitingforData(String var1);

        void onBluetoothBondFailed();

        void onBluetoothBondTimeout();

        void onReturniccCashBack(Hashtable<String, String> var1);

        void onLcdShowCustomDisplay(boolean var1);

        void onUpdatePosFirmwareResult(QPOSService.UpdateInformationResult var1);

        void onBluetoothBoardStateResult(boolean var1);

        void onReturnDownloadRsaPublicKey(HashMap<String, String> var1);

        void onGetPosComm(int var1, String var2, String var3);

        void onUpdateMasterKeyResult(boolean var1, Hashtable<String, String> var2);

        void onPinKey_TDES_Result(String var1);

        void onEmvICCExceptionData(String var1);

        void onSetParamsResult(boolean var1, Hashtable<String, Object> var2);

        void onGetInputAmountResult(boolean var1, String var2);

        void onReturnNFCApduResult(boolean var1, String var2, int var3);

        void onReturnPowerOnNFCResult(boolean var1, String var2, String var3, int var4);

        void onReturnPowerOffNFCResult(boolean var1);

        void onCbcMacResult(String var1);

        void onReadBusinessCardResult(boolean var1, String var2);

        void onWriteBusinessCardResult(boolean var1);

        void onConfirmAmountResult(boolean var1);

        void onSetManagementKey(boolean var1);

        void onSetSleepModeTime(boolean var1);

        void onGetSleepModeTime(String var1);

        void onGetShutDownTime(String var1);

        void onEncryptData(String var1);

        void onAddKey(boolean var1);

        void onSetBuzzerResult(boolean var1);

        void onSetBuzzerTimeResult(boolean var1);

        void onSetBuzzerStatusResult(boolean var1);

        void onGetBuzzerStatusResult(String var1);

        void onQposDoTradeLog(boolean var1);

        void onQposDoGetTradeLogNum(String var1);

        void onQposDoGetTradeLog(String var1, String var2);

        void onRequestDevice();

        void onGetKeyCheckValue(List<String> var1);

        void onGetDevicePubKey(String var1);
    }

    private class c extends BroadcastReceiver {
        private c() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (!TextUtils.isEmpty(action)) {
                aq.ai("QPOSService** ON RECEIVE **" + action);
            }

            if (!action.equals("android.bluetooth.device.action.ACL_DISCONNECTED") && !action.equals("android.bluetooth.device.action.ACL_CONNECTED") && !TextUtils.isEmpty(action)) {
                aq.ai("QPOSServiceanother action: " + action);
            }

        }
    }

    private class d extends BroadcastReceiver {
        private d() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            aq.ah("onReceive action : " + action);
            aq.ah("--- : android.intent.action.HEADSET_PLUG");
            if ("android.intent.action.HEADSET_PLUG".equals(action)) {
                aq.ah("ok ok ok ok " + action);
            }

            if (intent.hasExtra("state")) {
                if (intent.getIntExtra("state", 0) == 0) {
                    aq.ah("state no no: 0");
                    if (!QPOSService.this.ef) {
                        QPOSService.this.onRequestNoQposDetected();
                        return;
                    }

                    QPOSService.this.eC = true;
                    if (QPOSService.this.az()) {
                        QPOSService.this.s("MyBroadcastReceiver");
                        QPOSService.this.e(false);
                    }

                    QPOSService.this.setPosExistFlag(false);
                    QPOSService.this.onRequestQposDisconnected();
                } else if (intent.getIntExtra("state", 0) == 1) {
                    aq.ah("state ok ok: 1");
                    QPOSService.this.setVolume(QPOSService.this.getContext());
                    QPOSService.this.setPosExistFlag(true);
                    QPOSService.this.onRequestQposConnected();
                }
            }

        }
    }

    public static enum AmountType {
        MONEY_TYPE_NONE,
        MONEY_TYPE_RMB,
        MONEY_TYPE_DOLLAR,
        MONEY_TYPE_CUSTOM_STR;

        private AmountType() {
        }
    }

    public static enum PanStatus {
        DEFAULT,
        PLAINTEXT,
        ENCRYPTED;

        private PanStatus() {
        }
    }

    public static enum EncryptType {
        ENCRYPTED,
        PLAINTEXT;

        private EncryptType() {
        }
    }

    public static enum DoTradeMode {
        COMMON,
        CHECK_CARD_NO_IPNUT_PIN,
        IS_DEBIT_OR_CREDIT;

        private DoTradeMode() {
        }
    }

    private static enum e {
        lJ,
        lK,
        lL;

        private e() {
        }
    }

    private static enum a {
        ka,
        kb,
        kc,
        kd,
        ke,
        kf,
        kg,
        kh,
        ki,
        kj,
        kk,
        kl,
        km,
        kn,
        ko,
        kp,
        kq,
        kr,
        ks,
        kt,
        ku,
        kv,
        kw,
        kx,
        ky,
        kz,
        kA,
        kB,
        kC,
        kD,
        kE,
        kF,
        kG,
        kH,
        kI,
        kJ,
        kK,
        kL,
        kM,
        kN,
        kO,
        kP,
        kQ,
        kR,
        kS,
        kT,
        kU,
        kV,
        kW,
        kX,
        kY,
        kZ,
        la,
        lb,
        lc,
        ld,
        le,
        lf,
        lg,
        lh,
        li,
        lj,
        lk,
        ll,
        lm,
        ln;

        private a() {
        }
    }

    private static enum f {
        lR,
        lS,
        lT;

        private f() {
        }
    }

    public static enum LcdModeAlign {
        LCD_MODE_ALIGNLEFT,
        LCD_MODE_ALIGNRIGHT,
        LCD_MODE_ALIGNCENTER;

        private LcdModeAlign() {
        }
    }

    public static enum UpdateInformationResult {
        UPDATE_SUCCESS,
        UPDATE_FAIL,
        UPDATE_PACKET_VEFIRY_ERROR,
        UPDATE_PACKET_LEN_ERROR,
        UPDATE_LOWPOWER,
        UPDATING,
        USB_RECONNECTING;

        private UpdateInformationResult() {
        }
    }

    public static enum TransactionType {
        GOODS,
        SERVICES,
        CASH,
        CASHBACK,
        INQUIRY,
        TRANSFER,
        ADMIN,
        CASHDEPOSIT,
        PAYMENT,
        PBOCLOG,
        SALE,
        PREAUTH,
        ECQ_DESIGNATED_LOAD,
        ECQ_UNDESIGNATED_LOAD,
        ECQ_CASH_LOAD,
        ECQ_CASH_LOAD_VOID,
        ECQ_INQUIRE_LOG,
        REFUND,
        UPDATE_PIN;

        private TransactionType() {
        }
    }

    public static enum TransactionResult {
        APPROVED,
        TERMINATED,
        DECLINED,
        CANCEL,
        CAPK_FAIL,
        NOT_ICC,
        SELECT_APP_FAIL,
        DEVICE_ERROR,
        CARD_NOT_SUPPORTED,
        MISSING_MANDATORY_DATA,
        CARD_BLOCKED_OR_NO_EMV_APPS,
        INVALID_ICC_DATA,
        FALLBACK,
        NFC_TERMINATED,
        CARD_REMOVED,
        TRADE_LOG_FULL;

        private TransactionResult() {
        }
    }

    public static enum Display {
        TRY_ANOTHER_INTERFACE,
        PLEASE_WAIT,
        REMOVE_CARD,
        CLEAR_DISPLAY_MSG,
        PROCESSING,
        PIN_OK,
        TRANSACTION_TERMINATED,
        INPUT_PIN_ING,
        MAG_TO_ICC_TRADE,
        INPUT_OFFLINE_PIN_ONLY,
        CARD_REMOVED,
        INPUT_LAST_OFFLINE_PIN,
        MSR_DATA_READY;

        private Display() {
        }
    }

    public static enum Error {
        TIMEOUT,
        MAC_ERROR,
        CMD_TIMEOUT,
        CMD_NOT_AVAILABLE,
        DEVICE_RESET,
        UNKNOWN,
        DEVICE_BUSY,
        INPUT_OUT_OF_RANGE,
        INPUT_INVALID_FORMAT,
        INPUT_ZERO_VALUES,
        INPUT_INVALID,
        CASHBACK_NOT_SUPPORTED,
        CRC_ERROR,
        COMM_ERROR,
        WR_DATA_ERROR,
        EMV_APP_CFG_ERROR,
        EMV_CAPK_CFG_ERROR,
        APDU_ERROR,
        APP_SELECT_TIMEOUT,
        ICC_ONLINE_TIMEOUT,
        AMOUNT_OUT_OF_LIMIT;

        private Error() {
        }
    }

    public static enum EmvOption {
        START,
        START_WITH_FORCE_ONLINE;

        private EmvOption() {
        }
    }

    public static enum DoTradeResult {
        NONE,
        MCR,
        ICC,
        NOT_ICC,
        BAD_SWIPE,
        NO_RESPONSE,
        NO_UPDATE_WORK_KEY,
        NFC_ONLINE,
        NFC_OFFLINE,
        NFC_DECLINED;

        private DoTradeResult() {
        }
    }

    public static enum CHECKVALUE_KEYTYPE {
        MKSK_TMK,
        MKSK_PIK,
        MKSK_TDK,
        MKSK_MCK,
        DUKPT_TRK_IPEK,
        DUKPT_EMV_IPEK,
        DUKPT_PIN_IPEK,
        DUKPT_TRK_KSN,
        DUKPT_EMV_KSN,
        DUKPT_PIN_KSN,
        TCK,
        MAGK,
        DUKPT_MKSK_ALLTYPE;

        private CHECKVALUE_KEYTYPE() {
        }
    }

    public static enum CommunicationMode {
        AUDIO,
        /** @deprecated */
        @Deprecated
        BLUETOOTH_VER2,
        UART,
        UART_K7,
        /** @deprecated */
        @Deprecated
        BLUETOOTH_2Mode,
        USB,
        /** @deprecated */
        @Deprecated
        BLUETOOTH_4Mode,
        UART_GOOD,
        USB_OTG,
        USB_OTG_CDC_ACM,
        BLUETOOTH,
        BLUETOOTH_BLE,
        UNKNOW;

        private CommunicationMode() {
        }
    }

    public static enum DoTransactionType {
        Clear,
        GetAll,
        GetOne,
        ClearOne,
        ClearLast,
        ClearOneByBatchID,
        GetOneByBatchID;

        private DoTransactionType() {
        }
    }

    public static enum EMVDataOperation {
        Clear,
        Add,
        Delete,
        AttainList,
        update,
        getEmv;

        private EMVDataOperation() {
        }
    }

    public static enum MifareCardErrorCode {
        NFC_MIFARE_OK,
        NFC_MIFARE_PARAM_ERROR,
        NFC_MIFARE_TIMEOUT_ERROR,
        NFC_MIFARE_CRC_ERROR,
        NFC_MIFARE_NACK_ERROR,
        NFC_MIFARE_POLL_ERROR,
        NFC_MIFARE_OPERATION_ERROR,
        NFC_MIFARE_ERROR_END;

        private MifareCardErrorCode() {
        }
    }

    public static enum CardTradeMode {
        ONLY_INSERT_CARD,
        ONLY_SWIPE_CARD,
        TAP_INSERT_CARD,
        TAP_INSERT_CARD_NOTUP,
        SWIPE_TAP_INSERT_CARD,
        UNALLOWED_LOW_TRADE,
        SWIPE_INSERT_CARD,
        SWIPE_TAP_INSERT_CARD_UNALLOWED_LOW_TRADE,
        ONLY_TAP_CARD,
        ONLY_TAP_CARD_QF,
        SWIPE_TAP_INSERT_CARD_NOTUP,
        SWIPE_TAP_INSERT_CARD_DOWN;

        private CardTradeMode() {
        }
    }

    private static enum b {
        lt,
        lu,
        lv;

        private b() {
        }
    }

    public static enum BTCONNTYPE {
        AUTO,
        OLDAPI,
        NEWAPI;

        private BTCONNTYPE() {
        }
    }

    public static enum FORMATID {
        MKSK,
        LP,
        MKSK_PLAIN,
        MOSAMBEE,
        SOFTPAY,
        DUKPT;

        private FORMATID() {
        }
    }

    protected static enum DataFormat {
        OLD;

        private DataFormat() {
        }
    }

    public static enum UsbOTGDriver {
        CDCACM,
        FTDI,
        CH340,
        CP21XX,
        PROLIFIC;

        private UsbOTGDriver() {
        }
    }
}
