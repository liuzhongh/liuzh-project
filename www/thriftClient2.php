<?php
header('Content-Type: text/html; charset=utf-8');
/**
 * Created by PhpStorm.
 * User: admin
 * Date: 14-2-10
 * Time: 下午10:08
 */
$THRIFT_ROOT = dirname(__FILE__);

require_once $THRIFT_ROOT . '/lib/Thrift/ClassLoader/ThriftClassLoader.php';
use Thrift\ClassLoader\ThriftClassLoader;

$loader = new ThriftClassLoader();
$loader->registerNamespace('Thrift', $THRIFT_ROOT.'/lib');
$loader->registerDefinition('com', $THRIFT_ROOT . '/packages');
$loader->register();

use Thrift\Protocol\TBinaryProtocol;
use Thrift\Transport\THttpClient;
use Thrift\Transport\TBufferedTransport;
use Thrift\Exception\TException;
use com\test\service\TestThriftServiceTwoClient;

//require_once $THRIFT_ROOT . '/packages/com/test/service/TestThriftService.php';
require_once $THRIFT_ROOT . '/packages/com/test/service/TwoTypes.php';

try {
    $socket = new THttpClient($_SERVER['HTTP_HOST'], $_SERVER['SERVER_PORT'], '/thriftServer.php?srv=TestThriftServiceTwoImpl');
    $transport = new TBufferedTransport($socket, 1024, 1024);
    $protocol = new TBinaryProtocol($transport);
    $client = new TestThriftServiceTwoClient($protocol);

    $transport->open();

    $val = $client->test1('weweew89');
    var_dump($val);

    $person = new com\test\service\Person();

    $person->name = 'name1';
    $person->age = 32;
    $person->password = 'password';

    $val = $client->testPerson($person);
    var_dump($val);

    $transport->close();

} catch (TException $tx) {
    print 'TException: '.$tx->getMessage()."\n";
}