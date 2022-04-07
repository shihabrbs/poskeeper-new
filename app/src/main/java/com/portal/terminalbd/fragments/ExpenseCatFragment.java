package com.portal.terminalbd.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.portal.terminalbd.R;
import com.portal.terminalbd.activity.DashboardActivity;
import com.portal.terminalbd.adapter.ExpenseCatAdapter;
import com.portal.terminalbd.model.ExpenseCategory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class ExpenseCatFragment extends Fragment {

    Realm realm;

    RealmResults<ExpenseCategory> expenseCategories;

    List<ExpenseCategory> expenseCategoryList;

    @BindView(R.id.expense_category_rv)
    RecyclerView expense_category_rv;

    ExpenseCatAdapter adapter;

    public ExpenseCatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expense_cat, container, false);

        ButterKnife.bind(this,view);

        realm = Realm.getDefaultInstance();

        ((DashboardActivity)getActivity()).setTitle("Expense Category List");

        expenseCategoryList = new ArrayList<>();

        expenseCategories = realm.where(ExpenseCategory.class).findAll();

        expenseCategoryList.addAll(realm.copyFromRealm(expenseCategories));

        adapter = new ExpenseCatAdapter(getContext(),expenseCategoryList);
        expense_category_rv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        expense_category_rv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        expense_category_rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }
}