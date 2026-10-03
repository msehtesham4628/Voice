from datetime import datetime


class TimeSkill:
    name = "time"

    def can_handle(self, text: str) -> bool:
        t = text.lower()
        return "what time" in t or t == "time"

    def run(self, text: str) -> str:
        return datetime.now().strftime("It is %I:%M %p.")


class GreetingSkill:
    name = "greeting"

    def can_handle(self, text: str) -> bool:
        return text.lower().strip() in {"hello", "hi", "hey zentrixa"}

    def run(self, text: str) -> str:
        return "Hello. Zentrixa is online."
