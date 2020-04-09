package com.adityakhedekar.khedubaba.savinglanguagepreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    SharedPreferences sharedPreferences;
    TextView helloWorldText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helloWorldText = findViewById(R.id.helloTextView);

        sharedPreferences = this.getSharedPreferences("com.adityakhedekar.khedubaba.savinglanguagepreferences", Context.MODE_PRIVATE);
        String langauge = sharedPreferences.getString("language", "Error while retrieving language");

        Log.i(TAG, "The Lang: " + langauge);

        if (langauge.equals("Error while retrieving language")){
            new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_btn_speak_now)
                    .setTitle("Choose a language")
                    .setMessage("Which language would you like to use?")
                    .setPositiveButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setLanguage("English");
                        }
                    })
                    .setNegativeButton("हिन्दी", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setLanguage("हिन्दी");
                        }
                    })
                    .show();
        }
        else{
            helloWorldText.setText(langauge);
        }
    }

    public void setLanguage(String language){
        sharedPreferences.edit().putString("language", language).apply();
        helloWorldText.setText(language);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.english:
                setLanguage("English");
                return  true;
            case R.id.hindi:
                setLanguage("हिन्दी");
                return true;
            default:
                return false;
        }
    }
}
