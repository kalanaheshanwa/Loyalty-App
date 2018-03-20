package com.example.kalana.theapp1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PendingPromotion extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    String MY_PREFS_NAME = "login_pref";
    String merchantUserId = "";

    private static List<ListItem> listItems;

    private static final String url_data="http://10.0.2.2:8080/api/promotion/peningDiscoutPromo/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences preferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        merchantUserId = preferences.getString("merchantUserId", null);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_promotion);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewPending);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

    loadRecycleViewData();

}

    private void loadRecycleViewData() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_data + merchantUserId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);

                    for (int i=0;i<array.length();i++){
                        JSONObject o = array.getJSONObject(i);

                        ListItem u = new ListItem(o.getString("item"), o.getString("percentage"),o.getString("details"),o.getString("start_date"),o.getString("end_date"),o.getString("promo_id"),o.getString("item_code"));
                        listItems.add(u);

//                        String promoId = o.getString("promo_id");
//                        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
//                        editor.putString("promo_id",promoId);
//                        editor.apply();
//                        Toast.makeText(PendingPromotion.this,o.getString("promo_id") , Toast.LENGTH_SHORT).show();

                    }

                    adapter=new MyAdapter(listItems,getApplicationContext());
                    recyclerView.setAdapter(adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
