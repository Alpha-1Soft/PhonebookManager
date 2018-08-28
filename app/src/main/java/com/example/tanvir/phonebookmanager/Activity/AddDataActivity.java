package com.example.tanvir.phonebookmanager.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tanvir.phonebookmanager.Database.DatabaseManager;
import com.example.tanvir.phonebookmanager.R;
import com.example.tanvir.phonebookmanager.models.ContactsInfo;

public class AddDataActivity extends AppCompatActivity {
    EditText nameEt,numberEt,emailEt,descriptionEt;
    DatabaseManager databaseManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        Button saveBt = findViewById(R.id.saveButton);

        nameEt = (EditText)findViewById(R.id.nameEtOnSave);
        numberEt =(EditText)findViewById(R.id.phoneNumberEtOnSave);
        emailEt = (EditText)findViewById(R.id.emailEtOnSave);
        descriptionEt = (EditText)findViewById(R.id.descriptionEtOnSave);

        databaseManager=new DatabaseManager(this);

        saveBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });
    }


    public void cancelOnSAve(View view) {
        finish();
    }

    //data insert function
    public void insertData() {
        if(nameEt.getText().toString().length()==0 && numberEt.getText().toString().length()<2){
            Toast.makeText(this, "Check your name and phone number", Toast.LENGTH_SHORT).show();
        }
        else if(numberEt.getText().toString().length()<11){
            Toast.makeText(this, "Check your phone number.", Toast.LENGTH_SHORT).show();
        }
        else if(nameEt.getText().toString().length()==0){
            Toast.makeText(this, "Enter your name.", Toast.LENGTH_SHORT).show();
        }
        else{
            ContactsInfo contactsInfo = new ContactsInfo(nameEt.getText().toString(),numberEt.getText().toString(),emailEt.getText().toString(),descriptionEt.getText().toString(),"0");
            long l = databaseManager.addContactInfo(contactsInfo);

            if(l>0){
                Toast.makeText(this, "Contact information added", Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(AddDataActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
            else{
                Toast.makeText(this, "Unable to add contact information", Toast.LENGTH_SHORT).show();
            }
        }
    }
}