import java.util.Random;

abstract class Agent {

    int x, y;
    char sex;
    int power, speed;

    public Agent(int x, int y, char sex, int power, int speed) {
        this.x = x;
        this.y = y;
        this.sex = sex;
        this.power = power;
        this.speed = speed;
    }

    public int[] mov() { //
        Random rand = new Random();
        int next = rand.nextInt(8);
        int next_y = -1, next_x = -1;

        if (next == 0) {
            next_x = x - speed;
            next_y = y - speed;
        }

        if (next == 1) {
            next_x = x;
            next_y = y - speed;
        }

        if (next == 2) {
            next_x = x + speed;
            next_y = y - speed;
        }

        if (next == 3) {
            next_x = x - speed;
            next_y = y;
        }

        if (next == 4) {
            next_x = x + speed;
            next_y = y;
        }

        if (next == 5) {
            next_x = x - speed;
            next_y = y + speed;
        }

        if (next == 6) {
            next_x = x;
            next_y = y + speed;
        }

        if (next == 7) {
            next_x = x + speed;
            next_y = y + speed;
        }
        return new int[]{next_x, next_y};
    }
}


