/*
CSC-207 Mini-Project 1

Author: David William Stroud
Description: This project implements various early encryption techniques.
Resources: N/A
Source: https://github.com/dapper-gh/csc207-mp-1
 */

package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.CipherUtils;
import java.io.PrintWriter;

/**
 * A class that can encode or decode text using Caesar or Vigenere ciphers.
 */
public class Cipher {
  /**
   * A message to be used in printf representing a conflicting argument error.
   */
  private static final String REPEATED_ERROR = "Error: %s specified twice.\n";
  /**
   * A message to be used in printf representing an unspecified argument error.
   */
  private static final String UNSPECIFIED_ERROR = "Error: %s not specified.\n";

  /**
   * The main method of the program.
   * @param args The command-line arguments
   */
  public static void main(String[] args) {
    String text = null;
    String key = null;
    // Using Boolean instead of boolean to allow for null
    Boolean isCaesar = null;
    Boolean isEncode = null;

    for (String arg : args) {
      if (arg.equals("-encode")) {
        if (isEncode != null) {
          System.err.printf(Cipher.REPEATED_ERROR, "Encoding direction");
          return;
        } // if
        isEncode = true;
        continue;
      } // if

      if (arg.equals("-decode")) {
        if (isEncode != null) {
          System.err.printf(Cipher.REPEATED_ERROR, "Encoding direction");
          return;
        } // if
        isEncode = false;
        continue;
      } // if

      if (arg.equals("-caesar")) {
        if (isCaesar != null) {
          System.err.printf(Cipher.REPEATED_ERROR, "Cipher");
          return;
        } // if
        isCaesar = true;
        continue;
      } // if

      if (arg.equals("-vigenere")) {
        if (isCaesar != null) {
          System.err.printf(Cipher.REPEATED_ERROR, "Cipher");
          return;
        } // if
        isCaesar = false;
        continue;
      } // if

      if (text == null) {
        text = arg;
        continue;
      } // if

      if (key == null) {
        key = arg;
        continue;
      } // if

      System.err.println("Error: More than two non-flag arguments given.");
      return;
    } // for

    if (isCaesar == null) {
      System.err.printf(Cipher.UNSPECIFIED_ERROR, "Cipher type");
      return;
    } // if

    if (isEncode == null) {
      System.err.printf(Cipher.UNSPECIFIED_ERROR, "Encoding direction");
      return;
    } // if

    if (text == null) {
      System.err.printf(Cipher.UNSPECIFIED_ERROR, "Text");
      return;
    } // if

    if (key == null) {
      System.err.printf(Cipher.UNSPECIFIED_ERROR, "Key");
      return;
    } // if

    if (isCaesar && key.length() != 1) {
      System.err.println("Error: Cannot use Caesar cipher with a multi-character key.");
      return;
    } // if

    if (key.length() == 0) {
      System.err.println("Error: Cannot use a zero-length key.");
      return;
    } // if

    if (!CipherUtils.isValidInput(text)) {
      System.err.printf(CipherUtils.INVALID_INPUT_ERROR, "Text");
      return;
    } // if

    if (!CipherUtils.isValidInput(key)) {
      System.err.printf(CipherUtils.INVALID_INPUT_ERROR, "Key");
      return;
    } // if

    PrintWriter pen = new PrintWriter(System.out, true);
    if (isCaesar) {
      char keyChar = key.charAt(0);
      pen.println(
              isEncode
                      ? CipherUtils.caesarEncrypt(text, keyChar)
                      : CipherUtils.caesarDecrypt(text, keyChar)
      );
    } else {
      pen.println(
              isEncode
                      ? CipherUtils.vigenereEncrypt(text, key)
                      : CipherUtils.vigenereDecrypt(text, key)
      );
    } // else
    pen.close();
  } // main(String[])
} // Cipher
