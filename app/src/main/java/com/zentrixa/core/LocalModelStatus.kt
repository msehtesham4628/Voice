package com.zentrixa.core
data class LocalModelStatus(val loaded: Boolean = false, val modelName: String? = null, val message: String = "No local model loaded")