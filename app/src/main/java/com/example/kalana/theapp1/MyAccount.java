package com.example.kalana.theapp1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class MyAccount extends AppCompatActivity {

    String MY_PREFS_NAME = "login_pref";
    String company, address, fistName, lastName, merchantUserId, value, dateJoin;
    TextView tvCompanyName, tvAddress,tvFName,tvLName,tvMerchantId, tvPointValue,tvJointDate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        SharedPreferences preferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        company = preferences.getString("company", null);
        address = preferences.getString("address", null);
        fistName = preferences.getString("fistName", null);
        lastName = preferences.getString("lastName", null);
        merchantUserId = preferences.getString("merchantUserId", null);
        value = preferences.getString("value", null);
        dateJoin = preferences.getString("dateJoin", null);
//        Toast.makeText(getApplicationContext(), company, Toast.LENGTH_LONG).show();
//        Toast.makeText(getApplicationContext(), address, Toast.LENGTH_LONG).show();
//        Toast.makeText(getApplicationContext(), fistName, Toast.LENGTH_LONG).show();
//        Toast.makeText(getApplicationContext(), lastName, Toast.LENGTH_LONG).show();

        tvCompanyName= (TextView) findViewById(R.id.tvCompanyName);
        tvAddress= (TextView) findViewById(R.id.tvAddress);
        tvFName= (TextView) findViewById(R.id.tvFName);
        tvLName= (TextView) findViewById(R.id.tvLName);
        tvMerchantId= (TextView) findViewById(R.id.tvMerchantId);
        tvPointValue= (TextView) findViewById(R.id.tvPointValue);
        tvJointDate= (TextView) findViewById(R.id.tvJointDate);

        tvCompanyName.setText(company);
        tvAddress.setText(address);
        tvFName.setText(fistName);
        tvLName.setText(lastName);
        tvMerchantId.setText(merchantUserId);
        tvPointValue.setText(value);
        tvJointDate.setText(dateJoin);


    }


}
