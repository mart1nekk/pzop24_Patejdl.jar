package practical;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class WindowTask extends JFrame {
    JPanel inputPanel, pohlaviPanel;
    MyLabel lvek, lvyska, lvaha, lpohlavi, lposledniDarovani;
    MyInput ivek, ivyska, ivaha, iposledniDarovani;
    MyRadioButton rbMuz, rbZena;

    MyButton start;

    static void init() {
        WindowTask task = new WindowTask();
        task.setVisible(true);
    }

    private void drawComponents() {
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 3, 25, 20));

        lvek = new MyLabel("Věk:");
        lvyska = new MyLabel("Výška:");
        lvaha = new MyLabel("Váha:");
        lpohlavi = new MyLabel("Pohlaví:");
        lposledniDarovani = new MyLabel("Poslední darování");

        ivek = new MyInput("");
        ivyska = new MyInput("");
        ivaha = new MyInput("");
        iposledniDarovani = new MyInput("");

        pohlaviPanel = new JPanel();
        pohlaviPanel.setLayout(new GridLayout(1, 2));

        rbMuz = new MyRadioButton("Muž");
        rbZena = new MyRadioButton("Žena");

        ButtonGroup rbGroup = new ButtonGroup();
        rbGroup.add(rbMuz);
        rbGroup.add(rbZena);

        rbMuz.setSelected(true);

        pohlaviPanel.add(rbMuz);
        pohlaviPanel.add(rbZena);

        inputPanel.add(lvek);
        inputPanel.add(ivek);
        inputPanel.add(lvyska);
        inputPanel.add(ivyska);
        inputPanel.add(lvaha);
        inputPanel.add(ivaha);
        inputPanel.add(lpohlavi);
        inputPanel.add(pohlaviPanel);
        inputPanel.add(lposledniDarovani);
        inputPanel.add(iposledniDarovani);

        start = new MyButton("Mohu dnes darovat?");
        start.addActionListener((e) -> mohuDarovat());

        this.add(inputPanel, BorderLayout.CENTER);
        this.add(start, BorderLayout.SOUTH);
    }

    void mohuDarovat() {
        String text;
        text = "Krev darovat nemůžete";

        int vek = Integer.parseInt(ivek.getText());
        int vaha = Integer.parseInt(ivaha.getText());


        if ((vek >= 18 && vek < 65) && (vaha > 50 && vaha < 190)){
            LocalDate dnesni = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate lastDarovani = LocalDate.parse(iposledniDarovani.getText(), formatter);

            long dur = ChronoUnit.DAYS.between(lastDarovani, dnesni);

            if (rbMuz.isSelected() && dur >= 90){
                text = "Můžete darovat krev";
            }

            if (rbZena.isSelected() && dur >= 120){
                text = "Můžete darovat krev";
            }
        }


        JOptionPane.showMessageDialog(this, text, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    WindowTask() {
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 350);
        this.setTitle("Darovani krve");
        this.setLayout(new BorderLayout());

        drawComponents();
    }
}

class MyLabel extends JLabel {
    public MyLabel(String text) {
        super(text);
        this.setFont(new Font("Arial", Font.BOLD, 17));
    }
}

class MyInput extends JTextField {
    public MyInput(String text) {
        super(text);
        this.setFont(new Font("Arial", Font.BOLD, 14));
        this.setHorizontalAlignment(CENTER);
    }
}

class MyButton extends JButton {
    public MyButton(String text) {
        super(text);
        this.setFont(new Font("Arial", Font.BOLD, 17));
        this.setPreferredSize(new Dimension(200, 30));
    }
}

class MyRadioButton extends JRadioButton {
    public MyRadioButton(String text) {
        super(text);
        this.setFont(new Font("Arial", Font.BOLD, 22));
    }
}
