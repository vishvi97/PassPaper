package com.example.passpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button addArtist , AddPhoto , delete , View;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addArtist = findViewById(R.id.addArtist);
        AddPhoto = findViewById(R.id.addPhoto);
        delete = findViewById(R.id.delete);
        View = findViewById(R.id.view);

        addArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent intent = new Intent( MainActivity.this , AddArtist.class );
                startActivity(intent);
            }
        });

        AddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent intent = new Intent( MainActivity.this , AddPhotograph.class );
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent intent = new Intent( MainActivity.this , RemovePhotoOrArtist.class );
                startActivity(intent);
            }
        });

        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Intent intent = new Intent( MainActivity.this , ViewPhotos.class );
                startActivity(intent);
            }
        });


    }
}
