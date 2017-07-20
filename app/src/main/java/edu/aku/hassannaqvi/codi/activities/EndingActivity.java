package edu.aku.hassannaqvi.codi.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import org.json.JSONException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.aku.hassannaqvi.codi.R;
import edu.aku.hassannaqvi.codi.core.AppMain;
import edu.aku.hassannaqvi.codi.core.DatabaseHelper;

public class EndingActivity extends Activity {

    private static final String TAG = EndingActivity.class.getSimpleName();

    @BindView(R.id.scrollView01)
    ScrollView scrollView01;
    @BindView(R.id.iStatus)
    RadioGroup iStatus;
    @BindView(R.id.iStatusa)
    RadioButton iStatusa;
    @BindView(R.id.iStatusb)
    RadioButton iStatusb;
    @BindView(R.id.iStatusc)
    RadioButton iStatusc;
    @BindView(R.id.iStatusd)
    RadioButton iStatusd;
    @BindView(R.id.iStatuse)
    RadioButton iStatuse;
    @BindView(R.id.iStatusf)
    RadioButton iStatusf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ending);
        ButterKnife.bind(this);

        Boolean check = getIntent().getExtras().getBoolean("complete");

        if (check) {
            iStatusa.setEnabled(true);
            iStatusb.setEnabled(false);
            iStatusc.setEnabled(false);
            iStatusd.setEnabled(false);
            iStatuse.setEnabled(false);
            iStatusf.setEnabled(false);


        } else {
            //fldGrpmn0823Reason.setVisibility(View.GONE);
            iStatusa.setEnabled(false);
            iStatusb.setEnabled(true);
            iStatusc.setEnabled(true);
            iStatusd.setEnabled(true);
            iStatuse.setEnabled(true);
            iStatusf.setEnabled(true);


        }

    }

    @OnClick(R.id.btn_End)
    void onBtnEndClick() {

        Intent endSec = new Intent(this, MainActivity.class);
        endSec.putExtra("complete", false);
        startActivity(endSec);
    }

    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for  This Section", Toast.LENGTH_SHORT).show();

        AppMain.fc.setIstatus(iStatusa.isChecked() ? "1" : iStatusb.isChecked() ? "2" : iStatusc.isChecked() ? "3"
                : iStatusd.isChecked() ? "4" : iStatuse.isChecked() ? "5" : iStatusf.isChecked() ? "6" : "0");

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }


    private boolean formValidation() {
        Toast.makeText(this, "Validating This Section ", Toast.LENGTH_SHORT).show();

        if (iStatus.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "ERROR(Not Selected): " + getString(R.string.iStatus), Toast.LENGTH_LONG).show();
            iStatusa.setError("Please Select One");    // Set Error on last radio button
            Log.i(TAG, "dcstatus: This data is Required!");
            return false;
        } else {
            iStatusa.setError(null);
        }


        return true;
    }

    private boolean UpdateDB() {
        DatabaseHelper db = new DatabaseHelper(this);

       /* int updcount = db.updateEnding();

        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }*/
        return true;

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
    }




}
