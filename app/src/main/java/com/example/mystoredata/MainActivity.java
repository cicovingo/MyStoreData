package com.example.mystoredata;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView resultText;
    EditText ageText;
    Button saveButton;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultText = findViewById(R.id.resultAge);
        ageText = findViewById(R.id.ageText);
        saveButton = findViewById(R.id.buttonSave);

        sharedPreferences = this.getSharedPreferences("com.example.mystoredata", Context.MODE_PRIVATE);

        int storedAge = sharedPreferences.getInt("storedAge", 0);
        if(storedAge == 0){
            resultText.setText("Your Age: ");
        } else {
            resultText.setText("Your Age: "+storedAge);
        }
    }

    public void save(View view){
        if(ageText.getText().toString().matches("")){
            resultText.setText("Please Enter Age..!");
        } else {
            int age = Integer.parseInt(ageText.getText().toString());
            resultText.setText("Your Age: "+age);
            sharedPreferences.edit().putInt("storedAge",age).apply();
        }
    }

    public void delete(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Delete");
        alert.setMessage("Are you sure?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int storedAge = sharedPreferences.getInt("storedAge", 0);
                if(storedAge != 0){
                    sharedPreferences.edit().remove("storedAge").apply();
                    resultText.setText("Your Age: ");
                    Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_LONG).show();
                }

            }
        });
        alert.show();
    }


}
