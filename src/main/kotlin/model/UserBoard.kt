package model

import org.bson.codecs.pojo.annotations.BsonId

class UserBoard {
    @BsonId var _id: String? = null
    var body = ""
    var comment = mutableListOf<Comment>()
}