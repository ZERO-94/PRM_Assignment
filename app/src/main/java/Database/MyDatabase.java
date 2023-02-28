package Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabase extends SQLiteOpenHelper {

    public MyDatabase(@Nullable Context context) {
        super(context, "InstrumentManagement", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Instrument(code Text primary key not null, name Text, imageUrl Text, price Integer, amount Integer)";
        db.execSQL(sql);
        db.execSQL("INSERT INTO Instrument values('Lop01', 'Smarting1', 'https://plus.unsplash.com/premium_photo-1664194584456-c25febbccb2a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80', 10, 2)");
        db.execSQL("INSERT INTO Instrument values('Lop02', 'Smarting2', 'https://plus.unsplash.com/premium_photo-1664194584456-c25febbccb2a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80', 20, 3)");
        db.execSQL("INSERT INTO Instrument values('Lop03', 'Smarting3', 'https://plus.unsplash.com/premium_photo-1664194584456-c25febbccb2a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80', 15, 4)");
        db.execSQL("INSERT INTO Instrument values('Lop04', 'Smarting4', 'https://plus.unsplash.com/premium_photo-1664194584456-c25febbccb2a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80', 10, 4)");
        db.execSQL("INSERT INTO Instrument values('Lop05', 'Smarting5', 'https://plus.unsplash.com/premium_photo-1664194584456-c25febbccb2a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80', 25, 1)");
        db.execSQL("INSERT INTO Instrument values('Lop06', 'Smarting6', 'https://plus.unsplash.com/premium_photo-1664194584456-c25febbccb2a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80', 12, 2)");
        db.execSQL("INSERT INTO Instrument values('Lop07', 'Smarting7', 'https://plus.unsplash.com/premium_photo-1664194584456-c25febbccb2a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80', 18, 8)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
