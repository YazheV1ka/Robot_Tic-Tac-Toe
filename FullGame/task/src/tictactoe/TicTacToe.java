package tictactoe;
import javax.swing.*;

public class TicTacToe extends JFrame {

    public TicTacToe() {
        super("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,700);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        Board board = new Board();
        add(board);
    }
}
