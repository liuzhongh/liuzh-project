<?php
namespace com\test\service\impl;
/**
 * Created by PhpStorm.
 * User: admin
 * Date: 14-2-10
 * Time: 下午11:33
 */

use com\test\service\TestThriftServiceIf;

class TestThriftServiceImpl implements TestThriftServiceIf {

    public function test1($value1)
    {
        $result = array(
            'val'=>122,
            'val2'=>$value1,
            'val3'=>9011999,
            'val4'=>array(
                't1'=>'好'
            )
        );
        return json_encode($result);
    }
}