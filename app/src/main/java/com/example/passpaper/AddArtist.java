package com.example.passpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Database.DBHandler;

public class AddArtist extends AppCompatActivity {

    private Button add;
    private EditText artistName;
    private DBHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_artist);

        add = findViewById(R.id.add);
        artistName = findViewById( R.id.artistName );

        db = new DBHandler(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = db.addArtist( artistName.getText().toString().trim() );
                if( result == true ){
                    Toast.makeText( AddArtist.this , "Success" , Toast.LENGTH_LONG ).show();
                }else{
                    Toast.makeText( AddArtist.this , "false" , Toast.LENGTH_LONG ).show();
                }
            }
        });
    }
}
