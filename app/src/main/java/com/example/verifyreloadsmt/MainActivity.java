package com.example.verifyreloadsmt;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.verifyreloadsmt.api.ApiService;
import com.example.verifyreloadsmt.model.Get_TotalByMachine_Response;
import com.example.verifyreloadsmt.model.RecyclerViewAdapter;
import com.example.verifyreloadsmt.model.RecyclerViewAdapter_Get_TotalByMachine;
import com.example.verifyreloadsmt.model.tblReplaceVerify;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private RecyclerViewAdapter_Get_TotalByMachine adapter_Get_TotalByMachine;
    private List<tblReplaceVerify> dataList;
    private List<Get_TotalByMachine_Response> dataList_Get_TotalByMachine_Response;
    TextView txtTotalByMachine, txtResult;
    EditText edtWO, edtMachine, edtSlot, edtUPN;
    Button btnReset, btnExit, btnCheck;
    TextToSpeech t1;
    private boolean isTtsReady = false;  // Flag to check if TTS is ready

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        txtResult = findViewById(R.id.txtResult);
        txtTotalByMachine = findViewById(R.id.txtTotalByMachine);

        edtWO = findViewById(R.id.edtWO);
        edtMachine = findViewById(R.id.edtMachine);
        edtSlot = findViewById(R.id.edtSlot);
        edtUPN = findViewById(R.id.edtUPN);

        btnReset = findViewById(R.id.btnReset);
        btnExit = findViewById(R.id.btnExit);
        btnCheck = findViewById(R.id.btnCheck);

        recyclerView = findViewById(R.id.recyclerView);

        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                if (status == TextToSpeech.SUCCESS) {

                    int result = t1.setLanguage(Locale.US);

                    t1.setSpeechRate(1.0f);


                    // Check if the language is supported
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        isTtsReady = true;  // Mark TTS as ready
//                        speakResult();  // Automatically trigger speech after initialization
                        Log.d("TTS", "TextToSpeech is ready");
//                        Toast.makeText(MainActivity.this, "TextToSpeech is ready", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("TTS", "Initialization failed: " + status);
//                    Toast.makeText(MainActivity.this, "Initialization failed: " + status, Toast.LENGTH_SHORT).show();

                }
            }


        });

        edtWO.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (keyEvent != null && (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) || i == EditorInfo.IME_ACTION_DONE) {
                    String wo = edtWO.getText().toString();
                    ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this, "Please Wait", "Checking WO...", true);
                    ApiService.apiService.getReload(wo).enqueue(new Callback<List<tblReplaceVerify>>() {
                        @Override
                        public void onResponse(Call<List<tblReplaceVerify>> call, Response<List<tblReplaceVerify>> response) {
                            progressDialog.dismiss();
                            if (response.isSuccessful() && response.body() != null) {
                                dataList = new ArrayList<tblReplaceVerify>();
                                dataList = response.body();
                                if (!dataList.isEmpty()) {
                                    adapter = new RecyclerViewAdapter(dataList);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));// Vertical scrolling
                                    recyclerView.setAdapter(adapter);
                                } else {
                                    Toast.makeText(MainActivity.this, "WO Not Found", Toast.LENGTH_SHORT).show();
                                    edtWO.requestFocus();
                                }

                            } else {
                                // Extract detailed error message for debugging
                                String errorMessage = "No error message";
                                try {
                                    if (response.errorBody() != null) {
                                        errorMessage = response.errorBody().string(); // Read the error message from response
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                Log.e("API_ERROR", "Response failed. Status code: " + response.code() + ", Error: " + errorMessage);
                                Toast.makeText(MainActivity.this, "Error: Invalid response. Status code: " + response.code(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<tblReplaceVerify>> call, Throwable t) {
                            progressDialog.dismiss(); // Dismiss the dialog in case of failure
                            // Log the error message for debugging
                            Log.e("API_ERROR", "API Call Failed: ", t);
                            Toast.makeText(MainActivity.this, "Call API Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    });
                }
                return false;
            }
        });

        edtMachine.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (keyEvent != null && (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) || i == EditorInfo.IME_ACTION_DONE) {
                   btnCheck.performClick();

                }
                return false;
            }
        });
        edtUPN.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return false;
            }
        });
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    String wo = edtWO.getText().toString();
                    String machine = edtMachine.getText().toString();
                    ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this, "Please Wait", "Checking WO, Machine_ID...", true);
                    ApiService.apiService.Get_TotalByMachine(wo, machine).enqueue(new Callback<List<Get_TotalByMachine_Response>>() {
                        @Override
                        public void onResponse(Call<List<Get_TotalByMachine_Response>> call, Response<List<Get_TotalByMachine_Response>> response) {
                            progressDialog.dismiss();
                            if (response.isSuccessful() && response.body() != null) {
                                dataList_Get_TotalByMachine_Response = new ArrayList<Get_TotalByMachine_Response>();
                                dataList_Get_TotalByMachine_Response = response.body();
                                if (!dataList_Get_TotalByMachine_Response.isEmpty()) {
                                    adapter_Get_TotalByMachine = new RecyclerViewAdapter_Get_TotalByMachine(dataList_Get_TotalByMachine_Response);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));// Vertical scrolling
                                    recyclerView.setAdapter(adapter_Get_TotalByMachine);
                                    txtTotalByMachine.setText(dataList_Get_TotalByMachine_Response.get(0).getTotalByMachine());
                                } else {
                                    Toast.makeText(MainActivity.this, "Machine Not Found", Toast.LENGTH_SHORT).show();
                                    Reload();
                                }

                            } else {
                                // Extract detailed error message for debugging
                                String errorMessage = "No error message";
                                try {
                                    if (response.errorBody() != null) {
                                        errorMessage = response.errorBody().string(); // Read the error message from response
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                Reload();
                                Log.e("API_ERROR", "Response failed. Status code: " + response.code() + ", Error: " + errorMessage);
                                Toast.makeText(MainActivity.this, "Error: Invalid response. Status code: " + response.code(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Get_TotalByMachine_Response>> call, Throwable t) {
                            progressDialog.dismiss(); // Dismiss the dialog in case of failure
                            // Log the error message for debugging
                            Reload();
                            Log.e("API_ERROR", "API Call Failed: ", t);
                            Toast.makeText(MainActivity.this, "Call API Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    });
                }

        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtWO.setText("");
                edtMachine.setText("");
                edtSlot.setText("");
                edtUPN.setText("");
                Reload();

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

    private void Reload() {
        txtTotalByMachine.setText("0");
        dataList.clear();
        adapter.notifyDataSetChanged();

        dataList_Get_TotalByMachine_Response.clear();
        adapter_Get_TotalByMachine.notifyDataSetChanged();
    }

    private void speakResult() {
        String text = txtResult.getText().toString();
        if (isTtsReady) {
            if (!text.isEmpty()) {
                // Check if the text is "NG" and modify it
                if (text.equalsIgnoreCase("NG")) {
                    text = "N G";  // Separate the letters with a space

                } else if (text.equalsIgnoreCase("OK")) {
                    text = "O K";  // Separate the letters with a space

                }
                t1.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
            } else {
                Log.e("TTS", "Text is empty, cannot speak");
//                Toast.makeText(MainActivity.this, "Text is empty, cannot speak", Toast.LENGTH_LONG).show();
            }

        } else {
//            Log.e("TTS", "TextToSpeech not ready or text is empty");
            Toast.makeText(MainActivity.this, "TextToSpeech not ready or text is empty", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        // Shutdown TextToSpeech to release resources
        if (t1 != null && isTtsReady) {
//        if (t1 != null ) {
            t1.stop();
            t1.shutdown();
        }
        super.onDestroy();
    }
}