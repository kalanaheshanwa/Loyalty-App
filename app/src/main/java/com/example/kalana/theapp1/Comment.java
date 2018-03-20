package com.example.kalana.theapp1;

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


public class Comment extends AppCompatActivity {

    EditText etComment;
    Button btnComment;
    String MY_PREFS_NAME = "login_pref";
    String CommentUrl = "http://10.0.2.2:8080/api/comments/save";
    String comment;
    String password = "", merchantUserId = "";
    String promoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        promoId = getIntent().getStringExtra("promoId");

        SharedPreferences preferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        password = preferences.getString("password", null);
        merchantUserId = preferences.getString("merchantUserId", null);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        btnComment = (Button) findViewById(R.id.btnComment);
        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                etComment = (EditText) findViewById(R.id.etComment);
                comment = etComment.getText().toString();
                if (!etComment.getText().toString().isEmpty()) {
                    CommentPromotion();
                } else {
                    Toast.makeText(Comment.this, "Enter Your Comment", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void CommentPromotion() {

        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("details", etComment.getText().toString());
            jsonBody.put("merchantID", merchantUserId);
            jsonBody.put("promotionId", promoId);

            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, CommentUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(Comment.this, response, Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Comment.this, error.toString(), Toast.LENGTH_SHORT).show();
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
