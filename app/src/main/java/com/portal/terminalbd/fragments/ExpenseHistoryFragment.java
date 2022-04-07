package com.portal.terminalbd.fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.portal.terminalbd.activity.DashboardActivity;
import com.portal.terminalbd.R;
import com.portal.terminalbd.adapter.ExpenseHistoryAdapter;
import com.portal.terminalbd.model.ExpenseItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExpenseHistoryFragment extends Fragment {

    Realm realm;

    RealmResults<ExpenseItem> expenseHistories;

    List<ExpenseItem> expenseHistoriesList;

    @BindView(R.id.expense_history_rv)
    RecyclerView expense_history_rv;

    ExpenseHistoryAdapter adapter;

    public ExpenseHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expense_history, container, false);

        ButterKnife.bind(this,view);

        realm = Realm.getDefaultInstance();

        ((DashboardActivity)getActivity()).setTitle("Expense History");

        expenseHistoriesList = new ArrayList<>();

        expenseHistories = realm.where(ExpenseItem.class).findAll();

        expenseHistoriesList.addAll(realm.copyFromRealm(expenseHistories));

        Log.d("ExpenseList:",""+expenseHistoriesList.toString());

        adapter = new ExpenseHistoryAdapter(getContext(),expenseHistoriesList);
        expense_history_rv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        expense_history_rv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        expense_history_rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }

}
