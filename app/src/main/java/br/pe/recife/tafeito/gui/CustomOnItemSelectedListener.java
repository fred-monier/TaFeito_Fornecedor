package br.pe.recife.tafeito.gui;

import android.view.View;
import android.widget.AdapterView;

/**
 * Created by Laís Vidoto on 04/12/2017.
 */

public class CustomOnItemSelectedListener  implements AdapterView.OnItemSelectedListener
{
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
    {
          parent.getItemAtPosition(pos).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
}
