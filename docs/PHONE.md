# Phone control

Zentrixa uses Android intents, permissions, roles, and supported telecom APIs rather than hidden unrestricted control.

Current actions:
- Open Android settings
- Open an installed app by package name
- Open the dialer for a number
- Open an SMS composer

Call screening and in-call integration points are present, but a production call assistant must respect Android telecom roles, device behavior, carrier behavior, and applicable law.

Sensitive actions remain explicit until the permission and confirmation system is complete.
