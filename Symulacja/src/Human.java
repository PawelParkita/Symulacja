import java.util.Random;

public class Human extends Agent{

    char sex;

    public Human(int x, int y){
        super(x,y);
        Random rand=new Random();
        int s = rand.nextInt(2);
        if (s==0)  this.sex='M';
        else this.sex='W';
     }






}
