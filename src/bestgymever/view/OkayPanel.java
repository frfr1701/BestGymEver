package bestgymever.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.SwingConstants.*;

public class OkayPanel extends JPanel {

    private final JLabel info;
    private final JButton submit;

    public OkayPanel(ActionListener al) {
        this.info = new JLabel("");
        this.submit = new JButton("Okay");
        submit.addActionListener(al);

    }

    public void setupPanel() {
        setLayout(new GridLayout(5, 1, 0, 8));

        info.setHorizontalAlignment(CENTER);
        info.setVerticalAlignment(BOTTOM);

        info.setFont(new Font("SansSarif", 0, 16));
        submit.setFont(new Font("SansSarif", 1, 16));

        add(info);
        add(submit);
    }

    public void setInfo(String text) {
        info.setText(text);
    }

    public JButton getSubmit() {
        return submit;
    }
}
