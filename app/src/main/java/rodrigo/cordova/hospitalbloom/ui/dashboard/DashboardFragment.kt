package rodrigo.cordova.hospitalbloom.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelo.ClaseConexion
import modelo.tbEnfermedad
import modelo.tbMedicamento
import rodrigo.cordova.hospitalbloom.R
import rodrigo.cordova.hospitalbloom.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val txtNombre = root.findViewById<TextView>(R.id.txtNombre)
        val txtApellido = root.findViewById<TextView>(R.id.txtApellido)
        val txtEdad = root.findViewById<TextView>(R.id.txtEdad)
        val txtNumH = root.findViewById<TextView>(R.id.txtNumH)
        val txtNumC = root.findViewById<TextView>(R.id.txtNumC)
        val txtFechaIngreso = root.findViewById<TextView>(R.id.txtFechaIngreso)
        val spEnfermedad = root.findViewById<Spinner>(R.id.spEnfermedad)
        val spMedicamento = root.findViewById<Spinner>(R.id.spMedicamento)
        val btnAgregar = root.findViewById<Button>(R.id.btnAgregar)

        //TODO OBTENER DATOS PARA LOS SP-----------------------------------------------------------------------------------------------

        //Obtener la tbEnfermedades
        fun obtenerEnfermedades(): List<tbEnfermedad>{
            val objConexion = ClaseConexion().cadenaConexion()

            val statement = objConexion?.createStatement()
            val resultSet = statement?.executeQuery("SELECT * FROM tbEnfermedad")!!

            val listadoEnfermedades = mutableListOf<tbEnfermedad>()

            while (resultSet.next()){
                val idEnfermedad = resultSet.getInt("idEnfermedad")
                val nombreEnfermedad = resultSet.getString("nombreEnfermedad")

                val enfermedadCompleta = tbEnfermedad(idEnfermedad, nombreEnfermedad)
                listadoEnfermedades.add(enfermedadCompleta)
            }
            return listadoEnfermedades
        }

        //Obtener el tbMEdicamento
        fun obtenerMedicamento(): List<tbMedicamento>{
            val objConexion = ClaseConexion().cadenaConexion()

            val statement = objConexion?.createStatement()
            val resultSet = statement?.executeQuery("SELECT * FROM tbMedicamento")!!

            val listadoMedicamento = mutableListOf<tbMedicamento>()

            while (resultSet.next()){
                val idMedicamento = resultSet.getInt("idMedicamento")
                val nombreMedicamento = resultSet.getString("nombreMedicamento")
                val horaAplicacion = resultSet.getString("horaAplicacion")

                val medicamentoCompleto = tbMedicamento(idMedicamento, nombreMedicamento, horaAplicacion)
                listadoMedicamento.add(medicamentoCompleto)
            }
            return listadoMedicamento
        }

        //TODO PROGRAMACION DEL SPINNER-----------------------------------------------------------------------------------------------------

        //spinner enfermedad
        CoroutineScope(Dispatchers.IO).launch {
            val listadoEnfermedades = obtenerEnfermedades()
            val nombreEnfermedad = listadoEnfermedades.map { it.NombreEnfermedad }

            withContext(Dispatchers.Main){
                val miAdapador = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, nombreEnfermedad)
                spEnfermedad.adapter = miAdapador
            }
        }

        //spinner medicamento
        CoroutineScope(Dispatchers.IO).launch {
            val listaMedicamentos = obtenerMedicamento()
            val nombreMedicamento = listaMedicamentos.map { it.NombreMedicamento }

            withContext(Dispatchers.Main){
                val miAdaptador = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item,nombreMedicamento)
                spMedicamento.adapter = miAdaptador
            }
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}