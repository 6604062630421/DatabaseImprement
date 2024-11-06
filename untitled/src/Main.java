import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
public class Main extends JPanel {
    data Data = new data();
    Db db = new Db();
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();
    // Declare form components
    JLabel title = new JLabel("ACCOUNT MONEY");
    JLabel idLabel = new JLabel("ID :");
    JLabel moneyLabel = new JLabel("MONEY :");
    JLabel rateLabel = new JLabel("ANNUAL INTEREST RATE :");
    JLabel dayOpenLabel = new JLabel("DAY OPEN ACCOUNT :");
    JLabel firstNameLabel = new JLabel("FIRST NAME :");
    JLabel lastNameLabel = new JLabel("LAST NAME :");
    JLabel birthDayLabel = new JLabel("DATE OF BIRTH :");
    JLabel ageLabel = new JLabel("AGE :");
    JLabel yearLabel = new JLabel("YEAR");

    JTextField idField = new JTextField(10);
    JTextField moneyField = new JTextField(10);
    JTextField rateField = new JTextField(10);
    JTextField firstNameField = new JTextField(25);
    JTextField lastNameField = new JTextField(25);
    JTextField ageField = new JTextField(5);

    // Combo boxes for date selections
    JComboBox<String> dayOpenCombo = new JComboBox<>(generateDays());
    JComboBox<String> monthOpenCombo = new JComboBox<>(generateMonths());
    JComboBox<String> yearOpenCombo = new JComboBox<>(generateYears(1980,2024));

    JComboBox<String> dayBirthCombo = new JComboBox<>(generateDays());
    JComboBox<String> monthBirthCombo = new JComboBox<>(generateMonths());
    JComboBox<String> yearBirthCombo = new JComboBox<>(generateYears(1980,2005));

    JButton saveButton = new JButton("SAVE");
    JButton showButton = new JButton("SHOW");

    Main() {
        db.connect_to_db("BankAccount","postgres","1412");
        setLayout(new BorderLayout());
        //top
        p1.add(title);

        //center
        p2.add(idLabel);
        p2.add(idField);

        p2.add(moneyLabel);
        p2.add(moneyField);

        p2.add(rateLabel);
        p2.add(rateField);

        p2.add(dayOpenLabel);
        p2.add(dayOpenCombo);
        p2.add(monthOpenCombo);
        p2.add(yearOpenCombo);

        p2.add(firstNameLabel);
        p2.add(firstNameField);

        p2.add(lastNameLabel);
        p2.add(lastNameField);

        p2.add(birthDayLabel);
        p2.add(dayBirthCombo);
        p2.add(monthBirthCombo);
        p2.add(yearBirthCombo);


        p2.add(ageLabel);
        p2.add(ageField);
        p2.add(yearLabel);

        //bottom
        p3.setLayout(new FlowLayout(FlowLayout.RIGHT));
        p3.add(saveButton);
        p3.add(showButton);

        add(p1,BorderLayout.NORTH);
        add(p2,BorderLayout.CENTER);
        add(p3,BorderLayout.SOUTH);

        saveButton.addActionListener(new ButtonListener());
        showButton.addActionListener(new ButtonListener());
    }

    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("SAVE")) {
                Data.setDate(Date.valueOf(yearOpenCombo.getSelectedItem().toString()+"-"+lek(monthOpenCombo.getSelectedItem().toString())+"-"+dayOpenCombo.getSelectedItem().toString()));
                Data.setId(idField.getText());
                Data.setMoney(Float.parseFloat(moneyField.getText()));
                Data.setFirstName("'"+firstNameField.getText()+"'");
                Data.setLastName("'"+lastNameField.getText()+"'");
                Data.setBirth_day(Date.valueOf(yearBirthCombo.getActionCommand().toString()+"-"+monthOpenCombo.getSelectedItem().toString()+"-"+dayBirthCombo.getSelectedItem().toString()));
                Data.setAge(Integer.parseInt(ageField.getText()));
                Data.setRate(Float.parseFloat(rateField.getText()));
                db.insert(Data);
            }
            else if(e.getActionCommand().equals("SHOW")){
                System.out.println("Show button pressed!");
            }
        }
    }
    public String lek(String mount){
        switch (mount){
            case "Jan":
                return "1";
                case "Feb":
                    return "2";
                    case "Mar":
                        return "3";
                        case "Apr":
                            return "4";
                            case "May":
                                return "5";
                                case "Jun":
                                    return "6";
                                    case "Jul":
                                        return "7";
                                        case "Aug":
                                            return "8";
                                            case "Sep":
                                                return "9";
                                                case "Oct":
                                                    return "10";
                                                    case "Nov":
                                                        return "11";
                                                        case "Dec":
                                                            return "12";
                                                            default:
                                                                return "0";
        }

    }
    private static String[] generateDays() {
        String[] days = new String[32];
        days[0] = "day";
        for (int i = 1; i <= 31; i++) {
            days[i] = String.valueOf(i);
        }
        return days;
    }


    private static String[] generateMonths() {
        return new String[]{"month", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    }

    private static String[] generateYears(int startYear, int endYear) {
        String[] years = new String[(endYear - startYear) + 2];
        years[0] = "year";
        int index = 1;
        for (int year = startYear; year <= endYear; year++) {
            years[index++] = String.valueOf(year);
        }
        return years;
    }
    public static void main(String[] args) {
        Main main = new Main();
        JFrame frame = new JFrame();
        frame.add(main);
        frame.setTitle("Show Details of Account"); // Frame title
        frame.setSize(330, 330);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Make frame closable
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true); // Show frame on screen
    }
}