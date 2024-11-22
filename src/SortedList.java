import javax.swing.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class SortedList {

    private ArrayList<String> initialList;
    private ArrayList<String> sortedList;

    public SortedList(){
        initialList = new ArrayList<>();
    }

    public void add(String str){
        initialList.add(str);
        sortedList = new ArrayList<>(initialList.stream().sorted().collect(Collectors.toList()));
    }

    public void displaySortedList(JTextArea guiTextArea){
        String elementResults = "";
        elementResults += "Here is our list: \n";
        for (int i = 0; i < sortedList.size(); i++){
            elementResults += String.format("%s\n", sortedList.get(i));
        }
        guiTextArea.setText(elementResults);
    }


    //To create a Binary Search method we need to be able to take the ArrayList<String> as a parameter, and a String we want to find in the Arraylist.
    //We also need to pass in a JTextArea to know where to send the result text to.
    public void binarySearch(String stringToFind, JTextArea guiTextArea){

        int low = 0;// Represents the first index (first element) of the arraylist.
        int high = sortedList.size()-1; //ArrayList.size() gives you a non-zero based number on how many elements are in the array,
        // but we need an index position. So we find the length and subtract 1 to find the last index (last element)
        // of the arraylist.
        while(low <= high){// We are going to loop this until the low and high cross path/ overlap each other
            int middlePosition = (low + high) /2; //We find the middle element between the min and max of the arraylist
            String middleString = sortedList.get(middlePosition); // We set an int(middleNumber) that gets the value of the middle index.

            int comparisonResult = stringToFind.compareTo(middleString);//NEW: Have to use the compareTo() method. Returns a int value.
            //0 means the String is in the middle of the ArrayList. (-n, 0, +n)

            //If the stringToFind is equal to the middleString then we display to the TA what index it was at.
            if (comparisonResult == 0){
                 guiTextArea.setText("We have found your element: " + middleString + "\nIt was in index : " + middlePosition);
                 return; //"return" will exit the entire function, preventing any code after it from executing

            }
            //If we get a negative value from the comparisonResult, we will ignore the top half and set the high index
            //to be the middle index -1. We will now look between index 0 through middle position minus 1.
            if (comparisonResult < 0){
                System.out.println(comparisonResult);// This is just to show us what comparisonResult's value is.
                high = middlePosition -1;
            }
            //Should our comparisonResult is greater than 0 (a positive value), then we will ignore the bottom half and
            // set the low index to be the middle position plus 1. We will now look between middle position plus 1 though high index (sorted.size()-1).
            else {
                low = middlePosition + 1;
            }
        }
        //low is equal -1, which has information about where the element would be inserted if you wanted to add it to the list
        int insertionPoint = low;
        guiTextArea.setText("Element not found.\nWould've been inserted at index: " + insertionPoint);
    }
}
