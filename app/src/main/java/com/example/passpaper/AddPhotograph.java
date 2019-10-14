package com.example.passpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import Database.DBHandler;

public class AddPhotograph extends AppCompatActivity {

    private EditText imageName;
    private Spinner artistSpinner , categorySpinner;
    private Button add;
    private ImageView image;
    private DBHandler db;
    private Bitmap savedImage;
    private ArrayList<Artist> artists;

    private static final int REQUEST_IMAGE = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addphotograph);

        imageName = findViewById(R.id.imageName );

        artistSpinner = findViewById(R.id.spinner1);
        categorySpinner = findViewById(R.id.spinner2);

        add = findViewById(R.id.add);
        image = findViewById(R.id.image);

        db = new DBHandler(this);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Intent.ACTION_PICK , MediaStore.Images.Media.INTERNAL_CONTENT_URI );
                startActivityForResult( intent ,REQUEST_IMAGE  );
            }
        });

       String categories[] = { "Landscape" , "Wild Life" , "Potrait" , "Wedding" , "Fashion" , "Black and White"};

        ArrayAdapter categoryAdapter = new ArrayAdapter( this , R.layout.support_simple_spinner_dropdown_item , categories );
        categorySpinner.setAdapter( categoryAdapter );

        artists = db.loadArtist();
        ArrayList<String> artistNames = new ArrayList<>();

        for (Artist artist : artists) {
            artistNames.add( artist.getName() );
        }

        final ArrayAdapter artistNameAdapter = new ArrayAdapter( this , R.layout.support_simple_spinner_dropdown_item , artistNames );
        artistSpinner.setAdapter(artistNameAdapter);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String imagen = imageName.getText().toString().trim();
                int CurrentAID = 0;
                for (Artist artist : artists) {
                    if( artist.getName().equals(  artistSpinner.getSelectedItem().toString() ) ){
                        CurrentAID = artist.getID();
                    }
                }

                String cate = categorySpinner.getSelectedItem().toString();

                 boolean result = db.addPhotos(  imagen ,CurrentAID , cate , savedImage   );
                if( result == true ){
                    Toast.makeText( AddPhotograph.this , "Success" , Toast.LENGTH_LONG ).show();
                }else{
                    Toast.makeText( AddPhotograph.this , "false" , Toast.LENGTH_LONG ).show();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            try {
                savedImage = MediaStore.Images.Media.getBitmap( getContentResolver() , uri );
            } catch (IOException e) {
                e.printStackTrace();
            }
            image.setImageBitmap( savedImage );

        }
    }
}
