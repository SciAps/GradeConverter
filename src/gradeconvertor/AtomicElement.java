/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradeconvertor;

import java.util.Comparator;

/**
 *
 * @author jchen
 */
public enum AtomicElement
{
    Hydrogen(1, "H",EGroup.other_nonmetals),
    Helium(2, "He",EGroup.noble_gases),
    Lithium(3, "Li",EGroup.alkali_metals),
    Beryllium(4, "Be",EGroup.alkali_earth_metals),
    Boron(5, "B",EGroup.metaloids),
    Carbon(6, "C",EGroup.other_nonmetals),
    Nitrogen(7, "N",EGroup.other_nonmetals),
    Oxygen(8, "O",EGroup.other_nonmetals),
    Fluorine(9, "F",EGroup.halogens),
    Neon(10, "Ne",EGroup.noble_gases),
    Sodium(11, "Na",EGroup.alkali_metals),
    Magnesium(12, "Mg",EGroup.alkali_earth_metals),
    Aluminum(13, "Al",EGroup.post_transition_metals),
    Silicon(14, "Si",EGroup.metaloids),
    Phosphorus(15, "P",EGroup.other_nonmetals),
    Sulfur(16, "S",EGroup.other_nonmetals),
    Chlorine(17, "Cl",EGroup.halogens),
    Argon(18, "Ar",EGroup.noble_gases),
    Potassium(19, "K",EGroup.alkali_metals),
    Calcium(20, "Ca",EGroup.alkali_earth_metals),
    Scandium(21, "Sc",EGroup.transition_metals),
    Titanium(22, "Ti",EGroup.transition_metals),
    Vanadium(23, "V",EGroup.transition_metals),
    Chromium(24, "Cr",EGroup.transition_metals),
    Manganese(25, "Mn",EGroup.transition_metals),
    Iron(26, "Fe",EGroup.transition_metals),
    Cobalt(27, "Co",EGroup.transition_metals),
    Nickel(28, "Ni",EGroup.transition_metals),
    Copper(29, "Cu",EGroup.transition_metals),
    Zinc(30, "Zn",EGroup.transition_metals),
    Gallium(31, "Ga",EGroup.post_transition_metals),
    Germanium(32, "Ge",EGroup.metaloids),
    Arsenic(33, "As",EGroup.metaloids),
    Selenium(34, "Se",EGroup.other_nonmetals),
    Bromine(35, "Br",EGroup.halogens),
    Krypton(36, "Kr",EGroup.noble_gases),
    Rubidium(37, "Rb",EGroup.alkali_metals),
    Strontium(38, "Sr",EGroup.alkali_earth_metals),
    Yttrium(39, "Y",EGroup.transition_metals),
    Zirconium(40, "Zr",EGroup.transition_metals),
    Niobium(41, "Nb",EGroup.transition_metals),
    Molybdenum(42, "Mo",EGroup.transition_metals),
    Technetium(43, "Tc",EGroup.transition_metals),
    Ruthenium(44, "Ru",EGroup.transition_metals),
    Rhodium(45, "Rh",EGroup.transition_metals),
    Palladium(46, "Pd",EGroup.transition_metals),
    Silver(47, "Ag",EGroup.transition_metals),
    Cadmium(48, "Cd",EGroup.transition_metals),
    Indium(49, "In",EGroup.post_transition_metals),
    Tin(50, "Sn",EGroup.post_transition_metals),
    Antimony(51, "Sb",EGroup.metaloids),
    Tellurium(52, "Te",EGroup.metaloids),
    Iodine(53, "I",EGroup.halogens),
    Xenon(54, "Xe",EGroup.noble_gases),
    Cesium(55, "Cs",EGroup.alkali_metals),
    Barium(56, "Ba",EGroup.alkali_earth_metals),
    Lanthanum(57, "La",EGroup.lanthanoids),
    Cerium(58, "Ce",EGroup.lanthanoids),
    Praseodymium(59, "Pr",EGroup.lanthanoids),
    Neodymium(60, "Nd",EGroup.lanthanoids),
    Promethium(61, "Pm",EGroup.lanthanoids),
    Samarium(62, "Sm",EGroup.lanthanoids),
    Europium(63, "Eu",EGroup.lanthanoids),
    Gadolinium(64, "Gd",EGroup.lanthanoids),
    Terbium(65, "Tb",EGroup.lanthanoids),
    Dysprosium(66, "Dy",EGroup.lanthanoids),
    Holmium(67, "Ho",EGroup.lanthanoids),
    Erbium(68, "Er",EGroup.lanthanoids),
    Thulium(69, "Tm",EGroup.lanthanoids),
    Ytterbium(70, "Yb",EGroup.lanthanoids),
    Lutetium(71, "Lu",EGroup.lanthanoids),
    Hafnium(72, "Hf",EGroup.transition_metals),
    Tantalum(73, "Ta",EGroup.transition_metals),
    Tungsten(74, "W",EGroup.transition_metals),
    Rhenium(75, "Re",EGroup.transition_metals),
    Osmium(76, "Os",EGroup.transition_metals),
    Iridium(77, "Ir",EGroup.transition_metals),
    Platinum(78, "Pt",EGroup.transition_metals),
    Gold(79, "Au",EGroup.transition_metals),
    Mercury(80, "Hg",EGroup.transition_metals),
    Thallium(81, "Tl",EGroup.post_transition_metals),
    Lead(82, "Pb",EGroup.post_transition_metals),
    Bismuth(83, "Bi",EGroup.post_transition_metals),
    Polonium(84, "Po",EGroup.metaloids),
    Astatine(85, "At",EGroup.halogens),
    Radon(86, "Rn",EGroup.noble_gases),
    Francium(87, "Fr",EGroup.alkali_metals),
    Radium(88, "Ra",EGroup.alkali_earth_metals),
    Actinium(89, "Ac",EGroup.actinoids),
    Thorium(90, "Th",EGroup.actinoids),
    Protactinium(91, "Pa",EGroup.actinoids),
    Uranium(92, "U",EGroup.actinoids),
    Neptunium(93, "Np",EGroup.actinoids),
    Plutonium(94, "Pu",EGroup.actinoids),
    Americium(95, "Am",EGroup.actinoids),
    Curium(96, "Cm",EGroup.actinoids),
    Berkelium(97, "Bk",EGroup.actinoids),
    Californium(98, "Cf",EGroup.actinoids),
    Einsteinium(99, "Es",EGroup.actinoids),
    Fermium(100, "Fm",EGroup.actinoids),
    Mendelevium(101, "Md",EGroup.actinoids),
    Nobelium(102, "No",EGroup.actinoids),
    Lawrencium(103, "Lr",EGroup.actinoids),
    Rutherfordium(104, "Rf",EGroup.transition_metals),
    Dubnium(105, "Db",EGroup.transition_metals),
    Seaborgium(106, "Sg",EGroup.transition_metals),
    Bohrium(107, "Bh",EGroup.transition_metals),
    Hassium(108, "Hs",EGroup.transition_metals),
    Meitnerium(109, "Mt",EGroup.transition_metals),
    Darmstadtium(110, "Ds",EGroup.transition_metals),
    Roentgenium(111, "Rg",EGroup.transition_metals),
    Copernicium(112, "Cn",EGroup.transition_metals),
    Ununtrium(113, "Uut",EGroup.post_transition_metals),
    Flerovium(114, "Fl",EGroup.post_transition_metals),
    Ununpentium(115, "Uup",EGroup.post_transition_metals),
    Livermorium(116, "Lv",EGroup.post_transition_metals),
    Ununseptium(117, "Uus",EGroup.halogens),
    Ununoctium(118, "Uuo",EGroup.noble_gases);

    public final int atomicNumber;
    public final String symbol;
    public final EGroup group;

    private AtomicElement(int atomicnum, String symbol,EGroup group)
    {
        this.atomicNumber = atomicnum;
        this.symbol = symbol;
        this.group=group;
    }

    public static AtomicElement getElementBySymbol(String symbol)
    {
        AtomicElement retval = null;
        for (AtomicElement e : AtomicElement.values())
        {
            if (e.symbol.equalsIgnoreCase(symbol))
            {
                retval = e;
                break;
            }
        }

        return retval;
    }

    public static AtomicElement getElementByAtomicNum(int i) {
        AtomicElement retval = null;
        for (AtomicElement e : AtomicElement.values())
        {
            if (e.atomicNumber==i)
            {
                retval = e;
                break;
            }
        }

        return retval;

    }

    public static enum EGroup{
        unknown,
        alkali_metals,
        alkali_earth_metals,
        lanthanoids,
        actinoids,
        transition_metals,
        post_transition_metals,
        metaloids,
        other_nonmetals,
        halogens,
        noble_gases
    }

    public static final Comparator<AtomicElement> Atomic_NumberComparator = new Comparator<AtomicElement>() {

        @Override
        public int compare(AtomicElement element, AtomicElement element2) {
            return element.atomicNumber - element2.atomicNumber;
        }
    };
    
    public static final Comparator<AtomicElement> Atomic_Name = new Comparator<AtomicElement>() {

        @Override
        public int compare(AtomicElement element, AtomicElement element2) {
            return element.name().compareTo(element2.name());
        }
    };

}
