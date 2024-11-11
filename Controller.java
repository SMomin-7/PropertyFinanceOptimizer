
/**
 * This is the main class with all the packages of Swing, AWT and GUI components and containers
 * **MODEL**
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @see java.awt.Component
 * @see java.awt.Container
 *      This class is the main Model of the Jframe created in the program.
 */
public class Controller extends JFrame {
  private JTextField month;
  private JTextField loan;
  private JTextField rate;
  private JTextField freq;

  String[] s = { "Calculate Monthly", "Calculate Bi-weekly", "Calculate Weekly" };
  private JComboBox ptype;
  private JPanel outpanel;
  private JPanel outpanel1;
  private JPanel outpanel2;
  private JPanel outpanel3;
  private JPanel outpanel4;
  private JPanel outpanel5;
  private JPanel outpanel6;
  private JLabel outlabel;

  /**
   * This is the **VIEW** of the application which maintains the entire flow of
   * the program
   * It creates the main JFrame in which the components are stored.
   */
  public Controller() {
    setTitle("Mortgage Calculator");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(500, 500);
    setLayout(new FlowLayout(FlowLayout.CENTER));
    month = new JTextField(20);
    loan = new JTextField(20);
    rate = new JTextField(20);
    freq = new JTextField(20);
    add(new JLabel("Amortization in months:  "));
    add(month);
    add(new JLabel("Principal Loaned Amount: "));
    add(loan);
    add(new JLabel("Annual Interest Rate:    "));
    add(rate);
    add(new JLabel("Compounding Frequency:    "));
    add(freq);
    ptype = new JComboBox(s);
    ptype.addActionListener(new ptypelistener());
    ptype.setFocusable(false);
    add(ptype);
    setResizable(true);
    setVisible(true);
    outpanel = new JPanel();
    outpanel1 = new JPanel();
    outpanel2 = new JPanel();
    outpanel3 = new JPanel();
    outpanel4 = new JPanel();
    outpanel5 = new JPanel();
    outpanel6 = new JPanel();
    outlabel = new JLabel("Enter the values and select payment type and press Calculate ");
    outpanel.add(outlabel);
    outpanel.setBackground(new Color(173, 216, 230));
    outpanel1.setBackground(new Color(173, 216, 230));
    outpanel1.add(new JLabel("Total interest and principal: "));
    outpanel2.setBackground(new Color(173, 216, 230));
    outpanel2.add(new JLabel("Interest principal ratio"));
    outpanel3.setBackground(new Color(173, 216, 230));
    outpanel3.add(new JLabel("Average interest a year: "));
    outpanel4.setBackground(new Color(173, 216, 230));
    outpanel4.add(new JLabel("Average interest a month: "));
    outpanel5.setBackground(new Color(173, 216, 230));
    outpanel5.add(new JLabel("Total term"));
    outpanel6.setBackground(new Color(173, 216, 230));
    outpanel6.add(new JLabel("Total interest: "));
    add(outpanel, BorderLayout.SOUTH);
    add(outpanel1, BorderLayout.SOUTH);
    add(outpanel2, BorderLayout.SOUTH);
    add(outpanel3, BorderLayout.SOUTH);
    add(outpanel4, BorderLayout.SOUTH);
    add(outpanel5, BorderLayout.SOUTH);
    add(outpanel6, BorderLayout.SOUTH);

  }

  /**
   * This class is a part of controller which implements action of the buttons in
   * the combo box for different types of payments
   */
  private class ptypelistener implements ActionListener {
    /**
     * This processes the event when button is selected for different types of
     * payments.
     * 
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == ptype) {
        if (ptype.getSelectedItem() == "Calculate Monthly") {
          computeMonthly();
        }
        if (ptype.getSelectedItem() == "Calculate Bi-weekly") {
          computeBiweekly();
        }
        if (ptype.getSelectedItem() == "Calculate Weekly") {
          computeWeekly();
        }
      }
    }
  }

  /**
   * **CONTROLLER**
   * These method below calculates the required information and displays them on
   * JFrame after making the
   * required changes to the interface.
   *
   * Calculates monthly payments.
   */
  private void computeMonthly() // Method to compute monthly payment
  {
    double period = Double.parseDouble(month.getText()); // gets value of amortization
    double amt = Double.parseDouble(loan.getText()); // gets principal loaned amount
    double rates = Double.parseDouble(rate.getText()); // gets rate of interest
    double frequency = Double.parseDouble(freq.getText()); // gets value of compounding frequency
    double years = Math.floor(period / 12); // calculates year for loan
    double months = period % 12; // calculates month for loans
    double intfactor = Math.pow(((rates / 100) / (frequency)) + 1, frequency / 12) - 1; // Calculates interest factor
                                                                                        // for the laon
    double blended = (amt * intfactor) / (1 - (Math.pow(intfactor + 1, -period))); // calculates blended monthly
                                                                                   // payments
    double totalintandprinc = blended * period; // Calculates total interest and principal
    double totalinterest = (totalintandprinc - amt); // Calculates total interest
    double intprincratio = totalinterest / amt; // Calculates interest and principal ratio
    double avgintyear = totalinterest / years; // Calculates average interest per year
    double avgintmonth = totalinterest / period; // Calculate average interest a month
    outlabel.setText("Blended payments: $ " + blended);
    outpanel5.add(new JLabel(": " + years + " years and " + months + " months."));
    outpanel6.add(new JLabel("$ " + totalinterest));
    outpanel1.add(new JLabel("$ " + totalintandprinc));
    outpanel2.add(new JLabel(": " + intprincratio));
    outpanel3.add(new JLabel("$ " + avgintyear));
    outpanel4.add(new JLabel("$ " + avgintmonth));
  }

  /**
   * calculates Bi-weekly payments with same components
   */
  private void computeBiweekly() {
    double period = Double.parseDouble(month.getText());
    double amt = Double.parseDouble(loan.getText());
    double rates = Double.parseDouble(rate.getText());
    double frequency = Double.parseDouble(freq.getText());
    double years = Math.floor(period / 12);
    double months = period % 12;
    double intfactor = Math.pow(((rates / 100) / (frequency)) + 1, frequency / 26) - 1;
    double blended = (amt * intfactor) / (1 - (Math.pow(intfactor + 1, -period * 2)));
    double totalintandprinc = blended * period * 2;
    double totalinterest = (totalintandprinc - amt);
    double intprincratio = totalinterest / amt;
    double avgintyear = (totalinterest / years);
    double avgintmonth = (totalinterest / period);
    outlabel.setText("Blended payments: $ " + blended);
    outpanel5.add(new JLabel(": " + years + " years and " + months + " months."));
    outpanel6.add(new JLabel("$ " + totalinterest));
    outpanel1.add(new JLabel("$ " + totalintandprinc));
    outpanel2.add(new JLabel(": " + intprincratio));
    outpanel3.add(new JLabel("$ " + avgintyear));
    outpanel4.add(new JLabel("$ " + avgintmonth));
  }

  /**
   * Calculates Weekly payments with same components
   */
  private void computeWeekly() {
    double period = Double.parseDouble(month.getText());
    double amt = Double.parseDouble(loan.getText());
    double rates = Double.parseDouble(rate.getText());
    double frequency = Double.parseDouble(freq.getText());
    double years = Math.floor(period / 12);
    double months = period % 12;
    double intfactor = Math.pow(((rates / 100) / (frequency)) + 1, frequency / 52) - 1;
    double blended = ((amt * intfactor) / (1 - (Math.pow(intfactor + 1, -period * 4)))) * 4;
    double totalintandprinc = blended * period * 4;
    double totalinterest = (totalintandprinc - amt);
    double intprincratio = totalinterest / amt;
    double avgintyear = (totalinterest / years);
    double avgintmonth = (totalinterest / period);
    outlabel.setText("Blended payments: $ " + blended / 4);
    outpanel5.add(new JLabel(": " + years + " years and " + months + " months."));
    outpanel6.add(new JLabel("$ " + totalinterest));
    outpanel1.add(new JLabel("$ " + totalintandprinc));
    outpanel2.add(new JLabel(": " + intprincratio));
    outpanel3.add(new JLabel("$ " + avgintyear));
    outpanel4.add(new JLabel("$ " + avgintmonth));
  }
}
