/**
 * Encode Util for Darabonba. 
 */
// This file is auto-generated, don't edit it. Thanks.

using System;
using System.Collections;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;
using System.Web;


namespace AlibabaCloud.DarabonbaEncodeUtil
{
    public class Encoder 
    {

        /**
         * Encode the URL
         * @param url string
         * @return encoded string
         */
        public static string UrlEncode(string url)
        {
            return HttpUtility.UrlEncode(url);
        }

        /**
         * Special encoding for url params.
         * @param params string
         * @return encoded string
         */
        public static string PercentEncode(string raw)
        {
            if (raw == null)
            {
                return null;
            }

            var stringBuilder = new StringBuilder();
            var text = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_.~";
            var bytes = Encoding.UTF8.GetBytes(raw);
            foreach (char c in bytes)
            {
                if (text.IndexOf(c) >= 0)
                {
                    stringBuilder.Append(c);
                }
                else
                {
                    stringBuilder.Append("%").Append(string.Format(CultureInfo.InvariantCulture, "{0:X2}", (int)c));
                }
            }

            return stringBuilder.ToString().Replace("+", "%20")
                .Replace("*", "%2A").Replace("%7E", "~");
        }

        /**
         * Encode the partial path of url.
         * @param path string
         * @return encoded string
         */
        public static string PathEncode(string path)
        {
            List<string> encodeStr = new List<string>();
            string[] strSplit = path.Split('/');
            foreach (string str in strSplit)
            {
                encodeStr.Add(PercentEncode(str));
            }
            return string.Join("/", encodeStr);
        }

        /**
         * Hex encode for byte array.
         * @param raw byte array
         * @return encoded string
         */
        public static string HexEncode(byte[] raw)
        {
            if (raw == null)
            {
                return string.Empty;
            }

            StringBuilder result = new StringBuilder(raw.Length * 2);
            for (int i = 0; i < raw.Length; i++)
                result.Append(raw[i].ToString("x2"));
            return result.ToString();
        }

        /**
         * Hash the raw data with signatureAlgorithm.
         * @param raw hashing data
         * @param signatureAlgorithm the autograph method
         * @return hashed bytes
         */
        public static byte[] Hash(byte[] raw, string signatureAlgorithm)
        {
            if (signatureAlgorithm.Contains("HMAC-SHA256") || signatureAlgorithm.Contains("RSA-SHA256"))
            {
                byte[] signData;
                using (SHA256 sha256 = new SHA256Managed())
                {
                    signData = sha256.ComputeHash(raw);
                }

                return signData;
            }

            return null;
        }

        /**
         * Base64 encoder for byte array.
         * @param raw byte array
         * @return encoded string
         */
        public static string Base64EncodeToString(byte[] raw)
        {
            return Convert.ToBase64String(raw);
        }

        /**
         * Base64 dncoder for string.
         * @param src string
         * @return dncoded byte array
         */
        public static byte[] Base64Decode(string src)
        {
            return Convert.FromBase64String(src);
        }

    }
}
