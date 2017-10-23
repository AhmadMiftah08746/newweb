package com.aplikasiku.miftah.kodewebsite;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ConnectInternetTask c1;
    static TextView myText;
    static EditText myEdit;
    private Spinner mySpin;
    String isi;
    private String[] proto ={
            "http://","https://"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myText = (TextView)findViewById(R.id.myResult);
        myEdit = (EditText)findViewById(R.id.input);
        mySpin = (Spinner)findViewById(R.id.spinner);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,proto);
        mySpin.setAdapter(adapter);

        mySpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                isi = mySpin.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }}
        );
    }

    public void doSomething(View view) {
        c1 = new ConnectInternetTask(this);
        String input = myEdit.getText().toString();
        ConnectivityManager cm = (ConnectivityManager) getApplication(). getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()){
            Toast.makeText(getApplication(), "You are Connected", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplication(),"You dont have connection", Toast.LENGTH_SHORT).show();
        }

        c1.execute(input);


    }
}
