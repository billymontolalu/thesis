/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Arrays;

/**
 *
 * @author Momo
 */
public final class SyntaxString {
    public static boolean isSyntaxString(String syntaxString)
    {
        String[] syntaxStrings = {"String", "int", "set", "get"};
        return Arrays.asList(syntaxStrings).contains(syntaxString);
    }
    
    static String removeSyntax(String input)
    {
        return input.replaceAll("String|int |\\(\\)", "");
    }
}
