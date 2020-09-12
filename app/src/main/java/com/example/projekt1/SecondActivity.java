package com.example.projekt1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projekt1.adapter.RecyclerViewAdapter;
import com.example.projekt1.model.Dane;
import com.example.projekt1.model.Ocena;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements RecyclerViewAdapter.ListItemClickListener {

    private RecyclerView listaOcen;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Ocena> oceny = new ArrayList<>();
    private Dane dane;
    private final int max = 5;
    private final int min = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle extras = getIntent().getExtras();
        dane = (Dane) extras.getSerializable("dane");//przekazanie danych danych z pierwszej aktywności

        listaOcen = findViewById(R.id.recyclerView);
        listaOcen.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new RecyclerViewAdapter(this, oceny);
        recyclerViewAdapter.setClickListener(this);
        addGrades(dane.getLiczbaOcen());
    }

    private void addGrades(int amount) {
        for (int i = 1; i <= amount; i++) {
            int randomInt = (int) (Math.random() * ((max - min) + 1)) + min;
            oceny.add(new Ocena(String.format("ocena %s", i), randomInt));
        }
        listaOcen.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void onListItemClick(int itemIndex, int grade) {
        oceny.get(itemIndex).setWartosc(grade);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    public void doneButtonAction(View view) {//przycisk przejścia do aktywności pierwszej
        dane.setSredniaOcen(recyclerViewAdapter.getAverage());
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("uzupelnione_dane", dane);
        startActivity(intent);
        finish();
    }
}
