/* This file is Copyright (c) 2012 Robert Simons (CoHortSoftware@gmail.com).
 * See the MIT/X-like license in LICENSE.txt.
 * For more information visit www.cohortsoftware.com or contact CoHortSoftware@gmail.com.
 */
package com.cohort.util;

import java.io.OutputStream;

/** An outputStream that sends messages to String2.log. */
public class String2LogOutputStream extends OutputStream {

  // use StringBuffer (not StringBuilder) for thread safety
  final StringBuilder sb = new StringBuilder();

  @Override
  public void write(int b) {
    synchronized (sb) {
      if (b <= 13) { // one test
        if (b == 10) { // \n
          String2.log(sb.toString());
          sb.setLength(0);
          return;
        }

        if (b == 13) // \r
        return;

        // else fall through
      }

      sb.append((char) b);
    }
  }
} // End of String2LogOutputStream class.
