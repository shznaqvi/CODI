package edu.aku.hassannaqvi.codi.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.aku.hassannaqvi.codi.R;
import edu.aku.hassannaqvi.codi.core.AppMain;

public class AppointmentActivity extends Activity {

    private static final String TAG = AppointmentActivity.class.getSimpleName();
    final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    @BindView(R.id.cendt)
    TextView cendt;
    @BindView(R.id.centime)
    TextView centime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        ButterKnife.bind(this);

        Calendar cal = AppMain.getCalendarDate(AppMain.enrollDate);
        cal.add(Calendar.DAY_OF_MONTH, 28);
        cendt.setText("Date: " + sdf.format(AppMain.fc.getNextApp()) + "\n\nTime : " + new SimpleDateFormat("HH:mm").format(AppMain.fc.getNextApp()));
        //centime.setText(new SimpleDateFormat("hh:mm").format(System.currentTimeMillis()));


    }

    @OnClick(R.id.btn_End)
    void onBtnEndClick() {

        Toast.makeText(this, "Processing This Section", Toast.LENGTH_SHORT).show();

/*        finish();
        Toast.makeText(this, "Starting Form Ending Section", Toast.LENGTH_SHORT).show();
        Intent endSec = new Intent(this, EndingActivity.class);
        endSec.putExtra("complete", true);
        startActivity(endSec);*/

        AppMain.endActivity(this,this);

    }


    @OnClick(R.id.btn_Continue)
    void onBtnContinueClick() {
        //if (ValidateForm()) {

        Toast.makeText(this, "Starting Next Section", Toast.LENGTH_SHORT).show();

        finish();
        Intent endSec = new Intent(this, EndingActivity.class);
        endSec.putExtra("complete", true);
        startActivity(endSec);


    }

    /*private boolean UpdateDB() {

        DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.

        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }*/

    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for this Section", Toast.LENGTH_SHORT).show();

        JSONObject nextApp = new JSONObject();

        nextApp.put("cendt", cendt.getText().toString());


        AppMain.fc.setNextApp(String.valueOf(nextApp));

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }




    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
    }


}
