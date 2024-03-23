import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
class dataDemo extends JFrame implements ActionListener{
    DefaultTableModel de;
    JPanel p;
    JTable t;
    JTextField t1,t2,t3;
    JButton bt1,bt2,bt3;
    dataDemo() {
        setTitle("Table_Information");
        setBounds(10, 10, 1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Object data[][] = {{1, "Krishna", 20000},
                {2, "Aaryan", 60000}, {3, "Bhola", 50000}, {4, "Kiran", 90000}};
        String column[] = {"S.NO.", "Name", "Salary"};
        de = new DefaultTableModel(data, column);
        t = new JTable(de);
        t.setBackground(Color.GREEN);

        //container
        Container c=this.getContentPane();
        c.setLayout(new GridLayout(3,3));
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.red));

        //call japanel
        p=new JPanel();
        p.setBorder(new LineBorder(Color.BLACK,5));
        p.setBackground(Color.PINK);
        add(new JScrollPane(t));
        add(new JPanel());
        add(p);

        //object for textfield
        t1=new JTextField();
        t2=new JTextField();
        t3=new JTextField();

        //object fot button
        bt1=new JButton("ADD");
        bt2=new JButton("UPDATE");
        bt3=new JButton("DELETE");

        //set panel
        p.setLayout(new GridLayout(3,3));
        p.add(new JLabel("S.NO."));
        p.add(t1);
        p.add(bt1);

        p.add(new JLabel("Name"));
        p.add(t2);
        p.add(bt2);

        p.add(new JLabel("Salary"));
        p.add(t3);
        p.add(bt3);

        validate();
        //button for insert
        bt1.addActionListener(this);

        //button for update
        bt2.addActionListener(this);

        //button for delete
        bt3.addActionListener(this);

        //update the data then set mouse listener
        t.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
              int row=  t.getSelectedRow();
              int  sNo=(int)de.getValueAt(row,0);
              String name=(String)de.getValueAt(row,1);
                int  salary=(int)de.getValueAt(row,2);

                //set text on label
                t1.setText(String.valueOf(row));
                t2.setText(name);
                t3.setText(String.valueOf(salary));
            }
        });
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bt1) {
            if (t1.getText().toString().isEmpty() || t2.getText().toString().isEmpty() || t3.getText().toString().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please! fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                int sno = Integer.parseInt(t1.getText().toString());
                String name = t2.getText().toString();
                int sal = Integer.parseInt(t3.getText().toString());

                Object[] newRow = {sno, name, sal};
                de.addRow(newRow);

                t1.setText(null);
                t2.setText(null);
                t3.setText(null);
            }
        }

        if(e.getSource()==bt2) {
            if (t1.getText().toString().isEmpty() || t2.getText().toString().isEmpty() || t3.getText().toString().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please! Select Data To Be Updated", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                int sno = Integer.parseInt(t1.getText().toString());
                String name = t2.getText().toString();
                int sal = Integer.parseInt(t3.getText().toString());
               int row= t.getSelectedRow();
               de.setValueAt(sno,row,0);
                de.setValueAt(name,row,1);
                de.setValueAt(sal,row,2);

                t1.setText(null);
                t2.setText(null);
                t3.setText(null);
            }
        }
        if(e.getSource()==bt3){
            if(t.getSelectedRow()==-1){
                JOptionPane.showMessageDialog(null,"Please Select a row","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
            int status=JOptionPane.showConfirmDialog(null,"Do You Want To Delete This Row","Confirm",JOptionPane.YES_NO_OPTION);
            if(status==JOptionPane.YES_OPTION){
                de.removeRow(t.getSelectedRow());
            }
        }
        //multiple selection mode can be avoided
      t.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
}
public class table_Info {
    public static void main(String [] args){
        dataDemo de=new dataDemo();
    }
}
