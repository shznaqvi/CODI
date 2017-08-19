package edu.aku.hassannaqvi.codi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

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

public class BloodSamplingActivity extends AppCompatActivity {

    private static final String TAG = BloodSamplingActivity.class.getSimpleName();
    final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

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
    @BindView(R.id.fldGrpSerum)
    LinearLayout fldGrpSerum;

    String dateToday;
    String scanned;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_sampling);
        ButterKnife.bind(this);

        dateToday = new SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis());


        cen20.setManager(getSupportFragmentManager());
        cen21.setManager(getSupportFragmentManager());
        cen20.setMaxDate(dateToday);

        if (!AppMain.fc.getFormType().equals("V1")) {
            cen20.setMinDate(convertDateFormat(AppMain.visitList.get(0).getEXPECTEDDT()));
        }


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

        //============== Serum Sepration Skip pattern=============

        cen22.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (cen22a.isChecked()) {
                    fldGrpSerum.setVisibility(View.VISIBLE);
                } else {
                    fldGrpSerum.setVisibility(View.GONE);
                    cen23.clearCheck();
                    cen24.setText(null);
                }
            }
        });

        cen24.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Toast.makeText(BloodSamplingActivity.this,
                        "before: " + charSequence + "|" +
                                i + "|" +
                                i1 + "|" +
                                i2
                        , Toast.LENGTH_SHORT).show();
                scanned = charSequence.toString();

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Toast.makeText(BloodSamplingActivity.this,
                        "onText: " + charSequence + "|" +
                                i + "|" +
                                i1 + "|" +
                                i2
                        , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Toast.makeText(BloodSamplingActivity.this,
                        "after: " + editable
                        , Toast.LENGTH_SHORT).show();
                if (scanned.contains("§") && editable.toString().contains("§") && !scanned.equals(editable)) {

                    cen24.setText(editable.toString().replace("§", ""));
                    Toast.makeText(BloodSamplingActivity.this, "Text Changed!", Toast.LENGTH_SHORT).show();
                }

            }
        });
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
                cen24.setText("§" + result.getContents().trim());
                //mngsticker.setEnabled(false);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
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

                if (AppMain.fc.getFormType().equals("V1")) {

                    startActivity(new Intent(this, RandomizationActivity.class));
                } else if (AppMain.fc.getFormType().equals("V2") ||
                        (AppMain.fc.getFormType().equals("V3") && AppMain.getEnrollmentChild.get(0).getArmGrp().equals("AB"))
                        || AppMain.fc.getFormType().equals("V4")) {
                    startActivity(new Intent(this, AppointmentActivity.class));
                } else if (AppMain.fc.getFormType().equals("V5") || (AppMain.fc.getFormType().equals("V3")
                        && AppMain.getEnrollmentChild.get(0).getArmGrp().equals("CD"))) {
                    startActivity(new Intent(this, EndingActivity.class));
                }

            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for this Section", Toast.LENGTH_SHORT).show();

        JSONObject sEnBloodSample = new JSONObject();

        sEnBloodSample.put("bl01", cen18a.isChecked() ? "1" : cen18b.isChecked() ? "2" : "0");
        sEnBloodSample.put("bl02", cen19a.isChecked() ? "1" : cen19b.isChecked() ? "2" : "0");
        sEnBloodSample.put("bl03", cen20.getText().toString());
        sEnBloodSample.put("bl04", cen21.getText().toString());
        sEnBloodSample.put("bl05", cen22a.isChecked() ? "1" : cen22b.isChecked() ? "2" : "0");
        sEnBloodSample.put("bl06", cen23a.isChecked() ? "1" : cen23b.isChecked() ? "2" : "0");
        if (cen24.getText().toString().equals("Sticker")) {
            sEnBloodSample.put("bl07", "");
        } else {
            sEnBloodSample.put("bl07", cen24.getText().toString());
        }

        AppMain.fc.setsBloodSample(String.valueOf(sEnBloodSample));

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }

    private boolean UpdateDB() {
        DatabaseHelper db = new DatabaseHelper(this);


        int updcount = db.updateSBloodSample();

        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    public boolean ValidateForm() {


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

            if (cen22a.isChecked()) {
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
                int scanChar;
                if (cen24.getText().toString().contains("§")) {
                    scanChar = 10;
                } else {
                    scanChar = 9;
                }
                if (cen24.getText().length() != scanChar || !cen24.getText().toString().contains("-")) {
                    Toast.makeText(this, "ERROR(invalid)" + getString(R.string.cen24), Toast.LENGTH_SHORT).show();
                    cen24.setError("Invalid sample number..");

                    Log.i(TAG, "cen24: Invalid sample number");
                    return false;
                } else {
                    cen24.setError(null);
                }
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
