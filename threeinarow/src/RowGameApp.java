import controller.RowGameController;


public class RowGameApp 
{
    /**                                                                        
     * Starts a new game in the GUI.
     */
    public static void main(String[] args) throws IllegalArgumentException, NumberFormatException{
        if (args.length < 3 || args.length > 3) {
            throw new IllegalArgumentException("ARGUMENT ERROR: Invalid number of command line arguments.");
        } else {
            try {
                int rows = Integer.parseInt(args[0]);
                int cols = Integer.parseInt(args[1]);
                String strategy = args[2];

                if (rows!=cols || rows<=3 || cols<=3) {
                    throw new IllegalArgumentException("ARGUMENT ERROR: Rows must be equal to Columns and they must not be less than or equal to 3.");
                }

                RowGameController game = new RowGameController(rows, cols, strategy);
                game.startUp();
            } catch (NumberFormatException e) {
                System.err.println("FORMAT ERROR: Argument 1 and 2 must be integers and Argument 3 must be string");
            }
        }
    }
}
