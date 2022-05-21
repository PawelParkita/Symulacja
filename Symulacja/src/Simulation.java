public class Simulation {

    void movement(){
    }

    void attack(){
    }

    void birth(){
    }

    void start_simulation(){
        Map map = new Map(25, 25, 5, 5,10,10, 50);
        map.map_initialization();
        map.map_population();
        map.print_map();
    }

}
