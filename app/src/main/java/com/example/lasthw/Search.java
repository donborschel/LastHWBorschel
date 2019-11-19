package com.example.lasthw;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Search extends AppCompatActivity implements View.OnClickListener{


    EditText editTextZipCodeSearch;
    TextView textViewBirdName, textViewName;
    Button buttonSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editTextZipCodeSearch = findViewById(R.id.editTextZipCodeSearch);
        textViewBirdName = findViewById(R.id.textViewBirdName);
        textViewName = findViewById(R.id.textViewName);

        buttonSearch = findViewById(R.id.buttonSearch);

        buttonSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("MyBird");

        String findZip = editTextZipCodeSearch.getText().toString();

        myRef.orderByChild("ZipCode").equalTo(findZip).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Birds foundBird = dataSnapshot.getValue(Birds.class);
                String findName = foundBird.Name;
                String findBirdName = foundBird.BirdName;

                textViewName.setText(findName);
                textViewBirdName.setText(findBirdName);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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
