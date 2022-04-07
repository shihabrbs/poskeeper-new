package com.portal.terminalbd.fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.portal.terminalbd.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PurchaseDetailsFragment extends Fragment {


    public PurchaseDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_purchase_details, container, false);

        return view;
    }

}
