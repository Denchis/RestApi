package com.bftcom.person.services

import com.bftcom.person.models.Person
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*

interface PersonService {
    fun getAll(): List<Person>

    fun getById(id: Int): Person

    fun getByName(name: String): List<Person>

    fun getByLastName(lastName: String): List<Person>

    fun create(person: Person): Int

    fun update(id:Int, person: Person)

    fun deleteById(id: Int)

    fun deleteAll()
}