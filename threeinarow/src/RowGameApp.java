import controller.RowGameController;


public class RowGameApp 
{
    /**                                                                             
     * Starts a new game in the GUI.
     */
    public static void main(String[] args) throws IllegalArgumentException{
        int rows = 3;
        int cols = 3;
        if (rows!=cols || rows<=0 || cols<=0)
        {
            throw new IllegalArgumentException("ERROR:");
        }
        RowGameController game = new RowGameController(rows, cols);
        game.startUp();
    }
}
