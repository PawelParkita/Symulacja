import java.util.Random;

/**
 * Klasa losuje wartosci
 */
public class Randomizer
{
    char random_sex(){
        Random rand=new Random();
        int s = rand.nextInt(2);
        if (s==0)  return 'M';
        else return 'W';

    }

    int random_power(){
    Random rand=new Random();
        return rand.nextInt(100);
}

    int random_speed(){
        Random rand=new Random();
       return rand.nextInt(2)+1;
    }
}
