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

import edu.aku.hassannaqvi.codi.contracts.EligibilityContract;
import edu.aku.hassannaqvi.codi.contracts.EligibilityContract.EligibilityTable;
import edu.aku.hassannaqvi.codi.contracts.EnrollmentContract;
import edu.aku.hassannaqvi.codi.contracts.EnrollmentContract.EnrollmentTable;
import edu.aku.hassannaqvi.codi.contracts.UsersContract;
import edu.aku.hassannaqvi.codi.contracts.UsersContract.singleUser;


/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String SQL_CREATE_USERS = "CREATE TABLE " + singleUser.TABLE_NAME + "("
            + singleUser._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + singleUser.ROW_USERNAME + " TEXT,"
            + singleUser.ROW_PASSWORD + " TEXT,"
            + singleUser.FULL_NAME + " TEXT,"
            + singleUser.REGION_DSS + " TEXT );";
    public static final String DATABASE_NAME = "codi.db";
    public static final String DB_NAME = "codi_copy.db";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_ELIGIBILITY = "CREATE TABLE "
            + EligibilityTable.TABLE_NAME + "("
            + EligibilityTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + EligibilityTable.COLUMN_PROJECTNAME + " TEXT,"
            + EligibilityTable.COLUMN_UID + " TEXT,"
            + EligibilityTable.COLUMN_DSSID + " TEXT,"
            + EligibilityTable.COLUMN_STUDYID + " TEXT,"
            + EligibilityTable.COLUMN_CHILDNAME + " TEXT,"
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

    private static final String SQL_CREATE_ENROLLMENT = "CREATE TABLE "
            + EnrollmentTable.TABLE_NAME + "("
            + EligibilityTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + EnrollmentTable.COLUMN_PROJECTNAME + " TEXT,"
            + EnrollmentTable.COLUMN__UID + " TEXT,"
            + EnrollmentTable.COLUMN_DSSID + " TEXT,"
            + EnrollmentTable.COLUMN_STUDYID + " TEXT,"
            + EnrollmentTable.COLUMN_CHILDNAME + " TEXT,"
            + EnrollmentTable.COLUMN_FORMDATE + " TEXT,"
            + EnrollmentTable.COLUMN_USER + " TEXT,"
            + EnrollmentTable.COLUMN_NEXTAPP + " TEXT,"
            + EnrollmentTable.COLUMN_SENINFO + " TEXT,"
            + EnrollmentTable.COLUMN_SENBLOODSAMPLE + " TEXT,"
            + EnrollmentTable.COLUMN_SENRANDOMIZATION + " TEXT,"
            + EnrollmentTable.COLUMN_SENVACCINE + " TEXT,"
            + EnrollmentTable.COLUMN_GPSLAT + " TEXT,"
            + EnrollmentTable.COLUMN_GPSLNG + " TEXT,"
            + EnrollmentTable.COLUMN_GPSDT + " TEXT,"
            + EnrollmentTable.COLUMN_GPSACC + " TEXT,"
            + EnrollmentTable.COLUMN_DEVICEID + " TEXT,"
            + EnrollmentTable.COLUMN_DEVICETAGID + " TEXT,"
            + EnrollmentTable.COLUMN_SYNCED + " TEXT,"
            + EnrollmentTable.COLUMN_SYNCED_DATE + " TEXT"

            + " );";


    private static final String SQL_DELETE_USERS =
            "DROP TABLE IF EXISTS " + singleUser.TABLE_NAME;
    private static final String SQL_DELETE_ELIGIBILITY =
            "DROP TABLE IF EXISTS " + EligibilityTable.TABLE_NAME;
    private static final String SQL_DELETE_ENROLLMENT =
            "DROP TABLE IF EXISTS " + EnrollmentTable.TABLE_NAME;


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
        db.execSQL(SQL_CREATE_ENROLLMENT);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_USERS);
        db.execSQL(SQL_DELETE_ELIGIBILITY);
        db.execSQL(SQL_DELETE_ENROLLMENT);

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


    public boolean Login(String username, String password) throws SQLException {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor mCursor = db.rawQuery("SELECT * FROM " + singleUser.TABLE_NAME + " WHERE " + singleUser.ROW_USERNAME + "=? AND " + singleUser.ROW_PASSWORD + "=?", new String[]{username, password});
        if (mCursor != null) {
            if (mCursor.getCount() > 0) {

                if (mCursor.moveToFirst()) {
/*
                    MainApp.regionDss = mCursor.getString(mCursor.getColumnIndex("region_dss"));
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
        values.put(EligibilityTable._ID, elc.get_ID());
        values.put(EligibilityTable.COLUMN_UID, elc.get_UID());
        values.put(EligibilityTable.COLUMN_DSSID, elc.getDSSID());
        values.put(EligibilityTable.COLUMN_STUDYID, elc.getStudyID());
        values.put(EligibilityTable.COLUMN_CHILDNAME, elc.getChildName());
        values.put(EligibilityTable.COLUMN_FORMDATE, elc.getFormDate());
        values.put(EligibilityTable.COLUMN_USER, elc.getUser());
        values.put(EligibilityTable.COLUMN_SEN, elc.getsEn());
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

    public Long addEnrollment(EnrollmentContract enc) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        values.put(EnrollmentTable.COLUMN_PROJECTNAME, enc.getProjectName());
        values.put(EnrollmentTable._ID, enc.get_ID());
        values.put(EnrollmentTable.COLUMN__UID, enc.get_UID());
        values.put(EnrollmentTable.COLUMN_DSSID, enc.getDSSID());
        values.put(EnrollmentTable.COLUMN_STUDYID, enc.getStudyID());
        values.put(EnrollmentTable.COLUMN_CHILDNAME, enc.getChildName());
        values.put(EnrollmentTable.COLUMN_FORMDATE, enc.getFormDate());
        values.put(EnrollmentTable.COLUMN_USER, enc.getUser());
        values.put(EnrollmentTable.COLUMN_NEXTAPP, enc.getNextApp());
        values.put(EnrollmentTable.COLUMN_SENINFO, enc.getsEnInfo());
     /*   values.put(EnrollmentTable.COLUMN_SENBLOODSAMPLE, enc.getsEnBloodSample());
        values.put(EnrollmentTable.COLUMN_SENRANDOMIZATION, enc.getsEnRandomization());
        values.put(EnrollmentTable.COLUMN_SENVACCINE, enc.getsEnVaccine());*/
        values.put(EnrollmentTable.COLUMN_GPSLAT, enc.getGpsLat());
        values.put(EnrollmentTable.COLUMN_GPSLNG, enc.getGpsLng());
        values.put(EnrollmentTable.COLUMN_GPSDT, enc.getGpsDT());
        values.put(EnrollmentTable.COLUMN_GPSACC, enc.getGpsAcc());
        values.put(EnrollmentTable.COLUMN_DEVICEID, enc.getDeviceID());
        values.put(EnrollmentTable.COLUMN_DEVICETAGID, enc.getDevicetagID());


        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                EnrollmentTable.TABLE_NAME,
                EnrollmentTable.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }

    public int updateSenBloodSample() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(EnrollmentTable.COLUMN_SENBLOODSAMPLE, MainApp.enc.getsEnBloodSample());

// Which row to update, based on the ID
        String selection = EligibilityTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.elc.get_ID())};

        int count = db.update(EligibilityTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSenRandomization() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(EnrollmentTable.COLUMN_SENRANDOMIZATION, MainApp.enc.getsEnRandomization());
// Which row to update, based on the ID
        String selection = EligibilityTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.elc.get_ID())};

        int count = db.update(EligibilityTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSenVaccine() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(EnrollmentTable.COLUMN_SENVACCINE, MainApp.enc.getsEnVaccine());
// Which row to update, based on the ID
        String selection = EligibilityTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.elc.get_ID())};

        int count = db.update(EligibilityTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }


    public List<EligibilityContract> getFormsByDSS(String dssID) {
        List<EligibilityContract> formList = new ArrayList<EligibilityContract>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + EligibilityTable.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                EligibilityContract fc = new EligibilityContract();
                formList.add(fc.Hydrate(c));
            } while (c.moveToNext());
        }

        // return contact list
        return formList;
    }


    public void updateSyncedForms(String id) {
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


    public Collection<EligibilityContract> getAllForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                EligibilityTable.COLUMN_PROJECTNAME,
                EligibilityTable._ID,
                EligibilityTable.COLUMN_UID,
                EligibilityTable.COLUMN_DSSID,
                EligibilityTable.COLUMN_STUDYID,
                EligibilityTable.COLUMN_CHILDNAME,
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


    public Collection<EligibilityContract> getUnsyncedForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                EligibilityTable.COLUMN_PROJECTNAME,
                EligibilityTable._ID,
                EligibilityTable.COLUMN_UID,
                EligibilityTable.COLUMN_DSSID,
                EligibilityTable.COLUMN_STUDYID,
                EligibilityTable.COLUMN_CHILDNAME,
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

   /* public int updateEnding() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(EligibilityTable.COLUMN_ISTATUS, MainApp.elc.getIstatus());

// Which row to update, based on the ID
        String selection = " _ID = " + MainApp.elc.get_ID();
        String[] selectionArgs = {String.valueOf(MainApp.elc.get_ID())};

        int count = db.update(EligibilityTable.TABLE_NAME,
                values,
                selection,
                null);
        return count;
    }
*/

}