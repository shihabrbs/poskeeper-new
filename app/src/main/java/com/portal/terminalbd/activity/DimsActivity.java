package com.portal.terminalbd.activity;

import android.graphics.Color;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.MenuItemCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.portal.terminalbd.R;
import com.portal.terminalbd.adapter.DIMSAdapter;
import com.portal.terminalbd.model.DIMSItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

public class DimsActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    @BindView(R.id.dims_rv)
    RecyclerView dims_rv;

    @BindView(R.id.medicine)
    TextView medicine;

    @BindView(R.id.veternary)
    TextView veternary;

    @BindView(R.id.herbal)
    TextView herbal;


    List<DIMSItem> dimsItemList;

    RealmResults<DIMSItem> dimsItems;

    DIMSAdapter adapter;

    Realm realm;

    int index = 0;

    /*
  toolbar
   */
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dims);

        ButterKnife.bind(this);

        realm = Realm.getDefaultInstance();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_arrow_back,null));

        toolbar_title.setText("Medicine");


        dimsItemList = new ArrayList<>();

        dimsItems = realm.where(DIMSItem.class).findAll();
        dimsItemList.addAll(realm.copyFromRealm(dimsItems));

        adapter = new DIMSAdapter(DimsActivity.this,dimsItemList);
        dims_rv.setLayoutManager(new LinearLayoutManager(DimsActivity.this,LinearLayoutManager.VERTICAL,false));
        dims_rv.addItemDecoration(new DividerItemDecoration(DimsActivity.this,DividerItemDecoration.VERTICAL));
        dims_rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();



    }

    @OnClick(R.id.medicine)
    void medicine()
    {
        medicine.setBackgroundColor(getResources().getColor(R.color.dims_selected));
        veternary.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        herbal.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        index = 0;

        dimsItemList.clear();
        dimsItems = realm.where(DIMSItem.class).equalTo("useFor","Human").findAll();
        dimsItemList.addAll(realm.copyFromRealm(dimsItems));

        adapter.notifyDataSetChanged();


    }

    @OnClick(R.id.veternary)
    void veternary()
    {
        medicine.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        veternary.setBackgroundColor(getResources().getColor(R.color.dims_selected));
        herbal.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        index =1;

        dimsItemList.clear();
        dimsItems = realm.where(DIMSItem.class).equalTo("useFor","Veterinary").findAll();
        dimsItemList.addAll(realm.copyFromRealm(dimsItems));

        adapter.notifyDataSetChanged();



    }

    @OnClick(R.id.herbal)
    void herbal()
    {
        medicine.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        veternary.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        herbal.setBackgroundColor(getResources().getColor(R.color.dims_selected));

        index = 2;

        dimsItemList.clear();
        dimsItems = realm.where(DIMSItem.class).equalTo("useFor","Herbal").findAll();
        dimsItemList.addAll(realm.copyFromRealm(dimsItems));

        adapter.notifyDataSetChanged();



    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

            List<DIMSItem> filteredModelList = filter(dimsItemList, newText);
            adapter.setFilter(filteredModelList);

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);
        final MenuItem item = menu.findItem(R.id.ic_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);

        ImageView icon = searchView.findViewById(androidx.appcompat.R.id.search_button);
        icon.setColorFilter(Color.WHITE);

        ImageView searchClose = searchView.findViewById(androidx.appcompat.R.id.search_close_btn);
        searchClose.setColorFilter(Color.WHITE);

        EditText editText = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        editText.setHintTextColor(getResources().getColor(R.color.white));
        editText.setTextColor(getResources().getColor(R.color.white));


        MenuItemCompat.setOnActionExpandListener(item,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {

                        adapter.setFilter(dimsItemList);

                        return true;
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        return true;
                    }
                });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==android.R.id.home)
        {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private List<DIMSItem> filter(List<DIMSItem> stocks, String query) {
        query = query.toLowerCase();
        final List<DIMSItem> filteredModelList = new ArrayList<>();
        for (DIMSItem stock : stocks) {
            final String text = stock.getMedicineName().toLowerCase()
                    +" "+stock.getGenericName().toLowerCase()
                    +" "+stock.getMedicineForm().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(stock);
            }
        }
        return filteredModelList;
    }
}
