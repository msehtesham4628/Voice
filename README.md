# Zentrixa

A self-hosted, privacy-first personal AI assistant for Android.

Zentrixa is designed to become a JARVIS-style assistant that can understand voice, remember context, and perform phone actions — without Gemini, OpenAI, or other cloud AI providers.

## Project goals

- Local AI inference
- Voice-first interaction
- Persistent local memory
- Modular skills/tools
- Android-first phone control
- Permission-aware actions
- Offline-first design
- No cloud AI dependency

## Architecture

```
Microphone
   ↓
Speech-to-Text
   ↓
Zentrixa Core
   ├── Local LLM
   ├── Memory
   ├── Intent / tool routing
   └── Safety & permission checks
   ↓
Skills
   ├── Apps
   ├── Contacts
   ├── Messages
   ├── Notifications
   ├── Media
   ├── Alarms
   └── Calls (Android capabilities permitting)
   ↓
Text-to-Speech
   ↓
Speaker
```

## Development

The repository starts with the platform-independent assistant core. Android integration will be added as a separate layer so the AI engine remains testable and reusable.

See [ARCHITECTURE.md](ARCHITECTURE.md) for the development roadmap.
