package com.example.kalana.theapp1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import static com.android.volley.Request.Method.GET;


public class CustomPoint extends AppCompatActivity {

    EditText  etCustomerNum;
    Button btnViewPoint;
    String b;
    String URL = "http://10.0.2.2:8080/api/customer/merchant/point/";
    TextView text;
    String MY_PREFS_NAME = "login_pref";
    String company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences preferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        company = preferences.getString("company", null);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_point);


        btnViewPoint = (Button) findViewById(R.id.btnViewPoint);
        text = (TextView) findViewById(R.id.t);
        loadPoints();
    }

    public void loadPoints() {

        btnViewPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = getIntent();
                in.getSerializableExtra("userDetails");

                etCustomerNum = (EditText) findViewById(R.id.etCustomerNum);
                b=etCustomerNum.getText().toString();

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(GET, URL + company+'/'+ b, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {

                                    text.setText(response.getString("pointAmount"));

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Please enter Valid Phone Number", Toast.LENGTH_LONG).show();

                        error.printStackTrace();
                    }
                });

                MySingleton.getInstance(CustomPoint.this).addRequestque(jsonObjectRequest);

            }

        });
    }
}
