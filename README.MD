# IBAN VALIDATOR

This is an IBAN validator for a job interview
It can validate a String or a file containing IBAN's each in a new line.

The validation is done in a few steps:
1) IBAN format is checked.
2) IBAN country code and it's length per country is checked.
3) IBAN is converted to an integer, mod-97 operation performed. If the IBAN is valid, the remainder equals 1.


Application runs on 8080 port, everything else - self explanatory

References - https://en.wikipedia.org/wiki/International_Bank_Account_Number
All IBAN country codes are from wiki page.
