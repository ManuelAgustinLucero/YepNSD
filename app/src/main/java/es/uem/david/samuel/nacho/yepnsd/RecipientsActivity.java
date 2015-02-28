package es.uem.david.samuel.nacho.yepnsd;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;


public class RecipientsActivity extends ActionBarActivity {

    private static RecipientsActivity act;
    private MenuItem mSendMenuItem;
    private Uri mMediaUri;
    private String mFileType;

    public static RecipientsActivity getInstance() {
        return act;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipients);

        act = this;

        Intent intent = getIntent();
        mMediaUri = intent.getData();
        mFileType = intent.getStringExtra(Constantes.ParseClasses.Messages.KEY_FILE_TYPE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recipients, menu);
        mSendMenuItem = menu.findItem(R.id.action_send);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_send) {
            RecipientsFragment fragment = RecipientsFragment.getInstance();

            ParseObject message = null;
            AsyncTask task = new AsyncTask() {
                @Override
                protected Object doInBackground(Object[] params) {
                    return null;
                }
            };
            if (fragment != null) {
                message = fragment.createMessage(mMediaUri, mFileType);
            }
            if (message != null) {
                sendMessage(message);
            } else {
                //TODO Message Error
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void sendMessage(ParseObject pObject) {
        pObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(RecipientsActivity.this, "Message sended!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    // TODO Error Message
                }
            }
        });
    }

    public void hideSendMessage() {
        if (mSendMenuItem != null)
            mSendMenuItem.setVisible(false);
    }

    public void showSendMessage() {
        if (mSendMenuItem != null)
            mSendMenuItem.setVisible(true);
    }
}