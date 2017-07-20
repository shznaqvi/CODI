package edu.aku.hassannaqvi.codi.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.aku.hassannaqvi.codi.R;
import edu.aku.hassannaqvi.codi.core.AppMain;

public class VisitInfoActivity extends Activity {

    private static final String TAG = VisitInfoActivity.class.getSimpleName();

    @BindView(R.id.studyId)
    EditText studyId;
    @BindView(R.id.csv01)
    EditText csv01;
    @BindView(R.id.csv02)
    EditText csv02;
    @BindView(R.id.csv03)
    EditText csv03;
    @BindView(R.id.heading)
    TextView heading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_info);
        ButterKnife.bind(this);

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


                startActivity(new Intent(this, VaccineActivity.class));

            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for this Section", Toast.LENGTH_SHORT).show();


        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }

    private boolean UpdateDB() {

       /* DatabaseHelper db = new DatabaseHelper(this);

        int updcount = db.updateSRandomization(AppMain.getEnrollmentChild.get(0).getDSSID());

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
