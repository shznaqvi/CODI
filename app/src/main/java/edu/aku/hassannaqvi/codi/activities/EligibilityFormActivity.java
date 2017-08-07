package edu.aku.hassannaqvi.codi.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import edu.aku.hassannaqvi.codi.R;
import edu.aku.hassannaqvi.codi.contracts.FormsContract;
import edu.aku.hassannaqvi.codi.core.AppMain;
import edu.aku.hassannaqvi.codi.core.DatabaseHelper;
import io.blackbox_vision.datetimepickeredittext.view.DatePickerInputEditText;


public class EligibilityFormActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, TextWatcher {

    private static final String TAG = EligibilityFormActivity.class.getSimpleName();

    String dtToday = new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime());


    @BindView(R.id.dssID)
    EditText dssID;
    @BindView(R.id.celcn)
    EditText celcn;
    @BindView(R.id.celdob)
    DatePickerInputEditText celdob;
    @BindView(R.id.celmn)
    EditText celmn;
    @BindView(R.id.cel01)
    RadioGroup cel01;
    @BindView(R.id.cel01a)
    RadioButton cel01a;
    @BindView(R.id.cel01b)
    RadioButton cel01b;
    @BindView(R.id.fldGrpcel02)
    LinearLayout fldGrpcel02;
    @BindView(R.id.cel02)
    RadioGroup cel02;
    @BindView(R.id.cel02a)
    RadioButton cel02a;
    @BindView(R.id.cel02b)
    RadioButton cel02b;
    @BindView(R.id.fldGrp14wks)
    LinearLayout fldGrp14wks;
    @BindView(R.id.cel03)
    RadioGroup cel03;
    @BindView(R.id.cel03a)
    RadioButton cel03a;
    @BindView(R.id.cel03b)
    RadioButton cel03b;
    @BindView(R.id.fldGrp9m)
    LinearLayout fldGrp9m;
    @BindView(R.id.cel04)
    RadioGroup cel04;
    @BindView(R.id.cel04a)
    RadioButton cel04a;
    @BindView(R.id.cel04b)
    RadioButton cel04b;
    @BindView(R.id.cel05)
    RadioGroup cel05;
    @BindView(R.id.cel05a)
    RadioButton cel05a;
    @BindView(R.id.cel05b)
    RadioButton cel05b;
    @BindView(R.id.cel06)
    RadioGroup cel06;
    @BindView(R.id.cel06a)
    RadioButton cel06a;
    @BindView(R.id.cel06b)
    RadioButton cel06b;
    @BindView(R.id.cel07)
    RadioGroup cel07;
    @BindView(R.id.cel07a)
    RadioButton cel07a;
    @BindView(R.id.cel07b)
    RadioButton cel07b;
    @BindView(R.id.fldGrpcelEligible)
    LinearLayout fldGrpcelEligible;
    /*  @BindView(R.id.celee)
      RadioGroup celee;
      @BindView(R.id.celeea)
      RadioButton celeea;
      @BindView(R.id.celeeb)
      RadioButton celeeb;*/
    @BindView(R.id.celstdid)
    EditText celstdid;
    @BindView(R.id.celdoe)
    TextView celdoe;
    @BindView(R.id.celner)
    EditText celner;
    @BindView(R.id.fldGrprsn)
    LinearLayout fldGrprsn;

    @BindViews({R.id.celdob})
    List<DatePickerInputEditText> dates;
    @BindViews({R.id.cel03, R.id.cel04, R.id.cel05, R.id.cel06, R.id.cel07, R.id.cel01})
    List<RadioGroup> celEligible;
    @BindViews({R.id.cel05a, R.id.cel06a, R.id.cel07a, R.id.cel01a})
    List<RadioButton> celEligibleYes;
    @BindViews({R.id.cel05b, R.id.cel06b, R.id.cel07b, R.id.cel01b})
    List<RadioButton> celEligibleNo;
    String date14Weeks;
    String mindate14Weeks;
    String date9Months;
    String mindate9Months;
    Boolean flag = false;

    DatabaseHelper db;
    Boolean check = false;
    @BindView(R.id.fldGrpChild)
    LinearLayout fldGrpChild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eligibility_form);
        ButterKnife.bind(this);

        String dateToday = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
        date14Weeks = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTimeInMillis() - ((AppMain.MILLISECONDS_IN_14_WEEKS)));
        mindate14Weeks = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTimeInMillis() - ((AppMain.MILLISECONDS_IN_14_WEEKS1)));
        date9Months = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTimeInMillis() - ((AppMain.MILLISECONDS_IN_9_MONTH)));
        mindate9Months = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTimeInMillis() - ((AppMain.MILLISECONDS_IN_9_MONTH1)));


        for (DatePickerInputEditText de : dates) {
            de.setManager(getSupportFragmentManager());
        }
        AppMain.enrollDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(System.currentTimeMillis());


        //================== Q7 Skip Pattern ===========
        for (RadioGroup rg : celEligible) {
            rg.setOnCheckedChangeListener(this);
        }



        cel02.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (cel02b.isChecked()) {
                    //celstdid.setOnKeyListener(EligibilityFormActivity.this);
                    fldGrp9m.setVisibility(View.VISIBLE);
                    fldGrp14wks.setVisibility(View.GONE);
                    cel04.clearCheck();
                } else {
                    fldGrp9m.setVisibility(View.GONE);
                    fldGrp14wks.setVisibility(View.VISIBLE);
                    cel03.clearCheck();
                }
            }
        });

        db = new DatabaseHelper(this);


    }



    @OnTextChanged(value = R.id.dssID,
            callback = OnTextChanged.Callback.TEXT_CHANGED)
    void afterDssIDInput(Editable editable) {
        check = false;
        fldGrpChild.setVisibility(View.GONE);
    }


    @OnClick(R.id.btn_check)
    void onBtnCheckClick() {


        AppMain.getEnrollmentChild = db.getChildByDSS(dssID.getText().toString().toUpperCase());

        if (AppMain.getEnrollmentChild.size() != 0) {

            if (AppMain.getEnrollmentChild.get(0).getArmSlc().equals("null")) {

                Toast.makeText(getApplicationContext(), "Children found", Toast.LENGTH_LONG).show();

                fldGrpChild.setVisibility(View.VISIBLE);

                check = true;

                if (AppMain.getEnrollmentChild.get(0).getArmGrp().equals("AB")) {
                    celdob.setMinDate(mindate14Weeks);
                    celdob.setMaxDate(date14Weeks);
                    celstdid.setText("CODI14W-");
                } else {
                    celdob.setMinDate(mindate9Months);
                    celdob.setMaxDate(date9Months);
                    celstdid.setText("CODI09M-");
                }
            } else {
                Toast.makeText(getApplicationContext(), "Children Already Randomized!", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Children Not found", Toast.LENGTH_LONG).show();

            check = false;
        }


    }


    @OnClick(R.id.btnNext)
    void onBtnNextClick() {
        if (ValidateForm()) {
            try {
                SaveDraft();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                Toast.makeText(this, "Starting Next Section", Toast.LENGTH_SHORT).show();

                //finish();

                if (flag) {
                    startActivity(new Intent(this, EnrollmentInfoActivity.class));
                } else {
                    Intent intent = new Intent(this, EndingActivity.class);
                    intent.putExtra("complete", true);
                    startActivity(intent);

                }

/*
                startActivity(new Intent(this, SectionBActivity.class));
*/
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
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

    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for this Section", Toast.LENGTH_SHORT).show();

        SharedPreferences sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);

        AppMain.fc = new FormsContract();

        AppMain.fc.setDevicetagID(sharedPref.getString("tagName", null));
        AppMain.fc.setFormDate(dtToday);
        AppMain.fc.setUser(AppMain.userName);
        AppMain.fc.setDeviceID(Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID));
        AppMain.fc.setDSSID(dssID.getText().toString());
        AppMain.fc.setChildName(celcn.getText().toString());
        AppMain.fc.setStudyID(celstdid.getText().toString());
        AppMain.motherName = celmn.getText().toString();
        AppMain.dob = celdob.getText().toString();
        AppMain.fc.setFormType("V1");

        JSONObject sel = new JSONObject();


        //sel.put("celcn", celcn.getText().toString());
        sel.put("celdob", celdob.getText().toString());
        sel.put("celmn", celmn.getText().toString());
        sel.put("cel01", cel01a.isChecked() ? "1" : cel01b.isChecked() ? "2" : "0");
        sel.put("cel02", cel02a.isChecked() ? "1" : cel02b.isChecked() ? "2" : "0");
        sel.put("cel03", cel03a.isChecked() ? "1" : cel03b.isChecked() ? "2" : "0");
        sel.put("cel04", cel04a.isChecked() ? "1" : cel04b.isChecked() ? "2" : "0");
        sel.put("cel05", cel05a.isChecked() ? "1" : cel05b.isChecked() ? "2" : "0");
        sel.put("cel06", cel06a.isChecked() ? "1" : cel06b.isChecked() ? "2" : "0");
        sel.put("cel07", cel07a.isChecked() ? "1" : cel07b.isChecked() ? "2" : "0");
        sel.put("celee", isYes() ? "1" : "2");
        //sel.put("celstdid", celstdid.getText().toString());
        sel.put("celdoe", AppMain.enrollDate);
        sel.put("celner", celner.getText().toString());

        AppMain.selectecAgeGrp = cel02.indexOfChild(findViewById(cel02.getCheckedRadioButtonId())) + 1;


        AppMain.fc.setsEl(String.valueOf(sel));


        setGPS();

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }

    public boolean ValidateForm() {


        // =================== celcn ====================
        if (celcn.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.celcn), Toast.LENGTH_SHORT).show();
            celcn.setError("This data is required");
            Log.d(TAG, "celcn:empty ");
            return false;
        } else {
            celcn.setError(null);
        }

        // =================== dob ====================
        if (celdob.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.celdob), Toast.LENGTH_SHORT).show();
            celdob.setError("This data is required");
            Log.d(TAG, "celdob:empty ");
            return false;
        } else {
            celdob.setError(null);
        }


        // =================== celmn ====================
        if (celmn.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.celmn), Toast.LENGTH_SHORT).show();
            celmn.setError("This data is required");
            Log.d(TAG, "celmn:empty ");
            return false;
        } else {
            celmn.setError(null);
        }

        // =================== cel01 ====================
        if (cel01.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cel01), Toast.LENGTH_SHORT).show();
            cel01b.setError("This data is Required!");
            Log.i(TAG, "cel01: This Data is Required!");
            return false;
        } else {
            cel01b.setError(null);
        }


            // =================== cel02 ====================
            if (cel02.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cel02), Toast.LENGTH_SHORT).show();
                cel02b.setError("This data is Required!");
                Log.i(TAG, "cel02: This Data is Required!");
                return false;
            } else {
                cel02b.setError(null);
            }


        if (cel02a.isChecked()) {
            Calendar selectedDate = AppMain.getCalendarDate(celdob.getText().toString());
            Calendar minDate = Calendar.getInstance();
            minDate.add(Calendar.DAY_OF_YEAR, -105);

            if (selectedDate.before(minDate)) {
                Toast.makeText(this, "ERROR(invalid)" + getString(R.string.cel02) + " " + getString(R.string.celdob), Toast.LENGTH_SHORT).show();
                celdob.setError("Wrong date of Birth... please check again");
                cel02a.setError("Check age and date of birth");
                Log.i(TAG, "cel02: invalid date of birth");
                return false;
            } else {
                celdob.setError(null);
                cel02a.setError(null);
            }

                // =================== cel03 ====================
                if (cel03.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cel03), Toast.LENGTH_SHORT).show();
                    cel03b.setError("This data is Required!");
                    Log.i(TAG, "cel03: This Data is Required!");
                    return false;
                } else {
                    cel03b.setError(null);
                }
            }

            if (cel02b.isChecked()) {
                Calendar selectedDate = AppMain.getCalendarDate(celdob.getText().toString());
                Calendar minDate = Calendar.getInstance();
                minDate.add(Calendar.DAY_OF_YEAR, -273);

                if (selectedDate.after(minDate)) {
                    Toast.makeText(this, "ERROR(invalid)" + getString(R.string.cel02) + " " + getString(R.string.celdob), Toast.LENGTH_SHORT).show();
                    celdob.setError("Wrong date of Birth.. Please check again");
                    cel02b.setError("Check age and date of birth");
                    Log.i(TAG, "cel02: invalid date of birth");
                    return false;
                } else {
                    celdob.setError(null);
                    cel02a.setError(null);
                }

                // =================== cel04 ====================
                if (cel04.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cel04), Toast.LENGTH_SHORT).show();
                    cel04b.setError("This data is Required!");
                    Log.i(TAG, "cel04: This Data is Required!");
                    return false;
                } else {
                    cel04b.setError(null);
                }
            }

            // =================== cel05 ====================
            if (cel05.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cel05), Toast.LENGTH_SHORT).show();
                cel05b.setError("This data is Required!");
                Log.i(TAG, "cel05: This Data is Required!");
                return false;
            } else {
                cel05b.setError(null);
            }

            // =================== cel06 ====================
            if (cel06.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cel06), Toast.LENGTH_SHORT).show();
                cel06b.setError("This data is Required!");
                Log.i(TAG, "cel06 : This Data is Required!");
                return false;
            } else {
                cel06b.setError(null);
            }

            // =================== cel07 ====================
            if (cel07.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cel07), Toast.LENGTH_SHORT).show();
                cel07b.setError("This data is Required!");
                Log.i(TAG, "cel07 : This Data is Required!");
                return false;
            } else {
                cel07b.setError(null);
            }

            if ((isYes() && cel03a.isChecked()) || (isYes() && cel04a.isChecked())) {
                // =================== celee ====================
               /* if (celee.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(this, "ERROR(Empty)" + getString(R.string.celee), Toast.LENGTH_SHORT).show();
                    celeeb.setError("This data is Required!");
                    Log.i(TAG, "celee: This Data is Required!");
                    return false;
                } else {
                    celeeb.setError(null);
                }*/

                // =================== celstdid ====================
                if (celstdid.getText().toString().isEmpty()) {
                    Toast.makeText(this, "ERROR(Empty)" + getString(R.string.celstdid), Toast.LENGTH_SHORT).show();
                    celstdid.setError("This data is required");
                    Log.d(TAG, "celstdid:empty ");
                    return false;
                } else {
                    celstdid.setError(null);
                }

                if (!celstdid.getText().toString().substring(0, 4).contains("CODI")) {
                    Toast.makeText(this, "ERROR(invalid)" + getString(R.string.celstdid), Toast.LENGTH_SHORT).show();
                    celstdid.setError("Wrong Study ID.. Please correct \r\n\t - must contain word 'CODI'");
                    Log.d(TAG, "celstdid:invalid ");
                    return false;
                } else {
                    celstdid.setError(null);
                }


                if (celstdid.getText().length() < 12 || !celstdid.getText().toString().contains("-")) {
                    Toast.makeText(this, "ERROR(invalid)" + getString(R.string.celstdid), Toast.LENGTH_SHORT).show();
                    celstdid.setError("Wrong Study ID.. Please correct \r\n\t - must be 12 characters \r\n\t - must contain '-'");
                    Log.d(TAG, "celstdid:invalid ");
                    return false;
                } else {
                    celstdid.setError(null);
                }

                String celstdidtxt = celstdid.getText().toString().substring(4);

                if (cel02a.isChecked() && (!celstdidtxt.contains("14W"))) {
                    Toast.makeText(this, "ERROR(invalid)" + getString(R.string.celstdid) + " - " + getString(R.string.cel02), Toast.LENGTH_SHORT).show();
                    celstdid.setError("Study ID does not match age of child");
                    cel02a.setError("Study ID does not match age of child");
                    Log.d(TAG, "celstdid:invalid ");
                    return false;
                } else {
                    celstdid.setError(null);
                    cel02a.setError(null);
                }

                if (cel02b.isChecked() && (!celstdidtxt.contains("09M"))) {
                    Toast.makeText(this, "ERROR(invalid)" + getString(R.string.celstdid) + " - " + getString(R.string.cel02), Toast.LENGTH_SHORT).show();
                    celstdid.setError("Study ID does not match age of child");
                    cel02a.setError("Study ID does not match age of child");
                    Log.d(TAG, "celstdid:invalid ");
                    return false;
                } else {
                    celstdid.setError(null);
                    cel02a.setError(null);

                }

                String[] codiNo = celstdidtxt.split("-");

                if (celstdidtxt.contains("14W") && ((Integer.valueOf(codiNo[1].substring(0, 1)) > 0)
                        || (Integer.valueOf(codiNo[1].substring(1)) > 300))) {
                    Toast.makeText(this, "ERROR(invalid)" + getString(R.string.celstdid) + " - " + getString(R.string.cel02), Toast.LENGTH_SHORT).show();
                    celstdid.setError("Please check age and study id again ");
                    cel02a.setError("Please check age and study id again");
                    Log.d(TAG, "celstdid:invalid ");
                    return false;
                } else {
                    celstdid.setError(null);
                    cel02a.setError(null);
                }

                if (celstdidtxt.contains("09M") && ((Integer.valueOf(codiNo[0].substring(0, 1)) > 0)
                        || (Integer.valueOf(codiNo[1].substring(1)) < 300))) {
                    Toast.makeText(this, "ERROR(invalid)" + getString(R.string.celstdid) + " - " + getString(R.string.cel02), Toast.LENGTH_SHORT).show();
                    celstdid.setError("Invalid Study ID");
                    cel02b.setError("Please check age and study id again");
                    Log.d(TAG, "celstdid:invalid ");
                    return false;
                } else {
                    celstdid.setError(null);
                    cel02b.setError(null);
                }

                // =================== doe ====================
                /*if (celdoe.getText().toString().isEmpty()) {
                    Toast.makeText(this, "ERROR(Empty)" + getString(R.string.celdoe), Toast.LENGTH_SHORT).show();
                    celdoe.setError("This data is required");
                    Log.d(TAG, "celdoe:empty ");
                    return false;
                } else {
                    celdoe.setError(null);
                }*/
            } else {
                // =================== celner ====================
                if (celner.getText().toString().isEmpty()) {
                    Toast.makeText(this, "ERROR (Empty)" + getString(R.string.celner), Toast.LENGTH_SHORT).show();
                    celner.setError("this data is required");
                    Log.d(TAG, "celner : empty ");
                    return false;
                } else {
                    celner.setError(null);
                }
            }

        return true;
    }


    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        if ((isYes() && cel03a.isChecked())) {
            // Show answer here
            flag = true;
            fldGrpcelEligible.setVisibility(View.VISIBLE);
            celstdid.setText("CODI14W-");
            Selection.setSelection(celstdid.getText(), celstdid.getText().length());
            //celstdid.setOnKeyListener(EligibilityFormActivity.this);
            celstdid.addTextChangedListener(EligibilityFormActivity.this);
            fldGrprsn.setVisibility(View.GONE);
            celner.setText(null);


        } else if (isYes() && cel04a.isChecked()) {
            flag = true;
            fldGrpcelEligible.setVisibility(View.VISIBLE);
            celstdid.setText("CODI09M-");
            Selection.setSelection(celstdid.getText(), celstdid.getText().length());
            //celstdid.setOnKeyListener(EligibilityFormActivity.this);
            celstdid.addTextChangedListener(EligibilityFormActivity.this);
            fldGrprsn.setVisibility(View.GONE);
            celner.setText(null);
        } else if (isYes() && (cel03b.isChecked() || cel04b.isChecked())) {

            flag = false;
            fldGrpcelEligible.setVisibility(View.GONE);
            //  celee.clearCheck();
            celstdid.setText(null);
            celdoe.setText(null);
            fldGrprsn.setVisibility(View.VISIBLE);

        } else {
            flag = false;
            fldGrpcelEligible.setVisibility(View.GONE);
            //    celee.clearCheck();
            celstdid.setText(null);
            celdoe.setText(null);
            fldGrprsn.setVisibility(View.VISIBLE);

        }

    }

    public boolean isYes() {

        int i = 0;
        for (RadioButton rg : celEligibleYes) {
            if (rg.isChecked()) {
                i++;
            }

        }


        // Show answer here
        return i == celEligibleYes.size();
    }

    @Override
    public void onBackPressed() {
//        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
        super.onBackPressed();
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

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

        if (cel02a.isChecked() && !editable.toString().contains("CODI14W-")) {
            celstdid.setText("CODI14W-");
            Selection.setSelection(celstdid.getText(), celstdid.getText().length());
        } else if (cel02b.isChecked() && !editable.toString().contains("CODI09M-")) {
            celstdid.setText("CODI09M-");
            Selection.setSelection(celstdid.getText(), celstdid.getText().length());
        }


    }


}




