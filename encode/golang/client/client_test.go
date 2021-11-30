// This file is auto-generated, don't edit it. Thanks.
/**
 * Encode Util for Darabonba.
 */
package client

import (
	"testing"

	"github.com/alibabacloud-go/tea/tea"
	"github.com/alibabacloud-go/tea/utils"
)

func Test_UrlEncode(t *testing.T) {
	var str = tea.String("hello")
	res := UrlEncode(str)
	utils.AssertEqual(t, "aGVsbG8=", tea.StringValue(res))
}

func Test_PercentEncode(t *testing.T) {
	str := tea.String("test*abcd++")
	res := PercentEncode(str)
	utils.AssertEqual(t, "test%2Aabcd%2B%2B", tea.StringValue(res))
}

func Test_PathEncode(t *testing.T) {
	str := tea.String("test*/abcd+/+")
	res := PathEncode(str)
	utils.AssertEqual(t, "test%2A/abcd%2B/%2B", tea.StringValue(res))
}

func Test_HexEncode(t *testing.T) {
	res := HexEncode(Hash([]byte("test"), tea.String("ACS3-HMAC-SHA256")))
	utils.AssertEqual(t, "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08", tea.StringValue(res))

	res = HexEncode(Hash([]byte("test"), tea.String("ACS3-RSA-SHA256")))
	utils.AssertEqual(t, "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08", tea.StringValue(res))

	res = HexEncode(Hash([]byte("test"), tea.String("ACS3-HMAC-SM3")))
	utils.AssertEqual(t, "55e12e91650d2fec56ec74e1d3e4ddbfce2ef3a65890c2a19ecf88a307e76a23", tea.StringValue(res))

	res = HexEncode(Hash([]byte("test"), tea.String("ACS3-HM-SM3")))
	utils.AssertEqual(t, "", tea.StringValue(res))
}

func Test_Hash(t *testing.T) {
	byt := []byte("test")
	signatureAlgorithm := tea.String("ACS3-HMAC-SHA256")
	res := Hash(byt, signatureAlgorithm)
	utils.AssertEqual(t, []byte{159, 134, 208, 129, 136, 76, 125, 101, 154, 47, 234, 160, 197, 90, 208, 21, 163, 191, 79, 27, 43, 11, 130, 44, 209, 93, 108, 21, 176, 240, 10, 8}, res)
}

func TestBase64EncodeToString(t *testing.T) {
	raw := []byte("test")
	res := Base64EncodeToString(raw)
	utils.AssertEqual(t, "dGVzdA==", tea.StringValue(res))
}

func TestBase64Decode(t *testing.T) {
	var str = tea.String("test")
	res := Base64Decode(str)
	utils.AssertEqual(t, []byte{181, 235, 45}, res)
}
