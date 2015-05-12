/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sciaps.gradeconvertor;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author jchen
 */
public class CSVReader {
    ArrayList<Grade> grades = new ArrayList<Grade>();
        
    public CSV readCSVFromInputStream(InputStream is) throws IOException
    {
        CSV csv = new CSV();
        csv.csvRows = new ArrayList<String[]>();
        
        String[] header = null;
        
        int index = 0;
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new InputStreamReader(is));

            String strLine;
            while ((strLine = br.readLine()) != null)
            {
                //System.out.println(strLine);
                
                if (index == 0) {

                    header = strLine.split(",");
                    
                } else if (index > 0 && strLine.replace(",", "").trim().isEmpty() == false) {
                    
                    String[] columns = strLine.split(",");
                    
                    if (columns.length > 0) {
                    
                        String base = columns[1];

                        if (base != null && base.trim().isEmpty() == false) {
                            Grade grade = new Grade();
                            grade.comments = "";

                            if (columns[0] == null) {
                                grade.uns = "";
                            } else {
                                grade.uns = columns[0];
                            }

                            if (base.length() <= 2) {
                                grade.name = base + "_" + columns[2];
                            } else {
                                grade.name = getELementSymbol(base) + "_" + columns[2];
                            }

                            double min;
                            double max;
                            for(int i = 3; i < columns.length; i +=2) {
                                min = Double.parseDouble(columns[i]);
                                max = Double.parseDouble(columns[i+1]);

                                if (min != 0 || max != 0) {
                                    Grade.Spec spec = new Grade.Spec();
                                    spec.min = min;
                                    spec.max = max;
                                    
                                    String symbol = header[i].replace("Min", "").trim();
                                    AtomicElement element =  AtomicElement.getElementBySymbol(symbol);
                                    grade.spec.put(element, spec);
                                }
                            }
                            grades.add(grade);
                        }
                    }
                }
                
                index++;
            }
            
        } catch (Exception e) {
            System.out.println("EXCEPITON ***** " + e.getMessage());
        }
        finally
        {
            br.close();
        }

        return csv;
    }
    
    private String getELementSymbol(String baseName) {
        String symbol = "";
        
        for (AtomicElement e : AtomicElement.values())
        {
            if (e.name().equals(baseName)) {
                symbol = e.symbol;
            }
        }
                
        return symbol;         
    }
    
    public void printGrade() {
        Gson gson = new Gson();
        String jsonOutput = gson.toJson(grades);
      
        try {
            File file = new File("AlloyLibrary.json");
            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            output.write(jsonOutput);
            output.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        } 
    }
}
