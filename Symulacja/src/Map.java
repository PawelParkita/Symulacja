import java.util.ArrayList;
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
    int human_population;
    HashMap <Agent,String> agents = new HashMap<>();

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
            System.out.println(x+" "+y);

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
            System.out.println(x+" "+y);

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
        //generate humans
       int humans=human_population;
        while(humans>0){
            Random rand=new Random();
            int x = rand.nextInt((width - 2) + 1) + 1;
            int y = rand.nextInt((height - 2) + 1) + 1;

            if(map[x][y]==".") {
                if(rand.nextInt(100)<probability) {
                    map[x][y] = "H";
                    Human human = new Human(x, y);
                    agents.put(human, "H");
                }
                else{
                    map[x][y] = "G";
                    Guardian guard = new Guardian(x,y);
                    agents.put(guard, "G");
                }
                humans--;
            }
        }

        // generate aliens
       int aliens=alien_population;
       while(aliens>0){
           Random rand=new Random();
           int x = rand.nextInt((width - 2) + 1) + 1;
           int y = rand.nextInt((height - 2) + 1) + 1;

           if(map[x][y]==".") {
               map[x][y]="A";
               Alien alien = new Alien(x,y);
               agents.put(alien,"A");
               aliens--;
           }
       }
       Set< Agent > agent = agents.keySet();
       for (Agent object:agent)
       {
           System.out.println(object.x+" "+object.y+" "+agents.get(object));
       }
    }

    void print_map(){
        for(int i=0; i<height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(map[j][i]+" ");
            }
            System.out.println("");
        }
    }
}
