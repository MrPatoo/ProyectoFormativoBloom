package RecyclerViewHelpers

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import rodrigo.cordova.hospitalbloom.R

class ViewHolder(view: View):RecyclerView.ViewHolder(view) {

    //todo se mandan a llamar los elementos de la card%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    val txtNombreCard = view.findViewById<TextView>(R.id.txtNombreCard)
    val txtApellidoCard = view.findViewById<TextView>(R.id.txtApellidoCard)
    val txtEdadCard = view.findViewById<TextView>(R.id.txtEdadCard)
    val txtNumHCard = view.findViewById<TextView>(R.id.txtNumHCard)
    val txtNumCCard = view.findViewById<TextView>(R.id.txtNumCCard)
    val txtFechaIngresoCard = view.findViewById<TextView>(R.id.txtFechaIngresoCard)
    val txtEnfermedadCard = view.findViewById<TextView>(R.id.txtEnfermedadCard)
    val txtMedicamentoCard = view.findViewById<TextView>(R.id.txtMedicamentoCard)
    val btnEditarCard = view.findViewById<ImageView>(R.id.btnEditar)
    val btnEliminarCard = view.findViewById<ImageView>(R.id.btnEliminar)
}