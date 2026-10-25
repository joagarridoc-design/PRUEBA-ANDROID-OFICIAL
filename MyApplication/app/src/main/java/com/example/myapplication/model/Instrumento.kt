import com.example.myapplication.ui.screens.psicologo.Instrumento

interface InstrumentoRepository {
    suspend fun obtenerTodos(): List<Instrumento>
}