package com.quarterlife.viewbindingtest;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.quarterlife.viewbindingtest.databinding.ActivityMainBinding;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding; // add this for view binding

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater()); // add this for view binding
        setContentView(binding.getRoot()); // add this for view binding

        // set OnClickListener on calculate button
        binding.calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateTip();
            }
        });
    }

    // calculate tip
    private void calculateTip() {
        // get cost of service in EditText, and convert it to double
        String getCost = binding.costOfService.getText().toString();

        // prevent null values
        if(getCost.isEmpty()){
            displayTip(0.0);
            return; // stop here
        }

        // convert string to double
        Double cost = Double.parseDouble(getCost);

        // get tip percentage
        double tipPercentage;
        switch (binding.tipOptions.getCheckedRadioButtonId()){
            case R.id.option_twenty_percent:
                tipPercentage = 0.20;
                break;
            case R.id.option_eighteen_percent:
                tipPercentage = 0.18;
                break;
            default:
                tipPercentage = 0.15;
                break;
        }

        // calculate the tip
        double tip = tipPercentage * cost;

        // round up the tip if necessary
        if(binding.roundUpSwitch.isChecked()) tip = Math.ceil(tip); // ceil : unconditional carry to integer

        // display the formatted tip value on screen
        displayTip(tip);
    }

    // display the formatted tip value on screen
    private void displayTip(double tip) {
        // format the tip
        String formattedTip = NumberFormat.getCurrencyInstance().format(tip);

        // display tip on the TextView
        binding.tipResult.setText(getString(R.string.tip_amount, formattedTip));
    }
}