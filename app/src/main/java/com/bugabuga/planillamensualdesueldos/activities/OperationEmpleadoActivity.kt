package com.bugabuga.planillamensualdesueldos.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bugabuga.planillamensualdesueldos.databinding.ActivityOperationEmpleadoBinding
import androidx.activity.enableEdgeToEdge
import com.bugabuga.planillamensualdesueldos.R
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.bugabuga.planillamensualdesueldos.models.Empleado
import com.bugabuga.planillamensualdesueldos.operations.EmpleadoOperation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pe.pcs.libpcs.UtilsCommon
import pe.pcs.libpcs.UtilsMessage

class OperationEmpleadoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOperationEmpleadoBinding
    private var _id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOperationEmpleadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.extras != null) {
            obtenerEmpleado()
        }

        initListener()
    }

    private fun obtenerEmpleado() {
        _id = intent.extras?.getInt("id", 0) ?: 0
        binding.txtInputNombre.setText(intent.extras?.getString("nombre", ""))
        binding.txtInputApellidos.setText(intent.extras?.getString("apellidos", ""))
        binding.txtInputFIngreso.setText(intent.extras?.getString("fechaIngreso", ""))
        binding.txtInputCargo.setText(intent.extras?.getString("cargo", ""))
        binding.txtInputHaberB.setText(intent.extras?.getDouble("haberBasico", 0.0).toString())
    }

    private fun initListener() {

        binding.toolbar.toolbar.apply {
            subtitle = "Registro de Empleado"
            navigationIcon = AppCompatResources.getDrawable(this@OperationEmpleadoActivity, R.drawable.back_rounded)

            setNavigationOnClickListener {
                onBackPressedDispatcher.onBackPressed()

            }
        }

        binding.toolbar.btnAccion.setImageResource(R.drawable.check_rounded)

        binding.toolbar.btnAccion.setOnClickListener {
            if (binding.txtInputNombre.text.toString().trim().isEmpty() ||
                binding.txtInputApellidos.text.toString().trim().isEmpty() ||
                binding.txtInputFIngreso.text.toString().trim().isEmpty() ||
                binding.txtInputCargo.text.toString().trim().isEmpty() ||
                binding.txtInputHaberB.text.toString().trim().isEmpty()) {
                UtilsMessage.showAlertOk("Error", "Todos los campos son obligatorios", this)
                return@setOnClickListener
            }

            grabar(
                Empleado(
                    _id,
                    binding.txtInputNombre.text.toString().trim(),
                    binding.txtInputApellidos.text.toString().trim(),
                    binding.txtInputFIngreso.text.toString().trim(),
                    binding.txtInputCargo.text.toString().trim(),
                    binding.txtInputHaberB.text.toString().trim().toDouble()
                )
            )
        }
    }

    private fun grabar(empleado: Empleado) {
        var msgError = ""

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    EmpleadoOperation.grabar(empleado)
                } catch (ex: Exception) {
                    msgError = ex.message.toString()
                }
            }

            if (msgError.isNotEmpty()) {
                UtilsMessage.showAlertOk(
                    "Error", msgError, this@OperationEmpleadoActivity
                )
                return@launch
            }

            UtilsMessage.showToast(
                this@OperationEmpleadoActivity, "Empleado grabado"
            )

            UtilsCommon.cleanEditText(binding.root.rootView)
            _id = 0
            binding.txtInputNombre.requestFocus()
        }
    }
}