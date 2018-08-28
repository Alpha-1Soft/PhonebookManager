package com.example.tanvir.phonebookmanager.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tanvir.phonebookmanager.Database.DatabaseManager;
import com.example.tanvir.phonebookmanager.Fragments.ContactsFragment;
import com.example.tanvir.phonebookmanager.R;
import com.example.tanvir.phonebookmanager.models.ContactsInfo;

public class UpdateDataActivity extends AppCompatActivity {
    DatabaseManager databaseManager;
    EditText nameEt,numberEt,emailEt,descriptionEt;
    int cId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        databaseManager=new DatabaseManager(this);

        nameEt = (EditText)findViewById(R.id.nameEtOnUpdate);
        numberEt =(EditText)findViewById(R.id.phoneNumberEtOnUpdate);
        emailEt = (EditText)findViewById(R.id.emailEtonUpdate);
        descriptionEt = (EditText)findViewById(R.id.descriptionEtOnUpdate);
        String id = getIntent().getStringExtra("id");

        cId = Integer.parseInt(id);

        ContactsInfo contactsInfos = databaseManager.getContactsById(cId);
        //setting all previous data to updateData activity
        nameEt.setText(contactsInfos.getContactName());
        numberEt.setText(contactsInfos.getContactNumber());
        emailEt.setText(contactsInfos.getContactEmail());
        descriptionEt.setText(contactsInfos.getContactDescription());
    }

    //cancel button method
    public void cancelBtOnUpdate(View view) {
       finish();
    }

    //update button method
    public void updateButton(View view) {
        updateMethod();
    }

    //cancel method for finish current activity and go to previous activity
    public void updateAndExit(){
        finish();
        Intent intent = new Intent(this,UserInformationActivity.class);
        startActivity(intent);
    }

    //updating information here
    public void updateMethod(){
        ContactsInfo contacts = databaseManager.getContactsById(cId);
        ContactsInfo contactsInfo;
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
            if(contacts.getContactRating().equals("1")){//comparing with rating, if true then we insert 1 into db again
               contactsInfo = new ContactsInfo(cId,nameEt.getText().toString(),numberEt.getText().toString(),emailEt.getText().toString(),descriptionEt.getText().toString(),"1",R.drawable.staron);
            }
            else{
                contactsInfo = new ContactsInfo(cId,nameEt.getText().toString(),numberEt.getText().toString(),emailEt.getText().toString(),descriptionEt.getText().toString(),"0",R.drawable.staroff);
            }
            long l = databaseManager.updateContactInfo(contactsInfo);//updating database here

            if(l>0){
                Toast.makeText(this, "Contact information updated", Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(UpdateDataActivity.this,UserInformationActivity.class);
                //passing updated data to User information activity
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("id",String.valueOf(cId));
                intent.putExtra("name",contactsInfo.getContactName());
                intent.putExtra("phoneNum",contactsInfo.getContactNumber());
                intent.putExtra("email",contactsInfo.getContactEmail());
                intent.putExtra("description",contactsInfo.getContactDescription());
                startActivity(intent);

            }
            else{
                Toast.makeText(this, "Unable to add contact information", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
