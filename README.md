# Tarea_1
## Instrucciones
### Realizar ataques:
Para realizar un ataque de un **personaje_1** a un **personaje_2**, se debe usar el método _attackCharacter_ el cual recibe un _**"Character"**_ (oponente) y un _**"String"**_ ("Defensa" o " Evasión"), donde la primera variable determina quien es la victima del ataque y la segunda la reacción que toma frente al ataque.

De esta forma si queremos ver al **personaje_1** atacando a un **personaje_2** que se _"defiende"_, entonces se debe escribir:

_**personaje_1**.attackCharacter(**personaje_2**, "Defensa");_

Para aplicar el caso donde **personaje_2** trata de _"evadir"_ el ataque, basta reemplazar la segunda variable por _"Evasión"_. Luego si se aplica el método _getCurrentHP()_ en la victima del ataque, se podrá apreciar que tanto daño recibió del ataque.

### Recibir ataques en el caso de defender y esquivar:
En caso de recibir un ataque de un **personaje_1**, sí se quiere ver cuanto daño reduce la tirada de defensa de un **personaje_2**, invocamos al método _"defenseAction"_, el cual toma 2 variables _**int**_ ("ataque" y "defensa"), que corresponden a los resultados de las respectivas tiradas de cada uno de los personajes sumados a sus bonos de Stat.

_**int ataque** = roll() + **personaje_1**.getAtk();_ [Donde roll() es el método que "lanza" un dado de 6 caras, obteniendo así resultados del 1 al 6]
_**int defensa** = roll() + **personaje_2**.getDef();_

Una vez se tienen las variables, basta tomar cualquiera de los personajes y aplicar el método en cuestión.

**int** dañoRealizado = **personaje_1**.defenseAction(**ataque**, **defensa**);

o bien,

**int** dañoRecibido = **personaje_2**.defenseAction(**ataque**, **defensa**);

dañoRealizado == dañoRecibido >= 1

Para el caso donde se ha elegido hacer una tirada de evasión, se invoca al método _"evadeAction"_, que también toma la tirada de ataque como variable, y la otra es la tirada de evasión.

**evasión** = roll() + **personaje_2**.getEvd();

**int** dañoRecibido = **personaje_2**.evadeAction( **ataque**, **defensa**);

### Aumentar las victorias de una unidad para los casos de pelear con otra unidad:
Para realizar este tipo de casos, suponemos que un **player** reduce los puntos de vida (_"HP"_) de un **oponente** a 0, una vez hecho eso, implementamos el método _"winVictory"_ el cual solo esta disponible para la subclase _"Player"_. El resultado depende de la subclase de _"Character"_ **oponente** que se usa como variable, sí es _"WildUnit"_, aumenta en 1 PV, sí es _"Player"_ aumenta en 2 PV y en 3PV sí es un _"Boss"_.

_**player**.winVictory(**WildUnit**) == 1_
_**player**.winVictory(**Player**) == 2_
_**player**.winVictory(**Boss**) == 3_

### Cambiar el número de estrellas tras un ataque:
Siguiendo la misma idea que en la parte anterior, suponemos que un **personaje** reduce los puntos de vida (_"HP"_) de un **oponente** a 0, por lo que usaremos el método _"winStars()"_,pero esta vez no estará limitado solo a _"Player"_ si no que puede ser usado por todo _"Character"_. Para cada **personaje** _"Non-Player Character"_ recibira le quitara la mitad de las estrellas a su **oponente**, y para los _"Player Character"_ le quitara todas las estrellas a todo _"Non-Player"_ **oponente** y la mitad a algún _"Player"_. Sí _"getStars()"_ entrega el total de estrellas de cualquiera sea el _"Character"_:

_**int** estrellasPersonaje == **personaje**.getStars()_
_**int** estrellasOponente == **oponente**.getStars()_

Para todo **personaje** no jugador:
_**Non-Player**-Character.winStars(**oponente**);
**Non-Player**-Character.getStars() == estrellasOponente/2 + estrellasPersonaje_

Para todo **Player** contra otro jugador:
_**Player**-Character.winStars(**playerOponente**);
**Player**-Character.getStars() == estrellasOponente/2 + estrellasPersonaje_

Contra un otro _"Character"_:
_**Player**-Character.winStars(**non-PlayerOponente**);
**Player**-Character.getStars() == estrellasOponente + estrellasPersonaje_

### Modificar los stats de un jugador (ATK, DEF y EVD):
Por último, para modificar los stats de _"Atk, Def y Evd"_, aplicamos los métodos _"Boost"_. Cada uno recibe un **int** _"cantidad"_, y generan un nuevo _"Player"_ que es una copia del **Jugador**, pero con un incremento en el _"stat"_ deseado.

**Player** playerBoostAtk =**Jugador**.getBoostAtk(int "cantidad")
**Player** playerBoostDef =**Jugador**.getBoostDef(int "cantidad")
**Player** playerBoostEvd =**Jugador**.getBoostEvd(int "cantidad")
