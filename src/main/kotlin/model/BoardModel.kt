package model

import config.DbClient
import org.litote.kmongo.*
import org.litote.kmongo.MongoOperator.*

class BoardModel {

    fun addNewPost(userBoard: UserBoard): List<UserBoard> {
        upsertPost(userBoard)
        return getPosts()
    }

    fun newComment(id: String, comment: Comment): List<UserBoard> {
        println("data " + comment.json)
        return DbClient.BoardModelCol().run {
            updateOneById(id, "{$push: {'comment':${comment.json}}}")
            find().toList()
        }

    }

    private fun upsertPost(userBoard: UserBoard) = DbClient.BoardModelCol().insertOne(userBoard)

    fun getPosts() = DbClient.BoardModelCol().find().toList()

}