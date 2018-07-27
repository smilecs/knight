import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.gson
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.jackson.jackson
import io.ktor.request.receive
import io.ktor.request.receiveText
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import model.BoardModel
import model.UserBoard
import java.text.DateFormat
import java.time.Duration

fun main(args: Array<String>) {
    val server = embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        install(ContentNegotiation){
            gson {
                setDateFormat(DateFormat.LONG)
                setPrettyPrinting()
            }
        }
        install(DefaultHeaders)
        install(CORS){
            method(HttpMethod.Options)
            header(HttpHeaders.XForwardedProto)
            anyHost()
            host("my-host")
            // host("my-host:80")
            // host("my-host", subDomains = listOf("www"))
            // host("my-host", schemes = listOf("http", "https"))
            allowCredentials = true
            maxAge = Duration.ofDays(1)
        }
        routing {
            post("/comment", {
               // println(call.receive<UserBoard>().body)
                val user = call.receive<UserBoard>()
                val userBoard = BoardModel().addNewPost(user)
                //call.respond(mapOf("data" to userBoard))
                call.respond(userBoard)
            })
            get("/comment") {
                call.respondText("HELLO WORLD!")
            }
        }
    }
    server.start(wait = true)
}