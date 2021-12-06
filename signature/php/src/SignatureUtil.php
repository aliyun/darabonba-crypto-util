<?php

// This file is auto-generated, don't edit it. Thanks.
// namespace AlibabaCloud\Darabonba\Array;

// use \Exception;

namespace AlibabaCloud\Darabonba\SignatureUtil;

use AlibabaCloud\Tea\Model;
use AlibabaCloud\Tea\Request;
use AlibabaCloud\Tea\Utils\Utils;
use OneSm\Sm3;
use Psr\Http\Message\StreamInterface;


/**
 * This is a array module.
 */
class SignatureUtil
{   
    public static function HmacSHA1Sign($stringToSign, $secret)
    {
        if (!isset($stringToSign) || !isset($secret)) {
            throw new \InvalidArgumentException('not a valid value for parameter');
        }
        $str = hash_hmac('sha1', $stringToSign, $secret, true);
        return Utils::toBytes($str);
    }

    /**
     * @param string $raw
     * @return bool
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
     * @param string $raw
     * @return bool
     */
    public static function HmacSM3Sign($stringToSign, $secret)
    {
        if (!isset($stringToSign) || !isset($secret)) {
            throw new \InvalidArgumentException('not a valid value for parameter');
        }
        $ret = self::hmac_sm3($stringToSign, $secret, true);;
        return Utils::toBytes($ret);
    }

    private static function hmac_sm3($data, $key, $raw_output = false){
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
     * @param byte[] $raw
     * @return bool
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
}
