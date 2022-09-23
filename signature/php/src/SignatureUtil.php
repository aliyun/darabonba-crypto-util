<?php

// This file is auto-generated, don't edit it. Thanks.
// namespace AlibabaCloud\Darabonba\Array;

// use \Exception;

namespace AlibabaCloud\Darabonba\SignatureUtil;

use AlibabaCloud\Tea\Utils\Utils;
use OneSm\Sm3;


/**
 * This is a array module.
 */
class SignatureUtil
{
    /**
     * HmacSHA1 Signature
     * @param string $stringToSign string
     * @param string $secret string
     * @return array signed bytes
     */
    public static function HmacSHA1Sign($stringToSign, $secret)
    {
        if (!isset($stringToSign) || !isset($secret)) {
            throw new \InvalidArgumentException('not a valid value for parameter');
        }
        $str = hash_hmac('sha1', $stringToSign, $secret, true);
        return Utils::toBytes($str);
    }

    /**
     * HmacSHA1 Signature
     * @param string $stringToSign string
     * @param int[] $secret bytes
     * @return array signed bytes
     */
    public static function HmacSHA1SignByBytes($stringToSign, $secret)
    {
        return self::HmacSHA1Sign($stringToSign, Utils::toString($secret));
    }

    /**
     * HmacSHA256 Signature
     * @param string $stringToSign string
     * @param string $secret string
     * @return array signed bytes
     */
    public static function HmacSHA256Sign($stringToSign, $secret)
    {
        if (!isset($stringToSign) || !isset($secret)) {
            throw new \InvalidArgumentException('not a valid value for parameter');
        }
        $str = hash_hmac('sha256', $stringToSign, $secret, true);
        return Utils::toBytes($str);
    }

    /**
     * HmacSHA256 Signature
     * @param string $stringToSign string
     * @param int[] $secret bytes
     * @return array signed bytes
     */
    public static function HmacSHA256SignByBytes($stringToSign, $secret)
    {
        return self::HmacSHA256Sign($stringToSign, Utils::toString($secret));
    }

    /**
     * HmacSM3 Signature
     * @param string $stringToSign string
     * @param string $secret string
     * @return array signed bytes
     */
    public static function HmacSM3Sign($stringToSign, $secret)
    {
        if (!isset($stringToSign) || !isset($secret)) {
            throw new \InvalidArgumentException('not a valid value for parameter');
        }
        $ret = self::hmac_sm3($stringToSign, $secret, true);;
        return Utils::toBytes($ret);
    }

    /**
     * HmacSM3 Signature
     * @param string $stringToSign string
     * @param int[] $secret bytes
     * @return array signed bytes
     */
    public static function HmacSM3SignByBytes($stringToSign, $secret)
    {
        return self::HmacSM3Sign($stringToSign, Utils::toString($secret));
    }

    private static function hmac_sm3($data, $key, $raw_output = false)
    {
        $pack      = 'H' . \strlen(self::sm3('test'));
        $blocksize = 64;
        if (\strlen($key) > $blocksize) {
            $key = pack($pack, self::sm3($key));
        }
        $key  = str_pad($key, $blocksize, \chr(0x00));
        $ipad = $key ^ str_repeat(\chr(0x36), $blocksize);
        $opad = $key ^ str_repeat(\chr(0x5C), $blocksize);
        $hmac = self::sm3($opad . pack($pack, self::sm3($ipad . $data)));

        return $raw_output ? pack($pack, $hmac) : $hmac;
    }

    private static function sm3($str)
    {
        return (new Sm3())->sign($str);
    }

    /**
     * SHA256withRSA Signature
     * @param string $stringToSign string
     * @param string $secret string
     * @return array signed bytes
     */
    public static function SHA256withRSASign($stringToSign, $secret)
    {
        if (!isset($stringToSign) || !isset($secret)) {
            throw new \InvalidArgumentException('not a valid value for parameter');
        }
        $ret = '';
        $privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" . $secret . "\n-----END RSA PRIVATE KEY-----";
        @openssl_sign($stringToSign, $ret, $privateKey, OPENSSL_ALGO_SHA256);
        return Utils::toBytes($ret);
    }

    /**
     * MD5 Signature
     * @param string $stringToSign string
     * @return array signed bytes
     */
    public static function MD5Sign($stringToSign)
    {
        return Utils::toBytes(md5($stringToSign, true));
    }

    /**
     * MD5 Signature
     * @param int[] $bytesToSign bytes
     * @return array signed bytes
     */
    public static function MD5SignForBytes($bytesToSign)
    {
        return self::MD5Sign(Utils::toString($bytesToSign));
    }
}
