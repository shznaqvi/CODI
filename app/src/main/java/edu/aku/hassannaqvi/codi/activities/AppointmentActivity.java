package edu.aku.hassannaqvi.codi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.LinearLayout;
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
import io.blackbox_vision.datetimepickeredittext.view.DatePickerInputEditText;

public class AppointmentActivity extends AppCompatActivity {

    private static final String TAG = AppointmentActivity.class.getSimpleName();
    final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    @BindView(R.id.cendt)
    TextView cendt;
    @BindView(R.id.centime)
    TextView centime;
    @BindView(R.id.heading)
    TextView heading;
    @BindView(R.id.cendtedt)
    DatePickerInputEditText cendtedt;
    Calendar cal;
    @BindView(R.id.fldGrpMissed)
    LinearLayout fldGrpMissed;
    Calendar today = Calendar.getInstance();
    String txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        ButterKnife.bind(this);
        cendtedt.setManager(getSupportFragmentManager());

        cendtedt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (MainActivity.missed) {
                    txt = cendtedt.getText().toString();
                    cendt.setText("Date: " + txt + "\n\nTime : " + new SimpleDateFormat("HH:mm").format(System.currentTimeMillis()));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        if (!MainActivity.missed) {
            cal = AppMain.getCalendarDate(AppMain.enrollDate);

            if (AppMain.fc.getFormType().equals("V1")) {
                heading.setText(getResources().getString(R.string.cenascsub));
                cal.add(Calendar.DAY_OF_MONTH, 28);
                cendt.setText("Date: " + sdf.format(cal.getTime()) + "\n\nTime : " + new SimpleDateFormat("HH:mm").format(System.currentTimeMillis()));
            } else if (AppMain.fc.getFormType().equals("V3") && AppMain.fc.getStudyID().contains("14W")) {
                heading.setText("Appointment for Next Visit");
                today.add(Calendar.DAY_OF_MONTH, 28);
                cendt.setText("Date: " + sdf.format(today.getTime()) + "\n\nTime : " + new SimpleDateFormat("HH:mm").format(System.currentTimeMillis()));
            } else {
                heading.setText("Appointment for Next Visit");
                cendt.setText("Date: " + AppMain.convertDateFormat(AppMain.visitList.get(0).getEXPECTEDDT()) + "\n\nTime : " + new SimpleDateFormat("HH:mm").format(System.currentTimeMillis()));
            }
            fldGrpMissed.setVisibility(View.GONE);
            cendtedt.setText(null);

        } else {
            fldGrpMissed.setVisibility(View.VISIBLE);
            heading.setText("Appointment for Next Visit");

        }



    }

    @OnClick(R.id.btn_End)
    void onBtnEndClick() {

        Toast.makeText(this, "Processing This Section", Toast.LENGTH_SHORT).show();

        AppMain.endActivity(this,this);

    }


    @OnClick(R.id.btn_Continue)
    void onBtnContinueClick() {

        Toast.makeText(this, "Starting Next Section", Toast.LENGTH_SHORT).show();

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


        if (!MainActivity.missed) {
            if (AppMain.fc.getFormType().equals("V1")) {
                AppMain.fc.setNextApp(new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime()) + " " + new SimpleDateFormat("HH:mm").format(System.currentTimeMillis()));
            } else if (AppMain.fc.getFormType().equals("V3") && AppMain.fc.getStudyID().contains("14W")) {
                AppMain.fc.setNextApp(new SimpleDateFormat("dd-MM-yyyy").format(today.getTime()) + " " + new SimpleDateFormat("HH:mm").format(System.currentTimeMillis()));
            } else {
                AppMain.fc.setNextApp(AppMain.visitList.get(0).getEXPECTEDDT() + " " + new SimpleDateFormat("HH:mm").format(System.currentTimeMillis()));
            }
        } else {
            AppMain.fc.setNextApp(cendtedt.getText().toString() + " " + new SimpleDateFormat("HH:mm").format(System.currentTimeMillis()));
        }


        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }




    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
    }


}
