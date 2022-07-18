package calculator;

import javax.swing.*;
import java.awt.*;

public class Calculator extends JFrame {
    private JTextArea text;//���� ��� ����� ���� � ��������
    private double number1 = 0,number2 = 0;//���������� ��� �������� ���������
    private int operation = 0;//����� ��������
    private final JPanel panel = new JPanel();
    private final JButton btn[] = new JButton[24];
    private int numberButton = 7; //����� ��������� �������� ������


    Calculator(){
        panel.setLayout(null);
        panel.setBackground(Color.decode("#e0e1e1"));  //������������� ���� ����
        createButtonsPanel(); //������� ������ � ��������
        createInOutPanel(); //������� ���� �����/������ ����������
        getContentPane().add(panel);
        setSize(440,495);
        setTitle("�����������. ������ 1.0");
        createMenu(new JMenuBar());
        setDefaultCloseOperation(EXIT_ON_CLOSE);  //����� �� ������ �
        setVisible(true);
    }

    /**
     * �������� ����
     * @param menuBar ��������� ����
     */
    private void createMenu(JMenuBar menuBar){
        JMenu menu = new JMenu("����");
        JMenuItem notice = new JMenuItem("�������");
        Font font = new Font("arial", Font.PLAIN,14);
        menu.setFont(font);
        notice.setFont(font);
        menu.add(notice);
        menuBar.add(menu);
        setJMenuBar(menuBar);
        notice.addActionListener(e -> new Menu());
        menu.setVisible(true);
    }

    private void createButtonsPanel() {
        int y = 0;  //��������� ������������ ������
        int x = 0;
        for(var i = 0;i < btn.length;i++){
            btn[i] = new JButton();
            btn[i].setSize(100,60);//������ ������ ������
            btn[i].setLocation(10 + 101 * x++, 62 + 61 * y);
            // ���� � ���� ��� 4 ������, ����� ������� ����� ������ ������ � �������� x
            if (x == 4) {
                y++; x = 0;
                if (y > 2)  //����������� ��� ����������� ������������ ���� ������
                    numberButton-=6;
            }
            createButton(i);
            //���������� ������
            btn[i].addActionListener(e -> {
                JButton button = (JButton) e.getSource();//������ �� ������� ��� ����
                String str = button.getText().trim();
                handleListener(str);
            });
            panel.add(btn[i]);
        }
    }

    /**
     * ������������ ������ ������� ������
     * @param str - �������� ������
     */
    private void handleListener(String str) {
        switch (str) {
            case "+", "-", "*", "/" -> {
                if (!text.getText().isEmpty()) {
                    number1 = Double.parseDouble(text.getText());
                    setOperationNumber(str);
                }
            }
            case "C" -> {
                text.setText("");
                number1 = 0;
                number2 = 0;
            }
            case "1/x", "x^2", "sqrt(x)", "%", "|x|", "+/-", "<=", "=", "."  -> {
                if (!text.getText().isEmpty()) {
                    number2 = Double.parseDouble(text.getText());
                    operationsWithOneNumber(str);
                }
            }
            default -> defaultOperation(str);
        }

    }

    /**
     * ������������� ����� ��������
     * @param str - �������� ������
     */
    private void setOperationNumber(String str) {
        text.setText(str);
        switch (str) {
            case "+" -> operation = 1;
            case "-" -> operation = 2;
            case "*" -> operation = 3;
            case "/" -> operation = 4;
        }
    }

    /**
     * �������� ��������� ���������� �������� � ����� �������
     * @param operation - ����� ��������
     */
    void getResultOperation(int operation){
        switch (operation) {
            case 1 -> text.setText("" + (number1 + number2));
            case 2 -> text.setText("" + (number1 - number2));
            case 3 -> text.setText("" + (number1 * number2));
            case 4 -> {
                if(number2 == 0) {
                    try {
                        throw new Exception("�� 0 ������ ������");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                text.setText("" + (number1/number2));
            }
        }
    }

    /**
     * ��������� �������� ��� ����� ������
     * @param str - ��� ������
     */
    void operationsWithOneNumber(String str){
        switch (str) {
            case "=" ->  getResultOperation(operation);
            case "%" ->   text.setText("" + number2/100);
            case "|x|" -> text.setText("" + Math.abs(number2));
            case "x^2" -> text.setText("" + Math.pow(number2, 2));
            case "sqrt(x)" -> text.setText("" + (Math.sqrt(number2)));
            case "<=" -> text.setText(text.getText().substring(0, text.getText().length()-1));
            case "." -> {
                if (!text.getText().contains("."))
                    text.setText(text.getText() + str);
            }
            case "+/-" -> {
                if (number2 > 0)
                    text.setText("-" + number2);
                else
                    text.setText("" + Math.abs(number2));
            }
            case "1/x" -> {
                if(number2 == 0) {
                    try {
                        throw new Exception("�� ���� ������ ������");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                text.setText("" + (1/number2));
            }
        }
    }

    /**
     * ����� ����������� �� ���������, ���� �� �������� �����
     * @param str - ��� ������
     */
    private void defaultOperation(String str) {
        switch (text.getText()) {
            case "+", "/", "*", "-" -> text.setText(str);
            default -> text.setText(text.getText() + str);
        }
    }


    /**
     * ������������� �����/�����/����� �������
     */
    private void createButton(int i) {
        setStyleButton(btn[i], Color.decode("#f2f2f2"), Font.PLAIN);
        switch (i) {
            case 0 -> btn[i].setText("%");
            case 1 -> btn[i].setText("|x|");
            case 2 -> btn[i].setText("C");
            case 3 -> btn[i].setText("<=");      //�������� ��������
            case 4 -> btn[i].setText("1/x");
            case 5 -> btn[i].setText("x^2");     //x^2
            case 6 -> btn[i].setText("sqrt(x)");   //���������� ������
            case 7 -> btn[i].setText("/");
            case 11 -> btn[i].setText("*");     //���������  //�
            case 15 -> btn[i].setText("-");  //+
            case 19 -> btn[i].setText("+");  //+
            case 20 -> btn[i].setText("+/-");
            case 21 -> btn[i].setText("0");
            case 22 -> btn[i].setText(".");
            case 23 -> {
                btn[i].setText("=");
                setStyleButton(btn[i], Color.decode("#def5f7"), Font.PLAIN);
            }
            default -> {
                btn[i].setText("" + numberButton);
                setStyleButton(btn[i], Color.white, Font.BOLD);
                numberButton++;
            }
        }
    }

    /**
     * ������������� ����� ������
     * @param button - ������, �� ������� �����������  ��������������� �����
     * @param background - ���� ���� ������
     * @param font - ����� ����� ������
     */
    private void setStyleButton(JButton button, Color background, int font) {
        button.setFont(new Font("serif", font,25));
        button.setBackground(background);
        button.setForeground(Color.black);
        button.setBorder(BorderFactory.createLineBorder(Color.lightGray));
    }

    /**
     * ������� ������ �����/������� ������
     */
    private void createInOutPanel() {
        var res = new JLabel("���������:");
        setStyleLabel(res, new Font("serif",Font.PLAIN,30));
        panel.add(res);
        text = new JTextArea();

        text.setFont(new Font("serif",Font.PLAIN,30));
        text.setForeground(Color.black);
        text.setBounds(10,10,400,50);
        panel.add(text);
    }


    /**
     * ������������� ����� label
     * @param label - ������, �� ������� �����������  ��������������� �����
     * @param font - ����� ����� ������
     */
    private void setStyleLabel(JLabel label, Font font) {
        label.setFont(font);
        label.setForeground(Color.gray);
        label.setBounds(15,0,300,50);
    }
}