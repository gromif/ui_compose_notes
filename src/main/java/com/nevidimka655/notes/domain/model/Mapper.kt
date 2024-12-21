package com.nevidimka655.notes.domain.model

interface Mapper<T, R> {

    operator fun invoke(item: T): R

}