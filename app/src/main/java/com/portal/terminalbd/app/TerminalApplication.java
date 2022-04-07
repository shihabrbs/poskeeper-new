package com.portal.terminalbd.app;

import android.app.Application;

import com.rt.printerlibrary.connect.PrinterInterface;
import com.rt.printerlibrary.printer.RTPrinter;
import com.portal.terminalbd.utils.BaseEnum;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class TerminalApplication extends Application {

    public static TerminalApplication instance = null;
    private RTPrinter rtPrinter;

    @BaseEnum.CmdType
    private int currentCmdType = BaseEnum.CMD_ESC;//默认为针打

    @BaseEnum.ConnectType
    private int currentConnectType = BaseEnum.CON_USB;//默认未连接

    private ArrayList<PrinterInterface> printerInterfaceArrayList = new ArrayList<>();

    public static final String SP_NAME_SETTING = "setting";

    public static String labelSizeStr = "80*40", labelWidth="80", labelHeight="40", labelSpeed="2", labelType="CPCL", labelOffset="0";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration
                .Builder().name("terminalbd.realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);

    }

    public static TerminalApplication getInstance() {
        return instance;
    }


    public RTPrinter getRtPrinter() {
        return rtPrinter;
    }

    public void setRtPrinter(RTPrinter rtPrinter) {
        this.rtPrinter = rtPrinter;
    }

    @BaseEnum.CmdType
    public int getCurrentCmdType() {
        return currentCmdType;
    }

    public void setCurrentCmdType(@BaseEnum.CmdType int currentCmdType) {
        this.currentCmdType = currentCmdType;
    }

    @BaseEnum.ConnectType
    public int getCurrentConnectType() {
        return currentConnectType;
    }

    public void setCurrentConnectType(@BaseEnum.ConnectType int currentConnectType) {
        this.currentConnectType = currentConnectType;
    }

    public ArrayList<PrinterInterface> getPrinterInterfaceArrayList() {
        return printerInterfaceArrayList;
    }

    public void setPrinterInterfaceArrayList(ArrayList<PrinterInterface> printerInterfaceArrayList) {
        this.printerInterfaceArrayList = printerInterfaceArrayList;
    }
}
