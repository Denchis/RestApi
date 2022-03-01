package com.bftcom.person.repository

import com.bftcom.person.models.People
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.support.GeneratedKeyHolder

@Repository
class PersonRepositoryImpl(
    private val jdbcTemplate: NamedParameterJdbcTemplate,
) : PersonRepository {
    override fun getAll(): List<People> =
        jdbcTemplate.query("select * from Person", ROW_MAPPER)

    override fun findById(id: Int): People? =
        jdbcTemplate.query("select * from Person where id = :id",
            mapOf(
                "id" to id,
            ),
            ROW_MAPPER,
        ).firstOrNull()

    override fun findByName(name: String): List<People> =
        jdbcTemplate.query("select * from Person where name = :name",
            mapOf(
                "name" to name,
            ),
            ROW_MAPPER,
        )

    override fun findByLastName(lastName: String): List<People> =
        jdbcTemplate.query("select * from Person where lastName = :lastName",
            mapOf(
                "lastName" to lastName,
            ),
            ROW_MAPPER,
        )

    override fun create(name: String, lastName: String): Int {
        val keyHolder = GeneratedKeyHolder()
        jdbcTemplate.update("insert into Person(name, lastName) values (:name, :lastName)",
            MapSqlParameterSource(
                mapOf(
                    "name" to name,
                    "lastName" to lastName,
                )
            ),
            keyHolder,
            listOf("id").toTypedArray()
        )
        return keyHolder.keys?.getValue("id") as Int
    }

    override fun update(id: Int, name: String, lastName: String) {
        jdbcTemplate.update("update Person set name = :name, lastName = :lastName where id = :id",
            mapOf(
                "id" to id,
                "name" to name,
                "lastName" to lastName,
            )
        )
    }

    override fun deleteById(id: Int) {
        jdbcTemplate.update("delete from Person where id = :id",
            mapOf(
                "id" to id,
            )
        )
    }

    override fun deleteAll() {
        val id: Int = 0
        jdbcTemplate.update("delete from Person",
            mapOf(
            "id" to id,
            )
        )
    }


    companion object{
        val ROW_MAPPER = RowMapper<People>{ rs, _ ->
            People(
                id = rs.getInt("id"),
                name = rs.getString("name"),
                lastName = rs.getString("lastName"),
            )
        }
    }
}