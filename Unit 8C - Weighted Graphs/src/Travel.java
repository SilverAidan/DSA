package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Travel {
    public static void main(String[] args) {
        City[] cities = new City[] {
            new City("BDL"), //0 Bradley my boy
            new City("SYD"), //1 Sydney siddy
            new City("CDG"), //2 My man Charles De Gaulle
            new City("LAX"), //3 Be very LAX with LA
            new City("HND"), //4 Honda honda in Tokyo
            new City("EZE"), //5 Buenos Aires
        };

        cities[0].connections = new HashMap<City, Double>() {{ //BDL to LAX, CDG
            put(cities[3], 250.0);
            put(cities[2], 800.0);
        }};

        cities[2].connections = new HashMap<City, Double>() {{ //BDL to LAX, CDG
            put(cities[4], 900.0);
        }};

        cities[3].connections = new HashMap<City, Double>() {{ //LAX to EZE, SYD, HND
            put(cities[5], 500.0);
            put(cities[1], 1000.0);
            put(cities[4], 900.0);
        }};

        cities[5].connections = new HashMap<City, Double>() {{ //EZE to SYD
            put(cities[1], 700.0);
        }};

        HashSet<City> set = new HashSet<City>(Set.of(cities));

        Airline DSAir = new Airline(set, cities[0]);

        System.out.println(DSAir);
        // DSAir.dijkstra();
        DSAir.undirect();
        DSAir.hub = cities[3];
        System.out.println(DSAir);
        ArrayList<Edge> prim = DSAir.prim();
        System.out.println();
        double sum = 0;
        for (Edge e : prim) {
            sum += e.cost;
        }
        System.out.println(sum);
        // DSAir.cities.add(new City("Moscow"));
        System.out.println(DSAir.prim());
        // System.out.println(cities[4].getRoute());

    }
}
