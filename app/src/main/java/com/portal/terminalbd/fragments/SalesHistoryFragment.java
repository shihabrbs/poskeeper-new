package com.portal.terminalbd.fragments;


import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.portal.terminalbd.activity.DashboardActivity;
import com.portal.terminalbd.R;
import com.portal.terminalbd.adapter.SalesHistoryAdapter;
import com.portal.terminalbd.model.SalesHistory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * A simple {@link Fragment} subclass.
 */
public class SalesHistoryFragment extends Fragment implements SearchView.OnQueryTextListener{

    Realm realm;

    RealmResults<SalesHistory> salesHistories;

    List<SalesHistory> salesHistoriesList;

    @BindView(R.id.sales_history_rv)
    RecyclerView sales_history_rv;

    SalesHistoryAdapter adapter;

    public SalesHistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sales_history, container, false);

        ButterKnife.bind(this,view);

        realm = Realm.getDefaultInstance();

        ((DashboardActivity)getActivity()).setTitle("Sales History");

        salesHistoriesList = new ArrayList<>();

        salesHistories = realm.where(SalesHistory.class).sort("invoiceId", Sort.DESCENDING).findAll();

        salesHistoriesList.addAll(realm.copyFromRealm(salesHistories));

        adapter = new SalesHistoryAdapter(getContext(),salesHistoriesList);
        sales_history_rv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        sales_history_rv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        sales_history_rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        final List<SalesHistory> filteredModelList = filter(salesHistoriesList, newText);
        adapter.setFilter(filteredModelList);

        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.search, menu);
        final MenuItem item = menu.findItem(R.id.ic_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);

        ImageView icon = searchView.findViewById(androidx.appcompat.R.id.search_button);
        icon.setColorFilter(Color.WHITE);

        ImageView searchClose = searchView.findViewById(androidx.appcompat.R.id.search_close_btn);
        searchClose.setColorFilter(Color.WHITE);

        EditText editText = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        editText.setHintTextColor(getResources().getColor(R.color.white));
        editText.setTextColor(getResources().getColor(R.color.white));

        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(item,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        adapter.setFilter(salesHistoriesList);
                        return true;
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        return true;
                    }
                });
    }

    private List<SalesHistory> filter(List<SalesHistory> salesHistoryFilter, String query) {
        query = query.toLowerCase();
        final List<SalesHistory> filteredModelList = new ArrayList<>();
        for (SalesHistory salesHistory : salesHistoryFilter) {
            final String text = salesHistory.getInvoiceId().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(salesHistory);
            }
        }
        return filteredModelList;
    }
}
