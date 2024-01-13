import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

class GUICalculator implements ActionListener
{
    JFrame frame;
    ImageIcon logo = new ImageIcon("unnamed.png");
    JPanel panel;
    JTextField text;
    Font salsa = customFont();
    JButton[] nums = new JButton[10];
    JButton[] fn = new JButton[10];
    JButton divBtn, mulBtn, addBtn, subBtn, decBtn, eqBtn, negBtn, delBtn, clearBtn, emptyBtn;
    double a = 0, b = 0, result = 0;
    char operator;
    GUICalculator()
    {
        //JFrame
        frame = new JFrame();
        frame.setTitle("Calculator");
        frame.setSize(400,560);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setIconImage(logo.getImage());
        frame.getContentPane().setBackground(Color.BLACK);

        text = new JTextField();
		text.setBounds(50, 25, 300, 50);
		text.setFont(salsa);
		text.setEditable(false);

        //JButtons
        addBtn = new JButton("+");
        subBtn = new JButton("-");
        mulBtn = new JButton("*");
        divBtn = new JButton("/");
        decBtn = new JButton(".");
        eqBtn = new JButton("=");
        negBtn = new JButton("NEG");
        delBtn = new JButton("DEL");
        clearBtn = new JButton("CLR");
        emptyBtn = new JButton(" ");

        fn[0] = addBtn;
        fn[1] = subBtn;
        fn[2] = mulBtn;
        fn[3] = divBtn;
        fn[4] = decBtn;
        fn[5] = eqBtn;
        fn[6] = negBtn;
        fn[7] = clearBtn;
        fn[8] = delBtn;
        fn[9] = emptyBtn;

        for(int i =0;i<10;i++) 
        {
			fn[i].addActionListener(this);
			fn[i].setFont(salsa);
			fn[i].setFocusable(false);
            fn[i].setBackground(Color.BLACK);
            fn[i].setForeground(Color.YELLOW);
		}
		

        for(int i = 0; i<10; i++)
        {
            nums[i] = new JButton(String.valueOf(i));
            nums[i].addActionListener(this);
			nums[i].setFont(salsa);
			nums[i].setFocusable(true);
            nums[i].setForeground(Color.WHITE);
        }

        //Panel setup
        panel = new JPanel();
		panel.setBounds(50, 100, 300, 400);
		panel.setLayout(new GridLayout(5,4,10,10));
        panel.setBackground(Color.BLACK);
        panel.add(fn[7]);
        panel.add(negBtn);
        panel.add(delBtn);
        panel.add(divBtn);
        panel.add(nums[7]).setBackground(Color.BLACK);
        panel.add(nums[8]).setBackground(Color.BLACK);
        panel.add(nums[9]).setBackground(Color.BLACK);
        panel.add(mulBtn);
        panel.add(nums[6]).setBackground(Color.BLACK);
        panel.add(nums[5]).setBackground(Color.BLACK);
        panel.add(nums[4]).setBackground(Color.BLACK);
        panel.add(subBtn);
        panel.add(nums[3]).setBackground(Color.BLACK);
        panel.add(nums[2]).setBackground(Color.BLACK);
        panel.add(nums[1]).setBackground(Color.BLACK);
        panel.add(addBtn);
        panel.add(nums[0]).setBackground(Color.BLACK);
        panel.add(emptyBtn);
        panel.add(decBtn);
        panel.add(eqBtn);

        frame.add(text);
        frame.add(panel);
        frame.setResizable(false);
        frame.setVisible(true); //last line of constructor
    }
    Font customFont() 
    {
        try 
        {
            // Create a font object from the custom font file
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("Salsa-Regular.ttf")).deriveFont(17f);

            // Register the custom font with the GraphicsEnvironment
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);

            return font;
        } 
        catch (IOException | FontFormatException e) 
        {
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) 
    {
        new GUICalculator();
    }
    public void actionPerformed(ActionEvent e)
    {
        for(int i = 0; i<10;i++)
        {
            if (e.getSource() == nums[i])
            {
                text.setText(text.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == decBtn) 
        {
            text.setText(text.getText().concat("."));   
        }
        if (e.getSource() == delBtn)
        {
            String string = text.getText();
			text.setText("");
			for(int i=0;i<string.length()-1;i++) 
            {
				text.setText(text.getText() + string.charAt(i));
			}
        }
        if(e.getSource() == negBtn)
        {
            Double temp = Double.parseDouble(text.getText());
            temp *= -1;
            text.setText(String.valueOf(temp));
        }
        if(e.getSource() == clearBtn)
        {
            text.setText("");
        }
        if(e.getSource() == emptyBtn)
        {
            text.setText("");
        }
        if(e.getSource() == addBtn)
        {
            a = Double.parseDouble(text.getText());
            operator = '+';
            text.setText("");
        }
        if(e.getSource() == subBtn)
        {
            a = Double.parseDouble(text.getText());
            operator = '-';
            text.setText("");
        }
        if(e.getSource() == mulBtn)
        {
            a = Double.parseDouble(text.getText());
            operator = '*';
            text.setText("");
        }
        if(e.getSource() == divBtn)
        {
            a = Double.parseDouble(text.getText());
            operator = '/';
            text.setText("");
        }
        if(e.getSource() == eqBtn)
        {
            b = Double.parseDouble(text.getText());
            switch (operator) 
            {
                case '/': result = a / b; break;
                case '*': result = a * b; break;
                case '+': result = a + b; break;
                case '-': result = a - b; break;
            }
            text.setText(String.valueOf(result));
            a = result;
        }
    }
}