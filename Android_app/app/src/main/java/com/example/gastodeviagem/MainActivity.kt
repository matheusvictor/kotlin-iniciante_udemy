package com.example.gastodeviagem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        botaoCalcular.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.botaoCalcular) {
            calcularGasto()
        }
    }

    private fun calcularGasto() {
        val preco = inputPreco.text.toString().toFloat()
        val distancia = inputDistancia.text.toString().toFloat()
        val autonomia = outputAutonomia.text.toString().toFloat()

        val valorTotalGasto = (distancia * preco) / autonomia
        textoValorTotal.text = "R$ ${"%.2f".format(valorTotalGasto)}"
    }
}