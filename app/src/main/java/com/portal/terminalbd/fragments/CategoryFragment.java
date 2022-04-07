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
import com.portal.terminalbd.adapter.PCategoryAdapter;
import com.portal.terminalbd.model.Category;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {
    Realm realm;

    RealmResults<Category> vendors;

    List<Category> pCategoryList;

    @BindView(R.id.p_category_rv)
    RecyclerView p_category_rv;

    PCategoryAdapter adapter;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        ButterKnife.bind(this,view);

        realm = Realm.getDefaultInstance();

        ((DashboardActivity)getActivity()).setTitle("Category List");

        pCategoryList = new ArrayList<>();

        vendors = realm.where(Category.class).findAll();

        pCategoryList.addAll(realm.copyFromRealm(vendors));

        adapter = new PCategoryAdapter(getContext(),pCategoryList);
        p_category_rv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        p_category_rv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        p_category_rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }

}
