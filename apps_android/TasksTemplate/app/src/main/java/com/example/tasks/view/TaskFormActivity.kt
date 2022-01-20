package com.example.tasks.view

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import androidx.lifecycle.ViewModelProvider
import com.example.tasks.R
import com.example.tasks.viewmodel.RegisterViewModel
import com.example.tasks.viewmodel.TaskFormViewModel
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.button_save
import kotlinx.android.synthetic.main.activity_task_form.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class TaskFormActivity : AppCompatActivity(),
    View.OnClickListener,
    DatePickerDialog.OnDateSetListener {

    private lateinit var mViewModel: TaskFormViewModel

    private val mDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_form)

        mViewModel = ViewModelProvider(this).get(TaskFormViewModel::class.java)

        // Inicializa eventos
        listeners()
        observe()
    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.button_save) {
            //TODO
        } else if (id == R.id.button_date) {
            showDatePicker()
        }
    }

    private fun observe() {
    }

    private fun listeners() {
        button_save.setOnClickListener(this)
        button_date.setOnClickListener(this)
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this, this, year, month, day).show()
    }

    // listener chamado quando o usuário selecionou a data desejada
    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)

        //formatação de data
        val dateInTextForm = mDateFormat.format(calendar.time)
        button_date.text = dateInTextForm

    }

}
