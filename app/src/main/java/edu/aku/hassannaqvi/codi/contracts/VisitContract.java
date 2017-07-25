package edu.aku.hassannaqvi.codi.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class VisitContract {

    private static final String TAG = "FollowUps_CONTRACT";
    Long _ID;
    String CHILDNAME;
    String STUDYID;
    String EXPECTEDDT;
    String VISITNUM;
    String MOTHERNAME;


    public VisitContract() {
        // Default Constructor
    }

    public VisitContract(VisitContract vc) {
        this.STUDYID = vc.getSTUDYID();
        this.CHILDNAME = vc.getCHILDNAME();
        this.MOTHERNAME = vc.getMOTHERNAME();
        this.VISITNUM = vc.getVISITNUM();
        this.EXPECTEDDT = vc.getEXPECTEDDT();

    }

    public VisitContract Sync(JSONObject jsonObject) throws JSONException {
        this.CHILDNAME = jsonObject.getString(singleFollowUps.COLUMN_CHILDNAME);
        this.STUDYID = jsonObject.getString(singleFollowUps.COLUMN_STUDYID);
        this.EXPECTEDDT = jsonObject.getString(singleFollowUps.COLUMN_EXPECTEDDT);
        this.VISITNUM = jsonObject.getString(singleFollowUps.COLUMN_VISITNUM);
        this.MOTHERNAME = jsonObject.getString(singleFollowUps.COLUMN_MOTHERNAME);


        return this;

    }

    public VisitContract Hydrate(Cursor cursor) {

        this.CHILDNAME = cursor.getString(cursor.getColumnIndex(singleFollowUps.COLUMN_CHILDNAME));
        this.STUDYID = cursor.getString(cursor.getColumnIndex(singleFollowUps.COLUMN_STUDYID));
        this.EXPECTEDDT = cursor.getString(cursor.getColumnIndex(singleFollowUps.COLUMN_EXPECTEDDT));
        this.VISITNUM = cursor.getString(cursor.getColumnIndex(singleFollowUps.COLUMN_VISITNUM));
        this.MOTHERNAME = cursor.getString(cursor.getColumnIndex(singleFollowUps.COLUMN_MOTHERNAME));


        return this;
    }

    public Long get_ID() {
        return _ID;
    }

    public void set_ID(Long _ID) {
        this._ID = _ID;
    }

    public String getCHILDNAME() {
        return CHILDNAME;
    }

    public void setCHILDNAME(String CHILDNAME) {
        this.CHILDNAME = CHILDNAME;
    }

    public String getMOTHERNAME() {
        return MOTHERNAME;
    }

    public void setMOTHERNAME(String MOTHERNAME) {
        this.MOTHERNAME = MOTHERNAME;
    }

    public String getSTUDYID() {
        return STUDYID;
    }

    public void setSTUDYID(String STUDYID) {
        this.STUDYID = STUDYID;
    }

    public String getEXPECTEDDT() {

        return EXPECTEDDT;
    }

    public void setEXPECTEDDT(String EXPECTEDDT) {
        this.EXPECTEDDT = EXPECTEDDT;
    }

    public String getVISITNUM() {
        return VISITNUM;
    }

    public void setVISITNUM(String VISITNUM) {
        this.VISITNUM = VISITNUM;
    }

    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();
        json.put(singleFollowUps._ID, this._ID == null ? JSONObject.NULL : this._ID);
        json.put(singleFollowUps.COLUMN_CHILDNAME, this.CHILDNAME == null ? JSONObject.NULL : this.CHILDNAME);
        json.put(singleFollowUps.COLUMN_STUDYID, this.STUDYID == null ? JSONObject.NULL : this.STUDYID);
        json.put(singleFollowUps.COLUMN_EXPECTEDDT, this.EXPECTEDDT == null ? JSONObject.NULL : this.EXPECTEDDT);
        json.put(singleFollowUps.COLUMN_VISITNUM, this.VISITNUM == null ? JSONObject.NULL : this.VISITNUM);
        json.put(singleFollowUps.COLUMN_MOTHERNAME, this.MOTHERNAME == null ? JSONObject.NULL : this.MOTHERNAME);

        return json;
    }


    public static abstract class singleFollowUps implements BaseColumns {

        public static final String TABLE_NAME = "visits";
        public static final String _ID = "_id";
        public static final String COLUMN_CHILDNAME = "childname";
        public static final String COLUMN_STUDYID = "studyid";
        public static final String COLUMN_EXPECTEDDT = "expecteddt";
        public static final String COLUMN_VISITNUM = "visitnum";
        public static final String COLUMN_MOTHERNAME = "mothername";


        public static final String _URI = "getfollowups.php";
    }
}