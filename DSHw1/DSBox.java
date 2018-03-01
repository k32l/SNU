/**
 * Created by Evgenii on 15. 9. 27..
 */
/**
 * Implement this class using SingleLinkedList
 * @author DMLab
 *
 */
public class DSBox {
    private SingleLinkedList<String> list;



    DSBox(){
        list = new SingleLinkedList<String>();
    }


    /**
     *
     * @param document
     */
    //Add the new document at the top of DSBox.
    public void submit(String document){
        list.moveToStart();
        list.insert(document);
    }

    /**
     *
     * @return
     */
    public String get_top(){

        list.moveToStart();
        if (list.length()  == 0) {
            System.out.print("Box is empty");
            return null;
        } else
            return list.remove();

    }



    /**
     *
     * @return
     */
    public String get_bottom(){
        list.moveToEnd();
        if (list.length()  == 0) {
            System.out.print("Box is empty");
            return null;
        } else{
            list.prev();
            return list.remove();}
    }

    /**
     *
     * @return
     */
    public String view_top(){
        if (list.length()  == 0) {
            System.out.print("Box is empty");
            return null;}
        else {
        list.moveToStart();
        return list.getValue();}
    }

    /**
     *
     * @return
     */
    public String view_bottom(){
        if (list.length()  == 0){
            System.out.print("Box is empty");
        return null;}
        else {list.moveToEnd();
        list.prev();
        //System.out.println("view_bottom" + list.getValue());
        return list.getValue();}

    }

    /**
     *
     * @return
     */
    public int size(){

        return list.length();

    }
}






