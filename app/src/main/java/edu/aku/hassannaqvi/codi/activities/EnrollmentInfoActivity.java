package edu.aku.hassannaqvi.codi.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.aku.hassannaqvi.codi.R;
import edu.aku.hassannaqvi.codi.contracts.EnrollmentContract;
import edu.aku.hassannaqvi.codi.core.DatabaseHelper;
import edu.aku.hassannaqvi.codi.core.MainApp;
import io.blackbox_vision.datetimepickeredittext.view.DatePickerInputEditText;

public class EnrollmentInfoActivity extends AppCompatActivity {

    private static final String TAG = EnrollmentInfoActivity.class.getSimpleName();
    final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    @BindView(R.id.dssid)
    TextView dssid;
    @BindView(R.id.studyId)
    TextView studyId;
    @BindView(R.id.cen01)
    DatePickerInputEditText cen01;
    @BindView(R.id.cen02)
    RadioGroup cen02;
    @BindView(R.id.cen02a)
    RadioButton cen02a;
    @BindView(R.id.cen02b)
    RadioButton cen02b;
    @BindView(R.id.cen03)
    RadioGroup cen03;
    @BindView(R.id.cen03a)
    RadioButton cen03a;
    @BindView(R.id.cen03b)
    RadioButton cen03b;
    @BindView(R.id.cen04)
    EditText cen04;
    @BindView(R.id.cen05)
    EditText cen05;
    @BindView(R.id.cen06)
    DatePickerInputEditText cen06;
    @BindView(R.id.cen07w)
    EditText cen07w;
    @BindView(R.id.cen07m)
    EditText cen07m;
    @BindView(R.id.cen08)
    RadioGroup cen08;
    @BindView(R.id.cen08a)
    RadioButton cen08a;
    @BindView(R.id.cen08b)
    RadioButton cen08b;
    @BindView(R.id.cen09)
    EditText cen09;
    @BindView(R.id.cenfp)
    EditText cenfp;
    @BindView(R.id.cenmp)
    EditText cenmp;
    @BindView(R.id.cenac)
    EditText cenac;
    @BindView(R.id.cen10)
    RadioGroup cen10;
    @BindView(R.id.cen10a)
    RadioButton cen10a;
    @BindView(R.id.cen10b)
    RadioButton cen10b;
    @BindView(R.id.cen10c)
    RadioButton cen10c;
    @BindView(R.id.cen10d)
    RadioButton cen10d;
    @BindView(R.id.cen10e)
    RadioButton cen10e;
    @BindView(R.id.cen10f)
    RadioButton cen10f;
    @BindView(R.id.cen1099)
    RadioButton cen1099;
    @BindView(R.id.cen11)
    RadioGroup cen11;
    @BindView(R.id.cen11a)
    RadioButton cen11a;
    @BindView(R.id.cen11b)
    RadioButton cen11b;
    @BindView(R.id.cen11c)
    RadioButton cen11c;
    @BindView(R.id.cen11d)
    RadioButton cen11d;
    @BindView(R.id.cen11e)
    RadioButton cen11e;
    @BindView(R.id.cen11f)
    RadioButton cen11f;
    @BindView(R.id.cen1199)
    RadioButton cen1199;
    @BindView(R.id.cen12)
    RadioGroup cen12;
    @BindView(R.id.cen12a)
    RadioButton cen12a;
    @BindView(R.id.cen12b)
    RadioButton cen12b;
    @BindView(R.id.cen12c)
    RadioButton cen12c;
    @BindView(R.id.cen12d)
    RadioButton cen12d;
    @BindView(R.id.cen13)
    EditText cen13;
    @BindView(R.id.cen14)
    EditText cen14;
    @BindView(R.id.cen15)
    RadioGroup cen15;
    @BindView(R.id.cen15a)
    RadioButton cen15a;
    @BindView(R.id.cen15b)
    RadioButton cen15b;
    @BindView(R.id.cen16)
    RadioGroup cen16;
    @BindView(R.id.cen16a)
    RadioButton cen16a;
    @BindView(R.id.cen16b)
    RadioButton cen16b;
    @BindView(R.id.cen16c)
    RadioButton cen16c;
    @BindView(R.id.fldGrpcen17)
    LinearLayout fldGrpcen17;
    @BindView(R.id.cen17)
    RadioGroup cen17;
    @BindView(R.id.cen17a)
    RadioButton cen17a;
    @BindView(R.id.cen17b)
    RadioButton cen17b;
    @BindView(R.id.cen1788)
    RadioButton cen1788;
    @BindView(R.id.cen1788x)
    EditText cen1788x;

    String dateToday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment_info);
        ButterKnife.bind(this);
        dateToday = new SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis());

        cen01.setManager(getSupportFragmentManager());
        cen01.setMaxDate(dateToday);
        cen06.setManager(getSupportFragmentManager());
        cen06.setMaxDate(dateToday);

        cen1788.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cen1788x.setVisibility(View.VISIBLE);
                } else {
                    cen1788x.setVisibility(View.GONE);
                    cen1788x.setText(null);
                }
            }
        });

        //============= Partial Breast feeding skip pattern ===========
        cen16.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (cen16a.isChecked() || cen16b.isChecked()) {
                    fldGrpcen17.setVisibility(View.VISIBLE);
                } else {
                    fldGrpcen17.setVisibility(View.GONE);
                    cen17.clearCheck();
                    cen1788x.setText(null);
                }
            }
        });



    }

    @OnClick(R.id.btn_End)
    void onBtnEndClick() {
        Toast.makeText(this, "Processing This Section", Toast.LENGTH_SHORT).show();

        if (ValidateForm()) {
            try {
                SaveDraft();
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

        if (ValidateForm()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                Toast.makeText(this, "Starting Next Section", Toast.LENGTH_SHORT).show();

                finish();


                startActivity(new Intent(this, BloodSamplingActivity.class));

            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }

    }


    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for this Section", Toast.LENGTH_SHORT).show();

        SharedPreferences sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);

        MainApp.enc = new EnrollmentContract();

        MainApp.enc.setDevicetagID(sharedPref.getString("tagName", null));
        MainApp.enc.setDeviceID(Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID));
        MainApp.enc.setFormDate((DateFormat.format("dd-MM-yyyy HH:mm", new Date())).toString());
        MainApp.enc.setUser(MainApp.userName);

        MainApp.enrollDate = cen01.getText().toString();
        Calendar cal = getCalendarDate(MainApp.enrollDate);
        cal.add(Calendar.DAY_OF_MONTH, 28);
        MainApp.enc.setNextApp((new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime()) + " " + new SimpleDateFormat("HH:mm").format(System.currentTimeMillis())));

        JSONObject sEnInfo = new JSONObject();

        sEnInfo.put("dssid", dssid.getText().toString());
        sEnInfo.put("studyId", studyId.getText().toString());
        sEnInfo.put("cen01", cen01.getText().toString());

        sEnInfo.put("cen02", cen02a.isChecked() ? "1" : cen02b.isChecked() ? "2" : "0");
        sEnInfo.put("cen03", cen03a.isChecked() ? "1" : cen03b.isChecked() ? "2" : "0");
        sEnInfo.put("cen04", cen04.getText().toString());
        sEnInfo.put("cen05", cen05.getText().toString());
        sEnInfo.put("cen06", cen06.getText().toString());
        sEnInfo.put("cen07w", cen07w.getText().toString());
        sEnInfo.put("cen07m", cen07m.getText().toString());
        sEnInfo.put("cen08", cen08a.isChecked() ? "1" : cen08b.isChecked() ? "2" : "0");
        sEnInfo.put("cen09", cen09.getText().toString());
        sEnInfo.put("cenfp", cenfp.getText().toString());
        sEnInfo.put("cenmp", cenmp.getText().toString());
        sEnInfo.put("cenac", cenac.getText().toString());
        sEnInfo.put("cen10", cen10a.isChecked() ? "1" : cen10b.isChecked() ? "2" : cen10c.isChecked() ? "3" : cen10d.isChecked() ? "4" : cen10e.isChecked() ? "5" : cen10f.isChecked() ? "6" : cen1099.isChecked() ? "99" : "0");
        sEnInfo.put("cen11", cen11a.isChecked() ? "1" : cen11b.isChecked() ? "2" : cen11c.isChecked() ? "3" : cen11d.isChecked() ? "4" : cen11e.isChecked() ? "5" : cen11f.isChecked() ? "6" : "0");
        sEnInfo.put("cen12", cen12a.isChecked() ? "1" : cen12b.isChecked() ? "2" : cen12c.isChecked() ? "3" : cen12d.isChecked() ? "4" : "0");
        sEnInfo.put("cen13", cen13.getText().toString());
        sEnInfo.put("cen14", cen14.getText().toString());
        sEnInfo.put("cen15", cen15a.isChecked() ? "1" : cen15b.isChecked() ? "2" : "0");
        sEnInfo.put("cen16", cen16a.isChecked() ? "1" : cen16b.isChecked() ? "2" : cen16c.isChecked() ? "3" : "0");
        sEnInfo.put("cen17", cen17a.isChecked() ? "1" : cen17b.isChecked() ? "2" : cen1788.isChecked() ? "3" : "0");
        sEnInfo.put("cen1788x", cen1788x.getText().toString());


        MainApp.enc.setsEnInfo(String.valueOf(sEnInfo));

        setGPS();

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }

    private boolean UpdateDB() {
        DatabaseHelper db = new DatabaseHelper(this);

       /* Long updcount = db.addEnrollment(MainApp.enc);
        MainApp.enc.set_ID(String.valueOf(updcount));

        if (updcount != 0) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();

            MainApp.enc.set_UID(
                    (MainApp.enc.getDeviceID() + MainApp.enc.get_ID()));

            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
*/
        return true;
    }

    public boolean ValidateForm() {


        if (cen01.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen01), Toast.LENGTH_SHORT).show();
            cen01.setError("This data is Required!");

            Log.i(TAG, "cen01: This Data is Required!");
            return false;
        } else {
            cen01.setError(null);
        }

        if (cen02.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen02), Toast.LENGTH_SHORT).show();
            cen02a.setError("This data is Required!");

            Log.i(TAG, "cen02: This Data is Required!");
            return false;
        } else {
            cen02a.setError(null);
        }

        if (cen03.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen03), Toast.LENGTH_SHORT).show();
            cen03a.setError("This data is Required!");

            Log.i(TAG, "cen03: This Data is Required!");
            return false;
        } else {
            cen03a.setError(null);
        }

        if (cen04.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen04), Toast.LENGTH_SHORT).show();
            cen04.setError("This data is Required!");

            Log.i(TAG, "cen04: This Data is Required!");
            return false;
        } else {
            cen04.setError(null);
        }

        if (cen05.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen05), Toast.LENGTH_SHORT).show();
            cen05.setError("This data is Required!");

            Log.i(TAG, "cen05: This Data is Required!");
            return false;
        } else {
            cen05.setError(null);
        }

        if (cen06.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen06), Toast.LENGTH_SHORT).show();
            cen06.setError("This data is Required!");

            Log.i(TAG, "cen06: This Data is Required!");
            return false;
        } else {
            cen06.setError(null);
        }

        if (cen07w.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen07a), Toast.LENGTH_SHORT).show();
            cen07w.setError("This data is Required!");

            Log.i(TAG, "cen07w: This Data is Required!");
            return false;
        } else {
            cen07w.setError(null);
        }

        if (cen07m.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen07b), Toast.LENGTH_SHORT).show();
            cen07m.setError("This data is Required!");

            Log.i(TAG, "cen07m: This Data is Required!");
            return false;
        } else {
            cen07m.setError(null);
        }


        if (cen08.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen08), Toast.LENGTH_SHORT).show();
            cen08a.setError("This data is Required!");

            Log.i(TAG, "cen08: This Data is Required!");
            return false;
        } else {
            cen08a.setError(null);
        }

        if (cen09.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen09), Toast.LENGTH_SHORT).show();
            cen09.setError("This data is Required!");

            Log.i(TAG, "cen09: This Data is Required!");
            return false;
        } else {
            cen09.setError(null);
        }

        if (cen10.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen10), Toast.LENGTH_SHORT).show();
            cen10a.setError("This data is Required!");

            Log.i(TAG, "cen10: This Data is Required!");
            return false;
        } else {
            cen10a.setError(null);
        }

        if (cen11.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen11), Toast.LENGTH_SHORT).show();
            cen11a.setError("This data is Required!");

            Log.i(TAG, "cen11: This Data is Required!");
            return false;
        } else {
            cen11a.setError(null);
        }

        if (cen12.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen12), Toast.LENGTH_SHORT).show();
            cen12a.setError("This data is Required!");

            Log.i(TAG, "cen12: This Data is Required!");
            return false;
        } else {
            cen12a.setError(null);
        }

        if (cen13.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen13), Toast.LENGTH_SHORT).show();
            cen13.setError("This data is Required!");

            Log.i(TAG, "cen13: This Data is Required!");
            return false;
        } else {
            cen13.setError(null);
        }

        if (cen14.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen14), Toast.LENGTH_SHORT).show();
            cen14.setError("This data is Required!");

            Log.i(TAG, "cen14: This Data is Required!");
            return false;
        } else {
            cen14.setError(null);
        }

        if (cen15.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen15), Toast.LENGTH_SHORT).show();
            cen15a.setError("This data is Required!");

            Log.i(TAG, "cen15: This Data is Required!");
            return false;
        } else {
            cen15a.setError(null);
        }

        if (cen16.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen16), Toast.LENGTH_SHORT).show();
            cen16a.setError("This data is Required!");

            Log.i(TAG, "cen16: This Data is Required!");
            return false;
        } else {
            cen16a.setError(null);
        }

        if (cen16a.isChecked() || cen16b.isChecked()) {
            if (cen17.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen17), Toast.LENGTH_SHORT).show();
                cen17a.setError("This data is Required!");

                Log.i(TAG, "cen17: This Data is Required!");
                return false;
            } else {
                cen17a.setError(null);
            }

            if (cen1788.isChecked() && cen1788x.getText().toString().isEmpty()) {
                Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen17) + " - " + getString(R.string.others), Toast.LENGTH_SHORT).show();
                cen1788x.setError("This data is Required!");

                Log.i(TAG, "cen1788x: This Data is Required!");
                return false;
            } else {
                cen1788x.setError(null);
            }
        }


        return true;
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

            MainApp.enc.setGpsLat(GPSPref.getString("Latitude", "0"));
            MainApp.enc.setGpsLng(GPSPref.getString("Longitude", "0"));
            MainApp.enc.setGpsAcc(GPSPref.getString("Accuracy", "0"));
//            AppMain.fc.setGpsTime(GPSPref.getString(date, "0")); // Timestamp is converted to date above
            MainApp.enc.setGpsDT(date); // Timestamp is converted to date above

            Toast.makeText(this, "GPS set", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e(TAG, "setGPS: " + e.getMessage());
        }

    }

    public Calendar getCalendarDate(String value) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = sdf.parse(value);
            calendar.setTime(date);
            return calendar;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
    }




}