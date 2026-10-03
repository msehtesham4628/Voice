package com.zentrixa.core

interface LocalInferenceEngine {
    fun load(modelPath: String): Boolean
    fun generate(prompt: String, context: List<Pair<String, String>> = emptyList()): String
    fun unload()
    fun isLoaded(): Boolean
}
