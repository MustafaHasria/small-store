package com.mustafa.smallstore.view.main.product.addandeditproduct;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.fragment.app.Fragment;

import com.mustafa.smallstore.R;
import com.mustafa.smallstore.databinding.FragmentAddAndEditProductBinding;
import com.mustafa.smallstore.view.datepickerstartandend.MainForDateActivity;

import java.util.ArrayList;
import java.util.Locale;

public class AddAndEditProductFragment extends Fragment {

    //region Component
    FragmentAddAndEditProductBinding binding;
    AppCompatAutoCompleteTextView fragmentAddAndEditProductTextInputEditTextCategory;
    AppCompatAutoCompleteTextView fragmentAddAndEditProductTextInputEditTextMadeIn;
    //endregion

    //region Variable
    ArrayList<String> categoryList;
    ArrayList<String> madeInList;
    ArrayAdapter<String> categoryAdapter;
    ArrayAdapter<String> madeInAdapter;
    private static String TAG = "NumberPicker";
    //endregion

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_and_edit_product, container, false);
        binding = FragmentAddAndEditProductBinding.bind(view);

        //for Spinner And Auto Complete (category)
        categoryList = new ArrayList<>();
        categoryList.add("Food");
        categoryList.add("TShirt");
        categoryList.add("Jeans");
        categoryAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, categoryList);
        binding.fragmentAddAndEditProductTextInputEditTextCategory.setAdapter(categoryAdapter);

        //for Spinner And Auto Complete (madeIn)
        madeInList = new ArrayList<>();
        madeInList.add("SYRIA");
        madeInList.add("UAE");
        madeInList.add("ALGERIA");
        madeInAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, madeInList);
        binding.fragmentAddAndEditProductTextInputEditTextMadeIn.setAdapter(madeInAdapter);

        //For first value to number picker (initialization)
        binding.fragmentAddAndEditProductNumberPickerHorizontal.setValue(1);
        //get data from number picker
        binding.fragmentAddAndEditProductNumberPickerHorizontal.setOnValueChangedListener((com.shawnlin.numberpicker.NumberPicker.OnValueChangeListener) (picker, oldVal, newVal) -> Log.d(TAG, String.format(Locale.US, "oldVal: %d, newVal: %d", oldVal, newVal)));
        ;

        binding.fragmentAddAndEditProductButtonDatePicker.setOnClickListener(view1 -> {
            Intent intent = new Intent();
            intent.setClass(getActivity(), MainForDateActivity.class);
            getActivity().startActivity(intent);
        });
        return view;
    }

}