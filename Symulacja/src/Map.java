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
                    map[x][y] = "H";
                    Human human = new Human(x, y, random.random_sex());
                    agents.put(human, "H");
                }
                else{
                    map[x][y] = "G";
                    Guardian guard = new Guardian(x,y,random.random_sex(),random.random_power());
                    agents.put(guard, "G");
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
               agents.put(alien,"A");
               aliens--;
           }
       }

    }

    void movement(){
        Set<Agent> agent=agents.keySet();
        for(Agent object:agent) {
            /*
            0 1 2
            3   4
            5 6 7
             */
            Random rand = new Random();
            int next = rand.nextInt(8);

            if(next==0){
                int next_x=object.x-object.speed;
                int next_y=object.y-object.speed;

                if(does_exist(next_x, next_y)){
                    if(map[next_x][next_y]=="."){
                        map[object.x][object.y]=".";
                        map[next_x][next_y]=agents.get(object);
                        object.x=next_x;
                        object.y=next_y;
                    }
                }
            }

            if(next==1){
                int next_x=object.x;
                int next_y=object.y-object.speed;

                if(does_exist(next_x, next_y)){
                    if(map[next_x][next_y]=="."){
                        map[object.x][object.y]=".";
                        map[next_x][next_y]=agents.get(object);
                        object.x=next_x;
                        object.y=next_y;
                    }
                }
            }

            if(next==2){
                int next_x=object.x+object.speed;
                int next_y=object.y-object.speed;

                if(does_exist(next_x, next_y)){
                    if(map[next_x][next_y]=="."){
                        map[object.x][object.y]=".";
                        map[next_x][next_y]=agents.get(object);
                        object.x=next_x;
                        object.y=next_y;
                    }
                }
            }

            if(next==3){
                int next_x=object.x-object.speed;
                int next_y=object.y;

                if(does_exist(next_x, next_y)){
                    if(map[next_x][next_y]=="."){
                        map[object.x][object.y]=".";
                        map[next_x][next_y]=agents.get(object);
                        object.x=next_x;
                        object.y=next_y;
                    }
                }
            }

            if(next==4){
                int next_x=object.x+object.speed;
                int next_y=object.y;

                if(does_exist(next_x, next_y)){
                    if(map[next_x][next_y]=="."){
                        map[object.x][object.y]=".";
                        map[next_x][next_y]=agents.get(object);
                        object.x=next_x;
                        object.y=next_y;
                    }
                }
            }

            if(next==5){
                int next_x=object.x-object.speed;
                int next_y=object.y+object.speed;

                if(does_exist(next_x, next_y)){
                    if(map[next_x][next_y]=="."){
                        map[object.x][object.y]=".";
                        map[next_x][next_y]=agents.get(object);
                        object.x=next_x;
                        object.y=next_y;
                    }
                }
            }

            if(next==6){
                int next_x=object.x;
                int next_y=object.y+object.speed;

                if(does_exist(next_x, next_y)){
                    if(map[next_x][next_y]=="."){
                        map[object.x][object.y]=".";
                        map[next_x][next_y]=agents.get(object);
                        object.x=next_x;
                        object.y=next_y;
                    }
                }
            }

            if(next==7){
                int next_x=object.x+object.speed;
                int next_y=object.y+object.speed;

                if(does_exist(next_x, next_y)){
                    if(map[next_x][next_y]=="."){
                        map[object.x][object.y]=".";
                        map[next_x][next_y]=agents.get(object);
                        object.x=next_x;
                        object.y=next_y;
                    }
                }
            }
        }
    }

    boolean does_exist(int x, int y){
        if(x<0 || y<0) return false;
        if(x>=width || y>=height) return false;
        return true;
    }

    void print_map(){
        //statistics
        int guardians=0;
        int aliens=0;;
        int humans=0;

        Set<Agent> agent=agents.keySet();
        for(Agent object:agent) {
            if(agents.get(object)=="A") aliens++;
            if(agents.get(object)=="H") humans++;
            if(agents.get(object)=="G") guardians++;

        }

        //printing
        System.out.println("H: "+humans);
        System.out.println("G: "+guardians);
        System.out.println("A: "+aliens);

        for(int i=0; i<height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(map[j][i]+" ");
            }
            System.out.println("");
        }
    }
}
