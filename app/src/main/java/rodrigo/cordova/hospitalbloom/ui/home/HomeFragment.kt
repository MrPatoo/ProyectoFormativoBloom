package rodrigo.cordova.hospitalbloom.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelo.ClaseConexion
import modelo.tbPaciente
import rodrigo.cordova.hospitalbloom.R
import rodrigo.cordova.hospitalbloom.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val spPaciente = root.findViewById<Spinner>(R.id. spPaciente)
        val rcvPaciente = root.findViewById<RecyclerView>(R.id.rcvPacientes)


        //SP que buscara al paciente-----------------------------------------------------
        fun obtenerPaciente(): List<tbPaciente>{
            val objConexion = ClaseConexion().cadenaConexion()
            val statement = objConexion?.createStatement()
            val resultSet = statement?.executeQuery("select * from tbPaciente")!!

            val listadoPaciente = mutableListOf<tbPaciente>()

            while (resultSet.next()){
                val uuid = resultSet.getString("uuid")
                val nombre = resultSet.getString("nombre")
                val apellido = resultSet.getString("apellido")
                val edad = resultSet.getInt("edad")
                val numHabitacion = resultSet.getInt("numHabitacion")
                val numCama = resultSet.getInt("numCama")
                val fechaIngreso = resultSet.getString("fechaIngreso")
                val enfermedad = resultSet.getInt("enfermedad")
                val medicamento = resultSet.getInt("medicamento")
                val unPacienteCompleto = tbPaciente(uuid, nombre, apellido, edad, numHabitacion, numCama, fechaIngreso, enfermedad, medicamento)

                listadoPaciente.add(unPacienteCompleto)
            }
            return listadoPaciente
        }

        CoroutineScope(Dispatchers.IO).launch {
            val ListadoPacientes = obtenerPaciente()
            val nombrePaciente = ListadoPacientes.map { it.nombre }

            withContext(Dispatchers.Main){
                val miAdaptador = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, nombrePaciente)
                spPaciente.adapter = miAdaptador
            }
        }




        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}