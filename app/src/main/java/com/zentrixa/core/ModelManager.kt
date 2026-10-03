package com.zentrixa.core

import android.content.Context
import java.io.File

class ModelManager(context: Context) {
    private val dir = File(context.filesDir, "models").apply { mkdirs() }
    fun listModels(): List<File> =
        dir.listFiles { f -> f.isFile && f.extension.equals("gguf", true) }?.sortedBy { it.name } ?: emptyList()
    fun modelsDirectory(): File = dir
}
