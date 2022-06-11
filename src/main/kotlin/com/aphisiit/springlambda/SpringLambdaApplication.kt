package com.aphisiit.springlambda

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.function.Function

@SpringBootApplication
class SpringLambdaApplication {

	@Bean
	fun uppercase(): Function<String, String> {
		return Function<String, String> { value -> value.uppercase() }
	}

	@Bean
	fun reverseString(): Function<String, String> {
		return Function<String, String> { value -> value.reversed() }
	}

}

fun main(args: Array<String>) {
	runApplication<SpringLambdaApplication>(*args)
}
