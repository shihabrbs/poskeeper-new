package com.portal.terminalbd.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.portal.terminalbd.R;
import com.portal.terminalbd.model.AnonymousCustomer;

import java.util.ArrayList;
import java.util.List;

public class CustomerSuggestionAdapter extends ArrayAdapter<AnonymousCustomer> {

    private List<AnonymousCustomer> suggestions;

    public CustomerSuggestionAdapter(Context context, List<AnonymousCustomer> anonymousCustomer) {
        super(context,0,anonymousCustomer);
        suggestions = new ArrayList<>(anonymousCustomer);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView==null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_for_suggestion_item, parent, false
            );

        }

        TextView medName = convertView.findViewById(R.id.med_name);
        AnonymousCustomer customer = getItem(position);

        if (customer != null) {
            medName.setText(customer.getMobile()+" - "+customer.getName());
        }

        return convertView;
    }

    @Override
    public Filter getFilter() {

        Filter myFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();

                List<AnonymousCustomer> itemList =new ArrayList<>();

                if (constraint == null || constraint.length()==0) {

                    itemList.addAll(suggestions);

                }else
                {
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    for (AnonymousCustomer item : suggestions)
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
                return ((AnonymousCustomer)resultValue).getName();
            }
        };

        return myFilter;
    }
}
