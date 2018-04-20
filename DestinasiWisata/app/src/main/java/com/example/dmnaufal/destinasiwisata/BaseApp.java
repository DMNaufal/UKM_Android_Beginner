package com.example.dmnaufal.destinasiwisata;

import android.app.Application;

import com.example.dmnaufal.destinasiwisata.db.facade.Facade;
import com.example.dmnaufal.destinasiwisata.db.facade.FacadeOpenHelper;
import com.example.dmnaufal.destinasiwisata.model.DaoMaster;
import com.example.dmnaufal.destinasiwisata.model.DaoSession;

import org.greenrobot.greendao.database.Database;

import timber.log.Timber;

/**
 * Created by DMNaufal on 25/03/2018.
 */

public class BaseApp extends Application{
    private static final boolean ENCRYPTED = false;
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
        String databaseName = "wisata-db";
        FacadeOpenHelper helper = new FacadeOpenHelper(this, databaseName);

        String password = "y=m+c^";
        Timber.e("database name : %s", databaseName);
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb(password)
                : helper.getWritableDb();



        daoSession = new DaoMaster(db).newSession();

        Facade.init(daoSession);

    }
    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
