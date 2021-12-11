/**
 * Signature Util for Darabonba. 
 */
// This file is auto-generated, don't edit it. Thanks.

using Org.BouncyCastle.Crypto.Macs;
using Org.BouncyCastle.Crypto.Parameters;
using Org.BouncyCastle.Security;
using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;

using Tea;
using Tea.Utils;


namespace AlibabaCloud.DarabonbaSignatureUtil
{
    public class Signer 
    {

        /**
         * HmacSHA1 Signature
         * @param stringToSign string
         * @param secret string
         * @return signed bytes
         */
        public static byte[] HmacSHA1Sign(string stringToSign, string secret)
        {
            byte[] signData;
            using (KeyedHashAlgorithm algorithm = CryptoConfig.CreateFromName("HMACSHA1") as KeyedHashAlgorithm)
            {
                algorithm.Key = Encoding.UTF8.GetBytes(secret);
                signData = algorithm.ComputeHash(Encoding.UTF8.GetBytes(stringToSign.ToCharArray()));
            }
            return signData;
        }

        /**
         * HmacSHA256 Signature
         * @param stringToSign string
         * @param secret string
         * @return signed bytes
         */
        public static byte[] HmacSHA256Sign(string stringToSign, string secret)
        {
            byte[] signData;
            using (KeyedHashAlgorithm algorithm = CryptoConfig.CreateFromName("HMACSHA256") as KeyedHashAlgorithm)
            {
                algorithm.Key = Encoding.UTF8.GetBytes(secret);
                signData = algorithm.ComputeHash(Encoding.UTF8.GetBytes(stringToSign.ToSafeString().ToCharArray()));
            }

            return signData;
        }

        /**
         * HmacSM3 Signature
         * @param stringToSign string
         * @param secret string
         * @return signed bytes
         */
        public static byte[] HmacSM3Sign(string stringToSign, string secret)
        {
            byte[] msg = Encoding.Default.GetBytes(stringToSign);
            byte[] key = Encoding.Default.GetBytes(secret);

            KeyParameter keyParameter = new KeyParameter(key);
            SM3Digest sm3 = new SM3Digest();

            HMac mac = new HMac(sm3);//����Կ���Ӵ��㷨
            mac.Init(keyParameter);
            mac.BlockUpdate(msg, 0, msg.Length);
            byte[] signData = new byte[mac.GetMacSize()];
            mac.DoFinal(signData, 0);
            return signData;
        }

        /**
         * SHA256withRSA Signature
         * @param stringToSign string
         * @param secret string
         * @return signed bytes
         */
        public static byte[] SHA256withRSASign(string stringToSign, string secret)
        {
            byte[] signData;
            using (var rsa = new RSACryptoServiceProvider())
            {
                rsa.FromXmlString(secret);
                var rsaClear = new RSACryptoServiceProvider();
                var paras = rsa.ExportParameters(true);
                rsaClear.ImportParameters(paras);
                using (var sha256 = new SHA256CryptoServiceProvider())
                {
                    signData = rsa.SignData(Encoding.UTF8.GetBytes(stringToSign), sha256);
                }
            }
            return signData;
        }

        /**
         * MD5 Signature
         * @param stringToSign string
         * @return signed bytes
         */
        public static byte[] MD5Sign(string stringToSign)
        {
            MD5 md5 = MD5.Create();
            byte[] signData = md5.ComputeHash(Encoding.UTF8.GetBytes(stringToSign.ToCharArray()));
            return signData;
        }

    }
}
