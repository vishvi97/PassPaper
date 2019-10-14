package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.passpaper.Artist;
import com.example.passpaper.Photograph;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ArtistPhotograph.db";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_ARTIST_TABLE = "CREATE TABLE " + ArtistMaster.Artist.TABLE_NAME + " (" +
                                     ArtistMaster.Artist._ID + " INTEGER PRIMARY KEY, " +
                                     ArtistMaster.Artist.COLUMN_NAME_ARTIST + " TEXT );";

        String SQL_CREATE_PHOTOGRAPH_TABLE = "CREATE TABLE " + ArtistMaster.Photograph.TABLE_NAME + " ( " +
                ArtistMaster.Photograph._ID + " INTEGER PRIMARY KEY," +
                ArtistMaster.Photograph.COLUMN_NAME_PHOTOGRAPH + " TEXT , " +
                ArtistMaster.Photograph.COLUMN_NAME_ARTISTID + " INTEGER ," +
                ArtistMaster.Photograph.COLUMN_NAME_PHOTOCATEGORY + " TEXT , " +
                ArtistMaster.Photograph.COLUMN_NAME_IMAGE + " BLOB  );";


        sqLiteDatabase.execSQL(SQL_CREATE_ARTIST_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_PHOTOGRAPH_TABLE );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addPhotos(  String photoName , int AID , String category , Bitmap image   ){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put( ArtistMaster.Photograph.COLUMN_NAME_PHOTOGRAPH , photoName );
        values.put( ArtistMaster.Photograph.COLUMN_NAME_ARTISTID , AID );
        values.put( ArtistMaster.Photograph.COLUMN_NAME_PHOTOCATEGORY , category );

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress( Bitmap.CompressFormat.PNG , 0 , stream );
        byte[] imagearray = stream.toByteArray();

        values.put( ArtistMaster.Photograph.COLUMN_NAME_IMAGE , imagearray );


        long id = db.insert( ArtistMaster.Photograph.TABLE_NAME , null , values );
        if( id > 0 ){
            return true;
        }else{
            return false;
        }
    }

    public boolean addArtist( String artistName ){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put( ArtistMaster.Artist.COLUMN_NAME_ARTIST , artistName );

        long id = db.insert( ArtistMaster.Artist.TABLE_NAME , null , values );
        Log.i( "DB" , id+"");
        if( id > 0 ){
            return true;
        }else{
            return false;
        }
    }

    public boolean deleteDetails(  String ID , String columnName ,  String tablename ){
        SQLiteDatabase db = getReadableDatabase();

        String Selection =  columnName + " = ? ";
        String Args[] = { String.valueOf(ID)};

        int result = db.delete( tablename , Selection , Args );
        if( result > 0 ){
            return true;
        }else{
            return  false;
        }

    }

    public ArrayList<Artist> loadArtist(){

        SQLiteDatabase db = getReadableDatabase();

        String Projection[] = { ArtistMaster.Artist._ID , ArtistMaster.Artist.COLUMN_NAME_ARTIST };

        Cursor cursor = db.query( ArtistMaster.Artist.TABLE_NAME , Projection , null, null , null , null , null );

        ArrayList<Artist> artists = new ArrayList<>();
        while( cursor.moveToNext() ){

            String name = cursor.getString( cursor.getColumnIndexOrThrow( ArtistMaster.Artist.COLUMN_NAME_ARTIST  ));
            int  ID = cursor.getInt( cursor.getColumnIndexOrThrow( ArtistMaster.Artist._ID ));

            Artist newArtist = new Artist( ID , name );
            artists.add(newArtist);
        }

        return artists;
    }

    public ArrayList<Bitmap> searchPhotograph( int AID ){

        SQLiteDatabase db = getReadableDatabase();

        String Projection[] = { ArtistMaster.Photograph.COLUMN_NAME_ARTISTID , ArtistMaster.Photograph.COLUMN_NAME_IMAGE  };
        String Selection = ArtistMaster.Photograph.COLUMN_NAME_ARTISTID + " =  ? ";
        String Args[] = { String.valueOf(AID)};

        Cursor cursor = db.query( ArtistMaster.Photograph.TABLE_NAME , Projection , Selection, Args , null , null , null );

        ArrayList<Bitmap> images = new ArrayList<>();
        while( cursor.moveToNext() ){
            //Log.i( "DB" ,"ID : "+ cursor.getInt( cursor.getColumnIndexOrThrow( ArtistMaster.Photograph.COLUMN_NAME_ARTISTID ) ));
            byte imageFromDatabase []  = cursor.getBlob( cursor.getColumnIndexOrThrow( ArtistMaster.Photograph.COLUMN_NAME_IMAGE ));
            Bitmap image = BitmapFactory.decodeByteArray( imageFromDatabase , 0 , imageFromDatabase.length );

            images.add(image);
        }

        return images;
    }


}
