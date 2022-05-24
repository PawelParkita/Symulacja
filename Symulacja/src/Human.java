import java.util.Random;

public class Human extends Agent{

   // char sex;

    //char S(){
    //Random rand=new Random();
    //int s = rand.nextInt(2);
    //if (s==0)  this.sex='M';
    //else this.sex='W';
    //return sex;
    //}

    public Human(int x, int y,char sex){
        super(x,y,sex,0,1);
        //Random rand=new Random();
        //int s = rand.nextInt(2);
        //if (s==0)  this.sex='M';
        //else this.sex='W';
     }


    public Human(int x, int y, char sex, int power) {
        super(x,y,sex,power,1);
    }
}
