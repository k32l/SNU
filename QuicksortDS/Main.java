/**
 * Created by Evgenii on 15. 11. 24..
 */
import java.io.StringReader;
import java.util.Scanner;




public class Main {
    private static final int ERROR = -1;
    private static final int ADD = 0;
    private static final int REMOVE = 1;
    private static final int SORT = 2;
    private static final int TOP = 3;

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int size = 200000;
        Sorter sorter = new Sorter(size);

        while(scanner.hasNext()){
            String line = scanner.nextLine();
            Scanner i_scanner = new Scanner(new StringReader(line));
            String cmd = i_scanner.next();
            int value = 0;
            int k = 0;
            String type = null;

            switch(getCommandNum(cmd)){
                case ADD:
                    value = i_scanner.nextInt();
                    System.out.println("ADD: " + value);

                    // fill your code
                    sorter.add(value);

                    break;
                case REMOVE:
                    value = i_scanner.nextInt();
                    if (sorter.remove(value) == true) {
                        System.out.println("REMOVE: " + value);
                    } else if (sorter.remove(value) == false){
                        System.out.println("The value does not exist");
                    }
                    // fill your code

                    break;
                case SORT:
                    type = i_scanner.next();
                    sorter.sort(type);

                    // fill your code

                    break;

                case TOP:

                    k = i_scanner.nextInt();
                    type = i_scanner.next();
                    sorter.top(k, type);

                    // fill your code

                    break;

            }
            i_scanner.close();
        }

        scanner.close();
    }

    private static int getCommandNum(String cmd){
        if(cmd.equals("add"))
            return ADD;
        if(cmd.equals("remove"))
            return REMOVE;
        else if(cmd.equals("sort"))
            return SORT;
        else if(cmd.equals("top"))
            return TOP;
        else
            return ERROR;
    }

}

