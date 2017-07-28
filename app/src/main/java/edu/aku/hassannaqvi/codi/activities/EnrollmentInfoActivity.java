package edu.aku.hassannaqvi.codi.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.aku.hassannaqvi.codi.R;
import edu.aku.hassannaqvi.codi.core.AppMain;
import edu.aku.hassannaqvi.codi.core.DatabaseHelper;

public class EnrollmentInfoActivity extends AppCompatActivity {

    private static final String TAG = EnrollmentInfoActivity.class.getSimpleName();
    final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    @BindView(R.id.dssid)
    TextView dssid;
    @BindView(R.id.studyId)
    TextView studyId;
    @BindView(R.id.cen01)
    TextView cen01;
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
    TextView cen04;
    @BindView(R.id.cen05)
    TextView cen05;
    @BindView(R.id.cen06)
    TextView cen06;
    /*@BindView(R.id.cen07)
    EditText cen07;
    @BindView(R.id.cen07a)
    RadioGroup cen07a;*/
    @BindView(R.id.cen07w)
    EditText cen07w;
    @BindView(R.id.cen07m)
    EditText cen07m;
    @BindView(R.id.cen08)
    RadioGroup cen08;
    @BindView(R.id.cen08b)
    RadioButton cen08b;
    @BindView(R.id.cen08a)
    RadioButton cen08a;
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

    @BindView(R.id.fldGrpCheckChild)
    LinearLayout fldGrpCheckChild;
    /*@BindView(R.id.cen15)
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
*/
    String dateToday;

    DatabaseHelper db;

    //Boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment_info);
        ButterKnife.bind(this);

        dateToday = new SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis());

        dssid.setText(AppMain.fc.getDSSID());
        studyId.setText(AppMain.fc.getStudyID());
        cen01.setText(AppMain.enrollDate);
        cen04.setText(AppMain.fc.getChildName());
        cen05.setText(AppMain.motherName);
        cen06.setText(AppMain.dob);

        //cen01.setManager(getSupportFragmentManager());
        //cen01.setMaxDate(dateToday);
        //cen06.setManager(getSupportFragmentManager());

        //cen06.setMaxDate(dateToday);

        //double ageInDays = AppMain.ageInDays(cen06.); //

        /*db = new DatabaseHelper(this);

        dssid.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                flag = false;
                fldGrpCheckChild.setVisibility(View.GONE);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });*/

    }

    /*@OnClick(R.id.btn_Check) void onBtnCheckClick() {
        //TODO implement

        AppMain.getEnrollmentChild = db.getChildByDSS(dssid.getText().toString().toUpperCase());

        if(AppMain.getEnrollmentChild.size()!=0){

            if (AppMain.getEnrollmentChild.get(0).getArmSlc().equals("null")) {

                Toast.makeText(getApplicationContext(), "Children found", Toast.LENGTH_LONG).show();

                fldGrpCheckChild.setVisibility(View.VISIBLE);

                flag = true;
            }else {
                Toast.makeText(getApplicationContext(),"Children Already Randomized!",Toast.LENGTH_LONG).show();
            }
        }else {
             Toast.makeText(getApplicationContext(),"Children Not found",Toast.LENGTH_LONG).show();

            flag = false;
        }
    }
*/
//    @OnClick(R.id.btn_End)
//    void onBtnEndClick() {
//        Toast.makeText(this, "Processing This Section", Toast.LENGTH_SHORT).show();
//
//        if (ValidateForm()) {
//            try {
//                SaveDraft();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            if (UpdateDB()) {
//                finish();
//                Toast.makeText(this, "Starting Form Ending Section", Toast.LENGTH_SHORT).show();
//                Intent endSec = new Intent(this, EndingActivity.class);
//                endSec.putExtra("complete", false);
//                startActivity(endSec);
//            } else {
//                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//    }


    @OnClick(R.id.btn_Continue)
    void onBtnContinueClick() {

        //if (flag) {
            if (ValidateForm()) {
                try {
                    SaveDraft();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (UpdateDB()) {
                    Toast.makeText(this, "Starting Next Section", Toast.LENGTH_SHORT).show();

                    finish();

                    /*if(AppMain.formType.equals("V3") && AppMain.arm.equals("AB"))
                    {
                        startActivity(new Intent(this, VaccineActivity.class));
                    }else {
                        startActivity(new Intent(this, BloodSamplingActivity.class));
                    }
*/
                    startActivity(new Intent(this, ChildHealthAndBreastFeedActivity.class));


                } else {
                    Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
                }
            }
        /*}else {
            Toast.makeText(getApplicationContext(),"Click on CHECK button to check.",Toast.LENGTH_LONG).show();
        }*/
    }


    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for this Section", Toast.LENGTH_SHORT).show();

        SharedPreferences sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);

        //AppMain.fc = new FormsContract();

        //AppMain.fc.setDevicetagID(sharedPref.getString("tagName", null));
        //AppMain.fc.setDeviceID(Settings.Secure.getString(getApplicationContext().getContentResolver(),
        //      Settings.Secure.ANDROID_ID));

        //AppMain.fc.setFormDate((DateFormat.format("dd-MM-yyyy HH:mm", new Date())).toString());
        //AppMain.fc.setUser(AppMain.userName);




        /*Calendar cal = AppMain.getCalendarDate(AppMain.enrollDate);
        cal.add(Calendar.DAY_OF_MONTH, 28);
        AppMain.fc.setNextApp((new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime()) + " " + new SimpleDateFormat("HH:mm").format(System.currentTimeMillis())));
*/
        JSONObject sInfo = new JSONObject();

        //sInfo.put("dssid", AppMain.elc.getDSSID());
        //sInfo.put("studyId", AppMain.elc.getStudyID());
        sInfo.put("cen01", AppMain.enrollDate);
        sInfo.put("cen02", "1");
        sInfo.put("cen03", "1");
        // sInfo.put("cen05", AppMain.motherName);
        //sInfo.put("cen06", AppMain.dob);
        sInfo.put("cen07w", cen07w.getText().toString());
        sInfo.put("cen07m", cen07m.getText().toString());
        //sInfo.put("cen07a", cen07w.isChecked() ? "1" : cen07m.isChecked() ? "2" : "0");
        sInfo.put("cen08", cen08a.isChecked() ? "1" : cen08b.isChecked() ? "2" : "0");
        sInfo.put("cen09", cen09.getText().toString());
        sInfo.put("cenfp", cenfp.getText().toString());
        sInfo.put("cenmp", cenmp.getText().toString());
        sInfo.put("cenac", cenac.getText().toString());
        sInfo.put("cen10", cen10a.isChecked() ? "1" : cen10b.isChecked() ? "2" : cen10c.isChecked() ? "3"
                : cen10d.isChecked() ? "4" : cen10e.isChecked() ? "5" : cen10f.isChecked() ? "6"
                : cen1099.isChecked() ? "99" : "0");
        sInfo.put("cen11", cen11a.isChecked() ? "1" : cen11b.isChecked() ? "2" : cen11c.isChecked() ? "3"
                : cen11d.isChecked() ? "4" : cen11e.isChecked() ? "5" : cen11f.isChecked() ? "6" : "0");
        sInfo.put("cen12", cen12a.isChecked() ? "1" : cen12b.isChecked() ? "2" : cen12c.isChecked() ? "3"
                : cen12d.isChecked() ? "4" : "0");
        sInfo.put("cen13", cen13.getText().toString());
        sInfo.put("cen14", cen14.getText().toString());


        AppMain.fc.setsInfo(String.valueOf(sInfo));


        //setGPS();

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }

    private boolean UpdateDB() {
        DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateSInfo();
        //AppMain.fc.set_ID(String.valueOf(updcount));

        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();

            /*AppMain.fc.set_UID(
                    (AppMain.fc.getDeviceID() + AppMain.fc.get_ID()));
            db.updateFormID();*/
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    public boolean ValidateForm() {


        /*if (cen01.getText().toString().isEmpty()) {
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
*/
        if (cen07w.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen07a), Toast.LENGTH_SHORT).show();
            cen07w.setError("This data is Required!");

            Log.i(TAG, "cen07a: This Data is Required!");
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


        if (AppMain.selectecAgeGrp == 1) {
            if (Integer.valueOf(cen07w.getText().toString()) != 14
                    || Integer.valueOf(cen07m.getText().toString()) > 0)
            {

                Toast.makeText(this, "ERROR(invalid)" + getString(R.string.cen07a), Toast.LENGTH_SHORT).show();
                cen07w.setError("Age should be 14 weeks");
                cen07m.setError("Check weeks again");

                Log.i(TAG, "cen07w: Age should be 14 weeks");

                return false;
            } else {
                cen07w.setError(null);
                cen07m.setError(null);
            }
        }


        if (AppMain.selectecAgeGrp == 2) {
            if (Integer.valueOf(cen07m.getText().toString()) != 9
                    || Integer.valueOf(cen07w.getText().toString()) > 3) {
                Toast.makeText(this, "ERROR(invalid)" + getString(R.string.cen07b), Toast.LENGTH_SHORT).show();
                cen07m.setError("Age should be 9 months");
                cen07w.setError("Check weeks again");

                Log.i(TAG, "cen07m: Age should be 9 months");
                return false;
            } else {
                cen07m.setError(null);
                cen07w.setError(null);
            }
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

        if (cenfp.getText().toString().isEmpty() && cenmp.getText().toString().isEmpty() && cenac.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cenfp), Toast.LENGTH_SHORT).show();
            cenfp.setError("This data is Required!");

            Log.i(TAG, "cenfp: This Data is Required!");
            return false;
        } else {
            cenfp.setError(null);
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

        if (Integer.valueOf(cen13.getText().toString()) < 1) {
            Toast.makeText(this, "ERROR(invalid)" + getString(R.string.cen13), Toast.LENGTH_SHORT).show();
            cen13.setError("Zero not allowed");

            Log.i(TAG, "cen13: zero not allowed");
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

        if (Integer.valueOf(cen14.getText().toString()) >= Integer.valueOf(cen13.getText().toString())) {
            Toast.makeText(this, "ERROR(invalid)" + getString(R.string.cen14), Toast.LENGTH_SHORT).show();
            cen14.setError("Can not be greater than total members of house!");

            Log.i(TAG, "cen14: Can not be greater than total members of house!");
            return false;
        } else {
            cen14.setError(null);
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

   /* public Calendar getCalendarDate(String value) {
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

    public String convertDateFormat(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date d = sdf.parse(dateStr);
            return new SimpleDateFormat("dd/MM/yyyy").format(d);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        return "";
    }
*/




    @Override
    public void onBackPressed() {
//        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
        super.onBackPressed();
    }




}
