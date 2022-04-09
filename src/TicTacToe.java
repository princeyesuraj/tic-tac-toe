import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe implements ActionListener {
    JFrame frame = new JFrame();
    JButton[] buttons = new JButton[9];
    JButton resetButton;
    String turnSymbol;
    JPanel board;
    JPanel status;
    JLabel statusText;
    boolean gameActive = false;

    public TicTacToe() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLayout(new BorderLayout());
        status = new JPanel();
        frame.setTitle("Tic Tac Toe");
        initBoard();
        statusText = new JLabel();
        statusText.setFont(new Font(null, Font.BOLD, 72));
        statusText.setOpaque(true);
        statusText.setText("Play " + turnSymbol);
        status.setBounds(0, 0, 800, 100);
        status.add(statusText);
        frame.add(status, BorderLayout.NORTH);
        frame.add(board);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initBoard() {
        board = new JPanel(new GridLayout(3, 3));
        turnSymbol = "X";
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setForeground(Color.BLACK);
            buttons[i].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 72));
            buttons[i].addActionListener(this);
            buttons[i].setFocusable(false);
            board.add(buttons[i]);
        }
        resetButton = new JButton();
        resetButton.setText("reset");
        resetButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        resetButton.setVisible(false);
        resetButton.addActionListener(actionEvent -> {
            resetBoard();
        });
        status.add(resetButton);
        gameActive = true;
    }

    private void resetBoard() {
        for (JButton button : buttons) {
            button.setText("");
            button.setBackground(null);
        }
        resetButton.setVisible(false);
        gameActive = true;
    }

    public boolean checkWin() {
        if (buttons[0].getText().equals(turnSymbol) && buttons[1].getText().equals(turnSymbol) && buttons[2].getText().equals(turnSymbol)) {
            triggerWin(0, 1, 2);
            return true;
        } else if (buttons[3].getText().equals(turnSymbol) && buttons[4].getText().equals(turnSymbol) && buttons[5].getText().equals(turnSymbol)) {
            triggerWin(3, 4, 5);
            return true;
        } else if (buttons[6].getText().equals(turnSymbol) && buttons[7].getText().equals(turnSymbol) && buttons[8].getText().equals(turnSymbol)) {
            triggerWin(6, 7, 8);
            return true;
        } else if (buttons[0].getText().equals(turnSymbol) && buttons[3].getText().equals(turnSymbol) && buttons[6].getText().equals(turnSymbol)) {
            triggerWin(0, 3, 6);
            return true;
        } else if (buttons[1].getText().equals(turnSymbol) && buttons[4].getText().equals(turnSymbol) && buttons[7].getText().equals(turnSymbol)) {
            triggerWin(1, 4, 7);
            return true;
        } else if (buttons[2].getText().equals(turnSymbol) && buttons[5].getText().equals(turnSymbol) && buttons[8].getText().equals(turnSymbol)) {
            triggerWin(2, 5, 8);
            return true;
        } else if (buttons[0].getText().equals(turnSymbol) && buttons[4].getText().equals(turnSymbol) && buttons[8].getText().equals(turnSymbol)) {
            triggerWin(0, 4, 8);
            return true;
        } else if (buttons[2].getText().equals(turnSymbol) && buttons[4].getText().equals(turnSymbol) && buttons[6].getText().equals(turnSymbol)) {
            triggerWin(2, 4, 6);
            return true;
        } else {
            boolean atLeastOne = false;
            for (JButton button : buttons) {
                if (button.getText().equals("")) {
                    atLeastOne = true;
                }
            }
            if (!atLeastOne) {
                triggerTie();
                return true;
            }
        }
        return false;
    }

    private void triggerTie() {
        for (JButton button : buttons) {
            button.setBackground(Color.ORANGE);
        }
        statusText.setText("Game Tied");
        resetButton.setVisible(true);
        gameActive = false;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (!gameActive) {
            return;
        }
        for (JButton button : buttons) {
            if (actionEvent.getSource() == button) {
                if (button.getText().equals("")) {
                    button.setText(turnSymbol);
                    if (!checkWin()) {
                        if (turnSymbol.equals("X")) {
                            turnSymbol = "O";
                        } else {
                            turnSymbol = "X";
                        }
                        statusText.setText("Play " + turnSymbol);
                    }
                }
            }
        }
    }

    private void triggerWin(int x, int y, int z) {
        buttons[x].setBackground(Color.GREEN);
        buttons[y].setBackground(Color.GREEN);
        buttons[z].setBackground(Color.GREEN);
        statusText.setText(turnSymbol + " Wins");
        resetButton.setVisible(true);
        gameActive = false;
    }
}
