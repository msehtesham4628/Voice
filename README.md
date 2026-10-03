# Zentrixa

A self-hosted, privacy-first personal AI assistant for Android.

Zentrixa is being built as a JARVIS-style assistant: voice-first, local AI, local memory, phone skills, and a modular tool system. It is intentionally independent of Gemini, OpenAI, and hosted AI APIs.

## Current repository

- Android application module
- Local assistant core
- Persistent local conversation memory
- Skill router
- Android speech recognition and text-to-speech
- Android VoiceInteractionService shell
- CallScreeningService and InCallService integration points
- Role request helpers
- Local inference interface for a GGUF/on-device runtime

## Architecture

Microphone -> speech -> Zentrixa core -> local model/skills -> memory/tools -> speech output.

The local model layer is intentionally replaceable. llama.cpp supports Android and GGUF models; the repository does not contain large model weights.

## Build

Open the repository in Android Studio, allow Gradle sync, then run the app configuration on an Android device.

The project targets API 37 and uses Java 17.

## Roadmap

1. Integrate a real on-device GGUF inference runtime.
2. Add wake-word/background voice behavior.
3. Add contacts, notifications, messaging, media and app-launch skills.
4. Add permission-aware call workflows.
5. Add settings/model management UI.
6. Add tests and performance profiles for different phone RAM tiers.

## Privacy

Zentrixa's intended AI path is on-device. Sensitive permissions are not silently granted, and model weights are never committed to this repository.
