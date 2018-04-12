/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.microedition.midlet.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import javax.microedition.location.*;
import javax.microedition.location.Coordinates;
import javax.microedition.location.Criteria;
import javax.microedition.location.Location;
import javax.microedition.location.LocationException;
import javax.microedition.location.LocationProvider;
import javax.microedition.midlet.MIDlet;
import java.util.*;

/**
 * @author riad
 */
public class LocSearch extends MIDlet implements CommandListener{
    

    private Form formReceiver = new Form("SMS Receiver");
    private Display display;
    public void startApp() {
        display = Display.getDisplay(this);
        

        formReceiver.append("\n\n\n\n");
        formReceiver.append("cell id : "+getCellId()+"\n");
        formReceiver.append("area code : "+getLAC()+"\n");
        formReceiver.append("subscriber id : "+getIMSI()+"\n");
        formReceiver.append("country code : "+getMCC()+"\n");
        formReceiver.append("network code : "+getMNC()+"\n");
        formReceiver.append("equipment-identity : "+getIMEI()+"\n");
        
        display.setCurrent(formReceiver);
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
    public void commandAction(Command c, Displayable d)
    {


    }
    
    
    
    public static String getCellId(){
    String out = "";
    try{
     
    out = System.getProperty("Cell-ID");
    if(out== null || out.equals("null") || out.equals(""))
    out = System.getProperty("CellID");
    if(out== null ||out.equals("null")|| out.equals(""))
    System.getProperty("phone.cid");
    if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("com.nokia.mid.cellid");
    if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("com.sonyericsson.net.cellid");
    if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("phone.cid");//System.getProperty("CellID");
    if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("com.samsung.cellid");
    if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("com.siemens.cellid");
    if(out== null ||out.equals("null")|| out.equals(""))
    if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("cid");
     
    }catch(Exception e){
    return out==null?"":out;
    }
     
    return out==null?"":out;
    }
     
    
    public static String getLAC(){
    String out = "";
    try{
     
    out = System.getProperty("phone.lac");
     
    if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("com.nokia.mid.lac");
    if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("com.sonyericsson.net.lac");
    if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("LocAreaCode");
    if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("com.samsung.cellid");
    if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("com.siemens.cellid");
    if(out== null ||out.equals("null")|| out.equals(""))
    if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("cid");
     
    }catch(Exception e){
    return out==null?"":out;
    }
     
    return out==null?"":out;
    }
     
    /**
    * Example IMSI (O2 UK): 234103530089555
    String mcc = imsi.substring(0,3); // 234 (UK)
    String mnc = imsi.substring(3,5); // 10 (O2)
    * @return
    */
    public static String getIMSI(){
    String out = "";
    try{
     
    out = System.getProperty("IMSI");
    if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("phone.imsi") ;
    if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("com.nokia.mid.mobinfo.IMSI");
    if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("com.nokia.mid.imsi");
    /* if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("com.sonyericsson.imsi");*/
    if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("IMSI");
    /* if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("com.samsung.imei");*/
    /* if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("com.siemens.imei");*/
    if(out== null ||out.equals("null")|| out.equals(""))
    if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("imsi");
     
    }catch(Exception e){
    return out==null?"":out;
    }
     
    return out==null?"":out;
    }
     
    /**
    *
    * For moto, Example IMSI (O2 UK): 234103530089555
    String mcc = imsi.substring(0,3); // 234 (UK)
    * @return
    */
    public static String getMCC(){
    String out = "";
    try{
     
    if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("phone.mcc") ;
    if(out== null ||out.equals("null")|| out.equals(""))
    //out = System.getProperty("com.nokia.mid.mobinfo.IMSI");
    if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("com.sonyericsson.net.mcc");
    if(out== null ||out.equals("null")|| out.equals("")){
    out = getIMSI().equals("")?"": getIMSI().substring(0,3);
    }
    /* if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("com.samsung.imei");*/
    /* if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("com.siemens.imei");*/
    if(out== null ||out.equals("null")|| out.equals(""))//getMNC()
    if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("mcc");
     
    }catch(Exception e){
    return out==null?"":out;
    }
     
    return out==null?"":out;
    }
     
    /**
    *
    * For moto, Example IMSI (O2 UK): 234103530089555
    String mnc = imsi.substring(3,5); // 10 (O2)
    * @return
    */
    public static String getMNC(){
    String out = "";
    try{
     
    if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("phone.mnc") ;
    if(out== null ||out.equals("null")|| out.equals(""))
    out = getIMSI().equals("")?"": getIMSI().substring(3,5);
    if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("com.sonyericsson.net.mnc");
    if(out== null ||out.equals("null")|| out.equals("")){
    out = getIMSI().equals("")?"": getIMSI().substring(3,5);
    }
    /* if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("com.samsung.imei");*/
    /* if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("com.siemens.imei");*/
    if(out== null ||out.equals("null")|| out.equals(""))//getMNC()
    if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("mnc");
     
    }catch(Exception e){
    return out==null?"":out;
    }
     
    return out==null?"":out;
    }
     
    
    public static String getIMEI(){
    String out = "";
    try{
     
    out = System.getProperty("com.imei");
    if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("phone.imei");
    if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("com.nokia.IMEI");
    if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("com.nokia.mid.imei");
    if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("com.nokia.mid.imei");
    if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("com.sonyericsson.imei");
    if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("IMEI");
    if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("com.motorola.IMEI");
    if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("com.samsung.imei");
    if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("com.siemens.imei");
    if(out== null ||out.equals("null")|| out.equals(""))
    out = System.getProperty("imei");
     
    }catch(Exception e){
    return out==null?"":out;
    }
     
    return out==null?"":out;
    }
}
