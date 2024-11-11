import java.util.*;

// This class takes user-inputted notes to compile a song. It also identifies the most
// common natural note which was inputted by the user.
public class MusicBox {
    public static final String NOTES = "CDEFGAB";
    public static final String SHARP = "♯";
    public static final String FLAT = "♭";
    
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String[][] song = composeSong(console);
        System.out.println("Returned song 2D array:");
        for (int i = 0; i < song.length; i++) {
            for (int j = 0; j < song[0].length; j++) {
                System.out.print(song[i][j] + " "); 
            }
            System.out.println();
        }
    }


    // Behavior:
    //  - This method asks for user input for number of melodies and number of notes.
    // Parameters:
    //  - input: the user's input for numbers
    // Returns:
    //  - returns the number of melodies and notes inputted by user as an int[]
    public static int[] getSongStructure(Scanner input) {
        System.out.print("Enter the number of melodies: ");
        String stringMelodies = input.nextLine();
        int numMelodies = Integer.parseInt(stringMelodies);

        System.out.print("Enter the length of each melody: ");
        String stringNotes = input.nextLine();
        int numNotes = Integer.parseInt(stringNotes);

        int[] structure = {numMelodies, numNotes};
        return structure;
    }


    // Behavior:
    //  - This method collects the user inputted notes and shows them to user
    // Parameters:
    //  - input: the user's input for numbers and notes
    // Returns:
    //  - returns the notes the user inputted as a String[][]
    public static String[][] composeSong(Scanner input) {
        int[] songStructure = getSongStructure(input);
        int numMelodies = songStructure[0];
        int numNotes = songStructure[1];
        
        System.out.println();
        String[][] song = new String[numMelodies][numNotes];

        for (int i = 0; i < numMelodies; i++) {
            System.out.println("Composing melody #" + (i + 1));
            for (int j = 0; j < numNotes; j++) {
                System.out.print("  Enter note #" + (j + 1) + ": ");
                song[i][j] = input.nextLine();
            }
            System.out.println();
        }
        return song; 
    }


    // Behavior:
    //  - Finds the natural note which was inputted most in each melody and returns it
    // Parameters:
    //  - song: represents all of the notes which the user inputted AKA the song!
    // Returns:
    //  - returns the natural note which was inputted the most, and in
    //    the case of a tie, the note which is earlier in the sequence as a String[]
    public static String[] mostCommonNaturals(String[][] song) {
        String[] mostCommonNaturals = new String[song.length]; //to create array that is length of the number of melodies
        
        for (int i = 0; i < song.length; i++) {
            int[] count = countNotes(song[i]);
            int mostCommonIndex = getMostCommonNote(count);
            mostCommonNaturals[i] = NOTES.charAt(mostCommonIndex) + "";
        }
        return mostCommonNaturals;
    }


    // Behavior:
    //  - This method counts the number of each natural note the user inputted
    // Parameters:
    //  - melody: represents each melody the user uses in the song
    // Returns:
    //  - returns the number of each natural note in the melody as an int[]
    public static int[] countNotes(String[] melody) {
        int[] count = new int[7];

        for (int i = 0; i < melody.length; i++) {
            String note = melody[i];
            if (note.length() == 1) {
                int index = NOTES.indexOf(note);
                if (index != -1) {
                    count[index]++;
                }
            }
        }
        return count;
    }


    // Behavior:
    //  - This method chooses the note with the highest number of instances
    //    and works as a tiebreaker by choosing the note which is earlier in
    //    note order as the most common note
    // Parameters:
    //  - count: represents the number of instances each natural note was inputted
    //    in a melody
    // Returns:
    //  - returns the index associated with the note that appears
    //    the most in the user's input, and in the case of a tie, the note which 
    //    is earlier in the sequence as an int
    public static int getMostCommonNote(int[] count) {
        int highestIndex = -1;
        int mostCommonIndex = -1; 

        for (int k = 0; k < count.length; k++) {
            if (count[k] > highestIndex) {
            highestIndex = count[k];
            mostCommonIndex = k;
            } 
            else if (count[k] == highestIndex && mostCommonIndex > k) {
                mostCommonIndex = k;
            }
        }
        return mostCommonIndex;
    }
    
}
