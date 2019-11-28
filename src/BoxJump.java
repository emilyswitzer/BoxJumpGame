import javax.swing.*;

public class BoxJump {
    public static void main(String[] args) {
        JFrame f = new JFrame();
        JOptionPane.showMessageDialog(null,"Press space to jump!");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("Box Jump");
        f.setLocation(500,200);
        f.add(new GamePanel()); //creates the GamePanel object and adds it to JFrame f
        f.pack();
        f.setResizable(false);
        f.setVisible(true);

      }//end main
}//end BoxJump()
