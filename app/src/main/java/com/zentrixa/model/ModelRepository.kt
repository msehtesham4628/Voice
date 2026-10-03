package com.zentrixa.model
import android.content.Context
import android.net.Uri
import java.io.File
class ModelRepository(context: Context) {
    private val dir = File(context.filesDir, "models").apply { mkdirs() }
    fun import(uri: Uri, resolver: android.content.ContentResolver): File {
        val name = resolver.query(uri, null, null, null, null)?.use { c ->
            val index = c.getColumnIndex(android.provider.OpenableColumns.DISPLAY_NAME)
            if (c.moveToFirst() && index >= 0) c.getString(index) else null
        } ?: "zentrixa-model.gguf"
        val destination = File(dir, name.replace(Regex("[^A-Za-z0-9._-]"), "_"))
        resolver.openInputStream(uri).use { input ->
            requireNotNull(input)
            destination.outputStream().use { output -> input.copyTo(output) }
        }
        return destination
    }
    fun list(): List<File> = dir.listFiles { f -> f.extension.equals("gguf", true) }?.toList() ?: emptyList()
}