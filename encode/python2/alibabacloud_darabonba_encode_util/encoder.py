# -*- coding: utf-8 -*-
# This file is auto-generated, don't edit it. Thanks.
try:
    from urllib import urlencode, quote_plus, quote
except ImportError:
    from urllib.parse import urlencode, quote_plus, quote
import binascii
import hashlib
import base64

from .sm3 import hash_sm3

class Encoder(object):
    """
    Encode Util for Darabonba.
    """
    def __init__(self):
        pass

    @staticmethod
    def url_encode(url):
        """
        Encode the URL

        @type url: str
        @param url: string

        @rtype: str
        @return: encoded string
        """
        return urlencode(url)

    @staticmethod
    def percent_encode(raw):
        """
        Special encoding for url params.

        @param params: string

        @rtype: str
        @return: encoded string
        """
        return quote(str(raw), safe='~')

    @staticmethod
    def path_encode(path):
        """
        Encode the partial path of url.

        @type path: str
        @param path: string

        @rtype: str
        @return: encoded string
        """
        return quote(str(path), safe='/~')

    @staticmethod
    def hex_encode(raw):
        """
        Hex encode for byte array.

        @param raw: byte array

        @rtype: str
        @return: encoded string
        """
        return binascii.b2a_hex(raw).decode('utf-8')

    @staticmethod
    def hash(raw, signature_algorithm):
        """
        Hash the raw data with signatureAlgorithm.

        @param raw: hashing data

        @type signature_algorithm: str
        @param signature_algorithm: the autograph method

        @return: hashed bytes
        """
        if signature_algorithm == 'ACS3-HMAC-SHA256' or signature_algorithm == 'ACS3-RSA-SHA256':
            return hashlib.sha256(raw).digest()
        elif signature_algorithm == 'ACS3-HMAC-SM3':
            return hash_sm3(raw)

    @staticmethod
    def base_64encode_to_string(raw):
        """
        Base64 encoder for byte array.

        @param raw: byte array

        @rtype: str
        @return: encoded string
        """
        return str(base64.b64encode(raw))

    @staticmethod
    def base_64decode(src):
        """
        Base64 dncoder for string.

        @type src: str
        @param src: string

        @return: dncoded byte array
        """
        return base64.b64encode(src).decode('utf-8')
