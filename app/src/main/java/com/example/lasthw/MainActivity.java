package com.example.lasthw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editTextName, editTextZipCode, editTextBirdName;

    Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextBirdName = findViewById(R.id.editTextBirdName);
        editTextZipCode = findViewById(R.id.editTextZipCode);

        buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {


        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("MyBird");

        String createName = editTextName.getText().toString();
        String createBirdName = editTextBirdName.getText().toString();
        String createZipCode = editTextZipCode.getText().toString();

        Birds createBird = new Birds(createBirdName, createName, createZipCode);
        myRef.push().setValue(createBird);

        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

        editTextZipCode.setText("");
        editTextName.setText("");
        editTextBirdName.setText("");


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.ItemSearch) {

            //go to search
            Intent SearchIntent = new Intent(this,Search.class);
            startActivity(SearchIntent);

        } else {
         //go to submit
            Intent submitIntent = new Intent(this,MainActivity.class);
            startActivity(submitIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater Inflater = getMenuInflater();
        Inflater.inflate(R.menu.mainmenu, menu);

        return super.onCreateOptionsMenu(menu);
    }
}
