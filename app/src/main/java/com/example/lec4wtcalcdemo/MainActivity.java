package com.example.lec4wtcalcdemo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
//Added a comment form central repo
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
// made change locally in lab computer
public class MainActivity extends AppCompatActivity {

    RadioGroup redGroupConvType;
    TextView txtViewResults;
    EditText editTextInputWt;
    Button btnConvert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setLogo(R.mipmap.ic_launcher_wt_round);

        actionBar.setTitle(R.string.txtTitle);

        //we will complete the rest of this logic in
        //class next week
        redGroupConvType = findViewById(R.id.radGroupConv);
        editTextInputWt = findViewById(R.id.editTextInputWt);
        btnConvert = findViewById(R.id.btnConvertWt);
        txtViewResults = findViewById(R.id.txtViewResults);

        btnConvert.setOnClickListener((View v) -> {
           if(redGroupConvType.getCheckedRadioButtonId() == -1) {
               Toast.makeText(this, "Please select conversion type", Toast.LENGTH_SHORT).show();
           } else if (editTextInputWt.getText().toString().isEmpty()){
               Toast.makeText(this, "Input weight to be entered", Toast.LENGTH_SHORT).show();
           }else {
               double inputWt = 0, outputWt = 0;
               try{
                   inputWt = Double.parseDouble(editTextInputWt.getText().toString());
                   if (inputWt < 0 ){
                       Toast.makeText(this, "Input can not be negative", Toast.LENGTH_SHORT).show();
                   } else if (redGroupConvType.getCheckedRadioButtonId() == R.id.radBtnKgsToLbs){
                       if (inputWt > 500){
                           Toast.makeText(this, "Baggage weight in kilos exceeded", Toast.LENGTH_SHORT).show();
                       } else {
                           outputWt = inputWt * 2.2;
                           txtViewResults.setText(String.format("Converted wt : %2f Lbs",outputWt));
                       }

                   }else if (redGroupConvType.getCheckedRadioButtonId() == R.id.radBtnLbsToKgs){
                       if (inputWt > 1000){
                           Toast.makeText(this, "Baggage weight in kilos exceeded", Toast.LENGTH_SHORT).show();
                       } else {
                           outputWt = inputWt / 2.2;
                           txtViewResults.setText(String.format("Converted wt : %2f Kgs",outputWt));
                       }
                   }

               }catch(Exception ex){
                   ex.printStackTrace();
                   Toast.makeText(this, "Must be a valid number", Toast.LENGTH_SHORT).show();
               }
           }
        });

        redGroupConvType.setOnCheckedChangeListener((RadioGroup group, int checkedId) ->{
                if (checkedId == R.id.radBtnKgsToLbs){
                    txtViewResults.setText("Option Kilos to Pounds, update input weight and click on button");
                }else if (checkedId == R.id.radBtnLbsToKgs){
                    txtViewResults.setText("Option Pounds to Kilos, update input weight and click on button");
                }

        });

    }
}
