package com.sabutos.translation.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sabutos.translation.model.Category;
import com.sabutos.translation.model.Translation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by devil on 16-Nov-16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "translationapp.sqlite";
    public static final String DBLOCATION = "/data/data/com.sabutos.translation/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if (mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    public List<Category> getAllCategoryList(){
        Category category = null;
        List<Category> categoryList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM category ORDER BY expt_title",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            category = new Category(cursor.getInt(0),cursor.getString(1));
            categoryList.add(category);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return categoryList;
    }

    public List<Translation> getAllTranslationOnCategory(String refCategory){
        Translation translation = null;
        List<Translation> translationList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * from translation as a \n" +
                "LEFT outer join translation_category as c  On c.exp_id=a.id\n" +
                "LEFT outer join category  as b  On b.expt_id=c.extp_id\n" +
                "where b.expt_title='"+refCategory+"'",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            translation = new Translation(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
            translationList.add(translation);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return translationList;
    }
}
