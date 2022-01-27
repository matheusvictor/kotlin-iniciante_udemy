package com.example.myguests.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myguests.R
import com.example.myguests.constants.GuestConstants
import com.example.myguests.viewmodel.GuestFormViewModel
import kotlinx.android.synthetic.main.activity_guest_form.*

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    // ref from viewModel
    private lateinit var _viewModel: GuestFormViewModel
    private var _guestID: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)

        _viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        setListeners()
        observe()
        loadData()

        confirmed_radio_button.isChecked = true
    }

    override fun onClick(v: View) {
        val id = v.id

        if (id == R.id.button_save_guest) {

            val guestName = findViewById<EditText>(R.id.edit_guest_name).text.toString()
            val presence = findViewById<RadioButton>(R.id.confirmed_radio_button).isChecked

            _viewModel.save(_guestID, guestName, presence)

        }
    }

    private fun setListeners() {
        val buttonSave = findViewById<Button>(R.id.button_save_guest)
        buttonSave.setOnClickListener(this)
    }

    private fun observe() {
        _viewModel.saveGuest.observe(this, Observer {
            if (it) {
                Toast.makeText(applicationContext, "Sucess", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
            }
            finish()
        })

        _viewModel.guest.observe(this, Observer {
            edit_guest_name.setText(it.name)
            if (it.presence) {
                confirmed_radio_button.isChecked = true
            } else {
                denied_radio_button.isChecked = true
            }
        })
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            _guestID = bundle.getInt(GuestConstants.GUESTID)
            _viewModel.load(_guestID)
        }
    }

}
