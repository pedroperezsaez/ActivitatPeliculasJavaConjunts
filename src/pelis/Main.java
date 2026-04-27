package pelis;

import java.util.*;

/**
 * EXERCICI: METFLIX (Gestió de Catàleg i Recomanacions)
 * 
 * OBJECTIU: Implementar un sistema de gestió de pel·lícules i usuaris utilitzant
 * el Java Collections Framework (List, Map, Set, Queue).
 * 
 * TASQUES:
 * 1. Implementar la classe Pelicula: ha de ser Comparable per valoració (descendent).
 * 2. Implementar la classe Usuari: amb historial (Set) i cua de recomanacions (Queue).
 * 3. Implementar la classe Metflix:
 *    - afegirPelicula: Omple el catàleg (Map) i el llistat per gènere.
 *    - buscarPerGenere: Retorna les pel·lícules d'un gènere específic.
 *    - topValorades(n): Retorna les n millors pel·lícules del catàleg.
 *    - generarRecomanacions: Busca el gènere més vist per l'usuari i li recomana
 *      pel·lícules d'aquest gènere que encara no hagi vist.
 * 4. Implementar el mètode Main: per testejar tot el sistema.
 */

public class Main {
    static String[][] dadesPelicules = {
            { "P001", "The Godfather",           "Drama",   "1972", "9.2" },
            { "P002", "The Dark Knight",         "Acció",   "2008", "9.0" },
            { "P003", "Interstellar",            "CiF",     "2014", "8.6" },
            { "P004", "Parásitos",               "Drama",   "2019", "8.5" },
            { "P005", "Mad Max: Fury Road",      "Acció",   "2015", "8.1" },
            { "P006", "El Gran Hotel Budapest",  "Comèdia", "2014", "7.9" },
            { "P007", "Dune",                    "CiF",     "2021", "7.8" },
            { "P008", "Superbad",                "Comèdia", "2007", "7.6" },
            { "P009", "John Wick",               "Acció",   "2014", "7.4" },
            { "P010", "La La Land",              "Comèdia", "2016", "7.3" }
    };

    static String[][] dadesUsuaris = {
            { "U1", "Marta Puig" },
            { "U2", "Jordi Mas"  },
            { "U3", "Laia Soler" }
    };

    public static void main(String[] args) {
        // TODO: Tasca 4 - Implementar el flux principal
        
        // 1. Instanciar l'objecte Metflix.
        
        // 2. Carregar les dades de les matrius 'dadesPelicules' i 'dadesUsuaris' 
        //    dins del sistema (recorda fer els càstings de dades necessaris).
        
        // 3. Cercar un usuari i simular que visualitza unes quantes pel·lícules.
        
        // 4. Generar les recomanacions per a aquest usuari.
        
        // 5. Mostrar per pantalla les recomanacions obtingudes (usant el mètode seguentRecomanacio).
        
        // 6. Mostrar el TOP 3 de pel·lícules millor valorades del catàleg.
    }
}

class Pelicula implements Comparable<Pelicula> {
    private String id;
    private String titol;
    private String genere;
    private int any;
    private double valoracio;

    public Pelicula(String id, String titol, String genere, int any, double valoracio) {
        this.id = id;
        this.titol = titol;
        this.genere = genere;
        this.any = any;
        this.valoracio = valoracio;
    }

    public String getId() { return id; }
    public String getTitol() { return titol; }
    public String getGenere() { return genere; }
    public double getValoracio() { return valoracio; }

    @Override
    public int compareTo(Pelicula o) {
        // TODO: Tasca 1 - Implementar l'ordenació descendent per valoració
        if(this.valoracio== o.valoracio) return 0;
        if (this.valoracio < o.valoracio) return -1;
        return 1;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (%d) - %.1f \u2605", genere, titol, any, valoracio);
    }
}

class Usuari {
    private String id;
    private String nom;
    private Set<String> historial;
    private Queue<Pelicula> recomanacions;

    public Usuari(String id, String nom) {
        this.id = id;
        this.nom = nom;
       this.historial=new HashSet<>();
       this.recomanacions=new PriorityQueue<>();
        // TODO: Tasca 2 - Inicialitzar les estructures de dades adequades (HashSet, LinkedList...)
    }

    public String getId() { return id; }
    public String getNom() { return nom; }
    public Set<String> getHistorial() { return historial; }
    public Queue<Pelicula> getRecomanacions() { return recomanacions; }

}

class Metflix {
    // TODO: Tasca 3 - Definir i inicialitzar les estructures: catàleg, perGenere i usuaris
    private Map<String, Pelicula> cataleg;
    private Map<String, List<Pelicula>> perGenere;
    private Map<String, Usuari> usuaris;
    Metflix(){
        this.cataleg= new HashMap<>();
        this.perGenere=new HashMap<>();
        this.usuaris=new HashMap<>();
    }

    public Map<String, Pelicula> getCataleg() {
        return cataleg;
    }

    public void setCataleg(Map<String, Pelicula> cataleg) {
        this.cataleg = cataleg;
    }

    public Map<String, List<Pelicula>> getPerGenere() {
        return perGenere;
    }

    public void setPerGenere(Map<String, List<Pelicula>> perGenere) {
        this.perGenere = perGenere;
    }

    public Map<String, Usuari> getUsuaris() {
        return usuaris;
    }

    public void setUsuaris(Map<String, Usuari> usuaris) {
        this.usuaris = usuaris;
    }

    public void afegirPelicula(Pelicula p) {
        // TODO: Afegir al catàleg i a la llista del seu gènere
        cataleg.put(p.getId(),p);
        String genere=p.getGenere();
        if (!perGenere.containsKey(genere)){
            List<Pelicula> nueveGenero=new ArrayList<>();
            perGenere.put(genere,nueveGenero);
        }
            perGenere.get(genere).add(p);


    }

    public void afegirUsuari(Usuari u) {
        // TODO: Afegir al mapa d'usuaris
        usuaris.put(u.getId(), u);
    }

    public Pelicula buscarPerId(String id) {
        // TODO: Retornar la pel·lícula del catàleg
        return cataleg.get(id);
    }

    public Usuari buscarUsuari(String id) {
        // TODO: Retornar l'usuari del mapa d'usuaris

        return null;
    }

    public List<Pelicula> topValorades(int n) {
        // TODO: Retornar les n pel·lícules amb millor puntuació
        // Pista: Convertir el Map a List i usar Collections.sort()
        return null;
    }

    public void visualitzar(Usuari u, Pelicula p) {
        // TODO: Afegir l'ID de la pel·lícula a l'historial de l'usuari
    }

    public void generarRecomanacions(String idUsuari) {
        // TODO: Tasca avançada
        // 1. Trobar quin és el gènere que l'usuari ha vist més vegades.
        // 2. Cercar pel·lícules d'aquell gènere que l'usuari ENCARA NO hagi vist.
        // 3. Afegir-les a la cua de recomanacions de l'usuari.
    }

    public Pelicula seguentRecomanacio(String idUsuari) {
        // TODO: Retornar i extreure el següent element de la cua de recomanacions (poll)
        return null;
    }
}
