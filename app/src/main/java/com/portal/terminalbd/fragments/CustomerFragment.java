package com.portal.terminalbd.fragments;


import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.portal.terminalbd.activity.DashboardActivity;
import com.portal.terminalbd.R;
import com.portal.terminalbd.activity.SearchSalesActivity;
import com.portal.terminalbd.adapter.CustomerAdapter;
import com.portal.terminalbd.model.AnonymousCustomer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerFragment extends Fragment {

    Realm realm;

    RealmResults<AnonymousCustomer> anonymousCustomers;

    List<AnonymousCustomer> anonymousCustomerList;

    @BindView(R.id.customer_rv)
    RecyclerView customer_rv;

    CustomerAdapter adapter;
    String TAB_FRAGMENT_TAG = "some tag name";

    public CustomerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer, container, false);
        ButterKnife.bind(this, view);

        realm = Realm.getDefaultInstance();

        ((DashboardActivity) getActivity()).setTitle("Customer List");

        anonymousCustomerList = new ArrayList<>();



        refreshData();

        return view;
    }

    public void refreshData()
    {
        anonymousCustomerList.clear();
        anonymousCustomers = realm.where(AnonymousCustomer.class).findAll();

        anonymousCustomerList.addAll(realm.copyFromRealm(anonymousCustomers));

        adapter = new CustomerAdapter(getContext(), anonymousCustomerList);
        customer_rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
       // customer_rv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        customer_rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


}
