package edu.aku.hassannaqvi.codi.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.aku.hassannaqvi.codi.R;
import edu.aku.hassannaqvi.codi.contracts.FormsContract;
import edu.aku.hassannaqvi.codi.core.AppMain;
import edu.aku.hassannaqvi.codi.core.DatabaseHelper;

public class VisitInfoActivity extends Activity {

    private static final String TAG = VisitInfoActivity.class.getSimpleName();

    @BindView(R.id.studyId)
    EditText studyId;
    @BindView(R.id.csv01)
    EditText csv01;
    @BindView(R.id.csv02)
    EditText csv02;
    //@BindView(R.id.csv03)
    //EditText csv03;
    @BindView(R.id.heading)
    TextView heading;

    boolean check = false;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_info);
        ButterKnife.bind(this);

        db = new DatabaseHelper(this);

        if (AppMain.formType.equals("V2")) {
            heading.setText(R.string.csvheading);

        } else if (AppMain.formType.equals("V3")) //&& AppMain.cc.getArmGrp().equals("AB"))
        {
            heading.setText(R.string.ctvheading);
        }/*else if(AppMain.formType.equals("V3") && AppMain.cc.getArmGrp().equals("CD"))
        {
            heading.setText(R.string.ctvbheading);
        }*/ else if (AppMain.formType.equals("V4")) {
            heading.setText(R.string.cfvheading);
        } else if (AppMain.formType.equals("V5")) {
            heading.setText(R.string.cfivheading);
        }

        /*csv01.setText(AppMain.fc.getNextApp());
        csv02.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(System.currentTimeMillis()));
        csv03.setText(AppMain.cc.getArmSlc());

*/

    }

    @OnClick(R.id.btn_End)
    void onBtnEndClick() {
        //TODO implement
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


                startActivity(new Intent(this, ChildHealthAndBreastFeedActivity.class));

            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @OnClick(R.id.btnCheck)
    void onBtnCheckClick() {


        AppMain.getVisitChild = db.getChildByStudyID(studyId.getText().toString().toUpperCase());

        if (AppMain.getVisitChild.size() != 0) {

            if (getDays(AppMain.getVisitChild.get(0).getNextApp())) {

                Toast.makeText(getApplicationContext(), "Children found", Toast.LENGTH_LONG).show();

                csv01.setText(AppMain.getVisitChild.get(0).getNextApp());

                csv02.setText(new SimpleDateFormat("dd-MM-yyyy").format(System.currentTimeMillis()));

                //fldGrpChild.setVisibility(View.VISIBLE);

                check = true;
            } else {
                Toast.makeText(getApplicationContext(), "Children Already Randomized!", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Children Not found", Toast.LENGTH_LONG).show();

            check = false;
        }
    }

    public Boolean getDays(String date) {
        SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");


        try {
            Date date1 = myFormat.parse(AppMain.convertDateFormat(date));
            Date date2 = myFormat.parse("25-08-2017");
            long diff = date2.getTime() - date1.getTime();

            return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) <= 7;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }


    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for this Section", Toast.LENGTH_SHORT).show();
        SharedPreferences sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);
        AppMain.fc = new FormsContract();

        AppMain.fc.setDevicetagID(sharedPref.getString("tagName", null));
        AppMain.fc.setFormDate(new Date().toString());
        AppMain.fc.setUser(AppMain.userName);
        AppMain.fc.setDeviceID(Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID));
        AppMain.fc.setDSSID(AppMain.getVisitChild.get(0).getDSSID());
        AppMain.fc.setChildName(AppMain.getVisitChild.get(0).getChildName());
        AppMain.fc.setStudyID(studyId.getText().toString());
        //AppMain.motherName = celmn.getText().toString();
        //AppMain.dob = celdob.getText().toString();
        //AppMain.fc.setFormType("V1");
        AppMain.fc.setFormType("V2");

        JSONObject sInfo = new JSONObject();

        sInfo.put("csv01", csv01.getText().toString());
        sInfo.put("csv02", csv02.getText().toString());

        AppMain.fc.setsInfo(String.valueOf(sInfo));

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();


    }

    private boolean UpdateDB() {

        DatabaseHelper db = new DatabaseHelper(this);

        long updcount = db.addEnrollment(AppMain.fc);

        AppMain.fc.set_ID(String.valueOf(updcount));

        if (updcount != 0) {
            Toast.makeText(this, "Updating Database... Successful!", Toast.LENGTH_SHORT).show();

            AppMain.fc.set_UID(
                    (AppMain.fc.getDeviceID() + AppMain.fc.get_ID()));
            db.updateFormID();
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    public boolean ValidateForm() {

        /*if (cen25.getText().toString().isEmpty()) {
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

*/
        return true;
    }

   /* @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "You Can't go back", Toast.LENGTH_LONG).show();
    }
*/



}
