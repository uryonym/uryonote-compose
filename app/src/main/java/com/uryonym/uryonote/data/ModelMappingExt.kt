package com.uryonym.uryonote.data

import com.uryonym.uryonote.data.local.Note
import com.uryonym.uryonote.data.network.NetworkNote

fun NetworkNote.toLocal() = Note(
    id = this.id,
    title = this.title,
    content = this.content
)

fun List<NetworkNote>.toLocal() = map { it.toLocal() }

fun Note.toNetwork() = NetworkNote(
    id = this.id,
    title = this.title,
    content = this.content
)

fun List<Note>.toNetwork() = map { it.toNetwork() }
