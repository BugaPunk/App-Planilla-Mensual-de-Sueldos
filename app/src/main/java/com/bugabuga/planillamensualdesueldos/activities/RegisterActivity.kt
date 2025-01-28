package com.bugabuga.planillamensualdesueldos.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bugabuga.planillamensualdesueldos.databinding.ActivityRegisterBinding
import com.bugabuga.planillamensualdesueldos.models.Usuario
import com.google.android.material.textfield.TextInputEditText
import pe.pcs.libpcs.UtilsCommon
import pe.pcs.libpcs.UtilsMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.lifecycle.lifecycleScope
import com.bugabuga.planillamensualdesueldos.operations.UsuarioOperation

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private var _id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()
    }

    private fun initListener() {
        binding.btnAddUser.setOnClickListener {
            if (binding.txtInputNombre.text.toString().trim().isEmpty() ||
                binding.txtInputApellido.text.toString().trim().isEmpty() ||
                binding.txtInputCorreo.text.toString().trim().isEmpty() ||
                binding.txtInputContrasena.text.toString().trim().isEmpty()) {
                UtilsMessage.showAlertOk("Error", "Todos los campos son obligatorios", this)
                return@setOnClickListener
            }
            grabar(
                Usuario(
                    _id,
                    binding.txtInputNombre.text.toString().trim(),
                    binding.txtInputApellido.text.toString().trim(),
                    binding.txtInputCorreo.text.toString().trim(),
                    binding.txtInputContrasena.text.toString().trim()
                )
            )
        }
    }

    private fun grabar(usuario: Usuario) {
        var msgError = ""

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    UsuarioOperation.registrarUsuario(usuario)

                } catch (ex: Exception) {
                    msgError = ex.message.toString()
                }
            }

            if (msgError.isNotEmpty()) {
                UtilsMessage.showAlertOk(
                    "Error", msgError, this@RegisterActivity
                )
                return@launch
            }

            UtilsMessage.showToast(
                this@RegisterActivity, "Usuario guardado"
            )

            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()

            UtilsCommon.cleanEditText(binding.root.rootView)
            _id = 0
            binding.txtInputNombre.requestFocus()
        }
    }
}