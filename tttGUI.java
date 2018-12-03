import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class tttGUI extends JFrame {

    private Container pane;
    private String currentPlayer;//keeps track of which player is playing
    private JButton[][] board;//two-d array of buttons that acts as the game board
    private boolean hasWinner;//flag for determining who won
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem quit, newGame;
    private int count;//counter to keep track of spots used for determining a tie at the end

    public tttGUI(){

        pane = getContentPane();
        pane.setLayout(new GridLayout(3,3));
        this.setTitle("Tic-Tac-Toe");
        this.setSize(1000,1000);
        this.setResizable(false);
        ImageIcon image = new ImageIcon("C:/Users/antho/Desktop/tictactoe.jpg");
        this.setIconImage(image.getImage());
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        currentPlayer = "x";
        board = new JButton[3][3];
        hasWinner = false;
        addButton();
        initializeMenuBar();
    }

    //Method for creating the menu bar
    private void initializeMenuBar(){
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        newGame = new JMenuItem("New Game");
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetBoard();
            }
        });
        quit = new JMenuItem("Quit");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               System.exit(0);
            }
        });
        menu.add(newGame);
        menu.add(quit);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    //Method for resetting the game board
    private void resetBoard(){
        currentPlayer = "x";
        hasWinner = false;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                board[i][j].setText("");
                board[i][j].setEnabled(true);
                count=0;//every time we reset the board for a new game, we need to set count to zero otherwise the last
                //count from the previous game would not have been reset and it still increments from whatever number it
                //was before
            }
        }
    }

    //Method for adding the buttons
    private void addButton(){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                JButton button = new JButton();
                board[i][j] = button;
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //Here we are checking for empty spaces and no winner so if both evaluate to true
                        //then we can set text to current player check for winner and change between players
                        if(((JButton)e.getSource()).getText().equals("") && !hasWinner){
                            button.setText(currentPlayer);
                            button.setEnabled(false);
                            button.setFont(new Font("Times New Roman", Font.BOLD, 100));
                            hasWinner();
                            changeBetweenPlayer();
                        }
                    }
                });
                pane.add(button);
            }
        }
    }

    //Simple method to change between players
    private void changeBetweenPlayer(){
        if(currentPlayer.equals("x")){
            currentPlayer = "o";
        }else{
            currentPlayer = "x";
        }
    }

    //A tedious method I created that checks every single possibility of winning for the current player
    //otherwise if there is no win then obviously it is a tie at the end
    private boolean hasWinner(){

        if(board[0][0].getText().equals(currentPlayer) && board[1][0].getText().equals(currentPlayer)
         && board[2][0].getText().equals(currentPlayer)){
            JOptionPane.showMessageDialog(null, "Player " + currentPlayer + "Wins!");
            count++;
            hasWinner = true;
        }
        else if(board[0][1].getText().equals(currentPlayer) && board[1][1].getText().equals(currentPlayer)
               && board[2][1].getText().equals(currentPlayer)){
            JOptionPane.showMessageDialog(null, "Player " + currentPlayer + "Wins!");
            count++;
            hasWinner = true;
        }
        else if(board[0][2].getText().equals(currentPlayer) && board[1][2].getText().equals(currentPlayer)
               && board[2][2].getText().equals(currentPlayer)){
            JOptionPane.showMessageDialog(null, "Player " + currentPlayer + "Wins!");
            count++;
            hasWinner = true;
        }
        else if(board[0][0].getText().equals(currentPlayer) && board[1][1].getText().equals(currentPlayer)
               && board[2][2].getText().equals(currentPlayer)){
            JOptionPane.showMessageDialog(null, "Player " + currentPlayer + "Wins!");
            count++;
            hasWinner = true;
        }
        else if(board[0][2].getText().equals(currentPlayer) && board[1][1].getText().equals(currentPlayer)
               && board[2][0].getText().equals(currentPlayer)){
            JOptionPane.showMessageDialog(null, "Player " + currentPlayer + "Wins!");
            count++;
            hasWinner = true;
        }
        else if(board[0][0].getText().equals(currentPlayer) && board[0][1].getText().equals(currentPlayer)
               && board[0][2].getText().equals(currentPlayer)){
            JOptionPane.showMessageDialog(null, "Player " + currentPlayer + "Wins!");
            count++;
            hasWinner = true;
        }
        else if(board[1][0].getText().equals(currentPlayer) && board[1][1].getText().equals(currentPlayer)
               && board[1][2].getText().equals(currentPlayer)){
            JOptionPane.showMessageDialog(null, "Player " + currentPlayer + "Wins!");
            count++;
            hasWinner = true;
        }
        else if(board[2][0].getText().equals(currentPlayer) && board[2][1].getText().equals(currentPlayer)
               && board[2][2].getText().equals(currentPlayer)){

            JOptionPane.showMessageDialog(null, "Player " + currentPlayer + "Wins!");
            count++;
            hasWinner = true;
        }
        else{
            //after checking for all possible wins above if nobody wins then it is a tie here
            // we use count to keep track of positions filled and check if has winner is false
            count++;
            hasWinner = false;
            if(!hasWinner && count == 9){
                JOptionPane.showMessageDialog(null,"Tie Game!");
            }
        }
        return hasWinner;
    }

}
