/*
CSC-207 Mini-Project 1

Author: David William Stroud
Description: This project implements various early encryption techniques.
Resources: N/A
Source: https://github.com/dapper-gh/csc207-mp-1
 */

package edu.grinnell.csc207.util;

/**
 * This class provides a number of utility functions for Caesar/Vigenere encoding/decoding.
 */
public class CipherUtils {
  /**
   * A message to be used in printf representing an invalid input error.
   */
  public static final String INVALID_INPUT_ERROR =
          "Error: %s contains characters other than lowercase letters.\n";

  private static int letter2int(char letter) {
    return letter - 'a';
  } // letter2int(char)

  private static char int2letter(int i) {
    return (char) (i + (int) 'a');
  } // int2letter(int)

  private static char offsetChar(char toOffset, int offset) {
    return CipherUtils.int2letter(
            (CipherUtils.letter2int(toOffset) + offset + CipherUtils.letter2int('z') + 1)
                    % (CipherUtils.letter2int('z') + 1)
    );
  } // offsetChar(char, int)

  private static String caesarOffset(String str, int offset) {
    StringBuilder modified = new StringBuilder();
    for (char toOffset : str.toCharArray()) {
      modified.append(CipherUtils.offsetChar(toOffset, offset));
    } // for
    return modified.toString();
  } // caesarOffset(String, int)

  /**
   * Encrypts a message with the Caesar cipher.
   * @param str The message to encrypt
   * @param letter The letter with which to encrypt
   * @return The encrypted message
   */
  public static String caesarEncrypt(String str, char letter) {
    return CipherUtils.caesarOffset(str, letter2int(letter));
  } // caesarEncrypt(String, char)

  /**
   * Decrypts a message with the Caesar cipher.
   * @param str The message to decrypt
   * @param letter The letter with which to decrypt
   * @return The decrypted message
   */
  public static String caesarDecrypt(String str, char letter) {
    return CipherUtils.caesarOffset(str, -letter2int(letter));
  } // caesarDecrypt(String, char)

  private static String vigenereOffset(String str, String key, int offsetMultiplier) {
    StringBuilder modified = new StringBuilder();
    for (int i = 0; i < str.length(); i++) {
      char toOffset = str.charAt(i);
      int offset = CipherUtils.letter2int(key.charAt(i % key.length())) * offsetMultiplier;
      modified.append(CipherUtils.offsetChar(toOffset, offset));
    } // for
    return modified.toString();
  } // vigenereOffset(String, String, int)

  /**
   * Encrypts a message with the Vigenere cipher.
   * @param str The message to encrypt
   * @param key The key with which to encrypt
   * @return The encrypted message
   */
  public static String vigenereEncrypt(String str, String key) {
    return CipherUtils.vigenereOffset(str, key, 1);
  } // vigenereEncrypt(String, String)

  /**
   * Decrypts a message with the Vigenere cipher.
   * @param str The message to decrypt
   * @param key The key with which to decrypt
   * @return The decrypted message
   */
  public static String vigenereDecrypt(String str, String key) {
    return CipherUtils.vigenereOffset(str, key, -1);
  } // vigenereDecrypt(String, String)

  /**
   * Determines if a string contains only valid, code-able characters.
   * @param str The string to check
   * @return Whether the string is code-able
   */
  public static boolean isValidInput(String str) {
    for (char c : str.toCharArray()) {
      if ('a' > c || 'z' < c) {
        return false;
      } // if
    } // for

    return true;
  } // isValidInput(String)
} // CipherUtils
