package model;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Battle {

    public static Random random = new Random();
    public static Scanner scan = new Scanner(System.in);

    public static final Type acero = new Type("Acero");
    public static final Type agua = new Type("Agua");
    public static final Type bicho = new Type("Bicho");
    public static final Type dragon = new Type("Dragón");
    public static final Type electrico = new Type("Eléctrico");
    public static final Type fantasma = new Type("Fantasma");
    public static final Type fuego = new Type("Fuego");
    public static final Type hada = new Type("Hada");
    public static final Type hielo = new Type("Hielo");
    public static final Type lucha = new Type("Lucha");
    public static final Type normal = new Type("Normal");
    public static final Type planta = new Type("Planta");
    public static final Type psiquico = new Type("Psíquico");
    public static final Type roca = new Type("Roca");
    public static final Type siniestro = new Type("Siniestro");
    public static final Type tierra = new Type("Tierra");
    public static final Type veneno = new Type("Veneno");
    public static final Type volador = new Type("Volador");
    public static final Type nulo = new Type("Nulo");

    static {
        loadEffectiveness();
    }

    private static void loadEffectiveness() {
        acero.addEffectiveness("Hada", 2.0);
        acero.addEffectiveness("Hielo", 2.0);
        acero.addEffectiveness("Roca", 2.0);
        acero.addEffectiveness("Acero", 0.5);
        acero.addEffectiveness("Agua", 0.5);
        acero.addEffectiveness("Eléctrico", 0.5);
        acero.addEffectiveness("Fuego", 0.5);

        agua.addEffectiveness("Fuego", 2.0);
        agua.addEffectiveness("Tierra", 2.0);
        agua.addEffectiveness("Roca", 2.0);
        agua.addEffectiveness("Agua", 0.5);
        agua.addEffectiveness("Dragón", 0.5);
        agua.addEffectiveness("Planta", 0.5);

        bicho.addEffectiveness("Planta", 2.0);
        bicho.addEffectiveness("Psíquico", 2.0);
        bicho.addEffectiveness("Siniestro", 2.0);
        bicho.addEffectiveness("Bicho", 0.5);
        bicho.addEffectiveness("Fuego", 0.5);
        bicho.addEffectiveness("Lucha", 0.5);
        bicho.addEffectiveness("Volador", 0.5);
        bicho.addEffectiveness("Roca", 0.5);
        bicho.addEffectiveness("Fantasma", 0.5);
        bicho.addEffectiveness("Dragón", 0.5);

        dragon.addEffectiveness("Dragón", 2.0);
        dragon.addEffectiveness("Acero", 0.5);
        dragon.addEffectiveness("Hada", 0.0);

        electrico.addEffectiveness("Agua", 2.0);
        electrico.addEffectiveness("Volador", 2.0);
        electrico.addEffectiveness("Eléctrico", 0.5);
        electrico.addEffectiveness("Dragón", 0.5);
        electrico.addEffectiveness("Planta", 0.5);
        electrico.addEffectiveness("Tierra", 0.0);

        fantasma.addEffectiveness("Psíquico", 2.0);
        fantasma.addEffectiveness("Fantasma", 2.0);
        fantasma.addEffectiveness("Siniestro", 0.5);
        fantasma.addEffectiveness("Normal", 0.0);

        fuego.addEffectiveness("Acero", 2.0);
        fuego.addEffectiveness("Bicho", 2.0);
        fuego.addEffectiveness("Hielo", 2.0);
        fuego.addEffectiveness("Planta", 2.0);
        fuego.addEffectiveness("Agua", 0.5);
        fuego.addEffectiveness("Dragón", 0.5);
        fuego.addEffectiveness("Fuego", 0.5);
        fuego.addEffectiveness("Roca", 0.5);

        hada.addEffectiveness("Dragón", 2.0);
        hada.addEffectiveness("Lucha", 2.0);
        hada.addEffectiveness("Siniestro", 2.0);
        hada.addEffectiveness("Veneno", 0.5);
        hada.addEffectiveness("Acero", 0.5);
        hada.addEffectiveness("Fuego", 0.5);

        hielo.addEffectiveness("Dragón", 2.0);
        hielo.addEffectiveness("Planta", 2.0);
        hielo.addEffectiveness("Tierra", 2.0);
        hielo.addEffectiveness("Volador", 2.0);
        hielo.addEffectiveness("Acero", 0.5);
        hielo.addEffectiveness("Agua", 0.5);
        hielo.addEffectiveness("Fuego", 0.5);
        hielo.addEffectiveness("Hielo", 0.5);

        lucha.addEffectiveness("Acero", 2.0);
        lucha.addEffectiveness("Hielo", 2.0);
        lucha.addEffectiveness("Normal", 2.0);
        lucha.addEffectiveness("Roca", 2.0);
        lucha.addEffectiveness("Siniestro", 2.0);
        lucha.addEffectiveness("Bicho", 0.5);
        lucha.addEffectiveness("Hada", 0.5);
        lucha.addEffectiveness("Psíquico", 0.5);
        lucha.addEffectiveness("Veneno", 0.5);
        lucha.addEffectiveness("Volador", 0.5);
        lucha.addEffectiveness("Fantasma", 0.0);

        normal.addEffectiveness("Acero", 0.5);
        normal.addEffectiveness("Roca", 0.5);
        normal.addEffectiveness("Fantasma", 0.0);

        planta.addEffectiveness("Agua", 2.0);
        planta.addEffectiveness("Roca", 2.0);
        planta.addEffectiveness("Tierra", 2.0);
        planta.addEffectiveness("Acero", 0.5);
        planta.addEffectiveness("Bicho", 0.5);
        planta.addEffectiveness("Dragón", 0.5);
        planta.addEffectiveness("Fuego", 0.5);
        planta.addEffectiveness("Planta", 0.5);
        planta.addEffectiveness("Veneno", 0.5);
        planta.addEffectiveness("Volador", 0.5);

        psiquico.addEffectiveness("Lucha", 2.0);
        psiquico.addEffectiveness("Veneno", 2.0);
        psiquico.addEffectiveness("Psíquico", 0.5);
        psiquico.addEffectiveness("Fantasma", 0.5);
        psiquico.addEffectiveness("Siniestro", 0.0);

        roca.addEffectiveness("Fuego", 2.0);
        roca.addEffectiveness("Hielo", 2.0);
        roca.addEffectiveness("Volador", 2.0);
        roca.addEffectiveness("Bicho", 2.0);
        roca.addEffectiveness("Acero", 0.5);
        roca.addEffectiveness("Lucha", 0.5);
        roca.addEffectiveness("Tierra", 0.5);

        siniestro.addEffectiveness("Psíquico", 2.0);
        siniestro.addEffectiveness("Fantasma", 2.0);
        siniestro.addEffectiveness("Hada", 0.5);
        siniestro.addEffectiveness("Lucha", 0.5);
        siniestro.addEffectiveness("Siniestro", 0.5);

        tierra.addEffectiveness("Fuego", 2.0);
        tierra.addEffectiveness("Eléctrico", 2.0);
        tierra.addEffectiveness("Roca", 2.0);
        tierra.addEffectiveness("Agua", 0.5);
        tierra.addEffectiveness("Planta", 0.5);
        tierra.addEffectiveness("Veneno", 0.5);
        tierra.addEffectiveness("Volador", 0.0);

        veneno.addEffectiveness("Planta", 2.0);
        veneno.addEffectiveness("Hada", 2.0);
        veneno.addEffectiveness("Fantasma", 0.5);
        veneno.addEffectiveness("Roca", 0.5);
        veneno.addEffectiveness("Siniestro", 0.5);
        veneno.addEffectiveness("Acero", 0.0);

        volador.addEffectiveness("Bicho", 2.0);
        volador.addEffectiveness("Lucha", 2.0);
        volador.addEffectiveness("Planta", 2.0);
        volador.addEffectiveness("Acero", 0.5);
        volador.addEffectiveness("Roca", 0.5);
        volador.addEffectiveness("Eléctrico", 0.5);
    }

    public static ATK aBocajarro = new ATK("A Bocajarro", lucha, 0, 5, 120, 100, null, "Físisco");
    public static ATK puñoBala = new ATK("Puño Bala", acero, 1, 30, 40, 100, null, "Físico");
    public static ATK triturar = new ATK("Triturar", siniestro, 0, 15, 80, 100, null, "Físico");
    public static ATK esferaAural = new ATK("Esfera Aural", lucha, 0, 20, 80, 0, null, "Especial");
    public static ATK bolaSombra = new ATK("Bola Sombra", fantasma, 0, 15, 80, 100, null, "Especial");
    public static ATK pulsoUmbrio = new ATK("Pulso Umbrío", siniestro, 0, 15, 80, 100, null, "Especial");
    public static ATK velocidadExtrema = new ATK("Velocidad Extrema", normal, 2, 5, 80, 100, null, "Físico");
    public static ATK terremoto = new ATK("Terremoto", tierra, 0, 10, 100, 100, null, "Físico");
    public static ATK psiquicoM = new ATK("Psíquico", psiquico, 0, 10, 90, 100, null, "Especial");
    public static ATK puñoHielo = new ATK("Puño Hielo", hielo, 0, 15, 75, 100, null, "Físico");
    public static ATK rocaAfilada = new ATK("Roca Afilada", roca, 0, 5, 100, 80, null, "Físico");
    public static ATK llamarada = new ATK("Llamarada", fuego, 0, 5, 110, 85, null, "Especial");
    public static ATK pulsoDragon = new ATK("Pulso Dragón", dragon, 0, 10, 85, 100, null, "Especial");
    public static ATK tajoAereo = new ATK("Tajo Aéreo", volador, 0, 15, 75, 95, null, "Especial");
    public static ATK garraDragon = new ATK("Garra Dragón", dragon, 0, 15, 80, 100, null, "Físico");
    public static ATK puñoTrueno = new ATK("Puño Trueno", electrico, 0, 15, 75, 100, null, "Físico");
    public static ATK lanzallamas = new ATK("Lanzallamas", fuego, 0, 15, 90, 100, null, "Especial");
    public static ATK puñoFuego = new ATK("Puño Fuego", fuego, 0, 15, 75, 100, null, "Físico");
    public static ATK demolición = new ATK("Demolición", lucha, 0, 15, 75, 100, null, "Físico");
    public static ATK hidrobomba = new ATK("Hidrobomba", agua, 0, 5, 110, 80, null, "Especial");
    public static ATK rayoHielo = new ATK("Rayo Hielo", hielo, 0, 10, 90, 100, null, "Especial");
    public static ATK surf = new ATK("Surf", agua, 0, 15, 90, 100, null, "Especial");
    public static ATK lanzaMugre = new ATK("Lanza Mugre", veneno, 0, 5, 120, 80, null, "Físico");
    public static ATK tajoUmbrio = new ATK("Tajo Umbrío", siniestro, 0, 15, 70, 100, null, "Físico");
    public static ATK sombraVil = new ATK("Sombra Vil", fantasma, 1, 30, 40, 100, null, "Físico");
    public static ATK llueveHojas = new ATK("Llueve Hojas", planta, 0, 5, 130, 90, null, "Especial");
    public static ATK hojaAguda = new ATK("Hoja Aguda", planta, 0, 15, 90, 100, null, "Físico");
    public static ATK dobleRayo = new ATK("Doble Rayo", bicho, 0, 15, 75, 100, null, "Especial");
    public static ATK chispazo = new ATK("Chispazo", electrico, 0, 15, 80, 100, null, "Especial");
    public static ATK rayo = new ATK("Rayo", electrico, 0, 15, 90, 100, null, "Especial");
    public static ATK trueno = new ATK("Trueno", electrico, 0, 10, 110, 70, null, "Especial");
    public static ATK ondaToxica = new ATK("Onda Tóxica", veneno, 0, 10, 95, 100, null, "Especial");
    public static ATK bombaLodo = new ATK("Bomba Lodo", veneno, 0, 10, 90, 100, null, "Especial");
    public static ATK vientoHielo = new ATK("Viento Hielo", hielo, 0, 15, 55, 95, null, "Especial");
    public static ATK pajaroOsado = new ATK("Pájaro Osado", volador, 0, 15, 120, 100, null, "Físico");
    public static ATK acrobata = new ATK("Acróbata", volador, 0, 15, 55, 100, null, "Físico");
    public static ATK fuerzaBruta = new ATK("Fuerza Bruta", lucha, 0, 5, 120, 100, null, "Físico");
    public static ATK tierraViva = new ATK("Tierra Viva", tierra, 0, 10, 90, 100, null, "Especial");
    public static ATK carantoña = new ATK("Carantoña", hada, 0, 10, 90, 90, null, "Físico");
    public static ATK cañonFloral = new ATK("Cañón Floral", hada, 0, 5, 130, 90, null, "Especial");
    public static ATK megacuerno = new ATK("Megacuerno", bicho, 0, 10, 120, 85, null, "Físico");

    public static PKMN lucario = new PKMN("Lucario", lucha, acero, 70, 110, 70, 115, 70, 90);
    public static PKMN gengar = new PKMN("Gengar", fantasma, veneno, 60, 65, 60, 130, 75, 110);
    public static PKMN metagross = new PKMN("Metagross", psiquico, acero, 80, 135, 130, 95, 90, 70);
    public static PKMN snorlax = new PKMN("Snorlax", normal, nulo, 160, 110, 65, 65, 110, 30);
    public static PKMN dragonite = new PKMN("Dragonite", dragon, volador, 91, 134, 95, 100, 100, 80);
    public static PKMN scizor = new PKMN("Scizor", bicho, acero, 70, 130, 100, 55, 80, 65);
    public static PKMN greninja = new PKMN("Greninja", agua, siniestro, 72, 95, 67, 103, 71, 122);
    public static PKMN charizard = new PKMN("Charizard", fuego, volador, 78, 84, 78, 109, 85, 100);
    public static PKMN toxtricity = new PKMN("Toxtricity", electrico, veneno, 75, 98, 70, 114, 70, 75);
    public static PKMN tyranitar = new PKMN("Tyranitar", roca, siniestro, 100, 134, 110, 95, 100, 61);
    public static PKMN salamence = new PKMN("Salamence", dragon, volador, 95, 135, 80, 110, 80, 100);
    public static PKMN hydreigon = new PKMN("Hydreigon", dragon, siniestro, 92, 105, 90, 125, 90, 98);
    public static PKMN goodra = new PKMN("Goodra", dragon, nulo, 90, 100, 70, 110, 150, 80);
    public static PKMN kommo_o = new PKMN("Kommo-o", dragon, lucha, 75, 110, 125, 100, 105, 85);
    public static PKMN dragapult = new PKMN("Dragapult", dragon, fantasma, 88, 120, 75, 100, 75, 142);
    public static PKMN baxcalibur = new PKMN("Baxcalibur", dragon, hielo, 115, 145, 92, 75, 86, 87);
    public static PKMN gyarados = new PKMN("Gyarados", agua, volador, 95, 125, 79, 60, 100, 81);
    public static PKMN krookodile = new PKMN("Krookodile", tierra, siniestro, 95, 117, 80, 65, 70, 92);
    public static PKMN venusaur = new PKMN("Venusaur", planta, veneno, 80, 82, 83, 100, 100, 80);
    public static PKMN blastoise = new PKMN("Blastoise", agua, nulo, 79, 83, 100, 85, 105, 78);
    public static PKMN aggron = new PKMN("Aggron", acero, tierra, 70, 110, 180, 60, 60, 50);
    public static PKMN alakazam = new PKMN("Alakazam", psiquico, nulo, 55, 50, 45, 135, 95, 120);
    public static PKMN ceruledge = new PKMN("Ceruledge", fantasma, fuego, 70, 125, 80, 60, 100, 85);
    public static PKMN umbreon = new PKMN("Umbreon", siniestro, nulo, 95, 65, 110, 60, 130, 65);
    public static PKMN sylveon = new PKMN("Sylveon", hada, nulo, 95, 65, 60, 110, 130, 60);
    public static PKMN flygon = new PKMN("Flygon", dragon, tierra, 80, 100, 80, 80, 80, 100);
    public static PKMN aegislash = new PKMN("Aegislash", fantasma, acero, 60, 50, 150, 50, 150, 60);
    public static PKMN talonflame = new PKMN("Talonflame", volador, fuego, 78, 81, 71, 74, 69, 126);
    public static PKMN corviknight = new PKMN("Corviknight", volador, acero, 98, 87, 105, 53, 85, 67);
    public static PKMN lapras = new PKMN("Lapras", agua, hielo, 130, 85, 80, 85, 95, 60);
    public static PKMN golisopod = new PKMN("Golisopod", bicho, agua, 75, 125, 140, 60, 90, 40);
    public static PKMN garchomp = new PKMN("Garchomp", dragon, tierra, 108, 130, 95, 80, 85, 102);
    public static PKMN lycanroc = new PKMN("Lycanroc", roca, nulo, 75, 115, 65, 55, 105, 82);
    public static PKMN gallade = new PKMN("Gallade", lucha, psiquico, 68, 125, 65, 115, 110, 80);

    static {
        loadMoves();
    }

    private static void loadMoves() {
        lucario.assignMove(esferaAural, 0);
        lucario.assignMove(puñoBala, 1);
        lucario.assignMove(pulsoDragon, 2);
        lucario.assignMove(velocidadExtrema, 3);

        gengar.assignMove(bolaSombra, 0);
        gengar.assignMove(pulsoUmbrio, 1);
        gengar.assignMove(psiquicoM, 2);
        gengar.assignMove(ondaToxica, 3);

        metagross.assignMove(puñoBala, 0);
        metagross.assignMove(terremoto, 1);
        metagross.assignMove(triturar, 2);
        metagross.assignMove(fuerzaBruta, 3);

        snorlax.assignMove(demolición, 0);
        snorlax.assignMove(terremoto, 1);
        snorlax.assignMove(puñoFuego, 2);
        snorlax.assignMove(rayoHielo, 3);

        dragonite.assignMove(tajoAereo, 0);
        dragonite.assignMove(pulsoDragon, 1);
        dragonite.assignMove(lanzallamas, 2);
        dragonite.assignMove(terremoto, 3);

        scizor.assignMove(puñoBala, 0);
        scizor.assignMove(triturar, 1);
        scizor.assignMove(acrobata, 2);
        scizor.assignMove(tajoUmbrio, 3);

        greninja.assignMove(hidrobomba, 0);
        greninja.assignMove(surf, 1);
        greninja.assignMove(rayo, 2);
        greninja.assignMove(pulsoUmbrio, 3);

        charizard.assignMove(llamarada, 0);
        charizard.assignMove(lanzallamas, 1);
        charizard.assignMove(tajoAereo, 2);
        charizard.assignMove(tierraViva, 3);

        toxtricity.assignMove(chispazo, 0);
        toxtricity.assignMove(rayo, 1);
        toxtricity.assignMove(pulsoUmbrio, 2);
        toxtricity.assignMove(bombaLodo, 3);

        tyranitar.assignMove(terremoto, 0);
        tyranitar.assignMove(triturar, 1);
        tyranitar.assignMove(puñoBala, 2);
        tyranitar.assignMove(pulsoUmbrio, 3);

        salamence.assignMove(pulsoDragon, 0);
        salamence.assignMove(llamarada, 1);
        salamence.assignMove(acrobata, 2);
        salamence.assignMove(velocidadExtrema, 3);

        hydreigon.assignMove(bolaSombra, 0);
        hydreigon.assignMove(pulsoDragon, 1);
        hydreigon.assignMove(megacuerno, 2);
        hydreigon.assignMove(rayo, 3);

        goodra.assignMove(hidrobomba, 0);
        goodra.assignMove(rayoHielo, 1);
        goodra.assignMove(garraDragon, 2);
        goodra.assignMove(dobleRayo, 3);

        kommo_o.assignMove(aBocajarro, 0);
        kommo_o.assignMove(puñoFuego, 1);
        kommo_o.assignMove(puñoBala, 2);
        kommo_o.assignMove(carantoña, 3);

        dragapult.assignMove(tajoAereo, 0);
        dragapult.assignMove(puñoTrueno, 1);
        dragapult.assignMove(dobleRayo, 2);
        dragapult.assignMove(pajaroOsado, 3);

        baxcalibur.assignMove(megacuerno, 0);
        baxcalibur.assignMove(llamarada, 1);
        baxcalibur.assignMove(puñoHielo, 2);
        baxcalibur.assignMove(tajoUmbrio, 3);

        gyarados.assignMove(hidrobomba, 0);
        gyarados.assignMove(rayoHielo, 1);
        gyarados.assignMove(terremoto, 2);
        gyarados.assignMove(rayoHielo, 3);

        krookodile.assignMove(puñoTrueno, 0);
        krookodile.assignMove(lanzaMugre, 1);
        krookodile.assignMove(terremoto, 2);
        krookodile.assignMove(chispazo, 3);

        venusaur.assignMove(llueveHojas, 0);
        venusaur.assignMove(cañonFloral, 1);
        venusaur.assignMove(lanzaMugre, 2);
        venusaur.assignMove(rayoHielo, 3);

        blastoise.assignMove(hidrobomba, 0);
        blastoise.assignMove(rayoHielo, 1);
        blastoise.assignMove(psiquicoM, 2);
        blastoise.assignMove(surf, 3);

        aggron.assignMove(demolición, 0);
        aggron.assignMove(tierraViva, 1);
        aggron.assignMove(rocaAfilada, 2);
        aggron.assignMove(terremoto, 3);

        alakazam.assignMove(pulsoDragon, 0);
        alakazam.assignMove(psiquicoM, 1);
        alakazam.assignMove(bolaSombra, 2);
        alakazam.assignMove(ondaToxica, 3);

        ceruledge.assignMove(bolaSombra, 0);
        ceruledge.assignMove(llamarada, 1);
        ceruledge.assignMove(tajoUmbrio, 2);
        ceruledge.assignMove(lanzallamas, 3);

        umbreon.assignMove(pulsoUmbrio, 0);
        umbreon.assignMove(bolaSombra, 1);
        umbreon.assignMove(ondaToxica, 2);
        umbreon.assignMove(terremoto, 3);

        sylveon.assignMove(cañonFloral, 0);
        sylveon.assignMove(puñoBala, 1);
        sylveon.assignMove(rayo, 2);
        sylveon.assignMove(pulsoUmbrio, 3);

        flygon.assignMove(tajoAereo, 0);
        flygon.assignMove(terremoto, 1);
        flygon.assignMove(rayoHielo, 2);
        flygon.assignMove(garraDragon, 3);

        aegislash.assignMove(tajoUmbrio, 0);
        aegislash.assignMove(pulsoDragon, 1);
        aegislash.assignMove(bolaSombra, 2);
        aegislash.assignMove(demolición, 3);

        talonflame.assignMove(pajaroOsado, 0);
        talonflame.assignMove(llamarada, 1);
        talonflame.assignMove(tajoAereo, 2);
        talonflame.assignMove(acrobata, 3);

        corviknight.assignMove(rocaAfilada, 0);
        corviknight.assignMove(terremoto, 1);
        corviknight.assignMove(pulsoUmbrio, 2);
        corviknight.assignMove(tajoAereo, 3);

        lapras.assignMove(hidrobomba, 0);
        lapras.assignMove(rayoHielo, 1);
        lapras.assignMove(psiquicoM, 2);
        lapras.assignMove(llueveHojas, 3);

        golisopod.assignMove(lanzaMugre, 0);
        golisopod.assignMove(puñoBala, 1);
        golisopod.assignMove(terremoto, 2);
        golisopod.assignMove(fuerzaBruta, 3);

        garchomp.assignMove(terremoto, 0);
        garchomp.assignMove(garraDragon, 1);
        garchomp.assignMove(rocaAfilada, 2);
        garchomp.assignMove(llamarada, 3);

        lycanroc.assignMove(triturar, 0);
        lycanroc.assignMove(terremoto, 1);
        lycanroc.assignMove(rocaAfilada, 2);
        lycanroc.assignMove(carantoña, 3);

        gallade.assignMove(aBocajarro, 0);
        gallade.assignMove(puñoBala, 1);
        gallade.assignMove(triturar, 2);
        gallade.assignMove(esferaAural, 3);
    }

    public static Dex dex = new Dex();

    static {
        loadPKMN();
    }

    private static void loadPKMN() {
        dex.addPKMN(448, lucario);
        dex.addPKMN(94, gengar);
        dex.addPKMN(376, metagross);
        dex.addPKMN(143, snorlax);
        dex.addPKMN(149, dragonite);
        dex.addPKMN(212, scizor);
        dex.addPKMN(658, greninja);
        dex.addPKMN(6, charizard);
        dex.addPKMN(849, toxtricity);
        dex.addPKMN(248, tyranitar);
        dex.addPKMN(373, salamence);
        dex.addPKMN(635, hydreigon);
        dex.addPKMN(706, goodra);
        dex.addPKMN(783, kommo_o);
        dex.addPKMN(887, dragapult);
        dex.addPKMN(998, baxcalibur);
        dex.addPKMN(130, gyarados);
        dex.addPKMN(553, krookodile);
        dex.addPKMN(3, venusaur);
        dex.addPKMN(9, blastoise);
        dex.addPKMN(306, aggron);
        dex.addPKMN(65, alakazam);
        dex.addPKMN(937, ceruledge);
        dex.addPKMN(197, umbreon);
        dex.addPKMN(700, sylveon);
        dex.addPKMN(330, flygon);
        dex.addPKMN(681, aegislash);
        dex.addPKMN(663, talonflame);
        dex.addPKMN(823, corviknight);
        dex.addPKMN(131, lapras);
        dex.addPKMN(792, golisopod);
        dex.addPKMN(445, garchomp);
        dex.addPKMN(745, lycanroc);
        dex.addPKMN(475, gallade);

    }

    public static int calculateDamage(PKMN attacker, Move move, PKMN defender) {
        int attack;
        int defense;
        if (move.getPower() == 0) {
            return -1;
        } else {
            if (move.getCategory().equals("Especial")) {
                attack = attacker.getSpecialAttack();
                defense = defender.getSpecialDefense();
            } else {
                attack = attacker.getAttack();
                defense = defender.getDefense();
            }
            int level = attacker.getlevel();
            int power = move.getPower();
            double critical = (random.nextInt(24) == 0) ? 1.5 : 1.0;
            double stab = (attacker.getType1().equals(move.getType()) || attacker.getType2().equals(move.getType())) ? 1.5 : 1.0;
            double effectiveness = (move.getType().getEffectiveness(defender.getType1())) * (move.getType().getEffectiveness(defender.getType2()));
            double baseDamage = ((level * 2.0 / 5.0 + 2) * power * ((double) attack / defense)) / 50.0 + 2;
            double modifier = (85 + random.nextInt(16)) * 0.01;
            int damage = (int) (baseDamage * stab * effectiveness * modifier * critical);
            System.out.printf("%n%s ha usado %s.%n", attacker.getName(), move.getName());
            if (critical > 1.0 && damage > 0) {
                System.out.println("!Golpe crítico¡");
            }
            return damage;
        }
    }

    public static boolean doesAttackHit(PKMN attacker, PKMN defender, Move move) {
        if (move.getAccuracy() == 0) {
            return true;
        }
        double hitChance = 0.01 * move.getAccuracy() * (attacker.getPrecision() / defender.getEvasion());
        return Math.random() < hitChance;
    }

    public static void useMove(PKMN attacker, Move move, PKMN defender) {
        move.reducePp();
        if (doesAttackHit(attacker, defender, move)) {
            int damage = calculateDamage(attacker, move, defender);
            if (damage > 0) {
                defender.takeDamage(damage);
            } else if (damage == 0) {
                System.out.println("No afecta a " + defender.getName());
            }
        } else {
            System.out.printf("%n%s ha usado %s.%n", attacker.getName(), move.getName());
            System.out.println(attacker.getName() + " falló el ataque.");
        }
    }

    public static PKMN firstAttacker(Move move1, Move move2, PKMN pokemon1, PKMN pokemon2) {
        if (move1.getPriority() > move2.getPriority()) {
            return pokemon1;
        } else if (move1.getPriority() < move2.getPriority()) {
            return pokemon2;
        } else {
            if (pokemon1.getSpeed() > pokemon2.getSpeed()) {
                return pokemon1;
            } else if (pokemon1.getSpeed() < pokemon2.getSpeed()) {
                return pokemon2;
            } else {
                return (random.nextBoolean()) ? pokemon1 : pokemon2;
            }
        }
    }

    public static boolean isEntryInDex(Integer entry, Dex pokedex) {
        for (Integer entryDex : pokedex.getEntrys()) {
            if (Objects.equals(entry, entryDex)) {
                return true;
            }
        }
        return false;
    }

    public static void showBattleInfo(PKMN rivalPokemon, PKMN ownPokemon) {
        System.out.println("\nPokemon del rival: " + rivalPokemon.getName());
        System.out.printf("HP: %s/%s%n", rivalPokemon.getCurrentHP(), rivalPokemon.getHP());
        System.out.println("\nTu pokemon: " + ownPokemon.getName());
        System.out.printf("HP: %s/%s%n", ownPokemon.getCurrentHP(), ownPokemon.getHP());
    }

    public static Team selectTeam(Dex pokedex, int teamSize) {
        Team team = new Team(teamSize);
        Integer dexN;
        pokedex.showDex();
        System.out.println("\nIngresa el Nº Pokédex del pokemon para agregarlo al equipo");

        while (!team.isFull()) {
            try {
                System.out.printf("%nNº Pokédex del %s° pokemon: ", team.getCurrentSize() + 1);
                dexN = scan.nextInt();
                if (!isEntryInDex(dexN, pokedex)) {
                    System.err.println("Número Pokédex inválido.");
                } else {
                    team.addToTeam(pokedex.getPKMN(dexN).clone());
                    System.out.printf("%s fue añadido a tu equipo.%n", pokedex.getPKMN(dexN).getName());
                }
            } catch (InputMismatchException e) {
                System.err.println("Entrada inválida.");
                scan.nextLine();
            }
        }
        return team;
    }

    public static Team setRandomTeam(Dex pokedex, int teamSize) {
        Team team = new Team(teamSize);
        int dexN;
        Integer[] entrys = dex.getEntrys();
        do {
            dexN = entrys[random.nextInt(entrys.length)];
            team.addToTeam(pokedex.getPKMN(dexN).clone());
        } while (!team.isFull());
        return team;
    }

    public static Move selectMove(PKMN pokemon) {
        int moveN = -1;
        Move move;
        pokemon.showMoves();
        System.out.println("\nIngresa el Nº para seleccionar un movimiento:");
        do {
            try {
                moveN = scan.nextInt() - 1;
                if (moveN < 0 || moveN >= pokemon.getMoves().length) {
                    System.out.println("Entrada inválida");
                }
            } catch (InputMismatchException e) {
                System.err.println("Entrada inválida");
                scan.nextLine();
            }
        } while (moveN < 0 || moveN >= pokemon.getMoves().length);
        move = pokemon.getMoves()[moveN];
        return move;
    }

    public static Move selectRandomMove(PKMN pokemon) {
        int moveN = random.nextInt(4);
        Move move;
        move = pokemon.getMoves()[moveN];
        return move;
    }

    public static PKMN changePokemon(Team team) {
        int index = -1;
        System.out.println("\nSelecciona un pokemon para continuar: ");
        team.showTeam();
        do {
            try {
                index = scan.nextInt() - 1;
                if (index < 0 || index >= team.getCurrentSize()) {
                    System.out.println("Entrada inválida. Intentalo de nuevo.");

                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Intentalo de nuevo.");
                scan.nextLine();
            }
        } while (index < 0 || index >= team.getCurrentSize());
        return team.getPokemon(index);
    }

    public static PKMN changeRandomPokemon(Team team) {
        int index = random.nextInt(team.getCurrentSize());
        return team.getPokemon(index);
    }

    public static boolean isPokemonFainted(PKMN pokemon) {
        return pokemon.getCurrentHP() <= 0;
    }

    public static boolean isTeamDefeated(Team team) {
        return team.isEmpty();
    }

    public static PKMN executeTurn(PKMN playerCurrent, PKMN cpuCurrent, Team playerTeam) {
        int choice = 1;

        if (playerTeam.getCurrentSize() > 1) {
            while (choice < 1 || choice > 2) {
                try {
                    System.out.println("\nElige una opción:");
                    System.out.println("1. Atacar");
                    System.out.println("2. Cambiar Pokémon");
                    choice = scan.nextInt();
                    if (choice < 1 || choice > 2) {
                        System.out.println("Opción inválida.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada no válida. Ingresa un número.");
                    scan.nextLine();
                }
            }
        }
        if (choice == 1) {

            Move playerMove = selectMove(playerCurrent);
            Move cpuMove = selectRandomMove(cpuCurrent);

            PKMN firstAttacker = firstAttacker(playerMove, cpuMove, playerCurrent, cpuCurrent);
            PKMN secondAttacker = (firstAttacker == playerCurrent) ? cpuCurrent : playerCurrent;
            Move firstMove = (firstAttacker == playerCurrent) ? playerMove : cpuMove;
            Move secondMove = (firstAttacker == playerCurrent) ? cpuMove : playerMove;

            useMove(firstAttacker, firstMove, secondAttacker);
            if (isPokemonFainted(secondAttacker)) {
                System.out.printf("%n%s ha sido debilitado.%n", secondAttacker.getName());
            } else {
                useMove(secondAttacker, secondMove, firstAttacker);
                if (isPokemonFainted(firstAttacker)) {
                    System.out.printf("%n%s ha sido debilitado.%n", firstAttacker.getName());
                }
            }
        } else {
            playerCurrent = changePokemon(playerTeam);
            System.out.println("\nHas elegido a " + playerCurrent.getName());
            useMove(cpuCurrent, selectRandomMove(cpuCurrent), playerCurrent);
            if (isPokemonFainted(playerCurrent)) {
                System.out.printf("%n%s ha sido debilitado.%n", playerCurrent.getName());
            }
        }
        return playerCurrent;
    }

    public static boolean verifyFainted(PKMN pokemon, Team team) {
        if (isPokemonFainted(pokemon)) {
            team.removePokemon(pokemon);
            if (!team.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static void battle(Team playerTeam, Team cpuTeam) {
        PKMN playerCurrent = playerTeam.getPokemon(0);
        PKMN cpuCurrent = cpuTeam.getPokemon(random.nextInt(playerTeam.getCurrentSize()));

        System.out.println("\n--Equipo rival--\n");
        cpuTeam.showTeam();
        System.out.println("\n--Tu equipo--\n");
        playerTeam.showTeam();

        while (!isTeamDefeated(playerTeam) && !isTeamDefeated(cpuTeam)) {
            showBattleInfo(cpuCurrent, playerCurrent);

            playerCurrent = executeTurn(playerCurrent, cpuCurrent, playerTeam);
            playerCurrent = (verifyFainted(playerCurrent, playerTeam)) ? changePokemon(playerTeam) : playerCurrent;
            cpuCurrent = (verifyFainted(cpuCurrent, cpuTeam)) ? changeRandomPokemon(cpuTeam) : cpuCurrent;
        }
        if (isTeamDefeated(playerTeam)) {
            System.out.println("\n¡El entrenador rival ha ganado la batalla!");
        } else {
            System.out.println("\n¡Has ganado la batalla!");
        }
    }

    public static int selectTeamSize() {
        int teamSize = -1;

        System.out.println("Selecciona el tamaño de tu equipo (1, 3, o 6):");
        while (teamSize != 1 && teamSize != 3 && teamSize != 6) {
            try {
                System.out.print("Tamaño del equipo: ");
                teamSize = scan.nextInt();

                if (teamSize != 1 && teamSize != 3 && teamSize != 6) {
                    System.err.println("Entrada inválida. Debes ingresar 1, 3, o 6.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Entrada inválida. Debes ingresar un número.");
                scan.nextLine();
            }
        }
        return teamSize;
    }

    public static int selectDifficulty(int teamSize) {
        int choice = 0;

        System.out.println("Selecciona el nivel de dificultad:");
        System.out.println("1. Fácil");
        System.out.println("2. Medio");
        System.out.println("3. Difícil");

        while (choice < 1 || choice > 3) {
            try {
                System.out.print("Ingresa el número correspondiente a la dificultad: ");
                choice = scan.nextInt();

                switch (choice) {
                    case 1 ->
                        teamSize--;
                    case 3 ->
                        teamSize++;
                    default ->
                        System.err.println("Entrada inválida. Por favor, ingresa 1, 2 o 3.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Entrada inválida. Por favor, ingresa un número (1, 2 o 3).");
                scan.nextLine();
            }
        }
        return teamSize;
    }

    public void start() {
        int teamSize = selectTeamSize();
        int rivalTeamSize = teamSize;
        if (teamSize > 1) {
            rivalTeamSize = selectDifficulty(teamSize);
        }
        Team playerTeam = selectTeam(dex, teamSize);
        Team cpuTeam = setRandomTeam(dex, rivalTeamSize);
        battle(playerTeam, cpuTeam);
    }
}
