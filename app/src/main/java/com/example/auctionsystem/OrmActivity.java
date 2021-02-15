package com.example.auctionsystem;

import android.os.Bundle;

import com.example.auctionsystem.db.DatabaseHelper;

public  class OrmActivity extends BaseActivity {
    private DatabaseHelper databaseHelper = null;
    public DatabaseHelper getDBHelper() {
        if (databaseHelper == null) {
            databaseHelper = (DatabaseHelper) OrmHelperManager.getHelper(TAG, this, DatabaseHelper.class);
        }
        return databaseHelper;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (databaseHelper != null) {
            OrmHelperManager.releaseHelper(TAG);
            databaseHelper = null;
        }
    }
    protected abstract void onStop();



}