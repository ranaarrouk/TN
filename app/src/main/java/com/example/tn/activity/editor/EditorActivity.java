package com.example.tn.activity.editor;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tn.R;
import com.thebluealliance.spectrum.SpectrumPalette;

public class EditorActivity extends AppCompatActivity implements EditorView{

    EditText et_title, et_note;
    ProgressDialog progressDialog;
    SpectrumPalette palette;

    EditorPresenter presenter;

    int color;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        presenter = new EditorPresenter(this);

        et_note = (EditText) findViewById(R.id.note);
        et_title = (EditText) findViewById(R.id.title);
        palette = (SpectrumPalette) findViewById(R.id.palette);

        palette.setOnColorSelectedListener(clr -> color = clr);

        palette.setSelectedColor(getResources().getColor(R.color.white));
        color = getResources().getColor(R.color.white);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                String title = et_title.getText().toString().trim();
                String note = et_note.getText().toString().trim();
                int color =  this.color;

                if(title.isEmpty()) {
                    et_title.setError("Please enter a title..");
                }
                else if(note.isEmpty())
                {
                    et_note.setError("Please enter a note..");
                }
                else
                   presenter.saveNote(title,note,color);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void onAddSuccess(String message) {
        Toast.makeText(EditorActivity.this, message, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onAddError(String message) {
        Toast.makeText(EditorActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
