package com.example.kalana.theapp1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChangePointValuve extends AppCompatActivity {
    String response1;
    EditText etNewValue;
    Button btnChangeValue;
    String MY_PREFS_NAME = "login_pref";
    RequestQueue requestQueue;
    String ChangeValueUrl = "http://10.0.2.2:8080/api/pointvalue/merchant/";
    String merchpass = "", merchantUserId = "", pointValueId = "";
    String NewValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences preferences = getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE);
        merchpass = preferences.getString("password",null);
        merchantUserId = preferences.getString("merchantUserId",null);
        pointValueId = preferences.getString("pointValueId",null);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_point_valuve);


        btnChangeValue = (Button) findViewById(R.id.btnChangeValue);
        btnChangeValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    changePointValue();


            }
        });
    }

    public void changePointValue() {

        Map<String, String> jsonParams = new HashMap<String, String>();

        etNewValue = (EditText) findViewById(R.id.etNewValue);
        NewValue = etNewValue.getText().toString();

        jsonParams.put("value",etNewValue.toString());
        jsonParams.put("merchantId", merchantUserId);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, ChangeValueUrl +pointValueId + '/'+ NewValue , new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(ChangePointValuve.this, "Point Value Change Success", Toast.LENGTH_SHORT).show();
                        try {
                            Intent intent = new Intent(ChangePointValuve.this,Navi.class);
                            startActivity(intent);

                            response1 = response.getString("message");


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }


        );
        requestQueue = Volley.newRequestQueue(ChangePointValuve.this);
        requestQueue.add(jsonObjectRequest);

    }
}
