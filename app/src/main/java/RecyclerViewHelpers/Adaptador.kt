package RecyclerViewHelpers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import modelo.ClaseConexion
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



        //TODO eLIMINAR DATOS-------------------------------------------------------------------------------------------------

        fun eliminarDatos(nombre: String, position: Int){
            val ListaDatos = Datos.toMutableList()
            ListaDatos.removeAt(position)

            GlobalScope.launch(Dispatchers.IO){
                //crear objConexion
                val objConexion = ClaseConexion().cadenaConexion()

                //variable con el PrepareStatement
                val deletePaciente = objConexion?.prepareStatement("delete from tbPaciente where nombre = ?")!!
                deletePaciente.setString(1, nombre)
                deletePaciente.executeUpdate()

                val commit = objConexion.prepareStatement("commit")
                commit.executeUpdate()
            }
        }



        val paciente = Datos[position]
        holder.txtNombreCard.text = paciente.nombre
        holder.txtApellidoCard.text = paciente.apellido
        holder.txtEdadCard.text = paciente.edad.toString()
        holder.txtNumHCard.text = paciente.numHabitacion.toString()
        holder.txtNumCCard.text = paciente.numCama.toString()
        holder.txtFechaIngresoCard.text = paciente.fechaIngreso
        holder.txtEnfermedadCard.text = paciente.enfermedad.toString()
        holder.txtMedicamentoCard.text = paciente.medicamento.toString()

        holder.btnEliminarCard.setOnClickListener{
            //alert
            val context = holder.itemView.context

            val builder = AlertDialog.Builder(context)
            builder.setTitle("Eliminar")
            builder.setTitle("¿Desea eliminar el ticket?")
            //botones
            builder.setPositiveButton("Si"){dialog, switch -> eliminarDatos(paciente. nombre, position)

            }

            builder.setNegativeButton("No"){dialog, switch -> dialog.dismiss()

            }
            val dialog = builder.create()
            dialog.show()
        }





    }


}