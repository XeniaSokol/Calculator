package calculator;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {
        Menu(){
            JLabel text = new JLabel("<html> �����������. ������ 1.0<br> " +
                    "��������� ������ ����������, 2022. ��� ����� ��������.<br><br>" +
                    "��������� ��������� ����������� ��������� �������: <br>"+
                    "<li> ��������;</li>" +
                    "<li> ���������;</li>" +
                    "<li> ���������;</li>" +
                    "<li> �������;</li>" +
                    "<li> ������ ����������� �����;</li>" +
                    "<li> ���������� ����� �� ������ �������;</li>" +
                    "<li> ��������� ����� � ��������;</li>" +
                    "<li> �������� ������ �����. </li><br>" +
                    "��������� ��������������� ���� ����� � ������� �����. " +
                    "���� ������ �������������� �������� ����� �� ������ ������������. "
                    + "��������� ������������ �� ������ �����.</html>");

            setTitle("�������");
            setSize(440,300);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            text.setFont(new Font("arial", Font.PLAIN, 12));
            text.setVerticalAlignment(JLabel.VERTICAL);
            getContentPane().add(text);
            setVisible(true);
        }
}
