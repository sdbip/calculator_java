# Calculator kata

This repository illustrates a few ways to build a calculator using TDD.
Please see the various branches for different runs.

---

There is no GUI in this code, but imagine a digit pad, some operator
buttons (plus, minus etc) and a display.

Pressing digits should enter a number into the display, and pressing an
operator button should perform a calculation and replace the content of
the display.

- Predecence applies (multiplication and division is calculated before
  addition and subtraction).
- Locale – some locales use a different decimal separator.
