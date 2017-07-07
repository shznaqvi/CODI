package edu.aku.hassannaqvi.codi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.aku.hassannaqvi.codi.R;
import edu.aku.hassannaqvi.codi.core.DatabaseHelper;
import edu.aku.hassannaqvi.codi.core.MainApp;
import io.blackbox_vision.datetimepickeredittext.view.DatePickerInputEditText;
import io.blackbox_vision.datetimepickeredittext.view.TimePickerInputEditText;

public class EnrollmentFormActivity extends AppCompatActivity {

    private static final String TAG = EnrollmentFormActivity.class.getSimpleName();
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
    @BindView(R.id.cen18)
    RadioGroup cen18;
    @BindView(R.id.cen18a)
    RadioButton cen18a;
    @BindView(R.id.cen18b)
    RadioButton cen18b;
    @BindView(R.id.fldGrpcen19)
    LinearLayout fldGrpcen19;
    @BindView(R.id.cen19)
    RadioGroup cen19;
    @BindView(R.id.cen19a)
    RadioButton cen19a;
    @BindView(R.id.cen19b)
    RadioButton cen19b;
    @BindView(R.id.cen20)
    DatePickerInputEditText cen20;
    @BindView(R.id.cen21)
    TimePickerInputEditText cen21;
    @BindView(R.id.cen22)
    RadioGroup cen22;
    @BindView(R.id.cen22a)
    RadioButton cen22a;
    @BindView(R.id.cen22b)
    RadioButton cen22b;
    @BindView(R.id.cen23)
    RadioGroup cen23;
    @BindView(R.id.cen23a)
    RadioButton cen23a;
    @BindView(R.id.cen23b)
    RadioButton cen23b;
    @BindView(R.id.cen24)
    EditText cen24;
    @BindView(R.id.cen25)
    DatePickerInputEditText cen25;
    @BindView(R.id.cen26)
    TimePickerInputEditText cen26;
    @BindView(R.id.cen27)
    RadioGroup cen27;
    @BindView(R.id.cen27a)
    RadioButton cen27a;
    @BindView(R.id.cen27b)
    RadioButton cen27b;
    @BindView(R.id.cen27c)
    RadioButton cen27c;
    @BindView(R.id.cen27d)
    RadioButton cen27d;
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
    @BindView(R.id.cen33b)
    EditText cen33b;
    /*@BindView(R.id.cendt)
    DatePickerInputEditText cendt;
    @BindView(R.id.centime)
    TimePickerInputEditText centime;*/
    @BindView(R.id.cendt)
    TextView cendt;
    @BindView(R.id.centime)
    TextView centime;

    @BindViews({R.id.cen01, R.id.cen06, R.id.cen20, R.id.cen25,
            R.id.cen30})
    List<DatePickerInputEditText> dates;

    @BindViews({R.id.cen21, R.id.cen26, R.id.cen31})
    List<TimePickerInputEditText> time;
    String dateToday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment_form);
        ButterKnife.bind(this);

        dateToday = new SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis());


        for (DatePickerInputEditText de : dates) {
            de.setManager(getSupportFragmentManager());
            de.setMaxDate(dateToday);

        }

        for (TimePickerInputEditText te : time) {
            te.setManager(getSupportFragmentManager());
        }

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

        //================ Blood Sampling Skip Pattern============
        cen18.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (cen18a.isChecked()) {
                    fldGrpcen19.setVisibility(View.VISIBLE);
                } else {
                    fldGrpcen19.setVisibility(View.GONE);
                    cen19.clearCheck();
                    cen20.setText(null);
                    cen21.setText(null);
                    cen22.clearCheck();
                    cen23.clearCheck();
                    cen24.setText(null);
                }
            }
        });

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
                    cen33b.setText(null);
                }
            }
        });

        //============== Vaccine based on Arm ========
        cen27.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (cen27c.isChecked()) {
                    cen28b.setEnabled(false);
                    cen28b.setChecked(false);
                } else {
                    cen28b.setEnabled(true);
                }
            }
        });

        Calendar cal = getCalendarDate(MainApp.enrollDate);
        cal.add(Calendar.DAY_OF_MONTH, 28);
        cendt.setText(sdf.format(cal.getTime()));
        centime.setText(new SimpleDateFormat("hh:mm").format(System.currentTimeMillis()));


    }

    @OnClick(R.id.btnScan)
    void onBtnScanClick() {
        cen24.setText(null);
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scan a blood sample sticker");
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(true);
        integrator.setOrientationLocked(false);

        integrator.initiateScan();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                cen24.setText("§" + result.getContents());
                //mngsticker.setEnabled(false);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
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


                startActivity(new Intent(this, MainActivity.class));

            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }




    }

    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for this Section", Toast.LENGTH_SHORT).show();

        JSONObject sen = new JSONObject();

        sen.put("dssid", dssid.getText().toString());
        sen.put("studyId", studyId.getText().toString());
        sen.put("cen01", cen01.getText().toString());
        sen.put("cen02", cen02a.isChecked() ? "1" : cen02b.isChecked() ? "2" : "0");
        sen.put("cen03", cen03a.isChecked() ? "1" : cen03b.isChecked() ? "2" : "0");
        sen.put("cen04", cen04.getText().toString());
        sen.put("cen05", cen05.getText().toString());
        sen.put("cen06", cen06.getText().toString());
        sen.put("cen07w", cen07w.getText().toString());
        sen.put("cen07m", cen07m.getText().toString());
        sen.put("cen08", cen08a.isChecked() ? "1" : cen08b.isChecked() ? "2" : "0");
        sen.put("cen09", cen09.getText().toString());
        sen.put("cenfp", cenfp.getText().toString());
        sen.put("cenmp", cenmp.getText().toString());
        sen.put("cenac", cenac.getText().toString());
        sen.put("cen10", cen10a.isChecked() ? "1" : cen10b.isChecked() ? "2" : cen10c.isChecked() ? "3" : cen10d.isChecked() ? "4" : cen10e.isChecked() ? "5" : cen10f.isChecked() ? "6" : cen1099.isChecked() ? "99" : "0");
        sen.put("cen11", cen11a.isChecked() ? "1" : cen11b.isChecked() ? "2" : cen11c.isChecked() ? "3" : cen11d.isChecked() ? "4" : cen11e.isChecked() ? "5" : cen11f.isChecked() ? "6" : "0");
        sen.put("cen12", cen12a.isChecked() ? "1" : cen12b.isChecked() ? "2" : cen12c.isChecked() ? "3" : cen12d.isChecked() ? "4" : "0");
        sen.put("cen13", cen13.getText().toString());
        sen.put("cen14", cen14.getText().toString());
        sen.put("cen15", cen15a.isChecked() ? "1" : cen15b.isChecked() ? "2" : "0");
        sen.put("cen16", cen16a.isChecked() ? "1" : cen16b.isChecked() ? "2" : cen16c.isChecked() ? "3" : "0");
        sen.put("cen17", cen17a.isChecked() ? "1" : cen17b.isChecked() ? "2" : cen1788.isChecked() ? "3" : "0");
        sen.put("cen1788x", cen1788x.getText().toString());
        sen.put("cen18", cen18a.isChecked() ? "1" : cen18b.isChecked() ? "2" : "0");
        sen.put("cen19", cen19a.isChecked() ? "1" : cen19b.isChecked() ? "2" : "0");
        sen.put("cen20", cen20.getText().toString());
        sen.put("cen21", cen21.getText().toString());
        sen.put("cen22", cen22a.isChecked() ? "1" : cen22b.isChecked() ? "2" : "0");
        sen.put("cen23", cen23a.isChecked() ? "1" : cen23b.isChecked() ? "2" : "0");
        if (cen24.getText().toString().equals("Sticker")) {
            sen.put("cen24", "");
        } else {
            sen.put("cen24", cen24.getText().toString());
        }
        sen.put("cen25", cen25.getText().toString());
        sen.put("cen26", cen26.getText().toString());
        sen.put("cen27", cen27a.isChecked() ? "1" : cen27b.isChecked() ? "2" : cen27c.isChecked() ? "3" : cen27d.isChecked() ? "4" : "0");
        sen.put("cen28", cen28a.isChecked() ? "1" : cen28b.isChecked() ? "2" : "0");
        sen.put("cen29", cen29a.isChecked() ? "1" : cen29b.isChecked() ? "2" : "0");
        sen.put("cen30", cen30.getText().toString());
        sen.put("cen31", cen31.getText().toString());
        sen.put("cen32", cen32a.isChecked() ? "1" : cen32b.isChecked() ? "2" : "0");
        sen.put("cen33a", cen33a.getText().toString());
        sen.put("cen33b", cen33b.getText().toString());


        sen.put("cendt", cendt.getText().toString());
        sen.put("centime", centime.getText().toString());

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }

    private boolean UpdateDB() {
        DatabaseHelper db = new DatabaseHelper(this);

       /* long updcount = db.addForm(AppMain.elc);

        AppMain.elc.setID(String.valueOf(updcount));

        if (updcount != 0) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();

            AppMain.elc.setUID(
                    (AppMain.elc.getDeviceID() + AppMain.elc.getID()));
            db.updateFormID();
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
        }*/
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
        }

        if (cen18.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen18), Toast.LENGTH_SHORT).show();
            cen18a.setError("This data is Required!");

            Log.i(TAG, "cen18: This Data is Required!");
            return false;
        } else {
            cen18a.setError(null);
        }

        if (cen18a.isChecked()) {
            if (cen19.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen19), Toast.LENGTH_SHORT).show();
                cen19a.setError("This data is Required!");

                Log.i(TAG, "cen19: This Data is Required!");
                return false;
            } else {
                cen19a.setError(null);
            }

            if (cen20.getText().toString().isEmpty()) {
                Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen20), Toast.LENGTH_SHORT).show();
                cen20.setError("This data is Required!");

                Log.i(TAG, "cen20: This Data is Required!");
                return false;
            } else {
                cen20.setError(null);
            }

            if (cen21.getText().toString().isEmpty()) {
                Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen21), Toast.LENGTH_SHORT).show();
                cen21.setError("This data is Required!");

                Log.i(TAG, "cen21: This Data is Required!");
                return false;
            } else {
                cen21.setError(null);
            }

            if (cen22.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen22), Toast.LENGTH_SHORT).show();
                cen22a.setError("This data is Required!");

                Log.i(TAG, "cen22: This Data is Required!");
                return false;
            } else {
                cen22a.setError(null);
            }

            if (cen23.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen23), Toast.LENGTH_SHORT).show();
                cen23a.setError("This data is Required!");

                Log.i(TAG, "cen23: This Data is Required!");
                return false;
            } else {
                cen23a.setError(null);
            }

            if (cen24.getText().toString().isEmpty()) {
                Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen24), Toast.LENGTH_SHORT).show();
                cen24.setError("This data is Required!");

                Log.i(TAG, "cen24: This Data is Required!");
                return false;
            } else {
                cen24.setError(null);
            }
        }

        if (cen25.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen25), Toast.LENGTH_SHORT).show();
            cen25.setError("This data is Required!");

            Log.i(TAG, "cen25: This Data is Required!");
            return false;
        } else {
            cen25.setError(null);
        }

        if (cen26.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen26), Toast.LENGTH_SHORT).show();
            cen26.setError("This data is Required!");

            Log.i(TAG, "cen26: This Data is Required!");
            return false;
        } else {
            cen26.setError(null);
        }

        if (cen27.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cen27), Toast.LENGTH_SHORT).show();
            cen27a.setError("This data is Required!");

            Log.i(TAG, "cen27: This Data is Required!");
            return false;
        } else {
            cen27a.setError(null);
        }

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

        }

        /*if (cendt.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.cendt), Toast.LENGTH_SHORT).show();
            cendt.setError("This data is Required!");

            Log.i(TAG, "cendt: This Data is Required!");
            return false;
        } else {
            cendt.setError(null);
        }

        if (centime.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.centime), Toast.LENGTH_SHORT).show();
            centime.setError("This data is Required!");

            Log.i(TAG, "centime: This Data is Required!");
            return false;
        } else {
            centime.setError(null);
        }
*/

        return true;
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



}
