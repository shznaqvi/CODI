package edu.aku.hassannaqvi.codi.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.hassannaqvi.codi.R;
import edu.aku.hassannaqvi.codi.core.AndroidDatabaseManager;
import edu.aku.hassannaqvi.codi.core.AppMain;
import edu.aku.hassannaqvi.codi.core.DatabaseHelper;
import edu.aku.hassannaqvi.codi.sync.SyncChildren;
import edu.aku.hassannaqvi.codi.sync.SyncForms;
import edu.aku.hassannaqvi.codi.sync.SyncFormsV2;
import edu.aku.hassannaqvi.codi.sync.SyncFormsV3;
import edu.aku.hassannaqvi.codi.sync.SyncFormsV4;
import edu.aku.hassannaqvi.codi.sync.SyncFormsV5;

public class MainActivity extends Activity {

    private final String TAG = "MainActivity";

    String dtToday = new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime());
    @BindView(R.id.adminsec)
    LinearLayout adminsec;
    @BindView(R.id.lblheader)
    TextView lblheader;
    @BindView(R.id.recordSummary)
    TextView recordSummary;

    @BindView(R.id.syncDevice)
    Button syncDevice;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    AlertDialog.Builder builder;
    String m_Text = "";
    private ProgressDialog pd;
    private Boolean exit = false;
    private String rSumText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        if (AppMain.admin) {
            adminsec.setVisibility(View.VISIBLE);
        } else {
            adminsec.setVisibility(View.GONE);
        }

        // Reset working variables
        AppMain.child_name = "Test";

        sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);
        editor = sharedPref.edit();

        builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Tag Name");

        final EditText input = new EditText(MainActivity.this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
                if (!m_Text.equals("")) {
                    editor.putString("tagName", m_Text);
                    editor.commit();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        if (sharedPref.getString("tagName", null) == "" || sharedPref.getString("tagName", null) == null) {
            builder.show();
        }


        DatabaseHelper db = new DatabaseHelper(this);
        if (db.getAllEnrolled() != null) {
            recordSummary.setText("Enrolled: " + db.getAllEnrolled());
        }
  /*      Collection<FormsContract> todayForms = db.getTodayForms();
        Collection<FormsContract> unsyncedForms = db.getUnsyncedForms();
        Collection<FormsContract> unsyncedForms2 = db.getUnsyncedFormsV2();
        Collection<FormsContract> unsyncedForms3 = db.getUnsyncedFormsV3();
        Collection<FormsContract> unsyncedForms4 = db.getUnsyncedFormsV4();
        Collection<FormsContract> unsyncedForms5 = db.getUnsyncedFormsV5();


        rSumText += "TODAY'S RECORDS SUMMARY\r\n";

        rSumText += "=======================\r\n";
        rSumText += "\r\n";
        rSumText += "Total Forms Today: " + todaysForms.size() + "\r\n";
        rSumText += "\r\n";
        if (todaysForms.size() > 0) {
            rSumText += "\tFORMS' LIST: \r\n";
            String iStatus;
            rSumText += "--------------------------------------------------\r\n";
            rSumText += "[ DSS_ID ] \t[Form Status] \t[Sync Status]----------\r\n";
            rSumText += "--------------------------------------------------\r\n";

            for (FormsContract fc : todayForms) {
            for (FormsContract fc : todaysForms) {
                if (fc.getIstatus() != null) {
                    switch (fc.getIstatus()) {
                        case "1":
                            iStatus = "\tComplete";
                            break;
                        case "2":
                            iStatus = "\tIncomplete";
                            break;
                        case "3":
                            iStatus = "\tRefused";
                            break;
                        case "4":
                            iStatus = "\tRefused";
                            break;
                        default:
                            iStatus = "\tN/A";
                    }
                } else {
                    iStatus = "\tN/A";
                }

                rSumText += fc.get_ID();

                rSumText += " " + iStatus + " ";

                rSumText += (fc.getSynced() == null ? "\t\tNot Synced" : "\t\tSynced");
                rSumText += "\r\n";
                rSumText += "--------------------------------------------------\r\n";
            }
        }
        if (AppMain.admin) {
            adminsec.setVisibility(View.VISIBLE);
            SharedPreferences syncPref = getSharedPreferences("SyncInfo", Context.MODE_PRIVATE);
            rSumText += "Last Data Download: \t" + syncPref.getString("LastDownSyncServer", "Never Updated");
            rSumText += "\r\n";
            rSumText += "Last Data Upload: \t" + syncPref.getString("LastUpSyncServer", "Never Synced");
            rSumText += "\r\n";
            rSumText += "Unsynced Forms: \t" + unsyncedForms.size();
            rSumText += "\r\n";
            rSumText += "Unsynced FormsV2: \t" + unsyncedForms2.size();
            rSumText += "\r\n";
            rSumText += "Unsynced FormsV3: \t" + unsyncedForms3.size();
            rSumText += "\r\n";
            rSumText += "Unsynced FormsV4: \t" + unsyncedForms4.size();
            rSumText += "\r\n";
            rSumText += "Unsynced FormsV5: \t" + unsyncedForms5.size();
            rSumText += "\r\n";


        }
        Log.d(TAG, "onCreate: " + rSumText);
        recordSummary.setText(rSumText);


    }*/
    }

    public void openForm(View v) {
        if (sharedPref.getString("tagName", null) != "" && sharedPref.getString("tagName", null) != null) {
            Intent oF = new Intent(MainActivity.this, EligibilityFormActivity.class);
            startActivity(oF);
            AppMain.formType = "V1";
        } else {

            builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Tag Name");

            final EditText input = new EditText(MainActivity.this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    m_Text = input.getText().toString();
                    if (!m_Text.equals("")) {
                        editor.putString("tagName", m_Text);
                        editor.commit();

                        Intent oF = new Intent(MainActivity.this, EligibilityFormActivity.class);
                        startActivity(oF);
                    }
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();
        }
    }

    public void openForm1(View v) {
        Intent iMem = new Intent(this, EnrollmentInfoActivity.class);
        startActivity(iMem);
        AppMain.formType = "V1";
    }

    public void openV2(View v) {
        Intent v2 = new Intent(this, VisitInfoActivity.class);
        startActivity(v2);
        AppMain.formType = "V2";
    }

    public void openV3(View v) {
        Intent v3 = new Intent(this, VisitInfoActivity.class);
        AppMain.formType = "V3";
        startActivity(v3);

    }

    public void openV4(View v) {
        Intent v4 = new Intent(this, VisitInfoActivity.class);
        startActivity(v4);
        AppMain.formType = "V4";
    }

    public void openV5(View v) {
        Intent v5 = new Intent(this, VisitInfoActivity.class);
        startActivity(v5);
        AppMain.formType = "V5";
    }

    public void openMembers(View v) {
       /* Intent iMem = new Intent(this, MotherListActivity.class);
        startActivity(iMem);
    }

    public void openA(View v) {
       /* Intent iA = new Intent(this, SectionAActivity.class);
        startActivity(iA);*/
    }

    public void openB(View v) {
       /* Intent iB = new Intent(this, SectionBActivity.class);
        startActivity(iB);*/
    }

    public void openC(View v) {
        /*Intent iC = new Intent(this, SectionCActivity.class);
        startActivity(iC);*/
    }

    public void openD(View v) {
       /* Intent iD = new Intent(this, SectionDActivity.class);
        startActivity(iD);*/
    }

    public void openE(View v) {
      /*  Intent iD = new Intent(this, SectionEActivity.class);
        startActivity(iD);*/
    }

    public void openF(View v) {
        /*Intent iD = new Intent(this, SectionFActivity.class);
        startActivity(iD);*/
    }

    public void openG(View v) {
      /*  Intent iG = new Intent(this, SectionGActivity.class);
        startActivity(iG);*/
    }

    public void openH(View v) {
      /*  Intent iEnd = new Intent(this, SectionHActivity.class);
        startActivity(iEnd);*/
    }

    public void openI(View v) {
       /* Intent iEnd = new Intent(this, SectionIActivity.class);
        startActivity(iEnd);*/
    }

    public void openJ(View v) {
       /* Intent iEnd = new Intent(this, SectionJActivity.class);
        startActivity(iEnd);*/
    }

    public void openK(View v) {
      /*  Intent iEnd = new Intent(this, SectionKActivity.class);
        startActivity(iEnd);*/
    }

    public void openL(View v) {
       /* Intent iEnd = new Intent(this, SectionLActivity.class);
        startActivity(iEnd);*/
    }

    public void openM(View v) {
       /* Intent iEnd = new Intent(this, SectionMActivity.class);
        startActivity(iEnd);*/
    }

    public void testGPS(View v) {

        SharedPreferences sharedPref = getSharedPreferences("GPSCoordinates", Context.MODE_PRIVATE);
        Log.d("MAP", "testGPS: " + sharedPref.getAll().toString());
        Map<String, ?> allEntries = sharedPref.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            Log.d("Map", entry.getKey() + ": " + entry.getValue().toString());
        }

    }

    public void openDB(View v) {
        Intent dbmanager = new Intent(getApplicationContext(), AndroidDatabaseManager.class);
        startActivity(dbmanager);
    }

    public void CheckCluster(View v) {
        /*Intent cluster_list = new Intent(getApplicationContext(), FormsList.class);
        cluster_list.putExtra("dssid", AppMain.regionDss);
        startActivity(cluster_list);
*/
    }

    public void syncServer(View view) {

        // Require permissions INTERNET & ACCESS_NETWORK_STATE
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Toast.makeText(getApplicationContext(), "Syncing Children", Toast.LENGTH_SHORT).show();
            new SyncChildren(this).execute();
            Toast.makeText(getApplicationContext(), "Syncing Forms", Toast.LENGTH_SHORT).show();
            new SyncForms(this).execute();
            new SyncFormsV2(this).execute();
            new SyncFormsV3(this).execute();
            new SyncFormsV4(this).execute();
            new SyncFormsV5(this).execute();

/*            Toast.makeText(getApplicationContext(), "Syncing Eligiblity", Toast.LENGTH_SHORT).show();
            new SyncEligibilities(this).execute();*/



            /*Toast.makeText(getApplicationContext(), "Syncing Mother", Toast.LENGTH_SHORT).show();
            new SyncMother(this).execute();

            Toast.makeText(getApplicationContext(), "Syncing IM", Toast.LENGTH_SHORT).show();
            new SyncIM(this).execute();*/

            SharedPreferences syncPref = getSharedPreferences("SyncInfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = syncPref.edit();

            editor.putString("LastUpSyncServer", dtToday);

            editor.apply();

        } else {
            Toast.makeText(this, "No network connection available.", Toast.LENGTH_SHORT).show();
        }

    }

    public void syncDevice(View view) {

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            // Sync Users
           /* BackgroundDrawable bg = new BackgroundDrawable();
            syncDevice.setBackground(bg);
            bg.start();*/
           /* new GetMembers(this).execute();*/
            //bg.stop();

            SharedPreferences syncPref = getSharedPreferences("SyncInfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = syncPref.edit();

            editor.putString("LastDownSyncServer", dtToday);

            editor.apply();
        } else {
            Toast.makeText(this, "No network connection available.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity

            startActivity(new Intent(this, LoginActivity.class));

        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }
    }

}