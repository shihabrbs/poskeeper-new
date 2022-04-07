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
import com.portal.terminalbd.adapter.BankAdapter;
import com.portal.terminalbd.adapter.MobileAdapter;
import com.portal.terminalbd.model.BankAccount;
import com.portal.terminalbd.model.MobileAccount;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentListFragment extends Fragment {

    Realm realm;

    RealmResults<MobileAccount> mobileAccounts;
    RealmResults<BankAccount> bankAccounts;

    List<MobileAccount> mobileAcoountsList;
    List<BankAccount> bankAcoountsList;

    @BindView(R.id.mobile_rv)
    RecyclerView mobile_rv;

    @BindView(R.id.bank_rv)
    RecyclerView bank_rv;

    MobileAdapter mobileAdapter;

    BankAdapter bankAdapter;

    public PaymentListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment_list, container, false);

        ButterKnife.bind(this,view);

        realm = Realm.getDefaultInstance();

        ((DashboardActivity)getActivity()).setTitle("Account List");

        mobileAcoountsList = new ArrayList<>();
        bankAcoountsList = new ArrayList<>();

        mobileAccounts = realm.where(MobileAccount.class).findAll();
        bankAccounts = realm.where(BankAccount.class).findAll();

        mobileAcoountsList.addAll(realm.copyFromRealm(mobileAccounts));
        bankAcoountsList.addAll(realm.copyFromRealm(bankAccounts));

        /*
        mobile adapter
         */
        mobileAdapter = new MobileAdapter(getContext(),mobileAcoountsList);
        mobile_rv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        mobile_rv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        mobile_rv.setAdapter(mobileAdapter);
        mobileAdapter.notifyDataSetChanged();

         /*
        bank adapter
         */
        bankAdapter = new BankAdapter(getContext(),bankAcoountsList);
        bank_rv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        bank_rv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        bank_rv.setAdapter(bankAdapter);
        bankAdapter.notifyDataSetChanged();


        return view;
    }

}
