package com.example.tasks.view

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.tasks.R
import com.example.tasks.viewmodel.TaskFormViewModel
import kotlinx.android.synthetic.main.activity_register.button_save
import kotlinx.android.synthetic.main.activity_task_form.*
import java.text.SimpleDateFormat
import androidx.lifecycle.Observer
import com.example.tasks.service.model.TaskModel
import java.util.*

class TaskFormActivity : AppCompatActivity(),
    View.OnClickListener,
    DatePickerDialog.OnDateSetListener {

    private lateinit var mViewModel: TaskFormViewModel

    private val mDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

    //instancia uma lista de inteiros vazia para salvar os IDs das prioridades
    private val mListPriorityId: MutableList<Int> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_form)

        mViewModel = ViewModelProvider(this).get(TaskFormViewModel::class.java)

        // Inicializa eventos
        listeners()
        observe()

        mViewModel.listPriorities()

    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.button_save) {
            handleSaveTask()
        } else if (id == R.id.button_date) {
            showDatePicker()
        }
    }

    private fun handleSaveTask() {
        val task: TaskModel = TaskModel().apply {
            this.description = edit_description.text.toString()
            this.complete = check_complete.isChecked
            this.dueDate = button_date.text.toString()
            /*
            O ID da prioridade da Task a ser salva será igual ao valor do item selecionado no spinner,
            referenciado dentro de mListPriorityId
             */
            this.priorityId = mListPriorityId[spinner_priority.selectedItemPosition]
        }

        mViewModel.saveTask(task)
    }

    private fun observe() {

        mViewModel.priorities.observe(this, Observer {
            // atribuição de uma lista de String para o Spinner
            val list: MutableList<String> = arrayListOf()

            for (item in it) {
                list.add(item.description)
                //adiciona o ID do item atual na lista mListPriorityId, instanciada como vazia anteriormente
                //então, a lista passará a ser composta apenas pelos IDs das prioridades
                mListPriorityId.add(item.id)
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list)
            spinner_priority.adapter = adapter
        })

        mViewModel.validation.observe(this, Observer {
            if (it.isSuccessed()) {
                Toast.makeText(this, "Sucesso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, it.getErrorMessage(), Toast.LENGTH_SHORT).show()
            }
        })
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
