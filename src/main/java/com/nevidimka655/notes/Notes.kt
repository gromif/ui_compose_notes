package com.nevidimka655.notes

object Notes {

    data class Item(
        val id: Long = 0,
        val title: String? = null,
        val textPreview: String? = null,
        val creationTime: String? = null
    )

}