package com.example.alcoolougasolina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Switch
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var percentual : Double = 0.7
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            percentual=savedInstanceState.getDouble("percentual")
        }

        Log.i("PDM24.1","No onCreate, $percentual")

        val btCalc : Button = findViewById(R.id.btCalcular)
        val swPerc : Switch = findViewById(R.id.swPercentual)

        swPerc.setOnCheckedChangeListener { _, isChecked ->
            percentual = if (isChecked){
                0.75
            } else {
                0.7
            }
        }

        btCalc.setOnClickListener(View.OnClickListener {
            alcGas(percentual)
            Log.d("PDM24","No btCalcular, $percentual")
        })
    }

    fun alcGas (percentual : Double){
        val alcValor : TextView = findViewById(R.id.edAlcool)
        val gasValor : TextView = findViewById(R.id.edGasolina)
        val msgResultado : TextView = findViewById(R.id.result)

        if(alcValor.text.toString() != "" && gasValor.text.toString() != ""){
            val alcDouble: Double = alcValor.text.toString().toDouble()
            val gasDouble: Double = gasValor.text.toString().toDouble()

            msgResultado.text = when {
                alcDouble <= percentual * gasDouble -> getString(R.string.msgAlcool)
                else -> getString(R.string.msgGasolina)
            }
        } else if (alcValor.text.toString() == "" || gasValor.text.toString() == "") {
            msgResultado.text = getString(R.string.msgError)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putDouble("percentual",percentual)
        super.onSaveInstanceState(outState)
    }

    override fun onResume(){
        super.onResume()
        Log.d("PDM24","No onResume, $percentual")
    }
    override fun onStart(){
        super.onStart()
        Log.v("PDM24","No onStart")
    }
    override fun onPause(){
        super.onPause()
        Log.e("PDM24","No onPause")
    }
    override fun onStop(){
        super.onStop()
        Log.w("PDM24","No onStop")
    }
    override fun onDestroy(){
        super.onDestroy()
        Log.wtf("PDM24","No Destroy")
    }
}