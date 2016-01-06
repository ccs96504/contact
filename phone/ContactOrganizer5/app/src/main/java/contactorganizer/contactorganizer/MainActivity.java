package contactorganizer.contactorganizer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.DatabaseErrorHandler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    EditText nameTxt,phoneTxt,addressTxt,EmailTxt;
    List<Contact> Contacts =new ArrayList<Contact>();
    ListView contactListView;
    DataBaseHander dbHandler;


    int lockClickedItemIndex;
    ArrayAdapter<Contact> contactAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        nameTxt = (EditText) findViewById(R.id.editText);
        phoneTxt = (EditText) findViewById(R.id.editText2);
        addressTxt = (EditText) findViewById(R.id.editText3);
        EmailTxt = (EditText) findViewById(R.id.editText4);
        contactListView=(ListView)findViewById(R.id.listView);
        dbHandler=new DataBaseHander(getApplication());
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        contactListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("確定要刪除")
                        .setMessage("刪除的是" + parent.getItemAtPosition(position).toString())
                        .setNegativeButton("確定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                contactAdapter.clear();
                                contactListView.setAdapter(null);

                            }
                        })
                        .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
//                items2.clear();
                return true;
            }
        });


        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("creator");
        tabSpec.setContent(R.id.tab1);
        tabSpec.setIndicator("creator");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("list");
        tabSpec.setContent(R.id.tab2);
        tabSpec.setIndicator("list");
        tabHost.addTab(tabSpec);


        final Button addBtn = (Button) findViewById(R.id.button);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Contacts.add(new Contact(0,nameTxt.getText().toString(), phoneTxt.getText().toString(), EmailTxt.getText().toString(), addressTxt.getText().toString()));
                populateList();
                Toast.makeText(getApplicationContext(),nameTxt.getText().toString()+"has been add your Contact",Toast.LENGTH_SHORT).show();
            }
        });


                nameTxt.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        addBtn.setEnabled(!nameTxt.getText().toString().trim().isEmpty());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
    }


    private void populateList(){
        contactAdapter= new ContactListddaptes();
        contactListView.setAdapter(contactAdapter);
    }

    public void addcontact(int id,String name,String phone,String email, String address){
        Contacts.add(new Contact(id,name, phone, email, address));
    }
    private class ContactListddaptes extends ArrayAdapter<Contact> {
        public ContactListddaptes() {
            super(MainActivity.this, R.layout.listview_item, Contacts);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null)
                view = getLayoutInflater().inflate(R.layout.listview_item, parent, false);

            Contact currentContect = Contacts.get(position);

            TextView name = (TextView) view.findViewById(R.id.textView2);
            name.setText(currentContect.getName());
            TextView phone = (TextView) view.findViewById(R.id.textView3);
            phone.setText(currentContect.getphone());
            TextView address = (TextView) view.findViewById(R.id.textView4);
            address.setText(currentContect.getAddress());
            TextView email = (TextView) view.findViewById(R.id.textView5);
            email.setText(currentContect.getEmail());

            return view;


        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
