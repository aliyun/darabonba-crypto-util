# -*- coding: utf-8 -*-
import base64
import unittest

from alibabacloud_darabonba_encode_util.encoder import Encoder


class TestEncoder(unittest.TestCase):
    def test_percent_encode(self):
        self.assertEqual(
            'https%3A%2F%2Fwww.bai%2B%2A~du.com%2F',
            Encoder.percent_encode('https://www.bai+*~du.com/'),
        )

    def test_path_encode(self):
        self.assertEqual(
            '/work_space/DARABONBA/GIT/darabonba-util/ts',
            Encoder.path_encode('/work_space/DARABONBA/GIT/darabonba-util/ts'),
        )

    def test_hex_encode_and_hash(self):
        raw = b'test'
        self.assertEqual(
            '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08',
            Encoder.hex_encode(Encoder.hash(raw, 'ACS3-HMAC-SHA256')),
        )
        self.assertEqual(
            '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08',
            Encoder.hex_encode(Encoder.hash(raw, 'ACS3-RSA-SHA256')),
        )
        self.assertEqual(
            '55e12e91650d2fec56ec74e1d3e4ddbfce2ef3a65890c2a19ecf88a307e76a23',
            Encoder.hex_encode(Encoder.hash(raw, 'ACS3-HMAC-SM3')),
        )

    def test_base_64encode_to_string(self):
        self.assertEqual('dGVzdA==', Encoder.base_64encode_to_string(b'test'))

    def test_base_64decode(self):
        # base64Decode must decode, not encode.
        self.assertEqual(b'test', Encoder.base_64decode('dGVzdA=='))

    def test_base_64decode_utf8_payload(self):
        raw = '阿里云,coding the world!'.encode('utf-8')
        encoded = base64.b64encode(raw).decode('utf-8')
        self.assertEqual(raw, Encoder.base_64decode(encoded))

    def test_base64_round_trip(self):
        raw = b'\x00\x01\x02\xff darabonba'
        encoded = Encoder.base_64encode_to_string(raw)
        self.assertEqual(raw, Encoder.base_64decode(encoded))


if __name__ == '__main__':
    unittest.main()
