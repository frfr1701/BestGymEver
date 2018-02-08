package bestgymever.view;

import static java.awt.BorderLayout.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class GraphicalView extends JFrame implements Runnable, IView {

    JLabel title;
    JPanel topPadding;
    JPanel bottomtPadding;
    JPanel leftPadding;
    JPanel rightPadding;
    List<JPanel> paddings;

    List<String> viewList;
    String returnString;
    boolean done;

    ActionListener al;

    JPanel CurrentPanel;
    TextFieldPanel textFieldPanel;
    OkayPanel okayPanel;
    DropDownPanel dropDownPanel;

    public GraphicalView() {
        this.topPadding = new JPanel();
        this.bottomtPadding = new JPanel();
        this.leftPadding = new JPanel();
        this.rightPadding = new JPanel();
        this.paddings = Arrays.asList(topPadding, bottomtPadding, leftPadding, rightPadding);
        this.title = new JLabel();
        al = (ActionEvent a) -> {
            if (a.getSource() == textFieldPanel.getSubmit()) {
                returnString = (textFieldPanel.getTextField().getText());
            } else if (a.getSource() == dropDownPanel.getSubmit()) {
                returnString = (String.valueOf(dropDownPanel.getOptions().getSelectedIndex() + 1));
            } else if (a.getSource() == okayPanel.getSubmit()) {
                returnString = ("");
            }
            done = true;
        };
        this.CurrentPanel = new JPanel();
        this.textFieldPanel = new TextFieldPanel(al);
        this.dropDownPanel = new DropDownPanel(al);
        this.okayPanel = new OkayPanel(al);
    }

    public void setupFrame() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(900, 500));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        title.setText("Receptionist");
        title.setFont(new Font("SansSarif", 1, 30));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.BOTTOM);
        paddings.forEach((s) -> {
            s.setPreferredSize(new Dimension(50, 60));
        });
        topPadding.add(title);
        add(topPadding, NORTH);
        add(bottomtPadding, SOUTH);
        add(leftPadding, WEST);
        add(rightPadding, EAST);
        add(CurrentPanel);
        pack();

        setLocationRelativeTo(null);
    }

    @Override
    public String display(List<String> viewList) {
        done = false;
        this.viewList = viewList;
        run();
        while (!done) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                //shhhhhhh
            }
        }
        return returnString;
    }

    @Override
    public void run() {
        remove(CurrentPanel);
        switch (viewList.get(0)) {
            case "User not found | Write username:":
            case "Write username:":
                CurrentPanel = textFieldPanel;
                textFieldPanel.setupPanel();
                textFieldPanel.setInfo(viewList.get(0));
                add(textFieldPanel);
                break;
            case "Write password:":
                CurrentPanel = textFieldPanel;
                textFieldPanel.setupPanel();
                textFieldPanel.setInfo(viewList.get(0));
                add(textFieldPanel);
                break;
            case "Which member would you like to checkin?":
                CurrentPanel = dropDownPanel;
                dropDownPanel.setupPanel(viewList);
                dropDownPanel.setInfo(viewList.get(0));
                add(dropDownPanel);
                break;
            case "Which workout do you want to check in?":
                CurrentPanel = dropDownPanel;
                dropDownPanel.setupPanel(viewList);
                dropDownPanel.setInfo(viewList.get(0));
                add(dropDownPanel);
                break;
            default:
                CurrentPanel = okayPanel;
                okayPanel.setupPanel();
                okayPanel.setInfo(viewList.get(0));
                add(okayPanel);
        }
        revalidate();
        repaint();
    }
}
