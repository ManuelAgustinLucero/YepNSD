package es.uem.david.samuel.nacho.yepnsd;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

/**
 * Created by usuario.apellido on 23/01/2015.
 *
 * @author david.sancho
 */
public abstract class AbstractActionBarActivity extends ActionBarActivity {

    protected String getEditTextValue(int id) {
        View v = findViewById(id);
        if(v != null) {
            if(v instanceof EditText) {
                EditText et = (EditText) v;
                Editable editable = et.getText();
                return editable.toString();
            }
        }
        return "";
    }

    protected String getEditTextHint(int id) {
        View v = findViewById(id);
        if(v != null) {
            if(v instanceof EditText) {
                EditText et = (EditText) v;
                return et.getHint().toString();
            }
        }
        return "";
    }

    protected String getEditTextValueAndValidate(int id) {
        String text = getEditTextValue(id);
        String field = getEditTextHint(id);
        validateNoEmpty(text, field);
        return text;
    }

    protected boolean validateNoEmpty(String text, String field) {
        if(isTextEmpty(text)) {
            doAlertDialog(field);
            return false;
        }
        return true;
    }

    protected String getResourceString(int id) {
        Resources res = getResources();
        return res.getString(id);
    }

    protected String getResourceString(int id, String var1) {
        String s = getResourceString(id);
        return String.format(s, var1);
    }

    protected boolean isTextEmpty(String s) {
        return s == null || s.isEmpty();
    }

    protected void doAlertDialog(String field) {
        String title = getResourceString(R.string.alert);
        String msg = getResourceString(R.string.field_missing, field);
        String button = getResourceString(R.string.alert_button);
        doDialog(title, msg, button);
    }

    protected void doDialog(String title, String msg, String button) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(msg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, button,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    protected boolean isAnyEmpty(String... s) {
        for(int i=0;i<s.length;i++) {
            if(isTextEmpty(s[i])) {
                return true;
            }
        }
        return false;
    }

}
