package bestgymever.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.SwingConstants.*;

public class TextFieldPanel extends JPanel {

    private final JLabel info;
    private final JTextField textField;
    private final JButton submit;

    public TextFieldPanel(ActionListener al) {
        this.info = new JLabel("");
        this.textField = new JTextField();
        this.submit = new JButton("Submit");
        this.submit.addActionListener(al);

    }

    public void setupPanel() {
        setLayout(new GridLayout(5, 1, 0, 8));
        info.setVerticalAlignment(BOTTOM);
        info.setHorizontalAlignment(CENTER);
        textField.setHorizontalAlignment(CENTER);

        textField.setText("");
        info.setFont(new Font("SansSarif", 0, 16));
        textField.setFont(new Font("SansSarif", 2, 18));
        submit.setFont(new Font("SansSarif", 1, 16));

        add(info);
        add(textField);
        add(submit);
    }

    public void setInfo(String text) {
        info.setText(text);
    }

    public JButton getSubmit() {
        return submit;
    }

    public JTextField getTextField() {
        return textField;
    }
}
