Provides Geyser API functions for CommandHelper

## Functions
boolean **geyser_connected([player])** Returns whether a player is connected using a Bedrock client.

string **geyser_input_mode([player])** Returns the player's input mode.  
Will be null if player is not connected using a Bedrock client.  
Otherwise will return KEYBOARD_MOUSE, TOUCH, CONTROLLER, VR, or UNKNOWN.

string **geyser_xuid([player])** Returns the player's XUID, or null.

string **geyser_name([player])** Returns the player's gamertag, or null.

string **geyser_username_prefix()** Returns the username prefix defined in floodgate config, or null.