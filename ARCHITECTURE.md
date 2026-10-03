# Zentrixa Architecture

## Principles

1. **Local first:** AI inference and memory should run on the user's device or hardware they control.
2. **Provider independent:** No Gemini/OpenAI dependency.
3. **Modular:** AI, voice, memory, and Android skills are separate modules.
4. **Permission aware:** Sensitive phone actions must respect Android permissions and user confirmation.
5. **Testable:** Core reasoning and skills must be testable without a physical phone.

## Planned layers

### 1. Core
- Conversation state
- Personality
- Intent extraction
- Tool selection
- Response generation

### 2. Local AI
The first implementation will support a local model through a replaceable inference adapter. The adapter will not contain provider-specific business logic.

### 3. Memory
- Short-term conversation context
- Long-term user preferences
- Local SQLite storage
- Explicit memory controls

### 4. Voice
- Local speech-to-text
- Wake-word detection
- Local text-to-speech
- Barge-in/interruption support

### 5. Android
- Foreground/background behavior within Android rules
- Accessibility integration where appropriate
- Notifications
- Contacts
- Messaging
- Media
- Alarms
- Phone/call integration where Android and carrier capabilities allow it

## Roadmap

### Phase 1 — Core
- [x] Repository foundation
- [ ] Assistant loop
- [ ] Personality configuration
- [ ] Skill registry
- [ ] Local memory

### Phase 2 — Local voice
- [ ] Speech-to-text adapter
- [ ] Text-to-speech adapter
- [ ] Wake word

### Phase 3 — Android
- [ ] Android project
- [ ] Permissions
- [ ] App launcher
- [ ] Notification reader
- [ ] Contacts
- [ ] Messaging
- [ ] Media controls

### Phase 4 — Calls
- [ ] Call state handling
- [ ] Assistant call UI
- [ ] Call actions supported by the target Android version/device
- [ ] Consent-aware call recording/transcription only where permitted

### Phase 5 — Advanced skills
- [ ] Web automation
- [ ] PC control
- [ ] Smart-home integration
- [ ] Cross-device synchronization

## Important limitation

A self-hosted assistant can control only capabilities exposed by the operating system and granted by the user. Android does not provide unrestricted access to every phone function, so Zentrixa will use supported APIs/permissions and transparent user controls.
