# Zentrixa implementation status

## Implemented

- Android application shell
- Local conversation memory
- Modular skill routing
- Voice input through Android speech recognition
- Local text-to-speech output
- VoiceInteractionService shell
- CallScreeningService shell
- InCallService integration point
- Role request helpers
- Basic phone actions
- Local inference interface
- No Gemini/OpenAI API dependency

## Next production-critical work

### Local LLM
Integrate llama.cpp or another native on-device GGUF runtime through LocalInferenceEngine. Model weights must remain outside Git.

### Voice assistant
Add a proper foreground voice pipeline, wake-word detector, interruption handling, and lifecycle management. Android's selected VoiceInteractionService can be kept running by the system for hotword-driven interactions. 

### Phone automation
Implement each capability through the Android permission/role appropriate to the action. Do not assume an ordinary application can silently control every phone function.

### Calls
Call screening and default-dialer capabilities are separate Android roles. A CallScreeningService can screen calls, while broader call control requires the appropriate telecom role and system-supported APIs.

## Definition of done for v1

- A real local GGUF model generates responses on-device.
- Voice conversation works without a cloud AI provider.
- Memory survives app restarts.
- User can enable/disable individual skills.
- Phone actions require appropriate Android permissions/roles.
- Calls are handled only through supported Android telecom APIs.
- App has automated unit/instrumentation tests.
