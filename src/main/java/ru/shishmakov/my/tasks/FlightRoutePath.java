package ru.shishmakov.my.tasks;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FlightRoutePath {

    record Flight(String cityA, String cityB) {
        @Override
        public String toString() {
            return String.format("(%s, %s)", cityA, cityB);
        }
    }

    public List<String> findRoutePath(List<Flight> flights) {
        // A -> [B]
        // B -> [A, C]
        // C -> [B, D]
        // D -> [C, E]
        // E -> [D]
        var mapCities = new HashMap<String, Set<String>>();
        for (Flight flight : flights) {
            mapCities.merge(flight.cityA, Set.of(flight.cityB), (old, next) -> {
                var combined = new HashSet<String>(old.size() + next.size());
                combined.addAll(old);
                combined.addAll(next);
                return combined;
            });
            mapCities.merge(flight.cityB, Set.of(flight.cityA), (old, next) -> {
                var combined = new HashSet<String>(old.size() + next.size());
                combined.addAll(old);
                combined.addAll(next);
                return combined;
            });
        }

        String headCity = null;
        for (Map.Entry<String, Set<String>> entry : mapCities.entrySet()) {
            if (entry.getValue().size() == 1) {
                headCity = entry.getKey();
                break;
            }
        }

        var result = new LinkedHashSet<String>();
        result.add(headCity);
        String lastVisitedCity = headCity;
        while (!mapCities.isEmpty()) {
            Set<String> destCities = mapCities.remove(lastVisitedCity);
            for (String city : destCities) {
                if (result.add(city)) {
                    lastVisitedCity = city;
                    break;
                }
            }
        }

        return result.stream().toList();
    }


    public static void main(String[] args) {
        List<Flight> flights = List.of(
                new Flight("A", "B"),
                new Flight("C", "D"),
                new Flight("C", "B"),
                new Flight("D", "E")
        );
        System.out.println("Flights: " + flights);
        List<String> routePath = new FlightRoutePath().findRoutePath(flights);
        System.out.println("Route path: " + routePath);
    }
}
