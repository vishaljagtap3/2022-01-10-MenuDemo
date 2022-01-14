package in.bitcode.demo2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int MENU_ITEM_HELP = 1, MENU_ITEM_INFO = 2, MENU_ITEM_SETTINGS = 3;
    public static final int MENU_ITEM_PHONE_SETTINGS = 11, MENU_ITEM_SYSTEM_SETTINGS = 12;

    private CheckBox chkSettings;
    private TextView txtInfo;
    private EditText edtInfo;

    public static final int CM_PASTE = 1, CM_APPEND = 2, CM_COPY = 3, CM_CUT = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.user_profile);

        //LayoutInflater layoutInflater = getLayoutInflater();
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.user_profile, null);
        setContentView(view);

        txtInfo = view.findViewById(R.id.txtInfo);
        edtInfo = view.findViewById(R.id.edtInfo);

        chkSettings = findViewById(R.id.chkSettings);
        chkSettings.setOnCheckedChangeListener(new MyOnCheckChangedListener());

        //Context Menu

        registerForContextMenu(txtInfo);
        registerForContextMenu(edtInfo);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);

        mt("onCreateContextMenu");

        if(view == txtInfo) {
            menu.add(0, CM_PASTE, 0, "Paste");
            menu.add(0, CM_APPEND, 0, "Append");
        }
        if(view == edtInfo) {
            menu.add(0, CM_COPY, 0, "Copy");
            menu.add(0, CM_CUT, 0, "Cut");
        }
    }

    String str;

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case CM_PASTE:
                txtInfo.setText(str);
                break;

            case CM_APPEND:
                txtInfo.append(str);
                break;

            case CM_COPY:
                str = edtInfo.getText().toString();
                break;

            case CM_CUT:
                str = edtInfo.getText().toString();
                edtInfo.setText("");
                break;
        }

        return super.onContextItemSelected(item);
    }

    class MyOnCheckChangedListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            mt("Check Status: " + isChecked);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        mt("onCreateOptionsMenu");

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        /*MenuItem menuItem = menu.add(1, MENU_ITEM_HELP, 0, "Help");
        menuItem.setAlphabeticShortcut('h');
        menuItem.setNumericShortcut('9');
        menuItem.setIcon(R.mipmap.ic_launcher);
        menuItem.setCheckable(true);
        menuItem.setChecked(true);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        menu.add(1, MENU_ITEM_INFO, 0, "Info");

        SubMenu subMenu = menu.addSubMenu(1, MENU_ITEM_SETTINGS, 0, "Settings");
        menuItem = subMenu.add(2, MENU_ITEM_PHONE_SETTINGS, 0,  "Phone Settings");
        menuItem = subMenu.add(2, MENU_ITEM_SYSTEM_SETTINGS, 0,  "Sys Settings");

        menu.setGroupVisible(1, true);*/

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        mt("onPrepareOptionsMenu");

        //menu.findItem(MENU_ITEM_SETTINGS)
        menu.findItem(R.id.menuItemSettings)
                .setEnabled(
                        chkSettings.isChecked()
                );

        /*MenuItem menuItemSettings = menu.findItem(MENU_ITEM_SETTINGS);
        menuItemSettings.setEnabled(chkSettings.isChecked());*/

        /*if(chkSettings.isChecked()) {
            menuItemSettings.setEnabled(true);
        }
        else {
            menuItemSettings.setEnabled(false);
        }*/

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            //case MENU_ITEM_HELP:
            case R.id.menuItemHelp:
                mt("Help");
                break;

            //case MENU_ITEM_INFO:
            case R.id.menuItemInfo:
                mt("Info");
                break;

            //case MENU_ITEM_SETTINGS:
            case R.id.menuItemSettings:
                mt("Settings");
                break;

            //case MENU_ITEM_PHONE_SETTINGS:
            case R.id.menuItemPhone:
                mt("Phone Settings");
                break;

            //case MENU_ITEM_SYSTEM_SETTINGS:
            case R.id.menuItemSys:
                mt("System Settings");
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void mt(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}