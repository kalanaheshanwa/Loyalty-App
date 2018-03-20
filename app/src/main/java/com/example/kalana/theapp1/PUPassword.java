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

public class PUPassword extends AppCompatActivity {
    EditText etOldPass, etNewPass, etConformPass;
    Button btnChangePassword;
    String MY_PREFS_NAME = "login_pref";
    String ChangePasswordUrl = "http://10.0.2.2:8080/api/merchant/promouser/changepassword/";
    String newPass, OldPass, ConformPss;
    String password = "", merchantUserId = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences preferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        password = preferences.getString("password", null);
        merchantUserId = preferences.getString("merchantUserId", null);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pupassword);

        btnChangePassword = (Button) findViewById(R.id.btnChangePassword);
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                etOldPass = (EditText) findViewById(R.id.etOldPass);
                etNewPass = (EditText) findViewById(R.id.etNewPass);
                etConformPass = (EditText) findViewById(R.id.etConformPass);

                OldPass = etOldPass.getText().toString();
                newPass = etNewPass.getText().toString();
                ConformPss = etConformPass.getText().toString();



                if (OldPass.equals(password) ) {
                    if (newPass.equals(ConformPss)) {

                        changePassword();

                    } else {
                        Toast.makeText(PUPassword.this, "Password Not Match", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    Toast.makeText(PUPassword.this, "Enter Correct Old Password", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void changePassword() {

        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("merchantUserId", merchantUserId);
            jsonBody.put("password", etNewPass.getText().toString());
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.PUT, ChangePasswordUrl + merchantUserId + '/' + etNewPass.getText().toString(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(PUPassword.this, response, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(PUPassword.this,Navi.class);
                    startActivity(intent);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(PUPassword.this, error.toString(), Toast.LENGTH_SHORT).show();
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