package com.example.tema9;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText etName, etAge;
    CheckBox cbStatus;
    ListView mainListView;
    ArrayAdapter studentArrayAdapter;
    TextView rezCautat;
    DataBaseHelper dataBaseHelper;
    String name, age, status;
    int iName, iAge, iStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        cbStatus = findViewById(R.id.cbStatus);
        mainListView = findViewById(R.id.mainListView);

        //displaying the DB content inside the listview as soon as the app starts
        dataBaseHelper = new DataBaseHelper(MainActivity.this);
        //DisplayStudentsInListView(dataBaseHelper);
        studentArrayAdapter = new ArrayAdapter<StudentModel>(MainActivity.this, android.R.layout.simple_list_item_1,dataBaseHelper.getEveryone());
        mainListView.setAdapter(studentArrayAdapter);

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StudentModel clickedStudent = (StudentModel) parent.getItemAtPosition(position);
                dataBaseHelper.deleteOne(clickedStudent);

                studentArrayAdapter = new ArrayAdapter<StudentModel>(MainActivity.this, android.R.layout.simple_list_item_1,dataBaseHelper.getEveryone());
                mainListView.setAdapter(studentArrayAdapter);

                Toast.makeText(getApplicationContext(), "Student deleted: "+clickedStudent.toString(),Toast.LENGTH_LONG).show();
            }
        });

    }//onCreate
    public void onClickAdd(View v) {

        StudentModel studentModel = null;

        try{
            studentModel = new StudentModel(-1, etName.getText().toString(),Integer.parseInt(etAge.getText().toString()), cbStatus.isChecked());
        }
        catch(Exception exception){
            studentModel = new StudentModel(-1,"Error",0,false);
            Toast.makeText(getApplicationContext(), exception.toString(),Toast.LENGTH_LONG).show();
        }

        DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
        boolean actionSuccess = dataBaseHelper.addOne(studentModel);

        //update the listview content
        dataBaseHelper = new DataBaseHelper(MainActivity.this);
        //DisplayStudentsInListView(dataBaseHelper);
        studentArrayAdapter = new ArrayAdapter<StudentModel>(MainActivity.this, android.R.layout.simple_list_item_1,dataBaseHelper.getEveryone());
        mainListView.setAdapter(studentArrayAdapter);

    }

    public void onClickViw(View v) {
        dataBaseHelper = new DataBaseHelper(MainActivity.this);
        List<StudentModel> everyone = dataBaseHelper.getEveryone();
        //DisplayStudentsInListView(dataBaseHelper);
        studentArrayAdapter = new ArrayAdapter<StudentModel>(MainActivity.this, android.R.layout.simple_list_item_1, everyone);
        mainListView.setAdapter(studentArrayAdapter);
    }

    public void onClickSearch(View v) {
        rezCautat = findViewById(R.id.textView);
        String deCautat = etName.getText().toString();
        // cautam cuvantul.
        Cursor cursor = dataBaseHelper.search(deCautat);
        // daca nu este null
        if (cursor.getCount() > 0) {
            // mutam cursorul in capul listei
            cursor.moveToFirst();
            // cautam cel putin 1 data
            do {
                // preia indexul si afiseaza daele din acel index
                iName = cursor.getColumnIndex(DataBaseHelper.COLUMN_STUDENT_NAME);
                iAge = cursor.getColumnIndex(DataBaseHelper.COLUMN_STUDENT_AGE);
                iStatus = cursor.getColumnIndex(DataBaseHelper.COLUMN_ACTIVE_STATUS);
//preluam stringul
                name = cursor.getString(iName);
                age = cursor.getString(iAge);
                //verificam statusul, 1/0
                if(iStatus==1){
                    status = "is active";
                }else if(iStatus==0){
                    status = "is inactive";
                }
                // punem rezultatul in textview
                rezCautat.setText(name+", "+age+", "+status+ "\n");
            } while (cursor.moveToNext());
            cursor.close();
        }else{
            Toast.makeText(getApplicationContext(),"Nu-i prin lista asta", Toast.LENGTH_SHORT).show();
        }
    }
}