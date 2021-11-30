// This file is auto-generated, don't edit it. Thanks.
package com.aliyun.darabonba.encode;

import com.aliyun.tea.*;
import com.aliyun.tea.utils.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Arrays;

public class Encoder {
    public final static String ENCODING = "UTF-8";
    public static final String HASH_SHA256 = "SHA-256";
    public static final String HASH_SM3 = "SM3";
    public static final String HMAC_SHA256 = "ACS3-HMAC-SHA256";
    public static final String RSA_SHA256 = "ACS3-RSA-SHA256";
    public static final String HMAC_SM3 = "ACS3-HMAC-SM3";

    /**
     * Encode the URL
     * @param url string
     * @return encoded string
     */
    public static String urlEncode(String url) throws UnsupportedEncodingException {
        return url != null ? URLEncoder.encode(url, ENCODING) : "";
    }

    /**
     * Special encoding for url params.
     * @param raw string
     * @return encoded string
     */
    public static String percentEncode(String raw) throws UnsupportedEncodingException {
        return raw != null ? URLEncoder.encode(raw, ENCODING).replace("+", "%20")
                .replace("*", "%2A").replace("%7E", "~") : null;
    }

    /**
     * Encode the partial path of url.
     * @param path string
     * @return encoded string
     */
    public static String pathEncode(String path) throws Exception {
        if (StringUtils.isEmpty(path) || "/".equals(path)) {
            return path;
        }
        String[] paths = path.split("/");
        StringBuilder sb = new StringBuilder();
        for (String s : paths) {
            sb.append(percentEncode(s));
            sb.append("/");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    /**
     * Hex encode for byte array.
     * @param raw byte array
     * @return encoded string
     */
    public static String hexEncode(byte[] raw) {
        if (raw == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : raw) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() < 2) {
                sb.append(0);
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * Hash the raw data with signatureAlgorithm.
     * @param raw hashing data
     * @param signatureAlgorithm the autograph method
     * @return hashed bytes
     */
    public static byte[] hash(byte[] raw, String signatureAlgorithm) throws Exception {
        if (signatureAlgorithm == null) {
            return null;
        }
        if (signatureAlgorithm.equals(HMAC_SHA256) || signatureAlgorithm.equals(RSA_SHA256)) {
            MessageDigest digest = MessageDigest.getInstance(HASH_SHA256);
            return digest.digest(raw);
        } else if (signatureAlgorithm.equals(HMAC_SM3)) {
            BouncyCastleProvider provider = new BouncyCastleProvider();
            MessageDigest digest = MessageDigest.getInstance(HASH_SM3, provider);
            return digest.digest(raw);
        }
        return null;
    }

    /**
     * Base64 encoder for byte array.
     * @param raw byte array
     * @return encoded string
     */
    public static String base64EncodeToString(byte[] raw) throws Exception {
        return DaraBase64.RFC4648.encode(raw);
    }

    /**
     * Base64 encoder for string.
     * @param src string
     * @return dncoded byte array
     */
    public static byte[] base64Decode(String src) throws Exception {
        return DaraBase64.RFC4648.decode(src);
    }


    public static class DaraBase64 {
        private final byte[] newline;
        private final int linemax;
        private final boolean isURL;
        private final boolean doPadding;
        private final boolean isMIME;

        private DaraBase64(boolean isURL, boolean isMIME, byte[] newline, int linemax, boolean doPadding) {
            this.isURL = isURL;
            this.isMIME = isMIME;
            this.newline = newline;
            this.linemax = linemax;
            this.doPadding = doPadding;
        }

        /**
         * This array is a lookup table that translates 6-bit positive integer
         * index values into their "Base64 Alphabet" equivalents as specified
         * in "Table 1: The Base64 Alphabet" of RFC 2045 (and RFC 4648).
         */
        private static final char[] toBase64 = {
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
        };

        /**
         * It's the lookup table for "URL and Filename safe Base64" as specified
         * in Table 2 of the RFC 4648, with the '+' and '/' changed to '-' and
         * '_'. This table is used when BASE64_URL is specified.
         */
        private static final char[] toBase64URL = {
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_'
        };

        private static final int[] fromBase64 = new int[256];
        static {
            Arrays.fill(fromBase64, -1);
            for (int i = 0; i < toBase64.length; i++)
                fromBase64[toBase64[i]] = i;
            fromBase64['='] = -2;
        }

        private static final int[] fromBase64URL = new int[256];
        static {
            Arrays.fill(fromBase64URL, -1);
            for (int i = 0; i < toBase64URL.length; i++)
                fromBase64URL[toBase64URL[i]] = i;
            fromBase64URL['='] = -2;
        }

        static final DaraBase64 RFC4648 = new DaraBase64(false, false, null, -1, true);

        private int encodeOutLength(int srclen) {
            int len = 0;
            if (doPadding) {
                len = 4 * ((srclen + 2) / 3);
            } else {
                int n = srclen % 3;
                len = 4 * (srclen / 3) + (n == 0 ? 0 : n + 1);
            }
            if (linemax > 0)                                  // line separators
                len += (len - 1) / linemax * newline.length;
            return len;
        }

        public String encode(byte[] src) throws UnsupportedEncodingException {
            int len = encodeOutLength(src.length);          // dst array size
            byte[] dst = new byte[len];
            int ret = encode0(src, 0, src.length, dst);
            if (ret != dst.length)
                return new String(Arrays.copyOf(dst, ret), ENCODING);
            return new String(dst, ENCODING);
        }

        private int encode0(byte[] src, int off, int end, byte[] dst) {
            char[] base64 = isURL ? toBase64URL : toBase64;
            int sp = off;
            int slen = (end - off) / 3 * 3;
            int sl = off + slen;
            if (linemax > 0 && slen  > linemax / 4 * 3)
                slen = linemax / 4 * 3;
            int dp = 0;
            while (sp < sl) {
                int sl0 = Math.min(sp + slen, sl);
                for (int sp0 = sp, dp0 = dp ; sp0 < sl0; ) {
                    int bits = (src[sp0++] & 0xff) << 16 |
                            (src[sp0++] & 0xff) <<  8 |
                            (src[sp0++] & 0xff);
                    dst[dp0++] = (byte)base64[(bits >>> 18) & 0x3f];
                    dst[dp0++] = (byte)base64[(bits >>> 12) & 0x3f];
                    dst[dp0++] = (byte)base64[(bits >>> 6)  & 0x3f];
                    dst[dp0++] = (byte)base64[bits & 0x3f];
                }
                int dlen = (sl0 - sp) / 3 * 4;
                dp += dlen;
                sp = sl0;
                if (dlen == linemax && sp < end) {
                    for (byte b : newline){
                        dst[dp++] = b;
                    }
                }
            }
            if (sp < end) {               // 1 or 2 leftover bytes
                int b0 = src[sp++] & 0xff;
                dst[dp++] = (byte)base64[b0 >> 2];
                if (sp == end) {
                    dst[dp++] = (byte)base64[(b0 << 4) & 0x3f];
                    if (doPadding) {
                        dst[dp++] = '=';
                        dst[dp++] = '=';
                    }
                } else {
                    int b1 = src[sp++] & 0xff;
                    dst[dp++] = (byte)base64[(b0 << 4) & 0x3f | (b1 >> 4)];
                    dst[dp++] = (byte)base64[(b1 << 2) & 0x3f];
                    if (doPadding) {
                        dst[dp++] = '=';
                    }
                }
            }
            return dp;
        }

        public byte[] decode(String src) throws UnsupportedEncodingException {
            return decode(src.getBytes(ENCODING));
        }

        public byte[] decode(byte[] src) {
            byte[] dst = new byte[decodeOutLength(src, 0, src.length)];
            int ret = decode0(src, 0, src.length, dst);
            if (ret != dst.length) {
                dst = Arrays.copyOf(dst, ret);
            }
            return dst;
        }

        private int decodeOutLength(byte[] src, int sp, int sl) {
            int[] base64 = isURL ? fromBase64URL : fromBase64;
            int paddings = 0;
            int len = sl - sp;
            if (len == 0)
                return 0;
            if (len < 2) {
                if (isMIME && base64[0] == -1)
                    return 0;
                throw new IllegalArgumentException(
                        "Input byte[] should at least have 2 bytes for base64 bytes");
            }
            if (isMIME) {
                // scan all bytes to fill out all non-alphabet. a performance
                // trade-off of pre-scan or Arrays.copyOf
                int n = 0;
                while (sp < sl) {
                    int b = src[sp++] & 0xff;
                    if (b == '=') {
                        len -= (sl - sp + 1);
                        break;
                    }
                    if ((b = base64[b]) == -1)
                        n++;
                }
                len -= n;
            } else {
                if (src[sl - 1] == '=') {
                    paddings++;
                    if (src[sl - 2] == '=')
                        paddings++;
                }
            }
            if (paddings == 0 && (len & 0x3) !=  0)
                paddings = 4 - (len & 0x3);
            return 3 * ((len + 3) / 4) - paddings;
        }

        private int decode0(byte[] src, int sp, int sl, byte[] dst) {
            int[] base64 = isURL ? fromBase64URL : fromBase64;
            int dp = 0;
            int bits = 0;
            int shiftto = 18;       // pos of first byte of 4-byte atom
            while (sp < sl) {
                int b = src[sp++] & 0xff;
                if ((b = base64[b]) < 0) {
                    if (b == -2) {         // padding byte '='
                        // =     shiftto==18 unnecessary padding
                        // x=    shiftto==12 a dangling single x
                        // x     to be handled together with non-padding case
                        // xx=   shiftto==6&&sp==sl missing last =
                        // xx=y  shiftto==6 last is not =
                        if (shiftto == 6 && (sp == sl || src[sp++] != '=') ||
                                shiftto == 18) {
                            throw new IllegalArgumentException(
                                    "Input byte array has wrong 4-byte ending unit");
                        }
                        break;
                    }
                    if (isMIME)    // skip if for rfc2045
                        continue;
                    else
                        throw new IllegalArgumentException(
                                "Illegal base64 character " +
                                        Integer.toString(src[sp - 1], 16));
                }
                bits |= (b << shiftto);
                shiftto -= 6;
                if (shiftto < 0) {
                    dst[dp++] = (byte)(bits >> 16);
                    dst[dp++] = (byte)(bits >>  8);
                    dst[dp++] = (byte)(bits);
                    shiftto = 18;
                    bits = 0;
                }
            }
            // reached end of byte array or hit padding '=' characters.
            if (shiftto == 6) {
                dst[dp++] = (byte)(bits >> 16);
            } else if (shiftto == 0) {
                dst[dp++] = (byte)(bits >> 16);
                dst[dp++] = (byte)(bits >>  8);
            } else if (shiftto == 12) {
                // dangling single "x", incorrectly encoded.
                throw new IllegalArgumentException(
                        "Last unit does not have enough valid bits");
            }
            // anything left is invalid, if is not MIME.
            // if MIME, ignore all non-base64 character
            while (sp < sl) {
                if (isMIME && base64[src[sp++]] < 0)
                    continue;
                throw new IllegalArgumentException(
                        "Input byte array has incorrect ending byte at " + sp);
            }
            return dp;
        }
    }
}