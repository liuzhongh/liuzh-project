<?php
header('Content-Type: text/html; charset=utf-8');
/**
 * Created by PhpStorm.
 * User: admin
 * Date: 14-2-10
 * Time: 下午10:08
 * (调试未成功)
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
use Thrift\Transport\TSocket;
use com\test\service\TestThriftServiceClient;

//require_once $THRIFT_ROOT . '/packages/com/test/service/TestThriftService.php';
//require_once $THRIFT_ROOT . '/packages/com/test/service/PersonServiceTypes.php';

try {
    $socket = new TSocket($_SERVER['HTTP_HOST'], '9988');
    $transport = new TBufferedTransport($socket, 1024, 1024);
    $protocol = new TBinaryProtocol($transport);
    $client = new TestThriftServiceClient($protocol);

    $transport->open();

    $val = $client->test1('weweew89');
    var_dump($val);

    $transport->close();

} catch (TException $tx) {
    print 'TException: '.$tx->getMessage()."\n";
}