package football.egy.T3Allam;

import java.util.Arrays;
import java.util.*;

public class TikiTaka {

    public static class Player {
        String name;
        Ball football;

        public Player(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void pass(Player passer, Ball football, Player receiver) {
            System.out.println(passer.getName() + " has passed the " + football.getName() + " to " + receiver.getName());
        }

        public void dribble (Ball football) {
            System.out.println(this.getName() + " is dribbling with the ball");
        }
    }

    public static class Ball {
        String name;
        public Ball (String name) {
            this.name = name;
        }
        public String getName(){
            return this.name;
        }
    }

    public static class PlayTheTikiTaka extends Thread {
        Random random = new Random();
        Player gk = new Player("Ter Stegen");
        Player rb = new Player("Dani Alves");
        Player lcb = new Player("Carlos Puyol");
        Player rcb = new Player("Gerard Pique");
        Player lb = new Player("Jordi Alba");
        Player cdm = new Player("Sergio Busquets");
        Player lcm = new Player("Andres Iniesta");
        Player rcm = new Player("Xavi");
        Player lw = new Player("Neymar");
        Player cam = new Player("Messi");
        Player st = new Player("Suarez");

        List<Player> squad = Arrays.asList(gk,rb,rcb,lcb,lb,cdm,rcm,lcm,cam,lw,st);
        Ball football = new Ball("football");

        public void run() {
            //Start with Ter Stegen
            Player whoHasTheBall = squad.get(0);
            //Selecting random player to pass to
            int r = random.nextInt(10);
            //if no player available Ter is dribbling

            while (r==0) {
                squad.get(0).dribble(football);
                r = random.nextInt(10);
            }
            Player receiverFromGoalie = squad.get(r);
            whoHasTheBall.pass(whoHasTheBall, football, receiverFromGoalie);
            whoHasTheBall = receiverFromGoalie;

            while (whoHasTheBall.getName() != "Messi") {
                int r2 = random.nextInt(10);

                while (r2 ==r) {
                    whoHasTheBall.dribble(football);
                    r2 = random.nextInt(10);
                }
                r = r2;
                Player receiver = squad.get(r2);
                whoHasTheBall.pass(whoHasTheBall, football, receiver);
                whoHasTheBall = receiver;
            }
            System.out.println("As usual, no surprise, Messi, the GOAT, smashes the ball into the back of the net");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        PlayTheTikiTaka playTheTikiTaka = new PlayTheTikiTaka();
        playTheTikiTaka.start();
    }
}
