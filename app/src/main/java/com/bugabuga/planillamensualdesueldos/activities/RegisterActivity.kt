package com.bugabuga.planillamensualdesueldos.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bugabuga.planillamensualdesueldos.databinding.ActivityRegisterBinding
import com.bugabuga.planillamensualdesueldos.models.Usuario
import com.bugabuga.planillamensualdesueldos.operations.UsuarioOperation
import com.bugabuga.planillamensualdesueldos.operations.UtilsCrypto
import com.bugabuga.planillamensualdesueldos.operations.UtilsValidacion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pe.pcs.libpcs.UtilsCommon
import pe.pcs.libpcs.UtilsMessage

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
            val nombre = binding.txtInputNombre.text.toString().trim()
            val apellido = binding.txtInputApellido.text.toString().trim()
            val correo = binding.txtInputCorreo.text.toString().trim()
            val contrasena = binding.txtInputContrasena.text.toString().trim()

            // Validar campos vacíos
            if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
                UtilsMessage.showAlertOk("Error", "Todos los campos son obligatorios", this)
                return@setOnClickListener
            }

            // Validar formato del correo
            if (!UtilsValidacion.validarCorreo(correo)) {
                UtilsMessage.showAlertOk("Error", "Correo electrónico no válido", this)
                return@setOnClickListener
            }

            grabar(
                Usuario(
                    _id,
                    nombre,
                    apellido,
                    correo,
                    contrasena
                )
            )
        }
    }

    private fun grabar(usuario: Usuario) {
        var msgError = ""

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    // Cifrar la contraseña antes de guardarla
                    val constraseniaCifrada = UtilsCrypto.hashSHA256(usuario.password)
                    val usuarioCifrado = usuario.copy(password = constraseniaCifrada)

                    UsuarioOperation.registrarUsuario(usuarioCifrado)
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