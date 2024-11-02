package com.example.planetZe_gp5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder> {
    private List<String> answers;

    public AnswerAdapter(List<String> ans){
        this.answers = ans;
    }

    @NonNull
    @Override
    public AnswerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.activity_acfquestion, parent, false);
        return new AnswerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerViewHolder holder, int position){
        holder.answerTextView.setText(answers.get(position));
    }

    @Override
    public int getItemCount(){
        return answers.size();
    }

    static class AnswerViewHolder extends RecyclerView.ViewHolder{
        TextView answerTextView;

        public AnswerViewHolder(@NonNull View itemview){
            super(itemview);
            answerTextView = itemView.findViewById(R.id.answerTextView);
        }
    }
}
