import kotlin.collections.*

data class Resposta(val palavra:String, val dicas : MutableList<String>)

val dicasCachorro = mutableListOf("Animal", "anda em 4 patas", "doméstico")
val cachorro = Resposta("Cachorro", dicasCachorro)

val dicasCamisa = mutableListOf("Objeto", "Roupa", "Manga")
val camisa = Resposta("Camisa", dicasCamisa)

val dicasPizza = mutableListOf("Comida", "Massa", "Redonda")
val pizza = Resposta("Pizza", dicasPizza)

val dicasMedico = mutableListOf("Profissão", "Saúde", "Pacientes")
val medico = Resposta("Médico", dicasMedico)

val dicasOceano = mutableListOf("Grande", "Ondas", "Salgado")
val oceano = Resposta("Oceano", dicasOceano)

val listaResp = mutableListOf<Resposta>(cachorro, camisa, pizza, medico, oceano)