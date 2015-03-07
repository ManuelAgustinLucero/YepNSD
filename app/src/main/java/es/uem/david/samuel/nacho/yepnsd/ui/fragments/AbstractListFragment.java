package es.uem.david.samuel.nacho.yepnsd.ui.fragments;

import android.support.v4.app.ListFragment;

import es.uem.david.samuel.nacho.yepnsd.utils.UtilActivity;

/**
 * Created by usuario.apellido on 07/03/2015.
 *
 * @author david.sancho
 */
public abstract class AbstractListFragment extends ListFragment {

    protected static final String ARG_SECTION_NUMBER = "section_number";

    protected UtilActivity getUtil() {
        return new UtilActivity(getActivity());
    }

}
