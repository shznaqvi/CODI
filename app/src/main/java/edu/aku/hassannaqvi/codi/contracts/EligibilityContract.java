package edu.aku.hassannaqvi.codi.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class EligibilityContract {

    private final String projectName = "CODI";
    //private final String surveyType = "Eligibility";
    private String _ID = "";
    private String _UID = "";
    private String DSSID = "";
    private String studyID = "";
    private String childName = "";
    private String formDate = ""; // Date
    private String user = ""; // Interviewer


    private String istatus = ""; // Interview Status
    private String sEn = "";
    //private String sB = ""; // commented out for Members
    //private String sC = ""; // Commented out for Deceased


    private String gpsLat = "";
    private String gpsLng = "";
    private String gpsDT = "";
    private String gpsAcc = "";
    private String deviceID = "";
    private String devicetagID = "";
    private String synced = "";
    private String synced_date = "";

    public EligibilityContract() {
    }


    public EligibilityContract Sync(JSONObject jsonObject) throws JSONException {
        this._ID = jsonObject.getString(EligibilityTable._ID);
        this._UID = jsonObject.getString(EligibilityTable.COLUMN_UID);
        this.DSSID = jsonObject.getString(EligibilityTable.COLUMN_DSSID);
        this.studyID = jsonObject.getString(EligibilityTable.COLUMN_STUDYID);
        this.childName = jsonObject.getString(EligibilityTable.COLUMN_CHILDNAME);
        this.formDate = jsonObject.getString(EligibilityTable.COLUMN_FORMDATE);
        this.user = jsonObject.getString(EligibilityTable.COLUMN_USER);
        this.istatus = jsonObject.getString(EligibilityTable.COLUMN_ISTATUS);
        this.sEn = jsonObject.getString(EligibilityTable.COLUMN_SEN);
        this.gpsLat = jsonObject.getString(EligibilityTable.COLUMN_GPSLAT);
        this.gpsLng = jsonObject.getString(EligibilityTable.COLUMN_GPSLNG);
        this.gpsDT = jsonObject.getString(EligibilityTable.COLUMN_GPSDT);
        this.gpsAcc = jsonObject.getString(EligibilityTable.COLUMN_GPSACC);
        this.deviceID = jsonObject.getString(EligibilityTable.COLUMN_DEVICEID);
        this.devicetagID = jsonObject.getString(EligibilityTable.COLUMN_DEVICETAGID);

        return this;

    }

    public EligibilityContract Hydrate(Cursor cursor) {
        this._ID = cursor.getString(cursor.getColumnIndex(EligibilityTable._ID));
        this._UID = cursor.getString(cursor.getColumnIndex(EligibilityTable.COLUMN_UID));
        this.DSSID = cursor.getString(cursor.getColumnIndex(EligibilityTable.COLUMN_DSSID));
        this.studyID = cursor.getString(cursor.getColumnIndex(EligibilityTable.COLUMN_STUDYID));
        this.childName = cursor.getString(cursor.getColumnIndex(EligibilityTable.COLUMN_CHILDNAME));
        this.formDate = cursor.getString(cursor.getColumnIndex(EligibilityTable.COLUMN_FORMDATE));
        this.user = cursor.getString(cursor.getColumnIndex(EligibilityTable.COLUMN_USER));
        this.istatus = cursor.getString(cursor.getColumnIndex(EligibilityTable.COLUMN_ISTATUS));
        this.sEn = cursor.getString(cursor.getColumnIndex(EligibilityTable.COLUMN_SEN));
        this.gpsLat = cursor.getString(cursor.getColumnIndex(EligibilityTable.COLUMN_GPSLAT));
        this.gpsLng = cursor.getString(cursor.getColumnIndex(EligibilityTable.COLUMN_GPSLNG));
        this.gpsDT = cursor.getString(cursor.getColumnIndex(EligibilityTable.COLUMN_GPSDT));
        this.gpsAcc = cursor.getString(cursor.getColumnIndex(EligibilityTable.COLUMN_GPSACC));
        this.deviceID = cursor.getString(cursor.getColumnIndex(EligibilityTable.COLUMN_DEVICEID));
        this.devicetagID = cursor.getString(cursor.getColumnIndex(EligibilityTable.COLUMN_DEVICETAGID));

        // TODO:

        return this;

    }


    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();

        json.put(EligibilityTable.COLUMN_PROJECTNAME, this.projectName);
        json.put(EligibilityTable._ID, this._ID == null ? JSONObject.NULL : this._ID);
        json.put(EligibilityTable.COLUMN_UID, this._UID == null ? JSONObject.NULL : this._UID);
        json.put(EligibilityTable.COLUMN_DSSID, this.DSSID == null ? JSONObject.NULL : this.DSSID);
        json.put(EligibilityTable.COLUMN_STUDYID, this.studyID == null ? JSONObject.NULL : this.studyID);
        json.put(EligibilityTable.COLUMN_CHILDNAME, this.childName == null ? JSONObject.NULL : this.childName);
        json.put(EligibilityTable.COLUMN_FORMDATE, this.formDate == null ? JSONObject.NULL : this.formDate);
        json.put(EligibilityTable.COLUMN_USER, this.user == null ? JSONObject.NULL : this.user);
        json.put(EligibilityTable.COLUMN_ISTATUS, this.istatus == null ? JSONObject.NULL : this.istatus);
        json.put(EligibilityTable.COLUMN_SEN, this.sEn == null ? JSONObject.NULL : this.sEn);
        json.put(EligibilityTable.COLUMN_GPSLAT, this.gpsLat == null ? JSONObject.NULL : this.gpsLat);
        json.put(EligibilityTable.COLUMN_GPSLNG, this.gpsLng == null ? JSONObject.NULL : this.gpsLng);
        json.put(EligibilityTable.COLUMN_GPSDT, this.gpsDT == null ? JSONObject.NULL : this.gpsDT);
        json.put(EligibilityTable.COLUMN_GPSACC, this.gpsAcc == null ? JSONObject.NULL : this.gpsAcc);
        json.put(EligibilityTable.COLUMN_DEVICEID, this.deviceID == null ? JSONObject.NULL : this.deviceID);
        json.put(EligibilityTable.COLUMN_DEVICETAGID, this.devicetagID == null ? JSONObject.NULL : this.devicetagID);


        return json;
    }

    public String getProjectName() {
        return projectName;
    }

    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public String get_UID() {
        return _UID;
    }

    public void set_UID(String _UID) {
        this._UID = _UID;
    }

    public String getDSSID() {
        return DSSID;
    }

    public void setDSSID(String DSSID) {
        this.DSSID = DSSID;
    }

    public String getStudyID() {
        return studyID;
    }

    public void setStudyID(String studyID) {
        this.studyID = studyID;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getFormDate() {
        return formDate;
    }

    public void setFormDate(String formDate) {
        this.formDate = formDate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getIstatus() {
        return istatus;
    }

    public void setIstatus(String istatus) {
        this.istatus = istatus;
    }

    public String getsEn() {
        return sEn;
    }

    public void setsEn(String sEn) {
        this.sEn = sEn;
    }

    public String getGpsLat() {
        return gpsLat;
    }

    public void setGpsLat(String gpsLat) {
        this.gpsLat = gpsLat;
    }

    public String getGpsLng() {
        return gpsLng;
    }

    public void setGpsLng(String gpsLng) {
        this.gpsLng = gpsLng;
    }

    public String getGpsDT() {
        return gpsDT;
    }

    public void setGpsDT(String gpsDT) {
        this.gpsDT = gpsDT;
    }

    public String getGpsAcc() {
        return gpsAcc;
    }

    public void setGpsAcc(String gpsAcc) {
        this.gpsAcc = gpsAcc;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getDevicetagID() {
        return devicetagID;
    }

    public void setDevicetagID(String devicetagID) {
        this.devicetagID = devicetagID;
    }

    public String getSynced() {
        return synced;
    }

    public void setSynced(String synced) {
        this.synced = synced;
    }

    public String getSynced_date() {
        return synced_date;
    }

    public void setSynced_date(String synced_date) {
        this.synced_date = synced_date;
    }

    public static abstract class EligibilityTable implements BaseColumns {

        public static final String TABLE_NAME = "eligibility";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";


        public static final String COLUMN_PROJECTNAME = "projectname";
        public static final String _ID = "_id";
        public static final String COLUMN_UID = "_uid";
        public static final String COLUMN_DSSID = "dssid";
        public static final String COLUMN_STUDYID = "studyid";
        public static final String COLUMN_CHILDNAME = "childname";
        public static final String COLUMN_FORMDATE = "formdate";
        public static final String COLUMN_USER = "user";
        public static final String COLUMN_ISTATUS = "istatus";
        public static final String COLUMN_SEN = "sen";
        public static final String COLUMN_GPSLAT = "gpslat";
        public static final String COLUMN_GPSLNG = "gpslng";
        public static final String COLUMN_GPSDT = "gpsdt";
        public static final String COLUMN_GPSACC = "gpsacc";
        public static final String COLUMN_DEVICEID = "deviceid";
        public static final String COLUMN_DEVICETAGID = "devicetagid";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCED_DATE = "synced_date";


        public static String _URL = "eligibility.php";
    }
}
