import controller.RowGameController;

/**
Main class. Starts up the game.
 */

public class RowGameApp 
{
    /**                                                                        
     * Starts a new game in the GUI.
     * @param args Command line arguments. Argument 1 is the number of rows (Integer). Argument 2 is the number of columns (Integer). Argument 3 is strategy to be used (String).
     */
    public static void main(String[] args) throws IllegalArgumentException, NumberFormatException{
        if (args.length < 3 || args.length > 3) {
            throw new IllegalArgumentException("ARGUMENT ERROR: Invalid number of command line arguments.");
        } else {
            try {
                int rows = Integer.parseInt(args[0]);
                int cols = Integer.parseInt(args[1]);
                String strategy = args[2];

                if (rows<3 || cols<3) {
                    throw new IllegalArgumentException("ARGUMENT ERROR: Rows and Columns must not be less than 3.");
                }

                RowGameController game = new RowGameController(rows, cols, strategy);
                game.startUp();
            } catch (NumberFormatException e) {
                System.err.println("FORMAT ERROR: Argument 1 and 2 must be integers and Argument 3 must be string");
            }
        }
    }
}
