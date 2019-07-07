# Project 2: Project 2: Jukebox Hero

* Author: Robbie Gill
* Class: CS121 Section 035
* Semester: Summer 2019 

## Overview

The Jukebox Hero program will allow the user to open a music catalog file, then print stats about the catalog. Display the songs in the Catalog to the user, Search for songs in the catalog. 

## Compiling and Using
*Add catalog of songs in type **.csv** to directory containing jukeboxHero.java*


To compile, execute the following command in the main project directory:
```
$ javac jukeboxHero.java
```

Run the compiled class with the command:
```
$ java jukeboxHero
```
You will be prompted by a menu that displays:
```
*****************************
*      Program Menu         *
*****************************
(L)oad catalog
(S)earch catalog
(A)nalyse catalog
(P)rint catalog
(Q)uit

Please enter a command (press 'm' for Menu): 
```
When the program reads input from the user it will redirect the program to the corresponding selections avaliable.
open a music catalog file:
``` 
Please enter a command (press 'm' for Menu): L 
```
**Note: file names are case sensative**

print stats about the catalog (number of songs, number of artists, number of albums, total play time)
``` 
Please enter a command (press 'm' for Menu): A 
```
search for songs in the catalog:
``` 
Please enter a command (press 'm' for Menu): S 
```
display the entire catalog:
``` 
Please enter a command (press 'm' for Menu): P 
```

Quit:

```
Please enter a command (press 'm' for Menu):Q
```

## Sources used
https://docs.oracle.com/javase/tutorial/java/nutsandbolts/switch.html

https://stackoverflow.com/questions/8117781/does-every-method-in-main-class-have-to-be-static


