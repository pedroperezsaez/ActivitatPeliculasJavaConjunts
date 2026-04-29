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
        Metflix metflix= new Metflix();
        // 2. Carregar les dades de les matrius 'dadesPelicules' i 'dadesUsuaris' 
        //    dins del sistema (recorda fer els càstings de dades necessaris).
        for (int i = 0; i < dadesPelicules.length; i++) {
            String[] ar=dadesPelicules[i];
            String id=ar[0];
            String titol=ar[1];
            String genere = ar[2];
            int any= Integer.parseInt(ar[3]);
            double valoracio = Double.parseDouble(ar[4]);
            Pelicula p = new Pelicula(id,titol,genere,any,valoracio);
            metflix.afegirPelicula(p);
        }
        for (int i = 0; i < dadesUsuaris.length; i++) {
            String[] ar= dadesUsuaris[i];
            String id=ar[0];
            String nom=ar[1];
            Usuari u= new Usuari(id,nom);
            metflix.afegirUsuari(u);
        }
        // 3. Cercar un usuari i simular que visualitza unes quantes pel·lícules.
        Usuari u = metflix.buscarUsuari("U3");
        metflix.visualitzar(u,metflix.buscarPerId("P001"));
        metflix.visualitzar(u,metflix.buscarPerId("P006"));
        metflix.visualitzar(u,metflix.buscarPerId("P008"));
        // 4. Generar les recomanacions per a aquest usuari.
        metflix.generarRecomanacions("U3");
        // 5. Mostrar per pantalla les recomanacions obtingudes (usant el mètode seguentRecomanacio).
        Queue<Pelicula> recomanacions = u.getRecomanacions();
        System.out.println("Recomanaciones per usuari: "+ u.getNom());
        System.out.println(recomanacions);
        // 6. Mostrar el TOP 3 de pel·lícules millor valorades del catàleg.
        List<Pelicula> top3=metflix.topValorades(3);
        System.out.println("top 3 pelicules del catalge");
        System.out.println(top3);
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
            List<Pelicula> nuevoGenero=new ArrayList<>();
            perGenere.put(genere,nuevoGenero);
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

        return usuaris.get(id);
    }

    public List<Pelicula> topValorades(int n) {
        // TODO: Retornar les n pel·lícules amb millor puntuació
        // Pista: Convertir el Map a List i usar Collections.sort()
        List<Pelicula> listpeliculas=new ArrayList<>();

          for (String id: cataleg.keySet()){
              Pelicula p = cataleg.get(id);
              listpeliculas.add(p);
          }
          Collections.sort(listpeliculas);
         List<Pelicula> result = new ArrayList<>();
        for (int i = listpeliculas.size()-1; i >= 0; i--) {
            result.add(listpeliculas.get(i));
        }
        return result;

    }

    public void visualitzar(Usuari u, Pelicula p) {
        // TODO: Afegir l'ID de la pel·lícula a l'historial de l'usuari
       u.getHistorial().add(p.getId());
    }

    public void generarRecomanacions(String idUsuari) {
        // TODO: Tasca avançada
        // 1. Trobar quin és el gènere que l'usuari ha vist més vegades.
        // 2. Cercar pel·lícules d'aquell gènere que l'usuari ENCARA NO hagi vist.
        // 3. Afegir-les a la cua de recomanacions de l'usuari.
        Usuari u= buscarUsuari(idUsuari);
        String genereMesVist=trobaGenereMesVist(u);
        //Una vegada hem obtingut el genere que s'ha vist mes vegades
        //ara hem de cercar al cataleg pelicules d'aquest genere que lusuari encara noha vist
        List<Pelicula> pelGenere = perGenere.get(genereMesVist);
        for(Pelicula p : pelGenere){
            if (!u.getHistorial().contains(p.getId())){
                u.getRecomanacions().add(p);
            }
        }
    }

    private String trobaGenereMesVist(Usuari u) {
        Map<String, Integer> map1 = new HashMap<>();
        Set<String> historial=u.getHistorial();
        for (String idP:historial){
            Pelicula p=buscarPerId(idP);
            String gen = p.getGenere();
            if (map1.containsKey(gen)){
                int vegades=map1.get(gen)+1;
                map1.put(gen,vegades);
            }else {
                map1.put(gen,1);
            }
        }
        String genereMesVist="";
        int vegades=0;
        for (String gen: map1.keySet()){
            int n=map1.get(gen);
            if (n> vegades){
                vegades=n;
                genereMesVist=gen;
            }
        }
        return genereMesVist;
    }

    public Pelicula seguentRecomanacio(String idUsuari) {
        // TODO: Retornar i extreure el següent element de la cua de recomanacions (poll)
        Usuari u=buscarUsuari(idUsuari);
        return  u.getRecomanacions().poll();
    }
}
