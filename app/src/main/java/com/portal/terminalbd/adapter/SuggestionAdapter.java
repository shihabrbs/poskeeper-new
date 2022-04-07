package com.portal.terminalbd.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.portal.terminalbd.R;
import com.portal.terminalbd.model.StockItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class SuggestionAdapter extends ArrayAdapter<StockItem> {
Context contextt;
    private List<StockItem> suggestions;
    Spinner spinner;
    public SuggestionAdapter(Context context,List<StockItem> stockItems) {
        super(context,0,stockItems);
        suggestions = new ArrayList<>(stockItems);
        contextt= context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView==null) {
           // convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_for_suggestion_item, parent, false);
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sales_cart, parent, false);

        }




        TextView medName = convertView.findViewById(R.id.name);
        TextView stockmrp = convertView.findViewById(R.id.mrp);
        EditText mrp= convertView.findViewById(R.id.edittextmrp);
        ImageView quantity_plus= convertView.findViewById(R.id.plusebtn);
        ImageView quantity_minus= convertView.findViewById(R.id.minusbtn);
        TextView sale_item_quantity= convertView.findViewById(R.id.edittextqty);
        TextView total= convertView.findViewById(R.id.textView17);
        spinner= convertView.findViewById(R.id.spinnerid);
        ConstraintLayout itemcartlayout= convertView.findViewById(R.id.constraintLayout6);

        StockItem item = getItem(position);
        getDiscountSpinner();

        mrp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        if (item != null) {
            medName.setText(item.getName());
            stockmrp.setText(""+item.getSalesPrice());
            mrp.setText(""+item.getSalesPrice());
            total.setText("৳" + "0");
        }

        return convertView;
    }

    private void getDiscountSpinner() {

        ArrayList<String> number = new ArrayList<>();
        number.add("Dis(%)");
        number.add("1");
        number.add("2");
        number.add("3");
        number.add("4");
        number.add("6");
        number.add("7");
        number.add("8");
        number.add("9");
        number.add("10");
        number.add("11");
        number.add("12");
        number.add("13");
        number.add("14");
        number.add("15");
        number.add("16");
        number.add("17");
        number.add("18");
        number.add("19");
        number.add("20");
        number.add("21");
        number.add("22");
        number.add("23");
        number.add("24");
        number.add("25");


        ArrayAdapter adapter2 = new ArrayAdapter(contextt, R.layout.spinner_item, number);

        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
       spinner.setAdapter(adapter2);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

              //  discountpercent = "" + holder.spinner.getItemAtPosition(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    @Override
    public Filter getFilter() {

        Filter myFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();

                List<StockItem> itemList =new ArrayList<>();

                if (constraint == null || constraint.length()==0) {

                        itemList.addAll(suggestions);

            }else
                {
                 String filterPattern = constraint.toString().toLowerCase().trim();

                 for (StockItem item : suggestions)
                 {
                     if (item.getName().toLowerCase().contains(filterPattern))
                     {
                         itemList.add(item);
                     }
                 }

                }
                // Now assign the values and count to the FilterResults
                // object
                filterResults.values = itemList;
                filterResults.count = itemList.size();

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence contraint, FilterResults results) {
                    clear();
                    addAll((List) results.values);
                    notifyDataSetChanged();
            }

            @Override
            public CharSequence convertResultToString(Object resultValue) {
                return ((StockItem)resultValue).getName();
            }
        };

        return myFilter;
    }

}