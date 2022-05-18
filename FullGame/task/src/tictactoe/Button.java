package tictactoe;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {

    public Button(String text) {
        super(text);
        this.setName("Button" + text);
        this.setFont(new Font("Courier", Font.BOLD, 50));
        this.setFocusPainted(false);
        this.setMargin(new Insets(5,  0, 5, 0));
    }

}