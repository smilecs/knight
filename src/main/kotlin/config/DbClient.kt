package config

import com.mongodb.client.MongoCollection
import model.BoardModel
import model.UserBoard
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection

object DbClient {
    const val BoardModelCol = "BoardModelCol"
    val url:String = if(System.getenv("MONGOLAB_URI").isNotEmpty()) System.getenv("JDBC_DATABASE_URL") else ""

    fun BoardModelCol(): MongoCollection<UserBoard> {
        return KMongo.createClient(url).run {
            getDatabase(BoardModelCol).getCollection()
        }
    }
}