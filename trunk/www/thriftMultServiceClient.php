<?php
/**
 * Created by PhpStorm.
 * User: liuzh
 * Date: 14-2-11
 * Time: 下午4:01
 */
$THRIFT_ROOT = dirname(__FILE__);

require_once $THRIFT_ROOT . '/lib/Thrift/ClassLoader/ThriftClassLoader.php';
use Thrift\ClassLoader\ThriftClassLoader;

$loader = new ThriftClassLoader();
$loader->registerNamespace('Thrift', $THRIFT_ROOT.'/lib');
$loader->registerDefinition('com', $THRIFT_ROOT . '/packages');
$loader->register();

use Thrift\Protocol\TMultiplexedProtocol;
use Thrift\Protocol\TProtocol;
use Thrift\Protocol\TProtocolDecorator;
use Thrift\Protocol\TBinaryProtocol;
use Thrift\Transport\TSocket;
use Thrift\Transport\THttpClient;
use Thrift\Transport\TBufferedTransport;
use Thrift\Exception\TException;

try {
    /*if (array_search('--http', $argv)) {
        $socket = new THttpClient('localhost', 8080, '/html/thrift_test.php');
    } else {
        $socket = new TSocket('localhost', 9988);
    }*/

    $socket = new TSocket('localhost', 9988);
    $transport = new TBufferedTransport($socket, 1024, 1024);
    $protocol = new TMultiplexedProtocol(new TBinaryProtocol($transport), "PERSON_SERVICE");

    echo "iiiiii\n\r</br>";
    $transport->open();

    $client = new \com\thrift\test\service\PersonServiceClient($protocol);

    $value = $client->getValue();

    print "sfsfsf\n\r</br>";
    print "$value\n</br>";

    $person = new \com\thrift\test\bo\Person();

    $person->name = "name1";
    $person->passwod = "password1";
    $person->age = "12";

    $p = $client->getPerson($person);

    print $p->name;

    $transport->close();

} catch (TException $tx) {
    print 'TException: ' . $tx->getMessage() . "\n";
}