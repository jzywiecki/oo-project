# oo-project

A large two-person project<br/>
Technologies: Java 17, JavaFX, JUnit<br/>
Tools: SceneBuilder, IntelliJ<br/>
Project description in Polish: https://github.com/apohllo/obiektowe-lab/blob/master/proj1/Readme.md

## Short description:

### Project Goal:

Let's create a game! But it won't be a game that we play ourselves. Instead, it will be a world that evolves before our eyes! We will create a environment of steppes and jungles with animals that run, forage in the undergrowth, eat, and reproduce. And after millions of years, we will see that they have evolved into different species!

### World Setup:

Our game world is quite simple. It consists of a regular, rectangular area divided into square fields. Most of the world is covered in steppes, where there are few plants that provide food for animals. However, some areas are covered in jungle, where plants grow much faster. Plants will grow in random locations, but their concentration will be higher in the jungle than in the steppe.

### Animal Anatomy:

We must track several characteristics of all objects. Firstly, both for plants and those who eat them, we need to know the x and y coordinates. They indicate where a given animal or plant is on the map.

We must also know how much energy each animal has. It's a game of survival, so if an animal fails to obtain enough food, it will starve and die... Energy tells us how many days of functioning are left for each animal. It must find more food before its supply runs out.

We must also remember which direction the animal is facing. This is important because every day it will move on the map in that direction. There are eight different possible positions and the same number of possible rotations. Rotation 0 means that the animal does not change its orientation, rotation 1 means that the animal rotates by 45°, 2 by 90°, etc. For example, if an animal was facing north and the rotation was 1, the animal is now facing northeast.

Finally, we must also store the animal's genes. Each animal has N genes, each of which is a number from 0 to 7. These genes describe (in a very simplified way) the behavioral pattern of a given creature. The existence of our animals has a cyclical nature. Each of them stores information about which part of its genome it will use the next day. During each movement, the animal first changes its position, rotating according to the currently active gene, and then moves one square in the designated direction. Then, the gene is deactivated, and the gene to its right is activated (it will control the animal the next day). When the genes run out, activation returns to the beginning of the genome.

### Available variants:

MAPS: GlobeMap, HellishMap;
FORESTATION: ForestedEquators, ToxicCorpses;
REPRODUCTION: FullRandomness, SlightCorrection;
GENE BEHAVIOR: BitOfMadness, FullPredestination;


### Parameters:
- Height of the map
- Width of the map
- Variant of the map
- Number of grass at the start
- Energy gained from eating grass
- Number of grass growing each day
- Variant of forestation
- Number of animals at the start
- Starting energy of animals
- Minimum energy needed to create offspring
- Starting energy of animals
- Minimum mutations
- Maximum mutations
- Variant of mutations
- Length of genome
- Variant of gene behavior
- Daily cost of living for animals

### Screenshots:

<img width="501" alt="Zrzut ekranu 2023-04-2 o 01 28 38" src="https://user-images.githubusercontent.com/105950890/229322703-b3d48b31-f6f5-46eb-aaf0-3b070e10f39c.png">

<img width="963" alt="Zrzut ekranu 2023-04-2 o 01 29 03" src="https://user-images.githubusercontent.com/105950890/229322707-25221e68-435f-4ed1-ab4e-f04cf01c6a1a.png">

### Unfinished:
- Single animal stats
