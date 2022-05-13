package com.example.sqliteapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    private DatabaseHelper myDb;
    private EditText editName, editSurname, editMarks, editTextId;
    private Button btnAddData;
    private Button btnViewAll;
    private Button btnUpdate;
    private Button btnDelete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.myDb = new DatabaseHelper(this);

        editName = findViewById(R.id.editTextTextName);
        editSurname = findViewById(R.id.editTextTextSurname);
        editMarks = findViewById(R.id.editTextTextMarks);
        editTextId = findViewById(R.id.editTextTextID);
        btnAddData= findViewById(R.id.button_add);
        btnViewAll = findViewById(R.id.button_view_all);
        btnUpdate = findViewById(R.id.button_update);
        btnDelete = findViewById(R.id.button_delete);
        addData();
        viewAll();
        updateData();
        deleteData();
    }

    public void deleteData()
    {
        btnDelete.setOnClickListener(
                view ->
                {
                    Integer deletedRows = myDb.deleteData(editTextId.getText().toString());
                    if(deletedRows > 0)
                    {
                        Toast.makeText(MainActivity.this,
                                "Data Deleted", Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(MainActivity.this,
                                "Data not Deleted", Toast.LENGTH_LONG).show();
                }
        );
    }

    public void updateData()
    {
        btnUpdate.setOnClickListener(
                view ->
                {
                    boolean isUpdate = myDb.updateData(editTextId.getText().toString(),
                            editName.getText().toString(), editSurname.getText().toString(),
                            editMarks.getText().toString());
                    if(isUpdate)
                    {
                        Toast.makeText(MainActivity.this,
                                "Data Updated", Toast.LENGTH_LONG).show();
                    }
                   else
                        Toast.makeText(MainActivity.this,
                                "Data not Updated", Toast.LENGTH_LONG).show();
                }
        );
    }

    public void viewAll()
    {
        btnViewAll.setOnClickListener(
                view ->
                {
                    Cursor result = myDb.getAllData();
                    if (result.getCount() ==0)
                    {
                        showMessage("Error", "No data found");
                        return;
                    }

                    StringBuilder buffer = new StringBuilder();
                    while (result.moveToNext())
                    {
                        buffer.append("Id :" + result.getString(0) + "\n");
                        buffer.append("Name :" + result.getString(1) + "\n");
                        buffer.append("Surname :" + result.getString(2) + "\n");
                        buffer.append("Marks :" + result.getString(3) + "\n\n");
                    }
                    showMessage("Data", buffer.toString());
                }
        );
    }

    public void showMessage(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
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

    public DatabaseHelper getMyDb()
    {
        return myDb;
    }

    public void setMyDb(DatabaseHelper myDb)
    {
        this.myDb = myDb;
    }

    public EditText getEditName()
    {
        return editName;
    }

    public void setEditName(EditText editName)
    {
        this.editName = editName;
    }

    public EditText getEditSurname()
    {
        return editSurname;
    }

    public void setEditSurname(EditText editSurname)
    {
        this.editSurname = editSurname;
    }

    public EditText getEditMarks()
    {
        return editMarks;
    }

    public void setEditMarks(EditText editMarks)
    {
        this.editMarks = editMarks;
    }

    public Button getBtnAddData()
    {
        return btnAddData;
    }

    public void setBtnAddData(Button btnAddData)
    {
        this.btnAddData = btnAddData;
    }

    public Button getBtnViewAll()
    {
        return btnViewAll;
    }

    public void setBtnViewAll(Button btnViewAll)
    {
        this.btnViewAll = btnViewAll;
    }

    public Button getBtnUpdate()
    {
        return btnUpdate;
    }

    public void setBtnUpdate(Button btnUpdate)
    {
        this.btnUpdate = btnUpdate;
    }

    public EditText getEditTextId()
    {
        return editTextId;
    }

    public void setEditTextId(EditText editTextId)
    {
        this.editTextId = editTextId;
    }

    public Button getBtnDelete()
    {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete)
    {
        this.btnDelete = btnDelete;
    }
}