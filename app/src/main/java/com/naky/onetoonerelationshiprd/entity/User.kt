package com.naky.onetoonerelationshiprd.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation


@Entity
data class User(
    @PrimaryKey
    val userId : Int,
    val name : String,
    val age : Int
)

@Entity
data class Library(
    @PrimaryKey
    val id : Int,
    val title : String,
    val userOwnerId : Int
)

//one to one relationship
data class UserAndLibrarry(
    @Embedded val user : User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userOwnerId"
    )
    val library: Library
)
