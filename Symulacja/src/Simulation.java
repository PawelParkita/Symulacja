import java.util.Scanner;

public class Simulation {

    void movement(){
    }

    void attack(){
    }

    void birth(){
    }

    void start_simulation(){
        Map map = new Map(10, 10, 2, 2,5,5, 50);
        map.map_initialization();
        map.map_population();

        for(int i=0; i<3; i++){
            map.print_map();
            System.out.println("");
            map.movement();
        }
    }

}
