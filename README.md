# GdxExtend
A LibGdx library extension by jojpeg

## SpriteSet
A simple class to display a SpriteSet with basic methods like:  
*getFrame(int id)* 

## AnimatedSpriteSet
Extends SpriteSet with animation methods like:  
*addAnimation(String name, int from, int to, float speed)*  
*setAnimation(String name)*  
*getCurrentFrame()*
*more to come*

## AseSprite
Parses a png with a json file generated from an [Aseprite](http://www.aseprite.org/) Document and converting it to an AnimatedSpriteSet.
With v1.1.1 You have to export the json via the following command:   
`aseprite -b --list-tags player.ase --format json-array --data player.json` 