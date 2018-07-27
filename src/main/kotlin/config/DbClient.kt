package config

import com.mongodb.client.MongoCollection
import model.BoardModel
import model.UserBoard
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection

object DbClient {
    const val BoardModelCol = "BoardModelCol"

    fun BoardModelCol(): MongoCollection<UserBoard> {
        return KMongo.createClient().run {
            getDatabase(BoardModelCol).getCollection()
        }
    }
}