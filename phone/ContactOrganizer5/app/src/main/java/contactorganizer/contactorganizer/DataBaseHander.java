package contactorganizer.contactorganizer;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.v7.widget.ActionBarContextView;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by User on 2016/1/3.
 */
public class DataBaseHander extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION=1;

    private static final String DATABASE_NAME="contactManager",
    TABLE_CONTACTS="id",
    KEI_ID="id",
    KEY_NAME="name",
    KEY_PHONE="phone",
    KEY_EMAIL="email",
    KEY_ADDRESS="address";

    public DataBaseHander(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE" + TABLE_CONTACTS + "(" + KEI_ID + "INTGER PRIMARY KEY," + KEY_NAME + "TEXT" + KEY_PHONE + "TEXT" + KEY_EMAIL + "TEXT" + KEY_ADDRESS + "TEXT");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("DROP TABLE EXISTS" + TABLE_CONTACTS);
        onCreate(db);
    }
    public void createContact(Contact contact){
        SQLiteDatabase db=getWritableDatabase();

        ContentValues values= new ContentValues();

        values.put(KEY_NAME,contact.getName());
        values.put(KEY_PHONE,contact.getName());
        values.put(KEY_EMAIL,contact.getName());
        values.put(KEY_ADDRESS, contact.getName());

        db.insert(TABLE_CONTACTS, null, values);

    }
    public Contact getContact(int id){
        SQLiteDatabase db=getReadableDatabase();

        Cursor cursor=db.query(TABLE_CONTACTS,new String[]{KEI_ID,KEY_NAME,KEY_PHONE,KEY_PHONE,KEY_ADDRESS},KEI_ID+"=?",new String[]{String.valueOf(id)},null, null,null,null);

        if(cursor!=null)
            cursor.moveToFirst();

        Contact contact=new Contact(Integer.parseInt (cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4));
        db.close();
        cursor.close();
        return contact;



    }


    public void deleteContact(Contact contact){
        SQLiteDatabase db= getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEI_ID + "=?", new String[]{String.valueOf(contact.getid())});
        db.close();
    }

    public int getContactsCount(){
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT  FROM" + TABLE_CONTACTS, null);
        int count= cursor.getCount();
        db.close();
        cursor.close();
        return cursor.getCount();
    }

    public int UpdataContact(Contact contact){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values =new ContentValues();

        values.put(KEY_NAME,contact.getName());
        values.put(KEY_PHONE,contact.getName());
        values.put(KEY_EMAIL, contact.getName());
        values.put(KEY_ADDRESS, contact.getName());

        return db.update(TABLE_CONTACTS, values, KEI_ID + "=?", new String[]{String.valueOf(contact.getid())});

    }

    public List<Contact> getallContacts(){
        List<Contact> contacts = new ArrayList<Contact>();
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor =db.rawQuery("SELECT FROM"+TABLE_CONTACTS, null);
        if(cursor.moveToFirst()){
            do{
                Contact contact=new Contact(Integer.parseInt (cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4));
                contacts.add(contact);
            }
            while (cursor.moveToNext());
        }
        return contacts;
    }




}
