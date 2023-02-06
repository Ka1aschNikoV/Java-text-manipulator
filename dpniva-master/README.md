## Niko Väkiparta Harkkatyö 2H
## Päivitetty 8.5.2020

# Ohjelman kuvaus

Ohjelman tehtävä on lyhykäisyydessään käsitellä sille annettua tekstikokonaisuutta
komennoilla. Ohjelma on toteutettu Java-kielellä ja sen koodi pyrkii seuraamaan
olio-ohjelmoinnin periaatteita. Ohjelma kykenee käsittelemään tekstitiedostoja,
joiden nimi sisältää joko merkkijonon "jokes" tai merkkijonon "news". Tämän lisäksi
ohjelmaan ladataan käynnistyksen yhteydessä sulkusanojen tiedosto, jonka nimessä
pitäisi olla merkkijono "stop". Ohjelma hyödyntää paljon tällä kurssikokonaisuudella
opittua kuten esimerkiksi Javan LinkedList-pakkausta, rakentajia ja aksessoreita sekä
Object-pakkauksen sisältämiä olion ominaisuuksien vertailuun ja tulostukseen liittyviä
toimintoja.

# Ohjelman toiminnot

Ohjelma on harjoitustyön suppea versio, joka käsittää kokoelmasta poistamisen ja siihen
lisäämisen, kokoelmasta sanojen perusteella etsimisen ja tekstien siivoamisen merkeistä
Kaikki muutokset kokoelmaan voi saada näkyviin tulostamalla joko yhden kokoelman osan tai 
vaikka koko tekstikokoelman.

Näiden toimintojen lisäksi ohjelmassa on mukana enemmän ohjelman toiminnan kannalta
oleellisia toimintoja kuten tekstikokoelman uudelleenlataaminen, komentojen kaiuttaminen ja
ohjelman käytön keskeyttäminen.

# Komentoerittely

**echo**  - kaiuttaa käyttäjän kirjoittaman komennon, toimii kytkinperiaatteella siten, että 
kaiutus joko on päällä tai ei

**print** - tulostaa koko tekstikokoelman

**print <numero>** - tulostaa yhden tietyn kokoelman osan

**find <etsittävät sanat>** - etsii ne kokoelman tunnisteet, joissa esiintyy kaikki komennon
yhteydessä annetut sanat

**polish <poistettavat merkit>** - poistaa kokoelmasta komennon kanssa annetut merkit, 
muuttaa kaikki kirjaimet pieniksi ja poistaa ohjelman käynnistyksen yhteydessä annetun
sulkusanatiedoston sisältämät sulkusanat kokoelmasta

**add <tunniste///tieto///teksti>** - lisää kokoelmaan annetun tunnisteen ja tiedon mukaisen
tekstin, jos kokoelmassa ei saman tunnisteen omaavaa tekstiä vielä ollut

**remove <tunniste>** - poistaa kokoelmasta tunnisteen mukaan tekstin, jos sellainen kokoelmasta
löytyy

**reset** - lataa uudelleen ohjelmaan käynnistyksen yhteydessä annetun tekstikokoelman ja
sulkusanatiedoston

**quit** - poistuu ohjelmasta








