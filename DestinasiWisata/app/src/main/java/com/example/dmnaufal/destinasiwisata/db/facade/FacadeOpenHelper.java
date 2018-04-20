package com.example.dmnaufal.destinasiwisata.db.facade;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.dmnaufal.destinasiwisata.model.DaoMaster;

/**
 * Created by DMNaufal on 25/03/2018.
 */

public class FacadeOpenHelper extends DaoMaster.OpenHelper {
    public FacadeOpenHelper(Context context, String name) {
        super(context, name);
    }

    public FacadeOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }
}
