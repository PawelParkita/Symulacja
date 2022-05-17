public class Map {
    String[][] map;
    int alien_population, human_population, probability,moutains,lakes;
    public Map(int lakes, int mountains,int probalility,int alien_population, int human_population)
    {
        this.alien_population=alien_population;
        this.human_population=human_population;
        this.lakes=lakes;
        this.moutains=mountains;
        this.probability=probalility;

    }

    void map_initialization(){
    }

}
