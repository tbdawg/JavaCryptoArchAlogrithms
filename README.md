# JavaCryptoArchAlgorithms
Dynamically create Enums for Java Cryptography Architecture Standard Algorithms for your system by running Main.java in your IDE.

http://docs.oracle.com/javase/8/docs/technotes/guides/security/StandardNames.html

This program is used to create enums of the various cryptography algorithms.
Often when working with hashing passwords etc one must know the available algorithm strings to pass into
the corresponding method to create a key pair, hash, etc. With this program one can create enums that
consist of the available options for the algorithm type and use the values toString() method to pass the 
appropriate string to the method call.

Usage: Add this project to your project and then run Main.java or open the project in your IDE etc and 
run Main.java and copy the output enum files in the <code>com.chaoticdawgsoftware.algorithms.enums</code> 
package to your project as needed.

Example:<br>
...<br>
<code>import com.chaoticdawgsoftware.algorithms.enums.MacAlgorithms;</code><br>
<code>import javax.crypto.Mac;</code><br>
...<br>
...<br>
<code>private Mac hMac;</code><br>
...<br>
...<br>
<code>hMac = Mac.getInstance(MacAlgorithms.HmacSHA512.toString()); // "HmacSHA512"</code><br>


Instead of needing to look up the algorithm string from a list you can choose from the enum values.
This ensures that the option is available on your system, because the enums are created dynamically from the 
Providers that are on your system at build time.

Algorithms that have a character such as <code>".", "/", "-"</code> in the Provider key name have those
characters changed to <code>"_"</code> for the enum value/name. Likewise, a Provider key name that begins
with a number has an underscore appended to the front of that enum value/name. This is done
to make a legal name per Java naming rules. However, when the <code>toString()</code> method is called, 
the appropriate string value is returned. Example: <code>MessageDigestAlgorithms.SHA_512.toString()</code>
would return <code>"SHA-512"</code>

Algorithm retrievers can be added by simply adding the class file to:<br>
<code>com.chaoticdawgsoftware.algorithms.retrievers</code><br> 
See any of the <code>*Retriever.java</code> class files within this package implementing Retriever.
Then add the new algorithm Retriever to the <code>CreateAlgorithmEnums Retriever[] retrievers</code>
array.

Depending on your system, edits may be needed to <code>CreateAlgorithmEnums.create()</code> method and/or
the <code>ProviderAlgorithmRetrievingFactory.getAlgorithms()</code> method.

The algorithm enum java files will not be overridden if Main.java is run again. The file will need to
be deleted, moved, or renamed in order to be recreated.

This has only been tested on macOS Sierra using vanilla jdk1.8.0_92, jdk1.8.0_121, jdk1.8.0_131.
