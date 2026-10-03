from zentrixa.assistant import Zentrixa
from zentrixa.skills import GreetingSkill, TimeSkill


def main() -> None:
    assistant = Zentrixa()
    assistant.register_skill(GreetingSkill())
    assistant.register_skill(TimeSkill())

    print("Zentrixa local core — type 'exit' to stop.")
    while True:
        try:
            text = input("You: ")
        except (EOFError, KeyboardInterrupt):
            print()
            break

        if text.lower().strip() in {"exit", "quit"}:
            break

        print(f"Zentrixa: {assistant.handle(text)}")


if __name__ == "__main__":
    main()
