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
use com\thrift\test\bo\Person;

//require_once $THRIFT_ROOT . '/packages/com/test/service/TestThriftService.php';

try {
    $socket = new THttpClient($_SERVER['HTTP_HOST'], $_SERVER['SERVER_PORT'], '/thriftServer.php?srv=PersonServiceImpl');
    $transport = new TBufferedTransport($socket, 1024, 1024);
    $protocol = new TBinaryProtocol($transport);
    $client = new \com\thrift\test\service\PersonServiceClient($protocol);

    $transport->open();

    $val = $client->getValue();
    var_dump($val);

    $person = new Person();

    $person->name = 'name1';
    $person->age = 3122;
    $person->passwod = 'pwd';

    $val = $client->getPerson($person);
    var_dump($val);

    $transport->close();

} catch (TException $tx) {
    print 'TException: '.$tx->getMessage()."\n";
}