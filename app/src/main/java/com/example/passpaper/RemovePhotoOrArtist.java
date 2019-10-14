package com.example.passpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

import Database.ArtistMaster;
import Database.DBHandler;

public class RemovePhotoOrArtist extends AppCompatActivity {

    private Spinner spinner;
    private Button delete;
    private DBHandler db;
    private ArrayList<Artist> artists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_photo_or_artist);

        spinner = findViewById(R.id.spinner1);
        delete = findViewById( R.id.delete);

        db = new DBHandler(this);

        artists = db.loadArtist();
        ArrayList<String> artistNames = new ArrayList<>();

        for (Artist artist : artists) {
            artistNames.add( artist.getName() );
        }

        final ArrayAdapter artistNameAdapter = new ArrayAdapter( this , R.layout.support_simple_spinner_dropdown_item , artistNames );
        spinner.setAdapter(artistNameAdapter);


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteDetails( spinner.getSelectedItem().toString() , "artistName"  , "ArtistDetails");
            }
        });
    }
}
