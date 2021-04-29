package com.company.creational.factory;

interface RouteAlgorithm
{
    void calculate();
}

class DijkstraAlgorithm implements RouteAlgorithm {
    @Override
    public void calculate() {

    }
}

abstract class RoutePlanner {

    public void build(){
        RouteAlgorithm a = createFactory();
        a.calculate();

        return;
    }

    abstract RouteAlgorithm createFactory();
}

class DijkstraPlanner extends RoutePlanner {

    @Override
    RouteAlgorithm createFactory() {
        return new DijkstraAlgorithm();
    }
}

public class FactoryMethod {

    public static void main(String[] args) {
        RoutePlanner p = new DijkstraPlanner();
        p.build();
    }
}
