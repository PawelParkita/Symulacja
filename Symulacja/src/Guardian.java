import java.util.Random;

public class Guardian extends Human {

    int power;

    public Guardian( int x, int y) {
        super(x,y);
        Random rand=new Random();
        this.power=rand.nextInt(100);
    }
}
