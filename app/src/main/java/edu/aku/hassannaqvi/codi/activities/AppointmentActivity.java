package edu.aku.hassannaqvi.codi.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.aku.hassannaqvi.codi.R;
import edu.aku.hassannaqvi.codi.core.AppMain;
import edu.aku.hassannaqvi.codi.core.DatabaseHelper;

public class AppointmentActivity extends Activity {

    private static final String TAG = AppointmentActivity.class.getSimpleName();
    final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    @BindView(R.id.cendt)
    TextView cendt;
    @BindView(R.id.centime)
    TextView centime;
    @BindView(R.id.heading)
    TextView heading;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        ButterKnife.bind(this);
        //Calendar cal = AppMain.getCalendarDate(AppMain.visitList.get(0).getEXPECTEDDT());



        if (AppMain.fc.getFormType().equals("V1")) {
            heading.setText(getResources().getString(R.string.cenascsub));
            Calendar cal = AppMain.getCalendarDate(AppMain.enrollDate);
            cal.add(Calendar.DAY_OF_MONTH, 28);
            cendt.setText("Date: " + sdf.format(cal.getTime()) + "\n\nTime : " + new SimpleDateFormat("HH:mm").format(System.currentTimeMillis()));
        } else if (AppMain.fc.getFormType().equals("V2") && AppMain.fc.getStudyID().contains("14W")) {
            heading.setText(getResources().getString(R.string.csvatcsub));
            Calendar cal = AppMain.getCalendarDate(AppMain.visitList.get(0).getEXPECTEDDT());
            cal.add(Calendar.MONTH, 6);
            cendt.setText("Date: " + sdf.format(cal.getTime()) + "\n\nTime : " + new SimpleDateFormat("HH:mm").format(System.currentTimeMillis()));
        } else if (AppMain.fc.getFormType().equals("V2") && AppMain.fc.getStudyID().contains("09M")) {
            heading.setText(getResources().getString(R.string.csvatcsub1));
            Calendar cal = AppMain.getCalendarDate(AppMain.visitList.get(0).getEXPECTEDDT());
            cal.add(Calendar.MONTH, 11);
            cendt.setText("Date: " + sdf.format(cal.getTime()) + "\n\nTime : " + new SimpleDateFormat("HH:mm").format(System.currentTimeMillis()));
        } else if (AppMain.fc.getFormType().equals("V3")) {
            heading.setText(getResources().getString(R.string.ctvafcsub));
            Calendar cal = AppMain.getCalendarDate(AppMain.visitList.get(0).getEXPECTEDDT());
            cal.add(Calendar.MONTH, 1);
            cendt.setText("Date: " + sdf.format(cal.getTime()) + "\n\nTime : " + new SimpleDateFormat("HH:mm").format(System.currentTimeMillis()));
        } else if (AppMain.fc.getFormType().equals("V4")) {
            heading.setText(getResources().getString(R.string.cfvafcsub));
            Calendar cal = AppMain.getCalendarDate(AppMain.visitList.get(0).getEXPECTEDDT());
            cal.add(Calendar.MONTH, 11);
            cendt.setText("Date: " + sdf.format(cal.getTime()) + "\n\nTime : " + new SimpleDateFormat("HH:mm").format(System.currentTimeMillis()));
        }



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

        //if (()) {
        try {
            SaveDraft();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (UpdateDB()) {
            Toast.makeText(this, "Starting Next Section", Toast.LENGTH_SHORT).show();

            finish();
            Intent endSec = new Intent(this, EndingActivity.class);
            endSec.putExtra("complete", true);
            startActivity(endSec);

        } else {
            Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
        }
        // }



    }

    private boolean UpdateDB() {

        DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateNextApp();

        if (updcount == 1) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for this Section", Toast.LENGTH_SHORT).show();


        if (AppMain.fc.getFormType().equals("V1")) {
            Calendar cal = AppMain.getCalendarDate(AppMain.enrollDate);
            cal.add(Calendar.DAY_OF_MONTH, 28);
            AppMain.fc.setNextApp(new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime()) + " " + new SimpleDateFormat("HH:mm").format(System.currentTimeMillis()));
        } else if (AppMain.fc.getFormType().equals("V2") && AppMain.fc.getStudyID().contains("14W")) {
            Calendar cal = AppMain.getCalendarDate(AppMain.visitList.get(0).getEXPECTEDDT());
            cal.add(Calendar.MONTH, 6);
            AppMain.fc.setNextApp(new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime()) + " " + new SimpleDateFormat("HH:mm").format(System.currentTimeMillis()));
        } else if (AppMain.fc.getFormType().equals("V2") && AppMain.fc.getStudyID().contains("09M")) {
            Calendar cal = AppMain.getCalendarDate(AppMain.visitList.get(0).getEXPECTEDDT());
            cal.add(Calendar.MONTH, 11);
            AppMain.fc.setNextApp(new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime()) + " " + new SimpleDateFormat("HH:mm").format(System.currentTimeMillis()));
        } else if (AppMain.fc.getFormType().equals("V3")) {
            Calendar cal = AppMain.getCalendarDate(AppMain.visitList.get(0).getEXPECTEDDT());
            cal.add(Calendar.MONTH, 1);
            AppMain.fc.setNextApp(new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime()) + " " + new SimpleDateFormat("HH:mm").format(System.currentTimeMillis()));
        } else if (AppMain.fc.getFormType().equals("V4")) {
            Calendar cal = AppMain.getCalendarDate(AppMain.visitList.get(0).getEXPECTEDDT());
            cal.add(Calendar.MONTH, 11);
            AppMain.fc.setNextApp(new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime()) + " " + new SimpleDateFormat("HH:mm").format(System.currentTimeMillis()));
        }


        //AppMain.fc.setNextApp(String.valueOf(nextApp));

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }




    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
    }


}
