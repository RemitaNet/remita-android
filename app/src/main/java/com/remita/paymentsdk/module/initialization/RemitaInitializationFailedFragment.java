package com.remita.paymentsdk.module.initialization;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.remita.paymentsdk.R;
import com.remita.paymentsdk.util.RIPGateway;

public class RemitaInitializationFailedFragment extends Fragment {

    Button cancel;
    ImageView close;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.remita_payment_initialization_failed_fragment, container, false);
        cancel = root.findViewById(R.id.cancel);
        close = root.findViewById(R.id.close);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeAllPages();
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeAllPages();
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                closeAllPages();
            }
        }, 3000);
        return root;
    }

    private void closeAllPages() {
        try {
            getActivity().setResult(RIPGateway.Keys.CLOSE_ALL_ACTIVITY);
            getActivity().finish();
        } catch (Exception e) {

        }
    }

}