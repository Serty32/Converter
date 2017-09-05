package com.example.sinelnikovserhii.converter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by sinelnikovserhii on 27.08.17.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    private List<Currency> mCurrecnyList;

    public MyRecyclerViewAdapter(List<Currency> currencyList) {
        this.mCurrecnyList = currencyList;
    }

    @Override
    public MyRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclerview_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyRecyclerViewAdapter.MyViewHolder holder, int position) {
        Currency currency= mCurrecnyList.get(position);

        holder.mCurrency.setText(currency.getCurrency());
    }

    @Override
    public int getItemCount() {
        return mCurrecnyList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mCurrency;

        public MyViewHolder(View itemView) {
            super(itemView);
            mCurrency = (TextView) itemView.findViewById(R.id.text_curency);
        }
    }
}