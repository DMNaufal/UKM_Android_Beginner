package app_project.healthapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.RequestQueue;

/**
 * Created by Ally on 31/05/2017.
 */

public class SharedPrefManager {

    private static SharedPrefManager mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    private static final String SHARED_PREF_NAME ="mysharedpref12";
    private static final String KEY_ID="id_pasien";
    private static final String KEY_NAME ="nama_pasien";
    private static final String KEY_EMAIL="email_pasien";
    private static final String KEY_TGL="tanggal_pasien";
    private static final String KEY_JK="jenis_kelamin";
    private static final String KEY_ALMT="alamat_pasien";

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }
    public boolean pasienLogin(int id_pasien, String nama_pasien, String email_pasien,
                               String tanggal_lahir, String jenis_kelamin, String alamat_pasien ){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID,id_pasien);
        editor.putString(KEY_NAME,nama_pasien);
        editor.putString(KEY_EMAIL,email_pasien);
        editor.putString(KEY_TGL,tanggal_lahir);
        editor.putString(KEY_JK,jenis_kelamin);
        editor.putString(KEY_ALMT,alamat_pasien);

        editor.apply();
        return true;
    }
    public boolean isLogin(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_EMAIL,null)!=null){
            return true;
        }
        return  false;
    }
    public boolean logout(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }
    public String getEmail(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMAIL,null);
    }
    public String getNama(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_NAME,null);
    }
    public String getKeyTgl(){
        SharedPreferences sharedPrefManager = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPrefManager.getString(KEY_TGL,null);
    }
    public String getKeyAlmt(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ALMT,null);
    }
    public String getKeyJk(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_JK,null);
    }

}