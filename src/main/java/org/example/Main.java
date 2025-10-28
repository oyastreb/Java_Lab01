package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        ObjectContainer myContainer = new ObjectContainer();
        myContainer.add(2);
        myContainer.add(1);
        myContainer.add(3);

        System.out.println(myContainer);

        myContainer.remove(1);
        System.out.println(myContainer);

        System.out.println(myContainer.size());
        }

}