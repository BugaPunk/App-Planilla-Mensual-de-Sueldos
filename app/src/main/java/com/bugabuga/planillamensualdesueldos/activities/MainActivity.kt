package com.bugabuga.planillamensualdesueldos.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bugabuga.planillamensualdesueldos.databinding.ActivityMainBinding
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bugabuga.planillamensualdesueldos.adapters.EmpleadoAdapter
import com.bugabuga.planillamensualdesueldos.models.Empleado
import com.bugabuga.planillamensualdesueldos.operations.EmpleadoOperation
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pe.pcs.libpcs.UtilsCommon
import pe.pcs.libpcs.UtilsMessage

class MainActivity : AppCompatActivity(), EmpleadoAdapter.IOnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()
        leerEmpleado("")
    }

    private fun initListeners() {
        binding.rvLista.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = EmpleadoAdapter(this@MainActivity)
        }

        binding.txtILBuscar.setEndIconOnClickListener {
            leerEmpleado(binding.txtInputBuscar.text.toString().trim())
        }

        binding.toolbar.btnAccion.setOnClickListener {
            startActivity(
                Intent(this, OperationEmpleadoActivity::class.java)
            )
        }
    }

    private fun leerEmpleado(dato: String) {
        var msgError = ""

        lifecycleScope.launch {

            val result = withContext(Dispatchers.IO) {
                try {
                    EmpleadoOperation.listar(dato)
                } catch (e: Exception) {
                    msgError = e.message.toString()
                    emptyList<Empleado>()
                }
            }

            if (msgError.isNotEmpty())
                UtilsMessage.showAlertOk("ERROR", msgError, this@MainActivity)

            (binding.rvLista.adapter as EmpleadoAdapter).setData(result)
        }
    }

    private fun eliminar(empleado: Empleado) {
        var msgError = ""

        lifecycleScope.launch {

            withContext(Dispatchers.IO) {
                try {
                    EmpleadoOperation.eliminar(empleado)
                } catch (e: Exception) {
                    msgError = e.message.toString()
                }
            }

            if (msgError.isNotEmpty()){
                UtilsMessage.showAlertOk("Error", msgError, this@MainActivity)
                return@launch
            }
            UtilsMessage.showToast(this@MainActivity, "Empleado eliminado")
            leerEmpleado(binding.txtInputBuscar.text.toString().trim())
        }
    }

    override fun clickEditar(empleado: Empleado) {
        startActivity(
            Intent(this, OperationEmpleadoActivity::class.java).apply {
                putExtra("id", empleado.id)
                putExtra("nombre", empleado.nombre)
                putExtra("apellidos", empleado.apellidos)
                putExtra("fechaIngreso", empleado.fechaIngreso)
                putExtra("cargo", empleado.cargo)
                putExtra("haberBasico", empleado.haberBasico)
            }
        )
    }

    override fun clickEliminar(empleado: Empleado) {
        MaterialAlertDialogBuilder(this).apply {
            setCancelable(false)
            setTitle("Eliminar")
            setMessage("¿Desea eliminar el empleado ${empleado.nombre}?")
            setPositiveButton("Si") { dialog, _ ->
                eliminar(empleado)
                dialog.dismiss()
            }
            setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            show()
        }
    }
}