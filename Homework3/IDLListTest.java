//Jiawei Lu
//20007605

public class IDLListTest {

    public static void main(String[] args) {

        IDLList<Integer> idlList = new IDLList();

        idlList.add(9);
        System.out.println("Add 1 element");
        System.out.println(idlList.toString());

        idlList.add(3);
        System.out.println("Add 1 element");
        System.out.println(idlList.toString());

        idlList.add(0,4);
        System.out.println("Add element at index 0");
        System.out.println(idlList.toString());

        idlList.append(13);
        System.out.println("Add element at last");
        System.out.println( idlList.toString());

        System.out.println("Print the list " + idlList.toString());
        System.out.println("Get element from index 2");
        System.out.println(idlList.get(2));

        System.out.println("Get first element " + idlList.getHead());

        System.out.println("Get last element " + idlList.getLast());


        System.out.println("Get the size " + idlList.size());

        System.out.println("Get the linked list " + idlList.toString());
        System.out.println("Remove first element " + idlList.remove());
        System.out.println("Get the linked list " + idlList.toString());

        System.out.println("Remove the last element " + idlList.removeLast());
        System.out.println("Print the linked list " + idlList.toString());

        System.out.println("Add two elements at first");
        idlList.add(7);
        idlList.add(13);
        System.out.println("Now the list is: " + idlList.toString());
        idlList.remove(9);
        System.out.println("Remove the first occurrence 9");
        System.out.println(idlList.toString());

    }

}
