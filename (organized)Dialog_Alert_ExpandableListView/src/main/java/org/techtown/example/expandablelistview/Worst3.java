package org.techtown.example.expandablelistview;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Calendar;

public class Worst3 extends Activity {
    EditText dateTXTWorst3;
    ImageView calWorst3;
    private int mDateW3, mMonthW3, mYearW3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.worst_3);

        dateTXTWorst3 = findViewById(R.id.dateWorst3);
        calWorst3 = findViewById(R.id.datepickerWorst3);

        calWorst3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar Cal = Calendar.getInstance();
                mDateW3 = Cal.get(Calendar.DATE);
                mMonthW3 = Cal.get(Calendar.MONTH);
                mYearW3 = Cal.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Worst3.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOFMonth) {
                        dateTXTWorst3.setText(year + "-" + (monthOfYear +1) + "-" + dayOFMonth);

                    }
                }, mYearW3, mMonthW3, mDateW3);

                datePickerDialog.show();
            }
        });
    }
}