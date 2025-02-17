package com.nevidimka655.domain.notes.model

sealed class AeadMode {

    object None: AeadMode()

    data class Template(
        val id: Int,
        val name: String,
    ): AeadMode()

}