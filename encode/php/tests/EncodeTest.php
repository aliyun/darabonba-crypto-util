<?php

namespace AlibabaCloud\Darabonba\EncodeUtil\Tests;

use AlibabaCloud\Darabonba\EncodeUtil\EncodeUtil;
use AlibabaCloud\Tea\Utils\Utils;
use PHPUnit\Framework\TestCase;

/**
 * @internal
 * @coversNothing
 */
class ArrayTest extends TestCase
{
    public function test()
    {
        $this->assertEquals(
            '123abc%21%40%23%24%25%5E%26*%28%29-%3D_%2B+%7E%7C%5C%2F',
            EncodeUtil::urlEncode('123abc!@#$%^&*()-=_+ ~|\\/')
        );

         $this->assertEquals(
            '123abc%21%40%23%24%25%5E%26%2A%28%29-%3D_%2B%20~%7C%5C%2F',
            EncodeUtil::percentEncode('123abc!@#$%^&*()-=_+ ~|\\/')
        );


        $this->assertEquals(
            '/123/abc/d%21%40%23%24%25%5E%26%2A%28%29-%3D_%2B%20~%7C/sdf',
            EncodeUtil::pathEncode('/123/abc/d!@#$%^&*()-=_+ ~|/sdf')
        );


        $this->assertEquals(
            '140a1e05',
            EncodeUtil::hexEncode([20, 10, 30, 05])
        );

         $this->assertEquals(
            Utils::toBytes(hex2bin('9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08')),
            EncodeUtil::hash(Utils::toBytes('test'), 'ACS3-HMAC-SHA256')
        );

        $this->assertEquals(
            Utils::toBytes(hex2bin('9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08')),
            EncodeUtil::hash(Utils::toBytes('test'), 'ACS3-RSA-SHA256')
        );

        $this->assertEquals(
            Utils::toBytes(hex2bin('55e12e91650d2fec56ec74e1d3e4ddbfce2ef3a65890c2a19ecf88a307e76a23')),
            EncodeUtil::hash(Utils::toBytes('test'), 'ACS3-HMAC-SM3')
        );

        $this->assertEquals(
            '6Zi/6YeM5LqRLGNvZGluZyB0aGUgd29ybGQh',
            EncodeUtil::base64EncodeToString(Utils::toBytes('阿里云,coding the world!'))
        );

        $this->assertEquals(
            Utils::toBytes('阿里云,coding the world!'),
            EncodeUtil::base64Decode('6Zi/6YeM5LqRLGNvZGluZyB0aGUgd29ybGQh')
        );
    }
}
