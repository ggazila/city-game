Application presents an implementation of "game in city".

The game has next rules:

1. The system offers the city

2. The player must name the word on the last letter of the city.

3. The system responds to the last letter of the Player's town.

To check, the following URLs are available:

* GET /begin - The system offers the word

* GET /next?word=$word - The player says his word ($word) to the system. The system sends its word in return.
* POST /end - The system sends the text "Thank you for playing."

