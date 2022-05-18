public class Simulation {

    void movement(){
    }

    void attack(){
    }

    void birth(){
    }

    void start_simulation(){
        Map map = new Map(25, 25, 5, 5, 0);
        map.map_initialization();
        map.print_map();
    }

}
