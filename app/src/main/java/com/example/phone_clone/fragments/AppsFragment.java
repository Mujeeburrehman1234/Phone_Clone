package com.example.phone_clone.fragments;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phone_clone.R;
import com.example.phone_clone.adapter.ApplicationAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.example.phone_clone.Model.AppIication;

public class AppsFragment extends Fragment {
    public AppsFragment() {
    }
    RecyclerView mRecyclerView;
    AsyncTaskRunner asyn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=  inflater.inflate ( R.layout.apps_fragment ,container,false);
        mRecyclerView=view.findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));
        asyn = new AsyncTaskRunner();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            asyn.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else
            asyn.execute();
        return view;
    }
    private ArrayList<AppIication> getInstalledApps(boolean getSysPackages) {
        ArrayList<AppIication> appsList = new ArrayList<AppIication>();
        //  List<ApplicationInfo> apps = getContext().getPackageManager().getInstalledApplications(0);
        List<PackageInfo> packs = getContext().getPackageManager().getInstalledPackages(0);
        for(int i = 0; i<packs.size(); i++) {

            PackageInfo p = packs.get(i);

            ApplicationInfo a=p.applicationInfo;
            File apk = new File(a.publicSourceDir);

            String path=apk.getAbsolutePath();
            if ((!getSysPackages) && (p.versionName == null)) {
                continue ;
            }
            AppIication newApp = new AppIication();
            newApp.setAppname(p.applicationInfo.loadLabel(getContext().getPackageManager()).toString());
            newApp.setPname(p.packageName);
            newApp.setPath(path);

            newApp.setIcon(p.applicationInfo.loadIcon(getContext().getPackageManager()));
            appsList.add(newApp);
        }
        return appsList;
    }
    private class AsyncTaskRunner extends AsyncTask<Void, Void, Void> {
        List<AppIication> appList;


        @Override
        protected Void doInBackground(Void... params) {

            try {
                appList=getInstalledApps(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            try {
                if(appList.size()> 0) {
                    mRecyclerView.setAdapter(new ApplicationAdapter(getActivity(), appList));
                }
                mRecyclerView.setVisibility(View.VISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPreExecute() {
            try {
                mRecyclerView.setVisibility(View.GONE);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }
}
