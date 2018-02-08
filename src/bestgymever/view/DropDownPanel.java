package bestgymever.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;
import static javax.swing.SwingConstants.*;

public class DropDownPanel extends JPanel {

    private final JLabel info;
    private JComboBox<String> options;
    private final JButton submit;

    public DropDownPanel(ActionListener al) {
        this.info = new JLabel("");
        this.options = new JComboBox<>();
        this.submit = new JButton("Submit");
        submit.addActionListener(al);

    }

    public void setupPanel(List<String> viewList) {
        removeAll();
        String[] temp = new String[viewList.size() - 1];
        for (int i = 1; i < viewList.size(); i++) {
            temp[i - 1] = viewList.get(i).substring(4);
        }
        setLayout(new GridLayout(5, 1, 0, 8));

        info.setHorizontalAlignment(CENTER);
        info.setVerticalAlignment(BOTTOM);

        info.setFont(new Font("SansSarif", 0, 16));
        options.setFont(new Font("SansSarif", 2, 18));
        submit.setFont(new Font("SansSarif", 1, 16));

        add(info);
        options = new JComboBox<>(temp);
        add(options);
        add(submit);
    }

    public void setInfo(String text) {
        info.setText(text);
    }

    public JComboBox<String> getOptions() {
        return options;
    }

    public JButton getSubmit() {
        return submit;
    }
}
