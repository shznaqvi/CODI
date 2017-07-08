package edu.aku.hassannaqvi.codi.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class VaccinesContract {

    private final String projectName = "CODI";
    //private final String surveyType = "Enrollment";
    private String _ID = "";
    private String _UID = "";
    private String DSSID = "";
    private String studyID = "";
    private String childName = "";
    private String formDate = ""; // Date
    private String formType = ""; // [EN, V1, V2, V3, V4, V5]
    private String user = ""; // Interviewer
    private String nextApp = ""; // Next Appointment Date Time


    private String istatus = ""; // Interview Status
    private String sEnInfo = "";
    private String sEnBloodSample = "";
    private String sEnRandomization = "";
    private String sEnVaccine = "";


    private String gpsLat = "";
    private String gpsLng = "";
    private String gpsDT = "";
    private String gpsAcc = "";
    private String deviceID = "";
    private String devicetagID = "";
    private String synced = "";
    private String synced_date = "";

    public VaccinesContract() {
    }


    public VaccinesContract Sync(JSONObject jsonObject) throws JSONException {
        this._ID = jsonObject.getString(EnrollmentTable._ID);
        this._UID = jsonObject.getString(EnrollmentTable.COLUMN__UID);
        this.DSSID = jsonObject.getString(EnrollmentTable.COLUMN_DSSID);
        this.studyID = jsonObject.getString(EnrollmentTable.COLUMN_STUDYID);
        this.childName = jsonObject.getString(EnrollmentTable.COLUMN_CHILDNAME);
        this.formDate = jsonObject.getString(EnrollmentTable.COLUMN_FORMDATE);
        this.formType = jsonObject.getString(EnrollmentTable.COLUMN_FORMTYPE);
        this.user = jsonObject.getString(EnrollmentTable.COLUMN_USER);
        this.istatus = jsonObject.getString(EnrollmentTable.COLUMN_ISTATUS);
        this.nextApp = jsonObject.getString(EnrollmentTable.COLUMN_NEXTAPP);
        this.sEnInfo = jsonObject.getString(EnrollmentTable.COLUMN_SENINFO);
        this.sEnBloodSample = jsonObject.getString(EnrollmentTable.COLUMN_SENBLOODSAMPLE);
        this.sEnRandomization = jsonObject.getString(EnrollmentTable.COLUMN_SENRANDOMIZATION);
        this.sEnVaccine = jsonObject.getString(EnrollmentTable.COLUMN_SENVACCINE);
        this.gpsLat = jsonObject.getString(EnrollmentTable.COLUMN_GPSLAT);
        this.gpsLng = jsonObject.getString(EnrollmentTable.COLUMN_GPSLNG);
        this.gpsDT = jsonObject.getString(EnrollmentTable.COLUMN_GPSDT);
        this.gpsAcc = jsonObject.getString(EnrollmentTable.COLUMN_GPSACC);
        this.deviceID = jsonObject.getString(EnrollmentTable.COLUMN_DEVICEID);
        this.devicetagID = jsonObject.getString(EnrollmentTable.COLUMN_DEVICETAGID);


        return this;

    }

    public VaccinesContract Hydrate(Cursor cursor) {
        this._ID = cursor.getString(cursor.getColumnIndex(EnrollmentTable._ID));
        this._UID = cursor.getString(cursor.getColumnIndex(EnrollmentTable.COLUMN__UID));
        this.DSSID = cursor.getString(cursor.getColumnIndex(EnrollmentTable.COLUMN_DSSID));
        this.studyID = cursor.getString(cursor.getColumnIndex(EnrollmentTable.COLUMN_STUDYID));
        this.childName = cursor.getString(cursor.getColumnIndex(EnrollmentTable.COLUMN_CHILDNAME));
        this.formDate = cursor.getString(cursor.getColumnIndex(EnrollmentTable.COLUMN_FORMDATE));
        this.formType = cursor.getString(cursor.getColumnIndex(EnrollmentTable.COLUMN_FORMTYPE));
        this.user = cursor.getString(cursor.getColumnIndex(EnrollmentTable.COLUMN_USER));
        this.istatus = cursor.getString(cursor.getColumnIndex(EnrollmentTable.COLUMN_ISTATUS));
        this.nextApp = cursor.getString(cursor.getColumnIndex(EnrollmentTable.COLUMN_NEXTAPP));
        this.sEnInfo = cursor.getString(cursor.getColumnIndex(EnrollmentTable.COLUMN_SENINFO));
        this.sEnBloodSample = cursor.getString(cursor.getColumnIndex(EnrollmentTable.COLUMN_SENBLOODSAMPLE));
        this.sEnRandomization = cursor.getString(cursor.getColumnIndex(EnrollmentTable.COLUMN_SENRANDOMIZATION));
        this.sEnVaccine = cursor.getString(cursor.getColumnIndex(EnrollmentTable.COLUMN_SENVACCINE));
        this.gpsLat = cursor.getString(cursor.getColumnIndex(EnrollmentTable.COLUMN_GPSLAT));
        this.gpsLng = cursor.getString(cursor.getColumnIndex(EnrollmentTable.COLUMN_GPSLNG));
        this.gpsDT = cursor.getString(cursor.getColumnIndex(EnrollmentTable.COLUMN_GPSDT));
        this.gpsAcc = cursor.getString(cursor.getColumnIndex(EnrollmentTable.COLUMN_GPSACC));
        this.deviceID = cursor.getString(cursor.getColumnIndex(EnrollmentTable.COLUMN_DEVICEID));
        this.devicetagID = cursor.getString(cursor.getColumnIndex(EnrollmentTable.COLUMN_DEVICETAGID));


        return this;

    }


    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();

        json.put(EnrollmentTable.COLUMN_PROJECTNAME, this.projectName);
        json.put(EnrollmentTable._ID, this._ID == null ? JSONObject.NULL : this._ID);
        json.put(EnrollmentTable.COLUMN__UID, this._UID == null ? JSONObject.NULL : this._UID);
        json.put(EnrollmentTable.COLUMN_DSSID, this.DSSID == null ? JSONObject.NULL : this.DSSID);
        json.put(EnrollmentTable.COLUMN_STUDYID, this.studyID == null ? JSONObject.NULL : this.studyID);
        json.put(EnrollmentTable.COLUMN_CHILDNAME, this.childName == null ? JSONObject.NULL : this.childName);
        json.put(EnrollmentTable.COLUMN_FORMDATE, this.formDate == null ? JSONObject.NULL : this.formDate);
        json.put(EnrollmentTable.COLUMN_FORMTYPE, this.formType == null ? JSONObject.NULL : this.formType);
        json.put(EnrollmentTable.COLUMN_USER, this.user == null ? JSONObject.NULL : this.user);
        json.put(EnrollmentTable.COLUMN_ISTATUS, this.istatus == null ? JSONObject.NULL : this.istatus);
        json.put(EnrollmentTable.COLUMN_NEXTAPP, this.nextApp == null ? JSONObject.NULL : this.nextApp);
        json.put(EnrollmentTable.COLUMN_SENINFO, this.sEnInfo == null ? JSONObject.NULL : this.sEnInfo);
        json.put(EnrollmentTable.COLUMN_SENBLOODSAMPLE, this.sEnBloodSample == null ? JSONObject.NULL : this.sEnBloodSample);
        json.put(EnrollmentTable.COLUMN_SENRANDOMIZATION, this.sEnRandomization == null ? JSONObject.NULL : this.sEnRandomization);
        json.put(EnrollmentTable.COLUMN_SENVACCINE, this.sEnVaccine == null ? JSONObject.NULL : this.sEnVaccine);
        json.put(EnrollmentTable.COLUMN_GPSLAT, this.gpsLat == null ? JSONObject.NULL : this.gpsLat);
        json.put(EnrollmentTable.COLUMN_GPSLNG, this.gpsLng == null ? JSONObject.NULL : this.gpsLng);
        json.put(EnrollmentTable.COLUMN_GPSDT, this.gpsDT == null ? JSONObject.NULL : this.gpsDT);
        json.put(EnrollmentTable.COLUMN_GPSACC, this.gpsAcc == null ? JSONObject.NULL : this.gpsAcc);
        json.put(EnrollmentTable.COLUMN_DEVICEID, this.deviceID == null ? JSONObject.NULL : this.deviceID);
        json.put(EnrollmentTable.COLUMN_DEVICETAGID, this.devicetagID == null ? JSONObject.NULL : this.devicetagID);


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

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    public String getIstatus() {
        return istatus;
    }

    public void setIstatus(String istatus) {
        this.istatus = istatus;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getNextApp() {
        return nextApp;
    }

    public void setNextApp(String nextApp) {
        this.nextApp = nextApp;
    }

    public String getIStatus() {
        return istatus;
    }

    public String getsEnInfo() {
        return sEnInfo;
    }

    public void setsEnInfo(String sEnInfo) {
        this.sEnInfo = sEnInfo;
    }

    public String getsEnBloodSample() {
        return sEnBloodSample;
    }

    public void setsEnBloodSample(String sEnBloodSample) {
        this.sEnBloodSample = sEnBloodSample;
    }

    public String getsEnRandomization() {
        return sEnRandomization;
    }

    public void setsEnRandomization(String sEnRandomization) {
        this.sEnRandomization = sEnRandomization;
    }

    public String getsEnVaccine() {
        return sEnVaccine;
    }

    public void setsEnVaccine(String sEnVaccine) {
        this.sEnVaccine = sEnVaccine;
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

    public static abstract class EnrollmentTable implements BaseColumns {

        public static final String TABLE_NAME = "enrollment";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";


        public static final String COLUMN_PROJECTNAME = "projectname";
        public static final String _ID = "_id";
        public static final String COLUMN__UID = "_uid";
        public static final String COLUMN_DSSID = "dssid";
        public static final String COLUMN_STUDYID = "studyid";
        public static final String COLUMN_CHILDNAME = "childname";
        public static final String COLUMN_FORMDATE = "formdate";
        public static final String COLUMN_FORMTYPE = "formtype";
        public static final String COLUMN_USER = "user";
        public static final String COLUMN_NEXTAPP = "nextapp";
        public static final String COLUMN_ISTATUS = "istatus";
        public static final String COLUMN_SENINFO = "seninfo";
        public static final String COLUMN_SENBLOODSAMPLE = "senbloodsample";
        public static final String COLUMN_SENRANDOMIZATION = "senrandomization";
        public static final String COLUMN_SENVACCINE = "senvaccine";
        public static final String COLUMN_GPSLAT = "gpslat";
        public static final String COLUMN_GPSLNG = "gpslng";
        public static final String COLUMN_GPSDT = "gpsdt";
        public static final String COLUMN_GPSACC = "gpsacc";
        public static final String COLUMN_DEVICEID = "deviceid";
        public static final String COLUMN_DEVICETAGID = "devicetagid";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCED_DATE = "synced_date";


        public static String _URL = "enrollment.php";
    }
}
