package edu.aku.hassannaqvi.codi.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.aku.hassannaqvi.codi.R;
import edu.aku.hassannaqvi.codi.core.AppMain;
import edu.aku.hassannaqvi.codi.core.DatabaseHelper;

public class ChildHealthAndBreastFeedActivity extends Activity {
    private static final String TAG = ChildHealthAndBreastFeedActivity.class.getSimpleName();

    @BindView(R.id.fldGrpcsv)
    LinearLayout fldGrpcsv;
    @BindView(R.id.csv04)
    RadioGroup csv04;
    @BindView(R.id.csv04a)
    RadioButton csv04a;
    @BindView(R.id.csv04b)
    RadioButton csv04b;
    @BindView(R.id.csv05)
    RadioGroup csv05;
    @BindView(R.id.csv05a)
    RadioButton csv05a;
    @BindView(R.id.csv05b)
    RadioButton csv05b;
    @BindView(R.id.csv06)
    RadioGroup csv06;
    @BindView(R.id.csv06a)
    RadioButton csv06a;
    @BindView(R.id.csv06b)
    RadioButton csv06b;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_health_and_breast_feed);
        ButterKnife.bind(this);

        if (AppMain.fc.getFormType().equals("V1")) {
            fldGrpcsv.setVisibility(View.GONE);
            csv04.clearCheck();
            csv05.clearCheck();
            csv06.clearCheck();
        } else {
            fldGrpcsv.setVisibility(View.VISIBLE);
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


    }

    @OnClick(R.id.btn_End)
    void onBtnEndClick() {

        Toast.makeText(this, "Processing This Section", Toast.LENGTH_SHORT).show();
        AppMain.endActivity(this, this);

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

                if (AppMain.formType.equals("V3") && AppMain.arm.equals("AB")) {
                    startActivity(new Intent(this, VaccineActivity.class));
                } else {
                    startActivity(new Intent(this, BloodSamplingActivity.class));
                }


                //startActivity(new Intent(this, BloodSamplingActivity.class));

            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for this Section", Toast.LENGTH_SHORT).show();


        JSONObject sChbf = new JSONObject();

        sChbf.put("hb01", csv04a.isChecked() ? "1" : csv04b.isChecked() ? "2" : "0");
        sChbf.put("hb02", csv05a.isChecked() ? "1" : csv05b.isChecked() ? "2" : "0");
        sChbf.put("hb03", csv06a.isChecked() ? "1" : csv06b.isChecked() ? "2" : "0");
        sChbf.put("hb04", cen15a.isChecked() ? "1" : cen15b.isChecked() ? "2" : "0");
        sChbf.put("hb05", cen16a.isChecked() ? "1" : cen16b.isChecked() ? "2" : cen16c.isChecked() ? "3" : "0");
        sChbf.put("hb06", cen17a.isChecked() ? "1" : cen17b.isChecked() ? "2" : cen1788.isChecked() ? "88" : "0");
        sChbf.put("hb0688x", cen1788x.getText().toString());


        AppMain.fc.setsCHBF(String.valueOf(sChbf));

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }

    private boolean UpdateDB() {
        DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateSCHBF();

        if (updcount != 0) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();

            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    public boolean ValidateForm() {

        if (!AppMain.fc.getFormType().equals("V1")) {
            if (csv04.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(Empty)" + getString(R.string.csv04), Toast.LENGTH_SHORT).show();
                csv04a.setError("This data is Required!");

                Log.i(TAG, "csv04: This Data is Required!");
                return false;
            } else {
                csv04a.setError(null);
            }

            if (csv05.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(Empty)" + getString(R.string.csv05), Toast.LENGTH_SHORT).show();
                csv05a.setError("This data is Required!");

                Log.i(TAG, "csv05: This Data is Required!");
                return false;
            } else {
                csv05a.setError(null);
            }

            if (csv06.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "ERROR(Empty)" + getString(R.string.csv06), Toast.LENGTH_SHORT).show();
                csv06a.setError("This data is Required!");

                Log.i(TAG, "csv06: This Data is Required!");
                return false;
            } else {
                csv06a.setError(null);
            }
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


}
