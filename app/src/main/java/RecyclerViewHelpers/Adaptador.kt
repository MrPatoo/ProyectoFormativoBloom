package RecyclerViewHelpers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import modelo.tbPaciente
import rodrigo.cordova.hospitalbloom.R

class Adaptador(var Datos: List<tbPaciente>): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //Unir rcv con la card
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.activity_card_paciente, parent, false)
        return ViewHolder(vista)
    }

    override fun getItemCount() = Datos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//Controlar la card
        //todo Mostrar datos --------------------------------------------------------------------------------------------
        val item = Datos[position]
        holder.txtNombreCard.text = item.nombre
        holder.txtApellidoCard.text = item.apellido
        holder.txtEdadCard.text = item.edad.toString()
        holder.txtNumHCard.text = item.numHabitacion.toString()
        holder.txtNumCCard.text = item.numCama.toString()
        holder.txtFechaIngresoCard.text = item.fechaIngreso
        holder.txtEnfermedadCard.text = item.enfermedad.toString()
        holder.txtMedicamentoCard.text = item.medicamento.toString()








    }


}