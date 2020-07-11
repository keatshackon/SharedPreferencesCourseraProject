package com.keatssalazar.persistancestorage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private EditText editName,editAddress,editPhone,editEmail;
    private RadioGroup rdg;
    private RadioButton rdgM,rdgA,rdgE,rdgN;

    public static  final String MYPREFS = "mySharedPreferences";
    private String favouritePartOfDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editName);
        editAddress = findViewById(R.id.editAddress);
        editPhone = findViewById(R.id.editPhone);
        editEmail = findViewById(R.id.editEmail);

        rdg = findViewById(R.id.rdg);
        rdgM = findViewById(R.id.rdbM);
        rdgA = findViewById(R.id.rdbA);
        rdgE = findViewById(R.id.rdbE);
        rdgN = findViewById(R.id.rdbN);

        this.loadPreferences();

    }
    public void  loadPreferences(){
        android.content.SharedPreferences mysharedpref = getSharedPreferences(MYPREFS, Activity.MODE_PRIVATE);
        editName.setText(mysharedpref.getString("name",""));
        editAddress.setText(mysharedpref.getString("address",""));
        editPhone.setText(mysharedpref.getString("phone",""));
        editEmail.setText(mysharedpref.getString("email",""));

        favouritePartOfDay = mysharedpref.getString("favouritePartyOfDay","m");

        loadRadioButtonPreference();
    }
    public void loadRadioButtonPreference(){
        switch (favouritePartOfDay) {
            case "m":
                rdg.check(R.id.rdbM);
                break;
            case "a":
                rdg.check(R.id.rdbA);
                break;
            case "e":
                rdg.check(R.id.rdbE);
                break;
            case "n":
                rdg.check(R.id.rdbN);
                break;
            default:
                rdg.check(R.id.rdbM);
                break;
        }

    }

    public void onClick(View view){
        if(rdgM.isChecked()){
            favouritePartOfDay = "m";

        }else if(rdgA.isChecked()){
            favouritePartOfDay = "a";
        }else if(rdgE.isChecked()){
            favouritePartOfDay = "e";
        }else if(rdgN.isChecked()){
            favouritePartOfDay = "n";
        }else {
            favouritePartOfDay = "";
        }

    }
    public void savePreferences(){
        android.content.SharedPreferences mysharedpref = getSharedPreferences(MYPREFS, Activity.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = mysharedpref.edit();
        editor.putString("name",editName.getText().toString());
        editor.putString("address",editAddress.getText().toString());
        editor.putString("phone",editPhone.getText().toString());
        editor.putString("email",editEmail.getText().toString());
        editor.putString("favouritePartOfDay",favouritePartOfDay);
        editor.apply();

    }

    @Override
    protected void onStop() {
        super.onStop();
        this.savePreferences();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_shared_pref,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();
        if(id == R.id.action_setting){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}