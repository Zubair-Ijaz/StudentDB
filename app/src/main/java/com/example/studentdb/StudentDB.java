package com.example.studentdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class StudentDB {
    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "student_name";
    public static final String KEY_FATHERNAME = "father_name";
    public static final String KEY_CELL = "student_cell";
    public static final String KEY_EMAIL = "_email";
    public static final String KEY_CGPA = "student_cgpa";

    private final String DATABASE_NAME = "StudentsDB";
    private final String DATABASE_TABLE = "StudentTable";
    private final int DATABASE_VERSION = 1;

    private DBHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    public StudentDB(Context context)
    {
        ourContext = context;
    }
    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            String sqlCode = "CREATE TABLE "+DATABASE_TABLE +
                    "("+KEY_ROWID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                    +KEY_NAME +" TEXT NOT NULL,"
                    +KEY_FATHERNAME +" TEXT NOT NULL,"
                    +KEY_CELL +" TEXT NOT NULL,"
                    +KEY_EMAIL +" TEXT NOT NULL,"
                    +KEY_CGPA +" TEXT NOT NULL);";
            sqLiteDatabase.execSQL(sqlCode);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            // code to save previous records of table before deleting it.
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
            onCreate(sqLiteDatabase);
        }
    }

    public StudentDB open()
    {
        ourHelper = new DBHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        ourHelper.close();
    }

    public long createEntry(String name, String fatherName, String cell, String email, String cgpa)
    {
        ContentValues cv = new ContentValues();  // key_value pair
        cv.put(KEY_NAME,name);
        cv.put(KEY_FATHERNAME,fatherName);
        cv.put(KEY_CELL, cell);
        cv.put(KEY_EMAIL,email);
        cv.put(KEY_CGPA,cgpa);

        return ourDatabase.insert(DATABASE_TABLE, null, cv);
    }

    public String getData()
    {
        final String []columns = new String[]{KEY_ROWID, KEY_NAME, KEY_FATHERNAME, KEY_CELL, KEY_EMAIL, KEY_CGPA};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null,null);
        int iRowID = c.getColumnIndex(KEY_ROWID);
        int iName = c.getColumnIndex(KEY_NAME);
        int ifName = c.getColumnIndex(KEY_FATHERNAME);
        int iCell = c.getColumnIndex(KEY_CELL);
        int iEmail = c.getColumnIndex(KEY_EMAIL);
        int iCGPA = c.getColumnIndex(KEY_CGPA);
        String text= "";
        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
        {
            text = text + c.getString(iRowID)+" : "+ c.getString(iName) +" "+c.getString(ifName)+ " "+c.getString(iCell)+" "+ c.getString(iEmail)+" "+ c.getString(iCGPA)+ " " + "\n";
        }
        c.close();
        return text;
    }

    public long deleteEntry(String rowID)
    {
        return ourDatabase.delete(DATABASE_TABLE, KEY_ROWID+"=?", new String[]{rowID});
    }

    public long updateEntry(String rowId, String name, String fatherName, String cell, String email, String cgpa)
    {
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, name);
        cv.put(KEY_FATHERNAME,fatherName);
        cv.put(KEY_CELL, cell);
        cv.put(KEY_EMAIL, email);
        cv.put(KEY_CGPA, cgpa);
        return ourDatabase.update(DATABASE_TABLE, cv, KEY_ROWID+"=?", new String[]{rowId});
    }

}
