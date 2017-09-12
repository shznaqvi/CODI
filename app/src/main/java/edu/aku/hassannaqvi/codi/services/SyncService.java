package edu.aku.hassannaqvi.codi.services;

import android.accounts.Account;
import android.app.Service;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SyncResult;
import android.os.Bundle;
import android.os.IBinder;

/**
 * Created by Hassan.naqvi on 9/12/2017.
 */

public class SyncService extends Service {
    // Object to use as a thread-safe lock
    private static final Object sSyncAdapterLock = new Object();
    // Storage for an instance of the sync adapter
    private static SyncAdapter sSyncAdapter = null;

    public SyncService() {
        super();
    }

    /*
     * Instantiate the sync adapter object.
     */
    @Override
    public void onCreate() {
        /*
         * Create the sync adapter as a singleton.
         * Set the sync adapter as syncable
         * Disallow parallel syncs
         */
        synchronized (sSyncAdapterLock) {
            if (sSyncAdapter == null) {
                sSyncAdapter = new SyncAdapter(getApplicationContext(), true);
            }
        }
    }

    /**
     * Return an object that allows the system to invoke
     * the sync adapter.
     */
    @Override
    public IBinder onBind(Intent intent) {
        /*
         * Get the object that allows external processes
         * to call onPerformSync(). The object is created
         * in the base class code when the SyncAdapter
         * constructors call super()
         */
        return sSyncAdapter.getSyncAdapterBinder();
    }

    public class SyncAdapter extends AbstractThreadedSyncAdapter {

        ContentResolver mContentResolver;

        public SyncAdapter(Context context, boolean autoInitialize) {
            super(context, autoInitialize);
        /*
         * If your app uses a content resolver, get an instance of it
         * from the incoming Context
         */
            mContentResolver = context.getContentResolver();
        }

        public SyncAdapter(
                Context context,
                boolean autoInitialize,
                boolean allowParallelSyncs) {
            super(context, autoInitialize, allowParallelSyncs);

            mContentResolver = context.getContentResolver();
        }

        @Override
        public void onPerformSync(Account account, Bundle bundle, String s, ContentProviderClient contentProviderClient, SyncResult syncResult) {

        }
    }
}