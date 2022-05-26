package com.mustafa.smallstore.view.main.product.addandeditproduct;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.gson.Gson;
import com.mustafa.smallstore.R;
import com.mustafa.smallstore.databinding.FragmentAddAndEditProductBinding;
import com.mustafa.smallstore.model.entity.ProductEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class AddAndEditProductFragment extends Fragment {

    private static final String TAG = "NumberPicker";

    //region Component
    FragmentAddAndEditProductBinding binding;
    AppCompatAutoCompleteTextView fragmentAddAndEditProductTextInputEditTextCategory;
    AppCompatAutoCompleteTextView fragmentAddAndEditProductTextInputEditTextMadeIn;
    //endregion

    //region Variable
    ArrayList<String> nameCategoryList;
    ArrayAdapter<String> categoryAdapter;
    ArrayAdapter<String> countryAdapter;
    AddAndEditProductViewModel addAndEditProductViewModel;
    CountryModelJson[] countries;
    ProductEntity productEntity;
    String startOfferDate;
    String endOfferDate;
    int quantity;
    Bundle bundle;
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


        //Get Data From From Product Fragment (when click to edit new or old product only)
        bundle = getArguments();
        if (bundle != null) {
            binding.fragmentAddAndEditProductEditButtonAddProduct.setText("Update");
            binding.fragmentAddAndEditProductEditTextName.setText(bundle.getString("name"));
            binding.fragmentAddAndEditProductEditPrice.setText((bundle.get("price")).toString());
            binding.fragmentAddAndEditProductTextInputEditTextMadeIn.setText(bundle.getString("madeIn"));

            if (bundle.getBoolean("isNew") == true && bundle.getBoolean("isOffered") == true) {
                binding.fragmentAddAndEditProductCheckedBoxIsNewProduct.setChecked(true);
                binding.fragmentAddAndEditProductCheckedBoxIsOffer.setChecked(true);
            } else if (bundle.getBoolean("isNew") == false && bundle.getBoolean("isOffered") == false) {
                binding.fragmentAddAndEditProductCheckedBoxIsOffer.setChecked(false);
                binding.fragmentAddAndEditProductCheckedBoxIsNewProduct.setChecked(false);
            } else if (bundle.getBoolean("isNew") == true && bundle.getBoolean("isOffered") == false) {
                binding.fragmentAddAndEditProductCheckedBoxIsNewProduct.setChecked(true);
                binding.fragmentAddAndEditProductCheckedBoxIsOffer.setChecked(false);
            } else if (bundle.getBoolean("isNew") == false && bundle.getBoolean("isOffered") == true) {
                binding.fragmentAddAndEditProductCheckedBoxIsNewProduct.setChecked(false);
                binding.fragmentAddAndEditProductCheckedBoxIsOffer.setChecked(true);
            } else {
                binding.fragmentAddAndEditProductCheckedBoxIsNewProduct.isChecked();
                binding.fragmentAddAndEditProductCheckedBoxIsOffer.isChecked();
            }
            binding.fragmentAddAndEditProductTextInputEditTextCategory.setText(bundle.getString("categoryName"));
            binding.fragmentAddAndEditProductNumberPickerHorizontal.setValue(bundle.getInt("quantity"));
            binding.fragmentAddAndEditProductEditOfferPrice.setText((bundle.get("offerPrice")).toString());
        }


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

        //For Spinner And Auto Complete (made in)
        Gson gson = new Gson();
        countries = gson.fromJson(readCountryJsonFileAsString(), CountryModelJson[].class);
        countryAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, countries);
        binding.fragmentAddAndEditProductTextInputEditTextMadeIn.setAdapter(countryAdapter);

        //For first value to number picker (initialization)
        // binding.fragmentAddAndEditProductNumberPickerHorizontal.setValue(1);
        //get data from number picker
        binding.fragmentAddAndEditProductNumberPickerHorizontal.setOnValueChangedListener((com.shawnlin.numberpicker.NumberPicker.OnValueChangeListener) (picker, oldVal, newVal) ->
                quantity = picker.getValue());

//
//        //For date picker
//        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
//        builder.setTitleText("Select Start Offer Date");
//        final MaterialDatePicker<Long> materialDatePicker = builder.build();

        //For click choose start date
        binding.fragmentAddAndEditProductButtonDatePickerStartOfferDate.setOnClickListener(view1 -> {
            showDataPickerDialogForStartDate();
        });

        //For click choose end date
        binding.fragmentAddAndEditProductButtonDatePickerEndOfferDate.setOnClickListener(view1 -> {

            showDataPickerDialogForEndDate();
        });


        //And And Edit Button
        binding.fragmentAddAndEditProductEditButtonAddProduct.setOnClickListener(view1 -> {


            boolean checkedOffer;
            boolean productNew;
            if (binding.fragmentAddAndEditProductCheckedBoxIsOffer.isChecked()) {
                checkedOffer = true;
            } else {
                checkedOffer = false;
            }
            if (binding.fragmentAddAndEditProductCheckedBoxIsNewProduct.isChecked()) {
                productNew = true;
            } else {
                productNew = false;
            }


            productEntity = new ProductEntity(Objects.requireNonNull(binding.fragmentAddAndEditProductEditTextName.getText()).toString(),
                    Double.parseDouble(Objects.requireNonNull(binding.fragmentAddAndEditProductEditPrice.getText()).toString()),
                    binding.fragmentAddAndEditProductTextInputEditTextMadeIn.getText().toString(),
                    checkedOffer, endOfferDate, productNew, Double.parseDouble(Objects.requireNonNull(binding.fragmentAddAndEditProductEditOfferPrice.getText()).toString()),
                    null, null, null, null, 1,
                    binding.fragmentAddAndEditProductTextInputEditTextCategory.getText().toString(), quantity);
            Toast.makeText(getContext(), "The Product Has Been Saved", Toast.LENGTH_SHORT).show();


            if (bundle != null && bundle.getInt("id") != 0) {
                productEntity.setId(bundle.getInt("id"));
                addAndEditProductViewModel.updateProduct(productEntity);
            } else {
                addAndEditProductViewModel.insertProduct(productEntity);
            }


            //Back to account activity after add
            requireActivity().onBackPressed();

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

    private void showDataPickerDialogForStartDate() {
        MaterialDatePicker materialDatePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Start Offer Date").build();

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                startOfferDate = materialDatePicker.getHeaderText();
            }
        });

        materialDatePicker.show(requireActivity().getSupportFragmentManager(), "DATE_PICKER_START");


    }

    private void showDataPickerDialogForEndDate() {
        MaterialDatePicker materialDatePicker1 = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select End Offer Date").build();

        materialDatePicker1.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                endOfferDate = materialDatePicker1.getHeaderText();

            }
        });

        materialDatePicker1.show(requireActivity().getSupportFragmentManager(), "DATE_PICKER_END");

    }

    //endregion
}
