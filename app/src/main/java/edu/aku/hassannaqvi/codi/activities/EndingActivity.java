package edu.aku.hassannaqvi.codi.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import org.json.JSONException;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.hassannaqvi.codi.R;
import edu.aku.hassannaqvi.codi.core.MainApp;

public class EndingActivity extends Activity {

    private static final String TAG = EndingActivity.class.getSimpleName();
    @BindView(R.id.scrollView01)
    ScrollView scrollView01;
    @BindView(R.id.dcstatus)
    RadioGroup dcstatus;
    @BindView(R.id.status01)
    RadioButton status01;
    @BindView(R.id.status02)
    RadioButton status02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ending);
        ButterKnife.bind(this);

        Boolean check = getIntent().getExtras().getBoolean("check");

        if (check) {
            status01.setEnabled(true);
            status02.setEnabled(false);

        } else {
            //fldGrpmn0823Reason.setVisibility(View.GONE);
            status01.setEnabled(false);
            status02.setEnabled(true);
        }

    }


    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for  This Section", Toast.LENGTH_SHORT).show();

        MainApp.fc.setIstatus(status01.isChecked() ? "1" : status02.isChecked() ? "2" : "0");

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }

    private boolean UpdateDB() {
        /*DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateEnd();

        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();*/
        return false;
        //}
    }

    public boolean ValidateForm() {
        Toast.makeText(this, "Validating This Section ", Toast.LENGTH_SHORT).show();

        if (dcstatus.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(Not Selected): " + getString(R.string.status), Toast.LENGTH_LONG).show();
            status02.setError("Please Select One");    // Set Error on last radio button
            Log.i(TAG, "status: This data is Required!");
            return false;
        } else {
            status02.setError(null);
        }


        return true;
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
    }


}
