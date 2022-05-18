import java.util.Random;

public class Map {
    String[][] map;
    int width;
    int height;
    int mountains;
    int lakes;
    int probability;

    Map(int width, int height, int mountains, int lakes, int probability){
        this.width=width;
        this.height=height;
        this.mountains=mountains;
        this.lakes=lakes;
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

    void print_map(){
        for(int i=0; i<height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(map[j][i]+" ");
            }
            System.out.println("");
        }
    }
}
