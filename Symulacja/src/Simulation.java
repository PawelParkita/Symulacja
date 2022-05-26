import java.util.Scanner;

public class Simulation {

    void movement(){
    }

    void attack(){
    }

    void birth(){
    }

    void start_simulation() {

        Map map = new Map(5, 5, 0, 0, 3, 5, 50);
        map.map_initialization();
        map.map_population();

        //for (int i = 0; i < 3; i++) {
            while (map.human_population > 0 && map.alien_population>0 ) {
            //    map.print_map();
            //    System.out.println("");
                map.movement();
            }
        map.print_map();
    }
}
