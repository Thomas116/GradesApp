package com.example.projekt1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projekt1.model.Dane;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Max;
import com.mobsandgeeks.saripaar.annotation.Min;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.text.DecimalFormat;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Validator.ValidationListener {

    @NotEmpty
    private EditText editText_imie;

    @NotEmpty
    private EditText editText_nazwisko;

    private TextView sredniaOcen;

    @Min(5)
    @Max(15)
    private EditText editText_liczbaOcen;

    private Button oceny;

    private Validator validator;

    private DecimalFormat df2 = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_imie = findViewById(R.id.editText_imie);
        editText_nazwisko = findViewById(R.id.editText_nazwisko);
        editText_liczbaOcen = findViewById(R.id.editText_liczbaOcen);
        sredniaOcen = findViewById(R.id.textView_sredniaOcen);
        oceny = findViewById(R.id.oceny);

        Bundle extras = getIntent().getExtras();

        try {
            Dane dane = (Dane) extras.getSerializable("uzupelnione_dane");
            editText_imie.setText(dane.getImie());
            editText_imie.setEnabled(false);
            editText_nazwisko.setText(dane.getNazwisko());
            editText_nazwisko.setEnabled(false);
            editText_liczbaOcen.setText(String.valueOf(dane.getLiczbaOcen()));
            editText_liczbaOcen.setEnabled(false);
            sredniaOcen.setText(String.format(getResources().getString(R.string.average), df2.format(dane.getSredniaOcen())));
            sredniaOcen.setVisibility(View.VISIBLE);
            boolean zaliczenie = false;
            StringBuilder stringBuilder = new StringBuilder();
            if(dane.getSredniaOcen() >= 3){
                zaliczenie = true;
                stringBuilder.append("Super :)");
            } else {
                stringBuilder.append("Tym razem mi nie poszło");
            }
            oceny.setText(stringBuilder.toString());
            final boolean finalZaliczenie = zaliczenie;
            oceny.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    StringBuilder stringBuilder = new StringBuilder();
                    if(finalZaliczenie){
                        stringBuilder.append("Gratulacje! Otrzymujesz zaliczenie!");
                    } else {
                        stringBuilder.append("Wysyłam podanie o zaliczenie warunkowe");
                    }
                    Toast.makeText(view.getContext(), stringBuilder.toString(), Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        } catch (NullPointerException e) {
            oceny.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    validator.validate();
                }
            });
        }
        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    public void NextActivity() {//przejście do kolejnej aktywności
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("dane", new Dane(editText_imie.getText().toString(), editText_nazwisko.getText().toString(), Integer.parseInt(editText_liczbaOcen.getText().toString())));
        startActivity(intent);
        finish();
    }

    @Override
    public void onValidationSucceeded() { //jeśli walidacja zakończy się sukcesem
        Toast.makeText(this, "Dane poprawne", Toast.LENGTH_SHORT).show();
        NextActivity();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) { //jeśli walidacja zakończy się porażką
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        }
    }
}