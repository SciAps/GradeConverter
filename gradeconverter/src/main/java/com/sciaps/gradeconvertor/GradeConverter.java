package com.sciaps.gradeconvertor;


import au.com.bytecode.opencsv.CSVReader;
import com.google.common.base.Objects;
import com.google.common.collect.Collections2;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sciaps.common.AtomicElement;
import com.sciaps.common.data.Grade;
import org.apache.commons.cli.*;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GradeConverter {

    private static final Pattern MIN_REGEX = Pattern.compile("([A-Z][a-z]*)\\sMin");
    private static final Pattern MAX_REGEX = Pattern.compile("([A-Z][a-z]*)\\sMax");
    private static final Pattern WEIGHT_REGEX = Pattern.compile("([A-Z][a-z]*)\\sWeight");

    private static final int COLUMN_UNS = 0;
    private static final int COLUMN_BASE = 1;
    private static final int COLUMN_NAME = 2;


    public static void main(String[] args) {

        Options options = new Options();
        options.addOption(Option.builder("i")
                .hasArg()
                .argName("input.csv")
                .required(false)
                .build());

        options.addOption(Option.builder("o")
                .hasArg()
                .argName("output.json")
                .required(false)
                .build());

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);


            File inputFile = new File(cmd.getOptionValue("i", "AlloyLibrary.csv"));
            if(!inputFile.exists()) {
                System.out.println(String.format("file does not exists: %s", inputFile.getAbsolutePath()));
                System.exit(1);
            }

            File outFile = new File(cmd.getOptionValue("o", "AlloyLibrary.json"));

            CSVReader reader = new CSVReader(new FileReader(inputFile));
            String[] line = reader.readNext();

            //verify the header
            if(!line[COLUMN_UNS].contains("UNS")) {
                throw new Exception(String.format("column %d must be UNS", COLUMN_UNS));
            }
            if(!line[COLUMN_BASE].contains("Base")) {
                throw new Exception(String.format("column %d must be Alloy Base", COLUMN_BASE));
            }
            if(!line[COLUMN_NAME].contains("Name")) {
                throw new Exception(String.format("column %d must be Grade name", COLUMN_NAME));
            }

            HashMap<AtomicElement, Integer> minColumns = new HashMap<AtomicElement, Integer>();
            HashMap<AtomicElement, Integer> maxColumns = new HashMap<AtomicElement, Integer>();
            HashMap<AtomicElement, Integer> weightColumns = new HashMap<AtomicElement, Integer>();

            for(int i=3;i<line.length;i++) {

                final String headerName = line[i];


                Matcher m = MIN_REGEX.matcher(headerName);
                if(m.find()){
                    final String symbol = m.group(1);
                    AtomicElement element = AtomicElement.getElementBySymbol(symbol);
                    minColumns.put(element, i);
                    continue;
                }

                m = MAX_REGEX.matcher(headerName);
                if(m.find()) {
                    final String symbol = m.group(1);
                    AtomicElement element = AtomicElement.getElementBySymbol(symbol);
                    maxColumns.put(element, i);
                    continue;
                }

                m = WEIGHT_REGEX.matcher(headerName);
                if(m.find()) {
                    final String symbol = m.group(1);
                    AtomicElement element = AtomicElement.getElementBySymbol(symbol);
                    weightColumns.put(element, i);
                    continue;
                }

                System.out.println(String.format("unknown column: %d", i));

            }

            Set<AtomicElement> availableElements = Sets.intersection(minColumns.keySet(), maxColumns.keySet());


            List<Grade> grades = new ArrayList<Grade>();

            while((line = reader.readNext()) != null) {
                Grade grade = new Grade();
                grade.uns = line[COLUMN_UNS];

                String baseName = line[COLUMN_BASE];
                AtomicElement element = AtomicElement.getElementByName(baseName);
                if(element != null) {
                    baseName = element.symbol;
                }

                if(StringUtils.isNotBlank(baseName) && StringUtils.isNotBlank(line[COLUMN_NAME])){
                    grade.name = String.format("%s_%s", baseName, line[COLUMN_NAME]);
                } else if(StringUtils.isBlank(baseName) && StringUtils.isBlank(line[COLUMN_NAME])){
                    continue;
                } else if(StringUtils.isBlank(baseName)) {
                    grade.name = line[COLUMN_NAME];
                } else if(StringUtils.isBlank(line[COLUMN_NAME])) {
                    grade.name = baseName;
                }





                for(AtomicElement e : availableElements) {
                    Grade.Spec spec = new Grade.Spec();

                    spec.min = parseNumber(line[minColumns.get(e)]);
                    spec.max = parseNumber(line[maxColumns.get(e)]);

                    if((spec.min == -1 && spec.max == -1) || (spec.min == 0 && spec.max == 0)) {
                        continue;
                    }

                    if(weightColumns.containsKey(e)) {
                        spec.weight = Double.parseDouble(line[weightColumns.get(e)]);
                    } else {
                        spec.weight = 1.0;
                    }

                    if(isValid(spec)) {
                        grade.spec.put(e, spec);
                    } else {
                        System.err.println(String.format("Warning: spec is invalid for %s", e));
                    }
                }
                grades.add(grade);
            }

            Gson gson = new GsonBuilder().create();

            FileWriter writer = new FileWriter(outFile);
            gson.toJson(grades, writer);
            writer.close();



        } catch (ParseException e) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "cmdlineparser", options );
            System.exit(1);
        } catch(IOException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    private static double parseNumber(String str) {
        double retval = -1;
        if(StringUtils.isNotBlank(str)) {
            try {
                retval = Double.parseDouble(str);
            } catch(NumberFormatException e){}
        }
        return retval;
    }

    private static boolean isValid(Grade.Spec spec) {
        return spec.min >= 0 && spec.max <= 100 && spec.max >= spec.min;
    }
}
