package edu.aku.hassannaqvi.codi.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import edu.aku.hassannaqvi.codi.contracts.ChildrenContract;
import edu.aku.hassannaqvi.codi.contracts.ChildrenContract.ChildrenTable;
import edu.aku.hassannaqvi.codi.contracts.EligibilityContract;
import edu.aku.hassannaqvi.codi.contracts.EligibilityContract.EligibilityTable;
import edu.aku.hassannaqvi.codi.contracts.FormsContract;
import edu.aku.hassannaqvi.codi.contracts.FormsContract.FormsTable;
import edu.aku.hassannaqvi.codi.contracts.UsersContract;
import edu.aku.hassannaqvi.codi.contracts.UsersContract.singleUser;


/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "codi.db";
    public static final String DB_NAME = "codi_copy.db";

    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_USERS = "CREATE TABLE " + singleUser.TABLE_NAME + "("
            + singleUser._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + singleUser.ROW_USERNAME + " TEXT,"
            + singleUser.ROW_PASSWORD + " TEXT,"
            + singleUser.FULL_NAME + " TEXT,"
            + singleUser.REGION_DSS + " TEXT );";

    private static final String SQL_CREATE_CHILDREN = "CREATE TABLE " + ChildrenTable.TABLE_NAME + "("
            + ChildrenTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ChildrenTable.COLUMN_DSSID + " TEXT,"
            + ChildrenTable.COLUMN_ARMGRP + " TEXT,"
            + ChildrenTable.COLUMN_ARMSLC + " TEXT,"
            + ChildrenTable.COLUMN_RANDOMIZATION_DATE + " TEXT,"
            + ChildrenTable.COLUMN_SYNCED + " TEXT,"
            + ChildrenTable.COLUMN_SYNCED_DATE + " TEXT"
            + " );";

    private static final String SQL_CREATE_ELIGIBILITY = "CREATE TABLE "
            + EligibilityTable.TABLE_NAME + "("
            + EligibilityTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + EligibilityTable.COLUMN_PROJECTNAME + " TEXT,"
            + EligibilityTable.COLUMN_UID + " TEXT,"
            + EligibilityTable.COLUMN_DSSID + " TEXT,"
            + EligibilityTable.COLUMN_STUDYID + " TEXT,"
            + EligibilityTable.COLUMN_CHILDNAME + " TEXT,"
            + EligibilityTable.COLUMN_MOTHERNAME + " TEXT,"
            + EligibilityTable.COLUMN_DOB + " TEXT,"
            + EligibilityTable.COLUMN_FORMDATE + " TEXT,"
            + EligibilityTable.COLUMN_USER + " TEXT,"
            + EligibilityTable.COLUMN_SEN + " TEXT,"
            + EligibilityTable.COLUMN_GPSLAT + " TEXT,"
            + EligibilityTable.COLUMN_GPSLNG + " TEXT,"
            + EligibilityTable.COLUMN_GPSDT + " TEXT,"
            + EligibilityTable.COLUMN_GPSACC + " TEXT,"
            + EligibilityTable.COLUMN_DEVICEID + " TEXT,"
            + EligibilityTable.COLUMN_DEVICETAGID + " TEXT,"
            + EligibilityTable.COLUMN_SYNCED + " TEXT,"
            + EligibilityTable.COLUMN_SYNCED_DATE + " TEXT"

            + " );";

    private static final String SQL_CREATE_FORMS = "CREATE TABLE "
            + FormsTable.TABLE_NAME + "("
            + EligibilityTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + FormsTable.COLUMN_PROJECTNAME + " TEXT,"
            + FormsTable.COLUMN__UID + " TEXT,"
            + FormsTable.COLUMN_DSSID + " TEXT,"
            + FormsTable.COLUMN_STUDYID + " TEXT,"
            + FormsTable.COLUMN_CHILDNAME + " TEXT,"
            + FormsTable.COLUMN_FORMDATE + " TEXT,"
            + FormsTable.COLUMN_FORMTYPE + " TEXT,"
            + FormsTable.COLUMN_USER + " TEXT,"
            + FormsTable.COLUMN_NEXTAPP + " TEXT,"
            + FormsTable.COLUMN_ISTATUS + " TEXT,"
            + FormsTable.COLUMN_SINFO + " TEXT,"
            + FormsTable.COLUMN_SCHBF + " TEXT,"
            + FormsTable.COLUMN_SBLOODSAMPLE + " TEXT,"
            + FormsTable.COLUMN_SRANDOMIZATION + " TEXT,"
            + FormsTable.COLUMN_SVACCINE + " TEXT,"
            + FormsTable.COLUMN_GPSLAT + " TEXT,"
            + FormsTable.COLUMN_GPSLNG + " TEXT,"
            + FormsTable.COLUMN_GPSDT + " TEXT,"
            + FormsTable.COLUMN_GPSACC + " TEXT,"
            + FormsTable.COLUMN_DEVICEID + " TEXT,"
            + FormsTable.COLUMN_DEVICETAGID + " TEXT,"
            + FormsTable.COLUMN_SYNCED + " TEXT,"
            + FormsTable.COLUMN_SYNCED_DATE + " TEXT"


            + " );";


    private static final String SQL_DELETE_USERS =
            "DROP TABLE IF EXISTS " + singleUser.TABLE_NAME;
    private static final String SQL_DELETE_ELIGIBILITY =
            "DROP TABLE IF EXISTS " + EligibilityTable.TABLE_NAME;
    private static final String SQL_DELETE_FORMS =
            "DROP TABLE IF EXISTS " + FormsTable.TABLE_NAME;
    private static final String SQL_DELETE_CHILDREN =
            "DROP TABLE IF EXISTS " + ChildrenTable.TABLE_NAME;


    private static final String SQL_SELECT_MOTHER_BY_CHILD =
            "SELECT c.agem cm, c.agey cy, c.aged cd, c.gender, c.serial serial, m.serial serialm, c.name child_name, c.dss_id_member child_id, m.name mother_name, c.dss_id_member mother_id, c.dob date_of_birth FROM census C join census m on c.dss_id_m = m.dss_id_member where c.member_type =? and c.uuid = m.uuid and c.current_status IN ('1', '2') and c.uuid = ? group by mother_id order by substr(c.dob, 7) desc, substr(c.dob, 4,2) desc, substr(c.dob, 1,2) desc;";
    private static final String SQL_SELECT_CHILD =
            "SELECT * from census where member_type =? and dss_id_hh =? and uuid =? and current_status IN ('1', '2')";


    private final String TAG = "DatabaseHelper";


    public String spDateT = new SimpleDateFormat("dd-MM-yy").format(new Date().getTime());


    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_USERS);
        db.execSQL(SQL_CREATE_ELIGIBILITY);
        db.execSQL(SQL_CREATE_FORMS);
        db.execSQL(SQL_CREATE_CHILDREN);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_USERS);
        db.execSQL(SQL_DELETE_ELIGIBILITY);
        db.execSQL(SQL_DELETE_FORMS);
        db.execSQL(SQL_DELETE_CHILDREN);

    }

    public void syncUser(JSONArray userlist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(UsersContract.singleUser.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = userlist;
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObjectUser = jsonArray.getJSONObject(i);

                UsersContract user = new UsersContract();
                user.Sync(jsonObjectUser);
                ContentValues values = new ContentValues();

                values.put(singleUser.ROW_USERNAME, user.getUserName());
                values.put(singleUser.ROW_PASSWORD, user.getPassword());
                values.put(singleUser.FULL_NAME, user.getFULL_NAME());
                values.put(singleUser.REGION_DSS, user.getREGION_DSS());
                db.insert(singleUser.TABLE_NAME, null, values);
            }


        } catch (Exception e) {
            Log.d(TAG, "syncUser(e): " + e);
        } finally {
            db.close();
        }
    }

    public void getChildren(JSONArray userlist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ChildrenTable.TABLE_NAME, null, null);
        try {
            JSONArray jsonArray = userlist;
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObjectUser = jsonArray.getJSONObject(i);

                ChildrenContract cc = new ChildrenContract();
                cc.Sync(jsonObjectUser);
                ContentValues values = new ContentValues();

                values.put(ChildrenTable.COLUMN_DSSID, cc.getDSSID());
                values.put(ChildrenTable.COLUMN_ARMGRP, cc.getArmGrp());
                values.put(ChildrenTable.COLUMN_ARMSLC, cc.getArmSlc());
                values.put(ChildrenTable.COLUMN_RANDOMIZATION_DATE, cc.getArmSlc());
                db.insert(ChildrenTable.TABLE_NAME, null, values);
            }

        } catch (Exception e) {
            Log.d(TAG, "syncChildren(e): " + e);
        } finally {
            db.close();
        }
    }


    public boolean Login(String username, String password) throws SQLException {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor mCursor = db.rawQuery("SELECT * FROM " + singleUser.TABLE_NAME + " WHERE " + singleUser.ROW_USERNAME + "=? AND " + singleUser.ROW_PASSWORD + "=?", new String[]{username, password});
        if (mCursor != null) {
            if (mCursor.getCount() > 0) {

                if (mCursor.moveToFirst()) {
/*
                    AppMain.regionDss = mCursor.getString(mCursor.getColumnIndex("region_dss"));
*/
                }
                return true;
            }
        }
        return false;
    }

    public Long addEligibility(EligibilityContract elc) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        values.put(EligibilityTable.COLUMN_PROJECTNAME, elc.getProjectName());
        values.put(EligibilityTable.COLUMN_UID, elc.get_UID());
        values.put(EligibilityTable.COLUMN_DSSID, elc.getDSSID());
        values.put(EligibilityTable.COLUMN_STUDYID, elc.getStudyID());
        values.put(EligibilityTable.COLUMN_CHILDNAME, elc.getChildName());
        values.put(EligibilityTable.COLUMN_MOTHERNAME, elc.getMotherName());
        values.put(EligibilityTable.COLUMN_DOB, elc.getDob());
        values.put(EligibilityTable.COLUMN_FORMDATE, elc.getFormDate());
        values.put(EligibilityTable.COLUMN_USER, elc.getUser());
        values.put(EligibilityTable.COLUMN_SEN, elc.getsEl());
        values.put(EligibilityTable.COLUMN_GPSLAT, elc.getGpsLat());
        values.put(EligibilityTable.COLUMN_GPSLNG, elc.getGpsLng());
        values.put(EligibilityTable.COLUMN_GPSDT, elc.getGpsDT());
        values.put(EligibilityTable.COLUMN_GPSACC, elc.getGpsAcc());
        values.put(EligibilityTable.COLUMN_DEVICEID, elc.getDeviceID());
        values.put(EligibilityTable.COLUMN_DEVICETAGID, elc.getDevicetagID());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                EligibilityTable.TABLE_NAME,
                EligibilityTable.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }

    public Long addEnrollment(FormsContract enc) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        values.put(FormsTable.COLUMN_PROJECTNAME, enc.getProjectName());
        values.put(FormsTable.COLUMN__UID, enc.get_UID());
        values.put(FormsTable.COLUMN_DSSID, enc.getDSSID());
        values.put(FormsTable.COLUMN_STUDYID, enc.getStudyID());
        values.put(FormsTable.COLUMN_CHILDNAME, enc.getChildName());
        values.put(FormsTable.COLUMN_FORMDATE, enc.getFormDate());
        values.put(FormsTable.COLUMN_FORMTYPE, enc.getFormType());
        values.put(FormsTable.COLUMN_USER, enc.getUser());
        values.put(FormsTable.COLUMN_NEXTAPP, enc.getNextApp());
        values.put(FormsTable.COLUMN_ISTATUS, enc.getIstatus());
        values.put(FormsTable.COLUMN_SINFO, enc.getsInfo());
        values.put(FormsTable.COLUMN_SCHBF, enc.getsCHBF());
        values.put(FormsTable.COLUMN_SBLOODSAMPLE, enc.getsBloodSample());
        values.put(FormsTable.COLUMN_SRANDOMIZATION, enc.getsRandomization());
        values.put(FormsTable.COLUMN_SVACCINE, enc.getsVaccine());
        values.put(FormsTable.COLUMN_GPSLAT, enc.getGpsLat());
        values.put(FormsTable.COLUMN_GPSLNG, enc.getGpsLng());
        values.put(FormsTable.COLUMN_GPSDT, enc.getGpsDT());
        values.put(FormsTable.COLUMN_GPSACC, enc.getGpsAcc());
        values.put(FormsTable.COLUMN_DEVICEID, enc.getDeviceID());
        values.put(FormsTable.COLUMN_DEVICETAGID, enc.getDevicetagID());


        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                FormsTable.TABLE_NAME,
                FormsTable.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }

    public int updateSBloodSample() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SBLOODSAMPLE, AppMain.fc.getsBloodSample());
        //values.put(FormsTable.COLUMN__UID, AppMain.fc.get_UID());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(AppMain.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSCHBF() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SCHBF, AppMain.fc.getsCHBF());
        //values.put(FormsTable.COLUMN__UID, AppMain.fc.get_UID());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(AppMain.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }


    public int updateSRandomizationChild(String DSSID) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(ChildrenTable.COLUMN_RANDOMIZATION_DATE, AppMain.cc.getRandDate());
        values.put(ChildrenTable.COLUMN_ARMSLC, AppMain.cc.getArmSlc());

// Which row to update, based on the ID
        String selection = ChildrenTable.COLUMN_DSSID + " = ?";
        String[] selectionArgs = {String.valueOf(DSSID)};

        int count = db.update(ChildrenTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSRandomization() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SRANDOMIZATION, AppMain.fc.getsRandomization());
        //values.put(FormsTable.COLUMN__UID, AppMain.fc.get_UID());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(AppMain.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }


    public int updateSVaccine() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SVACCINE, AppMain.fc.getsVaccine());
        //values.put(FormsTable.COLUMN__UID, AppMain.fc.get_UID());
// Which row to update, based on the ID
        String selection = FormsTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(AppMain.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }


    public List<ChildrenContract> getChildByDSS(String dssID) {
        List<ChildrenContract> childrenList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + ChildrenTable.TABLE_NAME + " where " + ChildrenTable.COLUMN_DSSID + " = '" + dssID + "';";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                ChildrenContract cc = new ChildrenContract();
                childrenList.add(cc.Hydrate(c));
            } while (c.moveToNext());
        }

        // return contact list
        return childrenList;
    }


    public void updateSyncedEligibility(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(EligibilityTable.COLUMN_SYNCED, true);
        values.put(EligibilityTable.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = EligibilityTable._ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                EligibilityTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }


    public Collection<EligibilityContract> getAllEligibility() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                EligibilityTable.COLUMN_PROJECTNAME,
                EligibilityTable._ID,
                EligibilityTable.COLUMN_UID,
                EligibilityTable.COLUMN_DSSID,
                EligibilityTable.COLUMN_STUDYID,
                EligibilityTable.COLUMN_CHILDNAME,
                EligibilityTable.COLUMN_MOTHERNAME,
                EligibilityTable.COLUMN_DOB,
                EligibilityTable.COLUMN_FORMDATE,
                EligibilityTable.COLUMN_USER,
                EligibilityTable.COLUMN_SEN,
                EligibilityTable.COLUMN_GPSLAT,
                EligibilityTable.COLUMN_GPSLNG,
                EligibilityTable.COLUMN_GPSDT,
                EligibilityTable.COLUMN_GPSACC,
                EligibilityTable.COLUMN_DEVICEID,
                EligibilityTable.COLUMN_DEVICETAGID


        };
        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                EligibilityTable._ID + " ASC";

        Collection<EligibilityContract> allFC = new ArrayList<EligibilityContract>();
        try {
            c = db.query(
                    EligibilityTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                EligibilityContract fc = new EligibilityContract();
                allFC.add(fc.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }


    public Collection<EligibilityContract> getUnsyncedEligibility() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                EligibilityTable.COLUMN_PROJECTNAME,
                EligibilityTable._ID,
                EligibilityTable.COLUMN_UID,
                EligibilityTable.COLUMN_DSSID,
                EligibilityTable.COLUMN_STUDYID,
                EligibilityTable.COLUMN_CHILDNAME,
                EligibilityTable.COLUMN_MOTHERNAME,
                EligibilityTable.COLUMN_DOB,
                EligibilityTable.COLUMN_FORMDATE,
                EligibilityTable.COLUMN_USER,
                EligibilityTable.COLUMN_SEN,
                EligibilityTable.COLUMN_GPSLAT,
                EligibilityTable.COLUMN_GPSLNG,
                EligibilityTable.COLUMN_GPSDT,
                EligibilityTable.COLUMN_GPSACC,
                EligibilityTable.COLUMN_DEVICEID,
                EligibilityTable.COLUMN_DEVICETAGID,
                EligibilityTable.COLUMN_SYNCED,
                EligibilityTable.COLUMN_SYNCED_DATE,

        };
        String whereClause = EligibilityTable.COLUMN_SYNCED + " is null";
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                EligibilityTable._ID + " ASC";

        Collection<EligibilityContract> allFC = new ArrayList<EligibilityContract>();
        try {
            c = db.query(
                    EligibilityTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                EligibilityContract fc = new EligibilityContract();
                allFC.add(fc.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public Collection<FormsContract> getAllForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable.COLUMN_PROJECTNAME,
                FormsTable._ID,
                FormsTable.COLUMN__UID,
                FormsTable.COLUMN_DSSID,
                FormsTable.COLUMN_STUDYID,
                FormsTable.COLUMN_CHILDNAME,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_FORMTYPE,
                FormsTable.COLUMN_USER,
                FormsTable.COLUMN_NEXTAPP,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_SINFO,
                FormsTable.COLUMN_SCHBF,
                FormsTable.COLUMN_SBLOODSAMPLE,
                FormsTable.COLUMN_SRANDOMIZATION,
                FormsTable.COLUMN_SVACCINE,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDT,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_DEVICETAGID


        };
        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable._ID + " ASC";

        Collection<FormsContract> allFC = new ArrayList<FormsContract>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsContract fc = new FormsContract();
                allFC.add(fc.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }


    public Collection<FormsContract> getUnsyncedForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable.COLUMN_PROJECTNAME,
                FormsTable._ID,
                FormsTable.COLUMN__UID,
                FormsTable.COLUMN_DSSID,
                FormsTable.COLUMN_STUDYID,
                FormsTable.COLUMN_CHILDNAME,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_FORMTYPE,
                FormsTable.COLUMN_USER,
                FormsTable.COLUMN_NEXTAPP,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_SINFO,
                FormsTable.COLUMN_SCHBF,
                FormsTable.COLUMN_SBLOODSAMPLE,
                FormsTable.COLUMN_SRANDOMIZATION,
                FormsTable.COLUMN_SVACCINE,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSDT,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_DEVICETAGID


        };
        String whereClause = FormsTable.COLUMN_SYNCED + " is null";
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable._ID + " ASC";

        Collection<FormsContract> allFC = new ArrayList<FormsContract>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormsContract fc = new FormsContract();
                allFC.add(fc.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public int updateFormID() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN__UID, AppMain.fc.get_UID());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(AppMain.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateELC() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN__UID, AppMain.elc.get_UID());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(AppMain.elc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public void updateSyncedForms(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SYNCED, true);
        values.put(FormsTable.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = FormsTable._ID + " LIKE ?";
        String[] whereArgs = {id};

        int count = db.update(
                FormsTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }


    public Collection<ChildrenContract> getUnsyncedChildren() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                ChildrenTable._ID,
                ChildrenTable.COLUMN_DSSID,
                ChildrenTable.COLUMN_ARMGRP,
                ChildrenTable.COLUMN_ARMSLC,
                ChildrenTable.COLUMN_RANDOMIZATION_DATE
        };
        String whereClause = ChildrenTable.COLUMN_SYNCED + " is null and " + ChildrenTable.COLUMN_ARMSLC + " is not null";
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                ChildrenTable._ID + " ASC";

        Collection<ChildrenContract> allFC = new ArrayList<>();
        try {
            c = db.query(
                    ChildrenTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                ChildrenContract fc = new ChildrenContract();
                allFC.add(fc.Hydrate(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public void updateSyncedChildren(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(ChildrenTable.COLUMN_SYNCED, true);
        values.put(ChildrenTable.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = ChildrenTable._ID + " LIKE ?";
        String[] whereArgs = {id};

        int count = db.update(
                ChildrenTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    public Collection<EligibilityContract> getTodayForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                EligibilityTable._ID,
                EligibilityTable.COLUMN_DSSID,
                EligibilityTable.COLUMN_FORMDATE,
                EligibilityTable.COLUMN_SYNCED,

        };
        String whereClause = EligibilityTable.COLUMN_FORMDATE + " Like ? ";
        String[] whereArgs = new String[]{"%" + spDateT.substring(0, 8).trim() + "%"};
        String groupBy = null;
        String having = null;

        String orderBy =
                EligibilityTable._ID + " ASC";

        Collection<EligibilityContract> allFC = new ArrayList<>();
        try {
            c = db.query(
                    EligibilityTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                EligibilityContract fc = new EligibilityContract();
                fc.set_ID(c.getString(c.getColumnIndex(EligibilityTable._ID)));
                fc.setDSSID(c.getString(c.getColumnIndex(EligibilityTable.COLUMN_DSSID)));
                fc.setFormDate(c.getString(c.getColumnIndex(EligibilityTable.COLUMN_FORMDATE)));
                fc.setSynced(c.getString(c.getColumnIndex(EligibilityTable.COLUMN_SYNCED)));
                allFC.add(fc);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    // ANDROID DATABASE MANAGER
    public ArrayList<Cursor> getData(String Query) {
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[]{"message"};
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2 = new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try {
            String maxQuery = Query;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[]{"Success"});

            alc.set(1, Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0, c);
                c.moveToFirst();

                return alc;
            }
            return alc;
        } catch (SQLException sqlEx) {
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + sqlEx.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        } catch (Exception ex) {

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + ex.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        }
    }

    public int updateEnEnding() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(EligibilityTable.COLUMN_ISTATUS, AppMain.elc.getIstatus());

// Which row to update, based on the ID
        String selection = " _ID = " + AppMain.elc.get_ID();
        String[] selectionArgs = {String.valueOf(AppMain.elc.get_ID())};

        int count = db.update(EligibilityTable.TABLE_NAME,
                values,
                selection,
                null);
        return count;
    }

}