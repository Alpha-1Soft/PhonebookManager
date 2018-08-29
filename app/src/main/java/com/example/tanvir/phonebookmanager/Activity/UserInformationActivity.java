package com.example.tanvir.phonebookmanager.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
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
    TextView nameTv,phoneNumTv,emailTv,desTv;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;


    CheckBox checkBox;
    SharedPreferences sharedPreferences;
    ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);

        callBt = findViewById(R.id.callButton);
        massageBt = findViewById(R.id.massegeButton);

        nameTv = findViewById(R.id.contactNameTextView);
        phoneNumTv = findViewById(R.id.phoneNumberTv);
        emailTv = findViewById(R.id.emailTv);
        desTv = findViewById(R.id.descriptionTv);

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

        if (email.length()==0){
            emailTv.setText(" ");
        }
        else{
            emailTv.setText(email);
        }
        if (description.length()==0){
            desTv.setText(" ");
        }
        else{
            desTv.setText(description);
        }
    }



    public void callMethod(View view) {
        checkForPhonePermissionJustCall();
    }

    public void massageMethod(View view) {
        String phoneNum = getIntent().getStringExtra("phoneNum");
        Intent massageIntent = new Intent(Intent.ACTION_SENDTO);
        massageIntent.setData(Uri.parse("smsto:"+phoneNum));
        Intent massageChooser = Intent.createChooser(massageIntent,"Choose your application");
        startActivity(massageChooser);
    }

    public void phoneNumberMethod(View view) {
        checkForPhonePermissionJustCall();
    }
    //checking phone permission here
    private void checkForPhonePermissionJustCall() {
        Intent intent = getIntent();
        String phone = intent.getStringExtra("phoneNum");
        if (phone.length() > 0) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // Permission not yet granted. Use requestPermissions().
                // Log.d(Tag, getString(R.string.permission_not_grante));
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},
                        MY_PERMISSIONS_REQUEST_CALL_PHONE);
            } else {
                Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                //Intent intentChooser = Intent.createChooser(callIntent,"Choose your application");
                startActivity(callIntent);
            }
        } else {
            Toast.makeText(getApplicationContext(), "Enter number", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                checkForPhonePermissionJustCall();
            }
        }
        else Toast.makeText(getApplicationContext(),"Permission denied",Toast.LENGTH_SHORT).show();
    }


    public void sendMailMethod(View view) {
        String email = getIntent().getStringExtra("email");
        if(email.length()!=0){
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
            Intent emailChoose = Intent.createChooser(emailIntent, "Choose your application");
            startActivity(emailChoose);
        }
        else{
            Toast.makeText(this, "Empty email", Toast.LENGTH_SHORT).show();
        }
    }

    //edit button
    public void editInformationBt(View view) {
        Intent intent = new Intent(this,UpdateDataActivity.class);
        String id = getIntent().getStringExtra("id");// receiving id from contacts fragment
        intent.putExtra("id",id);//passing specific contact id for updating contact information
        startActivity(intent);
    }

    public void descriptionMethod(View view) {
        String description = getIntent().getStringExtra("description");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(description);

        if(description.length()>0){
            AlertDialog.Builder alBuilder = new AlertDialog.Builder(this);
            alBuilder.setMessage(stringBuffer.toString());
            alBuilder.setTitle("Contact description");
            alBuilder.setCancelable(true);
            alBuilder.show();
        }
        else{
            Toast.makeText(this, "Empty description", Toast.LENGTH_SHORT).show();
        }
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
