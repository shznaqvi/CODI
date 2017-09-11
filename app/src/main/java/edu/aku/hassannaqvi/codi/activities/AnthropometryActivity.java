package edu.aku.hassannaqvi.codi.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.aku.hassannaqvi.codi.R;
import edu.aku.hassannaqvi.codi.core.AppMain;
import edu.aku.hassannaqvi.codi.core.DatabaseHelper;

public class AnthropometryActivity extends Activity {

    private static final String TAG = AnthropometryActivity.class.getSimpleName();

    @BindView(R.id.an01)
    EditText an01;
    @BindView(R.id.an02)
    EditText an02;
    @BindView(R.id.fldGrpan03)
    LinearLayout fldGrpan03;
    @BindView(R.id.an03)
    EditText an03;
    @BindView(R.id.an04)
    RadioGroup an04;
    @BindView(R.id.an04a)
    RadioButton an04a;
    @BindView(R.id.an04b)
    RadioButton an04b;
    @BindView(R.id.an04c)
    RadioButton an04c;
    @BindView(R.id.an05)
    EditText an05;
    @BindView(R.id.an06)
    EditText an06;
    @BindView(R.id.fldGrpan07)
    LinearLayout fldGrpan07;
    @BindView(R.id.an07)
    EditText an07;
    @BindView(R.id.an08)
    EditText an08;
    @BindView(R.id.an09)
    EditText an09;
    @BindView(R.id.fldGrpan10)
    LinearLayout fldGrpan10;
    @BindView(R.id.an10)
    EditText an10;

    boolean flag_q3 = false, flag_q7 = false, flag_q10 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anthropometry);
        ButterKnife.bind(this);


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

                if (AppMain.fc.getFormType().equals("V1")) {

                    startActivity(new Intent(this, BloodSamplingActivity.class));
                }

            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for this Section", Toast.LENGTH_SHORT).show();


        AppMain.sInfo.put("an01", an01.getText().toString());
        AppMain.sInfo.put("an02", an02.getText().toString());
        AppMain.sInfo.put("an03", an03.getText().toString());
        AppMain.sInfo.put("an04", an04a.isChecked() ? "1" : an04b.isChecked() ? "2" : an04c.isChecked() ? "3" : "0");
        AppMain.sInfo.put("an05", an05.getText().toString());
        AppMain.sInfo.put("an06", an06.getText().toString());
        AppMain.sInfo.put("an07", an07.getText().toString());
        AppMain.sInfo.put("an08", an08.getText().toString());
        AppMain.sInfo.put("an09", an09.getText().toString());
        AppMain.sInfo.put("an10", an10.getText().toString());


        AppMain.fc.setsInfo(String.valueOf(AppMain.sInfo));

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }

    private boolean UpdateDB() {
        DatabaseHelper db = new DatabaseHelper(this);


        int updcount = db.updateSInfo();

        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    public boolean ValidateForm() {


        //========== MUAC 1st=============
        if (an01.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.an01), Toast.LENGTH_SHORT).show();
            an01.setError("This data is Required!");

            Log.i(TAG, "an01: This Data is Required!");
            return false;
        } else {
            an01.setError(null);
        }

        if (Double.parseDouble(an01.getText().toString()) < 1) {
            Toast.makeText(this, "ERROR(invalid): " + getString(R.string.an01), Toast.LENGTH_SHORT).show();
            an01.setError("Invalid: Greater than 0");
            Log.i(TAG, "an01: Invalid Greater then 0");
            return false;
        } else {
            an01.setError(null);
        }

        // MUAC 2nd========

        if (an02.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.an02), Toast.LENGTH_SHORT).show();
            an02.setError("This data is Required!");

            Log.i(TAG, "an02: This Data is Required!");
            return false;
        } else {
            an02.setError(null);
        }

        if (Double.parseDouble(an02.getText().toString()) < 1) {
            Toast.makeText(this, "ERROR(invalid): " + getString(R.string.an02), Toast.LENGTH_SHORT).show();
            an02.setError("Invalid: Greater than 0");
            Log.i(TAG, "an02: Invalid Greater than 0");
            return false;
        } else {
            an01.setError(null);
        }

        // MUAC 3rd===============

        double q3 = Double.parseDouble(an01.getText().toString()) - Double.parseDouble(an02.getText().toString());

        if (Math.signum(q3) == -1) {
            q3 = q3 * -1;
        }

        if (!flag_q3) {
            if (q3 > 0.1) {
                flag_q3 = true;

                fldGrpan03.setVisibility(View.VISIBLE);

                Toast.makeText(this, "ERROR(invalid): Difference in Measurement", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "mp02d005 & mp02d006: Difference in Measurement");
                return false;

            } else {
                fldGrpan03.setVisibility(View.GONE);
                an03.setText(null);
            }
        }

        if (flag_q3) {
            if (an03.getText().toString().isEmpty()) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.an03), Toast.LENGTH_SHORT).show();
                an03.setError("This data is Required!");
                Log.i(TAG, "an03: This data is Required!");
                return false;
            } else {
                an03.setError(null);
            }

            if (Double.parseDouble(an03.getText().toString()) < 1) {
                Toast.makeText(this, "ERROR(invalid): " + getString(R.string.an03), Toast.LENGTH_SHORT).show();
                an03.setError("Invalid: Greater than 0");
                Log.i(TAG, "an03: Invalid Greater then 0");
                return false;
            } else {
                an03.setError(null);
            }
        }

        //============= Q4============

        if (an04.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.an04), Toast.LENGTH_SHORT).show();
            an04a.setError("This data is Required!");

            Log.i(TAG, "an04: This Data is Required!");
            return false;
        } else {
            an04a.setError(null);
        }

        // Weight 1st=============


        if (an05.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.an05), Toast.LENGTH_SHORT).show();
            an05.setError("This data is Required!");

            Log.i(TAG, "an05: This Data is Required!");
            return false;
        } else {
            an05.setError(null);
        }

        if (Double.parseDouble(an05.getText().toString()) < 1) {
            Toast.makeText(this, "ERROR(invalid): " + getString(R.string.an05), Toast.LENGTH_SHORT).show();
            an05.setError("Invalid: Greater than 0");
            Log.i(TAG, "an05: Invalid Greater then 0");
            return false;
        } else {
            an05.setError(null);
        }

        //======== Weight 2nd

        if (an06.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.an06), Toast.LENGTH_SHORT).show();
            an06.setError("This data is Required!");

            Log.i(TAG, "an06: This Data is Required!");
            return false;
        } else {
            an06.setError(null);
        }

        if (Double.parseDouble(an06.getText().toString()) < 1) {
            Toast.makeText(this, "ERROR(invalid): " + getString(R.string.an06), Toast.LENGTH_SHORT).show();
            an06.setError("Invalid: Greater than 0");
            Log.i(TAG, "an06: Invalid Greater than 0");
            return false;
        } else {
            an06.setError(null);
        }

        //=============== Weight 3

        double q7 = Double.parseDouble(an05.getText().toString()) - Double.parseDouble(an06.getText().toString());

        if (Math.signum(q7) == -1) {
            q7 = q7 * -1;
        }

        if (!flag_q7) {
            if (q7 >= 0.10) {
                flag_q7 = true;

                fldGrpan07.setVisibility(View.VISIBLE);

                Toast.makeText(this, "ERROR(invalid): Difference in Measurement", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "an05 & an06: Difference in Measurement");
                return false;

            } else {

                fldGrpan07.setVisibility(View.GONE);
                an07.setText(null);
            }
        }

        if (flag_q7) {
            if (an07.getText().toString().isEmpty()) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.an07), Toast.LENGTH_SHORT).show();
                an07.setError("This data is Required!");
                Log.i(TAG, "an07: This data is Required!");
                return false;
            } else {
                an03.setError(null);
            }

            if (Double.parseDouble(an07.getText().toString()) < 1) {
                Toast.makeText(this, "ERROR(invalid): " + getString(R.string.an07), Toast.LENGTH_SHORT).show();
                an07.setError("Invalid: Greater than 0");
                Log.i(TAG, "an07: Invalid Greater then 0");
                return false;
            } else {
                an07.setError(null);
            }
        }


        //========= Length 1

        if (an08.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.an08), Toast.LENGTH_SHORT).show();
            an08.setError("This data is Required!");

            Log.i(TAG, "an08: This Data is Required!");
            return false;
        } else {
            an08.setError(null);
        }

        if (Double.parseDouble(an08.getText().toString()) < 1) {
            Toast.makeText(this, "ERROR(invalid): " + getString(R.string.an08), Toast.LENGTH_SHORT).show();
            an08.setError("Invalid: Greater than 0");
            Log.i(TAG, "an08: Invalid Greater then 0");
            return false;
        } else {
            an08.setError(null);
        }

        //======== Height 2nd

        if (an09.getText().toString().isEmpty()) {
            Toast.makeText(this, "ERROR(Empty)" + getString(R.string.an09), Toast.LENGTH_SHORT).show();
            an09.setError("This data is Required!");

            Log.i(TAG, "an09: This Data is Required!");
            return false;
        } else {
            an09.setError(null);
        }

        if (Double.parseDouble(an09.getText().toString()) < 1) {
            Toast.makeText(this, "ERROR(invalid): " + getString(R.string.an09), Toast.LENGTH_SHORT).show();
            an09.setError("Invalid: Greater than 0");
            Log.i(TAG, "an09: Invalid Greater than 0");
            return false;
        } else {
            an09.setError(null);
        }

        //=============== Height 3

        double q10 = Double.parseDouble(an08.getText().toString()) - Double.parseDouble(an09.getText().toString());

        if (Math.signum(q10) == -1) {
            q10 = q10 * -1;
        }

        if (!flag_q10) {
            if (q10 >= 0.5) {
                flag_q10 = true;

                fldGrpan10.setVisibility(View.VISIBLE);

                Toast.makeText(this, "ERROR(invalid): Difference in Measurement", Toast.LENGTH_SHORT).show();
                an10.setError("This data is required");
                Log.i(TAG, "an08 & an09: Difference in Measurement");
                return false;

            } else {
                an10.setError(null);
                fldGrpan10.setVisibility(View.GONE);
                an10.setText(null);
            }
        }

        if (flag_q10) {
            if (an10.getText().toString().isEmpty()) {
                Toast.makeText(this, "ERROR(empty): " + getString(R.string.an10), Toast.LENGTH_SHORT).show();
                an10.setError("This data is Required!");
                Log.i(TAG, "an10: This data is Required!");
                return false;
            } else {
                an10.setError(null);
            }

            if (Double.parseDouble(an10.getText().toString()) < 1) {
                Toast.makeText(this, "ERROR(invalid): " + getString(R.string.an10), Toast.LENGTH_SHORT).show();
                an10.setError("Invalid: Greater than 0");
                Log.i(TAG, "an10: Invalid Greater then 0");
                return false;
            } else {
                an10.setError(null);
            }
        }


        return true;
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
    }


}
