package com.mustafa.smallstore.view.main.product.addandeditproduct;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.gson.Gson;
import com.mustafa.smallstore.R;
import com.mustafa.smallstore.databinding.FragmentAddAndEditProductBinding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class AddAndEditProductFragment extends Fragment {

    private static final String TAG = "NumberPicker";
    //region Component
    FragmentAddAndEditProductBinding binding;
    AppCompatAutoCompleteTextView fragmentAddAndEditProductTextInputEditTextCategory;
    //endregion
    AppCompatAutoCompleteTextView fragmentAddAndEditProductTextInputEditTextMadeIn;
    //region Variable
    ArrayList<String> nameCategoryList;
    ArrayAdapter<String> categoryAdapter;
    ArrayAdapter<String> countryAdapter;
    AddAndEditProductViewModel addAndEditProductViewModel;
    CountryModelJson[] countries;
    //endregion

    //region Life cycle
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_and_edit_product, container, false);
        binding = FragmentAddAndEditProductBinding.bind(view);
        // We init this array to be sure it's not gonna take null value.
        nameCategoryList = new ArrayList<>();

        //For Date Picker (Material Design)
        addAndEditProductViewModel = new ViewModelProvider(requireActivity()).get(AddAndEditProductViewModel.class);

        addAndEditProductViewModel.categoryRepository.getAllCategories().observe(requireActivity(), categoryEntities -> {
            if (categoryEntities != null && categoryEntities.size() != 0) {
                for (int i = 0; i < categoryEntities.size(); i++) {
                    nameCategoryList.add(categoryEntities.get(i).getName());
                }
                //For Spinner And Auto Complete (category)
                categoryAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, nameCategoryList);
                binding.fragmentAddAndEditProductTextInputEditTextCategory.setAdapter(categoryAdapter);
            }
        });

        //For Spinner And Auto Complete (Country)
        Gson gson = new Gson();
        countries = gson.fromJson(readCountryJsonFileAsString(), CountryModelJson[].class);
        countryAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, countries);
        binding.fragmentAddAndEditProductTextInputEditTextMadeIn.setAdapter(countryAdapter);

        //For first value to number picker (initialization)
//        binding.fragmentAddAndEditProductNumberPickerHorizontal.setValue(1);
        //get data from number picker
//        binding.fragmentAddAndEditProductNumberPickerHorizontal.setOnValueChangedListener((com.shawnlin.numberpicker.NumberPicker.OnValueChangeListener) (picker, oldVal, newVal) -> Log.d(TAG, String.format(Locale.US, "oldVal: %d, newVal: %d", oldVal, newVal)));


        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Select Start Offer Date");
        final MaterialDatePicker<Long> materialDatePicker = builder.build();

        //for click choose date
        binding.fragmentAddAndEditProductButtonDatePicker.setOnClickListener(view1 -> {
            materialDatePicker.show(requireActivity().getSupportFragmentManager(), "DATE_PICKER");
        });


        return view;
    }

    //endregion

    //region Methods

    public String readCountryJsonFileAsString() {
        //Read File from resources package
        InputStream XmlFileInputStream = getResources().openRawResource(R.raw.countries);
        return readTextFile(XmlFileInputStream);
    }

    //Convert File To String
    public String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }


    //endregion
}
