package Utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5
{
    private static final int HEXADECIMAL_RADIX = 16;

    private static final int POSITIVE_SIGNUM = 1;

    private static final String MD5_SIGNATURE_FORMAT = "%32s";

    private static final String MD5 = "MD5";

    private static final String HEXADECIMAL_PREFIX = "0x";


    /**
     * This function get a string as input and calculate its MD5 value.
     *
     * @param stringToCalculateMD5HashOn    The String that we will calculate the cryptographic hash function MD5 on.
     * @return  The hash value of the input string.
     */
    public static String calculateHash(String stringToCalculateMD5HashOn) {
        try {
            /*
            Creating an MessageDigest instance with MD5 algorithm, digesting our input string,
            converting it into BigDecimal in oder to use its toString method with changeable radix (we use hexadecimal),
            aligning it with zero so it length will match the MD5 128 bit signature, and adding the hexadecimal string prefix "0x".
             */
            return HEXADECIMAL_PREFIX +
                    alignZeroes(
                            new BigInteger(POSITIVE_SIGNUM, MessageDigest.getInstance(MD5).digest(stringToCalculateMD5HashOn.getBytes()))
                                    .toString(HEXADECIMAL_RADIX).toUpperCase());
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param someMD5Signature  Partial signature, with length that might be smaller then 32 characters.
     * @return  A full signature with length of exact 32 characters, so it will match the conventional MD5 128 bit signature.
     */
    private static String alignZeroes(String someMD5Signature) {
        return String.format(MD5_SIGNATURE_FORMAT, someMD5Signature).replace(' ', '0');
    }
}
