package com.example.tanvir.phonebookmanager.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tanvir.phonebookmanager.Database.DatabaseManager;
import com.example.tanvir.phonebookmanager.Fragments.ContactsFragment;
import com.example.tanvir.phonebookmanager.R;

public class UserInformationActivity extends AppCompatActivity {

    DatabaseManager databaseManager;
    Button callBt,massageBt,extraCallBt;
    TextView nameTv,phoneNumTv,emailTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);

        callBt = findViewById(R.id.callButton);
        massageBt = findViewById(R.id.massegeButton);
        extraCallBt = findViewById(R.id.extraCallButton);

        nameTv = findViewById(R.id.contactNameTextView);
        phoneNumTv = findViewById(R.id.phoneNumberTv);
        emailTv = findViewById(R.id.emailTv);


        receiveIntentValue();

    }
    //receiving all data from contact fragment activity
    public void receiveIntentValue(){
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String phoneNum = intent.getStringExtra("phoneNum");
        String email = intent.getStringExtra("email");
        String description = intent.getStringExtra("description");

        nameTv.setText(name);
        phoneNumTv.setText(phoneNum);
        emailTv.setText(email);
    }



    public void callMethod(View view) {
        Toast.makeText(this, "Call clicked", Toast.LENGTH_SHORT).show();
    }

    public void massageMethod(View view) {
    }

    public void extraCallMethod(View view) {
    }

    public void phoneNumberMethod(View view) {
         Toast.makeText(this, "Phone number clicked", Toast.LENGTH_SHORT).show();
    }

    public void sendMailMethod(View view) {
    }

    public void editInformationBt(View view) {
        Intent intent = new Intent(this,UpdateDataActivity.class);
        String id = getIntent().getStringExtra("id");// receiving id from contacts fragment
        intent.putExtra("id",id);//passing specific contact id for updating contact information
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent intent = new Intent(UserInformationActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
