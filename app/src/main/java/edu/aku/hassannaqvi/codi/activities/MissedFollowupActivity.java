package edu.aku.hassannaqvi.codi.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.aku.hassannaqvi.codi.R;
import edu.aku.hassannaqvi.codi.contracts.FormsContract;
import edu.aku.hassannaqvi.codi.core.AppMain;
import edu.aku.hassannaqvi.codi.core.DatabaseHelper;
import io.blackbox_vision.datetimepickeredittext.view.DatePickerInputEditText;

public class MissedFollowupActivity extends AppCompatActivity {
    private static final String TAG = MissedFollowupActivity.class.getSimpleName();

    @BindView(R.id.heading)
    TextView heading;
    @BindView(R.id.studyid)
    EditText studyid;
    @BindView(R.id.childname)
    EditText childname;
    @BindView(R.id.mother)
    EditText mother;
    @BindView(R.id.csv01)
    DatePickerInputEditText csv01;
    @BindView(R.id.csv02)
    DatePickerInputEditText csv02;
    @BindView(R.id.vround)
    EditText vround;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missed_followup);
        ButterKnife.bind(this);
        //AppMain.enrollDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(System.currentTimeMillis());

        db = new DatabaseHelper(this);

        csv01.setManager(getSupportFragmentManager());
        csv02.setManager(getSupportFragmentManager());
        csv01.setMaxDate(new SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis()));
        csv02.setMaxDate(new SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis()));

    }

    @OnClick(R.id.btn_End)
    void onBtnEndClick() {
        Toast.makeText(this, "Processing This Section", Toast.LENGTH_SHORT).show();

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


    @OnClick(R.id.btn_Continue)
    void onBtnContinueClick() {
        Toast.makeText(this, "Processing thi section", Toast.LENGTH_SHORT).show();
        if (ValidateForm()) {

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


    private void SaveDraftIncomplete() throws JSONException {
        Toast.makeText(this, "Saving Draft for this Section", Toast.LENGTH_SHORT).show();

        SharedPreferences sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);

        AppMain.fc = new FormsContract();

        AppMain.fc.setDevicetagID(sharedPref.getString("tagName", null));
        AppMain.fc.setFormDate(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(System.currentTimeMillis()));
        AppMain.fc.setUser(AppMain.userName);
        AppMain.fc.setDeviceID(Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID));
        AppMain.fc.setChildName(childname.getText().toString());
        AppMain.fc.setStudyID(studyid.getText().toString());
        AppMain.fc.setFormType("V" + vround.getText().toString());
        AppMain.formType = "V" + vround.getText().toString();
        AppMain.fc.setProjectName("CODI-AMNA");

        setGPS();

    }

    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for this Section", Toast.LENGTH_SHORT).show();
        SharedPreferences sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);
        AppMain.fc = new FormsContract();

        AppMain.fc.setDevicetagID(sharedPref.getString("tagName", null));
        AppMain.fc.setFormDate(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(System.currentTimeMillis()));
        AppMain.fc.setUser(AppMain.userName);
        AppMain.fc.setDeviceID(Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID));
        AppMain.fc.setChildName(childname.getText().toString());
        AppMain.fc.setStudyID(studyid.getText().toString());
        AppMain.fc.setFormType("V" + vround.getText().toString());
        AppMain.formType = "V" + vround.getText().toString();


        AppMain.sInfo.put("csv01", csv01.getText().toString());
        AppMain.sInfo.put("csv02", csv02.getText().toString());
        AppMain.sInfo.put("mother", mother.getText().toString());
        AppMain.sInfo.put("vround", vround.getText().toString());

        AppMain.fc.setsInfo(String.valueOf(AppMain.sInfo));

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

        if (studyid.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.studyID), Toast.LENGTH_SHORT).show();
            studyid.setError("This data is Required!");

            Log.i(TAG, "studyid: This Data is Required!");
            return false;
        } else {
            studyid.setError(null);
        }


        if (!studyid.getText().toString().substring(0, 4).contains("CODI")) {
            Toast.makeText(this, "ERROR(invalid)" + getString(R.string.celstdid), Toast.LENGTH_SHORT).show();
            studyid.setError("Wrong Study ID.. Please correct \r\n\t - must contain word 'CODI'");
            Log.d(TAG, "studyid:invalid ");
            return false;
        } else {
            studyid.setError(null);
        }


        if (studyid.getText().length() < 12 || !studyid.getText().toString().contains("-")) {
            Toast.makeText(this, "ERROR(invalid)" + getString(R.string.celstdid), Toast.LENGTH_SHORT).show();
            studyid.setError("Wrong Study ID.. Please correct \r\n\t - must be 12 characters \r\n\t - must contain '-'");
            Log.d(TAG, "studyId:invalid ");
            return false;
        } else {
            studyid.setError(null);
        }


        if (vround.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + "Visit Number", Toast.LENGTH_SHORT).show();
            vround.setError("This data is Required!");

            Log.i(TAG, "vround: This Data is Required!");
            return false;
        } else {
            vround.setError(null);
        }


        if (childname.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.celcn), Toast.LENGTH_SHORT).show();
            childname.setError("This data is Required!");

            Log.i(TAG, "childname: This Data is Required!");
            return false;
        } else {
            childname.setError(null);
        }


        if (mother.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.celmn), Toast.LENGTH_SHORT).show();
            mother.setError("This data is Required!");

            Log.i(TAG, "mother: This Data is Required!");
            return false;
        } else {
            mother.setError(null);
        }

        if (csv01.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.csv01), Toast.LENGTH_SHORT).show();
            csv01.setError("This data is Required!");

            Log.i(TAG, "csv01: This Data is Required!");
            return false;
        } else {
            csv01.setError(null);
        }


        if (csv02.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.csv02), Toast.LENGTH_SHORT).show();
            csv02.setError("This data is Required!");

            Log.i(TAG, "csv02: This Data is Required!");
            return false;
        } else {
            csv02.setError(null);
        }

        return true;
    }


}
