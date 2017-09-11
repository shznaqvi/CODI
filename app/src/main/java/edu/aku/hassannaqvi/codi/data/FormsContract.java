package edu.aku.hassannaqvi.codi.data;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class FormsContract {

    // The authority, which is how your code knows which Content Provider to access
    public static final String AUTHORITY = "edu.aku.hassannaqvi.codi";

    // The base content URI = "content://" + <authority>
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    // Define the possible paths for accessing data in this contract
    // This is the path for the "tasks" directory
    public static final String PATH_FORMS = "forms";

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
    private String sEl = "";
    private String sInfo = "";
    private String sCHBF = "";
    private String sBloodSample = "";
    private String sRandomization = "";
    private String sVaccine = "";


    private String gpsLat = "";
    private String gpsLng = "";
    private String gpsDT = "";
    private String gpsAcc = "";
    private String deviceID = "";
    private String devicetagID = "";
    private String synced = "";
    private String synced_date = "";


    public FormsContract() {
    }


    public FormsContract Sync(JSONObject jsonObject) throws JSONException {
        this._ID = jsonObject.getString(FormsTable._ID);
        this._UID = jsonObject.getString(FormsTable.COLUMN__UID);
        this.DSSID = jsonObject.getString(FormsTable.COLUMN_DSSID);
        this.studyID = jsonObject.getString(FormsTable.COLUMN_STUDYID);
        this.childName = jsonObject.getString(FormsTable.COLUMN_CHILDNAME);
        this.formDate = jsonObject.getString(FormsTable.COLUMN_FORMDATE);
        this.formType = jsonObject.getString(FormsTable.COLUMN_FORMTYPE);
        this.user = jsonObject.getString(FormsTable.COLUMN_USER);
        this.istatus = jsonObject.getString(FormsTable.COLUMN_ISTATUS);
        this.nextApp = jsonObject.getString(FormsTable.COLUMN_NEXTAPP);
        this.sInfo = jsonObject.getString(FormsTable.COLUMN_SINFO);
        this.sEl = jsonObject.getString(FormsTable.COLUMN_SELIGIBLE);
        this.sCHBF = jsonObject.getString(FormsTable.COLUMN_SCHBF);
        this.sBloodSample = jsonObject.getString(FormsTable.COLUMN_SBLOODSAMPLE);
        this.sRandomization = jsonObject.getString(FormsTable.COLUMN_SRANDOMIZATION);
        this.sVaccine = jsonObject.getString(FormsTable.COLUMN_SVACCINE);
        this.gpsLat = jsonObject.getString(FormsTable.COLUMN_GPSLAT);
        this.gpsLng = jsonObject.getString(FormsTable.COLUMN_GPSLNG);
        this.gpsDT = jsonObject.getString(FormsTable.COLUMN_GPSDT);
        this.gpsAcc = jsonObject.getString(FormsTable.COLUMN_GPSACC);
        this.deviceID = jsonObject.getString(FormsTable.COLUMN_DEVICEID);
        this.devicetagID = jsonObject.getString(FormsTable.COLUMN_DEVICETAGID);


        return this;

    }

    public FormsContract Hydrate(Cursor cursor) {
        this._ID = cursor.getString(cursor.getColumnIndex(FormsTable._ID));
        this._UID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN__UID));
        this.DSSID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_DSSID));
        this.studyID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_STUDYID));
        this.childName = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_CHILDNAME));
        this.formDate = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_FORMDATE));
        this.formType = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_FORMTYPE));
        this.user = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_USER));
        this.istatus = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ISTATUS));
        this.nextApp = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_NEXTAPP));
        this.sInfo = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SINFO));
        this.sEl = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SELIGIBLE));
        this.sCHBF = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SCHBF));
        this.sBloodSample = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SBLOODSAMPLE));
        this.sRandomization = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SRANDOMIZATION));
        this.sVaccine = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SVACCINE));
        this.gpsLat = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSLAT));
        this.gpsLng = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSLNG));
        this.gpsDT = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSDT));
        this.gpsAcc = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSACC));
        this.deviceID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_DEVICEID));
        this.devicetagID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_DEVICETAGID));


        return this;

    }

    public String getsEl() {

        return sEl;
    }

    public void setsEl(String sEl) {
        this.sEl = sEl;
    }

    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();

        json.put(FormsTable.COLUMN_PROJECTNAME, this.projectName);
        json.put(FormsTable._ID, this._ID == null ? JSONObject.NULL : this._ID);
        json.put(FormsTable.COLUMN__UID, this._UID == null ? JSONObject.NULL : this._UID);
        json.put(FormsTable.COLUMN_DSSID, this.DSSID == null ? JSONObject.NULL : this.DSSID);
        json.put(FormsTable.COLUMN_STUDYID, this.studyID == null ? JSONObject.NULL : this.studyID);
        json.put(FormsTable.COLUMN_CHILDNAME, this.childName == null ? JSONObject.NULL : this.childName);
        json.put(FormsTable.COLUMN_FORMDATE, this.formDate == null ? JSONObject.NULL : this.formDate);
        json.put(FormsTable.COLUMN_FORMTYPE, this.formType == null ? JSONObject.NULL : this.formType);
        json.put(FormsTable.COLUMN_USER, this.user == null ? JSONObject.NULL : this.user);
        json.put(FormsTable.COLUMN_ISTATUS, this.istatus == null ? JSONObject.NULL : this.istatus);
        json.put(FormsTable.COLUMN_NEXTAPP, this.nextApp == null ? JSONObject.NULL : this.nextApp);
        if (!this.sEl.equals("")) {
            json.put(FormsTable.COLUMN_SELIGIBLE, new JSONObject(this.sEl));
        }
        if (!this.sInfo.equals("")) {
            json.put(FormsTable.COLUMN_SINFO, new JSONObject(this.sInfo));
        }
        if (!this.sCHBF.equals("")) {
            json.put(FormsTable.COLUMN_SCHBF, new JSONObject(this.sCHBF));
        }
        if (!this.sBloodSample.equals("")) {
            json.put(FormsTable.COLUMN_SBLOODSAMPLE, new JSONObject(this.sBloodSample));
        }
        if (!this.sRandomization.equals("")) {
            json.put(FormsTable.COLUMN_SRANDOMIZATION, new JSONObject(this.sRandomization));
        }
        if (!this.sVaccine.equals("")) {
            json.put(FormsTable.COLUMN_SVACCINE, new JSONObject(this.sVaccine));
        }
        json.put(FormsTable.COLUMN_GPSLAT, this.gpsLat == null ? JSONObject.NULL : this.gpsLat);
        json.put(FormsTable.COLUMN_GPSLNG, this.gpsLng == null ? JSONObject.NULL : this.gpsLng);
        json.put(FormsTable.COLUMN_GPSDT, this.gpsDT == null ? JSONObject.NULL : this.gpsDT);
        json.put(FormsTable.COLUMN_GPSACC, this.gpsAcc == null ? JSONObject.NULL : this.gpsAcc);
        json.put(FormsTable.COLUMN_DEVICEID, this.deviceID == null ? JSONObject.NULL : this.deviceID);
        json.put(FormsTable.COLUMN_DEVICETAGID, this.devicetagID == null ? JSONObject.NULL : this.devicetagID);


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

    public String getsInfo() {
        return sInfo;
    }

    public void setsInfo(String sInfo) {
        this.sInfo = sInfo;
    }

    public String getsCHBF() {
        return sCHBF;
    }

    public void setsCHBF(String sCHBF) {
        this.sCHBF = sCHBF;
    }

    public String getsBloodSample() {
        return sBloodSample;
    }

    public void setsBloodSample(String sBloodSample) {
        this.sBloodSample = sBloodSample;
    }

    public String getsRandomization() {
        return sRandomization;
    }

    public void setsRandomization(String sRandomization) {
        this.sRandomization = sRandomization;
    }

    public String getsVaccine() {
        return sVaccine;
    }

    public void setsVaccine(String sVaccine) {
        this.sVaccine = sVaccine;
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


    public static abstract class FormsTable implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_FORMS).build();

        public static final String TABLE_NAME = "forms";
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
        public static final String COLUMN_SELIGIBLE = "seligible";
        public static final String COLUMN_SINFO = "sinfo";
        public static final String COLUMN_SCHBF = "schbf";
        public static final String COLUMN_SBLOODSAMPLE = "sbloodsample";
        public static final String COLUMN_SRANDOMIZATION = "srandomization";
        public static final String COLUMN_SVACCINE = "svaccine";
        public static final String COLUMN_GPSLAT = "gpslat";
        public static final String COLUMN_GPSLNG = "gpslng";
        public static final String COLUMN_GPSDT = "gpsdt";
        public static final String COLUMN_GPSACC = "gpsacc";
        public static final String COLUMN_DEVICEID = "deviceid";
        public static final String COLUMN_DEVICETAGID = "devicetagid";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCED_DATE = "synced_date";


        public static String _URL = "forms.php";
    }
}
