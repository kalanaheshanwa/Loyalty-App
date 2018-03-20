package com.example.kalana.theapp1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;


public class AddNewPromo extends AppCompatActivity {

    String PromotionAddUrl = "http://10.0.2.2:8080/api/promotion/login/promotionuser/create";
    Button addPromo;
    String MY_PREFS_NAME = "login_pref";
    EditText etNewPromoId;
    EditText etNewPassword;
    String merchantUserId = "";
    String password,promoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences preferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        merchantUserId = preferences.getString("merchantUserId", null);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_promo);


        etNewPromoId = (EditText) findViewById(R.id.etNewPromoId);
        etNewPassword = (EditText) findViewById(R.id.etNewPassword);
        addPromo = (Button)findViewById(R.id.btnAddPromo);

        promoId = etNewPromoId.getText().toString();
        password = etNewPassword.getText().toString();

        addPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!etNewPromoId.getText().toString().isEmpty() && !etNewPassword.getText().toString().isEmpty()) {
                    submitAddProUser();
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter the credentials!", Toast.LENGTH_LONG).show();
                }

            }
        });
    }


    public void submitAddProUser() {

        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("merchantId", merchantUserId);
            jsonBody.put("promotionUserId", etNewPromoId.getText().toString());
            jsonBody.put("password", etNewPassword.getText().toString());
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, PromotionAddUrl , new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(AddNewPromo.this, response, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AddNewPromo.this,Navi.class);
                    startActivity(intent);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(AddNewPromo.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }
            };

            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
