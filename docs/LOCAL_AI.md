# Local AI integration

Zentrixa uses a local-model boundary and does not require a Gemini or OpenAI credential.

The recommended native runtime is llama.cpp with GGUF. Its current build documentation lists Android arm64-v8a as a supported target and provides an Android NDK CMake build.

Production path:
1. Build llama.cpp as native Android libraries with the Android NDK.
2. Expose a small JNI bridge implementing LocalInferenceEngine.
3. Import a GGUF model into app-private storage.
4. Load it only when enabled.
5. Generate responses on-device.
6. Unload it when memory pressure requires it.

Do not commit model weights to Git.

Start with a small quantized instruct model appropriate to the target phone RAM. Context size and model size affect memory pressure, so test on the actual device.
