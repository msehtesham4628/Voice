# Local AI model

Zentrixa has no Gemini, OpenAI, or hosted AI dependency.

The Android app keeps model inference behind a replaceable local runtime boundary. A production build should use a GGUF model through llama.cpp or another on-device runtime and store model weights in app-private storage.

Do not commit multi-gigabyte model weights to Git.

llama.cpp documents Android/NDK builds and GGUF model execution. The selected model should be sized for the target phone's RAM and thermals.
