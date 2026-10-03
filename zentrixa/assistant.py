from dataclasses import dataclass, field
from typing import Any, Protocol


class ModelAdapter(Protocol):
    def generate(self, prompt: str, context: list[dict[str, str]]) -> str:
        ...


class Skill(Protocol):
    name: str

    def can_handle(self, text: str) -> bool:
        ...

    def run(self, text: str) -> str:
        ...


@dataclass
class Zentrixa:
    model: ModelAdapter | None = None
    skills: list[Skill] = field(default_factory=list)
    history: list[dict[str, str]] = field(default_factory=list)

    def register_skill(self, skill: Skill) -> None:
        self.skills.append(skill)

    def handle(self, text: str) -> str:
        text = text.strip()
        if not text:
            return "I didn't hear anything."

        for skill in self.skills:
            if skill.can_handle(text):
                result = skill.run(text)
                self.history.append({"role": "user", "content": text})
                self.history.append({"role": "assistant", "content": result})
                return result

        if self.model is None:
            return (
                "My local AI model is not connected yet. "
                "The Zentrixa core is ready for a local model."
            )

        result = self.model.generate(text, self.history)
        self.history.append({"role": "user", "content": text})
        self.history.append({"role": "assistant", "content": result})
        return result
