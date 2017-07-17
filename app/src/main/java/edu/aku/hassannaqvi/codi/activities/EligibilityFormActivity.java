package edu.aku.hassannaqvi.codi.activities;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.aku.hassannaqvi.codi.R;
import edu.aku.hassannaqvi.codi.contracts.EligibilityContract;
import edu.aku.hassannaqvi.codi.core.AppMain;
import edu.aku.hassannaqvi.codi.core.DatabaseHelper;
import io.blackbox_vision.datetimepickeredittext.view.DatePickerInputEditText;


public class EligibilityFormActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private static final String TAG = EligibilityFormActivity.class.getSimpleName();

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
    @BindView(R.id.celee)
    RadioGroup celee;
    @BindView(R.id.celeea)
    RadioButton celeea;
    @BindView(R.id.celeeb)
    RadioButton celeeb;
    @BindView(R.id.celstdid)
    EditText celstdid;
    @BindView(R.id.celdoe)
    DatePickerInputEditText celdoe;
    @BindView(R.id.celner)
    EditText celner;
    @BindView(R.id.fldGrprsn)
    LinearLayout fldGrprsn;

    @BindViews({R.id.celdob, R.id.celdoe})
    List<DatePickerInputEditText> dates;
    @BindViews({R.id.cel03, R.id.cel04, R.id.cel05, R.id.cel06, R.id.cel07})
    List<RadioGroup> celEligible;
    @BindViews({R.id.cel05a, R.id.cel06a, R.id.cel07a})
    List<RadioButton> celEligibleYes;
    String date14Weeks;
    String date9Months;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eligibility_form);
        ButterKnife.bind(this);

        String dateToday = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
        date14Weeks = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTimeInMillis() - ((AppMain.MILLISECONDS_IN_14_WEEKS)));
        date9Months = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTimeInMillis() - ((AppMain.MILLISECONDS_IN_9_MONTH) + (AppMain.MILLISECONDS_IN_DAY)));

        for (DatePickerInputEditText de : dates) {
            de.setManager(getSupportFragmentManager());
        }
        celdoe.setMaxDate(dateToday);

        celdob.setMaxDate(date14Weeks);
        celdob.setMinDate(date9Months);



        //================== Q7 Skip Pattern ===========
        for (RadioGroup rg : celEligible) {
            rg.setOnCheckedChangeListener(this);
        }

        cel01.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (cel01a.isChecked()) {
                    fldGrpcel02.setVisibility(View.VISIBLE);
                } else {
                    fldGrpcel02.setVisibility(View.GONE);
                    cel02.clearCheck();
                    cel03.clearCheck();
                    cel04.clearCheck();
                    cel05.clearCheck();
                    cel06.clearCheck();
                    cel07.clearCheck();
                    celee.clearCheck();
                    celstdid.setText(null);
                    celdoe.setText(null);
                    celner.setText(null);

                }
            }
        });

        cel02.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (cel02b.isChecked()) {
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

                finish();

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

        long updcount = db.addEligibility(AppMain.elc);

        AppMain.elc.set_ID(String.valueOf(updcount));

        if (updcount != 0) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();

            AppMain.elc.set_UID(
                    (AppMain.elc.getDeviceID() + AppMain.elc.get_ID()));
            db.updateFormID();
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for this Section", Toast.LENGTH_SHORT).show();

        AppMain.elc = new EligibilityContract();


        JSONObject sel = new JSONObject();

        sel.put("celcn", celcn.getText().toString());
        sel.put("celdob", celdob.getText().toString());
        sel.put("celmn", celmn.getText().toString());
        sel.put("cel01", cel01a.isChecked() ? "1" : cel01b.isChecked() ? "2" : "0");
        sel.put("cel02", cel02a.isChecked() ? "1" : cel02b.isChecked() ? "2" : "0");
        sel.put("cel03", cel03a.isChecked() ? "1" : cel03b.isChecked() ? "2" : "0");
        sel.put("cel04", cel04a.isChecked() ? "1" : cel04b.isChecked() ? "2" : "0");
        sel.put("cel05", cel05a.isChecked() ? "1" : cel05b.isChecked() ? "2" : "0");
        sel.put("cel06", cel06a.isChecked() ? "1" : cel06b.isChecked() ? "2" : "0");
        sel.put("cel07", cel07a.isChecked() ? "1" : cel07b.isChecked() ? "2" : "0");
        sel.put("celee", celeea.isChecked() ? "1" : celeeb.isChecked() ? "2" : "0");
        sel.put("celstdid", celstdid.getText().toString());
        sel.put("celdoe", celdoe.getText().toString());
        sel.put("celner", celner.getText().toString());

        //AppMain.dob = celdob.getText().toString();

        AppMain.elc.setsEn(String.valueOf(sel));


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

        if (cel01a.isChecked()) {
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
                if (celee.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(this, "ERROR(Empty)" + getString(R.string.celee), Toast.LENGTH_SHORT).show();
                    celeeb.setError("This data is Required!");
                    Log.i(TAG, "celee: This Data is Required!");
                    return false;
                } else {
                    celeeb.setError(null);
                }

                // =================== celstdid ====================
                if (celstdid.getText().toString().isEmpty()) {
                    Toast.makeText(this, "ERROR(Empty)" + getString(R.string.celstdid), Toast.LENGTH_SHORT).show();
                    celstdid.setError("This data is required");
                    Log.d(TAG, "celstdid:empty ");
                    return false;
                } else {
                    celstdid.setError(null);
                }

                // =================== doe ====================
                if (celdoe.getText().toString().isEmpty()) {
                    Toast.makeText(this, "ERROR(Empty)" + getString(R.string.celdoe), Toast.LENGTH_SHORT).show();
                    celdoe.setError("This data is required");
                    Log.d(TAG, "celdoe:empty ");
                    return false;
                } else {
                    celdoe.setError(null);
                }
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
        }




        return true;
    }


    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        if ((isYes() && cel03a.isChecked()) || (isYes() && cel04a.isChecked())) {
            // Show answer here
            fldGrpcelEligible.setVisibility(View.VISIBLE);
            fldGrprsn.setVisibility(View.GONE);
            celner.setText(null);


        } else if (isYes() && (cel03b.isChecked() || cel04b.isChecked())) {
            fldGrpcelEligible.setVisibility(View.GONE);
            celee.clearCheck();
            celstdid.setText(null);
            celdoe.setText(null);
            fldGrprsn.setVisibility(View.VISIBLE);

        } else {
            fldGrpcelEligible.setVisibility(View.GONE);
            celee.clearCheck();
            celstdid.setText(null);
            celdoe.setText(null);
            fldGrprsn.setVisibility(View.VISIBLE);

        }

    }

    public boolean isYes() {

        int i = 0;
        for (RadioButton rg : celEligibleYes) {
            if (rg.isChecked())
                i++;
        }

        // Show answer here
        return i == celEligibleYes.size();
    }

    @Override
    public void onBackPressed() {
//        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
        super.onBackPressed();
    }

}



