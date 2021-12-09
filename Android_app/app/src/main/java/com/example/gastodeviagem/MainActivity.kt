package com.example.gastodeviagem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        botaoCalcular.setOnClickListener(this)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.botaoCalcular) {
            calcularGasto()
        }
    }

    private fun calcularGasto() {
        if (validarCampos()) {

            if (inputAutonomia.text.toString() == "0") {
                Toast.makeText(
                    this, getString(R.string.aviso_autonomia_diferente_zero),
                    Toast.LENGTH_LONG
                ).show()
                inputAutonomia.text.clear()
            } else {
                try {
                    val preco = inputPreco.text.toString().toFloat()
                    val distancia = inputDistancia.text.toString().toFloat()
                    val autonomia = inputAutonomia.text.toString().toFloat()

                    val valorTotalGasto = (distancia * preco) / autonomia
                    textoValorTotal.text = "R$ ${"%.2f".format(valorTotalGasto)}"

                } catch (erroFormatoNumero: NumberFormatException) {
                    Toast.makeText(
                        this,
                        getString(R.string.informar_valores_validos),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        } else Toast.makeText(this, getString(R.string.preencha_todos_campos), Toast.LENGTH_LONG)
            .show()
    }

    private fun validarCampos(): Boolean {
        return (inputDistancia.text.toString() != "" && inputPreco.text.toString() != "" &&
                inputAutonomia.text.toString() != "")
    }
}