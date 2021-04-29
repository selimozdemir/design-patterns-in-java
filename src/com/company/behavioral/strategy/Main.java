package com.company.behavioral.strategy;

import java.util.*;

/**
 * Strategy pattern provides to family of algoritms work together. These algoritms can be change at runtime.
 * Example:
 *  Route planning with by bus, by walk, by bsycle, by car
 */

interface Node {

    String getName();

    Location getLocation();


}

class Location {
    private float lat;
    private float lng;

    public Location(float lat, float lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }
}

class Place implements Node {
    private Location location;
    private String name;

    public Place(Location location, String name) {
        this.location = location;
        this.name = name;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Place{" +
                "location=" + location +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return location.equals(place.location) &&
                name.equals(place.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, name);
    }
}

class Graph {
    private Map<Node, Set<Node>> neighbors;

    public Graph() {
        this.neighbors = new HashMap<>();
    }

    public Set<Node> addNode(Node node){
        this.neighbors.put(node, new HashSet<>());

        return this.neighbors.get(node);
    }

    public Set<Node> addNode(Node node, Set<Node> neighbors){
        this.neighbors.put(node, neighbors);

        return this.neighbors.get(node);
    }

    public Map<Node, Set<Node>> getNeighbors() {
        return neighbors;
    }
}

interface RouteBuilder {
    String build(Graph graph);
}

class Bus implements RouteBuilder{

    @Override
    public String build(Graph graph) {
        return "You can go from here by bus";
    }
}

class Walk implements RouteBuilder{

    @Override
    public String build(Graph graph) {
        return "You can go from here by walk";

    }
}

class Car implements RouteBuilder{

    @Override
    public String build(Graph graph) {
        return "You can go from here by car";

    }
}

interface MST {

}

class Dijktra implements MST{

}

class RoutePlanner {

    private Graph graph;
    private RouteBuilder routeBuilder;

    public RoutePlanner(Graph graph, RouteBuilder routeBuilder) {
        this.graph = graph;
        this.routeBuilder = routeBuilder;
    }

    public void setRouteBuilder(RouteBuilder routeBuilder) {
        this.routeBuilder = routeBuilder;
    }

    public String buildRoute(){
        return this.routeBuilder.build(graph);
    }
}

public class Main {

    public static void main(String[] args) {
        Node a = new Place(new Location(33.3f,33.4f), "A");
        Node b = new Place(new Location(33.5f,33.6f), "B");
        Node c = new Place(new Location(33.6f,33.7f), "C");
        Node d = new Place(new Location(33.7f,33.8f), "D");
        Node e = new Place(new Location(33.8f,33.9f), "E");
        Node f = new Place(new Location(33.9f,34.0f), "F");

        Graph g = new Graph();
        g.addNode(a).addAll(Arrays.asList(b,c));
        g.addNode(b).addAll(Arrays.asList(c,d));
        g.addNode(c).addAll(Arrays.asList(d,e));
        g.addNode(e).addAll(Arrays.asList(f));

        RoutePlanner planner = new RoutePlanner(g, new Bus());
        String out1 = planner.buildRoute();
        System.out.println(out1);
        planner.setRouteBuilder(new Car());

        String out2 = planner.buildRoute();
        System.out.println(out2);


    }
}
