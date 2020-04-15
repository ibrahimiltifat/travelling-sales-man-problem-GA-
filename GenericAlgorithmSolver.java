import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GenericAlgorithmSolver {
    private static final double mutationRate = 0.02;
    private static final int tournamentSize = 25;
    private static final boolean elitism = true;
    private static int    rate=2;
    public static RouteManager evolveRoute(RouteManager routes) {

        RouteManager newgen= new RouteManager(50,false);
        RouteManager copy = routes;


        for( int i=0;i<tournamentSize;i++){
           Route best =tournamentSelection(copy);
           newgen.setRoute(i,best);
            copy.setRoute(i,copy.getRoute(i+1));
        }
        //store routes in an Array list and deal with them

        int x=24;
        for(int a=0; a<25;a=a+1){
            newgen.setRoute(x,(crossover(newgen.getRoute(a),newgen.getRoute(a+2))));
            x++;
        }
        newgen.setRoute(49,(mutate(newgen.getRoute(1))));


                int randomInt = (int) (50.0 * Math.random());
                newgen.setRoute(randomInt, mutate(newgen.getRoute(randomInt)));


        rate++;
        return newgen; // for avoiding error
    }

    public static Route crossover(Route parent1, Route parent2) {
        ArrayList<City> p1 = new ArrayList<City>(parent1.getroute());
        ArrayList<City> p2 = new ArrayList<City>(parent2.getroute());

        ArrayList<City> child= new ArrayList<City>(p1.subList(10,20));
        p2.removeAll(child);
        Collections.shuffle(p2);
        child.addAll(p2);

        Route Child = new Route(child);
        return Child; // for avoiding error
    }

    private static Route mutate(Route route) {
        // YOUR CODE HERE

        ArrayList<City> copy= new ArrayList<City>(route.getroute());
        ArrayList<City> mroute= new ArrayList<City>(copy.subList(0,16));
        copy.removeAll(mroute);
        mroute.addAll(copy);
        Route Mroute= new Route(mroute);

        return Mroute;
    }

    private static Route tournamentSelection(RouteManager routes) {

        RouteManager copy = routes;

        return copy.findBestFitness(); // for avoiding error
    }
}
