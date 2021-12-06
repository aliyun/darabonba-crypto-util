<?php

// This file is auto-generated, don't edit it. Thanks.
// namespace AlibabaCloud\Darabonba\Array;

// use \Exception;

namespace AlibabaCloud\Darabonba\EncodeUtil;

use AlibabaCloud\Tea\Model;
use AlibabaCloud\Tea\Request;
use AlibabaCloud\Tea\Utils\Utils;
use OneSm\Sm3;
use Psr\Http\Message\StreamInterface;


/**
 * This is a array module.
 */
class EncodeUtil
{   
    public static function urlEncode($raw)
    {
        if (empty($raw)) {
            throw new \InvalidArgumentException('not a valid value for parameter');
        }
        $str = urlencode($raw);
        $str = str_replace("%20", "+", $str);
        $str = str_replace("%2A", "*", $str);
        return $str;
    }

    /**
     * @param string $raw
     * @return bool
     */
    public static function percentEncode($raw)
    {
        if (!isset($raw)) {
            throw new \InvalidArgumentException('not a valid value for parameter');
        }
        $str = urlencode($raw);
        $str = str_replace("+", "%20", $str);
        $str = str_replace("*", "%2A", $str);
        $str = str_replace("%7E", "~", $str);
        return $str;
    }

    /**
     * @param string $raw
     * @return bool
     */
    public static function pathEncode($raw)
    {
        if (empty($raw) || $raw === '/') {
            return $raw;
        }
        $arr = explode('/', $raw);
        $ret = '';
        foreach ($arr as $i => $path) {
            $str = self::percentEncode($path);
            $ret .= "$str/";
        }
        return substr($ret, 0, -1);
    }

     /**
     * @param byte[] $raw
     * @return bool
     */
    public static function hexEncode($raw)
    {
        if (empty($raw)) {
            throw new \InvalidArgumentException('not a valid value for parameter');
        }
        $ret = '';
        foreach ($raw as $i => $b) {
            $str = dechex($b);
            if(strlen($str) < 2) {
                $str = str_pad($str, 2, '0', STR_PAD_LEFT);
            }
            $ret .= $str;
        }
        return $ret;
    }

     /**
     * @param byte[] $raw
     * @param string $algorithm
     * @return bytes[] hashed bytes
     */
    public static function hash($raw, $algorithm)
    {
        if (empty($raw) || empty($algorithm)) {
            return null;
        }

        $str = Utils::toString($raw);

        if($algorithm === 'ACS3-HMAC-SHA256' || $algorithm === 'ACS3-RSA-SHA256') {
            $res = hash('sha256', $str, true);
            return Utils::toBytes($res);
        } else if($algorithm === 'ACS3-HMAC-SM3') {
            $res = self::sm3($str);
            return Utils::toBytes(hex2bin($res));
        }
        return null;
    }

    private static function sm3($str)
    {
        return (new Sm3())->sign($str);
    }

    public static function base64EncodeToString($raw) {
        if (empty($raw)) {
            return '';
        }
        $str = Utils::toString($raw);
        return base64_encode($str);
    }

    public static function  base64Decode($raw) {
        if (empty($raw)) {
            return null;
        }
        
        $str = base64_decode($raw);
        return Utils::toBytes($str);
    }
}
