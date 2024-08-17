package com.example.podplay.domain.mapper

interface IMapper<From, To> {
    fun map(input: From): To
}

