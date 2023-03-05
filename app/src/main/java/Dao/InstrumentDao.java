package Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import Database.MyDatabase;
import Models.Instrument;

public class InstrumentDao {

    MyDatabase myDatabase;

    public InstrumentDao(Context context) {
        myDatabase = new MyDatabase(context);
    }

    public ArrayList<Instrument> getAll() {
        ArrayList<Instrument> data = new ArrayList<>();
        SQLiteDatabase db = myDatabase.getReadableDatabase();

        Cursor cs = db.rawQuery("SELECT * FROM Instrument", null);
        cs.moveToFirst();

        while(!cs.isAfterLast()) {
            String code = cs.getString(0);
            String name = cs.getString(1);
            String imageUrl = cs.getString(2);
            int price = cs.getInt(3);
            int amount = cs.getInt(4);
            String sound = cs.getString(5);

            data.add(new Instrument(code, name, imageUrl, price, amount, sound));
            cs.moveToNext();
        }

        return data;
    }

    public boolean create(Instrument newClassRoom) {
        SQLiteDatabase db = myDatabase.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("code", newClassRoom.getCode());
        values.put("name", newClassRoom.getName());
        values.put("imageUrl", newClassRoom.getImageUrl());
        values.put("price", newClassRoom.getPrice());
        values.put("amount", newClassRoom.getAmount());
        values.put("sound", newClassRoom.getSound());

        long row = db.insert("Instrument", null, values);
        return row > 0;
    }

    public boolean update(Instrument updateClassRoom) {
        SQLiteDatabase db = myDatabase.getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", updateClassRoom.getName());
        values.put("imageUrl", updateClassRoom.getImageUrl());
        values.put("price", updateClassRoom.getPrice());
        values.put("amount", updateClassRoom.getAmount());
        values.put("sound", updateClassRoom.getSound());

        long row = db.update("Instrument", values, "code=?", new String[] {updateClassRoom.getCode()});
        return row > 0;
    }

    public boolean delete(String code) {
        SQLiteDatabase db = myDatabase.getReadableDatabase();

        long row = db.delete("Instrument", "code=?", new String[] {code});
        return row > 0;
    }
}
