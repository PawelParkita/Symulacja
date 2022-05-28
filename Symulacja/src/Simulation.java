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
            while (stop_simulation(map)){
                map.print_map();
                System.out.println("");
                map.movement();
            }
        map.print_map();
    }

    boolean stop_simulation(Map map) {

       if (map.human_population <= 0) return false;
       if (map.alien_population <=0) return false;
       if (map.loops_witout_action>10000) return false;

    return true;

    }






}
