package com.example.mobile_lab;

import android.os.Bundle;
import java.util.ArrayList;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    public ListView listEmployee ;
    private EditText Gross, Name;
    protected Button btnCal;
    ArrayList<String> arrayEmployee;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listEmployee = (ListView) findViewById(R.id.listEmployee);
        Name = findViewById(R.id.Name);
        Gross = findViewById(R.id.Gross);
        btnCal = findViewById(R.id.btnCal);
        arrayEmployee = new ArrayList<>();

        ArrayAdapter adapter = new ArrayAdapter(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                arrayEmployee
        );

        listEmployee.setAdapter(adapter);

        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = Name.getText().toString();
                double gross = Double.parseDouble(Gross.getText().toString());
                Employee netSalary = new Employee(name, gross);
                String net = netSalary.calculationNetSalary();
                arrayEmployee.add(netSalary.getName() + " - Net Salary: " + net);
                adapter.notifyDataSetChanged();
            }
        });
//        myBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String text = Name.getText().toString();
//                double number = Double.parseDouble(Gross.getText().toString());
//                PersonalSalary netSalary = new PersonalSalary(text, number);
//                netSalary.calculationNetSalary();
//            }
//        });
    }
}