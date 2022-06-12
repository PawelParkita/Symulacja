import java.util.Scanner;

public class Simulation {

    void start_simulation() {
        int[]tab;
        Data data = new Data();
      //  tab=data.Data();
      //  Map map = new Map(tab[0], tab[1], tab[2], tab[3], tab[5], tab[4], tab[6]);
        Map map = new Map(25, 25,4, 3, 20,20,70);
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
       if (map.loops_witout_action>=20000) return false;

    return true;
    }
}
