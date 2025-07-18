# Crimenet

Crimenet is a Java program that simulates a criminal investigation network.

Given a list of **suspects**, track their **phone calls and SMS messages**, and see how they are **connected** to each other.

It also includes a simple **GUI (graphical user interface)** to search for suspects and view their details.

---

## 🔍 What it Does

- Given a list of suspects, each with:
  - Name
  - Code name
  - Phone numbers

- Track communications between phone numbers:
  - Phone calls (with duration)
  - SMS messages (with text content)

- Automatically:
  - Links suspects based on who they communicate with
  - Detects suspicious SMS messages with keywords like:
    - **Bomb**
    - **Attack**
    - **Gun**
    - **Explosives**
  - Shows:
    - Suspect with the most connections
    - Longest phone call between two numbers
    - Suspects that are likely connected through others (suggested partners)
