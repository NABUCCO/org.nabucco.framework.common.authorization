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
package org.nabucco.framework.common.authorization.impl.service.login.util;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.junit.Test;

/**
 * DatabaseAuthenticationTest
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class EnryptionTest {

    @Test
    public void testEnryption() throws Exception {

        String message = "nabucco";

        MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
        MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
        MessageDigest md5 = MessageDigest.getInstance("MD5");

        System.out.println(this.encrypt(message, sha1));
        System.out.println(this.encrypt(message, sha512));
        System.out.println(this.encrypt(message, md5));

    }

    /**
     * Encrypts the message
     * 
     * @param message
     *            the message to encrypt
     * @param algorithm
     *            the encryption algorithm
     * 
     * @return the encrypted message
     */
    private String encrypt(String message, MessageDigest algorithm) {
        algorithm.reset();
        algorithm.update(message.getBytes());

        BigInteger result = new BigInteger(1, algorithm.digest());
        return result.toString(16);
    }

}
