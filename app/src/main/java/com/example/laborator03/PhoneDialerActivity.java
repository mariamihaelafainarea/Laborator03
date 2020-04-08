package com.example.laborator03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class PhoneDialerActivity extends AppCompatActivity {

    EditText phoneNumberEditText;
    Button genericButton;
    ImageButton callButton;
    ImageButton eraseButton;
    ImageButton hangup;
    GenericOnClickListener GenericOnClickListener = new GenericOnClickListener();
    EraseOnClickListener EraseOnClickListener = new EraseOnClickListener();
    CallClickListener CallClickListener = new CallClickListener();
    HangupListener HangupListener = new HangupListener();

    final public static int genericButtonsNames[] = {
            R.id.number_0_button,
            R.id.number_1_button,
            R.id.number_2_button,
            R.id.number_3_button,
            R.id.number_4_button,
            R.id.number_5_button,
            R.id.number_6_button,
            R.id.number_7_button,
            R.id.number_8_button,
            R.id.number_9_button,
            R.id.star_button,
            R.id.pound_button
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_dialer);

        phoneNumberEditText = findViewById(R.id.phone_number_edit_text);


        for (int i = 0; i < genericButtonsNames.length; i++) {
            genericButton = findViewById(Integer.valueOf(genericButtonsNames[i]));
            genericButton.setOnClickListener(GenericOnClickListener);
        }
        eraseButton = findViewById(R.id.backspace_image_button);
        eraseButton.setOnClickListener(EraseOnClickListener);

        callButton = findViewById(R.id.call_image_button);
        callButton.setOnClickListener(CallClickListener);

        hangup = findViewById(R.id.hangup_image_button);
        hangup.setOnClickListener(HangupListener);

    }

    protected class GenericOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            String text = phoneNumberEditText.getText().toString();
            phoneNumberEditText.setText(text + ((Button)view).getText());
            Log.d("mesaj de la mine",phoneNumberEditText.getText().toString());
        }
    }

    protected class EraseOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            String text = phoneNumberEditText.getText().toString();
            if(text.length() > 1) {
                phoneNumberEditText.setText(text.substring(0, text.length() - 1));

            }
        }
    }

    protected class CallClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if(ContextCompat.checkSelfPermission(PhoneDialerActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(PhoneDialerActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE}, 1);
            }else {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + phoneNumberEditText.getText().toString()));
                startActivity(intent);
            }
        }

    }
    protected class HangupListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            finish();
        }
    }
}
