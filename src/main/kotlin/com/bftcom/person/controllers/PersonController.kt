package com.bftcom.person.controllers

import com.bftcom.person.models.Person
import com.bftcom.person.services.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/person")
class PersonController(
    private val personService: PersonService,
){

    @GetMapping
    fun getAll(): List<Person> = personService.getAll()

    @GetMapping("/id/{id}")
    fun getById(@PathVariable id: Int): Person = personService.getById(id)

    @GetMapping("/name/{name}")
    fun getByName(@PathVariable name:String):List<Person> = personService.getByName(name)

    @GetMapping("/lastName/{lastName}")
    fun getByLastName(@PathVariable lastName: String):List<Person> = personService.getByLastName(lastName)

    @PostMapping("/add")
    fun create(@RequestBody person: Person): Int = personService.create(person)

    @PutMapping("/update/{id}")
    fun update(@PathVariable id:Int, @RequestBody person: Person){
        personService.update(id,person)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteById(@PathVariable id: Int){
        personService.deleteById(id)
    }

    @DeleteMapping("/deleteAll")
    fun deleteAll(){
        personService.deleteAll()
    }
}
