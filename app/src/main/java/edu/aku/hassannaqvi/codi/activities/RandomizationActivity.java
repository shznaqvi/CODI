package edu.aku.hassannaqvi.codi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import edu.aku.hassannaqvi.codi.core.MainApp;
import io.blackbox_vision.datetimepickeredittext.view.DatePickerInputEditText;
import io.blackbox_vision.datetimepickeredittext.view.TimePickerInputEditText;

public class RandomizationActivity extends AppCompatActivity {

    private static final String TAG = RandomizationActivity.class.getSimpleName();

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

    String dateToday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randomization);
        ButterKnife.bind(this);
        dateToday = new SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis());

        cen25.setManager(getSupportFragmentManager());
        cen26.setManager(getSupportFragmentManager());
        cen25.setMaxDate(dateToday);
        cen25.setMinDate(convertDateFormat(MainApp.enrollDate));

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


                startActivity(new Intent(this, VaccineActivity.class));

            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for this Section", Toast.LENGTH_SHORT).show();

        JSONObject sEnRandomization = new JSONObject();

        sEnRandomization.put("cen25", cen25.getText().toString());
        sEnRandomization.put("cen26", cen26.getText().toString());
        sEnRandomization.put("cen27", cen27a.isChecked() ? "1" : cen27b.isChecked() ? "2" : cen27c.isChecked() ? "3"
                : cen27d.isChecked() ? "4" : "0");

        MainApp.arm = cen27.indexOfChild(findViewById(cen27.getCheckedRadioButtonId())) + 1;

        MainApp.enc.setsEnRandomization(String.valueOf(sEnRandomization));

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }

    private boolean UpdateDB() {

        /*DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateSenRandomization();

        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }*/

        return true;
    }

    public boolean ValidateForm() {

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
