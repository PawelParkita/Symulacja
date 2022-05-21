import java.util.Random;

public class Alien extends Agent {

    int power,speed;


    public Alien( int x, int y) {
        super (x,y);
        Random rand=new Random();
        this.power=rand.nextInt(100);
        this.speed= rand.nextInt(3)+1;



    }

}
