package com.bftcom.person.services

import com.bftcom.person.models.People
import com.bftcom.person.models.Person
import com.bftcom.person.repository.PersonRepository
import org.springframework.stereotype.Service

@Service
class PersonServiceImpl(
    private val personRepository: PersonRepository
) : PersonService {
    override fun getAll(): List<Person> = personRepository.getAll().map { it.toPerson() }

    override fun getById(id: Int): Person =
        personRepository.findById(id)
            ?.toPerson()
            ?: throw RuntimeException("Person with id = $id not found")

    override fun getByName(name: String): List<Person> =
        personRepository.findByName(name).map {it.toPerson()}

    override fun getByLastName(lastName: String): List<Person> =
        personRepository.findByLastName(lastName).map { it.toPerson() }

    override fun create(person: Person):Int  =
        personRepository.create(person.name,person.lastName)

    override fun update(id: Int, person: Person) {
        personRepository.update(id,person.name,person.lastName)
    }

    override fun deleteById(id: Int) {
        personRepository.deleteById(id)
    }

    override fun deleteAll() {
        personRepository.deleteAll()
    }

    private fun People.toPerson() = Person(
        id = id,
        name = name,
        lastName = lastName,
    )
}