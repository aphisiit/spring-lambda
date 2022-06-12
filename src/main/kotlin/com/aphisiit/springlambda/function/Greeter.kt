package com.aphisiit.springlambda.function

import java.util.function.Function

class Greeter : Function<String, String> {
	override fun apply(t: String): String {
		return "Hello $t, and welcome to Spring Cloud Function!!!"
	}
}
