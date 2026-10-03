from dataclasses import dataclass


@dataclass
class ZentrixaConfig:
    name: str = "Zentrixa"
    language: str = "en-IN"
    wake_word: str = "zentrixa"
    memory_enabled: bool = True
    confirmation_required: bool = True
