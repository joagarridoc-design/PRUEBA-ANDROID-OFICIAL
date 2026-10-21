interface InstrumentoRepository {
    suspend fun obtenerTodos(): List<Instrumento>
}