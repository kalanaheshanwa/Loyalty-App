package com.example.kalana.theapp1;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;
import static com.android.volley.Request.Method.POST;

public class HomeActivity extends Activity {

    private Button btnLogin;
    private EditText merchantUserId;
    private EditText Password;
    private ProgressDialog pDialog;
    String login_url = "http://10.0.2.2:8080/api/merchant/login";
    RequestQueue requestQueue;
    String MY_PREFS_NAME = "login_pref";



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        merchantUserId =  findViewById(R.id.etUserId);
        Password = findViewById(R.id.etPassword);
        btnLogin =  findViewById(R.id.btnLogin);


        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // Login button Click Event
        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
//                String merchantUserId = prefs.getString("merchantUserId", null);
//                String merchpass = prefs.getString("password", null);

                String merchantId = merchantUserId.getText().toString();
                String password = Password.getText().toString();

                // Check for empty data in the form
                if (!merchantId.isEmpty() && !password.isEmpty()) {
                    checkLogin(merchantId, password);
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(), "Please enter the credentials!", Toast.LENGTH_LONG).show();
                }
            }

        });

    }


    private void checkLogin(final String merchantUserId, final String password) {

        Map<String, String> jsonParams = new HashMap<String, String>();
        jsonParams.put("merchantUserId", merchantUserId);
        jsonParams.put("password", password);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(POST, login_url, new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                        try {
                            String merchantUserId = response.getString("merchantUserId");
                            String password = response.getString("password");
                            String address = response.getString("address");
                            String company = response.getString("company");
                            String dateJoin = response.getString("dateJoin");
                            String fistName = response.getString("fistName");
                            String lastName = response.getString("lastName");

                            JSONObject jsonObject = response.getJSONObject("pointValueId");
                            String pointValueId = jsonObject.getString("pointValueId");
                            String value = jsonObject.getString("value");


                            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                            editor.putString("merchantUserId",merchantUserId);
                            editor.putString("password",password);
                            editor.putString("address",address);
                            editor.putString("company",company);
                            editor.putString("dateJoin",dateJoin);
                            editor.putString("fistName",fistName);
                            editor.putString("lastName",lastName);
                            editor.putString("pointValueId",pointValueId);
                            editor.putString("value",value);
                            editor.putBoolean("loggedIn", true);
                            editor.apply();

                            //user = new User(merchantUserId, password, address, company, dateJoin, fistName, lastName);
                            Intent in = new Intent(getApplicationContext(), Navi.class);

                            startActivity(in);

                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());

                Toast.makeText(getApplicationContext(),"User Id or Password Is Wrong", Toast.LENGTH_LONG).show();

            }
        });
        requestQueue = Volley.newRequestQueue(HomeActivity.this);
        requestQueue.add(jsonObjectRequest);


    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences preferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        boolean loggedIn = preferences.getBoolean("loggedIn", false);
        if(loggedIn==true){
            Intent intent = new Intent(HomeActivity.this, Navi.class);
            //startActivity(intent);
        }
    }
}