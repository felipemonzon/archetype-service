package com.moontech.archetype

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ArchetypeServiceApplication

fun main(args: Array<String>) {
	runApplication<ArchetypeServiceApplication>(*args)
}
