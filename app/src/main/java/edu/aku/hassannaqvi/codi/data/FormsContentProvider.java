package edu.aku.hassannaqvi.codi.data;

/**
 * Created by hassan.naqvi on 9/6/2017.
 */


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;

import edu.aku.hassannaqvi.codi.core.DatabaseHelper;

import static edu.aku.hassannaqvi.codi.data.FormsContract.FormsTable.TABLE_NAME;

// Verify that FormsContentProvider extends from ContentProvider and implements required methods
public class FormsContentProvider extends ContentProvider {

    // Define final integer constants for the directory of forms and a single item.
    // It's convention to use 100, 200, 300, etc for directories,
    // and related ints (101, 102, ..) for items in that directory.
    public static final int FORMS = 100;
    public static final int FORM_WITH_ID = 101;

    // CDeclare a static variable for the Uri matcher that you construct
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    // Define a static buildUriMatcher method that associates URI's with their int match
    // Member variable for a FormDbHelper that's initialized in the onCreate() method
    private DatabaseHelper db;

    /**
     * Initialize a new matcher object without any matches,
     * then use .addURI(String authority, String path, int match) to add matches
     */
    public static UriMatcher buildUriMatcher() {

        // Initialize a UriMatcher with no matches by passing in NO_MATCH to the constructor
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        /*
          All paths added to the UriMatcher have a corresponding int.
          For each kind of uri you may want to access, add the corresponding match with addURI.
          The two calls below add matches for the form directory and a single item by ID.
         */
        uriMatcher.addURI(FormsContract.AUTHORITY, FormsContract.PATH_FORMS, FORMS);
        uriMatcher.addURI(FormsContract.AUTHORITY, FormsContract.PATH_FORMS + "/#", FORM_WITH_ID);

        return uriMatcher;
    }

    /* onCreate() is where you should initialize anything you’ll need to setup
    your underlying data source.
    In this case, you’re working with a SQLite database, so you’ll need to
    initialize a DbHelper to gain access to it.
     */
    @Override
    public boolean onCreate() {
        // Complete onCreate() and initialize a FormDbhelper on startup
        // [Hint] Declare the DbHelper as a global variable

        Context context = getContext();
        db = new DatabaseHelper(context);
        return true;
    }


    // Implement insert to handle requests to insert a single new row of data
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        // Get access to the form database (to write new data to)
        final SQLiteDatabase db = this.db.getWritableDatabase();

        // Write URI matching code to identify the match for the forms directory
        int match = sUriMatcher.match(uri);
        Uri returnUri; // URI to be returned

        switch (match) {
            case FORMS:
                // Insert new values into the database
                // Inserting values into forms table
                long id = db.insert(TABLE_NAME, null, values);
                if (id > 0) {
                    returnUri = ContentUris.withAppendedId(FormsContract.FormsTable.CONTENT_URI, id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            // Set the value for the returnedUri and write the default case for unknown URI's
            // Default case throws an UnsupportedOperationException
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        // Notify the resolver if the uri has been changed, and return the newly inserted URI
        getContext().getContentResolver().notifyChange(uri, null);

        // Return constructed uri (this points to the newly inserted row of data)
        return returnUri;
    }


    // Implement query to handle requests for data by URI
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        // Get access to underlying database (read-only for query)
        final SQLiteDatabase db = this.db.getReadableDatabase();

        // Write URI match code and set a variable to return a Cursor
        int match = sUriMatcher.match(uri);
        Cursor retCursor;

        // Query for the forms directory and write a default case
        switch (match) {
            // Query for the forms directory
            case FORMS:
                retCursor = db.query(TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            // Default exception
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        // Set a notification URI on the Cursor and return that Cursor
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);

        // Return the desired Cursor
        return retCursor;
    }


    // Implement delete to delete a single row of data
    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {

        // Get access to the database and write URI matching code to recognize a single item
        final SQLiteDatabase db = this.db.getWritableDatabase();

        int match = sUriMatcher.match(uri);
        // Keep track of the number of deleted forms
        int formsDeleted; // starts as 0

        // Write the code to delete a single row of data
        // [Hint] Use selections to delete an item by its row ID
        switch (match) {
            // Handle the single item case, recognized by the ID included in the URI path
            case FORM_WITH_ID:
                // Get the form ID from the URI path
                String id = uri.getPathSegments().get(1);
                // Use selections/selectionArgs to filter for this ID
                formsDeleted = db.delete(TABLE_NAME, "_id=?", new String[]{id});
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        // Notify the resolver of a change and return the number of items deleted
        if (formsDeleted != 0) {
            // A form was deleted, set notification
            getContext().getContentResolver().notifyChange(uri, null);
        }

        // Return the number of forms deleted
        return formsDeleted;
    }


    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public String getType(@NonNull Uri uri) {

        throw new UnsupportedOperationException("Not yet implemented");
    }

}