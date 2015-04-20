/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradeconvertor;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 *
 * @author jchen
 */
public class GradeConvertor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        if (args.length == 1) {
            try {
                InputStream inputStream = new FileInputStream(args[0]);
                CSVReader reader = new CSVReader();
                reader.readCSVFromInputStream(inputStream);
                reader.printGrade();
                
            } catch (Exception e) {
                System.out.println(e.getMessage() + "&&&&&");
            }
        } else {
            System.out.println("No args");
        } 
       
    }
    
}
