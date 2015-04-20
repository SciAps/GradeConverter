/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradeconvertor;

import java.util.TreeMap;

/**
 *
 * @author jchen
 */
public class Grade {
    
    public static class Spec {
        public double min;
        public double max;
        public boolean isTramp = false;
    }

    public String comments = "";
    public String uns = "";
    public String name = "";
    public boolean enabled = true;
    public TreeMap<AtomicElement, Spec> spec = new TreeMap<AtomicElement, Spec>();
    
}
