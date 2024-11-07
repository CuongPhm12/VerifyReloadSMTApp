package com.example.verifyreloadsmt;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.verifyreloadsmt.model.RecyclerViewAdapter;
import com.example.verifyreloadsmt.model.tblReplaceVerify;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private List<tblReplaceVerify> dataList;
    EditText edtWO, edtMachine, edtSlot, edtUPN;
    Button btnReset, btnExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edtWO = findViewById(R.id.edtWO);
        edtMachine = findViewById(R.id.edtMachine);
        edtSlot = findViewById(R.id.edtSlot);
        edtUPN = findViewById(R.id.edtUPN);

        btnReset = findViewById(R.id.btnReset);
        btnExit = findViewById(R.id.btnExit);

        recyclerView = findViewById(R.id.recyclerView);

        // Initialize data list
        dataList = new ArrayList<>();
        dataList.add(new tblReplaceVerify("WO123", "LINE1", "MACHINE_A", "SLOT1"));
        dataList.add(new tblReplaceVerify("WO456", "LINE2", "MACHINE_B", "SLOT2"));
        dataList.add(new tblReplaceVerify("WO789", "LINE3", "MACHINE_C", "SLOT3"));

        adapter = new RecyclerViewAdapter(dataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));// Vertical scrolling
        recyclerView.setAdapter(adapter);


        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtWO.setText("");
                edtMachine.setText("");
                edtSlot.setText("");
                edtUPN.setText("");

                dataList.clear();

                adapter.notifyDataSetChanged();

                edtWO.requestFocus();
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}