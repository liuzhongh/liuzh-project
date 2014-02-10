<?php
/**
 * Created by PhpStorm.
 * User: admin
 * Date: 14-2-11
 * Time: 上午12:10
 */

namespace com\test\service\impl;

use com\test\service\TestThriftServiceTwoIf;

require_once dirname(__FILE__).'/../TwoTypes.php';

class TestThriftServiceTwoImpl implements \com\test\service\TestThriftServiceTwoIf{

    public function test1($value1)
    {
        return json_encode(array(
            'v1'=>22,
            'v2'=>'sdsd'
        ));
    }

    public function testPerson(\com\test\service\Person $person)
    {
        $person->name = $person->name.'sfsf';

        return json_encode($person);
    }
}