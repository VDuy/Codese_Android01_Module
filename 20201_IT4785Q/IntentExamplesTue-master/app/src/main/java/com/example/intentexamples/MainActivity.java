package com.example.intentexamples;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String myPhoneNumberUri = "tel:555-1234";
//                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(myPhoneNumberUri));
//                startActivity(intent);

//                String myData = "tel:555-1234";
//                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(myData));
//                startActivity(intent);

//                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
//                intent.putExtra(SearchManager.QUERY,"straight hitting golf clubs");
//                startActivity(intent);

//                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:555-4321"));
//                intent.putExtra("sms_body", "are we playing golf next Sunday?");
//                startActivity(intent);

//                Intent intent = new Intent();
//                intent.setType("image/pictures/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivity(intent);

//                String myData = "content://contacts/people/";
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(myData));
//                startActivity(intent);

//                String myData = ContactsContract.Contacts.CONTENT_URI + "/" + "2";
//                Intent intent = new Intent(Intent.ACTION_EDIT, Uri.parse(myData));
//                startActivity(intent);

//                String myUriString = "https://www.hust.edu.vn";
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(myUriString));
//                startActivity(intent);

                String emailSubject = "Department Meeting";
                String emailText = "We’ll discuss the new project "
                        + "on Tue. at 9:00am @ room BU344";
                String[] emailReceiverList = {"v.matos@csuohio.edu"};

                Intent intent = new Intent(Intent.ACTION_SEND);

                intent.setType("vnd.android.cursor.dir/email");
                intent.putExtra(Intent.EXTRA_EMAIL, emailReceiverList);
                intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
                intent.putExtra(Intent.EXTRA_TEXT, emailText);

                startActivity(Intent.createChooser(intent,
                        "To complete action choose:"));

            }
        });
    }
}