package edu.aku.hassannaqvi.codi.activities;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.aku.hassannaqvi.codi.R;
import io.blackbox_vision.datetimepickeredittext.view.DatePickerInputEditText;
import io.blackbox_vision.datetimepickeredittext.view.TimePickerInputEditText;

public class EnrollmentFormActivity extends AppCompatActivity {

    @BindView(R.id.dssid)
    TextView dssid;
    @BindView(R.id.studyId)
    TextView studyId;
    @BindView(R.id.cen01)
    DatePickerInputEditText cen01;
    @BindView(R.id.cen02)
    RadioGroup cen02;
    @BindView(R.id.cen02a)
    RadioButton cen02a;
    @BindView(R.id.cen02b)
    RadioButton cen02b;
    @BindView(R.id.cen03)
    RadioGroup cen03;
    @BindView(R.id.cen03a)
    RadioButton cen03a;
    @BindView(R.id.cen03b)
    RadioButton cen03b;
    @BindView(R.id.cen04)
    EditText cen04;
    @BindView(R.id.cen05)
    EditText cen05;
    @BindView(R.id.cen06)
    DatePickerInputEditText cen06;
    @BindView(R.id.cen07w)
    EditText cen07w;
    @BindView(R.id.cen07m)
    EditText cen07m;
    @BindView(R.id.cen08)
    RadioGroup cen08;
    @BindView(R.id.cen08a)
    RadioButton cen08a;
    @BindView(R.id.cen08b)
    RadioButton cen08b;
    @BindView(R.id.cen09)
    EditText cen09;
    @BindView(R.id.cenfp)
    EditText cenfp;
    @BindView(R.id.cenmp)
    EditText cenmp;
    @BindView(R.id.cenac)
    EditText cenac;
    @BindView(R.id.cen10)
    RadioGroup cen10;
    @BindView(R.id.cen10a)
    RadioButton cen10a;
    @BindView(R.id.cen10b)
    RadioButton cen10b;
    @BindView(R.id.cen10c)
    RadioButton cen10c;
    @BindView(R.id.cen10d)
    RadioButton cen10d;
    @BindView(R.id.cen10e)
    RadioButton cen10e;
    @BindView(R.id.cen10f)
    RadioButton cen10f;
    @BindView(R.id.cen1099)
    RadioButton cen1099;
    @BindView(R.id.cen11)
    RadioGroup cen11;
    @BindView(R.id.cen11a)
    RadioButton cen11a;
    @BindView(R.id.cen11b)
    RadioButton cen11b;
    @BindView(R.id.cen11c)
    RadioButton cen11c;
    @BindView(R.id.cen11d)
    RadioButton cen11d;
    @BindView(R.id.cen11e)
    RadioButton cen11e;
    @BindView(R.id.cen11f)
    RadioButton cen11f;
    @BindView(R.id.cen1199)
    RadioButton cen1199;
    @BindView(R.id.cen12)
    RadioGroup cen12;
    @BindView(R.id.cen12a)
    RadioButton cen12a;
    @BindView(R.id.cen12b)
    RadioButton cen12b;
    @BindView(R.id.cen12c)
    RadioButton cen12c;
    @BindView(R.id.cen12d)
    RadioButton cen12d;
    @BindView(R.id.cen13)
    EditText cen13;
    @BindView(R.id.cen14)
    EditText cen14;
    @BindView(R.id.cen15)
    RadioGroup cen15;
    @BindView(R.id.cen15a)
    RadioButton cen15a;
    @BindView(R.id.cen15b)
    RadioButton cen15b;
    @BindView(R.id.cen16)
    RadioGroup cen16;
    @BindView(R.id.cen16a)
    RadioButton cen16a;
    @BindView(R.id.cen16b)
    RadioButton cen16b;
    @BindView(R.id.cen16c)
    RadioButton cen16c;
    @BindView(R.id.fldGrpcen17)
    LinearLayout fldGrpcen17;
    @BindView(R.id.cen17)
    RadioGroup cen17;
    @BindView(R.id.cen17a)
    RadioButton cen17a;
    @BindView(R.id.cen17b)
    RadioButton cen17b;
    @BindView(R.id.cen1788)
    RadioButton cen1788;
    @BindView(R.id.cen1788x)
    EditText cen1788x;
    @BindView(R.id.cen18)
    RadioGroup cen18;
    @BindView(R.id.cen18a)
    RadioButton cen18a;
    @BindView(R.id.cen18b)
    RadioButton cen18b;
    @BindView(R.id.fldGrpcen19)
    LinearLayout fldGrpcen19;
    @BindView(R.id.cen19)
    RadioGroup cen19;
    @BindView(R.id.cen19a)
    RadioButton cen19a;
    @BindView(R.id.cen19b)
    RadioButton cen19b;
    @BindView(R.id.cen20)
    DatePickerInputEditText cen20;
    @BindView(R.id.cen21)
    TimePickerInputEditText cen21;
    @BindView(R.id.cen22)
    RadioGroup cen22;
    @BindView(R.id.cen22a)
    RadioButton cen22a;
    @BindView(R.id.cen22b)
    RadioButton cen22b;
    @BindView(R.id.cen23)
    RadioGroup cen23;
    @BindView(R.id.cen23a)
    RadioButton cen23a;
    @BindView(R.id.cen23b)
    RadioButton cen23b;
    @BindView(R.id.cen24)
    EditText cen24;
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
    @BindView(R.id.cen28)
    RadioGroup cen28;
    @BindView(R.id.cen28a)
    RadioButton cen28a;
    @BindView(R.id.cen28b)
    RadioButton cen28b;
    @BindView(R.id.fldGrpcen29)
    LinearLayout fldGrpcen29;
    @BindView(R.id.cen29)
    RadioGroup cen29;
    @BindView(R.id.cen29a)
    RadioButton cen29a;
    @BindView(R.id.cen29b)
    RadioButton cen29b;
    @BindView(R.id.cen30)
    DatePickerInputEditText cen30;
    @BindView(R.id.cen31)
    TimePickerInputEditText cen31;
    @BindView(R.id.cen32)
    RadioGroup cen32;
    @BindView(R.id.cen32a)
    RadioButton cen32a;
    @BindView(R.id.cen32b)
    RadioButton cen32b;
    @BindView(R.id.cen33a)
    EditText cen33a;
    @BindView(R.id.cen33b)
    EditText cen33b;
    @BindView(R.id.cendt)
    DatePickerInputEditText cendt;
    @BindView(R.id.centime)
    TimePickerInputEditText centime;

    @BindViews({R.id.cen01, R.id.cen06, R.id.cen20, R.id.cen25,
            R.id.cen30, R.id.cendt})
    List<DatePickerInputEditText> dates;

    @BindViews({R.id.cen21, R.id.cen26, R.id.cen31, R.id.centime})
    List<TimePickerInputEditText> time;
    String dateToday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment_form);
        ButterKnife.bind(this);

        dateToday = new SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis());


        for (DatePickerInputEditText de : dates) {
            de.setManager(getSupportFragmentManager());
            de.setMaxDate(dateToday);

        }

        for (TimePickerInputEditText te : time) {
            te.setManager(getSupportFragmentManager());
        }

        cen1788.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cen1788x.setVisibility(View.VISIBLE);
                } else {
                    cen1788x.setVisibility(View.GONE);
                    cen1788x.setText(null);
                }
            }
        });

        //============= Partial Breast feeding skip pattern ===========
        cen16.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (cen16b.isChecked()) {
                    fldGrpcen17.setVisibility(View.VISIBLE);
                } else {
                    fldGrpcen17.setVisibility(View.GONE);
                    cen17.clearCheck();
                    cen1788x.setText(null);
                }
            }
        });

        //================ Blood Sampling Skip Pattern============
        cen18.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (cen18a.isChecked()) {
                    fldGrpcen19.setVisibility(View.VISIBLE);
                } else {
                    fldGrpcen19.setVisibility(View.GONE);
                    cen19.clearCheck();
                    cen20.setText(null);
                    cen21.setText(null);
                    cen22.clearCheck();
                    cen23.clearCheck();
                    cen24.setText(null);
                }
            }
        });

        //============== Vacccine after randomization skip pattern=========
        cen28.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (cen28a.isChecked()) {
                    fldGrpcen29.setVisibility(View.VISIBLE);
                } else {
                    fldGrpcen29.setVisibility(View.GONE);
                    cen29.clearCheck();
                    cen30.setText(null);
                    cen31.setText(null);
                    cen32.clearCheck();
                    cen33a.setText(null);
                    cen33b.setText(null);
                }
            }
        });



    }

    @OnClick(R.id.btnScan)
    void onBtnScanClick() {
        //TODO implement

        cen24.setText(null);
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scan a blood sample sticker");
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(true);
        integrator.setOrientationLocked(false);

    }


    @OnClick(R.id.btn_Continue)
    void onBtnContinueClick() {
        //TODO implement

    }

    private void SaveDraft() throws JSONException {
        Toast.makeText(this, "Saving Draft for this Section", Toast.LENGTH_SHORT).show();

        JSONObject sen = new JSONObject();

        sen.put("dssid", dssid.getText().toString());
        sen.put("studyId", studyId.getText().toString());
        sen.put("cen01", cen01.getText().toString());
        sen.put("cen02", cen02a.isChecked() ? "1" : cen02b.isChecked() ? "2" : "0");
        sen.put("cen03", cen03a.isChecked() ? "1" : cen03b.isChecked() ? "2" : "0");
        sen.put("cen04", cen04.getText().toString());
        sen.put("cen05", cen05.getText().toString());
        sen.put("cen06", cen06.getText().toString());
        sen.put("cen07w", cen07w.getText().toString());
        sen.put("cen07m", cen07m.getText().toString());
        sen.put("cen08", cen08a.isChecked() ? "1" : cen08b.isChecked() ? "2" : "0");
        sen.put("cen09", cen09.getText().toString());
        sen.put("cenfp", cenfp.getText().toString());
        sen.put("cenmp", cenmp.getText().toString());
        sen.put("cenac", cenac.getText().toString());
        sen.put("cen10", cen10a.isChecked() ? "1" : cen10b.isChecked() ? "2" : cen10c.isChecked() ? "3" : cen10d.isChecked() ? "4" : cen10e.isChecked() ? "5" : cen10f.isChecked() ? "6" : cen1099.isChecked() ? "99" : "0");
        sen.put("cen11", cen11a.isChecked() ? "1" : cen11b.isChecked() ? "2" : cen11c.isChecked() ? "3" : cen11d.isChecked() ? "4" : cen11e.isChecked() ? "5" : cen11f.isChecked() ? "6" : "0");
        sen.put("cen12", cen12a.isChecked() ? "1" : cen12b.isChecked() ? "2" : cen12c.isChecked() ? "3" : cen12d.isChecked() ? "4" : "0");
        sen.put("cen13", cen13.getText().toString());
        sen.put("cen14", cen14.getText().toString());
        sen.put("cen15", cen15a.isChecked() ? "1" : cen15b.isChecked() ? "2" : "0");
        sen.put("cen16", cen16a.isChecked() ? "1" : cen16b.isChecked() ? "2" : cen16c.isChecked() ? "3" : "0");
        sen.put("cen17", cen17a.isChecked() ? "1" : cen17b.isChecked() ? "2" : cen1788.isChecked() ? "3" : "0");
        sen.put("cen1788x", cen1788x.getText().toString());
        sen.put("cen18", cen18a.isChecked() ? "1" : cen18b.isChecked() ? "2" : "0");
        sen.put("cen19", cen19a.isChecked() ? "1" : cen19b.isChecked() ? "2" : "0");
        sen.put("cen20", cen20.getText().toString());
        sen.put("cen21", cen21.getText().toString());
        sen.put("cen22", cen22a.isChecked() ? "1" : cen22b.isChecked() ? "2" : "0");
        sen.put("cen23", cen23a.isChecked() ? "1" : cen23b.isChecked() ? "2" : "0");
        sen.put("cen24", cen24.getText().toString());
        sen.put("cen25", cen25.getText().toString());
        sen.put("cen26", cen26.getText().toString());
        sen.put("cen27", cen27a.isChecked() ? "1" : cen27b.isChecked() ? "2" : cen27c.isChecked() ? "3" : cen27d.isChecked() ? "4" : "0");
        sen.put("cen28", cen28a.isChecked() ? "1" : cen28b.isChecked() ? "2" : "0");
        sen.put("cen29", cen29a.isChecked() ? "1" : cen29b.isChecked() ? "2" : "0");
        sen.put("cen30", cen30.getText().toString());
        sen.put("cen31", cen31.getText().toString());
        sen.put("cen32", cen32a.isChecked() ? "1" : cen32b.isChecked() ? "2" : "0");
        sen.put("cen33a", cen33a.getText().toString());
        sen.put("cen33b", cen33b.getText().toString());
        sen.put("cendt", cendt.getText().toString());
        sen.put("centime", centime.getText().toString());

        Toast.makeText(this, "Validation Successful! - Saving Draft...", Toast.LENGTH_SHORT).show();
    }





}
