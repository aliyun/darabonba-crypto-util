// This file is auto-generated, don't edit it
/**
 * Signature Util for Darabonba. 
 */
import * as $tea from '@alicloud/tea-typescript';
import crypto from 'crypto';


export default class Client {

  /**
   * HmacSHA1 Signature
   * @param stringToSign string
   * @param secret string
   * @return signed bytes
   */
  static HmacSHA1Sign(stringToSign: string, secret: string): Buffer {
    const obj = crypto.createHmac('sha1', secret);
    obj.update(stringToSign);
    return obj.digest();
  }

  /**
   * HmacSHA256 Signature
   * @param stringToSign string
   * @param secret string
   * @return signed bytes
   */
  static HmacSHA256Sign(stringToSign: string, secret: string): Buffer {
    const obj = crypto.createHmac('sha256', secret);
    obj.update(stringToSign);
    return obj.digest();
  }

  /**
   * HmacSM3 Signature
   * @param stringToSign string
   * @param secret string
   * @return signed bytes
   */
  static HmacSM3Sign(stringToSign: string, secret: string): Buffer {
    const obj = crypto.createHmac('sm3', secret);
    obj.update(stringToSign);
    return obj.digest();
  }

  /**
   * SHA256withRSA Signature
   * @param stringToSign string
   * @param secret string
   * @return signed bytes
   */
  static SHA256withRSASign(stringToSign: string, secret: string): Buffer {
    const PEM_BEGIN = "-----BEGIN PRIVATE KEY-----\n";
    const PEM_END = "\n-----END PRIVATE KEY-----";
    if (!secret.startsWith(PEM_BEGIN)) {
      secret = PEM_BEGIN + secret;
    }
    if (!secret.endsWith(PEM_END)) {
      secret = secret + PEM_END;
    }
    var signerObject = crypto.createSign("RSA-SHA256");
    signerObject.update(stringToSign);
    var signature = signerObject.sign({ key: secret, padding: crypto.constants.RSA_PKCS1_PADDING });
    return signature;
  }

  /**
   * MD5 Signature
   *
   * @param stringToSign string
   * @return signed bytes
   */
  static MD5Sign(stringToSign: string): Buffer {
    return crypto.createHash('md5').update(stringToSign, 'utf8').digest();
  }

  /**
   * MD5 Signature
   *
   * @param bytesToSign bytes
   * @return signed bytes
   */
   static MD5SignForBytes(bytesToSign: Buffer): Buffer {
    return crypto.createHash('md5').update(bytesToSign.toString(), 'utf8').digest();
  }

}
