package com.att.app1usingsameappid;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String STRING_VALUE = "stringKey";
    public static final String INT_VALUE = "intKey";
    public static final String LONG_VALUE = "longKey";
    public static final String FLOAT_VALUE = "floatKey";
    public static final String BOOLEAN_VALUE = "booleanKeys";

    EditText edt_string, edt_int, edt_long, edt_float;
    CheckBox cbk;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(MyPREFERENCES,
                Context.MODE_PRIVATE); // available only to this activity
        edt_string = (EditText) findViewById(R.id.editTextString);
        edt_int = (EditText) findViewById(R.id.editTextInt);
        edt_long = (EditText) findViewById(R.id.editTextLong);
        edt_float = (EditText) findViewById(R.id.editTextFloat);
        cbk = (CheckBox) findViewById(R.id.checkBox1);

        loadSavedPreferences();
    }

    private void loadSavedPreferences() {
// TODO Auto-generated method stub

        boolean checkBoxValue = sharedPreferences.getBoolean(BOOLEAN_VALUE,
                false);
        String stringValue = sharedPreferences.getString(STRING_VALUE, "");
        int intValue = sharedPreferences.getInt(INT_VALUE, 0);
        long longValue = sharedPreferences.getLong(LONG_VALUE, 0);
        float floatValue = sharedPreferences.getFloat(FLOAT_VALUE, 0);

        if (checkBoxValue) {
            cbk.setChecked(true);
            cbk.setText("true");
        } else {
            cbk.setChecked(false);
            cbk.setText("false");
        }

        if (sharedPreferences.contains(STRING_VALUE)) {
            edt_string.setText(stringValue);
        }
        if (sharedPreferences.contains(INT_VALUE)) {
            edt_int.setText(String.valueOf(intValue));
        }
        if (sharedPreferences.contains(LONG_VALUE)) {
            edt_long.setText(String.valueOf(longValue));
        }
        if (sharedPreferences.contains(FLOAT_VALUE)) {
            edt_float.setText(String.valueOf(floatValue));
        }

    }

    public void clear(View view) {
        Editor editor = sharedPreferences.edit();
// editor.remove(STRING_VALUE); // use this for removing certain key
// only
        editor.clear();
        editor.commit();
        Toast.makeText(MainActivity.this, "All values removed",Toast.LENGTH_SHORT).show();
    }

    public void save(View view) {

        String stringValue = edt_string.getText().toString();
        String intValue = edt_int.getText().toString();
        String floatValue = edt_float.getText().toString();
        String longValue = edt_long.getText().toString();

        Editor editor = sharedPreferences.edit();
        editor.putString(STRING_VALUE, stringValue);
        if (intValue.isEmpty()) {
            edt_int.setError("Cant be empty");
        } else {
            editor.putInt(INT_VALUE, Integer.valueOf(intValue));
        }
        if (longValue.isEmpty()) {
            edt_long.setError("Cant be empty");
        } else {
            editor.putLong(LONG_VALUE, Long.valueOf(longValue));
        }
        if (floatValue.isEmpty()) {
            edt_float.setError("Cant be empty");
        } else {
            editor.putFloat(FLOAT_VALUE, Float.valueOf(floatValue));
        }

        editor.putBoolean(BOOLEAN_VALUE, cbk.isChecked());

        editor.commit();

        Toast.makeText(MainActivity.this, "All Values Saved",
        Toast.LENGTH_SHORT).show();
    }



}
