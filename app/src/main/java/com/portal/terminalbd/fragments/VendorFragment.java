package com.portal.terminalbd.fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.portal.terminalbd.activity.DashboardActivity;
import com.portal.terminalbd.R;
import com.portal.terminalbd.adapter.VendorAdapter;
import com.portal.terminalbd.model.Vendor;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class VendorFragment extends Fragment {

    Realm realm;

    RealmResults<Vendor> vendors;

    List<Vendor> vendorList;

    @BindView(R.id.vendor_rv)
    RecyclerView vendor_rv;

    VendorAdapter adapter;

    public VendorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vendor, container, false);

        ButterKnife.bind(this,view);

        realm = Realm.getDefaultInstance();

        ((DashboardActivity)getActivity()).setTitle("Vendor List");

        vendorList = new ArrayList<>();

        vendors = realm.where(Vendor.class).findAll();

        vendorList.addAll(realm.copyFromRealm(vendors));

        adapter = new VendorAdapter(getContext(),vendorList);
        vendor_rv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
     //   vendor_rv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        vendor_rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }

}
