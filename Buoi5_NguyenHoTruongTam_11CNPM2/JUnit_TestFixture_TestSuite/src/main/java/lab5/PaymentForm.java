package lab5;

import javax.swing.*;
import java.awt.*;

public class PaymentForm extends JFrame {
    private JRadioButton rbMale = new JRadioButton("Male");
    private JRadioButton rbFemale = new JRadioButton("Female");
    private JRadioButton rbChild = new JRadioButton("Child (0 - 17 years)");
    private JTextField txtAge = new JTextField(10);
    private JTextField txtPayment = new JTextField(10);
    private JButton btnCalc = new JButton("Calculate");

    public PaymentForm() {
        setTitle("Calculate the Payment for the Patient");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(520, 220);
        setLocationRelativeTo(null);

        ButtonGroup bg = new ButtonGroup();
        bg.add(rbMale); bg.add(rbFemale); bg.add(rbChild);
        rbMale.setSelected(true);

        txtPayment.setEditable(false);

        JPanel p = new JPanel();
        p.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);
        c.anchor = GridBagConstraints.WEST;

        c.gridx=0; c.gridy=0; p.add(rbMale, c);
        c.gridx=1; p.add(rbFemale, c);
        c.gridx=2; p.add(rbChild, c);

        c.gridx=0; c.gridy=1; p.add(new JLabel("Age (Years)"), c);
        c.gridx=1; p.add(txtAge, c);
        c.gridx=2; p.add(btnCalc, c);

        c.gridx=0; c.gridy=2; p.add(new JLabel("Payment is"), c);
        c.gridx=1; p.add(txtPayment, c);
        c.gridx=2; p.add(new JLabel("euro ?"), c);

        btnCalc.addActionListener(e -> onCalculate());

        add(p);
    }

    private void onCalculate() {
        try {
            int age = Integer.parseInt(txtAge.getText().trim());
            PaymentCalculator.Type type =
                    rbChild.isSelected() ? PaymentCalculator.Type.CHILD :
                    rbFemale.isSelected()? PaymentCalculator.Type.FEMALE :
                            PaymentCalculator.Type.MALE;

            int money = PaymentCalculator.calc(age, type);
            txtPayment.setText(String.valueOf(money));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            txtPayment.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PaymentForm().setVisible(true));
    }
}
