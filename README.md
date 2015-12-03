# joGdxExtend
A LibGdx library extension by jojpeg

# SpriteSet
A simple class to display a SpriteSet with basic methods like: getFrame(int id), 

# AnimatedSpriteSet
Extends SpriteSet with animation methods

# AseSprite
Parses a png with a json file generated from an Aseprite Document and converting it to an AnimatedSpriteSet.
You have to export the json with the following command: 
aseprite -b --list-tags player.ase --format json-array --data player.json 