package es.uem.david.samuel.nacho.yepnsd;

import android.app.Application;

import com.parse.Parse;

import es.uem.david.samuel.nacho.yepnsd.utils.ParseAD;

/**
 * Created by david.sancho on 16/01/2015.
 *
 * @author david.sancho
 */
public class YepApplication extends Application {

    private static YepApplication app;

    public static YepApplication getInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "t6VmmFjjxEJmJWlmMPD1OWX74QP0l2lxlMZAoNE1", "YXGs5UvIXHkDwpJ91mrOlzVw5gs0xutvNr7eW25K");

        ParseAD adatos = ParseAD.getInstance();
        adatos.updateInstallation();

        app = this;
    }

}
