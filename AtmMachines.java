import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class AtmMachines 
{
	private double balance = 10000.0;

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() ->
        {
            createAndShowGUI();
        });
    }
    public static void createAndShowGUI() {
        JFrame frame = new JFrame("ATM Machines");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("ATM Machine");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(6, 3));

        JLabel balanceLabel = new JLabel("Balance:");
        JTextField balanceField = new JTextField();
        balanceField.setEditable(false);

        JLabel amountLabel = new JLabel("Enter Amount:");
        JTextField amountField = new JTextField();

        JButton withdrawButton = new JButton("Withdrawal");
        JButton depositButton = new JButton("Deposit");

        panel.add(balanceLabel);
        panel.add(balanceField);
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(withdrawButton);
        panel.add(depositButton);

        frame.add(panel, BorderLayout.CENTER);

        AtmMachines atmMachine = new AtmMachines();

        updateBalance(balanceField, atmMachine.balance);

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    if (amount <= 0) {
                        JOptionPane.showMessageDialog(frame, "Invalid amount.");
                    } else if (amount > atmMachine.balance) {
                        JOptionPane.showMessageDialog(frame, "Insufficient funds.");
                    } else {
                        atmMachine.withdraw(amount);
                        updateBalance(balanceField, atmMachine.balance);
                        JOptionPane.showMessageDialog(frame, "Withdrawn: " + formatAmount(amount));
                    }
                } 
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input.");
                }
                amountField.setText("");
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    if (amount <= 0) {
                        JOptionPane.showMessageDialog(frame, "Invalid amount.");
                    } else {
                        atmMachine.deposit(amount);
                        updateBalance(balanceField, atmMachine.balance);
                        JOptionPane.showMessageDialog(frame, "Deposited: " + formatAmount(amount));
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input.");
                }
                amountField.setText("");
            }
        });

        frame.setVisible(true);
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public static void updateBalance(JTextField balanceField, double balance) {
        balanceField.setText(formatAmount(balance));
    }

    public static String formatAmount(double amount) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return "$" + df.format(amount);
    }
}
   