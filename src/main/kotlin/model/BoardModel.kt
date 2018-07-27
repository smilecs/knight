package model

import config.DbClient
import kotlinx.coroutines.experimental.async

class BoardModel {

    fun addNewPost(userBoard: UserBoard): List<UserBoard>  {
        upsertPost(userBoard)
        return getPosts()
    }

    private fun upsertPost(userBoard: UserBoard) = DbClient.BoardModelCol().insertOne(userBoard)

    fun getPosts() = DbClient.BoardModelCol().find().toList()

}