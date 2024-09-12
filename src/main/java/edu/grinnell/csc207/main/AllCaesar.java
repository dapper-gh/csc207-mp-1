/*
CSC-207 Mini-Project 1

Author: David William Stroud
Description: This project implements various early encryption techniques.
Resources: N/A
Source: https://github.com/dapper-gh/csc207-mp-1
 */

package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import edu.grinnell.csc207.util.CipherUtils;

/**
 * A class that allows the user to encode/decode messages with the Caesar cipher.
 */
public class AllCaesar {
  /**
   * The main method of the program.
   * @param args The command-line arguments
   */
  public static void main(String[] args) {
    boolean encode;
    if (args.length != 2) {
      System.err.println("Error: Incorrect number of parameters.");
      return;
    } // if

    if (args[0].equals("encode")) {
      encode = true;
    } else if (args[0].equals("decode")) {
      encode = false;
    } else {
      System.err.printf(
              "Error: Invalid option: \"%s\". Valid options are \"encode\" or \"decode\".\n",
              args[0]
      );
      return;
    } // if-else-if chain

    PrintWriter pen = new PrintWriter(System.out, true);
    String str = args[1];
    if (!CipherUtils.isValidInput(str)) {
      System.err.printf(CipherUtils.INVALID_INPUT_ERROR, "Text");
      return;
    } // if

    for (char ch = 'a'; ch <= 'z'; ch++) {
      pen.printf(
              "n = %c: %s\n",
              ch,
              encode
                      ? CipherUtils.caesarEncrypt(str, ch)
                      : CipherUtils.caesarDecrypt(str, ch)
      );
    } // for
    pen.close();
  } // main(String[])
} // AllCaesar
