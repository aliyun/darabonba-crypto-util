

import * as $tea from '@alicloud/tea-typescript';
import 'mocha';
import assert from 'assert';
import client from '../src/client'
describe('Tea Util', function () {
  it('urlEncode should ok', function () {
    const result = client.urlEncode('https://www.baidu.com/')
    assert.strictEqual("https%3A%2F%2Fwww.baidu.com%2F", result);
  });
  it('percentEncode should ok', function () {
    const result = client.percentEncode('https://www.bai+*~du.com/')
    assert.strictEqual("https%3A%2F%2Fwww.bai%2B%2A~du.com%2F", result);
  });
  it('pathEncode should ok', function () {
    const result = client.pathEncode("/work_space/DARABONBA/GIT/darabonba-util/ts")
    assert.strictEqual("/work_space/DARABONBA/GIT/darabonba-util/ts", result);
  });
  it('hexEncode & hash should ok', function () {
    const test = Buffer.from('test');
    var res = client.hexEncode(client.hash(test, "ACS3-HMAC-SHA256"));
    assert.strictEqual("9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08", res);

    res = client.hexEncode(client.hash(test, "ACS3-RSA-SHA256"));
    assert.strictEqual("9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08", res);

    res = client.hexEncode(client.hash(test, "ACS3-HMAC-SM3"));
    assert.strictEqual("55e12e91650d2fec56ec74e1d3e4ddbfce2ef3a65890c2a19ecf88a307e76a23", res);
  });

  it('base64EncodeToString should ok', function () {
    const test = Buffer.from('test');
    var res = client.base64EncodeToString(test);
    assert.strictEqual("dGVzdA==", res);
  });

  it('base64Decode should ok', function () {
    const test = 'dGVzdA==';
    var res = client.base64Decode(test);
    let expected = Buffer.from('test')
    assert.deepStrictEqual(expected, res);
  });
})