package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Board extends JPanel implements ActionListener {
    Random random = new Random();
    private int countClick = 1;
    JPanel buttonsPanel = new JPanel();
    JPanel labelAndReset = new JPanel();
    JPanel topButtonConfiguration = new JPanel();
    Button player1 = new Button("Player1");
    Button startReset = new Button("StartReset");
    Button player2 = new Button("Player2");
    Button[] buttons = new Button[9];
    JLabel label = new JLabel("LabelStatus");


    public Board() {
        setLayout(new BorderLayout());
        this.buttonsPanel.setLayout(new GridLayout(3, 3));

        buttons[0] = new Button("A3");
        buttons[1] = new Button("B3");
        buttons[2] = new Button("C3");
        buttons[3] = new Button("A2");
        buttons[4] = new Button("B2");
        buttons[5] = new Button("C2");
        buttons[6] = new Button("A1");
        buttons[7] = new Button("B1");
        buttons[8] = new Button("C1");
        for (Button button : buttons) {
            button.addActionListener(this);
            this.buttonsPanel.add(button);
            button.setFont(new Font("Comic Sans MS", Font.BOLD, 95));
        }
        add(buttonsPanel, BorderLayout.CENTER);

        this.labelAndReset.setLayout(new BorderLayout());
        label.setName("LabelStatus");
        label.setFont(new Font("Arial", Font.BOLD, 22));
        labelAndReset.add(label, BorderLayout.WEST);
        add(labelAndReset, BorderLayout.PAGE_END);

        this.topButtonConfiguration.setLayout(new GridLayout(0, 3));
        topButtonConfiguration.add(player1);
        topButtonConfiguration.add(startReset);
        topButtonConfiguration.add(player2);

        player1.addActionListener(this);
        player1.setFocusPainted(false);

        startReset.addActionListener(this);
        startReset.setFocusPainted(false);

        player2.addActionListener(this);
        player2.setFocusPainted(false);

        player1.setFont(new Font("Arial", Font.BOLD, 16));
        player2.setFont(new Font("Arial", Font.BOLD, 16));

        startReset.setText("Start");
        startReset.setFont(new Font("Arial", Font.BOLD, 24));
        label.setText("Game is not started");

        add(topButtonConfiguration, BorderLayout.PAGE_START);

        matchStart();
    }


    public void matchCheck() {
        if (isXEqual(0, 1, 2)) {
            xWins();
        } else if (isXEqual(3, 4, 5)) {
            xWins();
        } else if (isXEqual(6, 7, 8)) {
            xWins();
        } else if (isXEqual(0, 3, 6)) {
            xWins();
        } else if (isXEqual(1, 4, 7)) {
            xWins();
        } else if (isXEqual(2, 5, 8)) {
            xWins();
        } else if (isXEqual(0, 4, 8)) {
            xWins();
        } else if (isXEqual(2, 4, 6)) {
            xWins();
        } else if (isOEqual(0, 1, 2)) {
            oWins();
        } else if (isOEqual(3, 4, 5)) {
            oWins();
        } else if (isOEqual(6, 7, 8)) {
            oWins();
        } else if (isOEqual(0, 3, 6)) {
            oWins();
        } else if (isOEqual(1, 4, 7)) {
            oWins();
        } else if (isOEqual(2, 5, 8)) {
            oWins();
        } else if (isOEqual(0, 4, 8)) {
            oWins();
        } else if (isOEqual(2, 4, 6)) {
            oWins();
        } else if (countClick == 10) {
            draw();
        }

    }

    public boolean isXEqual(int firstNum, int secondNum, int thirdNum) {
        return buttons[firstNum].getText().equals("X") && buttons[secondNum].getText().equals("X") && buttons[thirdNum].getText().equals("X");
    }

    public boolean isOEqual(int firstNum, int secondNum, int thirdNum) {
        return buttons[firstNum].getText().equals("O") && buttons[secondNum].getText().equals("O") && buttons[thirdNum].getText().equals("O");
    }

    public void draw() {
        for (Button button : buttons) {
            button.setEnabled(false);
        }
        label.setText("Draw");
    }

    public void xWins() {
        for (Button button : buttons) {
            button.setEnabled(false);
        }
        label.setText("X wins");
    }

    public void oWins() {
        for (Button button : buttons) {
            button.setEnabled(false);
        }
        label.setText("O wins");
    }

    private void matchStart() {
        startReset.setText("Start");
        player1.setEnabled(true);
        player2.setEnabled(true);
        player1.setText("Human");
        player2.setText("Human");
        for (Button button : buttons) {
            button.setText(" ");
            button.setEnabled(false);
            this.label.setText("Game is not started");
        }

    }


    private void startClicked() {
        for (Button button : buttons) {
            button.setText(" ");
            button.setEnabled(true);
        }
        label.setText("Game in progress");
        player1.setEnabled(false);
        player2.setEnabled(false);
        countClick = 1;

        computerPlaying();
    }

    private void setXOrO(String xOrO) {
        boolean isTrue = true;
        while (isTrue) {
            int randomButton = random.nextInt(9);
            if (buttons[randomButton].getText().equals(" ")) {
                buttons[randomButton].setText(xOrO);
                isTrue = false;
            }
        }
        countClick++;
        matchCheck();
    }

    private void runTask() {
        if (countClick % 2 != 0) {
            setXOrO("X");
        } else {
            setXOrO("O");
        }
    }


    private void computerPlaying() {
        if (player1.getText().equals("Robot") && player2.getText().equals("Human")) {
            if (countClick % 2 != 0) {
                setXOrO("X");
            }
        } else if (player1.getText().equals("Human") && player2.getText().equals("Robot")) {
            if (countClick % 2 == 0) {
                setXOrO("O");
            }
        } else if (player1.getText().equals("Robot") && player2.getText().equals("Robot")) {
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    runTask();
                    if (!label.getText().equals("Game in progress")) {
                        timer.cancel();
                    }
                }
            };
            timer.scheduleAtFixedRate(task, 1000, 1000);
        }
    }

    private void changePlayerToRobot(JButton player) {
        if (player.getText().equals("Human")) {
            player.setText("Robot");
        } else {
            player.setText("Human");
        }
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (((JButton) actionEvent.getSource()).getText().equals("Start")) {
            startReset.setText("Reset");
            startClicked();
        } else if ((((JButton) actionEvent.getSource()).getText().equals("Reset"))) {
            matchStart();
        } else if (((JButton) actionEvent.getSource()).getName().equals("ButtonPlayer1")) {
            changePlayerToRobot(player1);
        } else if (((JButton) actionEvent.getSource()).getName().equals("ButtonPlayer2")) {
            changePlayerToRobot(player2);
        } else if (countClick % 2 != 0 && ((JButton) actionEvent.getSource()).getText().equals(" ")) {
            ((JButton) actionEvent.getSource()).setText("X");
            countClick++;
            matchCheck();
            computerPlaying();
        } else if (((JButton) actionEvent.getSource()).getText().equals(" ")) {
            ((JButton) actionEvent.getSource()).setText("O");
            countClick++;
            matchCheck();
            computerPlaying();
        }
    }
}