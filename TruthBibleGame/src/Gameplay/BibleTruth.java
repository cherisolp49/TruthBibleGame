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
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.Random;
import java.util.Date;
import javax.swing.JButton;

public class BibleTruth {

    private String[][] list;  //col 1 for question, col2 for answer
    private String option[] = new String[4];
    private int oldQues[], quesCount=1; //oldQues - being created to ensure no duplicate questions, quesCount - curr number start from 1
    private boolean duplicated;
    Players player;
    JButtonDemo b = new JButtonDemo();

    
   /*
    @param passing the name of the file
    */
    BibleTruth(String file, int numOfQues) {
        
        //construct the two dim array-questions & answers
        this.list = new String[numOfQues][7];
        this.oldQues = new int[list.length];

        try {
            Scanner in = new Scanner(new File(file));

            String gTemp = ""; //temp string
            while (in.hasNext()) {
                int num = in.nextInt();
                gTemp = in.next();//getting next line
                String question = "";

                //get the question
                while (!(gTemp.equalsIgnoreCase("#"))) {
                  
                    //concatenate the sentence
                    question = question + " " + gTemp;
                    gTemp = in.next();
                }//eo question
                  this.list[num][0] = question; //save the question

                gTemp = in.next();//getting next line
                String answer = "";
             
                while (!(gTemp.equalsIgnoreCase("#"))) //get the answer
                {
                    //concatenate the sentence
                    answer = answer + " " + gTemp;
                    gTemp = in.next();
                }//eo answer
                this.list[num][1] = answer; //save the answer 
    //////////////////////////////////////////////////////////////////////////////starting the multiple choice below            
                gTemp = in.next();//getting next line
                String option2 = "";
             
                while (!(gTemp.equalsIgnoreCase("#"))) //get the answer
                {
                    //concatenate the sentence
                    option2 = option2 + " " + gTemp;
                    gTemp = in.next();
                }//eo option2
                this.list[num][2] = option2; //save the answer
                
                gTemp = in.next();//getting next line
                String option3 = "";
             
                while (!(gTemp.equalsIgnoreCase("#"))) //get the answer
                {
                    //concatenate the sentence
                    option3 = option3 + " " + gTemp;
                    gTemp = in.next();
                }//eo option3
                this.list[num][3] = option3; //save the answer
                
                gTemp = in.next();//getting next line
                String option4 = "";
             
                while (!(gTemp.equalsIgnoreCase("#"))) //get the answer
                {
                    //concatenate the sentence
                    option4 = option4 + " " + gTemp;
                    gTemp = in.next();
                }//eo option4
                this.list[num][4] = option4; //save the answer
///////////////////////ending multiple choice-----Starting Reference/////////////////////////////////
                 gTemp = in.next();//getting next line
                String reference = "";
             
                while (!(gTemp.equalsIgnoreCase("#"))) //get the answer
                {
                    //concatenate the sentence
                    reference = reference + " " + gTemp;
                    gTemp = in.next();
                }//eo reference
                this.list[num][5] = reference; //save the answer
////////////////////////Level of Difficulty//////////////////////////////////////////////
                gTemp = in.next();//getting next line
                String levelOfDiff = "";
             
                while (!(gTemp.equalsIgnoreCase("#"))) //get the answer
                {
                    //concatenate the sentence
                    levelOfDiff = levelOfDiff + " " + gTemp;
                    gTemp = in.next();
                }//eo option4
                this.list[num][6] = levelOfDiff; //save the levelofDiff
                
            }//eol
            in.close();//closing scanner
          //System.out.println( printArray() ); //used this to test the array
        } catch (FileNotFoundException b) {
            System.out.println("File Not Found ");
        } catch (Exception d) {
            d.getMessage();
        }

    }//eoc

//method for creating a new game
//test method for displaying
    
    //this method is for testing the array 
    public String printArray() {

        String out = "NOW PRINTING THE LIST \n";
        for (int row = 1; row < this.list.length; row++) {
            for (int col = 0; col < this.list[row].length; col++) {
                if (col == 0) {
                    out = out + "Question " + row + " is " + this.list[row][col] + "\n";
                } else {
                    
                    out += "Answer " + " is " + this.list[row][col] + "\n";
                }
            }//eo col 
            out += "\n";
        }//eo row
        System.out.println(out);
         return out;

    }//end of printArray
    
    /*
        This method generates a random number
        Every number produced from this method is a newly generated number, meaning
        there will be no duplicates produced by this method
    */
    public int randomQuestionGenerator()
    {
        Random r = new Random();
        int randNum = 0;  //random question number from the text file;
        int index = quesCount - 1;

        randNum = 1 + r.nextInt(this.list.length - 1); //gets a random question number

            /*
            @param if the number isnt a duplicate, then the while statement is false and its automatically exited, if it is 
            a duplicate then it enters into the while statement until it isnt
            */
            while(this.isNumADuplicateQues(randNum))//making sure that we are not going to have duplicate questions
            {
                randNum = 1 + r.nextInt(this.list.length - 1); //gets a random question number
                this.isNumADuplicateQues(randNum);

            }//eo while loop
            oldQues[index] = randNum;

        return randNum;
    }//end of randomQuestionGenerator
    
    
      /*
      @param number is the number of a random question that will be extracted
      from the text file
      */
      public String getMultipleQuestions(int number)
      {
        option = new String[4];
        Random rand = new Random();
        int e;  //random number
        int counter = 1;    //counter is the number representing the multiple choice options
        //there are 4 options and the counter represents each one
        for (int i = 0; i < option.length; i++) 
        {
            e = rand.nextInt(4);    //random number from 0-3
                
            if(option[e] == null)
            {
                option[e] = this.list[number][counter]; //its extracting the MCOptions
                counter++;
            }
            else
            {
                boolean empty = true;
                
                while(empty == true)
                {
                    e = rand.nextInt(4);
                    if(option[e] == null) //is this spot empty?
                    {
                        //option[e] = "Question number " + counter; //extract the multiple choices and put them here!!!
                        option[e] = this.list[number][counter];
                        counter++;
                        empty = false;
                    }

                }
                
            }
               
        }
        //JButtonDemo b = new JButtonDemo();
        
        //b.JButtonChoices(option[0], option[1], option[2], option[3]);
        String choice = "A) " + option[0] + "\nB) " + option[1] + "\nC) " + option[2] + "\nD) " + option[3] + "\n";
        
        return choice;
      }//end of getMultipleQuestions
      
      /*
      @param testing for duplicated questions. dupNumCheck is the number of
      the question being checked to see if it hasnt already occured
      */
      public boolean isNumADuplicateQues(int dupNumCheck)
      {
          duplicated = false;
          int k = 0;
          //oldQues[k] can't equal 0 unless it hasnt yet been assigned a number, dupNumCheck will 
          //always be > 0 since the question numbers start from 1 to the end
          while(oldQues[k] != 0) 
          {
              if(oldQues[k] == dupNumCheck)
              {
                  duplicated = true;
                  break;
              }
              else
              {
                  k++;
              }
          }
          return duplicated;
      }
      
      public void gameOn2()
      {
           for (int i = 0; i < list.length-1; i++)
           {
               //getTheQuestions();
               getQuestions2();
           }
           for (int j=0; j<oldQues.length-1; j++)
           {
               //System.out.print(oldQues[j] + "  ");
           }
      }
      
      public void JBQues(String  ques, String ans)
      {
          System.out.println("Entering JBQuest.....");
          b = new JButtonDemo();
          b.JButtonChoices(ques, ans, option[0], option[1], option[2], option[3]);
      }

      public void openFile() throws IOException
      {
          Runtime rt=Runtime.getRuntime();
          
          String file = "C:\\Users\\Patrick\\Documents\\NetBeansProjects\\TheTruthBibleGame\\testthisfilething1.txt";
          Process p = rt.exec("notepad " + file);
      }
      public void getQuestions2()
    {
        String result = "";
        String gTemp = "";
        int num = randomQuestionGenerator(); //gets a random non duplicated number
        String choices =  getMultipleQuestions(num);    //gets all multiple choice answers
        String ques1 = this.list[num][0];
        JBQues(this.list[num][0], this.list[num][1]);//passing the question and answer to the JButtons
        String ques = ques1 + "\n" + choices;

        //gets the button that was clicked and checks to see if it is correct or not;
        if(checkAnswer2(b.answer(), num))//if answer is correct
        {
            JOptionPane.showMessageDialog(null, "You Got It Right!!!\nReference: " + this.list[num][5]);
            //System.out.println(this.list[num][6] + "testing!!!!!!!!!!!fcdwfdsafdsa");
            //player.incrementPoints(this.list[num][6]); //determines how much points player gets for correct response
            gTemp = quesCount + " " + this.list[num][0] + " " + "The answer is: " + this.list[num][1]
                    + " [You got this question correct!!!!]";
            result = gTemp + " " + result;
            //player.viewResults(result);
        }
        else
        {
            System.out.println("........." + b.answer());
            JOptionPane.showMessageDialog(null, "Sorry, but it looks like you were wrong.\nReference: " + this.list[num][5]);
            gTemp = quesCount + " " + this.list[num][0] + " " + "The answer is: " + this.list[num][1]
                    + " [You got this question incorrect]";
            result = gTemp + " " + result;
           // player.viewResults(result);
        }
        quesCount++;
    }//end of getQuestions
      
      public boolean checkAnswer2(String input, int num)
      {
          boolean correct = false;
          
          
         if(input.equalsIgnoreCase(this.list[num][1]))
         {
                  correct = true;
         }
          return correct;
      }//end of checkAnswer
}//eof
