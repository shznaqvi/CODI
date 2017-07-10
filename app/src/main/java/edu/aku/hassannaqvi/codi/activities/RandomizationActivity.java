package edu.aku.hassannaqvi.codi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.aku.hassannaqvi.codi.R;
import edu.aku.hassannaqvi.codi.contracts.ChildrenContract;
import edu.aku.hassannaqvi.codi.core.AppMain;
import edu.aku.hassannaqvi.codi.core.DatabaseHelper;

public class RandomizationActivity extends AppCompatActivity {

    private static final String TAG = RandomizationActivity.class.getSimpleName();

    @BindView(R.id.cen25)
    EditText cen25;
    /*@BindView(R.id.cen26)
    TimePickerInputEditText cen26;*/
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randomization);
        ButterKnife.bind(this);
        //dateToday = new SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis());

        cen25.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(System.currentTimeMillis()));


        double ageInDays = AppMain.ageInDays("01-04-2017"); //


        if (ageInDays >= 98 && ageInDays <= 104) // 14 Weeks
        {
            cen27a.setEnabled(true);
            cen27b.setEnabled(true);
            cen27c.setEnabled(false);
            cen27d.setEnabled(false);
        } else if (ageInDays >= 273.7 && ageInDays < 304) { // 9 Months
            cen27a.setEnabled(false);
            cen27b.setEnabled(false);
            cen27c.setEnabled(true);
            cen27d.setEnabled(true);
        }


        String arms = AppMain.getEnrollmentChild.get(0).getArmGrp();

        Log.d("ArmsGrp",arms);

        if (arms.equals("AB")){
            cen27a.setEnabled(true);
            cen27b.setEnabled(true);
            cen27c.setEnabled(false);
            cen27d.setEnabled(false);
        }else {
            cen27c.setEnabled(true);
            cen27d.setEnabled(true);
            cen27a.setEnabled(false);
            cen27b.setEnabled(false);
        }

    }

    @OnClick(R.id.btn_End) void onBtnEndClick() {
        //TODO implement

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


                startActivity(new Intent(this, VaccineActivity.class));

            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for this Section", Toast.LENGTH_SHORT).show();

        /*JSONObject sRandomization = new JSONObject();

        sRandomization.put("cen25", cen25.getText().toString());
        sRandomization.put("cen27", cen27a.isChecked() ? "1" : cen27b.isChecked() ? "2" : cen27c.isChecked() ? "3"
                : cen27d.isChecked() ? "4" : "0");
*/
        AppMain.cc = new ChildrenContract();

        AppMain.cc.setRandDate(cen25.getText().toString());
//        AppMain.cc.setArmSlc(String.valueOf(cen27.indexOfChild(findViewById(cen27.getCheckedRadioButtonId())) + 1));
        AppMain.cc.setArmSlc(cen27a.isChecked() ? "A" : cen27b.isChecked() ? "B" : cen27c.isChecked() ? "C"
                : cen27d.isChecked() ? "D" : "0");

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }

    private boolean UpdateDB() {

        DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateSRandomization(AppMain.getEnrollmentChild.get(0).getDSSID());

        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
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




}
