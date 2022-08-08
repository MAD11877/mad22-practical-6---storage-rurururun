package sg.edu.np.practical_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Debug;
import android.util.Log;

import java.io.Console;
import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userDB.db";
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_NAME = "username";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FOLLOWED = "follow_status";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + " TEXT," + COLUMN_DESCRIPTION + " TEXT," + COLUMN_FOLLOWED + " INTEGER" + ")";
        sqLiteDatabase.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<User> getUsers() {
        String query = "SELECT * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<User> userList = new ArrayList<>();

        while(true) {
            Log.v("test", "working");
            User u = new User();
            boolean state = false;
            if (cursor.moveToNext()) {
                u.setName(cursor.getString(1));
                u.setDescription(cursor.getString(2));
                int status = cursor.getInt(3);
                if (status == 1){
                    state = true;
                }
                u.setFollowed(state);
                u.setId(cursor.getInt(0));
                userList.add(u);
            }
            else {
                break;
            }
        }

        return userList;
    }

    public void addUsers(ArrayList<User> userList) {
        for (int i = 0; i < userList.size(); i++) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, userList.get(i).getName());
            values.put(COLUMN_DESCRIPTION, userList.get(i).getDescription());
            if (userList.get(i).isFollowed()) {
                values.put(COLUMN_FOLLOWED, 1);
            }
            else {
                values.put(COLUMN_FOLLOWED, 0);
            }
            values.put(COLUMN_ID, userList.get(i).getId());
            SQLiteDatabase db = this.getWritableDatabase();

            db.insert(TABLE_USERS, null,values);
            db.close();
        }
    }
}
