package com.bftcom.person.repository

import com.bftcom.person.models.People
import com.bftcom.person.models.Person
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.RequestEntity.get
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

@SpringBootTest
internal class PersonRepositoryImplTest {
    @Autowired
    lateinit var personRepository: PersonRepository

    @Test
    fun getAll() {
        personRepository.deleteAll()
        val id: Int = personRepository.create("Grisha","Gruzd")
        assertEquals(id,personRepository.getAll().get(0).id)
        assertEquals("Grisha",personRepository.getAll().get(0).name)
        assertEquals("Gruzd",personRepository.getAll().get(0).lastName)
    }

    @Test
    fun findById() {
        val id: Int = personRepository.create("Grisha","Gruzd")
        assertNotNull(personRepository.findById(id))
        val people:People? = personRepository.findById(id)
        assertEquals(id,people?.id)
        assertEquals("Grisha", people?.name)
        assertEquals("Gruzd", people?.lastName)
    }

    @Test
    fun findByName() {
        personRepository.deleteAll()
        val id: Int = personRepository.create("Grisha","Gruzd")
        assertNotNull(personRepository.findByName("Grisha"))
        val people:People = personRepository.findByName("Grisha").get(0)
        assertEquals(id,people.id)
        assertEquals("Grisha", people.name)
        assertEquals("Gruzd", people.lastName)
    }

    @Test
    fun findByLastName() {
        personRepository.deleteAll()
        val id: Int = personRepository.create("Grisha","Gruzd")
        assertNotNull(personRepository.findByName("Grisha"))
        val people:People = personRepository.findByLastName("Grisha").get(0)
        assertEquals(id,people.id)
        assertEquals("Grisha", people.name)
        assertEquals("Gruzd", people.lastName)
    }

    @Test
    fun create() {
        val id: Int = personRepository.create("Grisha","Gruzd")
        val people:People? = personRepository.findById(id)
        assertEquals(id,people?.id)
        assertEquals("Grisha", people?.name)
        assertEquals("Gruzd", people?.lastName)
    }

    @Test
    fun update() {
        val id: Int = personRepository.create("Grisha","Gruzd")
        personRepository.update(id,"Den","Yakovlev")
        val people:People? = personRepository.findById(id)
        assertEquals("Den", people?.name)
        assertEquals("Yakovlev", people?.lastName)
    }

    @Test
    fun deleteById() {
        val id: Int = personRepository.create("Grisha","Gruzd")
        personRepository.deleteById(id)
        assertNull(personRepository.findById(id))
    }
}