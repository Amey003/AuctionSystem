package com.example.auctionsystem.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.auctionsystem.OrmActivity;
import com.example.auctionsystem.R;
import com.example.auctionsystem.db.DatabaseHelper;
import com.example.auctionsystem.model.User;
import com.example.auctionsystem.util.Constants;
import com.example.auctionsystem.util.PrefManager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

public abstract class BaseFragment extends Fragment {

    protected static final String TAG = BaseFragment.class.getSimpleName();
    protected ProgressDialog mProgressDialog;
    protected void showProgressDialog(String title, String message) {
        try {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
            mProgressDialog = ProgressDialog.show(getActivity(), title, message);
        }
        catch (Exception ex) {
            Log.e(TAG, "##### --> " + ex);
        }
    }
    protected void hideProgressDialog() {
        try {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
            mProgressDialog = null;
        }
        catch (Exception ex) {
            Log.e(TAG, "##### --> " + ex);
        }
    }
    protected DatabaseHelper getDBHelper() {
        DatabaseHelper dbHelper = null;
        Activity mActivity = getActivity();
        if (mActivity != null && mActivity instanceof OrmActivity) {
            dbHelper = ((OrmActivity) mActivity).getDBHelper();
        }
        return dbHelper;
    }

    protected DisplayImageOptions getDisplayOptions() {
        return new DisplayImageOptions.Builder().showImageForEmptyUri(R.drawable.no_photo).showImageOnFail(R.drawable.no_photo).showImageOnLoading(R.drawable.no_photo).displayer(new FadeInBitmapDisplayer(500)).cacheOnDisk(true).handler(new Handler()).build();
    }

    protected User getUser() {
        User user = new User();
        PrefManager prefManager = new PrefManager(getActivity());
        user.setId(Long.valueOf(prefManager.readPreference(Constants.PREF_LOGGED_USER_ID, "-1")));
        return user;
    }
    protected void updateTitle(String title) {
        Activity mActivity = getActivity();
        if (mActivity != null && mActivity instanceof ActionBarActivity) {
            mActivity.setTitle(title);
            ((ActionBarActivity) mActivity).getSupportActionBar().setTitle(title);
        }
    }
    protected void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    public abstract View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public abstract void onActivityCreated(Bundle savedInstanceState);



}
