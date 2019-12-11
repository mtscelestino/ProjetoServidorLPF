import io.ktor.application.*
import io.ktor.http.ContentType
import io.ktor.http.content.*
import io.ktor.response.respondText
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlin.collections.*

var jogoEmAndamento = true
var jogadorVenceu = false
var vencedor = ""

var random = (0..4).random()
val resp = listaResp.elementAt(random)

var palpites = 0
var listaDePalpites = mutableListOf<String>()

fun main() {
    embeddedServer(Netty, port = 8080, host = "192.168.15.128") {
        routing {
            get("guesswhat.html") {
                var nome = call.parameters.get("nome")
                var palpite = call.parameters.get("palpite")
                while (jogoEmAndamento == true) {
                    if(palpite != null){
                        if (palpite != resp.palavra) {
                            if (palpites <= 5) {
                                listaDePalpites.add(palpite)
                                palpites++
                                call.respondText("""
                                <html>
                                    <head>
                                        <title>Guess What!</title>
                                        <meta charset="utf-8">
                                        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
                                        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
                                        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
                                        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
                                        <link href="https://fonts.googleapis.com/css?family=Annie+Use+Your+Telescope&display=swap" rel="stylesheet">
                                        <link href="https://fonts.googleapis.com/css?family=Gloria+Hallelujah&display=swap" rel="stylesheet">
                                    </head>
                                    <body>
                                        <div class="transparencia">	
                                            <header><h1 class="titulo">Guess What!</h1></header>
                                            <label id="resultado">
                                                <h2 class="resposta"> <b></b></h2>		
                                            </label>
                                            <div class="col-md-12">
                                                 <div id="boxes" class="row">
                                                        <div id="palpite" class="col-md-4">
                                                            <h2 class="subtitulo">Palpites: </h2>
                                                            <div>
                                                                <h4 class="subtitulo2"> ${listaDePalpites.toString()} </h4>
                                                            </div>
                                                            <h4 class="subtitulo">Contador de palpites: </h4>
                                                            <h5 class="subtitulo"> $palpites </h5>
                                                        </div>
                                                        <div id="dica" class="col-md-4">
                                                            <h2 class="subtitulo">Dicas:</h2>
                                                            <div>
                                                                <h4 class="subtitulo"> ${resp.dicas.component1()} </h4>
                                                            </div>
                                                        </div>
                                                 </div>
                                                 <div class="col-md-4">
                                                    <form class="formulario">
                                                        <div class="form-group">
                                                            <h5 class="subtitulo2">Nome:</h5>
                                                            <input class="form-control" type="text" name="nome">		
                                                        </div>
                                                        <div class="form-group">
                                                            <h5 class="subtitulo2">Palpite:</h5>
                                                            <input class="form-control" type="text" name="palpite">			
                                                        </div>
                                                        <div class="form-group">
                                                            <button class="envia">ENVIAR</button>
                                                        </div>		
                                                    </form>
                                                </div>
                                            </div>
                                            <footer style="margin-top: 10%;" class="subtitulo"> Desenvolvido por: Mateus Celestino, Bruno Cavalcanti, Felipe Steffano</footer>
                                        </div>	
                                    </body>
                                    <style>
                                    .formulario
                                    		{
                                    			background-color: rgba(68, 218, 235, 1);
                                    			border-style: dashed;
                                    			border-color: #fff;
                                    			margin-top: 1%;
                                    			padding: 10px 10px 10px 10px;
                                    			margin-left: 28%;
                                    			width: 50%;
                                    		}
                                    		.envia
                                    		{
                                    			color: #fff;
                                    			border-radius: 3px;
                                    			background-color: #f5338e;
                                    			font-family:  'Gloria Hallelujah', cursive;
                                    			border-style: none;
                                    			font-size: 1.2em;
                                    			width: 45%;
                                    			transition-duration: 0.6s;
                                    		}
                                    		.envia:hover
                                    		{
                                    			background-color: #fff;
                                    			color: #f5338e;
                                    		}
                                    		#resultado
                                    		{
                                    			margin-top: 2%;	
                                    			width: 100%;	
                                    		}
                                    		.resposta
                                    		{
                                    				color: #f5338e;
                                    				font-family:  'Gloria Hallelujah', cursive;
                                    				text-align: center;

                                    		}
                                    	
                                    		#boxes
                                    		{
                                    			
                                    			margin-top: 5%;
                                    		}
                                    		#palpite
                                    		{
                                    			background-color: rgba(68, 218, 235, 1);
                                    			margin-left: 10%;
                                    			border-style: dashed;
                                    			border-width: 2px;
                                    			border-color: #fff;	
                                    		}
                                    		#dica
                                    		{
                                    			background-color: rgba(68, 218, 235, 1);
                                    			margin-left: 10%;
                                    			border-style: dashed;
                                    			border-width: 2px;
                                    			border-color: #fff;	
                                    		}
                                    		.titulo{
                                    			color: #fff;
                                    			text-align: center;
                                    			margin-top: 5%;
                                    			font-size: 50px;
                                    			font-family:  'Gloria Hallelujah', cursive;	
                                    		}
                                    		.subtitulo
                                    		{
                                    			color: #fff;
                                    			text-align: center;
                                    			margin-top: 2%;
                                    			font-family:  'Gloria Hallelujah', cursive;	
                                    		}
                                    		.subtitulo2
                                    		{
                                    			color: #fff;
                                    			text-align: left;
                                    			margin-top: 2%;
                                    			font-family:  'Gloria Hallelujah', cursive;	
                                    		}
                                    	.transparencia
                                    	{
                                    		float: left;
                                    		background-color: rgba(68, 218, 235, 0.3);
                                    		width: 100%;
                                    	}
                                    	body{
                                    		background-image: url(https://media.giphy.com/media/xUOxfjsW9fWPqEWouI/giphy.gif);
                                    	}
                                    	</style>
                                    </html>
                        """.trimIndent(), ContentType.Text.Html)
                                palpite = null
                            }else if (palpites > 5 && palpites <= 10){
                                listaDePalpites.add(palpite)
                                palpites++
                                call.respondText("""
                                <html>
                                    <head>
                                        <title>Guess What!</title>
                                        <meta charset="utf-8">
                                        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
                                        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
                                        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
                                        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
                                        <link href="https://fonts.googleapis.com/css?family=Annie+Use+Your+Telescope&display=swap" rel="stylesheet">
                                        <link href="https://fonts.googleapis.com/css?family=Gloria+Hallelujah&display=swap" rel="stylesheet">
                                    </head>
                                    <body>
                                        <div class="transparencia">	
                                            <header><h1 class="titulo">Guess What!</h1></header>
                                            <label id="resultado">
                                                <h2 class="resposta"> <b></b></h2>		
                                            </label>
                                            <div class="col-md-12">
                                                 <div id="boxes" class="row">
                                                        <div id="palpite" class="col-md-4">
                                                            <h2 class="subtitulo">Palpites: </h2>
                                                            <div>
                                                                <h4 class="subtitulo2"> ${listaDePalpites.toString()} </h4>
                                                            </div>
                                                            <h4 class="subtitulo">Contador de palpites: </h4>
                                                            <h5 class="subtitulo"> $palpites </h5>
                                                        </div>
                                                        <div id="dica" class="col-md-4">
                                                            <h2 class="subtitulo">Dicas:</h2>
                                                            <div>
                                                                <h4 class="subtitulo"> ${resp.dicas.component1()} <br> ${resp.dicas.component2()} </h4>
                                                            </div>
                                                        </div>
                                                 </div>
                                                 <div class="col-md-4">
                                                    <form class="formulario">
                                                        <div class="form-group">
                                                            <h5 class="subtitulo2">Nome:</h5>
                                                            <input class="form-control" type="text" name="nome">		
                                                        </div>
                                                        <div class="form-group">
                                                            <h5 class="subtitulo2">Palpite:</h5>
                                                            <input class="form-control" type="text" name="palpite">			
                                                        </div>
                                                        <div class="form-group">
                                                            <button class="envia">ENVIAR</button>
                                                        </div>		
                                                    </form>
                                                </div>
                                            </div>
                                            <footer style="margin-top: 10%;" class="subtitulo"> Desenvolvido por: Mateus Celestino, Bruno Cavalcanti, Felipe Steffano</footer>
                                        </div>	
                                    </body>
                                    <style>
                                    .formulario
                                    		{
                                    			background-color: rgba(68, 218, 235, 1);
                                    			border-style: dashed;
                                    			border-color: #fff;
                                    			margin-top: 1%;
                                    			padding: 10px 10px 10px 10px;
                                    			margin-left: 28%;
                                    			width: 50%;
                                    		}
                                    		.envia
                                    		{
                                    			color: #fff;
                                    			border-radius: 3px;
                                    			background-color: #f5338e;
                                    			font-family:  'Gloria Hallelujah', cursive;
                                    			border-style: none;
                                    			font-size: 1.2em;
                                    			width: 45%;
                                    			transition-duration: 0.6s;
                                    		}
                                    		.envia:hover
                                    		{
                                    			background-color: #fff;
                                    			color: #f5338e;
                                    		}
                                    		#resultado
                                    		{
                                    			margin-top: 2%;	
                                    			width: 100%;	
                                    		}
                                    		.resposta
                                    		{
                                    				color: #f5338e;
                                    				font-family:  'Gloria Hallelujah', cursive;
                                    				text-align: center;

                                    		}
                                    	
                                    		#boxes
                                    		{
                                    			
                                    			margin-top: 5%;
                                    		}
                                    		#palpite
                                    		{
                                    			background-color: rgba(68, 218, 235, 1);
                                    			margin-left: 10%;
                                    			border-style: dashed;
                                    			border-width: 2px;
                                    			border-color: #fff;	
                                    		}
                                    		#dica
                                    		{
                                    			background-color: rgba(68, 218, 235, 1);
                                    			margin-left: 10%;
                                    			border-style: dashed;
                                    			border-width: 2px;
                                    			border-color: #fff;	
                                    		}
                                    		.titulo{
                                    			color: #fff;
                                    			text-align: center;
                                    			margin-top: 5%;
                                    			font-size: 50px;
                                    			font-family:  'Gloria Hallelujah', cursive;	
                                    		}
                                    		.subtitulo
                                    		{
                                    			color: #fff;
                                    			text-align: center;
                                    			margin-top: 2%;
                                    			font-family:  'Gloria Hallelujah', cursive;	
                                    		}
                                    		.subtitulo2
                                    		{
                                    			color: #fff;
                                    			text-align: left;
                                    			margin-top: 2%;
                                    			font-family:  'Gloria Hallelujah', cursive;	
                                    		}
                                    	.transparencia
                                    	{
                                    		float: left;
                                    		background-color: rgba(68, 218, 235, 0.3);
                                    		width: 100%;

                                    	}
                                    	body{
                                    		background-image: url(https://media.giphy.com/media/xUOxfjsW9fWPqEWouI/giphy.gif);
                                    	
                                    	}
                                    	</style>
                                    </html>
                        """.trimIndent(), ContentType.Text.Html)
                                palpite = null
                            }else if (palpites > 10 && palpites <= 15){
                                listaDePalpites.add(palpite)
                                palpites++
                                call.respondText("""
                                <html>
                                    <head>
                                        <title>Guess What!</title>
                                        <meta charset="utf-8">
                                        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
                                        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
                                        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
                                        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
                                        <link href="https://fonts.googleapis.com/css?family=Annie+Use+Your+Telescope&display=swap" rel="stylesheet">
                                        <link href="https://fonts.googleapis.com/css?family=Gloria+Hallelujah&display=swap" rel="stylesheet">
                                    </head>
                                    <body>
                                        <div class="transparencia">	
                                            <header><h1 class="titulo">Guess What!</h1></header>
                                            <label id="resultado">
                                                <h2 class="resposta"> <b></b></h2>		
                                            </label>
                                            <div class="col-md-12">
                                                 <div id="boxes" class="row">
                                                        <div id="palpite" class="col-md-4">
                                                            <h2 class="subtitulo">Palpites: </h2>
                                                            <div>
                                                                <h4 class="subtitulo2"> ${listaDePalpites.toString()} </h4>
                                                            </div>
                                                            <h4 class="subtitulo">Contador de palpites: </h4>
                                                            <h5 class="subtitulo"> $palpites </h5>
                                                        </div>
                                                        <div id="dica" class="col-md-4">
                                                            <h2 class="subtitulo">Dicas:</h2>
                                                            <div>
                                                                <h4 class="subtitulo">${resp.dicas.component1()}<br>${resp.dicas.component2()}<br>${resp.dicas.component3()}<br></h4>
                                                            </div>
                                                        </div>
                                                 </div>
                                                 <div class="col-md-4">
                                                    <form class="formulario">
                                                        <div class="form-group">
                                                            <h5 class="subtitulo2">Nome:</h5>
                                                            <input class="form-control" type="text" name="nome">		
                                                        </div>
                                                        <div class="form-group">
                                                            <h5 class="subtitulo2">Palpite:</h5>
                                                            <input class="form-control" type="text" name="palpite">			
                                                        </div>
                                                        <div class="form-group">
                                                            <button class="envia">ENVIAR</button>
                                                        </div>		
                                                    </form>
                                                </div>
                                            </div>
                                            <footer style="margin-top: 10%;" class="subtitulo"> Desenvolvido por: Mateus Celestino, Bruno Cavalcanti, Felipe Steffano</footer>
                                        </div>	
                                    </body>
                                    <style>
                                    .formulario
                                    		{
                                    			background-color: rgba(68, 218, 235, 1);
                                    			border-style: dashed;
                                    			border-color: #fff;
                                    			margin-top: 1%;
                                    			padding: 10px 10px 10px 10px;
                                    			margin-left: 28%;
                                    			width: 50%;
                                    		}
                                    		.envia
                                    		{
                                    			color: #fff;
                                    			border-radius: 3px;
                                    			background-color: #f5338e;
                                    			font-family:  'Gloria Hallelujah', cursive;
                                    			border-style: none;
                                    			font-size: 1.2em;
                                    			width: 45%;
                                    			transition-duration: 0.6s;
                                    		}
                                    		.envia:hover
                                    		{
                                    			background-color: #fff;
                                    			color: #f5338e;
                                    		}
                                    		#resultado
                                    		{
                                    			margin-top: 2%;	
                                    			width: 100%;	
                                    		}
                                    		.resposta
                                    		{
                                    				color: #f5338e;
                                    				font-family:  'Gloria Hallelujah', cursive;
                                    				text-align: center;
                                    		}
                                    		#boxes
                                    		{
                                    			margin-top: 5%;
                                    		}
                                    		#palpite
                                    		{
                                    			background-color: rgba(68, 218, 235, 1);
                                    			margin-left: 10%;
                                    			border-style: dashed;
                                    			border-width: 2px;
                                    			border-color: #fff;	
                                    		}
                                    		#dica
                                    		{
                                    			background-color: rgba(68, 218, 235, 1);
                                    			margin-left: 10%;
                                    			border-style: dashed;
                                    			border-width: 2px;
                                    			border-color: #fff;	
                                    		}
                                    		.titulo{
                                    			color: #fff;
                                    			text-align: center;
                                    			margin-top: 5%;
                                    			font-size: 50px;
                                    			font-family:  'Gloria Hallelujah', cursive;	
                                    		}
                                    		.subtitulo
                                    		{
                                    			color: #fff;
                                    			text-align: center;
                                    			margin-top: 2%;
                                    			font-family:  'Gloria Hallelujah', cursive;	
                                    		}
                                    		.subtitulo2
                                    		{
                                    			color: #fff;
                                    			text-align: left;
                                    			margin-top: 2%;
                                    			font-family:  'Gloria Hallelujah', cursive;	
                                    		}
                                    	.transparencia
                                    	{
                                    		float: left;
                                    		background-color: rgba(68, 218, 235, 0.3);
                                    		width: 100%;
                                    	}
                                    	body{
                                    		background-image: url(https://media.giphy.com/media/xUOxfjsW9fWPqEWouI/giphy.gif);
                                    	}
                                    	</style>
                                    </html>
                        """.trimIndent(), ContentType.Text.Html)
                                palpite = null
                            } else if (palpites > 15) {
                                jogoEmAndamento = false
                            }
                        } else {
                            jogoEmAndamento = false
                            jogadorVenceu = true
                            vencedor = nome.toString()
                        }
                    }else{
                        call.respondText("""
                                <html>
                                    <head>
                                        <title>Guess What!</title>
                                        <meta charset="utf-8">
                                        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
                                        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
                                        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
                                        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
                                        <link href="https://fonts.googleapis.com/css?family=Annie+Use+Your+Telescope&display=swap" rel="stylesheet">
                                        <link href="https://fonts.googleapis.com/css?family=Gloria+Hallelujah&display=swap" rel="stylesheet">
                                    </head>
                                    <body>
                                        <div class="transparencia">	
                                            <header><h1 class="titulo">Guess What!</h1></header>
                                            <label id="resultado">
                                                <h2 class="resposta"> <b></b></h2>		
                                            </label>
                                            <div class="col-md-12">
                                                 <div id="boxes" class="row">
                                                        <div id="palpite" class="col-md-4">
                                                            <h2 class="subtitulo">Palpites: </h2>
                                                            <div>
                                                                <h4 class="subtitulo2"> ${listaDePalpites.toString()} </h4>
                                                            </div>
                                                            <h4 class="subtitulo">Contador de palpites: </h4>
                                                            <h5 class="subtitulo"> $palpites </h5>
                                                        </div>
                                                        <div id="dica" class="col-md-4">
                                                            <h2 class="subtitulo">Dicas:</h2>
                                                            <div>
                                                                <h4 class="subtitulo">${resp.dicas.component1()}<br></h4>
                                                            </div>
                                                        </div>
                                                 </div>
                                                 <div class="col-md-4">
                                                    <form class="formulario">
                                                        <div class="form-group">
                                                            <h5 class="subtitulo2">Nome:</h5>
                                                            <input class="form-control" type="text" name="nome">		
                                                        </div>
                                                        <div class="form-group">
                                                            <h5 class="subtitulo2">Palpite:</h5>
                                                            <input class="form-control" type="text" name="palpite">			
                                                        </div>
                                                        <div class="form-group">
                                                            <button class="envia">ENVIAR</button>
                                                        </div>		
                                                    </form>
                                                </div>
                                            </div>
                                            <footer style="margin-top: 10%;" class="subtitulo"> Desenvolvido por: Mateus Celestino, Bruno Cavalcanti, Felipe Steffano</footer>
                                        </div>	
                                    </body>
                                    <style>
                                    .formulario
                                    		{
                                    			background-color: rgba(68, 218, 235, 1);
                                    			border-style: dashed;
                                    			border-color: #fff;
                                    			margin-top: 1%;
                                    			padding: 10px 10px 10px 10px;
                                    			margin-left: 28%;
                                    			width: 50%;
                                    		}
                                    		.envia
                                    		{
                                    			color: #fff;
                                    			border-radius: 3px;
                                    			background-color: #f5338e;
                                    			font-family:  'Gloria Hallelujah', cursive;
                                    			border-style: none;
                                    			font-size: 1.2em;
                                    			width: 45%;
                                    			transition-duration: 0.6s;
                                    		}
                                    		.envia:hover
                                    		{
                                    			background-color: #fff;
                                    			color: #f5338e;
                                    		}
                                    		#resultado
                                    		{
                                    			margin-top: 2%;	
                                    			width: 100%;	
                                    		}
                                    		.resposta
                                    		{
                                    				color: #f5338e;
                                    				font-family:  'Gloria Hallelujah', cursive;
                                    				text-align: center;

                                    		}
                                    	
                                    		#boxes
                                    		{
                                    			
                                    			margin-top: 5%;
                                    		}
                                    		#palpite
                                    		{
                                    			background-color: rgba(68, 218, 235, 1);
                                    			margin-left: 10%;
                                    			border-style: dashed;
                                    			border-width: 2px;
                                    			border-color: #fff;	
                                    		}
                                    		#dica
                                    		{
                                    			background-color: rgba(68, 218, 235, 1);
                                    			margin-left: 10%;
                                    			border-style: dashed;
                                    			border-width: 2px;
                                    			border-color: #fff;	
                                    		}
                                    		.titulo{
                                    			color: #fff;
                                    			text-align: center;
                                    			margin-top: 5%;
                                    			font-size: 50px;
                                    			font-family:  'Gloria Hallelujah', cursive;	
                                    		}
                                    		.subtitulo
                                    		{
                                    			color: #fff;
                                    			text-align: center;
                                    			margin-top: 2%;
                                    			font-family:  'Gloria Hallelujah', cursive;	
                                    		}
                                    		.subtitulo2
                                    		{
                                    			color: #fff;
                                    			text-align: left;
                                    			margin-top: 2%;
                                    			font-family:  'Gloria Hallelujah', cursive;	
                                    		}
                                    	.transparencia
                                    	{
                                    		float: left;
                                    		background-color: rgba(68, 218, 235, 0.3);
                                    		width: 100%;

                                    	}
                                    	body{
                                    		background-image: url(https://media.giphy.com/media/xUOxfjsW9fWPqEWouI/giphy.gif);
                                    	}
                                    	</style>
                                    </html>
                        """.trimIndent(), ContentType.Text.Html)
                    }
                }
                    if (jogadorVenceu == true){
                        call.respondText(
                            """
                                <html>
                                    <head>
                                        <title>Guess What!</title>
                                        <meta charset="utf-8">
                                        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
                                        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
                                        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
                                        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
                                        <link href="https://fonts.googleapis.com/css?family=Annie+Use+Your+Telescope&display=swap" rel="stylesheet">
                                        <link href="https://fonts.googleapis.com/css?family=Gloria+Hallelujah&display=swap" rel="stylesheet">
                                    </head>
                                    <body>
                                        <div class="transparencia">	
                                            <header><h1 class="titulo">Guess What!</h1></header>
                                            <label id="resultado">
                                                <h2 class="resposta"><br> Parabéns $vencedor!!! você advinhou corretamente a palavra!<br>A Resposta era: <b>${resp.palavra}</b>!!<br><b></b></h2>		
                                            </label>
                                            <div class="col-md-12">
                                                 <div id="boxes" class="row">
                                                        <div id="palpite" class="col-md-4">
                                                            <h2 class="subtitulo">Palpites: </h2>
                                                            <div>
                                                                <h4 class="subtitulo2"> ${listaDePalpites.toString()} </h4>
                                                            </div>
                                                            <h4 class="subtitulo">Contador de palpites: </h4>
                                                            <h5 class="subtitulo"> $palpites </h5>
                                                        </div>
                                                        <div id="dica" class="col-md-4">
                                                            <h2 class="subtitulo">Dicas:</h2>
                                                            <div>
                                                                <h4 class="subtitulo">${resp.dicas.component1()}<br>${resp.dicas.component2()}<br>${resp.dicas.component3()}<br></h4>
                                                            </div>
                                                        </div>
                                                 </div>
                                                 <div class="col-md-4">
                                                    <form class="formulario">
                                                        <div class="form-group">
                                                            <h5 class="subtitulo2">Nome:</h5>
                                                            <input class="form-control" type="text" name="nome">		
                                                        </div>
                                                        <div class="form-group">
                                                            <h5 class="subtitulo2">Palpite:</h5>
                                                            <input class="form-control" type="text" name="palpite">			
                                                        </div>
                                                        <div class="form-group">
                                                            <button class="envia">ENVIAR</button>
                                                        </div>		
                                                    </form>
                                                </div>
                                            </div>
                                            <footer style="margin-top: 10%;" class="subtitulo"> Desenvolvido por: Mateus Celestino, Bruno Cavalcanti, Felipe Steffano</footer>
                                        </div>	
                                    </body>
                                    <style>
                                    .formulario
                                    		{
                                    			background-color: rgba(68, 218, 235, 1);
                                    			border-style: dashed;
                                    			border-color: #fff;
                                    			margin-top: 1%;
                                    			padding: 10px 10px 10px 10px;
                                    			margin-left: 28%;
                                    			width: 50%;
                                    		}
                                    		.envia
                                    		{
                                    			color: #fff;
                                    			border-radius: 3px;
                                    			background-color: #f5338e;
                                    			font-family:  'Gloria Hallelujah', cursive;
                                    			border-style: none;
                                    			font-size: 1.2em;
                                    			width: 45%;
                                    			transition-duration: 0.6s;
                                    		}
                                    		.envia:hover
                                    		{
                                    			background-color: #fff;
                                    			color: #f5338e;
                                    		}
                                    		#resultado
                                    		{
                                    			margin-top: 2%;	
                                    			width: 100%;	
                                    		}
                                    		.resposta
                                    		{
                                    				color: #f5338e;
                                    				font-family:  'Gloria Hallelujah', cursive;
                                    				text-align: center;

                                    		}
                                    		#boxes
                                    		{
                                    			
                                    			margin-top: 5%;
                                    		}
                                    		#palpite
                                    		{
                                    			background-color: rgba(68, 218, 235, 1);
                                    			margin-left: 10%;
                                    			border-style: dashed;
                                    			border-width: 2px;
                                    			border-color: #fff;	
                                    		}
                                    		#dica
                                    		{
                                    			background-color: rgba(68, 218, 235, 1);
                                    			margin-left: 10%;
                                    			border-style: dashed;
                                    			border-width: 2px;
                                    			border-color: #fff;	
                                    		}
                                    		.titulo{
                                    			color: #fff;
                                    			text-align: center;
                                    			margin-top: 5%;
                                    			font-size: 50px;
                                    			font-family:  'Gloria Hallelujah', cursive;	
                                    		}
                                    		.subtitulo
                                    		{
                                    			color: #fff;
                                    			text-align: center;
                                    			margin-top: 2%;
                                    			font-family:  'Gloria Hallelujah', cursive;	
                                    		}
                                    		.subtitulo2
                                    		{
                                    			color: #fff;
                                    			text-align: left;
                                    			margin-top: 2%;
                                    			font-family:  'Gloria Hallelujah', cursive;	
                                    		}
                                    	.transparencia
                                    	{
                                    		float: left;
                                    		background-color: rgba(68, 218, 235, 0.3);
                                    		width: 100%;

                                    	}
                                    	body{
                                    		background-image: url(https://media.giphy.com/media/xUOxfjsW9fWPqEWouI/giphy.gif);
                                    	}
                                    	</style>
                                    </html>
                        """.trimIndent(), ContentType.Text.Html)
                    }else{
                        call.respondText(
                            """
                                <html>
                                    <head>
                                        <title>Guess What!</title>
                                        <meta charset="utf-8">
                                        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
                                        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
                                        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
                                        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
                                        <link href="https://fonts.googleapis.com/css?family=Annie+Use+Your+Telescope&display=swap" rel="stylesheet">
                                        <link href="https://fonts.googleapis.com/css?family=Gloria+Hallelujah&display=swap" rel="stylesheet">
                                    </head>
                                    <body>
                                        <div class="transparencia">	
                                            <header><h1 class="titulo">Guess What!</h1></header>
                                            <label id="resultado">
                                                <h2 class="resposta"><br> Fim de jogo! Ninguém conseguiu advinhar :( <br> Obrigado a todos pela participação! <br> A RESPOSTA ERA: ${resp.palavra}!!<br><b></b></h2>		
                                            </label>
                                            <div class="col-md-12">
                                                 <div id="boxes" class="row">
                                                        <div id="palpite" class="col-md-4">
                                                            <h2 class="subtitulo">Palpites: </h2>
                                                            <div>
                                                                <h4 class="subtitulo2"> ${listaDePalpites.toString()} </h4>
                                                            </div>
                                                            <h4 class="subtitulo">Contador de palpites: </h4>
                                                            <h5 class="subtitulo"> $palpites </h5>
                                                        </div>
                                                        <div id="dica" class="col-md-4">
                                                            <h2 class="subtitulo">Dicas:</h2>
                                                            <div>
                                                                <h4 class="subtitulo">${resp.dicas.component1()}<br>${resp.dicas.component2()}<br>${resp.dicas.component3()}<br></h4>
                                                            </div>
                                                        </div>
                                                 </div>
                                                 <div class="col-md-4">
                                                    <form class="formulario">
                                                        <div class="form-group">
                                                            <h5 class="subtitulo2">Nome:</h5>
                                                            <input class="form-control" type="text" name="nome">		
                                                        </div>
                                                        <div class="form-group">
                                                            <h5 class="subtitulo2">Palpite:</h5>
                                                            <input class="form-control" type="text" name="palpite">			
                                                        </div>
                                                        <div class="form-group">
                                                            <button class="envia">ENVIAR</button>
                                                        </div>		
                                                    </form>
                                                </div>
                                            </div>
                                            <footer style="margin-top: 10%;" class="subtitulo"> Desenvolvido por: Mateus Celestino, Bruno Cavalcanti, Felipe Steffano</footer>
                                        </div>	
                                    </body>
                                    <style>
                                    .formulario
                                    		{
                                    			background-color: rgba(68, 218, 235, 1);
                                    			border-style: dashed;
                                    			border-color: #fff;
                                    			margin-top: 1%;
                                    			padding: 10px 10px 10px 10px;
                                    			margin-left: 28%;
                                    			width: 50%;
                                    		}
                                    		.envia
                                    		{
                                    			color: #fff;
                                    			border-radius: 3px;
                                    			background-color: #f5338e;
                                    			font-family:  'Gloria Hallelujah', cursive;
                                    			border-style: none;
                                    			font-size: 1.2em;
                                    			width: 45%;
                                    			transition-duration: 0.6s;
                                    		}
                                    		.envia:hover
                                    		{
                                    			background-color: #fff;
                                    			color: #f5338e;
                                    		}
                                    		#resultado
                                    		{
                                    			margin-top: 2%;	
                                    			width: 100%;	
                                    		}
                                    		.resposta
                                    		{
                                    				color: #f5338e;
                                    				font-family:  'Gloria Hallelujah', cursive;
                                    				text-align: center;

                                    		}
                                    	
                                    		#boxes
                                    		{
                                    			
                                    			margin-top: 5%;
                                    		}
                                    		#palpite
                                    		{
                                    			background-color: rgba(68, 218, 235, 1);
                                    			margin-left: 10%;
                                    			border-style: dashed;
                                    			border-width: 2px;
                                    			border-color: #fff;	
                                    		}
                                    		#dica
                                    		{
                                    			background-color: rgba(68, 218, 235, 1);
                                    			margin-left: 10%;
                                    			border-style: dashed;
                                    			border-width: 2px;
                                    			border-color: #fff;	
                                    		}
                                    		.titulo{
                                    			color: #fff;
                                    			text-align: center;
                                    			margin-top: 5%;
                                    			font-size: 50px;
                                    			font-family:  'Gloria Hallelujah', cursive;	
                                    		}
                                    		.subtitulo
                                    		{
                                    			color: #fff;
                                    			text-align: center;
                                    			margin-top: 2%;
                                    			font-family:  'Gloria Hallelujah', cursive;	
                                    		}
                                    		.subtitulo2
                                    		{
                                    			color: #fff;
                                    			text-align: left;
                                    			margin-top: 2%;
                                    			font-family:  'Gloria Hallelujah', cursive;	
                                    		}
                                    	.transparencia
                                    	{
                                    		float: left;
                                    		background-color: rgba(68, 218, 235, 0.3);
                                    		width: 100%;

                                    	}
                                    	body{
                                    		background-image: url(https://media.giphy.com/media/xUOxfjsW9fWPqEWouI/giphy.gif);
                                    	
                                    	}
                                    	</style>
                                    </html>
                        """.trimIndent(), ContentType.Text.Html)
                    }
                }
                static("static") {
                    files("dados_estaticos")
                    files("build/js/packages/ServidorWeb/kotlin/")
            }
        }
    }.start(wait = true)
}