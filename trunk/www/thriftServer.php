<?php
header('Content-Type: text/html; charset=utf-8');
/**
 * Created by PhpStorm.
 * User: admin
 * Date: 14-2-10
 * Time: 下午9:42
 */
$THRIFT_ROOT = dirname(__FILE__);

require_once $THRIFT_ROOT . '/lib/Thrift/ClassLoader/ThriftClassLoader.php';
use Thrift\ClassLoader\ThriftClassLoader;

$loader = new ThriftClassLoader();
$loader->registerNamespace('Thrift', $THRIFT_ROOT.'/lib');
$loader->registerDefinition('com', $THRIFT_ROOT . '/packages');
$loader->register();

use Thrift\Protocol\TBinaryProtocol;
use Thrift\Transport\TPhpStream;
use Thrift\Transport\TBufferedTransport;

require_once $THRIFT_ROOT . '/packages/com/test/service/impl/TestThriftServiceImpl.php';
require_once $THRIFT_ROOT . '/packages/com/test/service/impl/TestThriftServiceTwoImpl.php';

/*class TestThriftServiceImpl implements TestThriftServiceIf {

    public function test1($value1)
    {
        $result = array(
            'val'=>122,
            'val2'=>$value1,
            'val3'=>90999,
            'val4'=>array(
                't1'=>'好'
            )
        );
        return json_encode($result);
    }
}*/

header('Content-Type', 'application/x-thrift');
if (php_sapi_name() == 'cli') {
    echo "\r\n";
}

$uri = $_REQUEST['srv'];
$handler = 'com\\test\\service\\impl\\'.$uri;
$service = new $handler();
$tmp = explode('Impl', $uri);

//服务端接口处理类
$class = 'com\\test\\service\\'.$tmp[0].'Processor';
$processor = new $class($service);

/*$handler = new com\test\service\impl\TestThriftServiceImpl();
$processor = new TestThriftServiceProcessor($handler);*/

$transport = new TBufferedTransport(new TPhpStream(TPhpStream::MODE_R | TPhpStream::MODE_W));
$protocol = new TBinaryProtocol($transport, true, true);

$transport->open();
$processor->process($protocol, $protocol);
$transport->close();