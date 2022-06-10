import java.util.*;
import java.util.Random;

public class Map {
    String[][] map;
    int width;
    int height;
    int mountains;
    int lakes;
    int probability;
    int alien_population;
    static int human_population;
    int loops_witout_action;
    int loops;
    HashMap <Integer,Agent> agents = new HashMap<>();
    HashMap <Integer,Agent> agents_copy = new HashMap<>();

    Map(int width, int height, int mountains, int lakes,int alien_population,int human_population, int probability){
        this.width=width;
        this.height=height;
        this.mountains=mountains;
        this.lakes=lakes;
        this.alien_population=alien_population;
        this.human_population=human_population;
        this.probability=probability;
    }

    void map_initialization(){

        map = new String[width][height];

        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                map[j][i]=".";
            }
        }

        //generate mountains
        while(mountains>0){
            Random rand=new Random();
            int x = rand.nextInt((width - 2) + 1) + 1;
            int y = rand.nextInt((height - 2) + 1) + 1;
            //
            // System.out.println(x+" "+y);

            if((map[x][y]==".")&&(map[x-1][y]==".")&&(map[x-1][y-1]==".")&&(map[x][y-1]==".")) {
                map[x][y]="^";
                map[x-1][y]="^"; //left
                map[x-1][y-1]="^"; //top left
                map[x][y-1]="^"; //top
                mountains--;
            }
        }

        //generate lakes
        while(lakes>0){
            Random rand=new Random();
            int x = rand.nextInt((width - 2) + 1) + 1;
            int y = rand.nextInt((height - 2) + 1) + 1;
            //System.out.println(x+" "+y);

            if((map[x][y]==".")&&(map[x-1][y]==".")&&(map[x-1][y-1]==".")&&(map[x][y-1]==".")) {
                map[x][y]="o";
                map[x-1][y]="o"; //left
                map[x-1][y-1]="o"; //top left
                map[x][y-1]="o"; //top
                lakes--;
            }
        }
    }

   void map_population(){
        Randomizer random = new Randomizer();
        //generate humans
       int humans=human_population;
        while(humans>0){
            Random rand=new Random();
            int x = rand.nextInt(width );
            int y = rand.nextInt(height) ;

            if(map[x][y]==".") {
                if(rand.nextInt(100)<probability) {

                    map[x][y] = "G";
                    Guardian guard = new Guardian(x,y,random.random_sex(),random.random_power());
                    int z=(y*height)+x;
                    agents.put(z, guard);
                }
                else{
                    map[x][y] = "H";
                    Human human = new Human(x, y, random.random_sex());
                    int z=(y*height)+x;
                    agents.put(z,human);
                }
                humans--;
            }
        }

        // generate aliens
       int aliens=alien_population;
       while(aliens>0){
           Random rand=new Random();
           int x = rand.nextInt(width );
           int y = rand.nextInt(height );

           if(map[x][y]==".") {
               map[x][y]="A";
               Alien alien = new Alien(x,y,random.random_power(),random.random_speed());
               int z=(y*height)+x;
               agents.put(z,alien);
               aliens--;
           }
       }
    }

    void movement(){
        loops++;
        loops_witout_action++;
        agents_copy.clear(); // copy map list
        Set<Integer> agent=agents.keySet();
        for (Integer object:agent)
        {
            agents_copy.put(object,agents.get(object));
        }


//        for (int i=0;i<width;i++) {
//            for (int j=0;j<height;j++) {
        Set< Integer > agenty = agents_copy.keySet();
        for (Integer object: agenty)
        {
         int j= agents.get(object).x;
         int i= agents.get(object).y;
         int z = (i * height) + j;
               if (agents_copy.containsKey(z)) {
                //    System.out.println("tak " + z);
                    int next_x, next_y;
                    do {
                        int next[] = human_exist(agents.get(z).x,agents.get(z).y,agents.get(z).speed);
                        next_x=next[0];
                        next_y=next[1];

                        if (next_x==-1) {
                            int nexty[] = agents.get(z).mov();
                            next_x = nexty[0];
                            next_y = nexty[1];
                        }
                    } while (!does_exist(next_x, next_y));
                    //System.out.println(next_y * height + next_x);

                    if (map[next_x][next_y] == ".") { // if cell is empty
                        agents.put((next_y * height + next_x), agents.get(z));
                        agents.remove(z);
                        agents.remove(z, agents.get(z));

                        map[next_x][next_y] = map[j][i];
                        map[j][i] = ".";
                    }
                    else if (map[next_x][next_y] == "o" || map[next_x][next_y] == "^" )
                    {
                        break;
                    }
                    else if (agents.get(z).sex == 'A') { // if alien exist

                        if ((agents.get(next_y * height + next_x).sex == 'W') || (agents.get(next_y * height + next_x).sex == 'M')) { // when alien -> human
                            loops_witout_action=0;
                            if (agents.get(next_y * height + next_x).power < agents.get(z).power) { //alien win
                                agents.get(z).power++;
                                agents.put(next_y * height + next_x, agents.get(z));
                                agents_copy.remove(next_y * height + next_x);
                                agents.remove(z);
                                agents.remove(z, agents.get(z));
                                human_population--;
                                map[next_x][next_y] = map[j][i];
                                map[j][i] = ".";
                            } else if (agents.get(next_y * height + next_x).power > agents.get(z).power) //human win
                            {
                                agents.remove(z);
                                agents.remove(z, agents.get(z));
                                alien_population--;
                                map[j][i] = ".";
                            } else { // alien power = human power
                                if (alien_population > human_population) {
                                    agents.put(next_y * height + next_x, agents.get(z));
                                    agents_copy.remove(next_y * height + next_x);
                                    agents.remove(z);
                                    agents.remove(z, agents.get(z));
                                    human_population--;
                                    map[next_x][next_y] = map[j][i];
                                    map[j][i] = ".";
                                } else {
                                    agents.remove(z);
                                    agents.remove(z, agents.get(z));
                                    alien_population--;
                                    map[j][i] = ".";
                                }
                            }
                        }
                    }
                    else if ((agents.get(z).sex == 'W') || (agents.get(z).sex == 'M')) { // if human exist

                        if (((agents.get(next_y * height + next_x).sex == 'W') && (agents.get(z).sex == 'M')) || ((agents.get(z).sex == 'W' && (agents.get(next_y * height + next_x).sex == 'M')))) { // humans other sex
                            Random rand = new Random();
                            Randomizer random = new Randomizer();
                            if (rand.nextInt(100) < 30 && human_population<((width*height-4*(mountains+lakes))/2)) {
                                loops_witout_action=0;

                                int x, y;
                                do {
                                    x = rand.nextInt(width);
                                    y = rand.nextInt(height);
                                } while (!(map[x][y] == "."));

                                if (rand.nextInt(100) < probability) {

                                    map[x][y] = "G";
                                    Guardian guard = new Guardian(x, y, random.random_sex(), random.random_power());
                                    int p = (y * height) + x;
                                    agents.put(p, guard);
                                } else {
                                    map[x][y] = "H";
                                    Human human = new Human(x, y, random.random_sex());
                                    int p = (y * height) + x;
                                    agents.put(p, human);

                                }
                                human_population++;
                            } else if (agents.get(next_y * height + next_x).sex == 'A') { // human -> alien
                                loops_witout_action=0;
                                if (agents.get(next_y * height + next_x).power < agents.get(z).power) { //human win
                                    agents.put(next_y * height + next_x, agents.get(z));
                                    agents.remove(z);
                                    agents.remove(z, agents.get(z));
                                    alien_population--;
                                    map[next_x][next_y] = map[j][i];
                                    map[j][i] = ".";
                                } else if (agents.get(next_y * height + next_x).power > agents.get(z).power) { //alien win
                                    agents.remove(z);
                                    agents.remove(z, agents.get(z));
                                    human_population--;
                                    map[j][i] = ".";
                                } else {
                                    if (alien_population > human_population) {
                                        agents.remove(z);
                                        agents.remove(z, agents.get(z));
                                        human_population--;
                                        map[j][i] = ".";
                                    } else {
                                        agents.put(next_y * height + next_x, agents.get(z));
                                        agents.remove(z);
                                        agents.remove(z, agents.get(z));
                                        alien_population--;
                                        map[next_x][next_y] = map[j][i];
                                        map[j][i] = ".";
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    //}

    boolean does_exist(int x, int y){
        if(x<0 || y<0) return false;
        if(x>=width || y>=height) return false;
        return true;
    }
    int[] human_exist(int x, int y, int speed){
        int [] tab = new int[2];
        tab[0]=-1;
        tab[1]=-1;
        if ( does_exist((x - speed),(y - speed)) && (map[(x - speed)][(y - speed)]=="H" || map[(x - speed)][(y - speed)]=="G" )) {
            tab[0] = x - speed;
            tab[1] = y - speed;
        }
        else if (does_exist((x),(y - speed)) && (map[(x)][(y - speed)]=="H" || map[(x)][(y - speed)]=="G" )) {
            tab[0] = x ;
            tab[1] = y - speed;
        }
        else if (does_exist((x + speed),(y - speed)) && (map[(x + speed)][(y - speed)]=="H"|| map[(x + speed)][(y - speed)]=="G" )) {
            tab[0] = x + speed ;
            tab[1] = y - speed;
        }
        else if (does_exist((x - speed),y ) && (map[(x - speed)][y]=="H" || map[(x - speed)][y]=="G")) {
            tab[0] = x - speed;
            tab[1] = y;
        }

        else if (does_exist((x + speed),y ) && (map[(x + speed)][y]=="H" || map[(x + speed)][y]=="G")) {
            tab[0] = x + speed;
            tab[1] = y;
        }

        else if (does_exist((x - speed),(y + speed)) && (map[(x - speed)][(y + speed)]=="H" || map[(x - speed)][(y + speed)]=="G")) {
            tab[0] = x - speed;
            tab[1] = y + speed;
        }
        else if (does_exist(x,(y - speed)) && (map[x][(y - speed)]=="H"|| map[x][(y - speed)]=="G")) {
            tab[0] = x;
            tab[1] = y + speed;
        }

       else if (does_exist((x + speed),(y + speed)) && (map[(x + speed)][(y + speed)]=="H" || map[(x + speed)][(y + speed)]=="G")) {
            tab[0] = x + speed;
            tab[1] = y + speed;
        }

        return tab;
    }



    void print_map(){
        //statistics printing
        System.out.println("H: "+human_population);
        System.out.println("A: "+alien_population);
        System.out.println("L: "+loops);
        System.out.println("Lo: "+loops_witout_action);
        // Map printing
        for(int i=0; i<height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(map[j][i]+" ");
            }
            System.out.println("");
        }
    }
}
