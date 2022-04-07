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
import com.portal.terminalbd.adapter.PurchaseHistoryAdapter;
import com.portal.terminalbd.model.PurchaseHistory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class PurchaseHistoryFragment extends Fragment {

    Realm realm;

    RealmResults<PurchaseHistory> purchaseHistories;

    List<PurchaseHistory> purchaseHistoriesList;

    @BindView(R.id.purchase_rv)
    RecyclerView purchase_history_rv;

    PurchaseHistoryAdapter adapter;

    public PurchaseHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_purchase_history, container, false);

        ButterKnife.bind(this,view);

        realm = Realm.getDefaultInstance();

        ((DashboardActivity)getActivity()).setTitle("Purchase History");

        purchaseHistoriesList = new ArrayList<>();

        purchaseHistories = realm.where(PurchaseHistory.class).findAll();

        purchaseHistoriesList.addAll(realm.copyFromRealm(purchaseHistories));

        adapter = new PurchaseHistoryAdapter(getContext(),purchaseHistoriesList);
        purchase_history_rv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        purchase_history_rv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        purchase_history_rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        return view;
    }

}
