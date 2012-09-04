/*
 * Copyright 2012 PRODYNA AG
 *
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nabucco.framework.common.authorization.impl.service.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.nabucco.framework.base.facade.datatype.security.Subject;

/**
 * EncryptionUtility
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class EncryptionUtility {

    /** Encryption algorithm */
    private static final String ENCRYPTION_ALGORITHM = "SHA-512";

    /** Random numbers */
    private static final Random RAND = new Random();

    /**
     * Private constructor must not be invoked.
     */
    private EncryptionUtility() {
        throw new IllegalStateException("Private constructor must not be invoked.");
    }

    /**
     * Generates the session-token for the given subject.
     * 
     * @param subject
     *            the subject to create to token for
     */
    public static void generateSecurityToken(Subject subject) {
        final MessageDigest md;
        try {
            md = MessageDigest.getInstance(ENCRYPTION_ALGORITHM);
        } catch (NoSuchAlgorithmException nsae) {
            throw new InternalError("Internal error: SHA-512 not available.");
        }

        md.reset();
        byte time = (byte) System.currentTimeMillis();
        md.update(time);

        Runtime rt = Runtime.getRuntime();
        byte[] memBytes = longToByteArray(rt.totalMemory());
        md.update(memBytes, 0, memBytes.length);
        memBytes = longToByteArray(rt.freeMemory());
        md.update(memBytes, 0, memBytes.length);

        byte[] rand = new byte[128];
        RAND.nextBytes(rand);
        md.update(rand);

        subject.setToken(md.digest());
    }

    /**
     * Converts a long value to a byte array.
     * 
     * @param l
     *            the long value
     * 
     * @return the byte array
     */
    private static byte[] longToByteArray(long l) {
        byte[] retVal = new byte[8];
        for (int i = 0; i < 8; i++) {
            retVal[i] = (byte) l;
            l >>= 8;
        }
        return retVal;
    }

    /**
     * Enrypt a message by the SHA-512 algorithm.
     * 
     * @param message
     *            the message to encrypt
     * 
     * @return the encrypted message
     */
    public static String encrypt(String message) {
        return encrypt(message, ENCRYPTION_ALGORITHM);
    }

    /**
     * Encrypt a message with the given algorithm.
     * 
     * @param message
     *            the message to encrypt
     * @param algorithm
     *            the encryption algorithm
     * 
     * @return the encrypted message
     */
    public static String encrypt(String message, String algorithm) {
        final MessageDigest md;
        try {
            md = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new InternalError("Internal error: " + algorithm + " not available.");
        }

        md.reset();
        md.update(message.getBytes());

        BigInteger encrypted = new BigInteger(1, md.digest());
        return encrypted.toString(16);
    }
}
