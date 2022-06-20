/**
 * Klasa  pozwala kotrolowac symulacje
 */
public class Simulation {

    void start_simulation() throws InterruptedException {
        int[]tab;
        Data data = new Data();
        tab=data.Data();
        Map map = new Map(tab[0], tab[1], tab[2], tab[3], tab[5], tab[4], tab[6]);
        //  Map map = new Map(15, 15,2, 1, 40,40,50);
        map.map_initialization();
        map.map_population();


            while (stop_simulation(map)){
                map.print_map();
                map.save_map();
                System.out.println("");
                map.movement();
                Thread.sleep(300);
            }
           // map.save_map();
        map.print_map();
    }

    boolean stop_simulation(Map map) {

       if (map.human_population <= 0  || (map.width>10 && map.human_population < (0.1*map.alien_population)) ) return false;
       if (map.alien_population <=0 || (map.width>10 && map.alien_population < (0.1*map.human_population))) return false;
       if (map.loops_witout_action>=200) return false;

    return true;
    }
}
