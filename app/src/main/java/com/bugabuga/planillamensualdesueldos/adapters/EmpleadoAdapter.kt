package com.bugabuga.planillamensualdesueldos.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bugabuga.planillamensualdesueldos.databinding.ItemEmpleadoBinding
import com.bugabuga.planillamensualdesueldos.models.Empleado

class EmpleadoAdapter(
    private val listener: IOnClickListener
): RecyclerView.Adapter<EmpleadoAdapter.EmpleadoViewHolder>() {

    private var lista = emptyList<Empleado>()

    interface IOnClickListener {
        fun clickEditar(empleado: Empleado)
        fun clickEliminar(empleado: Empleado)
    }

    inner class EmpleadoViewHolder(private val binding: ItemEmpleadoBinding): RecyclerView.ViewHolder(binding.root) {
        fun enlazar(empleado: Empleado) {
            binding.txtNombreYApellidos.text = empleado.nombre + " " + empleado.apellidos
            binding.txtFIngreso.text = empleado.fechaIngreso
            binding.txtCargo.text = empleado.cargo
            binding.txtHaberB.text = empleado.haberBasico.toString()

            binding.btnEdit.setOnClickListener { listener.clickEditar(empleado) }
            binding.btnDelete.setOnClickListener { listener.clickEliminar(empleado) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EmpleadoViewHolder {
        return EmpleadoViewHolder(
            ItemEmpleadoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(
        holder: EmpleadoViewHolder,
        position: Int
    ) {
        holder.enlazar(lista[position])
    }

    fun setData(lista: List<Empleado>) {
        this.lista = lista
        notifyDataSetChanged()
    }

}