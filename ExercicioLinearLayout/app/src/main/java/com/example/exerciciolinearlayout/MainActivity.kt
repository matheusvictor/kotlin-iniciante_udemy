package com.example.exerciciolinearlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkCor.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.checkCor) {
            mudarCor()
        }
    }

    private fun mudarCor() {
        checkCor.setOnClickListener {
            if (checkCor.isChecked) {
                corImagem.setColorFilter(resources.getColor(R.color.purple_700))
            } else {
                corImagem.setColorFilter(resources.getColor(R.color.black))
            }
        }
    }


}