package com.example.android.fetchapp;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

public class CarDetailBottomsheet extends BottomSheetDialogFragment {
    BottomSheetDialog d;
    Example example;
    ImageView close;
    TextView model , desc , date , specs;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.car_detail_bottomsheet, container, false);

        close = (ImageView)rootView.findViewById(R.id.close);
        model = (TextView)rootView.findViewById(R.id.model);
        desc = (TextView)rootView.findViewById(R.id.desc);
        date = (TextView)rootView.findViewById(R.id.date);
        specs = (TextView)rootView.findViewById(R.id.specs);

        Log.e("RESPONSE" , new Gson().toJson(example));

        model.setText(example.getCheckIndiaResponse().getCheckIndiaResult().getVehicleData().getCarModel());
        desc.setText(example.getCheckIndiaResponse().getCheckIndiaResult().getVehicleData().getDescription());
        date.setText(example.getCheckIndiaResponse().getCheckIndiaResult().getVehicleData().getRegistrationYear().toString());
        specs.setText(example.getCheckIndiaResponse().getCheckIndiaResult().getVehicleJson());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Drawable drawable=getResources().getDrawable(R.drawable.ic_clear_24px);
            VectorDrawable vectorDrawable = (VectorDrawable) drawable;
            close.setImageDrawable(vectorDrawable);
        }

        getDialog().setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                d = (BottomSheetDialog) dialog;
                View bottomSheetInternal = d.findViewById(android.support.design.R.id.design_bottom_sheet);
                BottomSheetBehavior.from(bottomSheetInternal).setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.dismiss();
            }
        });

        return rootView;
    }


    public void setData(Example example) {
        this.example = example;
    }

}
