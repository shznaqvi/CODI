package edu.aku.hassannaqvi.codi.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.aku.hassannaqvi.codi.R;
import edu.aku.hassannaqvi.codi.contracts.FormsContract;
import edu.aku.hassannaqvi.codi.core.AppMain;
import edu.aku.hassannaqvi.codi.core.DatabaseHelper;

public class VisitInfoActivity extends Activity {

    private static final String TAG = VisitInfoActivity.class.getSimpleName();

    @BindView(R.id.studyId)
    EditText studyId;
    @BindView(R.id.csv01)
    EditText csv01;
    @BindView(R.id.csv02)
    EditText csv02;
    //@BindView(R.id.csv03)
    //EditText csv03;
    @BindView(R.id.heading)
    TextView heading;

    boolean check = false;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_info);
        ButterKnife.bind(this);

        db = new DatabaseHelper(this);

        if (AppMain.formType.equals("V2")) {
            heading.setText(R.string.csvheading);

        } else if (AppMain.formType.equals("V3")) //&& AppMain.cc.getArmGrp().equals("AB"))
        {
            heading.setText(R.string.ctvheading);
        } else if (AppMain.formType.equals("V3") && AppMain.cc.getArmGrp().equals("CD"))
        {
            heading.setText(R.string.ctvbheading);
        } else if (AppMain.formType.equals("V4")) {
            heading.setText(R.string.cfvheading);
        } else if (AppMain.formType.equals("V5")) {
            heading.setText(R.string.cfivheading);
        }

        /*csv01.setText(AppMain.fc.getNextApp());
        csv02.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(System.currentTimeMillis()));
        csv03.setText(AppMain.cc.getArmSlc());

*/

    }

    @OnClick(R.id.btn_End)
    void onBtnEndClick() {
        Toast.makeText(this, "Processing This Section", Toast.LENGTH_SHORT).show();


        if (check) {
            try {
                SaveDraftIncomplete();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                finish();
                Toast.makeText(this, "Starting Form Ending Section", Toast.LENGTH_SHORT).show();
                Intent endSec = new Intent(this, EndingActivity.class);
                endSec.putExtra("complete", false);
                startActivity(endSec);
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }

    }


    @OnClick(R.id.btn_Continue)
    void onBtnContinueClick() {
        Toast.makeText(this, "Processing thi section", Toast.LENGTH_SHORT).show();
        if (ValidateForm()) {

            if (check) {
                try {
                    SaveDraft();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (UpdateDB()) {
                    Toast.makeText(this, "starting next section", Toast.LENGTH_SHORT).show();

                    finish();
                    Intent secB = new Intent(this, ChildHealthAndBreastFeedActivity.class);
                    startActivity(secB);
                } else {
                    Toast.makeText(this, "Failed to update Database", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Click on Check Button", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @OnClick(R.id.btnCheck)
    void onBtnCheckClick() {


        AppMain.visitList = db.getChildByStudyID(studyId.getText().toString().toUpperCase(),String.valueOf(AppMain.formType.charAt(1)));

        if (AppMain.visitList.size() != 0) {

            if (getDays(AppMain.visitList.get(0).getEXPECTEDDT())) {

                Toast.makeText(getApplicationContext(), "Children found", Toast.LENGTH_LONG).show();

                csv01.setText(AppMain.visitList.get(0).getEXPECTEDDT());

                csv02.setText(new SimpleDateFormat("dd-MM-yyyy").format(System.currentTimeMillis()));

                //fldGrpChild.setVisibility(View.VISIBLE);

                check = true;
            } else {
                Toast.makeText(getApplicationContext(), "Children Already Visited!", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Children Not found", Toast.LENGTH_LONG).show();

            check = false;
        }
    }

    public Boolean getDays(String date) {
        SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");


        try {
            Date date1 = myFormat.parse(date);
            Date date2 = new Date();
            long diff = date2.getTime() - date1.getTime();

            return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) <= 7;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }


    private void SaveDraftIncomplete() throws JSONException {
        Toast.makeText(this, "Saving Draft for this Section", Toast.LENGTH_SHORT).show();

        SharedPreferences sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);

        AppMain.fc = new FormsContract();

        AppMain.fc.setDevicetagID(sharedPref.getString("tagName", null));
        AppMain.fc.setFormDate(new Date().toString());
        AppMain.fc.setUser(AppMain.userName);
        AppMain.fc.setDeviceID(Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID));
        //AppMain.fc.setDSSID(dssID.getText().toString());
        AppMain.fc.setStudyID(AppMain.visitList.get(0).getSTUDYID());
        AppMain.fc.setFormType(AppMain.formType);

        setGPS();

    }

    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for this Section", Toast.LENGTH_SHORT).show();
        SharedPreferences sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);
        AppMain.fc = new FormsContract();

        AppMain.fc.setDevicetagID(sharedPref.getString("tagName", null));
        AppMain.fc.setFormDate(new Date().toString());
        AppMain.fc.setUser(AppMain.userName);
        AppMain.fc.setDeviceID(Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID));
        //AppMain.fc.setDSSID(AppMain.visitList.get(0).get());
        AppMain.fc.setChildName(AppMain.visitList.get(0).getCHILDNAME());
        AppMain.fc.setStudyID(AppMain.visitList.get(0).getSTUDYID());
        //AppMain.motherName = celmn.getText().toString();
        //AppMain.dob = celdob.getText().toString();
        //AppMain.fc.setFormType("V1");
        AppMain.fc.setFormType(AppMain.formType);

        JSONObject sInfo = new JSONObject();

        sInfo.put("csv01", csv01.getText().toString());
        sInfo.put("csv02", csv02.getText().toString());

        AppMain.fc.setsInfo(String.valueOf(sInfo));

        setGPS();

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();


    }

    public void setGPS() {
        SharedPreferences GPSPref = getSharedPreferences("GPSCoordinates", Context.MODE_PRIVATE);

//        String date = DateFormat.format("dd-MM-yyyy HH:mm", Long.parseLong(GPSPref.getString("Time", "0"))).toString();

        try {
            String lat = GPSPref.getString("Latitude", "0");
            String lang = GPSPref.getString("Longitude", "0");
            String acc = GPSPref.getString("Accuracy", "0");
            String dt = GPSPref.getString("Time", "0");

            if (lat == "0" && lang == "0") {
                Toast.makeText(this, "Could not obtained GPS points", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "GPS set", Toast.LENGTH_SHORT).show();
            }

            String date = DateFormat.format("dd-MM-yyyy HH:mm", Long.parseLong(GPSPref.getString("Time", "0"))).toString();

            AppMain.fc.setGpsLat(GPSPref.getString("Latitude", "0"));
            AppMain.fc.setGpsLng(GPSPref.getString("Longitude", "0"));
            AppMain.fc.setGpsAcc(GPSPref.getString("Accuracy", "0"));
//            AppMain.fc.setGpsTime(GPSPref.getString(date, "0")); // Timestamp is converted to date above
            AppMain.fc.setGpsDT(date); // Timestamp is converted to date above

            Toast.makeText(this, "GPS set", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e(TAG, "setGPS: " + e.getMessage());
        }

    }

    private boolean UpdateDB() {

        DatabaseHelper db = new DatabaseHelper(this);

        long updcount = db.addEnrollment(AppMain.fc);

        AppMain.fc.set_ID(String.valueOf(updcount));

        if (updcount != 0) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();

            AppMain.fc.set_UID(
                    (AppMain.fc.getDeviceID() + AppMain.fc.get_ID()));
            db.updateFormID();
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    public boolean ValidateForm() {

        /*if (cen25.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen25), Toast.LENGTH_SHORT).show();
            cen25.setError("This data is Required!");

            Log.i(TAG, "cen25: This Data is Required!");
            return false;
        } else {
            cen25.setError(null);
        }


        if (cen27.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen27), Toast.LENGTH_SHORT).show();
            cen27a.setError("This data is Required!");

            Log.i(TAG, "cen27: This Data is Required!");
            return false;
        } else {
            cen27a.setError(null);
        }

*/
        return true;
    }

   /* @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
    }
*/



}
