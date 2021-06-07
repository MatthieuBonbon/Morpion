# 1.
Le joueur qui commence (A) à forcément un coup supplémentaire par rapport au second joueur (B).
En effet : <br>
    Tours :<ul>
      <li>  0. A </li>
      <li>  1. B </li>
      <li>  2. A </li>
      <li>  3. B </li>
      <li>  4. A </li>
      <li>  5. B </li>
      <li>  6. A </li>
      <li>  7. B </li>
      <li>  8. A </li>
        </ul>

Car chaque case ne peut être remplie qu'une seule fois et il y a 9 cases.
Cinq cases seront remplies par le joueur A et quatre par le joueur B.

# 2.
La vérification d'une partie gagnée n'aura lieu qu'à partir du 5ème tour.
En effet, c'est à partir du 5ème tour que le joueur A dispose d'au moins 3 "jetons" dans la partie.


# 3.
A la fin du 9ème tour, si ni A ni B n'a gagné la partie, alors elle est perdue pour eux deux.

# 4.
Conditions de victoire : il existe 8 manières différentes de gagner une partie.<br><ul>
<li>Il y a 3 combinaisons possibles à l'<b>h</b>orizontale</li>
<li>Il y a 3 combinaisons possibles à la <b>v</b>erticale</li>
<li>Il y a 3 combinaisons possibles en <b>d</b>iagonale</li>
</ul>
Les voici ci-dessous : <br>

![Solutions possibles](https://github.com/Turbocrack/Morpion/blob/main/assets/winCondition.png)