package com.example.sinelnikovserhii.converter;

import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sinelnikovserhii.converter.R.id.spinner_from;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.edit_have) EditText edit_have;
    @BindView(R.id.edit_want) EditText edit_want;
    @BindView(R.id.img_btn_swop) ImageButton img_btn_swop;
    @BindView(R.id.spinner_from) Spinner spinnerFrom;
    @BindView(R.id.spinner_to) Spinner spinnerTo;
    @BindView(R.id.start) Button start;

    CurrencyApi currencyApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        currencyApi = CurrencyApi.retrofit.create(CurrencyApi.class);

        img_btn_swop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swapSpinner();
            }
        });

        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                String[] choose = getResources().getStringArray(R.array.currencyList);
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Ваш выбор: " + choose[selectedItemPosition], Toast.LENGTH_SHORT);
                toast.show();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        edit_have.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getAndInflateData();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void onClick(View view){
        getAndInflateData();
    }

    public void getAndInflateData() {
        currencyApi.getData(spinnerFrom.getSelectedItem().toString(), spinnerTo.getSelectedItem().toString()).enqueue((new Callback<FixerResponse>() {
            @Override
            public void onResponse(Call<FixerResponse> call, Response<FixerResponse> response) {
                if(response.isSuccessful()) {
                    if(spinnerTo!=spinnerFrom){
                     if(!edit_have.getText().toString().isEmpty()) {
                        float rate = response.body().rates.get(spinnerTo.getSelectedItem().toString());
                        edit_want.setText(String.valueOf(Float.parseFloat(edit_have.getText().toString()) * rate));
                         //    Toast.makeText(MainActivity.this, String.valueOf(rate), Toast.LENGTH_LONG).show();
                     }
                    }
                }
            }

            @Override
            public void onFailure(Call<FixerResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this ,"Something bad", Toast.LENGTH_LONG).show();

            }
        }));
    }

    void swapSpinner() {
        int i = spinnerFrom.getSelectedItemPosition();
        spinnerFrom.setSelection(spinnerTo.getSelectedItemPosition(), true);
        spinnerTo.setSelection(i, true);
    }
}
