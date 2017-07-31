package edu.aku.hassannaqvi.codi.activities;

import android.content.Intent;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.aku.hassannaqvi.codi.R;
import edu.aku.hassannaqvi.codi.core.AppMain;
import edu.aku.hassannaqvi.codi.core.DatabaseHelper;
import io.blackbox_vision.datetimepickeredittext.view.DatePickerInputEditText;
import io.blackbox_vision.datetimepickeredittext.view.TimePickerInputEditText;

public class VaccineActivity extends AppCompatActivity {

    private static final String TAG = VaccineActivity.class.getSimpleName();

    @BindView(R.id.cen28)
    RadioGroup cen28;
    @BindView(R.id.cen28a)
    RadioButton cen28a;
    @BindView(R.id.cen28b)
    RadioButton cen28b;
    @BindView(R.id.fldGrpcen29)
    LinearLayout fldGrpcen29;
    @BindView(R.id.cen29)
    RadioGroup cen29;
    @BindView(R.id.cen29a)
    RadioButton cen29a;
    @BindView(R.id.cen29b)
    RadioButton cen29b;
    @BindView(R.id.cen30)
    DatePickerInputEditText cen30;
    @BindView(R.id.cen31)
    TimePickerInputEditText cen31;
    @BindView(R.id.cen32)
    RadioGroup cen32;
    @BindView(R.id.cen32a)
    RadioButton cen32a;
    @BindView(R.id.cen32b)
    RadioButton cen32b;
    @BindView(R.id.cen33a)
    EditText cen33a;

    @BindView(R.id.cen34a)
    EditText cen34a;

    @BindView(R.id.fldGrpcen34)
    LinearLayout fldGrpcen34;

    String dateToday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine);
        ButterKnife.bind(this);

        dateToday = new SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis());

        cen30.setManager(getSupportFragmentManager());
        cen31.setManager(getSupportFragmentManager());
        cen30.setMaxDate(dateToday);
        cen30.setMinDate(convertDateFormat(AppMain.enrollDate));


        //============== Vacccine after randomization skip pattern=========
        cen28.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (cen28a.isChecked()) {
                    fldGrpcen29.setVisibility(View.VISIBLE);
                } else {
                    fldGrpcen29.setVisibility(View.GONE);
                    cen29.clearCheck();
                    cen30.setText(null);
                    cen31.setText(null);
                    cen32.clearCheck();
                    cen33a.setText(null);
                    cen34a.setText(null);
                }
            }
        });

        //============== Vaccine based on Arm ========
        /*if (AppMain.cc.getArmSlc().equals("C")) {
            cen29b.setEnabled(false);
            cen29b.setChecked(false);
        } else {
            cen29b.setEnabled(true);
        }*/

        cen32.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (cen32a.isChecked()) {
                    fldGrpcen34.setVisibility(View.GONE);
                    cen34a.setText(null);
                } else {
                    fldGrpcen34.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @OnClick(R.id.btn_End)
    void onBtnEndClick() {
        Toast.makeText(this, "Processing This Section", Toast.LENGTH_SHORT).show();

/*        if (ValidateForm()) {
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
        }*/
        AppMain.endActivity(this,this);
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


                startActivity(new Intent(this, AppointmentActivity.class));

            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for this Section", Toast.LENGTH_SHORT).show();

        JSONObject sVaccine = new JSONObject();

        sVaccine.put("vc01", cen28a.isChecked() ? "1" : cen28b.isChecked() ? "2" : "0");
        sVaccine.put("vc02", cen29a.isChecked() ? "1" : cen29b.isChecked() ? "2" : "0");
        sVaccine.put("vc03", cen30.getText().toString());
        sVaccine.put("vc04", cen31.getText().toString());
        sVaccine.put("vc05", cen32a.isChecked() ? "1" : cen32b.isChecked() ? "2" : "0");
        sVaccine.put("vc06", cen33a.getText().toString());
        sVaccine.put("vc07", cen34a.getText().toString());

        AppMain.fc.setsVaccine(String.valueOf(sVaccine));

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }

    private boolean UpdateDB() {
        DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateSVaccine();

        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

        //return true;
    }

    public boolean ValidateForm() {

        if (cen28.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen28), Toast.LENGTH_SHORT).show();
            cen28a.setError("This data is Required!");

            Log.i(TAG, "cen28: This Data is Required!");
            return false;
        } else {
            cen28a.setError(null);
        }

        if (cen28a.isChecked()) {
            if (cen29.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen29), Toast.LENGTH_SHORT).show();
                cen29a.setError("This data is Required!");

                Log.i(TAG, "cen29: This Data is Required!");
                return false;
            } else {
                cen29a.setError(null);
            }

            if (cen30.getText().toString().isEmpty()) {
                Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen30), Toast.LENGTH_SHORT).show();
                cen30.setError("This data is Required!");

                Log.i(TAG, "cen30: This Data is Required!");
                return false;
            } else {
                cen30.setError(null);
            }

            if (cen31.getText().toString().isEmpty()) {
                Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen31), Toast.LENGTH_SHORT).show();
                cen31.setError("This data is Required!");

                Log.i(TAG, "cen31: This Data is Required!");
                return false;
            } else {
                cen31.setError(null);
            }

            if (cen32.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen32), Toast.LENGTH_SHORT).show();
                cen32a.setError("This data is Required!");

                Log.i(TAG, "cen32: This Data is Required!");
                return false;
            } else {
                cen32a.setError(null);
            }

            if (cen32b.isChecked()) {
                if (cen34a.getText().toString().isEmpty()) {
                    Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen34), Toast.LENGTH_SHORT).show();
                    cen34a.setError("This data is Required!");

                    Log.i(TAG, "cen34: This Data is Required!");
                    return false;
                } else {
                    cen34a.setError(null);
                }
            }

            if (cen33a.getText().toString().isEmpty()) {
                Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen33), Toast.LENGTH_SHORT).show();
                cen33a.setError("This data is Required!");

                Log.i(TAG, "cen33: This Data is Required!");
                return false;
            } else {
                cen33a.setError(null);
            }
        }
        return true;
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
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

}
