package com.example.projekt1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projekt1.model.Ocena;
import com.example.projekt1.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Ocena> oceny;
    private LayoutInflater mInflater;
    private ListItemClickListener myOnClickListener;

    public interface ListItemClickListener {
        void onListItemClick(int itemIndex, int grade);
    }

    public RecyclerViewAdapter(Context context, List<Ocena> data) {
        this.mInflater = LayoutInflater.from(context);
        this.oceny = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Ocena ocena = oceny.get(position);
        holder.textView.setText(ocena.getNazwa());
        holder.setIsRecyclable(false);
        switch (ocena.getWartosc()){
            case 2:
                holder.rb2.setChecked(true);
                break;
            case 3:
                holder.rb3.setChecked(true);
                break;
            case 4:
                holder.rb4.setChecked(true);
                break;
            case 5:
                holder.rb5.setChecked(true);
                break;
            default:
                holder.radioGroup.clearCheck();
        }
    }

    @Override
    public int getItemCount() {
        return oceny.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        RadioGroup radioGroup;
        RadioButton rb2, rb3, rb4, rb5;

        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.listaTextEtykieta);
            radioGroup = itemView.findViewById(R.id.grupaOceny);
            rb2 = itemView.findViewById(R.id.radioButton_2);
            rb3 = itemView.findViewById(R.id.radioButton_3);
            rb4 = itemView.findViewById(R.id.radioButton_4);
            rb5 = itemView.findViewById(R.id.radioButton_5);

            rb2.setOnClickListener(this);
            rb3.setOnClickListener(this);
            rb4.setOnClickListener(this);
            rb5.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedCardPosition = getAdapterPosition();

            if(rb2.isPressed()) myOnClickListener.onListItemClick(clickedCardPosition, 2);
            if(rb3.isPressed()) myOnClickListener.onListItemClick(clickedCardPosition,3);
            if(rb4.isPressed()) myOnClickListener.onListItemClick(clickedCardPosition,4);
            if(rb5.isPressed()) myOnClickListener.onListItemClick(clickedCardPosition,5);
        }
    }

    public double getAverage() {//obliczenie średniej
        double sumOfGrades = 0;
        for(int i=0; i< oceny.size(); i++){
            sumOfGrades = sumOfGrades + oceny.get(i).getWartosc();
        }
        return sumOfGrades/oceny.size();
    }

    public void setClickListener(ListItemClickListener listItemClickListener) {
        this.myOnClickListener = listItemClickListener;
    }
}