import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;

class Snake {
    int head,tail;
    Snake(int head, int tail)
    {
        this.head=head;
        this.tail=tail;
    }
}

class Ladder
{
    int top, bottom;
    Ladder(int top, int bottom)
    {
        this.top=top;
        this.bottom=bottom;
    }
}

class Player
{
    String name;
    Player(String name)
    {
        this.name = name;
    }
    int progress = 0,rand;
    void move()
    {
        int min = 1, max = 6;
        rand = ThreadLocalRandom.current().nextInt(min, max+1);
        
        //Start dice should be 6
        if(this.progress == 0 && rand == 6)
        {
            this.progress = 1;
        }
        else if (this.progress == 0 && rand!=6)
        {
            this.progress = 0;
        }
        
        else 
        {
            this.progress += rand;
            if(this.progress > 100) // player cannot exceed 100;
            {
                this.progress -= this.rand;
            }
        }
    }
    boolean win(int p)
    {
        if( p == 100 )
        {
            return true;
        }
        return false;
    }
}

class SnakeLadder
{
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);        
        Snake s[] = new Snake[8];
        Ladder l[] = new Ladder[8];

        s[0] = new Snake(29,9);
        s[1] = new Snake(38,15);
        s[2] = new Snake(47,5);
        s[3] = new Snake(53,33);
        s[4] = new Snake(62,37);
        s[5] = new Snake(86,54);
        s[6] = new Snake(92,70);
        s[7] = new Snake(97,25);

        l[0] = new Ladder(23,2);
        l[1] = new Ladder(34,8);
        l[2] = new Ladder(77,20);
        l[3] = new Ladder(68,32);
        l[4] = new Ladder(79,41);
        l[5] = new Ladder(88,74);
        l[6] = new Ladder(100,82);
        l[7] = new Ladder(95,85);

        System.out.println("Enter name of player 1: ");
        String n1 = scanner.nextLine();

        System.out.println("Enter name of player 2: ");
        String n2 = scanner.nextLine();
        System.out.println();

        Player p1 = new Player(n1);
        Player p2 = new Player(n2);

        int i;
        while(true)
        {
            System.out.print(p1.name+"'s move...press Enter ");
            scanner.nextLine();
            p1.move();
            System.out.println(p1.name+" throws "+p1.rand+" and is at " + p1.progress);
            for(i = 0; i<7; i++)
            {
                if(p1.progress == s[i].head)
                {
                    System.out.println("SNAKE BITE AT "+p1.progress+"!");
                    p1.progress = s[i].tail;
                    System.out.println(p1.name+" is at " + p1.progress +"\n");
                    break;
                }
                else if(p1.progress == l[i].bottom)
                {
                    System.out.println("LADDER AT "+p1.progress+"!");
                    p1.progress = l[i].top;
                    System.out.println(p1.name+" is at " + p1.progress +"\n");
                    break;
                }
            }

            if(i == 5)
            {
                System.out.println();
            }

            if(p1.win(p1.progress))
            {
                System.out.println(p1.name+" Won the Game");
                break;
            }

            System.out.print(p2.name+"'s move...press Enter ");
            scanner.nextLine();
            p2.move();
            System.out.println(p2.name+" throws "+p2.rand+" and is at " + p2.progress);
            for(i = 0; i<7; i++)
            {
                if(p2.progress == s[i].head)
                {
                    System.out.println("SNAKE BITE AT "+p2.progress+"!");
                    p2.progress = s[i].tail;
                    System.out.println(p2.name+" is at " + p2.progress +"\n");
                    break;
                }
                else if(p2.progress == l[i].bottom)
                {
                    System.out.println("LADDER AT "+p2.progress+"!");
                    p2.progress = l[i].top;
                    System.out.println(p2.name+" is at " + p2.progress +"\n");
                    break;
                }
            }

           if(i == 5)
            {
                System.out.println();
            }

            if(p2.win(p2.progress))
            {
                System.out.println(p2.name + " Won the Game");
                break;
            }
        }
    }
}