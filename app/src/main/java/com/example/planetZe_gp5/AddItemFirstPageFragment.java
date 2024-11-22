package com.example.planetZe_gp5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class AddItemFirstPageFragment extends Fragment {
    private Spinner spinnerCategory;
    private String selectedCategory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_item_first_page, container, false);
        spinnerCategory = view.findViewById(R.id.spinnerCategory);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.categories_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCategory = parent.getItemAtPosition(position).toString();
                Fragment selectedFragment = null;

                if(selectedCategory.equals("Personal Vehicle")){
                    selectedFragment = new AddItemPersonalVehicleFragment();
                }else if(selectedCategory.equals("Public Transportation")){
                    selectedFragment = new AddItemPublicTransportationFragment();
                }else if(selectedCategory.equals("Cycling or Walking")){
                    selectedFragment = new AddItemCyclingWalkingFragment();
                }else if(selectedCategory.equals("Meal")){
                    selectedFragment = new AddItemMealFragment();
                }else if(selectedCategory.equals("Cloth Purchase")){
                    selectedFragment = new AddItemClothPurchaseFragment();
                }else if(selectedCategory.equals("Electronics Purchase")){
                    selectedFragment = new AddItemElectronicsPurchaseFragment();
                }else if(selectedCategory.equals("Other Purchases")){
                    selectedFragment = new AddItemOtherPurchaseFragment();
                }else if(selectedCategory.equals("Bills")){
                    selectedFragment = new AddItemBillsFragment();
                }
                if (selectedFragment != null) {
                    loadFragment(selectedFragment);
                } else {
                    Toast.makeText(getContext(), "No category selected or category not handled", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedCategory = null;
            }
        });
        return view;
    }
    private void loadFragment(Fragment fragment) {
        FragmentManager childFragmentManager = getChildFragmentManager();
        childFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit();
    }


}

