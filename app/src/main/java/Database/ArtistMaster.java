package Database;

import android.provider.BaseColumns;

public final class ArtistMaster {

    private ArtistMaster(){}

    protected static class Artist implements BaseColumns {
        public static final String TABLE_NAME = "ArtistDetails";
        public static final String COLUMN_NAME_ARTIST= "artistName";
    }
     protected static class Photograph implements BaseColumns {
        public static final String TABLE_NAME = "PhotographDetails";
        public static final String COLUMN_NAME_PHOTOGRAPH = "photographName";
        public static final String COLUMN_NAME_ARTISTID = "artistID";
        public static final String COLUMN_NAME_PHOTOCATEGORY= "photoCategory";
        public static final String COLUMN_NAME_IMAGE = "Image";
    }


}
