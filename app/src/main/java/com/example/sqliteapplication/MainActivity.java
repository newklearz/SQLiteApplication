package com.example.sqliteapplication;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    private DatabaseHelper myDb;
    private EditText editName, editSurname, editMarks;
    private Button btnAddData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.myDb = new DatabaseHelper(this);

//        editName = (EditText) findViewById(R.id.);
//        editName = (EditText) findViewById(R.id.);
//        editName = (EditText) findViewById(R.id.);
//        editName = (Button) findViewById(R.id.);
        addData();
    }

    public void addData()
    {
        btnAddData.setOnClickListener(
                view -> {
                  boolean isInserted = myDb.insertData(editName.getText().toString(),
                            editSurname.getText().toString(),
                            editMarks.getText().toString());
                    if (isInserted)
                    {
                        Toast.makeText(MainActivity.this,
                                "Data Inserted", Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(MainActivity.this,
                                "Data not Inserted", Toast.LENGTH_LONG).show();
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        return super.onOptionsItemSelected(item);
    }
}