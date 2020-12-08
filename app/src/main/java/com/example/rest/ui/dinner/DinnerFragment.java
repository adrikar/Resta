package com.example.rest.ui.dinner;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.*;

import com.example.rest.R;


public class DinnerFragment extends Fragment{
    private DinnerViewModel dinnerViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dinnerViewModel =
                new ViewModelProvider(this).get(DinnerViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dinner, container, false);
        final TextView textView = root.findViewById(R.id.text_dinner);
        dinnerViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
