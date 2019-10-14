package com.example.passpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.BitSet;

import Database.DBHandler;

public class ViewPhotos extends AppCompatActivity {

    private Spinner artistSpinner;
    private Button search;
    private DBHandler db;
    private GridView gridView;
    private  ArrayList<Artist> artists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_photos);

        artistSpinner = findViewById(R.id.spinner4);
        search = findViewById(R.id.search);
        gridView = findViewById(R.id.gridview);

        db = new DBHandler(this);

         artists = db.loadArtist();
        ArrayList<String> artistNames = new ArrayList<>();

        for (Artist artist : artists) {
            artistNames.add( artist.getName() );
        }

        ArrayAdapter adapter = new ArrayAdapter(this , R.layout.support_simple_spinner_dropdown_item , artistNames );
        artistSpinner.setAdapter(adapter);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int CurrentAID = 0;
                for (Artist artist : artists) {
                    if( artist.getName().equals(  artistSpinner.getSelectedItem().toString() ) ){
                        CurrentAID = artist.getID();
                    }
                }

                Toast.makeText( ViewPhotos.this , "ID" + CurrentAID , Toast.LENGTH_LONG ).show();
                ArrayList<Bitmap> imagesfromDB =  db.searchPhotograph(CurrentAID );
                ImageAdapter adapterForGridView = new ImageAdapter( ViewPhotos.this ,imagesfromDB );
                gridView.setAdapter( adapterForGridView );
            }
        });


    }
}
