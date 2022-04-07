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
import com.portal.terminalbd.adapter.UserAdapter;
import com.portal.terminalbd.model.SystemUser;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    Realm realm;

    RealmResults<SystemUser> systemUsers;

    List<SystemUser> systemUserList;

    @BindView(R.id.user_rv)
    RecyclerView user_rv;

    UserAdapter adapter;

    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        ButterKnife.bind(this,view);

        realm = Realm.getDefaultInstance();

        ((DashboardActivity)getActivity()).setTitle("User List");

        systemUserList = new ArrayList<>();

        systemUsers = realm.where(SystemUser.class).findAll();

        systemUserList.addAll(realm.copyFromRealm(systemUsers));

        adapter = new UserAdapter(getContext(),systemUserList);
        user_rv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        user_rv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        user_rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }

}
