package modelo

data class tbPaciente(
    val uuid: String,
    val nombre: String,
    val apellido: String,
    val edad: Number,
    val numHabitacion: Number,
    val numCama: Number,
    val fechaIngreso: String,
    val enfermedad: Number,
    val medicamento: Number
)
