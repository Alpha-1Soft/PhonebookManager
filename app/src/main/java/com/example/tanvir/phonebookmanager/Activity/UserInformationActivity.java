package com.example.tanvir.phonebookmanager.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.tanvir.phonebookmanager.Database.DatabaseManager;
import com.example.tanvir.phonebookmanager.Fragments.ContactsFragment;
import com.example.tanvir.phonebookmanager.Fragments.FavoriteFragment;
import com.example.tanvir.phonebookmanager.R;
import com.example.tanvir.phonebookmanager.models.ContactsInfo;

public class UserInformationActivity extends AppCompatActivity {
    public static final String IS_CHECKED = "chacked";
    DatabaseManager databaseManager;
    Button callBt,massageBt,extraCallBt;
    TextView nameTv,phoneNumTv,emailTv;

    CheckBox checkBox;
    SharedPreferences sharedPreferences;
    ToggleButton toggleButton;

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

        checkBox = findViewById(R.id.favoriteCheckbox);
        //toggleButton=findViewById(R.id.toogle);

        databaseManager=new DatabaseManager(this);

        SharedPreferences sharedPrefs = getSharedPreferences("Mode", MODE_PRIVATE);
        checkBox.setChecked(sharedPrefs.getBoolean("isClicked", false));

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
        //implement here
    }

    public void massageMethod(View view) {
        //implement here
    }

    public void extraCallMethod(View view) {
        //implement here
    }

    public void phoneNumberMethod(View view) {
        //implement here
    }

    public void sendMailMethod(View view) {
        //implement here
    }

    //edit button
    public void editInformationBt(View view) {
        Intent intent = new Intent(this,UpdateDataActivity.class);
        String id = getIntent().getStringExtra("id");// receiving id from contacts fragment
        intent.putExtra("id",id);//passing specific contact id for updating contact information
        startActivity(intent);
    }

    //on backpressed thing
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent intent = new Intent(UserInformationActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    //favorite contact thing
    @SuppressLint("ResourceAsColor")
    public void favoriteCheckbox(View view) {
        long l=0;
        String id = getIntent().getStringExtra("id");
        ContactsInfo contacts = databaseManager.getContactsById(Integer.parseInt(id));
        ContactsInfo contactsInfo=null;

        boolean checked = ((CheckBox) view).isChecked();

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String phoneNum = intent.getStringExtra("phoneNum");
        String email = intent.getStringExtra("email");
        String description = intent.getStringExtra("description");


        if(checked){
            if(contacts.getContactRating().equals("1")){//remove contact from favorite list
                contactsInfo = new ContactsInfo(Integer.parseInt(id),name,phoneNum,email,description,"0",R.drawable.staroff);
                l = databaseManager.updateContactInfo(contactsInfo);
                if(l>0){
                    Toast.makeText(this, "This contact removed to favorite list", Toast.LENGTH_SHORT).show();
                    checkBox.setButtonDrawable(R.drawable.staroff);
                }
            }
            else{
                //add contact on favorite list
               contactsInfo = new ContactsInfo(Integer.parseInt(id),name,phoneNum,email,description,"1",R.drawable.staron);
                l = databaseManager.updateContactInfo(contactsInfo);
                if(l>0){
                    Toast.makeText(this, "This contact added to favorite list", Toast.LENGTH_SHORT).show();
                    checkBox.setButtonDrawable(R.drawable.staron);
                }
            }
        }
    }

}
