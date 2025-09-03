package com.moontech.archetype

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<ArchetypeServiceApplication>().with(TestcontainersConfiguration::class).run(*args)
}
