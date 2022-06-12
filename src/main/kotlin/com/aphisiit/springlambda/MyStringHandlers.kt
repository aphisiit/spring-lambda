package com.aphisiit.springlambda

import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler

class MyStringHandlers : SpringBootRequestHandler<String, String>() {
}
