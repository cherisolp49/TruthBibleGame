package Gameplay;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cherisolp49
 */
import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;    //imported for testing purposes!!!!!!!!!!!delete when done!!!!!!!!
import javax.swing.*;

public class JButtonDemo extends JFrame {
  JButton[] b = new JButton[4]; // reference to the button object
  JLabel label;
  JFrame frame = new JFrame();
  boolean correct = false;
  boolean clicked = false;
  // constructor for ButtonFrame
  String answer;
  JButtonDemo(){}
  
  public void JButtonChoices(String ques, String ans, String b1, String b2, String b3, String b4) 
  {
   // super( title );                     // invoke the JFrame constructor
    label = new JLabel(ques, JLabel.CENTER);
    b[0] = new JButton(b1); // construct a JButton
    b[1] = new JButton(b2); // construct a JButton
    b[2] = new JButton(b3); // construct a JButton
    b[3] = new JButton(b4); // construct a JButton
    correct = false;
//    String ya = b[0].getActionCommand();  testing purrposes!!!!!!!!!!!!!
//    System.out.println(ya + "lkljklkl");
    for(int i=0; i<b.length; i++)
    {
        b[i].setBounds(50, 250, 40, 10);
    }
    
    for( int index=0; index<b.length; index++)
    {
//        System.out.println("hahahahahahha  1 ");
        displayJButton();
//            System.out.println("What is this: " + clicked);
        //System.out.println(b[j].getActionCommand());
        if(b[index].getActionCommand().equalsIgnoreCase(ans)){
//            correct = true;
//            isCorrect();
//            System.out.println("hahahahahahah\t\t2");
            String text = b[index].getText();
        b[index].addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JDialog d = new JDialog(frame, "You Got It Right!!!", true);
//                d.setLocationRelativeTo(frame);
//                d.setVisible(true);
//                System.out.println(clicked);
                correct = true;
                isCorrect();
                clicked = true;
                answer  = text;
                
            }
        });
        }else{
            String text = b[index].getText();
//            System.out.println("hahahahahah\t\t\t3");
            b[index].addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
//                JDialog d = new JDialog(frame, "wrong!!!!!!", true);
//                d.setLocationRelativeTo(frame);
//                d.setVisible(true);
                System.out.println("Got it wrong!!!");
                clicked = true;
                answer = text;
                isCorrect();
            }
        });
        }
//            System.out.println("heehehehehehehyoyoyoyoyoyoyoyoy\t" + b.length);
            
        
    }
    while(clicked==false){
        System.out.print(""); //do nothing...find a better way to do this as I believe you are wasting memory
    }
//      System.out.println("hahahahahahaha\t\t\t4");
            if(clicked==true){
//                System.out.println("something was click dawgie dawg!!!!!");
                frame.dispose();
                System.out.println(clicked + "\t\t and ");
            }
//      System.out.println("hehehehehehehehe");
  }
  
  
  public boolean isCorrect()
  {
      return correct;
  }

  public void displayJButton()
  {
      frame.setTitle("The Truth Bible Game");
      frame.setBounds(50, 50, 50, 50); 
      frame.setDefaultCloseOperation(EXIT_ON_CLOSE);  
      frame.setSize( 300, 300 );
      frame.setVisible( true );
      frame.setLayout(new GridLayout(5,15));      // set the layout manager
//      label.setVerticalTextPosition(JLabel.BOTTOM);
//      label.setHorizontalTextPosition(JLabel.CENTER);
      frame.add(label);
      frame.add(b[0]);                     // add the button to the JFrame
      frame.add(b[1]);
      frame.add(b[2]);
      frame.add(b[3]);
  }
  public String answer(){
      
      return answer;
  }
    public static void main(String[] args) {
        JButtonDemo ya;
        int count, sum, a, b, wrong, wrong2, wrong3= 0;
        Random r = new Random();
        for(int i=0; i<1; i++){
//            System.out.println("testing!!!!\t " + i);
           ya = new JButtonDemo();
           a = 1+r.nextInt(10);
           b = 1+r.nextInt(10);
           sum = a + b;
           String ques = "Whats the sum: " + a + " + " + b + " = ";
           wrong = 1+r.nextInt(10);
           wrong2 = 1+r.nextInt(10);
           wrong3 = 1+r.nextInt(10);
           ya.JButtonChoices(ques, sum+"", wrong2+"", wrong+"", sum+"", wrong3+"");
           System.out.println("isCorrect: " + ya.isCorrect());
            System.out.println(ya.answer());
           System.out.println("Game Over");
        }
    }
}
