// This file is auto-generated, don't edit it
/**
 * Encode Util for Darabonba. 
 */
import * as $tea from '@alicloud/tea-typescript';
import crypto from 'crypto';


export default class Client {

  /**
   * Encode the URL
   * @param url string
   * @return encoded string
   */
  static urlEncode(url: string): string {
    //URLEncoder.encode(url, URL_ENCODING)
    return url != null ? encodeURIComponent(url) : '';
  }

  /**
   * Special encoding for url params.
   * @param params string
   * @return encoded string
   */
  static percentEncode(raw: string): string {
    return raw != null ? encodeURIComponent(raw).replace('+', '%20')
      .replace('*', '%2A').replace('%7E', '~') : null;
  }

  /**
   * Encode the partial path of url.
   * @param path string
   * @return encoded string
   */
  static pathEncode(path: string): string {
    if (!path || path === '/') {
      return path;
    }
    let paths = path.split('/');
    let sb = [];
    for (let s of paths) {
      sb.push(Client.percentEncode(s));
    }
    return sb.join('/');
  }

  /**
   * Hex encode for byte array.
   * @param raw byte array
   * @return encoded string
   */
  static hexEncode(raw: Buffer): string {
    if (raw === null) {
      return null;
    }
    return raw.toString("hex");
  }

  /**
   * Hash the raw data with signatureAlgorithm.
   * @param raw hashing data
   * @param signatureAlgorithm the autograph method
   * @return hashed bytes
   */
  static hash(raw: Buffer, signatureAlgorithm: string): Buffer {
    if (signatureAlgorithm == null) {
      return null;
    }
    if (signatureAlgorithm === "ACS3-HMAC-SHA256" || signatureAlgorithm === "ACS3-RSA-SHA256") {
      const obj = crypto.createHash('sha256');
      obj.update(raw);
      return obj.digest();
    } else if (signatureAlgorithm == "ACS3-HMAC-SM3") {
      const obj = crypto.createHash('sm3');
      obj.update(raw);
      return obj.digest();
    }
  }

  /**
   * Base64 encoder for byte array.
   * @param raw byte array
   * @return encoded string
   */
  static base64EncodeToString(raw: Buffer): string {
    return raw.toString('base64');
  }

  /**
   * Base64 dncoder for string.
   * @param src string
   * @return dncoded byte array
   */
  static base64Decode(src: string): Buffer {
    /** Convert Base64 data to a string */
    var toBinaryTable = [-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63,
      52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, 0, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
      15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
      41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1
    ];
    var result = '';
    var leftbits = 0;
    var leftdata = 0;
    for (var i = 0; i < src.length; i++) {
      var c = toBinaryTable[src.charCodeAt(i) & 0x7f];
      var padding = (src.charCodeAt(i) == '='.charCodeAt(0));
      if (c == -1) continue;
      leftdata = (leftdata << 6) | c;
      leftbits += 6;
      if (leftbits >= 8) {
        leftbits -= 8;
        if (!padding)
          result += String.fromCharCode((leftdata >> leftbits) & 0xff);
        leftdata &= (1 << leftbits) - 1;
      }
    }
    if (leftbits)
      throw Error('Corrupted base64 string');
    return Buffer.from(result);
  }

}
