package ds.powergame;

/**
 * Created by Evgenii on 15. 11. 15..
 */
public class PowerGame {
    private ParPtrTree PPT;
    private Team[] TeamList;
    private static final int MAXPLAYER = 100000;


    public PowerGame(){
        PPT = new ParPtrTree(MAXPLAYER);
        TeamList = new Team[MAXPLAYER];
    }

    public boolean Merge(int player1, int player2){
        // fill your code

        int a = getPlayerPower(player1);
        int b = getPlayerPower(player2);


        if(a == b){
            System.out.println("Merge Failed");

        }else{
            if (a > b ){

                PPT.UNION(player1, player2);
                //Integer tmp_result = TeamList[player1].GetTeamPower() + TeamList[player2].GetTeamPower();
                //TeamList[player1].setLeader(tmp_result);

                //TeamList[player1].addTeamPower(TeamList[player2].GetTeamPower());
                TeamList[PPT.FIND(player1)].addTeamPower(b);
                System.out.println("MERGE: " + player1 + " " + player2);
            }
            else if(a < b){
                PPT.UNION(player2, player1);
               // TeamList[player2].addTeamPower(TeamList[player1].GetTeamPower());
                TeamList[PPT.FIND(player2)].addTeamPower(a);
                System.out.println("MERGE: " + player1 + " " + player2);
            }
        }
        return true;
    }

    public void Login(int player, int power){
        // fill your code
        TeamList[player] = new Team(player, power);
        // System.out.println(TeamList[player].GetTeamPower());
    }

    public void PrintLeader(int player){
        // fill your code

        System.out.println("LEADER: " + TeamList[PPT.FIND(player)].GetLeader());
    }

    public void PrintPower(int player){
        // fill your code
        System.out.println("POWER: " + TeamList[PPT.FIND(player)].GetTeamPower());
    }

    public static void main(String[] arg){
        PowerGame pg = new PowerGame();
        pg.Login(1, 100);
    }

    private int getPlayerPower(int player) {
        return TeamList[PPT.FIND(player)].GetTeamPower();
    }
}

