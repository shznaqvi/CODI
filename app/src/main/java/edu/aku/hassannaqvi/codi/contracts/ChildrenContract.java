package edu.aku.hassannaqvi.codi.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hassan.naqvi on 12/2/2016.
 */

public class ChildrenContract {
    private static final String TAG = "Children_CONTRACT";

    private Long _ID;

    // for child level Randomisation

    private String DSSID;
    private String armGrp; // hh02
    private String armSlc;  // Structure
    private String randDate;  // Randomization Date & Time

    private String synced = "";
    private String synced_date = "";

    public ChildrenContract() {
    }


    public ChildrenContract Sync(JSONObject jsonObject) throws JSONException {
//        this._ID = jsonObject.getLong(ChildrenTable._ID);
        this.DSSID = jsonObject.getString(ChildrenTable.COLUMN_DSSID);
        this.armGrp = jsonObject.getString(ChildrenTable.COLUMN_ARMGRP);
        this.armSlc = jsonObject.getString(ChildrenTable.COLUMN_ARMSLC);
//        this.randDate = jsonObject.getString(ChildrenTable.COLUMN_RANDOMIZATION_DATE);

        return this;

    }

    public ChildrenContract Hydrate(Cursor cursor) {
        this._ID = cursor.getLong(cursor.getColumnIndex(ChildrenTable._ID));
        this.DSSID = cursor.getString(cursor.getColumnIndex(ChildrenTable.COLUMN_DSSID));
        this.armGrp = cursor.getString(cursor.getColumnIndex(ChildrenTable.COLUMN_ARMGRP));
        this.armSlc = cursor.getString(cursor.getColumnIndex(ChildrenTable.COLUMN_ARMSLC));
        this.randDate = cursor.getString(cursor.getColumnIndex(ChildrenTable.COLUMN_RANDOMIZATION_DATE));

        return this;
    }

    public Long get_ID() {
        return _ID;
    }

    public void set_ID(Long _ID) {
        this._ID = _ID;
    }

    public String getDSSID() {
        return DSSID;
    }

    public void setDSSID(String DSSID) {
        this.DSSID = DSSID;
    }

    public String getArmGrp() {
        return armGrp;
    }

    public void setArmGrp(String armGrp) {
        this.armGrp = armGrp;
    }

    public String getArmSlc() {
        return armSlc;
    }

    public void setArmSlc(String armSlc) {
        this.armSlc = armSlc;
    }

    public String getRandDate() {
        return randDate;
    }

    public void setRandDate(String randDate) {
        this.randDate = randDate;
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

    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();

        json.put(ChildrenTable._ID, this._ID);
        json.put(ChildrenTable.COLUMN_DSSID, this.DSSID == null ? JSONObject.NULL : this.DSSID);
        json.put(ChildrenTable.COLUMN_ARMGRP, this.armGrp == null ? JSONObject.NULL : this.armGrp);
        json.put(ChildrenTable.COLUMN_ARMSLC, this.armSlc == null ? JSONObject.NULL : this.armSlc);
        json.put(ChildrenTable.COLUMN_RANDOMIZATION_DATE, this.randDate == null ? JSONObject.NULL : this.randDate);

        return json;
    }

    public static abstract class ChildrenTable implements BaseColumns {

        public static final String TABLE_NAME = "children";
        public static final String _URI = "children.php";
        public static final String _ID = "id";

        public static final String COLUMN_DSSID = "dssid";
        public static final String COLUMN_ARMGRP = "armgrp";
        public static final String COLUMN_ARMSLC = "armslct";
        public static final String COLUMN_RANDOMIZATION_DATE = "randdt";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCED_DATE = "synced_date";

    }

}
