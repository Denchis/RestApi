package com.bftcom.person.repository

import com.bftcom.person.models.People

interface PersonRepository {
    fun getAll(): List<People>

    fun findById(id: Int): People?

    fun findByName(name: String): List<People>

    fun findByLastName(lastName: String): List<People>

    fun create(name: String, lastName: String):Int

    fun update(id:Int, name: String, lastName: String)

    fun deleteById(id: Int)

    fun deleteAll()
}