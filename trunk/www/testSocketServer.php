<?php
/**
 * Created by PhpStorm.
 * User: liuzh
 * Date: 14-2-11
 * Time: 下午4:48
 * (调试未成功)
 */
$THRIFT_ROOT = dirname(__FILE__);

require_once $THRIFT_ROOT . '/lib/Thrift/ClassLoader/ThriftClassLoader.php';
use Thrift\ClassLoader\ThriftClassLoader;

$loader = new ThriftClassLoader();
$loader->registerNamespace('Thrift', $THRIFT_ROOT.'/lib');
$loader->registerDefinition('com', $THRIFT_ROOT . '/packages');
$loader->register();

use Thrift\Server\TServerSocket;
use Thrift\Factory\TTransportFactory;
use Thrift\Factory\TBinaryProtocolFactory;
use Thrift\Server\TForkingServer;

require_once $THRIFT_ROOT . '/packages/com/test/service/impl/TestThriftServiceImpl.php';
require_once $THRIFT_ROOT . '/packages/com/thrift/test/service/impl/PersonServiceImpl.php';

$handler = new \com\thrift\test\service\impl\PersonServiceImpl();
$processor = new \com\thrift\test\service\PersonServiceProcessor($handler);

$transport =  new TServerSocket('localhost', 9988);;

$outputTransportFactory = $inputTransportFactory = new TTransportFactory($transport);
$outputProtocolFactory = $inputProtocolFactory = new TBinaryProtocolFactory();
$server = new TForkingServer(
    $processor,
    $transport,
    $inputTransportFactory,
    $outputTransportFactory,
    $inputProtocolFactory,
    $outputProtocolFactory
);
header('Content-Type: application/x-thrift');
print 'Starting the server...';
$server->serve();
