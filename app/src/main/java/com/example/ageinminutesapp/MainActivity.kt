package com.example.ageinminutesapp

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_DatePicker.setOnClickListener {view ->
            clickDatePicker(view)
            }
    }

    fun clickDatePicker(view: View){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener {
                    view, selectedYear, selectedMonth, selectedDayOfMonth ->
                    Toast.makeText(this, "The chosen year is $selectedYear, month" +
                            " is $selectedMonth and the day is $selectedDayOfMonth", Toast.LENGTH_LONG).show()

                    val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                    txt_pickedDate.setText(selectedDate)

                    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.UK)
                    val theDate = sdf.parse(selectedDate)

                    val selectedDateInMinutes = theDate!!.time / 60_000
                    val selectedDateInDays = selectedDateInMinutes / 1440
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                    val currentDateInMinutes = currentDate!!.time / 60_000
                    val currentDateInDays = currentDateInMinutes / 1440
                    val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                    val differenceInDays = currentDateInDays - selectedDateInDays

                    txt_calculatedAgeInMins.setText("${differenceInMinutes.toString()} ${getResources().getString(R.string.txt_minutesText)}")
                    txt_calculatedAgeInDays.setText("${differenceInDays.toString()} ${getResources().getString(R.string.txt_daysText)}")


                }
                , year, month, day)
        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()

    }
}