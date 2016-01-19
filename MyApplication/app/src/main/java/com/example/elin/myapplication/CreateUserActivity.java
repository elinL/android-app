package com.example.elin.myapplication;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class CreateUserActivity extends ActionBarActivity {

    private EditText firstnameInput;
    private EditText lastnameInput;
    private EditText emailInput;
    private EditText passwordInput;
    private EditText repeatPasswordInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Detects things you might do by accident and brings them to your attention so you can fix them
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_create_user);

        firstnameInput = (EditText)findViewById(R.id.firstnameInput);
        lastnameInput = (EditText)findViewById(R.id.lastnameInput);
        emailInput = (EditText)findViewById(R.id.emailInput);
        passwordInput = (EditText)findViewById(R.id.passwordInput);
        repeatPasswordInput = (EditText)findViewById(R.id.repeatPasswordInput);

        Button confirmButton = (Button)findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    testInput(v);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        }


    private void testInput(View v) throws IOException {

        String fInput = firstnameInput.getText().toString();
        String lInput = lastnameInput.getText().toString();
        String eInput = emailInput.getText().toString();
        String pInput = passwordInput.getText().toString();
        String rPInput = repeatPasswordInput.getText().toString();
        InputStream is = null;

        if (TextUtils.isEmpty(fInput)) {
            Toast.makeText(this, "Please enter your firstname", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(lInput)) {
            Toast.makeText(this, "Please enter your lastname", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(eInput)) {
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(pInput)) {
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(rPInput)) {
            Toast.makeText(this, "Please enter your password once again", Toast.LENGTH_SHORT).show();
        } else if (!rPInput.equals(pInput)) {
            Toast.makeText(this, "Your passwords doesn't match \n Please try again", Toast.LENGTH_SHORT).show();
        } else {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://127.0.0.1:5000/register");

            try {
                List<NameValuePair> nameValuePairs = new ArrayList<>(4);
                nameValuePairs.add(new BasicNameValuePair("firstname", fInput));
                nameValuePairs.add(new BasicNameValuePair("lastname", lInput));
                nameValuePairs.add(new BasicNameValuePair("email", eInput));
                nameValuePairs.add(new BasicNameValuePair("password", pInput));
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse response = httpClient.execute(httpPost);
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

                Toast.makeText(this, "You've successfully created an account", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                startActivity(intent);
                finish();

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
